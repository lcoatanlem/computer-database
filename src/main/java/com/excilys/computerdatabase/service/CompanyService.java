package com.excilys.computerdatabase.service;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.pagination.Pagination;
import com.excilys.computerdatabase.persistence.dao.impl.CompanyDaoImpl;

public class CompanyService {
  private CompanyDaoImpl cpnDao;
  private Pagination page;

  private static final CompanyService INSTANCE = new CompanyService();

  public static CompanyService getCpnserv() {
    return INSTANCE;
  }

  /**
   * Instantiates the DAO when created.
   */
  private CompanyService() {
    cpnDao = CompanyDaoImpl.getInstance();
  }

  /**
   * Method to list all companies. To paginate, it starts with an index and a number of instances we
   * want.
   * 
   * @param offset
   *          the beginning of the entries
   * @param limit
   *          the maximum of entries
   */
  public void listCompanies(int offset, int limit) {
    // First initialization
    if (page.getCpnPageSize() <= 0) {
      page.setCpnPageSize(limit);
    }
    page.getCpnList().clear();

    for (Company cpn : cpnDao.findAll(offset, limit)) {
      page.getCpnList().add(cpn);
    }
  }

  /**
   * Counts the number of rows in the databases and set it into the page.
   */
  public void countEntries() {
    page.setCpnTotalEntries(cpnDao.countEntries());
  }

  // Getter and Setter for the page
  public Pagination getPage() {
    return page;
  }

  public void setPage(Pagination page) {
    this.page = page;
  }
}
