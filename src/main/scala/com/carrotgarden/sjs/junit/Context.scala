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

import sbt.testing.Framework
import java.security.MessageDigest
import org.scalajs.core.tools.jsdep.ResolvedJSDependency
import org.scalajs.core.tools.jsdep.ResolutionInfo
import org.scalajs.jsenv.ComJSEnv
import sbt.testing.Runner
import org.scalajs.testadapter.TestAdapter

/**
 * Testing session setup.
 */
class Context extends Reference {

  def configLocation : File = {
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

  def makeEnvJS( config : Config ) : ComJSEnv = {
    import config._
    JSEnv.Type.apply( envConf.envType ).apply( envConf )
  }

  def makeTester( config : Config ) : TestAdapter = {
    import config.module

    val jsEnv = makeEnvJS( config )
    val jsFiles = scriptList( config )
    val logger = new ManagerLogger()
    val console = new ConsoleLogger()

    val jsLibs = scriptLibs( jsFiles )
    val libEnv = jsEnv.loadLibs( jsLibs )

    val testConfig = TestAdapter.Config()
      .withLogger( logger )
      .withJSConsole( console )
      .withModuleSettings( module.kind, module.name )

    new TestAdapter(
      libEnv,
      testConfig
    )
  }

  def makeRunner(
    args :       Array[ String ] = Array(),
    remoteArgs : Array[ String ] = Array()
  ) : Runner = {
    defaultFramework.runner( Array(), Array(), null )
  }

  val testerCache = TrieMap[ Config, TestAdapter ]()

  def cachedTester( config : Config ) : TestAdapter = {
    testerCache.getOrElseUpdate( config, makeTester( config ) )
  }

  lazy val defaultConfig : Config = {
    configExtract()
  }

  lazy val defaultTester = {
    cachedTester( defaultConfig )
  }

  lazy val defaultFramework : Framework = {
    val names = List( List( defaultConfig.framework ) )
    val result = defaultTester.loadFrameworks( names )
    val framework = result( 0 ).get
    framework
  }

  lazy val defaultRunner : Runner = {
    makeRunner()
  }

}

object Context extends Context  
