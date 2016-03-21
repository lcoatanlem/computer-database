package com.excilys.computerdatabase.pagination.impl;

import com.excilys.computerdatabase.pagination.Pagination;
import com.excilys.computerdatabase.persistence.dto.CompanyDto;

import java.util.List;

public class CompanyPagination extends Pagination<CompanyDto> {

  private List<CompanyDto> list;

  /**
   * This is the implementation of the Pagination for the Companies. Uses the
   * superclass constructor and add the list to its parameters.
   * 
   * @param list
   *          of Companies
   */
  public CompanyPagination(int pageNumber, int pageSize, int totalEntries, List<CompanyDto> list) {
    super(pageNumber, pageSize, totalEntries);
    this.list = list;
  }

  @Override
  public List<CompanyDto> getList() {
    return list;
  }
}