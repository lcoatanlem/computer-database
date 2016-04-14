package com.excilys.computerdatabase.service.impl;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.persistence.dao.Dao;
import com.excilys.computerdatabase.persistence.dao.impl.CompanyDaoImpl;
import com.excilys.computerdatabase.persistence.mapping.query.Query;
import com.excilys.computerdatabase.service.Service;

import java.util.List;

public class CompanyServiceImpl implements Service<Company> {
  private Dao<Company> dao;

  private static final CompanyServiceImpl INSTANCE = new CompanyServiceImpl();

  public static CompanyServiceImpl getInstance() {
    return INSTANCE;
  }

  /**
   * Instantiates the DAO when created.
   */
  private CompanyServiceImpl() {
    dao = CompanyDaoImpl.getInstance();
  }

  /**
   * Method to list all companies. To paginate, it starts with an index and a
   * number of instances we want.
   */
  public List<Company> list(Query query) {
    return dao.findAll(query);
  }

  /**
   * Counts the number of rows in the databases and set it into the page.
   */
  public int count(Query query) {
    return dao.count(query);
  }

  @Override
  public void delete(Long id) {
    // TODO Not done yet.
    throw new RuntimeException("TODO.");
  }
}
