package com.carrotgarden.sjs.junit

import org.junit.jupiter.api.Assertions._;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import com.typesafe.config.ConfigFactory
import java.util.Properties

@RunWith( classOf[ JUnitPlatform ] )
class ReferenceTest {

  class RefTest extends Reference

  def withUserDir( testDir : String )( block : => Unit ) = {
    val key = "user.dir"
    val userDir = sys.props( key )
    try {
      if ( testDir == null ) {
        sys.props.remove( key )
      } else {
        sys.props.put( key, testDir )
      }
      println( s"[sys.props] ${key}=${sys.props( key )}" )
      block
    } finally {
      sys.props.put( key, userDir )
    }
  }

  @Test
  def verifyLocationAnyPath {
    ConfigFactory.invalidateCaches()
    val ref = new RefTest
    val basePath = ref.referenceBasePath
    assertEquals( basePath, s"target/scala-js-junit-tools/default.json" )
  }

  @Test
  def verifyLocationBaseDir {
    val baseDir = "" + System.nanoTime()
    sys.props.put( "basedir", baseDir )
    withUserDir( null ) {
      assertTrue( sys.props( "user.dir" ) == null )
      assertTrue( sys.props( "basedir" ) == baseDir )
      ConfigFactory.invalidateCaches()
      val ref = new RefTest
      val location = ref.referenceLocation
      val basePath = ref.referenceBasePath
      println( s"location ${location}" )
      assertEquals( location, s"${baseDir}/${basePath}" )
    }
  }

  @Test
  def verifyLocationUserDir {
    val baseDir = "" + System.nanoTime()
    sys.props.remove( "basedir" )
    withUserDir( baseDir ) {
      assertTrue( sys.props( "user.dir" ) == baseDir )
      assertTrue( sys.props( "basedir" ) == null )
      ConfigFactory.invalidateCaches()
      val ref = new RefTest
      val location = ref.referenceLocation
      val basePath = ref.referenceBasePath
      println( s"location ${location}" )
      assertEquals( location, s"${baseDir}/${basePath}" )
    }
  }

  @Test
  def verifyJUnitEvent {
    ConfigFactory.invalidateCaches()
    val ref = new RefTest
    val referenceEvent = ref.referenceJUnitEvent
    import referenceEvent._
    assertTrue( fireTest.fromRuns )
    assertTrue( fireMethod.fromLogs )
    assertFalse( fireTest.fromLogs )
    assertFalse( fireMethod.fromRuns )
  }

}
