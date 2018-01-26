package com.carrotgarden.sjs.junit

import java.io.File
import java.io.FileFilter
import org.scalajs.core.tools.io.VirtualJarFile
import org.scalajs.core.tools.io.FileVirtualBinaryFile
import java.nio.file.Files
import scala.collection.JavaConverters._
import org.scalajs.core.tools.io.VirtualFileContainer
import org.scalajs.jsdependencies.core.JSDependencyManifest
import java.io.InputStreamReader
import java.net.URLClassLoader

/**
 * Class path support.
 */
object Classer {

  def jsDependencyManifestsInJar(
    container : VirtualFileContainer
  ) : List[ JSDependencyManifest ] = {
    container.listEntries( _ == JSDependencyManifest.ManifestFileName ) {
      ( _, stream ) =>
        JSDependencyManifest.read( new InputStreamReader( stream, "UTF-8" ) )
    }
  }

  /**
   * Selects descendants of `base` directory matching `filter` and maps them to a path relative to `base`.
   * `base` itself is not included.
   */
  def selectSubpaths( base : File, filter : FileFilter ) : Traversable[ ( File, String ) ] = {
    val root = base.toPath()
    Files.newDirectoryStream( root ).iterator().asScala
      .filter( entry => filter.accept( entry.toFile() ) )
      .map( entry => ( entry.toFile(), root.relativize( entry ).toString() ) )
      .toList
  }

  val filterJS = new FileFilter {
    override def accept( file : File ) =
      file.exists() && file.isFile() && file.getName.endsWith( ".js" )
  }

  val filterJar = new FileFilter {
    override def accept( file : File ) =
      file.exists() && file.isFile() && file.getName.endsWith( ".jar" )
  }

  val filterDir = new FileFilter {
    override def accept( file : File ) =
      file.exists() && file.isDirectory()
  }

  def collectFromClasspath[ T ](
    classpath :   Seq[ File ],
    collectJar :  VirtualJarFile => Seq[ T ],
    collectFile : ( File, String ) => T,
    scanJar :     FileFilter                 = filterJar,
    scanDir :     FileFilter                 = filterDir,
    scanJS :      FileFilter                 = filterJS
  ) : ( Seq[ File ], Seq[ T ] ) = {

    val realFiles = Seq.newBuilder[ File ]
    val virtFiles = Seq.newBuilder[ T ]

    for (
      entry <- classpath // if entry.exists
    ) {
      if ( scanJar.accept( entry ) ) {
        realFiles += entry
        val jarFile = new FileVirtualBinaryFile( entry ) with VirtualJarFile
        virtFiles ++= collectJar( jarFile )
      } else if ( scanDir.accept( entry ) ) {
        for {
          ( file, rawPath ) <- selectSubpaths( entry, scanJS )
        } {
          val relPath = rawPath.replace( File.separatorChar, '/' )
          realFiles += file
          virtFiles += collectFile( file, relPath )
        }
      } else {
        throw new IllegalArgumentException(
          "Illegal classpath entry: " + entry.getPath
        )
      }
    }

    ( realFiles.result(), virtFiles.result() )
  }

  def classpathFrom( config : Config ) = {

  }

  // FIXME
  def currentClassPath : Seq[ File ] = {

    //    val loader = getClass.getClassLoader.asInstanceOf[ URLClassLoader ]
    //    loader.getURLs.map( url => new File( url.toURI() ) ).toSeq

    val classpath = System.getProperty( "java.class.path" );
    val entryList = classpath.split( File.pathSeparator );
    entryList.map( entry => new File( entry ) )

  }

}
