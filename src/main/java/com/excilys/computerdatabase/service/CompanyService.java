package com.excilys.computerdatabase.service;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.persistence.dao.impl.CompanyDaoImpl;

import java.util.List;

public class CompanyService {
  private CompanyDaoImpl cpnDao;

  private static final CompanyService INSTANCE = new CompanyService();

  public static CompanyService getInstance() {
    return INSTANCE;
  }

  /**
   * Instantiates the DAO when created.
   */
  private CompanyService() {
    cpnDao = CompanyDaoImpl.getInstance();
  }

  /**
   * Method to list all companies. To paginate, it starts with an index and a
   * number of instances we want.
   * 
   * @param offset
   *          the beginning of the entries
   * @param limit
   *          the maximum of entries
   */
  public List<Company> listCompanies(int offset, int limit) {
    return cpnDao.findAll(offset, limit);
  }

  /**
   * Counts the number of rows in the databases and set it into the page.
   */
  public int countEntries() {
    return cpnDao.countEntries();
  }
}
