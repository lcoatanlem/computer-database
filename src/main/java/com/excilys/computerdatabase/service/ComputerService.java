package com.excilys.computerdatabase.service;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.pagination.impl.ComputerPagination;
import com.excilys.computerdatabase.persistence.dao.impl.CompanyDaoImpl;
import com.excilys.computerdatabase.persistence.dao.impl.ComputerDaoImpl;

import java.util.List;

public class ComputerService {
  private ComputerDaoImpl cpuDao;
  private ComputerPagination cpuPage;

  private static final ComputerService INSTANCE;

  static {
    INSTANCE = new ComputerService();
  }

  public static ComputerService getInstance() {
    return INSTANCE;
  }

  /**
   * Instantiates the DAO and the list of computers.
   */
  private ComputerService() {
    cpuDao = ComputerDaoImpl.getInstance();
    // TODO :cpuPage = new ComputerPagination(1, 1, cpuDao.countEntries(), new
    // ArrayList<ComputerDto>());
  }

  public int countEntries() {
    return cpuDao.countEntries();
  }

  /**
   * Method to list all computers. To paginate, it starts with an index and a number of instances we
   * want. The method findAll() is called iff the list is empty. Will return the id and the name
   * only.
   * 
   * @param offset
   *          the beginning of the list
   * @param limit
   *          the maximum of elements in the list
   */
  public List<Computer> listComputers(int offset, int limit) {
    return cpuDao.findAll(offset, limit);
  }

  /**
   * Method to show the details of only one computer. The fields are displayed iff they are not
   * null.
   * 
   * @param id
   *          the id of the computer to show
   * @return a String containing our pretty printing
   */
  public Computer showComputer(Long id) {

    Computer cpu = cpuDao.find(id);

    // Validating the id
    if (cpu == null) {
      throw new IllegalArgumentException("This computer doesn't exists.");
    }

    // If everything's fine...
    return cpu;
  }

  /**
   * Method to create a new computer in DB. Calls CompanyDAO and findCompany(id) to check if the id
   * exists (not null).
   * 
   * @param cpu
   *          the computer you to create
   */
  public void createComputer(Computer cpu) {
    // Validating the name
    String name = cpu.getName();
    if (name == null | name.length() <= 2) {
      throw new IllegalArgumentException("Name cannot be null.");
    }

    // Validating the manufacturer
    if (cpu.getManufacturer() != null) {
      // must be valid
      CompanyDaoImpl cpnDao = CompanyDaoImpl.getInstance();
      if (cpu.getManufacturer().getId() == null
          | cpnDao.find(cpu.getManufacturer().getId()) == null) {
        throw new IllegalArgumentException("This company doesn't exists.");
      }
    }

    // If everything's fine...
    int totalEntries = cpuPage.getTotalEntries();
    cpuDao.create(cpu);
    cpuPage.setTotalEntries(totalEntries++);
  }

  /**
   * Method to update a new computer in DB. Calls CompanyDAO and findCompany(id) to check if the id
   * exists (not null).
   * 
   * @param cpu
   *          the computer to update
   */
  public void updateComputer(Computer cpu) {
    // Validating the id
    if (cpuDao.find(cpu.getId()) == null) {
      throw new IllegalArgumentException("This computer doesn't exists.");
    }

    // Validating the name
    String name = cpu.getName();
    if (name == null | name.length() <= 2) {
      throw new IllegalArgumentException("Name cannot be null.");
    }

    // Validating the manufacturer
    if (cpu.getManufacturer() != null) {
      // must be valid
      CompanyDaoImpl cpnDao = CompanyDaoImpl.getInstance();
      if (cpnDao.find(cpu.getManufacturer().getId()) == null) {
        throw new IllegalArgumentException("This company doesn't exists.");
      }
    }

    // If everything's fine...
    cpuDao.update(cpu);
  }

  /**
   * Method to delete a computer from a DB.
   * 
   * @param id
   *          the id of the computer to delete
   */
  public void deleteComputer(Long id) {
    // Validating the id
    if (cpuDao.find(id) == null) {
      throw new IllegalArgumentException("This computer doesn't exists.");
    }

    // If everything's fine...
    int totalEntries = cpuPage.getTotalEntries();
    cpuDao.delete(id);
    cpuPage.setTotalEntries(totalEntries--);
  }

  public ComputerPagination getCpuPage() {
    return cpuPage;
  }

  public void setCpuPage(ComputerPagination cpuPage) {
    this.cpuPage = cpuPage;
  }
}