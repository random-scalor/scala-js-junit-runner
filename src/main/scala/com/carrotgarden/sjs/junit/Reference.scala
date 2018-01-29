package com.carrotgarden.sjs.junit

import com.typesafe.config.ConfigFactory

/**
 * Library settings.
 */
trait Reference {

  import Reference._

  /**
   * Library settings.
   */
  val referenceKey = "scala-js-junit-tools"

  /**
   * Global name space.
   */
  lazy val referenceLoad = {
    ConfigFactory.load( this.getClass.getClassLoader )
  }

  /**
   * Local name space.
   */
  lazy val referenceConfig = {
    referenceLoad.getConfig( referenceKey )
  }

  /**
   * Location of serialized configuration.
   */
  lazy val referenceLocation = referenceConfig.getString( "location" )

  /**
   * Origin of JUnit test started/finished events.
   */
  lazy val referenceJUnitEvent = JUnitEvent( referenceConfig.getConfig( "junit-event" ) )

}

object Reference {

  import com.typesafe.config.Config

  case class JUnitEvent( config : Config ) {
    val printDebug = config.getBoolean( "print-debug" )
    val fireTest = FireMode( config.getConfig( "fire-test" ) )
    val fireMethod = FireMode( config.getConfig( "fire-method" ) )
    val regex = EventRegex( config.getConfig( "regex" ) )
  }

  case class EventRegex( config : Config ) {
    val testStarted = config.getString( "test-started" ).r
    val testFinished = config.getString( "test-finished" ).r
    val methodStarted = config.getString( "method-started" ).r
    val methodFinished = config.getString( "method-finished" ).r
  }

  case class FireMode( config : Config ) {
    val fromLogs = config.getBoolean( "from-logs" )
    val fromRuns = config.getBoolean( "from-runs" )
  }

}
