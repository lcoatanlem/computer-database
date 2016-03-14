package test.java.com.excilys.computerdatabase.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import main.java.com.excilys.computerdatabase.exception.NotSuchCompanyException;
import main.java.com.excilys.computerdatabase.model.Company;
import main.java.com.excilys.computerdatabase.persistence.dao.impl.CompanyDAOImpl;
import test.java.com.excilys.computerdatabase.persistence.DBTesting;

public class CompanyDAOTest extends DBTesting {

	@Test
	/**
	 * Tests findAll(), normal use.
	 */
	public void testFindAll(){
		CompanyDAOImpl cDao = new CompanyDAOImpl();
		List<Company> liste = cDao.findAll(10,20);
		for (Company comp : liste){
			assertNotNull(comp.getId());
		}
		assertTrue(liste.size() == 20);
	}

	@Test
	/**
	 * Tests findAll(), using wrong values.
	 */
	public void testFindAllInvalid(){
		CompanyDAOImpl cDao = new CompanyDAOImpl();
		List<Company> liste = cDao.findAll(60,20);
		assertTrue(liste.size() == 0);
	}

	@Test
	/**
	 * Tests findAll(), normal use.
	 */
	public void testFind(){
		CompanyDAOImpl cDao = new CompanyDAOImpl();
		Company cpn = new Company();
		try {
			cpn = cDao.find(17L);
		} catch (NotSuchCompanyException e) {
			fail();
		}
		assertEquals(cpn.getName(),"Sony");
	}

	@Test
	/**
	 * Tests findAll(), with null, should return null.
	 */
	public void testFindNull(){
		CompanyDAOImpl cDao = new CompanyDAOImpl();
		Company cpn = new Company();
		try {
			cpn = cDao.find(null);
		} catch (NotSuchCompanyException e) {
			fail();
		}
		assertNull(cpn);
	}

	@Test
	/**
	 * Tests findAll(), with an invalid id, should throw an exception.
	 */
	public void testFindInvalid(){
		CompanyDAOImpl cDao = new CompanyDAOImpl();
		Company cpn = new Company();
		Company cpntmp = cpn;
		try {
			cpn = cDao.find(150L);
			fail();
		} catch (NotSuchCompanyException e) {
			assertEquals(cpn,cpntmp);
		}
	}
}
