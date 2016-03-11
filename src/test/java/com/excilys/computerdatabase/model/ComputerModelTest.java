package test.java.com.excilys.computerdatabase.model;

import org.junit.Test;

import main.java.com.excilys.computerdatabase.model.Computer;

/**
 * Computer model testing.
 * @author lcoatanlem
 *
 */
public class ComputerModelTest {
	
	@Test
	/**
	 * Tests Computer(name), should work.
	 */
	public void testConstr(){
		new Computer("Test");
	}
	
	@Test(expected=IllegalArgumentException.class)
	/**
	 * Tests Computer(null), should throw a RuntimeException.
	 */
	public void testConstrException(){
		new Computer(null);
	}
	
	@Test
	/**
	 * Tests setName(name), should work.
	 */
	public void testSetName(){
		Computer cpuTest = new Computer("Test");
		cpuTest.setName("Tested");
	}
	
	@Test(expected=IllegalArgumentException.class)
	/**
	 * Tests setName(null), should throw a RuntimeException.
	 */
	public void testSetNameException(){
		Computer cpuTest = new Computer("Test");
		cpuTest.setName(null);
	}
}
