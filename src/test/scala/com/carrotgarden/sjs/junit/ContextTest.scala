package com.carrotgarden.sjs.junit

import org.junit.jupiter.api.Assertions._;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform

import java.io.File
import org.junit.runner.RunWith
import sbt.testing.TaskDef

/**
 * Invoked in JVM.
 */
@RunWith( classOf[ JUnitPlatform ] )
class ContextTest {

  lazy val classpath = Classer.currentClassPath

  lazy val config = {
    val module = Config.Module( path = "./target/frameworkTest.js" )
    Config( module = module )
  }

  import Context._

  @Test
  def serializeTest() {
    configLocation.delete()
    val source = Config()
    configPersist( source )
    val target = configExtract()
    //    println( s"source ${source}" )
    //    println( s"target ${target}" )
    assertEquals( source, target )
  }

  @Test
  def frameworkTest() {
    //    val framework = SuiteSetup.framework
    //    val runner = framework.runner( Array(), Array(), getClass.getClassLoader );
    //    val taskDefs = Array[ TaskDef ]()
    //    runner.tasks( taskDefs )
    //    runner.done()
  }

}
