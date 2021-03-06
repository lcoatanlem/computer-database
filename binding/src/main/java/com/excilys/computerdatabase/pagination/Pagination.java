package com.excilys.computerdatabase.pagination;

import com.excilys.computerdatabase.dto.ComputerDto;
import com.excilys.computerdatabase.model.Company;

import java.util.List;

/**
 * The pagination class, this will be a parameter, so it will have computer and
 * company informations. There are all getters and setters and a builder.
 * 
 * @author lcoatanlem
 */
public class Pagination {
  // Computer attributes
  private int cpuPageNumber;
  private int cpuPageSize;
  private int cpuTotalEntries;
  private int cpuNbPages;
  private List<ComputerDto> cpuList;

  // Companies attributes
  private int cpnPageNumber;
  private int cpnPageSize;
  private int cpnTotalEntries;
  private int cpnNbPages;
  private List<Company> cpnList;

  // Search and orderBy features
  private String search;
  private String nameOrder;
  private String introducedOrder;
  private String discontinuedOrder;
  private String companyOrder;

  public int getCpuPageNumber() {
    return cpuPageNumber;
  }

  public void setCpuPageNumber(int cpuPageNumber) {
    this.cpuPageNumber = cpuPageNumber;
  }

  public int getCpuPageSize() {
    return cpuPageSize;
  }

  public void setCpuPageSize(int cpuPageSize) {
    this.cpuPageSize = cpuPageSize;
  }

  public int getCpuTotalEntries() {
    return cpuTotalEntries;
  }

  public void setCpuTotalEntries(int cpuTotalEntries) {
    this.cpuTotalEntries = cpuTotalEntries;
  }

  public int getCpuNbPages() {
    return cpuNbPages;
  }

  public void setCpuNbPages(int cpuNbPages) {
    this.cpuNbPages = cpuNbPages;
  }

  public List<ComputerDto> getCpuList() {
    return cpuList;
  }

  public void setCpuList(List<ComputerDto> cpuList) {
    this.cpuList = cpuList;
  }

  public int getCpnPageNumber() {
    return cpnPageNumber;
  }

  public void setCpnPageNumber(int cpnPageNumber) {
    this.cpnPageNumber = cpnPageNumber;
  }

  public int getCpnPageSize() {
    return cpnPageSize;
  }

  public void setCpnPageSize(int cpnPageSize) {
    this.cpnPageSize = cpnPageSize;
  }

  public int getCpnTotalEntries() {
    return cpnTotalEntries;
  }

  public void setCpnTotalEntries(int cpnTotalEntries) {
    this.cpnTotalEntries = cpnTotalEntries;
  }

  public int getCpnNbPages() {
    return cpnNbPages;
  }

  public void setCpnNbPages(int cpnNbPages) {
    this.cpnNbPages = cpnNbPages;
  }

  public List<Company> getCpnList() {
    return cpnList;
  }

  public void setCpnList(List<Company> cpnList) {
    this.cpnList = cpnList;
  }

  public String getSearch() {
    return search;
  }

  public void setSearch(String search) {
    this.search = search;
  }

  public String getNameOrder() {
    return nameOrder;
  }

  public void setNameOrder(String nameOrder) {
    this.nameOrder = nameOrder;
  }

  public String getIntroducedOrder() {
    return introducedOrder;
  }

  public void setIntroducedOrder(String introducedOrder) {
    this.introducedOrder = introducedOrder;
  }

  public String getDiscontinuedOrder() {
    return discontinuedOrder;
  }

  public void setDiscontinuedOrder(String discontinuedOrder) {
    this.discontinuedOrder = discontinuedOrder;
  }

  public String getCompanyOrder() {
    return companyOrder;
  }

  public void setCompanyOrder(String companyOrder) {
    this.companyOrder = companyOrder;
  }

  // To build a new Page
  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private Pagination page = new Pagination();

    public Builder cpuPageNumber(int cpuPageNumber) {
      this.page.cpuPageNumber = cpuPageNumber;
      return this;
    }

    public Builder cpuPageSize(int cpuPageSize) {
      this.page.cpuPageSize = cpuPageSize;
      return this;
    }

    public Builder cpuTotalEntries(int cpuTotalEntries) {
      this.page.cpuTotalEntries = cpuTotalEntries;
      return this;
    }

    public Builder cpuNbPages(int cpuNbPages) {
      this.page.cpuNbPages = cpuNbPages;
      return this;
    }

    public Builder cpuList(List<ComputerDto> cpuList) {
      this.page.cpuList = cpuList;
      return this;
    }

    public Builder cpnPageNumber(int cpnPageNumber) {
      this.page.cpnPageNumber = cpnPageNumber;
      return this;
    }

    public Builder cpnPageSize(int cpnPageSize) {
      this.page.cpnPageSize = cpnPageSize;
      return this;
    }

    public Builder cpnTotalEntries(int cpnTotalEntries) {
      this.page.cpnTotalEntries = cpnTotalEntries;
      return this;
    }

    public Builder cpnNbPages(int cpnNbPages) {
      this.page.cpnNbPages = cpnNbPages;
      return this;
    }

    public Builder cpnList(List<Company> cpnList) {
      this.page.cpnList = cpnList;
      return this;
    }

    public Builder search(String search) {
      this.page.search = search;
      return this;
    }

    public Builder nameOrder(String nameOrder) {
      this.page.nameOrder = nameOrder;
      return this;
    }

    public Builder introducedOrder(String introducedOrder) {
      this.page.introducedOrder = introducedOrder;
      return this;
    }

    public Builder discontinuedOrder(String discontinuedOrder) {
      this.page.discontinuedOrder = discontinuedOrder;
      return this;
    }

    public Builder companyOrder(String companyOrder) {
      this.page.companyOrder = companyOrder;
      return this;
    }

    public Pagination build() {
      return this.page;
    }
  }
}
