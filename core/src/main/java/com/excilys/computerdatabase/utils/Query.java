package com.excilys.computerdatabase.utils;

public class Query {

  public enum Order {
    ASC("ASC"), DESC("DESC");

    private String value;

    Order(String value) {
      this.value = value;
    }

    /**
     * Validation of the enum.
     * @param value the value
     * @return the order, ASC or DESC depending on the value, null for everything else
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
  private Order nameOrder;
  private Order introducedOrder;
  private Order discontinuedOrder;
  private Order companyOrder;

  public Order getNameOrder() {
    return nameOrder;
  }

  public void setNameOrder(Order nameOrder) {
    this.nameOrder = nameOrder;
  }

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
   * @param filter the filter
   */
  public void setFilter(String filter) {
    if (filter != null && !filter.isEmpty()) {
      this.filter = "%" + filter + "%";
    }
  }

  public Order getIntroducedOrder() {
    return introducedOrder;
  }

  public void setIntroducedOrder(Order introducedOrder) {
    this.introducedOrder = introducedOrder;
  }

  public Order getDiscontinuedOrder() {
    return discontinuedOrder;
  }

  public void setDiscontinuedOrder(Order discontinuedOrder) {
    this.discontinuedOrder = discontinuedOrder;
  }

  public Order getCompanyOrder() {
    return companyOrder;
  }

  public void setCompanyOrder(Order companyOrder) {
    this.companyOrder = companyOrder;
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
     * @param filter the filter
     * @return this (Builder)
     */
    public Builder filter(String filter) {
      if (filter != null && !filter.isEmpty()) {
        this.query.filter = "%" + filter + "%";
      }
      return this;
    }

    public Builder nameOrder(Order nameOrder) {
      this.query.nameOrder = nameOrder;
      return this;
    }

    public Builder introducedOrder(Order introducedOrder) {
      this.query.introducedOrder = introducedOrder;
      return this;
    }

    public Builder discontinuedOrder(Order discontinuedOrder) {
      this.query.discontinuedOrder = discontinuedOrder;
      return this;
    }

    public Builder companyOrder(Order companyOrder) {
      this.query.companyOrder = companyOrder;
      return this;
    }

    public Query build() {
      return this.query;
    }
  }

}
