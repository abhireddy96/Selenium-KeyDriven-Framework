package com.abhi.reddy.testcases;

import org.testng.annotations.Test;

import com.abhi.reddy.commons.Listners;

/**
 * The Class TestCases.
 */
public class TestCases extends Listners {

	/**
	 * Test.
	 */
	@Test
	public void test() {

		TestExecutor.testSuiteDriver();
		
	}
	
}
