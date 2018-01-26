package com.carrotgarden.sjs.junit;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

/**
 * Runs JUnit 5 test inside JUnit 4 like environment.
 * 
 * Not detected by Scala.js JUnit runtime.
 */
@RunWith(JUnitPlatform.class)
public class BasicJUnit4Test {

	@Test
	public void basicTest() {
		assertTrue(true);
	}

}
