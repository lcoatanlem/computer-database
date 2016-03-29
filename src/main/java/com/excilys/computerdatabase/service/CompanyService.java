package com.excilys.computerdatabase.service;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.persistence.dao.impl.CompanyDaoImpl;
import com.excilys.computerdatabase.persistence.mapping.query.Query;

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
   */
  public List<Company> listCompanies(Query query) {
    return cpnDao.findAll(query);
  }

  /**
   * Counts the number of rows in the databases and set it into the page.
   */
  public int countEntries() {
    return cpnDao.countEntries();
  }
}
