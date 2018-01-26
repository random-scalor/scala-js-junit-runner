package com.carrotgarden.sjs.junit

import org.junit.jupiter.api.extension.TestInstancePostProcessor
import org.junit.jupiter.api.extension.ExtensionContext

/**
 * JUnit 5 extension for Scala.js.
 *
 * TODO
 */
class ScalaJS_Extension extends TestInstancePostProcessor {

  def postProcessTestInstance( instance : Any, context : ExtensionContext ) : Unit = {
    //
  }

}
