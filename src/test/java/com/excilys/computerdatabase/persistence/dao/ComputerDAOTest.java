package com.excilys.computerdatabase.persistence.dao;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;

import com.excilys.computerdatabase.exception.NoSuchCompanyException;
import com.excilys.computerdatabase.exception.NoSuchComputerException;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.dao.impl.ComputerDaoImpl;
import com.excilys.computerdatabase.persistence.DBTesting;

/**
 * ComputerDAO testing.
 * 
 * @author lcoatanlem
 *
 */
public class ComputerDAOTest extends DBTesting {

  @Test
  /**
   * Tests findAll(), normal use.
   */
  public void testFindAll() {
    ComputerDaoImpl cpuDao = new ComputerDaoImpl();
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
    ComputerDaoImpl cDao = new ComputerDaoImpl();
    List<Computer> liste = cDao.findAll(60, 20);
    assertTrue(liste.size() == 0);
  }

  @Test
  /**
   * Tests find(Long id) in a normal use.
   */
  public void testFind() {
    ComputerDaoImpl cDao = new ComputerDaoImpl();
    Computer comp = new Computer("");
    try {
      comp = cDao.find(12L);
      Computer same = new Computer("Apple III");
      same.setId(12L);
      // same.setIntroduced(LocalDate.parse("1980-05-01")); LES DATES SONT
      // NULLES /!\
      // same.setDiscontinued(LocalDate.parse("1984-04-01"));
      Company cpn = new Company();
      cpn.setId(1L);
      cpn.setName("Apple Inc.");
      same.setManufacturer(cpn);
      assertEquals(same, comp);
    } catch (NoSuchComputerException e) {
      fail();
    }
  }

  @Test
  /**
   * Tests find(Long id) in an abnormal use (id not in db).
   */
  public void testFindInvalid() {
    ComputerDaoImpl cDao = new ComputerDaoImpl();
    Computer comp = new Computer("Test");
    Computer comptmp = comp;
    try {
      comptmp = cDao.find(1000L);
      fail();
    } catch (NoSuchComputerException e) {
      assertEquals(comp, comptmp);
    }
  }

  @Test
  /**
   * Tests create(Computer t) in a normal use, with a computer.
   */
  public void testCreate() {
    ComputerDaoImpl cDao = new ComputerDaoImpl();
    Computer comp = new Computer("Test");
    comp.setIntroduced(LocalDate.parse("1990-11-10"));
    comp.setDiscontinued(LocalDate.parse("2016-03-09"));
    Company cpn = new Company();
    cpn.setId(2L);
    cpn.setName("Thinking Machines");
    comp.setManufacturer(cpn);
    try {
      cDao.create(comp);
      comp.setId(51L);
      assertEquals(cDao.find(51L), comp);
    } catch (NoSuchCompanyException | NoSuchComputerException e) {
      fail();
    }
  }

  @Test
  /**
   * Tests create(Computer t) in an abnormal, with a wrong company id.
   */
  public void testCreateNSCExc() {
    ComputerDaoImpl cDao = new ComputerDaoImpl();
    Computer comp = new Computer("Mine");
    comp.setIntroduced(LocalDate.parse("1990-11-10"));
    comp.setDiscontinued(LocalDate.parse("2016-03-09"));
    Company cpn = new Company();
    cpn.setId(100L);
    comp.setManufacturer(cpn);
    try {
      cDao.create(comp);
      assertEquals(cDao.find(52L).getManufacturer(), null);
    } catch (NoSuchCompanyException e) {
      fail();
    } catch (NoSuchComputerException e) {
      fail();
    }
  }

  @Test
  /**
   * Tests update(Computer t) in a normal use.
   */
  public void testUpdate() {
    ComputerDaoImpl cDao = new ComputerDaoImpl();
    Computer comp = new Computer("Update");
    comp.setId(35L);
    comp.setIntroduced(LocalDate.parse("1995-11-10"));
    comp.setDiscontinued(LocalDate.parse("2020-03-09"));
    Company cpn = new Company();
    cpn.setId(34L);
    cpn.setName("OMRON");
    comp.setManufacturer(cpn);
    try {
      cDao.update(comp);
      assertEquals(cDao.find(35L), comp);
    } catch (NoSuchComputerException | NoSuchCompanyException e) {
      fail();
    }
  }

  @Test
  /**
   * Tests update(Computer t) in an abnormal use, with a wrong computer id.
   */
  public void testUpdateInvalid() {
    ComputerDaoImpl cDao = new ComputerDaoImpl();
    Computer comp = new Computer("Update");
    comp.setId(1000L);
    comp.setIntroduced(LocalDate.parse("1995-11-10"));
    comp.setDiscontinued(LocalDate.parse("2020-03-09"));
    Company cpn = new Company();
    cpn.setId(34L);
    cpn.setName("OMRON");
    comp.setManufacturer(cpn);
    Computer same = comp;
    try {
      cDao.update(comp);
      fail();
    } catch (NoSuchComputerException | NoSuchCompanyException e) {
      assertEquals(same, comp);
    }
  }

  @Test
  /**
   * Tests update(Computer t) in an abnormal use, with a wrong company id.
   */
  public void testUpdateNSCExc() {
    ComputerDaoImpl cDao = new ComputerDaoImpl();
    Computer comp = new Computer("Update");
    comp.setId(1000L);
    comp.setIntroduced(LocalDate.parse("1995-11-10"));
    comp.setDiscontinued(LocalDate.parse("2020-03-09"));
    Company cpn = new Company();
    Computer same = comp;
    cpn.setId(150L);
    cpn.setName("OMRON");
    comp.setManufacturer(cpn);
    try {
      cDao.update(comp);
      fail();
    } catch (NoSuchComputerException | NoSuchCompanyException e) {
      assertEquals(same, comp);
    }
  }

  @Test
  /**
   * Tests delete(Long id) in a normal use.
   */
  public void testDelete() {
    ComputerDaoImpl cDao = new ComputerDaoImpl();
    try {
      cDao.delete(45L);
    } catch (NoSuchComputerException e) {
      fail();
    }
    try {
      cDao.find(45L);
      fail();
    } catch (NoSuchComputerException e) {
    }
  }

  @Test
  /**
   * Tests delete(Long id) in an abnormal use, with a wrong computer id.
   */
  public void testDeleteInvalid() {
    ComputerDaoImpl cDao = new ComputerDaoImpl();
    try {
      cDao.delete(445L);
      fail();
    } catch (NoSuchComputerException e) {
    }
  }

}
