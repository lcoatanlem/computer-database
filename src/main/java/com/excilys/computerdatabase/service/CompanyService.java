package com.excilys.computerdatabase.service;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.pagination.impl.CompanyPagination;
import com.excilys.computerdatabase.persistence.dao.impl.CompanyDaoImpl;
import com.excilys.computerdatabase.persistence.dto.CompanyDto;

import java.util.ArrayList;
import java.util.List;

public class CompanyService {
  private CompanyDaoImpl cpnDao;
  private CompanyPagination cpnPage;

  public static final CompanyService CPNSERV;

  static {
    CPNSERV = new CompanyService();
  }

  /**
   * Instantiates the DAO and the CompanyPage.
   */
  private CompanyService() {
    cpnDao = CompanyDaoImpl.getInstance();
    cpnPage = new CompanyPagination(0, 10, cpnDao.countEntries(), new ArrayList<CompanyDto>());
  }

  /**
   * Method to list all companies. To paginate, it starts with an index and a
   * number of instances we want. The method findAll() is called iff the list is
   * empty.
   * 
   * @param offset the beginning of the entries
   * @param limit the maximum of entries
   * @return a String containing the pretty printing of the instances we want.
   */
  public List<CompanyDto> listCompanies(int offset, int limit) {
    if (offset <= cpnPage.getTotalEntries() && (offset + limit) > 0) {

      cpnPage.setPageSize(limit);

      cpnPage.getList().clear();

      for (Company cpn : cpnDao.findAll(offset, limit)) {
        cpnPage.getList().add(new CompanyDto(cpn.getId(), cpn.getName()));
      }

      if (cpnPage.getList().size() == 0) {
        throw new IndexOutOfBoundsException();
      }
    }
    return cpnPage.getList();
  }

  public CompanyPagination getcPage() {
    return cpnPage;
  }

  public void setcPage(CompanyPagination cpnPage) {
    this.cpnPage = cpnPage;
  }
}
