package com.carrotgarden.sjs.junit

import java.io.File
import java.io.FileFilter
import org.scalajs.core.tools.io.VirtualJarFile
import org.scalajs.core.tools.io.FileVirtualBinaryFile
import java.nio.file.Files
import scala.collection.JavaConverters._
import java.io.InputStreamReader
import java.net.URLClassLoader

/**
 * Classes support.
 */
object Classer {

  // FIXME
  def currentClassPath : Seq[ File ] = {

    //    val loader = getClass.getClassLoader.asInstanceOf[ URLClassLoader ]
    //    loader.getURLs.map( url => new File( url.toURI() ) ).toSeq

    val classpath = System.getProperty( "java.class.path" );
    val entryList = classpath.split( File.pathSeparator );
    entryList.map( entry => new File( entry ) )

  }

}
