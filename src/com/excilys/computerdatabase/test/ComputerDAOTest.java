package com.excilys.computerdatabase.test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.excilys.computerdatabase.exception.NotSuchCompanyException;
import com.excilys.computerdatabase.exception.NotSuchComputerException;
import com.excilys.computerdatabase.model.Company;
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
	 * Tests findAll(), only normal use available.
	 */
	public void testFindAll(){
		List<Computer> liste = cDAO.findAll();
		for (Computer comp : liste){
			assertNotNull(comp.getId());
			assertNotNull(comp.getName());
		}
		assertTrue(liste.size() >= 0);
	}

	@Test
	/**
	 * Tests find(Long id) in a normal use.
	 */
	public void testFind(){
		Computer comp = new Computer();
		assertNull(comp.getId());
		assertNull(comp.getName());
		assertNull(comp.getIntroduced());
		assertNull(comp.getDiscontinued());
		assertNull(comp.getManufacturer());
		try {
			comp = cDAO.find(1L);
		} catch (NotSuchComputerException e) {
			fail();
		}
		assertEquals((Long) 1L, comp.getId());
		assertEquals("MacBook Pro 15.4 inch", comp.getName());
		assertEquals(null, comp.getIntroduced());
		assertEquals(null, comp.getDiscontinued());
		assertEquals((Long) 1L, comp.getManufacturer().getId());
		assertEquals("Apple Inc.", comp.getManufacturer().getName());
	}

	@Test
	/**
	 * Tests find(Long id) in an abnormal use (id not in db).
	 */
	public void testFindExc(){
		Computer comp = new Computer();
		Computer comptmp = comp;
		try {
			comptmp = cDAO.find(600L);
			fail();
		} catch (NotSuchComputerException e) {
			assertEquals(comp,comptmp);
		}
	}

	@Test
	/**
	 * Tests create(Computer t) in a normal use, with a computer.
	 */
	public void testCreate(){
		Computer comp = new Computer();
		try{
			comp.setName("Create");
		} catch (NullPointerException e){
			
		}
		comp.setIntroduced(LocalDate.parse("1990-11-10"));
		comp.setDiscontinued(LocalDate.parse("2016-03-09"));
		Company cpn = new Company();
		cpn.setId(2L);
		comp.setManufacturer(cpn);
		try {
			cDAO.create(comp);
		} catch (NotSuchCompanyException e) {
			fail();
		}
	}
	
	@Test
	/**
	 * Tests create(Computer t) in an abnormal, with a wrong company id.
	 */
	public void testCreateNSCExc(){
		Computer comp = new Computer();
		comp.setName("Mine");
		comp.setIntroduced(LocalDate.parse("1990-11-10"));
		comp.setDiscontinued(LocalDate.parse("2016-03-09"));
		Company cpn = new Company();
		cpn.setId(100L);
		comp.setManufacturer(cpn);
		try {
			cDAO.create(comp);
			fail();
		} catch (NotSuchCompanyException e) {}
	}
	
	@Test
	/**
	 * Tests create(Computer t) in an abnormal use, with a null name.
	 */
	public void testCreateNPExc(){
		Computer comp = new Computer();
		try{
			comp.setName(null);
		} catch (NullPointerException e){
			
		}
		comp.setIntroduced(LocalDate.parse("1990-11-10"));
		comp.setDiscontinued(LocalDate.parse("2016-03-09"));
		Company cpn = new Company();
		cpn.setId(100L);
		comp.setManufacturer(cpn);
		try {
			cDAO.create(comp);
			fail();
		} catch (NotSuchCompanyException e) {}
	}
	
	@Test
	/**
	 * Tests update(Computer t) in a normal use, with a null name.
	 */
	public void testUpdate(){
		Computer comp = new Computer();
		comp.setId(576L);
		try{
			comp.setName("Update");
		} catch (NullPointerException e){
			
		}
		comp.setIntroduced(LocalDate.parse("1995-11-10"));
		comp.setDiscontinued(LocalDate.parse("2020-03-09"));
		Company cpn = new Company();
		cpn.setId(34L);
		comp.setManufacturer(cpn);
		try {
			cDAO.update(comp);
		} catch (NotSuchComputerException | NotSuchCompanyException e) {
			fail();
		}
	}
	
	@Test
	/**
	 * Tests update(Computer t) in a normal use, with a null name.
	 */
	public void testUpdateExc(){
		Computer comp = new Computer();
		comp.setId(577L);
		try{
			comp.setName("Update");
		} catch (NullPointerException e){
			
		}
		comp.setIntroduced(LocalDate.parse("1995-11-10"));
		comp.setDiscontinued(LocalDate.parse("2020-03-09"));
		Company cpn = new Company();
		cpn.setId(34L);
		comp.setManufacturer(cpn);
		try {
			cDAO.update(comp);
			fail();
		} catch (NotSuchComputerException | NotSuchCompanyException e) {
		}
	}
	
	@Test
	/**
	 * Tests delete(Long id) in a normal use. Need to uncomment it and put a Long for the id to delete. (deleted each time).
	 */
	public void testDelete(){
		/*try {
			cDAO.delete(609L);
		} catch (NotSuchComputerException e) {
			fail();
		}*/
	}
	
}
