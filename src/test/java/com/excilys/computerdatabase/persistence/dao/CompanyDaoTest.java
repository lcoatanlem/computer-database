package com.excilys.computerdatabase.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.persistence.DbTesting;
import com.excilys.computerdatabase.persistence.dao.impl.CompanyDaoImpl;

import org.junit.Test;

import java.util.List;

public class CompanyDaoTest extends DbTesting {

  @Test
  /**
   * Tests findAll(), normal use.
   */
  public void testFindAll() {
    CompanyDaoImpl cpnDao = CompanyDaoImpl.getInstance();
    List<Company> liste = cpnDao.findAll(10, 20);
    for (Company comp : liste) {
      assertNotNull(comp.getId());
    }
    assertTrue(liste.size() == 20);
  }

  @Test
  /**
   * Tests findAll(), using wrong values.
   */
  public void testFindAllInvalid() {
    CompanyDaoImpl cpnDao = CompanyDaoImpl.getInstance();
    List<Company> liste = cpnDao.findAll(60, 20);
    assertTrue(liste.size() == 0);
  }

  @Test
  /**
   * Tests findAll(), normal use.
   */
  public void testFind() {
    CompanyDaoImpl cpnDao = CompanyDaoImpl.getInstance();
    Company cpn = Company.builder().build();
    cpn = cpnDao.find(17L);
    assertEquals(cpn.getName(), "Sony");
  }

  @Test
  /**
   * Tests findAll(), with null, should return null.
   */
  public void testFindNull() {
    CompanyDaoImpl cpnDao = CompanyDaoImpl.getInstance();
    Company cpn = Company.builder().build();
    cpn = cpnDao.find(null);
    assertNull(cpn);
  }

  @Test
  /**
   * Tests findAll(), with an invalid id, should throw an exception.
   */
  public void testFindInvalid() {
    CompanyDaoImpl cpnDao = CompanyDaoImpl.getInstance();
    Company cpn = Company.builder().build();
    cpn = cpnDao.find(150L);
    if (cpn != null) {
      fail();
    }
  }
}
