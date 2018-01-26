package com.carrotgarden.sjs.junit

import org.scalajs.jsenv.nodejs.NodeJSEnv
import org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv
import org.scalajs.jsenv.phantomjs.PhantomJSEnv
import org.scalajs.jsenv.ExternalJSEnv

/**
 * Extensions to JS-VM.
 */
object JSEnv {

  trait External extends ExternalJSEnv {

  }

  trait VmName {
    def vmName : String
  }

  class DefaultNodeBasic( config : NodeJSEnv.Config )
    extends NodeJSEnv( config ) with VmName {
    override def vmName : String = getClass.getSimpleName
  }

  class DefaultNodeJsdom( config : JSDOMNodeJSEnv.Config )
    extends JSDOMNodeJSEnv( config ) with VmName {
    override def vmName : String = getClass.getSimpleName
  }

  class DefaultPhantomBasic( config : PhantomJSEnv.Config )
    extends PhantomJSEnv( config ) with VmName {
    override def vmName : String = getClass.getSimpleName
  }

  class CustomNodeBasic( config : NodeJSEnv.Config )
    extends DefaultNodeBasic( config ) with VmName {
    override def vmName : String = getClass.getSimpleName
  }

  class CustomNodeJsdom( config : JSDOMNodeJSEnv.Config )
    extends DefaultNodeJsdom( config ) with VmName {
    override def vmName : String = getClass.getSimpleName
  }

  class CustomPhantomBasic( config : PhantomJSEnv.Config )
    extends DefaultPhantomBasic( config ) with VmName {
    override def vmName : String = getClass.getSimpleName
  }

}
