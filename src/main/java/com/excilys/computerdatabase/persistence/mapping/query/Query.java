package com.excilys.computerdatabase.persistence.mapping.query;

public class Query {

  private int offset;
  private int limit;
  private String filter;
  private String orderName;
  private String orderIntroduced;
  private String orderDiscontinued;
  private String orderCompany;

  public int getOffset() {
    return offset;
  }

  public void setOffset(int offset) {
    this.offset = offset;
  }

  public int getLimit() {
    return limit;
  }

  public void setLimit(int limit) {
    this.limit = limit;
  }

  public String getFilter() {
    return filter;
  }

  public void setFilter(String filter) {
    this.filter = filter;
  }

  public String getOrderName() {
    return orderName;
  }

  public void setOrderName(String orderName) {
    this.orderName = orderName;
  }

  public String getOrderIntroduced() {
    return orderIntroduced;
  }

  public void setOrderIntroduced(String orderIntroduced) {
    this.orderIntroduced = orderIntroduced;
  }

  public String getOrderDiscontinued() {
    return orderDiscontinued;
  }

  public void setOrderDiscontinued(String orderDiscontinued) {
    this.orderDiscontinued = orderDiscontinued;
  }

  public String getOrderCompany() {
    return orderCompany;
  }

  public void setOrderCompany(String orderCompany) {
    this.orderCompany = orderCompany;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private Query query = new Query();

    public Builder offset(int offset) {
      this.query.offset = offset;
      return this;
    }

    public Builder limit(int limit) {
      this.query.limit = limit;
      return this;
    }

    public Builder filter(String filter) {
      this.query.filter = filter;
      return this;
    }

    public Builder orderName(String orderName) {
      this.query.orderName = orderName;
      return this;
    }

    public Builder orderIntroduced(String orderIntroduced) {
      this.query.orderIntroduced = orderIntroduced;
      return this;
    }

    public Builder orderDiscontinued(String orderDiscontinued) {
      this.query.orderDiscontinued = orderDiscontinued;
      return this;
    }

    public Builder orderCompany(String orderCompany) {
      this.query.orderCompany = orderCompany;
      return this;
    }

    public Query build() {
      return this.query;
    }
  }

}
