package com.carrotgarden.sjs.junit.test

import org.junit.Test
import org.junit.Assert._
import org.junit.Ignore

/**
 * Invoked in JS-VM.
 *
 * Detected by Scala.js JUnit runtime, since using JUnit 4.
 */
class Test04 {

  @Test
  def verifyPrint() {
    println( s"### Message from JS-VM ${getClass.getName} ###" )
  }

  @Test
  def verifyVM() {
    // https://github.com/scala-js/scala-js/blob/master/javalanglib/src/main/scala/java/lang/System.scala
    assertEquals( "Running in Scala.js VM", System.getProperty( "java.vm.name" ), "Scala.js" )
  }

  @Ignore
  @Test
  def verifyIgnore() {
    throw new Exception( "Should not happen." )
  }

}
