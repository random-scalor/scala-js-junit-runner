package com.carrotgarden.sjs.junit

import org.junit.jupiter.api.Assertions._;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.junit.runner.JUnitCore

import ScalaJS_SuiteTest._
import org.junit.BeforeClass

/**
 * This test is invoked in JVM.
 */
@RunWith( classOf[ Suite ] )
@Suite.SuiteClasses( Array(
  classOf[ Suite01 ],
  classOf[ Suite02 ],
  classOf[ Suite03 ]
) )
class ScalaJS_SuiteTest

/**
 * These tests are forwarded to JS-VM.
 */
object ScalaJS_SuiteTest {

  @BeforeClass
  def setup : Unit = {
    println( "@@@ setup" )
    val config = Cache.config
    Context.configPersist( config )
    val runtime = Cache.runtime
  }

  @RunWith( classOf[ ScalaJS_Suite ] )
  @Suite.SuiteClasses( Array(
    classOf[ test.Test01 ]
  ) )
  class Suite01

  @RunWith( classOf[ ScalaJS_Suite ] )
  @Suite.SuiteClasses( Array(
    classOf[ test.Test02 ]
  ) )
  class Suite02

  @RunWith( classOf[ ScalaJS_Suite ] )
  @Suite.SuiteClasses( Array(
    classOf[ test.Test03 ]
  ) )
  class Suite03

}
