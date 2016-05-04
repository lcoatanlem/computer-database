package com.excilys.computerdatabase.utils;

import com.excilys.computerdatabase.dao.Dao;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.model.Computer.Builder;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

@Component
public class ComputerRowMapper implements RowMapper<Computer> {

  private static final Logger LOGGER = Logger.getLogger(ComputerRowMapper.class);

  @Autowired
  @Qualifier("companyDao")
  private Dao<Company> companyDao;

  /**
   * Mapping a row from a ResultSet to a Computer.
   */
  public Computer mapRow(ResultSet resultSet, int rowNum) throws SQLException {
    Builder cpuB;
    try {
      // The name
      cpuB = Computer.builder(resultSet.getString("name"));
      // The id
      cpuB.id(resultSet.getLong("id"));
      // The time it was introduced
      LocalDate introduced = (resultSet.getDate("introduced") == null) ? null
          : resultSet.getDate("introduced").toLocalDate();
      cpuB.introduced(introduced);
      // The time it was discontinued
      LocalDate discontinued = (resultSet.getDate("discontinued") == null) ? null
          : resultSet.getDate("discontinued").toLocalDate();
      cpuB.discontinued(discontinued);
      // The manufacturer
      Long cpnId = resultSet.getLong("company_id");
      Company manufacturer = null;
      if (cpnId != null) {
        // We retrieve the company from the db
        manufacturer = companyDao.read(cpnId);
      }
      cpuB.manufacturer(manufacturer);
    } catch (SQLException exn) {
      // Problem with the ResultSet
      LOGGER.debug("DEBUG : " + exn);
      throw new RuntimeException(exn);
    }
    return cpuB.build();
  }

}
