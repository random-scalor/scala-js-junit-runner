package com.carrotgarden.sjs.junit

import java.io.File

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
case class WebConf(
  webjarsDir : String        = "./test-tool/webjars",
  scriptList : Seq[ String ] = Seq()
)

object WebConf {

}
