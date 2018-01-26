package com.carrotgarden.sjs.junit.test

import org.junit._
import org.junit.Assert._

/**
 * Invoked in JS-VM.
 *
 * Detected by Scala.js JUnit runtime, since using JUnit 4.
 *
 * FIXME pending release
 */
class Test03 {

  // import org.scalajs.jquery.jQuery

  /**
   *
   */
  @Test()
  def verifyJQuery() {
    println( "### " + classOf[ Test03 ].getName )
    // jQuery( "body" ).append( "<p>Hello World</p>" )
  }

}
