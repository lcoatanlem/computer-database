package com.excilys.computerdatabase.pagination.impl;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.pagination.Pagination;

import java.util.List;

public class CompanyPagination extends Pagination<Company> {

  private List<Company> list;

  /**
   * This is the implementation of the Pagination for the Companies. Uses the
   * superclass constructor and add the list to its parameters.
   * 
   * @param list
   *          of Companies
   */
  public CompanyPagination(int pageNumber, int pageSize, int totalEntries, List<Company> list) {
    super(pageNumber, pageSize, totalEntries);
    this.list = list;
  }

  @Override
  public List<Company> getList() {
    return list;
  }
}