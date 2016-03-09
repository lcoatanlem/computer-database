package com.excilys.computerdatabase.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.excilys.computerdatabase.exception.NotSuchComputerException;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.dao.ComputerDAO;

/**
 * ComputerDAO testing.
 * @author lcoatanlem
 *
 */
public class ComputerDAOTest {
	
	private static ComputerDAO cDAO;
	
	@BeforeClass
	public static void initDAO(){
		cDAO = new ComputerDAO();
	}
	
	@Test
	/**
	 * Tests find(Long id) in a normal use.
	 */
	public void testFind(){
		Computer comp = new Computer("");
		assertNull(comp.getId());
		assertNull(comp.getName());
		try {
			comp = cDAO.find(2L);
		} catch (NotSuchComputerException e) {
			fail();
		}
		assertEquals((Long) 2L, comp.getId());
		assertEquals("Thinking Machines", comp.getName());
	}
	
	@Test
	/**
	 * Tests find(Long id) in an anormal use (id not in db).
	 */
	public void testFindExc(){
		Computer comp = new Computer("");
		Computer comptmp = comp;
		try {
			comptmp = cDAO.find(21L);
			fail();
		} catch (NotSuchComputerException e) {
			assertEquals(comp,comptmp);
		}
	}
	
	@Test
	/**
	 * Tests findAll(), only normal use available.
	 */
	public void testFindAll(){
		List<Computer> liste = cDAO.findAll();
		for (Computer comp : liste){
			assertNotNull(comp.getId());
			assertNotNull(comp.getName());
		}
		assertEquals(42,liste.size());
	}

}
