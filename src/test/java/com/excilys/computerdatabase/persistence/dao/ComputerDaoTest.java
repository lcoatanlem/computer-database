package com.excilys.computerdatabase.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.DbTesting;
import com.excilys.computerdatabase.persistence.dao.impl.ComputerDaoImpl;
import com.excilys.computerdatabase.persistence.mapping.query.Query;

import org.junit.Ignore;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

/**
 * ComputerDAO testing.
 * 
 * @author lcoatanlem
 *
 */
public class ComputerDaoTest extends DbTesting {

  @Test
  /**
   * Tests findAll(), normal use.
   */
  public void testFindAll() {
    ComputerDaoImpl cpuDao = ComputerDaoImpl.getInstance();
    Query query = Query.builder().offset(10).limit(5).build();
    List<Computer> liste = cpuDao.findAll(query);
    for (Computer comp : liste) {
      assertNotNull(comp.getId());
    }
    assertTrue(liste.size() == 5);
  }

  @Ignore
  @Test
  /**
   * Tests findAll(), using wrong values.
   */
  public void testFindAllInvalid() {
    ComputerDaoImpl cpuDao = ComputerDaoImpl.getInstance();
    Query query = Query.builder().offset(60).limit(20).build();
    List<Computer> liste = cpuDao.findAll(query);
    assertTrue(liste.size() == 0);
  }

  @Ignore
  @Test
  /**
   * Tests find(Long id) in a normal use.
   */
  public void testFind() {
    ComputerDaoImpl cpuDao = ComputerDaoImpl.getInstance();
    Computer comp = Computer.builder("").build();
    comp = cpuDao.read(12L);
    Computer same = Computer.builder("Apple III").build();
    same.setId(12L);
    // same.setIntroduced(LocalDate.parse("1980-05-01")); LES DATES SONT
    // NULLES /!\
    // same.setDiscontinued(LocalDate.parse("1984-04-01"));
    Company cpn = Company.builder().id(1L).name("Apple Inc.").build();
    same.setManufacturer(cpn);
    assertEquals(same, comp);
  }

  @Ignore
  @Test
  /**
   * Tests find(Long id) in an abnormal use (id not in db).
   */
  public void testFindInvalid() {
    ComputerDaoImpl cpuDao = ComputerDaoImpl.getInstance();
    cpuDao.read(1000L);
    fail();
  }

  @Ignore
  @Test
  /**
   * Tests create(Computer t) in a normal use, with a computer.
   */
  public void testCreate() {

    Company cpn = Company.builder().id(2L).name("Thinking Machines").build();
    Computer comp = Computer.builder("Test").introduced(LocalDate.parse("1990-11-10"))
        .discontinued(LocalDate.parse("2016-03-09")).manufacturer(cpn).build();
    comp.setManufacturer(cpn);
    ComputerDaoImpl cpuDao = ComputerDaoImpl.getInstance();
    cpuDao.create(comp);
    comp.setId(51L);
    assertEquals(cpuDao.read(51L), comp);

  }

  @Ignore
  @Test
  /**
   * Tests create(Computer t) in an abnormal, with a wrong company id.
   */
  public void testCreateNscExc() {
    Company cpn = Company.builder().id(100L).build();
    Computer comp = Computer.builder("Mine").introduced(LocalDate.parse("1990-11-10"))
        .discontinued(LocalDate.parse("2016-03-09")).manufacturer(cpn).build();
    ComputerDaoImpl cpuDao = ComputerDaoImpl.getInstance();
    cpuDao.create(comp);
    assertEquals(cpuDao.read(52L).getManufacturer(), null);
  }

  @Test
  /**
   * Tests update(Computer t) in a normal use.
   */
  public void testUpdate() {
    Company cpn = Company.builder().id(34L).name("OMRON").build();
    Computer comp = Computer.builder("Update").id(35L).introduced(LocalDate.parse("1995-11-10"))
        .discontinued(LocalDate.parse("2020-03-09")).manufacturer(cpn).build();
    ComputerDaoImpl cpuDao = ComputerDaoImpl.getInstance();
    cpuDao.update(comp);
    assertEquals(cpuDao.read(35L), comp);
  }

  @Ignore
  @Test
  /**
   * Tests update(Computer t) in an abnormal use, with a wrong computer id.
   */
  public void testUpdateInvalid() {
    Company cpn = Company.builder().id(34L).name("OMRON").build();
    Computer comp = Computer.builder("Update").id(1000L).introduced(LocalDate.parse("1995-11-10"))
        .discontinued(LocalDate.parse("2020-03-09")).manufacturer(cpn).build();
    ComputerDaoImpl cpuDao = ComputerDaoImpl.getInstance();
    cpuDao.update(comp);
    fail();

  }

  @Ignore
  @Test
  /**
   * Tests update(Computer t) in an abnormal use, with a wrong company id.
   */
  public void testUpdateNscExc() {
    Company cpn = Company.builder().id(150L).name("OMRON").build();
    Computer comp = Computer.builder("Update").id(35L).introduced(LocalDate.parse("1995-11-10"))
        .discontinued(LocalDate.parse("2020-03-09")).manufacturer(cpn).build();
    ComputerDaoImpl cpuDao = ComputerDaoImpl.getInstance();
    cpuDao.update(comp);
    fail();
  }

  @Ignore
  @Test
  /**
   * Tests delete(Long id) in a normal use.
   */
  public void testDelete() {
    ComputerDaoImpl cpuDao = ComputerDaoImpl.getInstance();
    cpuDao.delete(45L);
    cpuDao.read(45L);
    fail();
  }

  @Ignore
  @Test
  /**
   * Tests delete(Long id) in an abnormal use, with a wrong computer id.
   */
  public void testDeleteInvalid() {
    ComputerDaoImpl cpuDao = ComputerDaoImpl.getInstance();

    cpuDao.delete(445L);
    fail();

  }

}
