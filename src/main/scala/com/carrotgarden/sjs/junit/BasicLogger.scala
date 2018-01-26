package com.carrotgarden.sjs.junit

/*
 * https://github.com/scala-js/scala-js/blob/02be3eafcce8d2c43ae4b133969a7d5817b74bc8/tools/js/src/test/scala/org/scalajs/core/tools/test/js/TestRunner.scala
 */

import sbt.testing.Logger

/**
 * A basic Logger for testing
 */
class BasicLogger extends Logger {

  def ansiCodesSupported() : Boolean = false

  def error( message : String ) : Unit = println( message )

  def warn( message : String ) : Unit = println( message )

  def info( message : String ) : Unit = println( message )

  def debug( message : String ) : Unit = println( message )

  def trace( error : Throwable ) : Unit = error.printStackTrace()

}
