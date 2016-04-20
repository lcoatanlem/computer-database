package com.excilys.computerdatabase.persistence.dao.impl;

import com.excilys.computerdatabase.exception.DaoException;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.ConnectionJdbc;
import com.excilys.computerdatabase.persistence.dao.Dao;
import com.excilys.computerdatabase.persistence.mapping.query.Query;
import com.excilys.computerdatabase.persistence.mapping.query.QueryMapper;
import com.excilys.computerdatabase.persistence.mapping.rs.ResultSetToComputer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 * Class DAO for Computers, every method from interface DAO is implemented.
 * 
 * @author lcoatanlem
 */
@Repository ("computerDao")
public class ComputerDaoImpl implements Dao<Computer> {

  private ConnectionJdbc connJdbc;
  private static final Logger LOGGER = Logger.getLogger(ComputerDaoImpl.class);
  
  @Autowired
  private ResultSetToComputer rsToComputer;

  public ComputerDaoImpl() {
    connJdbc = ConnectionJdbc.getInstance();
  }

  @Override
  public int count(Query query) {
    int size = 0;
    try (Connection conn = connJdbc.getConnection()) {
      PreparedStatement stmt = conn.prepareStatement(QueryMapper.toComputerCount(query));
      // There is a filter
      if (query.getFilter() != null) {
        // 1,2,3,4 arguments of the PreparedStatement for filter
        stmt.setString(1, query.getFilter());
        stmt.setString(2, query.getFilter());
        stmt.setString(3, query.getFilter());
        stmt.setString(4, query.getFilter());
        // Limit
        if (query.getLimit() > 0) {
          stmt.setInt(5, query.getLimit());
          // Offset can exist iff Limit exists
          if (query.getOffset() > 0) {
            stmt.setInt(6, query.getOffset());
          }
        }
      } else {
        // Limit
        if (query.getLimit() > 0) {
          stmt.setInt(1, query.getLimit());
          // Offset can exist iff Limit exists
          if (query.getOffset() > 0) {
            stmt.setInt(2, query.getOffset());
          }
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
  public List<Computer> findAll(Query query) {
    List<Computer> list = new ArrayList<Computer>();
    try (Connection conn = connJdbc.getConnection()) {
      PreparedStatement stmt = conn.prepareStatement(QueryMapper.toComputerFindAll(query));
      // There is a filter
      if (query.getFilter() != null) {
        // 1,2,3,4 arguments of the PreparedStatement for filter
        stmt.setString(1, query.getFilter());
        stmt.setString(2, query.getFilter());
        stmt.setString(3, query.getFilter());
        stmt.setString(4, query.getFilter());
        // Limit
        if (query.getLimit() > 0) {
          stmt.setInt(5, query.getLimit());
          // Offset can exist iff Limit exists
          if (query.getOffset() > 0) {
            stmt.setInt(6, query.getOffset());
          }
        }
      } else {
        // Limit
        if (query.getLimit() > 0) {
          stmt.setInt(1, query.getLimit());
          // Offset can exist iff Limit exists
          if (query.getOffset() > 0) {
            stmt.setInt(2, query.getOffset());
          }
        }
      }
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        list.add(rsToComputer.map(rs));
      }
      stmt.close();
    } catch (SQLException exn) {
      // Database access error / closed connection / closed statement
      // returning something else than a ResultSet / timeout have been reached
      LOGGER.debug("DEBUG : " + exn.toString());
      throw new DaoException(exn);
    }
    return list;
  }

  @Override
  public Computer read(Long id) {
    Computer comp = null;
    try (Connection conn = connJdbc.getConnection()) {
      PreparedStatement stmt = conn.prepareStatement("SELECT * FROM computer WHERE id = ?");
      stmt.setLong(1, id);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        comp = (rsToComputer.map(rs));
      }
      stmt.close();
    } catch (SQLException exn) {
      // Database access error / closed connection / closed statement
      // returning something else than a ResultSet / timeout have been reached
      LOGGER.debug("DEBUG : " + exn.toString());
      throw new DaoException(exn);
    }
    return comp;
  }

  @Override
  public void create(Computer cpu) {
    try (Connection conn = connJdbc.getConnection()) {
      PreparedStatement stmt = conn.prepareStatement(
          "INSERT INTO computer(name,introduced,discontinued,company_id) VALUES (?,?,?,?)");
      // Fill the query
      stmt.setString(1, cpu.getName());
      stmt.setDate(2, (cpu.getIntroduced() == null ? null : Date.valueOf(cpu.getIntroduced())));
      stmt.setDate(3, (cpu.getDiscontinued() == null ? null : Date.valueOf(cpu.getDiscontinued())));
      Long idCpn = (cpu.getManufacturer() == null ? null : cpu.getManufacturer().getId());
      if (idCpn == null) {
        stmt.setNull(4, Types.NULL);
      } else {
        stmt.setLong(4, idCpn);
      }
      stmt.executeUpdate();
      stmt.close();
    } catch (SQLException exn) {
      LOGGER.debug("DEBUG : " + exn.toString());
      // Database access error / closed connection / closed statement
      // returning something else than a ResultSet / timeout have been reached
      throw new DaoException(exn);
    }
  }

  @Override
  public void update(Computer cpu) {
    try (Connection conn = connJdbc.getConnection()) {
      PreparedStatement stmt = conn
          .prepareStatement("UPDATE computer SET name = ?, introduced = ?, "
              + "discontinued = ?, company_id = ? WHERE id = ?");
      // Fill the query
      stmt.setString(1, cpu.getName());
      stmt.setDate(2, (cpu.getIntroduced() == null ? null : Date.valueOf(cpu.getIntroduced())));
      stmt.setDate(3, (cpu.getDiscontinued() == null ? null : Date.valueOf(cpu.getDiscontinued())));
      Long idCpn = (cpu.getManufacturer() == null ? null : cpu.getManufacturer().getId());
      if (idCpn == null) {
        stmt.setNull(4, Types.NULL);
      } else {
        stmt.setLong(4, idCpn);
      }
      stmt.setLong(5, cpu.getId());
      stmt.executeUpdate();
      stmt.close();
    } catch (SQLException exn) {
      // Database access error / closed connection / closed statement
      // returning something else than a ResultSet / timeout have been reached
      LOGGER.debug("DEBUG : " + exn.toString());
      throw new DaoException(exn);
    }
  }

  @Override
  public void delete(Long id) {
    try (Connection conn = connJdbc.getConnection()) {
      PreparedStatement stmt = conn.prepareStatement("DELETE FROM computer WHERE id = ?");
      read(id);
      stmt.setLong(1, id);
      stmt.executeUpdate();
      stmt.close();
    } catch (SQLException exn) {
      // Database access error / closed connection / closed statement
      // returning something else than a ResultSet / timeout have been reached
      LOGGER.debug("DEBUG : " + exn.toString());
      throw new DaoException(exn);
    }
  }
}
