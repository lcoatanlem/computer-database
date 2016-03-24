package com.excilys.computerdatabase.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Company.CompanyBuilder;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.DbTesting;
import com.excilys.computerdatabase.persistence.dao.impl.ComputerDaoImpl;

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
    List<Computer> liste = cpuDao.findAll(10, 5);
    for (Computer comp : liste) {
      assertNotNull(comp.getId());
    }
    assertTrue(liste.size() == 5);
  }

  @Test
  /**
   * Tests findAll(), using wrong values.
   */
  public void testFindAllInvalid() {
    ComputerDaoImpl cpuDao = ComputerDaoImpl.getInstance();
    List<Computer> liste = cpuDao.findAll(60, 20);
    assertTrue(liste.size() == 0);
  }

  @Test
  /**
   * Tests find(Long id) in a normal use.
   */
  public void testFind() {
    ComputerDaoImpl cpuDao = ComputerDaoImpl.getInstance();
    Computer comp = new Computer("");
    comp = cpuDao.find(12L);
    Computer same = new Computer("Apple III");
    same.setId(12L);
    // same.setIntroduced(LocalDate.parse("1980-05-01")); LES DATES SONT
    // NULLES /!\
    // same.setDiscontinued(LocalDate.parse("1984-04-01"));
    Company cpn = new CompanyBuilder().id(1L).name("Apple Inc.").build();
    same.setManufacturer(cpn);
    assertEquals(same, comp);
  }

  @Test
  /**
   * Tests find(Long id) in an abnormal use (id not in db).
   */
  public void testFindInvalid() {
    ComputerDaoImpl cpuDao = ComputerDaoImpl.getInstance();
    cpuDao.find(1000L);
    fail();
  }

  @Test
  /**
   * Tests create(Computer t) in a normal use, with a computer.
   */
  public void testCreate() {
    Computer comp = new Computer("Test");
    comp.setIntroduced(LocalDate.parse("1990-11-10"));
    comp.setDiscontinued(LocalDate.parse("2016-03-09"));
    Company cpn = new CompanyBuilder().id(2L).name("Thinking Machines").build();
    comp.setManufacturer(cpn);
    ComputerDaoImpl cpuDao = ComputerDaoImpl.getInstance();
    cpuDao.create(comp);
    comp.setId(51L);
    assertEquals(cpuDao.find(51L), comp);

  }

  @Test
  /**
   * Tests create(Computer t) in an abnormal, with a wrong company id.
   */
  public void testCreateNscExc() {
    Computer comp = new Computer("Mine");
    comp.setIntroduced(LocalDate.parse("1990-11-10"));
    comp.setDiscontinued(LocalDate.parse("2016-03-09"));
    Company cpn = new CompanyBuilder().id(100L).build();
    comp.setManufacturer(cpn);
    ComputerDaoImpl cpuDao = ComputerDaoImpl.getInstance();
    cpuDao.create(comp);
    assertEquals(cpuDao.find(52L).getManufacturer(), null);
  }

  @Test
  /**
   * Tests update(Computer t) in a normal use.
   */
  public void testUpdate() {
    Computer comp = new Computer("Update");
    comp.setId(35L);
    comp.setIntroduced(LocalDate.parse("1995-11-10"));
    comp.setDiscontinued(LocalDate.parse("2020-03-09"));
    Company cpn = new CompanyBuilder().id(34L).name("OMRON").build();
    comp.setManufacturer(cpn);
    ComputerDaoImpl cpuDao = ComputerDaoImpl.getInstance();
    cpuDao.update(comp);
    assertEquals(cpuDao.find(35L), comp);
  }

  @Test
  /**
   * Tests update(Computer t) in an abnormal use, with a wrong computer id.
   */
  public void testUpdateInvalid() {
    Computer comp = new Computer("Update");
    comp.setId(1000L);
    comp.setIntroduced(LocalDate.parse("1995-11-10"));
    comp.setDiscontinued(LocalDate.parse("2020-03-09"));
    Company cpn = new CompanyBuilder().id(34L).name("OMRON").build();
    comp.setManufacturer(cpn);
    ComputerDaoImpl cpuDao = ComputerDaoImpl.getInstance();
    cpuDao.update(comp);
    fail();

  }

  @Test
  /**
   * Tests update(Computer t) in an abnormal use, with a wrong company id.
   */
  public void testUpdateNscExc() {
    Computer comp = new Computer("Update");
    comp.setId(1000L);
    comp.setIntroduced(LocalDate.parse("1995-11-10"));
    comp.setDiscontinued(LocalDate.parse("2020-03-09"));
    Company cpn = new CompanyBuilder().id(150L).name("OMRON").build();
    comp.setManufacturer(cpn);
    ComputerDaoImpl cpuDao = ComputerDaoImpl.getInstance();
    cpuDao.update(comp);
    fail();
  }

  @Test
  /**
   * Tests delete(Long id) in a normal use.
   */
  public void testDelete() {
    ComputerDaoImpl cpuDao = ComputerDaoImpl.getInstance();
    cpuDao.delete(45L);
    cpuDao.find(45L);
    fail();
  }

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
