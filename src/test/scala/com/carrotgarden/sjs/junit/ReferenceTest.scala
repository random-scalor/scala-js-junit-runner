package com.carrotgarden.sjs.junit

import org.junit.jupiter.api.Assertions._;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith( classOf[ JUnitPlatform ] )
class ReferenceTest {

  class RefTest extends Reference

  @Test
  def verifyJUnitEvent {

    val ref = new RefTest
    import ref.referenceJUnitEvent._

    assertTrue( fireTest.fromRuns )
    assertTrue( fireMethod.fromLogs )
    assertFalse( fireTest.fromLogs )
    assertFalse( fireMethod.fromRuns )

  }

}
