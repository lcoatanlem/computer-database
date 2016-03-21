package com.excilys.computerdatabase.pagination.impl;

import com.excilys.computerdatabase.pagination.Pagination;
import com.excilys.computerdatabase.persistence.dto.ComputerDTO;

import java.util.List;

public class ComputerPagination extends Pagination<ComputerDTO> {

  private List<ComputerDTO> list;

  /**
   * This is the implementation of the Pagination for the Computers.
   * Uses the superclass constructor and add the list to its parameters.
   * @param list of Computers
   */
  public ComputerPagination(int pageNumber, int pageSize, int totalEntries,
      List<ComputerDTO> list) {
    super(pageNumber, pageSize, totalEntries);
    this.list = list;
  }

  @Override
  public List<ComputerDTO> getList() {
    return list;
  }
}