package com.excilys.computerdatabase.model;

import org.junit.Test;

/**
 * Computer model testing.
 * 
 * @author lcoatanlem
 *
 */
public class ComputerModelTest {

	@Test
	/**
	 * Tests Computer(name), should work.
	 */
	public void testConstr() {
		Computer.builder("Test").build();
	}

	@Test(expected = IllegalArgumentException.class)
	/**
	 * Tests Computer(null), should throw a RuntimeException.
	 */
	public void testConstrException() {
		Computer.builder(null);
	}

	@Test
	/**
	 * Tests setName(name), should work.
	 */
	public void testSetName() {
		Computer cpuTest = Computer.builder("Test").build();
		cpuTest.setName("Tested");
	}

	@Test(expected = IllegalArgumentException.class)
	/**
	 * Tests setName(null), should throw a RuntimeException.
	 */
	public void testSetNameException() {
		Computer cpuTest = Computer.builder("Test").build();
		cpuTest.setName(null);
	}
}
