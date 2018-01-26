package com.carrotgarden.sjs.junit

import java.io.File
import sbt.testing.Framework
import org.scalajs.testadapter.TestAdapter

class Cache {

  lazy val classpath : Seq[ File ] = {
    println( "XXX classpath" )
    Classer.currentClassPath
  }

  lazy val config : Config = {
    println( "XXX config" )
    val module = Config.Module( path = "./target/junit-tools.js" )
    Config( module = module )
  }

  lazy val runtime : File = {
    println( "XXX runtime" )
    val runtime = new File( config.module.path )
    Linker.link( classpath, runtime )
    runtime
  }

  lazy val tester : TestAdapter = {
    println( "XXX tester" )
    runtime
    Context.cachedTester( config )
  }

  lazy val framework : Framework = {
    println( "XXX framework" )
    val names = List( List( config.framework ) )
    val result = tester.loadFrameworks( names )
    val framework = result( 0 ).get
    framework
  }

}

object Cache extends Cache
