package com.carrotgarden.sjs.junit

import org.scalajs.jsenv.ComJSEnv
import org.scalajs.jsenv.ExternalJSEnv
import org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv
import org.scalajs.jsenv.nodejs.NodeJSEnv
import org.scalajs.jsenv.phantomjs.PhantomJSEnv

/**
 * Scala.js JavaScript VM environment types.
 *
 * Provides Node.js/JsDom by default.
 */
object EnvType {

  def apply( name : String ) = name match {
    case EnvTypeNodejs.name      => EnvTypeNodejs
    case EnvTypeJsdomNodejs.name => EnvTypeJsdomNodejs
    case EnvTypePhandomjs.name   => EnvTypePhandomjs
    case _                       => EnvTypeJsdomNodejs
  }

}

sealed trait EnvType {
  val name : String
  def apply( config : EnvConf ) : ComJSEnv with ExternalJSEnv
}

case object EnvTypeNodejs extends EnvType {
  override val name = "scalajs-env-nodejs"
  override def apply( envConf : EnvConf ) = {
    import envConf._
    val setup = NodeJSEnv.Config()
      .withExecutable( envExec )
      .withArgs( envArgs )
      .withEnv( envVars )
      .withSourceMap( true )
    new NodeJSEnv( setup )
  }
}

case object EnvTypeJsdomNodejs extends EnvType {
  override val name = "scalajs-env-jsdom-nodejs"
  override def apply( envConf : EnvConf ) = {
    import envConf._
    val setup = JSDOMNodeJSEnv.Config()
      .withExecutable( envExec )
      .withArgs( envArgs )
      .withEnv( envVars )
    new JSDOMNodeJSEnv( setup )
  }
}

case object EnvTypePhandomjs extends EnvType {
  override val name = "scalajs-env-phantomjs"
  override def apply( envConf : EnvConf ) = {
    import envConf._
    val setup = PhantomJSEnv.Config()
      .withExecutable( envExec )
      .withArgs( envArgs )
      .withEnv( envVars )
      .withAutoExit( true )
    new PhantomJSEnv( setup )
  }
}
