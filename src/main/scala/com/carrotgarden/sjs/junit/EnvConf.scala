package com.carrotgarden.sjs.junit

import org.scalajs.jsenv.ComJSEnv

/**
 * Scala.js JavaScript VM environment configuration.
 *
 * Provides Node.js by default.
 */
case class EnvConf(
  envType : EnvType               = EnvTypeNodejs,
  envExec : String                = "./test-tool/node/node",
  envArgs : List[ String ]        = List(),
  envVars : Map[ String, String ] = Map( "NODE_PATH" -> "./test-tool/node/node_modules" )
) extends Serializable {

  def makeEnv() : ComJSEnv = envType.apply( this )

}
