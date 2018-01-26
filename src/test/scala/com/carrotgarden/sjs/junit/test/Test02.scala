package com.carrotgarden.sjs.junit.test

import org.junit._
import org.junit.Assert._

/**
 * Invoked in JS-VM.
 *
 * Detected by Scala.js JUnit runtime, since using JUnit 4.
 */
class Test02 {

  /**
   * Not supported in Scala.js: "expected".
   */
  @Ignore
  @Test( expected = classOf[ Exception ] )
  def verifyException() {
    println( "### " + classOf[ Test02 ].getName )
    throw new Exception()
  }

}
