package com.excilys.computerdatabase.persistence.dao.impl;

import com.excilys.computerdatabase.exception.DaoException;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.persistence.ConnectionJdbc;
import com.excilys.computerdatabase.persistence.dao.Dao;
import com.excilys.computerdatabase.persistence.mapping.query.Query;
import com.excilys.computerdatabase.persistence.mapping.query.QueryMapper;
import com.excilys.computerdatabase.persistence.mapping.rs.RsToCpn;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Class DAO for Companies, methods findAll, countEntries and find are defined, all others methods
 * from interface DAO (CRUD) will raise DaoIllegalMethodException.
 * 
 * @author lcoatanlem
 */
public class CompanyDaoImpl implements Dao<Company> {
  private static final CompanyDaoImpl INSTANCE = new CompanyDaoImpl();

  private ConnectionJdbc connJdbc;

  Logger log = Logger.getLogger(CompanyDaoImpl.class);

  private CompanyDaoImpl() {
    connJdbc = ConnectionJdbc.getInstance();
  }

  public static CompanyDaoImpl getInstance() {
    return INSTANCE;
  }

  @Override
  public int countEntries() {
    int size = 0;
    try (Connection conn = connJdbc.getConnection()) {
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM company");
      if (rs.next()) {
        size = rs.getInt(1);
      }
      stmt.close();
    } catch (SQLException exn) {
      // Database access error / closed connection / closed statement
      // returning something else than a ResultSet / timeout have been reached
      log.error("FATAL : " + exn);
      throw new DaoException(exn);
    }
    return size;
  }

  @Override
  public List<Company> findAll(Query query) {
    List<Company> list = new ArrayList<Company>();
    try (Connection conn = connJdbc.getConnection()) {
      PreparedStatement stmt = conn.prepareStatement(QueryMapper.toCpnFindAll(query));
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        list.add(RsToCpn.map(rs));
      }
      stmt.close();
    } catch (SQLException exn) {
      // Database access error / closed connection / closed statement
      // returning something else than a ResultSet / timeout have been reached
      log.error("FATAL : " + exn);
      throw new DaoException(exn);
    }
    return list;
  }

  @Override
  public Company find(Long id) {
    Company comp = null;
    try (Connection conn = connJdbc.getConnection()) {
      PreparedStatement stmt = conn.prepareStatement("SELECT * FROM company WHERE id = ?");
      stmt.setLong(1, id);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        comp = (RsToCpn.map(rs));
      }
      stmt.close();
    } catch (SQLException exn) {
      // Database access error / closed connection / closed statement
      // returning something else than a ResultSet / timeout have been reached
      log.error("FATAL : " + exn);
      throw new DaoException(exn);
    }
    return comp;
  }
}
