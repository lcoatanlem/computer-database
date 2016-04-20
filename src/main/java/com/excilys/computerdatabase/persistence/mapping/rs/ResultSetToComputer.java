package com.excilys.computerdatabase.persistence.mapping.rs;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.model.Computer.Builder;
import com.excilys.computerdatabase.persistence.dao.impl.CompanyDaoImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * Class to map a result set with a Computer.
 * 
 * @author lcoatanlem
 *
 */
@Component
public class ResultSetToComputer {
  
  Logger log = Logger.getLogger(ResultSetToComputer.class);

  @Autowired
  @Qualifier ("companyDao")
  private CompanyDaoImpl companyDao;

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
      // The name
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
        manufacturer = companyDao.read(cpnId);
      }
      cpuB.manufacturer(manufacturer);
    } catch (SQLException exn) {
      // Problem with the ResultSet
      log.error("FATAL : " + exn);
      throw new RuntimeException(exn);
    }
    return cpuB.build();
  }

}
