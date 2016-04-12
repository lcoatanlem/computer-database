package com.excilys.computerdatabase.persistence.mapping.query;

public class QueryMapper {

  /**
   * Mapping for findAll computers.
   * TODO : faire une enum pour eviter les injections sql dans les orderby.
   * 
   * @return the sql request as a String
   */
  public static String toCpuFindAll(Query query) {
    // Basic one
    String queryStr = "SELECT * FROM computer";
    // Filter
    String filter = query.getFilter();
    if (filter != null || query.getOrderCompany() != null) {
      // We need a join in both cases
      queryStr += " INNER JOIN company ON computer.company_id = company.id ";
      // If there is a filter
      if (filter != null) {
        queryStr += "WHERE company.name LIKE '%" + filter + "%' " + "OR computer.name LIKE '%"
            + filter + "%' " + "OR computer.introduced LIKE '%" + filter + "%' "
            + "OR computer.discontinued LIKE '%" + filter + "%'";
      }
    }
    // Order
    if (query.getOrderName() != null || query.getOrderIntroduced() != null
        || query.getOrderDiscontinued() != null || query.getOrderCompany() != null) {
      // base
      queryStr += " ORDER BY ";
      // for Computer name ordering
      if (query.getOrderName() != null) {
        queryStr += " computer.name " + query.getOrderName();
      }
      // for introduced date ordering
      if (query.getOrderIntroduced() != null && queryStr.endsWith(" ")) {
        queryStr += " computer.introduced " + query.getOrderIntroduced();
      } else if (query.getOrderIntroduced() != null) {
        queryStr += ", computer.introduced " + query.getOrderIntroduced();
      }
      // for discontinued date ordering
      if (query.getOrderDiscontinued() != null && queryStr.endsWith(" ")) {
        queryStr += " computer.discontinued " + query.getOrderDiscontinued();
      } else if (query.getOrderDiscontinued() != null) {
        queryStr += ", computer.discontinued " + query.getOrderDiscontinued();
      }
      // for companies name ordering
      if (query.getOrderCompany() != null && queryStr.endsWith(" ")) {
        queryStr += " company.name " + query.getOrderCompany();
      } else if (query.getOrderCompany() != null) {
        queryStr += ", company.name " + query.getOrderCompany();
      }
    }
    // Limit
    if (query.getLimit() > 0) {
      queryStr += " LIMIT " + query.getLimit();
    }
    // Offset
    if (query.getOffset() > 0) {
      queryStr += " OFFSET " + query.getOffset();
    }
    // using LIMIT X OFFSET X because it is compatible MySql and others DBGS
    System.out.println(queryStr);
    return queryStr;
  }
  
  /**
   * Mapping for findAll companies.
   * 
   * @return the sql request as a String
   */
  public static String toCpnFindAll(Query query) {
    // Basic one
    String queryStr = "SELECT * FROM company";
    // Limit
    if (query.getLimit() > 0) {
      queryStr += " LIMIT " + query.getLimit();
    }
    // Offset
    if (query.getOffset() > 0) {
      queryStr += " OFFSET " + query.getOffset();
    }
    // using LIMIT X OFFSET X because it is compatible MySql and others DBGS
    System.out.println(queryStr);
    return queryStr;
  }
}
