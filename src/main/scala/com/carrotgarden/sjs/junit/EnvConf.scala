package com.carrotgarden.sjs.junit

import org.scalajs.jsenv.ComJSEnv

/**
 * Scala.js JavaScript VM environment configuration.
 *
 * Provides Node.js by default.
 */
case class EnvConf(
  envType : JSEnv.Type            = JSEnv.Type.NodejsBasic,
  envExec : String                = "./test-tool/node/node",
  envArgs : List[ String ]        = List(),
  envVars : Map[ String, String ] = Map( "NODE_PATH" -> "./test-tool/node/node_modules" )
) {

  def makeEnv() : ComJSEnv = envType.apply( this )

}
