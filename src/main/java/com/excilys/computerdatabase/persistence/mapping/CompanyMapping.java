package com.excilys.computerdatabase.persistence.mapping;

import com.excilys.computerdatabase.model.Company;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Class to map a result set with a Company.
 * 
 * @author lcoatanlem
 *
 */
public class CompanyMapping {
  private static CompanyMapping INSTANCE = new CompanyMapping();
  
  private CompanyMapping() {}
  
  /**
   * @param rs
   *          the iteration of the ResultSet we want to map.
   * @return Company
   */
  public static Company map(ResultSet rs) {
    Company comp = new Company();
    try {
      comp.setId(rs.getLong("id"));
      comp.setName(rs.getString("name"));
    } catch (SQLException exn) {
      throw new RuntimeException(exn);
    }
    return (comp);
  }

  public static CompanyMapping getInstance() {
    return INSTANCE;
  }
}
