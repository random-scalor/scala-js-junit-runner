package com.carrotgarden.sjs.junit

import java.io.File
import org.scalajs.jsenv.ComJSEnv
import org.scalajs.core.tools.linker.ModuleKind

/**
 * Testing session settings.
 */
case class Config(
  // Provided by: scalajs-junit-test-runtime.
  framework : String        = "com.novocode.junit.JUnitFramework",
  envConf :   EnvConf       = EnvConf(),
  webConf :   WebConf       = WebConf(),
  module :    Config.Module = Config.Module(),
  classpath : List[ File ]  = List()
)

object Config {

  case class Module(
    path : String           = "./target/test-classes/META-INF/resources/script-test/runtime-test.js",
    kind : ModuleKind       = ModuleKind.NoModule,
    name : Option[ String ] = None
  )

}
