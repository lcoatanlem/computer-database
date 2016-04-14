package com.excilys.computerdatabase.service.impl;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.dao.Dao;
import com.excilys.computerdatabase.persistence.dao.impl.CompanyDaoImpl;
import com.excilys.computerdatabase.persistence.dao.impl.ComputerDaoImpl;
import com.excilys.computerdatabase.persistence.mapping.query.Query;
import com.excilys.computerdatabase.service.Service;

import java.util.List;

public class ComputerServiceImpl implements Service<Computer> {
  
  private Dao<Computer> dao;

  private static final ComputerServiceImpl INSTANCE = new ComputerServiceImpl();

  public static ComputerServiceImpl getInstance() {
    return INSTANCE;
  }

  /**
   * Instantiates the DAO and the page.
   */
  private ComputerServiceImpl() {
    dao = ComputerDaoImpl.getInstance();
  }

  /**
   * Count the number of rows in the database.
   */
  @Override
  public int count(Query query) {
    return dao.count(query);
  }

  /**
   * Method to list all computers. To paginate, it starts with an index and a
   * number of instances we want.
   */
  public List<Computer> list(Query query) {
    return dao.findAll(query);
  }

  /**
   * Method to get the computer from his id.
   * 
   * @param id
   *          the id of the computer to show
   * @return a String containing our pretty printing
   */
  public Computer find(Long id) {
    return dao.read(id);
  }

  /**
   * Method to create a new computer in DB. Calls CompanyDAO and findCompany(id)
   * to check if the id exists (not null).
   * 
   * @param cpu
   *          the computer you to create
   */
  public void createComputer(Computer cpu) {
    // Validating the name
    String name = cpu.getName();
    if (name == null) {
      throw new IllegalArgumentException("Name cannot be null.");
    }
    if (name.length() < 2) {
      throw new IllegalArgumentException("Name cannot be less than 2 chars.");
    }

    // Validating the manufacturer
    if (cpu.getManufacturer() != null && cpu.getManufacturer().getId() != null) {
      // must be valid as soon as we have a list
      CompanyDaoImpl cpnDao = CompanyDaoImpl.getInstance();
      if (cpnDao.read(cpu.getManufacturer().getId()) == null) {
        throw new IllegalArgumentException("This company doesn't exists.");
      }
    }

    // If everything's fine...
    dao.create(cpu);
  }

  /**
   * Method to update a new computer in DB. Calls CompanyDAO and findCompany(id)
   * to check if the id exists (not null).
   * 
   * @param cpu
   *          the computer to update
   */
  public void updateComputer(Computer cpu) {
    // Validating the id
    if (dao.read(cpu.getId()) == null) {
      throw new IllegalArgumentException("This computer doesn't exists.");
    }

    // Validating the name
    String name = cpu.getName();
    if (name == null) {
      throw new IllegalArgumentException("Name cannot be null.");
    }
    if (name.length() < 2) {
      throw new IllegalArgumentException("Name cannot be less than 2 chars.");
    }

    // Validating the manufacturer
    if (cpu.getManufacturer() != null || cpu.getManufacturer().getId() != null) {
      // must be valid as soon as we have a list
      CompanyDaoImpl cpnDao = CompanyDaoImpl.getInstance();
      if (cpnDao.read(cpu.getManufacturer().getId()) == null) {
        throw new IllegalArgumentException("This company doesn't exists.");
      }
    }

    // If everything's fine...
    dao.update(cpu);
  }

  /**
   * Method to delete a computer from a DB.
   * 
   * @param id
   *          the id of the computer to delete
   */
  public void delete(Long id) {
    // Validating the id
    if (dao.read(id) == null) {
      throw new IllegalArgumentException("This computer doesn't exists.");
    }
    
    // If everything's fine...
    dao.delete(id);
  }
  
}