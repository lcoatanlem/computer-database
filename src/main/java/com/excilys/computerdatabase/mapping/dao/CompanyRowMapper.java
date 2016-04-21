package com.excilys.computerdatabase.mapping.dao;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Company.Builder;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CompanyRowMapper implements RowMapper<Company> {

  /**
   * Mapping a row from a ResultSet to a Company.
   */
  public Company mapRow(ResultSet resultSet, int rowNum) throws SQLException {
    Builder companyBuilder = Company.builder();
    companyBuilder.id(resultSet.getLong("id"));
    companyBuilder.name(resultSet.getString("name"));
    return (companyBuilder.build());
  }

}
