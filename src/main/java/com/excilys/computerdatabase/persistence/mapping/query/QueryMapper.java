package com.excilys.computerdatabase.persistence.mapping.query;

public class QueryMapper {

  /**
   * Mapping for findAll computers.
   *
   */
  public static String toComputerFindAll(Query query) {
    // Basic one
    StringBuilder queryBuilder = new StringBuilder("SELECT * FROM computer");

    if (query.getFilter() != null || query.getOrderCompany() != null) {
      // We need a join in both cases
      queryBuilder.append(" INNER JOIN company ON computer.company_id = company.id ");
      // If there is a filter
      if (query.getFilter() != null) {
        // 1,2,3,4 arguments of the PreparedStatement for filter
        queryBuilder.append("WHERE company.name LIKE ? OR computer.name LIKE ? "
            + "OR computer.introduced LIKE ? OR computer.discontinued LIKE ?");
      }
    }
    // Order
    if (query.getOrderName() != null || query.getOrderIntroduced() != null
        || query.getOrderDiscontinued() != null || query.getOrderCompany() != null) {
      // There is at least one order
      queryBuilder.append(" ORDER BY ");
      // for Computer name ordering
      if (query.getOrderName() != null) {
        queryBuilder.append("computer.name " + query.getOrderName());
      }
      // for introduced date ordering
      if (query.getOrderIntroduced() != null
          && queryBuilder.charAt(queryBuilder.length() - 1) == ' ') {
        queryBuilder.append("computer.introduced " + query.getOrderIntroduced());
      } else if (query.getOrderIntroduced() != null) {
        queryBuilder.append(", computer.introduced " + query.getOrderIntroduced());
      }
      // for discontinued date ordering
      if (query.getOrderDiscontinued() != null
          && queryBuilder.charAt(queryBuilder.length() - 1) == ' ') {
        queryBuilder.append("computer.discontinued " + query.getOrderDiscontinued());
      } else if (query.getOrderDiscontinued() != null) {
        queryBuilder.append(", computer.discontinued " + query.getOrderDiscontinued());
      }
      // for companies name ordering
      if (query.getOrderCompany() != null
          && queryBuilder.charAt(queryBuilder.length() - 1) == ' ') {
        queryBuilder.append("company.name " + query.getOrderCompany());
      } else if (query.getOrderCompany() != null) {
        queryBuilder.append(", company.name " + query.getOrderCompany());
      }
    }
    // Limit
    if (query.getLimit() > 0) {
      queryBuilder.append(" LIMIT ?");
      // Offset can exist iff Limit exists
      if (query.getOffset() > 0) {
        queryBuilder.append(" OFFSET ?");
      }
    }
    return queryBuilder.toString();
  }

  /**
   * Mapping for findAll companies.
   * 
   * @return the sql request as a String
   */
  public static String toCompanyFindAll(Query query) {
    // Basic one
    StringBuilder queryBuilder = new StringBuilder("SELECT * FROM company");
    // Limit
    if (query.getLimit() > 0) {
      queryBuilder.append(" LIMIT ?");
      // Offset can exist iff Limit exists
      if (query.getOffset() > 0) {
        queryBuilder.append(" OFFSET ?");
      }
    }
    return queryBuilder.toString();
  }

  /**
   * Mapping counting companies.
   */
  public static String toCompanyCount(Query query) {
    return "SELECT COUNT(*) FROM (" + toCompanyFindAll(query) + ") AS T";
  }

  /**
   * Mapping counting computers.
   */
  public static String toComputerCount(Query query) {
    return "SELECT COUNT(*) FROM (" + toComputerFindAll(query) + ") AS T";
  }
}
