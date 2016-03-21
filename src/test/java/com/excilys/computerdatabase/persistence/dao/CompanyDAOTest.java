package com.excilys.computerdatabase.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import com.excilys.computerdatabase.exception.NoSuchCompanyException;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.persistence.dao.impl.CompanyDaoImpl;
import com.excilys.computerdatabase.persistence.DBTesting;

public class CompanyDAOTest extends DBTesting {

	@Test
	/**
	 * Tests findAll(), normal use.
	 */
	public void testFindAll(){
		CompanyDaoImpl cDao = new CompanyDaoImpl();
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
		CompanyDaoImpl cDao = new CompanyDaoImpl();
		List<Company> liste = cDao.findAll(60,20);
		assertTrue(liste.size() == 0);
	}

	@Test
	/**
	 * Tests findAll(), normal use.
	 */
	public void testFind(){
		CompanyDaoImpl cDao = new CompanyDaoImpl();
		Company cpn = new Company();
		try {
			cpn = cDao.find(17L);
		} catch (NoSuchCompanyException e) {
			fail();
		}
		assertEquals(cpn.getName(),"Sony");
	}

	@Test
	/**
	 * Tests findAll(), with null, should return null.
	 */
	public void testFindNull(){
		CompanyDaoImpl cDao = new CompanyDaoImpl();
		Company cpn = new Company();
		try {
			cpn = cDao.find(null);
		} catch (NoSuchCompanyException e) {
			fail();
		}
		assertNull(cpn);
	}

	@Test
	/**
	 * Tests findAll(), with an invalid id, should throw an exception.
	 */
	public void testFindInvalid(){
		CompanyDaoImpl cDao = new CompanyDaoImpl();
		Company cpn = new Company();
		Company cpntmp = cpn;
		try {
			cpn = cDao.find(150L);
			fail();
		} catch (NoSuchCompanyException e) {
			assertEquals(cpn,cpntmp);
		}
	}
}
