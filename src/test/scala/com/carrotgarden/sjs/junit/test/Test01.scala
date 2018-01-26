package com.carrotgarden.sjs.junit.test

import org.junit.Test
import org.junit.Assert._

/**
 * Invoked in JS-VM.
 *
 * Detected by Scala.js JUnit runtime, since using JUnit 4.
 */
class Test01 {

  // https://github.com/scala-js/scala-js/blob/master/javalanglib/src/main/scala/java/lang/System.scala

  @Test
  def verifyVM() {
    println( "### " + classOf[ Test01 ].getName )
    assertEquals( "Running in Scala.js VM", System.getProperty( "java.vm.name" ), "Scala.js" )
  }

}
