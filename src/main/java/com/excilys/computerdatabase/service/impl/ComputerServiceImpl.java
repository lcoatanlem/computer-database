package com.excilys.computerdatabase.service.impl;

import com.excilys.computerdatabase.mapping.query.Query;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.dao.Dao;
import com.excilys.computerdatabase.persistence.dao.impl.CompanyDaoImpl;
import com.excilys.computerdatabase.persistence.dao.impl.ComputerDaoImpl;
import com.excilys.computerdatabase.service.IService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service ("computerService")
public class ComputerServiceImpl implements IService<Computer> {
  
  @Autowired
  @Qualifier ("computerDao")
  private Dao<Computer> computerDao;
  
  @Autowired
  @Qualifier ("companyDao")
  private CompanyDaoImpl companyDao;

  /**
   * Count the number of rows in the database.
   */
  @Override
  public int count(Query query) {
    return computerDao.count(query);
  }

  /**
   * Method to list all computers. To paginate, it starts with an index and a
   * number of instances we want.
   */
  public List<Computer> list(Query query) {
    return computerDao.findAll(query);
  }

  /**
   * Method to get the computer from his id.
   * 
   * @param id
   *          the id of the computer to show
   * @return a String containing our pretty printing
   */
  public Computer find(Long id) {
    return computerDao.read(id);
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
      if (companyDao.read(cpu.getManufacturer().getId()) == null) {
        throw new IllegalArgumentException("This company doesn't exists.");
      }
    }

    // If everything's fine...
    computerDao.create(cpu);
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
    if (computerDao.read(cpu.getId()) == null) {
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
      if (cpu.getManufacturer().getId() != null
          && companyDao.read(cpu.getManufacturer().getId()) == null) {
        throw new IllegalArgumentException("This company doesn't exists.");
      }
    }

    // If everything's fine...
    computerDao.update(cpu);
  }

  /**
   * Method to delete a computer from a DB.
   * 
   * @param id
   *          the id of the computer to delete
   */
  public void delete(Long id) {
    // Validating the id
    if (computerDao.read(id) == null) {
      throw new IllegalArgumentException("This computer doesn't exists.");
    }

    // If everything's fine...
    computerDao.delete(id);
  }
  
  public void deleteByCompany(Long id) {
    ((ComputerDaoImpl) computerDao).deleteByCompany(id);
  }

}