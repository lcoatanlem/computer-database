package com.excilys.computerdatabase.persistence.mapping.rs;

import com.excilys.computerdatabase.model.Company;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Class to map a result set with a Company.
 * 
 * @author lcoatanlem
 *
 */
public class RsToCpn {
  private static RsToCpn INSTANCE = new RsToCpn();
  
  private RsToCpn() {}
  
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

  public static RsToCpn getInstance() {
    return INSTANCE;
  }
}