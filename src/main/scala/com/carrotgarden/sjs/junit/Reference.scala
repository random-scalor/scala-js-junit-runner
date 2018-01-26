package com.carrotgarden.sjs.junit

import com.typesafe.config.ConfigFactory

/**
 * Library settings.
 */
trait Reference {

  /**
   * Library settings.
   */
  val referenceKey = "scala-js-junit-tools"

  /**
   * Global name space.
   */
  def referenceLoad = {
    ConfigFactory.load( this.getClass.getClassLoader )
  }

  /**
   * Local name space.
   */
  def referenceConfig = {
    referenceLoad.getConfig( referenceKey )
  }

  /**
   * Location of serialized configuration.
   */
  def referenceLocation = referenceConfig.getString( "location" )

}
