package com.excilys.computerdatabase.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.persistence.DbTesting;
import com.excilys.computerdatabase.persistence.mapping.query.Query;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CompanyDaoTest extends DbTesting {

  @Autowired
  @Qualifier("companyDao")
  private Dao<Company> companyDao;

  @Test
  /**
   * Tests findAll(), normal use.
   */
  public void testFindAll() {
    Query query = Query.builder().offset(10).limit(20).build();
    List<Company> liste = companyDao.findAll(query);
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
    Query query = Query.builder().offset(60).limit(20).build();
    List<Company> liste = companyDao.findAll(query);
    assertTrue(liste.size() == 0);
  }

  @Test
  /**
   * Tests findAll(), normal use.
   */
  public void testFind() {
    Company cpn = Company.builder().build();
    cpn = companyDao.read(17L);
    assertEquals(cpn.getName(), "Sony");
  }

  @Test(expected = IllegalArgumentException.class)
  /**
   * Tests findAll(), with null, should return null.
   */
  public void testFindNull() {
    companyDao.read(null);
  }

  @Test
  /**
   * Tests findAll(), with an invalid id, should throw an exception.
   */
  public void testFindInvalid() {
    Company cpn = Company.builder().build();
    cpn = companyDao.read(150L);
    if (cpn != null) {
      fail();
    }
  }
}
