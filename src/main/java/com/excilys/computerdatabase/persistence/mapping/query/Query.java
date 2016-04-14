package com.excilys.computerdatabase.persistence.mapping.query;

public class Query {

  public enum Order {
    ASC("ASC"), DESC("DESC");

    private String value;

    private Order(String value) {
      this.value = value;
    }

    /**
     * Validation of the enum.
     */
    public static Order safeValueOf(String value) {
      if (value == null) {
        return null;
      }
      for (Order ord : Order.values()) {
        if (value.equals(ord.value)) {
          return ord;
        }
      }
      return null;
    }
  }

  private int offset;
  private int limit;
  private String filter;
  private Order orderName;
  private Order orderIntroduced;
  private Order orderDiscontinued;
  private Order orderCompany;

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

  /**
   * If there is a filter, we add the % to put it in a Prepared Statement.
   */
  public void setFilter(String filter) {
    if (filter != null && !filter.isEmpty()) {
      this.filter = "%" + filter + "%";
    }
  }

  public Order getOrderName() {
    return orderName;
  }

  public void setOrderName(Order orderName) {
    this.orderName = orderName;
  }

  public Order getOrderIntroduced() {
    return orderIntroduced;
  }

  public void setOrderIntroduced(Order orderIntroduced) {
    this.orderIntroduced = orderIntroduced;
  }

  public Order getOrderDiscontinued() {
    return orderDiscontinued;
  }

  public void setOrderDiscontinued(Order orderDiscontinued) {
    this.orderDiscontinued = orderDiscontinued;
  }

  public Order getOrderCompany() {
    return orderCompany;
  }

  public void setOrderCompany(Order orderCompany) {
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

    /**
     * If there is a filter, we add the % to put it in a Prepared Statement.
     */
    public Builder filter(String filter) {
      if (filter != null && !filter.isEmpty()) {
        this.query.filter = "%" + filter + "%";
      }
      return this;
    }

    public Builder orderName(Order orderName) {
      this.query.orderName = orderName;
      return this;
    }

    public Builder orderIntroduced(Order orderIntroduced) {
      this.query.orderIntroduced = orderIntroduced;
      return this;
    }

    public Builder orderDiscontinued(Order orderDiscontinued) {
      this.query.orderDiscontinued = orderDiscontinued;
      return this;
    }

    public Builder orderCompany(Order orderCompany) {
      this.query.orderCompany = orderCompany;
      return this;
    }

    public Query build() {
      return this.query;
    }
  }

}
