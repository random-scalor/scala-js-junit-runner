package com.carrotgarden.sjs.junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Runs JUnit 5 test inside JUnit 5 full environment.
 * 
 * Not detected by Scala.js JUnit runtime.
 */
public class BasicJUnit5Test {

	@BeforeEach
	public void setUp() throws Exception {
	}

	@AfterEach
	public void tearDown() throws Exception {
	}

	@Test
	public void basicTest() {
		assertTrue(true);
	}

}
