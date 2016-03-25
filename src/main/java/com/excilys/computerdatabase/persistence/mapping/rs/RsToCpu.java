package com.excilys.computerdatabase.persistence.mapping.rs;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.model.Computer.Builder;
import com.excilys.computerdatabase.persistence.dao.impl.CompanyDaoImpl;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * Class to map a result set with a Computer.
 * 
 * @author lcoatanlem
 *
 */
public class RsToCpu {
  private static final RsToCpu INSTANCE = new RsToCpu();

  Logger log = Logger.getLogger(RsToCpu.class);

  private RsToCpu() {
  }

  /**
   * Method to map a result set with a Computer.
   * 
   * @param rs
   *          the ResultSet to map to a Computer
   * @return Computer
   */

  public Computer map(ResultSet rs) {
    Builder cpuB;
    try {
      // The name TODO : add builder
      cpuB = Computer.builder(rs.getString("name"));
      // The id
      cpuB.id(rs.getLong("id"));
      // The time it was introduced
      LocalDate introduced = (rs.getDate("introduced") == null) ? null
          : rs.getDate("introduced").toLocalDate();
      cpuB.introduced(introduced);
      // The time it was discontinued
      LocalDate discontinued = (rs.getDate("discontinued") == null) ? null
          : rs.getDate("discontinued").toLocalDate();
      cpuB.discontinued(discontinued);
      // The manufacturer
      Long cpnId = rs.getLong("company_id");
      Company manufacturer = null;
      if (cpnId != null) {
        // We retrieve the company from the db
        manufacturer = CompanyDaoImpl.getInstance().find(cpnId);
      }
      cpuB.manufacturer(manufacturer);
    } catch (SQLException exn) {
      // Problem with the ResultSet
      log.error("FATAL : " + exn);
      throw new RuntimeException(exn);
    }
    return cpuB.build();
  }

  public static RsToCpu getInstance() {
    return INSTANCE;
  }

}
