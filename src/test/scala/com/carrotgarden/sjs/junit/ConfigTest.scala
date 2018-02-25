package com.carrotgarden.sjs.junit

import org.junit.jupiter.api.Assertions._;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform

import java.io.File
import org.junit.runner.RunWith

@RunWith( classOf[ JUnitPlatform ] )
class ConfigTest {

  import Config._

  def makeConfig = {
    val envConf = EnvConf(
      envType = "envType",
      envExec = "envExec",
      envArgs = List( "1", "2" ),
      envVars = Map( "k1" -> "v1", "k2" -> "v2" )
    )
    val webConf = WebConf(
      webjarsDir = "webjarsDir",
      scriptList = Seq( "1", "2" )
    )
    val module = Module(
      path = "path"
    )
    Config(
      envConf = envConf,
      webConf = webConf,
      module  = module
    )
  }

  val textConfig = """
{
    "envConf": {
        "envType": "envType",
        "envExec": "envExec",
        "envArgs": [
            "1",
            "2"
        ],
        "envVars": {
            "k1": "v1",
            "k2": "v2"
        }
    },
    "webConf": {
        "webjarsDir": "webjarsDir",
        "scriptList": [
            "1",
            "2"
        ]
    },
    "module": {
        "path": "path"
    }
}
  """

  @Test
  def codecTest() {
    val source = makeConfig
    val sourceResult = Config.configUnparse( source )
    val targetResult = textConfig
    val target = Config.configParse( targetResult )
    //    println( s"sourceResult ${sourceResult}" )
    //    println( s"targetResult ${targetResult}" )
    assertEquals( source, target )
  }

}
