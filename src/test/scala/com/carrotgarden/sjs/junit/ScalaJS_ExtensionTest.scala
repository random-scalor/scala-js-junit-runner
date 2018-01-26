package com.carrotgarden.sjs.junit

import org.junit.jupiter.api.Assertions._;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith( classOf[ JUnitPlatform ] )
class ScalaJS_ExtensionTest {

  @Test
  def basicTest() : Unit = {
    assertTrue( true )
  }

}
