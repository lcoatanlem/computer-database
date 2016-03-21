package com.excilys.computerdatabase.pagination.impl;

import com.excilys.computerdatabase.pagination.Pagination;
import com.excilys.computerdatabase.persistence.dto.CompanyDTO;

import java.util.List;

public class CompanyPagination extends Pagination<CompanyDTO> {

  private List<CompanyDTO> list;

  /**
   * This is the implementation of the Pagination for the Companies.
   * Uses the superclass constructor and add the list to its parameters.
   * @param list of Companies
   */
  public CompanyPagination(int pageNumber, int pageSize, int totalEntries, List<CompanyDTO> list) {
    super(pageNumber, pageSize, totalEntries);
    this.list = list;
  }

  @Override
  public List<CompanyDTO> getList() {
    return list;
  }
}