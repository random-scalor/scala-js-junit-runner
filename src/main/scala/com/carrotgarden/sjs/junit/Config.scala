package com.carrotgarden.sjs.junit

import java.io.File
import org.scalajs.jsenv.ComJSEnv
import org.scalajs.core.tools.linker.ModuleKind

import Config._

/**
 * Scala.js JavaScript VM testing session configuration.
 */
@SerialVersionUID( 1L )
case class Config(
  framework : String  = "com.novocode.junit.JUnitFramework",
  envConf :   EnvConf = EnvConf(),
  webConf :   WebConf = WebConf(),
  module :    Module  = Module()
)

object Config {

  /**
   * Scala.js JavaScript VM linker module.
   */
  @SerialVersionUID( 1L )
  case class Module(
    path : String = "./target/test-classes/META-INF/resources/script-test/runtime-test.js",
    // serializable
    kind : ModuleKind = ModuleKind.NoModule,
    // serializable
    name : Option[ String ] = None
  )

  /**
   * Scala.js JavaScript VM environment configuration.
   */
  @SerialVersionUID( 1L )
  case class EnvConf(
    envType : String = "nodejs-jsdom",
    envExec : String = "./test-tool/node/node",
    // serializable
    envArgs : List[ String ] = List(),
    // serializable
    envVars : Map[ String, String ] = Map( "NODE_PATH" -> "./test-tool/node/node_modules" )
  )

  /**
   * Webjars provisioning configuration.
   *
   * @param webjarsDir
   * 	webjars extract folder
   * @param scriptList
   * 	list of extracted scripts to make available during tests
   * 	these scripts are provided by some webjars
   *  these are relative paths, with version erasure
   *  example:
   *  archive entry: META-INF/resources/webjars/jquery/3.2.1/dist/jquery.js
   *  extract entry: jquery/dist/jquery.js
   */
  @SerialVersionUID( 1L )
  case class WebConf(
    webjarsDir : String = "./test-tool/webjars",
    // serializable
    scriptList : Seq[ String ] = Seq()
  )

}
