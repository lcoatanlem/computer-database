package com.excilys.computerdatabase.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.excilys.computerdatabase.model.User;
import com.excilys.computerdatabase.model.User.Builder;

@Component
public class UserRowMapper implements RowMapper<User> {

  private static final Logger LOGGER = Logger.getLogger(ComputerRowMapper.class);

  /**
   * Mapping a row from a ResultSet to a User.
   */
  public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
    Builder userBuilder = User.builder();
    try {
      // The login
      userBuilder.login(resultSet.getString("login"));
      // The password
      userBuilder.password(resultSet.getString("password"));
    } catch (SQLException exn) {
      // Problem with the ResultSet
      LOGGER.debug("DEBUG : " + exn);
      throw new RuntimeException(exn);
    }
    return userBuilder.build();
  }

}
