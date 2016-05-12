package com.excilys.computerdatabase.mapping.query;

import java.util.Map;

import com.excilys.computerdatabase.utils.Query;
import com.excilys.computerdatabase.utils.Query.Order;

public class QueryMapper {

  /**
   * Mapping for findAll computers.
   */
  public static String toComputerFindAll(Query query) {
    // Basic one
    StringBuilder queryBuilder = new StringBuilder(
        "SELECT computer.id, computer.name, introduced, discontinued, company_id FROM computer");

    if (query.getFilter() != null || query.getCompanyOrder() != null) {
      // We need a join in both cases
      queryBuilder.append(" LEFT OUTER JOIN company ON computer.company_id = company.id ");
      // If there is a filter
      if (query.getFilter() != null) {
        // 1,2,3,4 arguments of the PreparedStatement for filter
        queryBuilder.append("WHERE company.name LIKE ? OR computer.name LIKE ? "
            + "OR computer.introduced LIKE ? OR computer.discontinued LIKE ?");
      }
    }
    // Order
    if (query.getNameOrder() != null || query.getIntroducedOrder() != null
        || query.getDiscontinuedOrder() != null || query.getCompanyOrder() != null) {
      // There is at least one order
      queryBuilder.append(" ORDER BY ");
      // for Computer name ordering
      if (query.getNameOrder() != null) {
        queryBuilder.append("computer.name " + query.getNameOrder());
      }
      // for introduced date ordering
      if (query.getIntroducedOrder() != null
          && queryBuilder.charAt(queryBuilder.length() - 1) == ' ') {
        queryBuilder.append("computer.introduced " + query.getIntroducedOrder());
      } else if (query.getIntroducedOrder() != null) {
        queryBuilder.append(", computer.introduced " + query.getIntroducedOrder());
      }
      // for discontinued date ordering
      if (query.getDiscontinuedOrder() != null
          && queryBuilder.charAt(queryBuilder.length() - 1) == ' ') {
        queryBuilder.append("computer.discontinued " + query.getDiscontinuedOrder());
      } else if (query.getDiscontinuedOrder() != null) {
        queryBuilder.append(", computer.discontinued " + query.getDiscontinuedOrder());
      }
      // for companies name ordering
      if (query.getCompanyOrder() != null
          && queryBuilder.charAt(queryBuilder.length() - 1) == ' ') {
        queryBuilder.append("company.name " + query.getCompanyOrder());
      } else if (query.getCompanyOrder() != null) {
        queryBuilder.append(", company.name " + query.getCompanyOrder());
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

  /**
   * Mapping read a company.
   */
  public static String toCompanyRead() {
    return "SELECT * FROM company WHERE id = ?";
  }

  /**
   * Mapping read a computer.
   */
  public static String toComputerRead() {
    return "SELECT * FROM computer WHERE id = ?";
  }

  /**
   * Mapping create a computer.
   */
  public static String toComputerCreate() {
    return "INSERT INTO computer(name,introduced,discontinued,company_id) VALUES (?,?,?,?)";
  }

  /**
   * Mapping update a computer.
   */
  public static String toComputerUpdate() {
    return "UPDATE computer SET name = ?, introduced = ?, "
        + "discontinued = ?, company_id = ? WHERE id = ?";
  }

  /**
   * Mapping delete a computer.
   */
  public static String toComputerDelete() {
    return "DELETE FROM computer WHERE id = ?";
  }

  /**
   * Mapping to delete computers by company id.
   */
  public static String toComputerDeleteByCompany() {
    return "DELETE FROM computer WHERE company_id = ?";
  }

  /**
   * Mapping to delete a company.
   */
  public static String toCompanyDelete() {
    return "DELETE FROM company WHERE id = ?";
  }

  /**
   * Mapping from a map<String,String> to a Query.
   */
  public static Query fromMaptoQuery(Map<String, String> params) {
    if (params == null) {
      return Query.builder().build();
    } else {
      String numPage = params.get("numPage");
      String limit = params.get("limit");
      String search = params.get("search");
      Order nameOrder = Order.safeValueOf(params.get("nameOrder"));
      Order introducedOrder = Order.safeValueOf(params.get("introducedOrder"));
      Order discontinuedOrder = Order.safeValueOf(params.get("discontinuedOrder"));
      Order companyOrder = Order.safeValueOf(params.get("companyOrder"));
      Query.Builder queryBuilder = Query.builder();
      if (numPage != null && !numPage.trim().isEmpty()) {
        queryBuilder = queryBuilder.offset(Integer.parseInt(numPage));
      }
      if (limit != null && !limit.trim().isEmpty()) {
        queryBuilder = queryBuilder.limit(Integer.parseInt(limit));
      }
      Query query = queryBuilder.filter(search == null ? null : search)
          .nameOrder(nameOrder == null ? null : nameOrder)
          .introducedOrder(introducedOrder == null ? null : introducedOrder)
          .discontinuedOrder(discontinuedOrder == null ? null : discontinuedOrder)
          .companyOrder(companyOrder == null ? null : companyOrder).build();
      return query;
    }
  }

}
