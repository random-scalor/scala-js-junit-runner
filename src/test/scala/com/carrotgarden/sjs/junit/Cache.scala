package com.carrotgarden.sjs.junit

import java.io.File
import sbt.testing.Framework
import org.scalajs.testadapter.ScalaJSFramework

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

  lazy val tester : ScalaJSFramework = {
    println( "XXX tester" )
    runtime
    Context.cachedTester( config )
  }

  lazy val framework : Framework = {
    println( "XXX framework" )
    tester
  }

}

object Cache extends Cache
