package com.excilys.computerdatabase.pagination;

import java.util.List;

/**
 * The pagination class, manipulated by the JSPs.
 * @author lcoatanlem
 * @param <T> can be a Computer or a Company
 */
public abstract class Pagination<T> {
  private int pageNumber;
  private int pageSize;
  private int totalEntries;

  /**
   * Pagination creation, same for all implementation of the Pagination.
   * @param pageNumber the number of the actual page
   * @param pageSize the size of the pages we want
   * @param totalEntries the number of rows in the table
   */
  public Pagination(int pageNumber, int pageSize, int totalEntries) {
    this.pageNumber = pageNumber;
    this.pageSize = pageSize;
    this.totalEntries = totalEntries;
  }

  public int getPageNumber() {
    return pageNumber;
  }
  
  public void setPageNumber(int pageNumber) {
    this.pageNumber = pageNumber;
  }
  
  public int getPageSize() {
    return pageSize;
  }
  
  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }
  
  public int getTotalEntries() {
    return totalEntries;
  }
  
  public void setTotalEntries(int totalEntries) {
    this.totalEntries = totalEntries;
  }
  
  /**
   * The implementation of the method depends on the type T.
   * @return List of T
   */
  public abstract List<T> getList();
}
