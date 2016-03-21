package com.excilys.computerdatabase.persistence.mapping;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
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
public class ComputerMapping {
  private static final ComputerMapping INSTANCE = new ComputerMapping();

  Logger log = Logger.getLogger(ComputerMapping.class);

  private ComputerMapping() {
  }

  /**
   * Method to map a result set with a Computer.
   * 
   * @param rs
   *          the ResultSet to map to a Computer
   * @return Computer
   */

  public Computer map(ResultSet rs) {
    Computer cpu = null;
    try {
      // The name
      cpu = new Computer(rs.getString("name"));
      // The id
      cpu.setId(rs.getLong("id"));
      // The time it was introduced
      LocalDate introduced = (rs.getDate("introduced") == null) ? null
          : rs.getDate("introduced").toLocalDate();
      cpu.setIntroduced(introduced);
      // The time it was discontinued
      LocalDate discontinued = (rs.getDate("discontinued") == null) ? null
          : rs.getDate("discontinued").toLocalDate();
      cpu.setDiscontinued(discontinued);
      // The manufacturer
      Long cpnId = rs.getLong("company_id");
      Company manufacturer = null;
      if (cpnId != null) {
        // We retrieve the company from the db
        manufacturer = CompanyDaoImpl.getInstance().find(cpnId);
      }
      cpu.setManufacturer(manufacturer);
    } catch (SQLException exn) {
      // Problem with the ResultSet
      log.error("FATAL : " + exn);
      throw new RuntimeException(exn);
    }
    return cpu;
  }

  public static ComputerMapping getInstance() {
    return INSTANCE;
  }

}
