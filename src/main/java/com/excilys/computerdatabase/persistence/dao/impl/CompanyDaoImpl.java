package com.excilys.computerdatabase.persistence.dao.impl;

import com.excilys.computerdatabase.exception.DaoException;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.persistence.ConnectionJdbc;
import com.excilys.computerdatabase.persistence.dao.Dao;
import com.excilys.computerdatabase.persistence.mapping.query.Query;
import com.excilys.computerdatabase.persistence.mapping.query.QueryMapper;
import com.excilys.computerdatabase.persistence.mapping.rs.ResultSetToCompany;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class DAO for Companies, methods findAll, count and find are defined, all
 * others methods from interface DAO (CRUD) will raise IllegalMethodException.
 * 
 * @author lcoatanlem
 */
public class CompanyDaoImpl implements Dao<Company> {

  // TODO Spring
  private static final CompanyDaoImpl INSTANCE = new CompanyDaoImpl();
  private ConnectionJdbc connJdbc;
  private static final Logger LOGGER = Logger.getLogger(CompanyDaoImpl.class);

  private CompanyDaoImpl() {
    connJdbc = ConnectionJdbc.getInstance();
  }

  public static CompanyDaoImpl getInstance() {
    return INSTANCE;
  }

  @Override
  public int count(Query query) {
    int size = 0;
    try (Connection conn = connJdbc.getConnection()) {
      PreparedStatement stmt = conn.prepareStatement(QueryMapper.toCompanyCount(query));
      // Limit is defined
      if (query.getLimit() > 0) {
        stmt.setInt(1, query.getLimit());
        // Offset is defined
        if (query.getOffset() > 0) {
          stmt.setInt(2, query.getOffset());
        }
      }
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        size = rs.getInt(1);
      }
      stmt.close();
    } catch (SQLException exn) {
      // Database access error / closed connection / closed statement
      // returning something else than a ResultSet / timeout have been reached
      LOGGER.debug("DEBUG : " + exn.toString());
      throw new DaoException(exn);
    }
    return size;
  }

  @Override
  public List<Company> findAll(Query query) {
    List<Company> list = new ArrayList<Company>();
    try (Connection conn = connJdbc.getConnection()) {
      PreparedStatement stmt = conn.prepareStatement(QueryMapper.toCompanyFindAll(query));
      // Limit is defined
      if (query.getLimit() > 0) {
        stmt.setInt(1, query.getLimit());
        // Offset is defined
        if (query.getOffset() > 0) {
          stmt.setInt(2, query.getOffset());
        }
      }
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        list.add(ResultSetToCompany.map(rs));
      }
      stmt.close();
    } catch (SQLException exn) {
      // Database access error / closed connection / closed statement
      // returning something else than a ResultSet / timeout have been reached
      LOGGER.debug("DEBUG : " + exn);
      throw new DaoException(exn);
    }
    return list;
  }

  @Override
  public Company read(Long id) {
    Company comp = null;
    if (id == null) {
      throw new IllegalArgumentException("Id cannot be null.");
    }
    try (Connection conn = connJdbc.getConnection()) {
      PreparedStatement stmt = conn.prepareStatement("SELECT * FROM company WHERE id = ?");
      stmt.setLong(1, id);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) { // iteration dans mapping
        comp = (ResultSetToCompany.map(rs));
      }
      stmt.close();
    } catch (SQLException exn) {
      // Database access error / closed connection / closed statement
      // returning something else than a ResultSet / timeout have been reached
      LOGGER.debug("DEBUG : " + exn);
      throw new DaoException(exn);
    }
    return comp;
  }
}
