package com.carrotgarden.sjs.junit

import org.scalajs.jsenv.nodejs.NodeJSEnv
import org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv
import org.scalajs.jsenv.phantomjs.PhantomJSEnv
import org.scalajs.jsenv.ExternalJSEnv
import org.scalajs.jsenv.ComJSEnv
import org.scalajs.core.tools.jsdep.ResolvedJSDependency
import org.scalajs.core.tools.io.VirtualJSFile
import org.scalajs.jsenv.JSRunner
import org.scalajs.jsenv.AsyncJSRunner
import org.scalajs.jsenv.ComJSRunner

/**
 * Extensions to JS-VM.
 */
object JSEnv {

  trait Type {
    val name : String
    def apply( config : EnvConf ) : ComJSEnv with ExternalJSEnv
  }

  object Type {

    case object NodejsBasic extends Type {
      override val name = "nodejs-basic"
      override def apply( envConf : EnvConf ) = {
        import envConf._
        val setup = NodeJSEnv.Config()
          .withExecutable( envExec )
          .withArgs( envArgs )
          .withEnv( envVars )
          .withSourceMap( true )
        new JSEnv.NodeBasic( setup )
      }
    }

    case object NodejsJsdom extends Type {
      override val name = "nodejs-jsdom"
      override def apply( envConf : EnvConf ) = {
        import envConf._
        val setup = JSDOMNodeJSEnv.Config()
          .withExecutable( envExec )
          .withArgs( envArgs )
          .withEnv( envVars )
        new JSEnv.NodeJsdom( setup )
      }
    }

    case object PhandomjsBasic extends Type {
      override val name = "phantomjs-basic"
      override def apply( envConf : EnvConf ) = {
        import envConf._
        val setup = PhantomJSEnv.Config()
          .withExecutable( envExec )
          .withArgs( envArgs )
          .withEnv( envVars )
          .withAutoExit( true )
        new JSEnv.PhantomBasic( setup )
      }
    }

  }

  trait VmName {
    def vmName : String
  }

  class NodeBasic( config : NodeJSEnv.Config )
    extends NodeJSEnv( config ) with VmName {
    override def vmName : String = Type.NodejsBasic.name

    println( s"############################" )

    trait CustomRunner extends AbstractNodeRunner {

      //new Error("ZZZZZZZZZZZZZZZZZZZZ").printStackTrace()

      override protected def startVM() : Process = {
        val envArgs = getVMArgs()
        val envVars = getVMEnv()
        val argsList = executable +: envArgs
        val builder = new ProcessBuilder( argsList : _* )
        builder.environment().clear()
        for ( ( name, value ) <- envVars ) builder.environment().put( name, value )
        logger.debug( "XXX Starting process: " + argsList.mkString( " " ) )
        builder.start()
      }

    }

    override def jsRunner( libs : Seq[ ResolvedJSDependency ], code : VirtualJSFile ) : JSRunner = {
      println("ZZZZZZZZZZ jsRunner")
      new NodeRunner( libs, code )
    }
    override def asyncRunner( libs : Seq[ ResolvedJSDependency ], code : VirtualJSFile ) : AsyncJSRunner = {
      println("ZZZZZZZZZZ asyncRunner")
      new AsyncNodeRunner( libs, code )
    }
    override def comRunner( libs : Seq[ ResolvedJSDependency ], code : VirtualJSFile ) : ComJSRunner = {
      println("ZZZZZZZZZZ comRunner")
      new ComNodeRunner( libs, code )
    }
    protected class NodeRunner( libs : Seq[ ResolvedJSDependency ], code : VirtualJSFile )
      extends ExtRunner( libs, code ) with AbstractBasicNodeRunner with CustomRunner
    protected class AsyncNodeRunner( libs : Seq[ ResolvedJSDependency ], code : VirtualJSFile )
      extends AsyncExtRunner( libs, code ) with AbstractBasicNodeRunner with CustomRunner
    protected class ComNodeRunner( libs : Seq[ ResolvedJSDependency ], code : VirtualJSFile )
      extends AsyncNodeRunner( libs, code ) with NodeComJSRunner

  }

  class NodeJsdom( config : JSDOMNodeJSEnv.Config )
    extends JSDOMNodeJSEnv( config ) with VmName {
    override def vmName : String = Type.NodejsJsdom.name
  }

  class PhantomBasic( config : PhantomJSEnv.Config )
    extends PhantomJSEnv( config ) with VmName {
    override def vmName : String = Type.PhandomjsBasic.name
  }

}
