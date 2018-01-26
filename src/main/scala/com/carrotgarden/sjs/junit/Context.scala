package com.carrotgarden.sjs.junit

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.lang.management.ManagementFactory

import scala.collection.concurrent.TrieMap

import org.scalajs.core.tools.io.FileVirtualJSFile
import org.scalajs.core.tools.io.VirtualJSFile
import org.scalajs.core.tools.logging.Logger
import org.scalajs.core.tools.logging.ScalaConsoleLogger
import org.scalajs.jsenv.ConsoleJSConsole
import org.scalajs.jsenv.JSConsole
import org.scalajs.testadapter.ScalaJSFramework

import sbt.testing.Framework
import java.security.MessageDigest
import org.scalajs.core.tools.jsdep.ResolvedJSDependency
import org.scalajs.core.tools.jsdep.ResolutionInfo

/**
 * Testing session configuration.
 */
class Context extends Reference {

  def hex( array : Array[ Byte ] ) : String = array.map( "%02x" format _ ).mkString

  def identity : String = {
    val basedir = System.getProperty( "user.dir" )
    val folder = new File( basedir ).getAbsolutePath
    val codec = MessageDigest.getInstance( "MD5" )
    codec.update( folder.getBytes( "UTF-8" ) )
    val digest = codec.digest()
    val summary = hex( digest )
    summary
  }

  def configLocation = {
    val location = new File( referenceLocation ).getAbsoluteFile
    val folder = location.getParentFile
    folder.mkdirs()
    location
  }

  def configExtract() : Config = {
    val fileInput = new FileInputStream( configLocation )
    val objectInput = new ObjectInputStream( fileInput )
    val config = objectInput.readObject.asInstanceOf[ Config ]
    objectInput.close
    config
  }

  def configPersist( config : Config ) : Unit = {
    val fileOutput = new FileOutputStream( configLocation )
    val objectOutput = new ObjectOutputStream( fileOutput )
    objectOutput.writeObject( config )
    objectOutput.close
  }

  def scriptList( config : Config ) : Seq[ VirtualJSFile ] = {
    import config._
    val webDir = new File( webConf.webjarsDir )
    val webList = webConf.scriptList.map { entry =>
      FileVirtualJSFile( new File( webDir, entry ) )
    }
    val moduleList = Seq( FileVirtualJSFile( new File( module.path ) ) )
    webList ++ moduleList
  }

  def scriptLibs( jsFiles : Seq[ VirtualJSFile ] ) : Seq[ ResolvedJSDependency ] = {
    jsFiles.map { file => ResolvedJSDependency.minimal( file ) }
  }

  def makeTester( config : Config ) : ScalaJSFramework = {
    import config.module

    val jsEnv = config.envConf.makeEnv()
    val jsFiles : Seq[ VirtualJSFile ] = scriptList( config )
    val logger : Logger = new ScalaConsoleLogger()
    val console : JSConsole = ConsoleJSConsole

    val jsLibs = scriptLibs( jsFiles )
    val libEnv = jsEnv.loadLibs( jsLibs )

    new ScalaJSFramework(
      frameworkName    = config.framework,
      libEnv           = libEnv,
      moduleKind       = module.kind,
      moduleIdentifier = module.name,
      logger           = logger,
      jsConsole        = console
    )

  }

  val testerCache = TrieMap[ Config, ScalaJSFramework ]()

  def cachedTester( config : Config ) : ScalaJSFramework = {
    testerCache.getOrElseUpdate( config, makeTester( config ) )
  }

  lazy val defaultConfig = {
    configExtract()
  }

  lazy val defaultTester = {
    cachedTester( defaultConfig )
  }

  lazy val defaultFramework : Framework = {
    defaultTester
  }

}

object Context extends Context  
