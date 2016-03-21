package com.excilys.computerdatabase.persistence.dao.impl;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.ConnectionJdbc;
import com.excilys.computerdatabase.persistence.dao.Dao;
import com.excilys.computerdatabase.persistence.mapping.ComputerMapping;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 * Class DAO for Computers, every method from interface DAO is implemented.
 * 
 * @author lcoatanlem
 */
public class ComputerDaoImpl implements Dao<Computer> {
  private static final ComputerDaoImpl INSTANCE = new ComputerDaoImpl();

  private ConnectionJdbc connJdbc;

  Logger log = Logger.getLogger(ComputerDaoImpl.class);

  private ComputerDaoImpl() {
    connJdbc = ConnectionJdbc.getInstance();
  }

  public static ComputerDaoImpl getInstance() {
    return INSTANCE;
  }
  
  @Override
  public int countEntries() {
    int size = 0;
    try (Statement stmt = connJdbc.getConnection().createStatement()) {
      ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM computer");
      if (rs.next()) {
        size = rs.getInt(1);
      }
    } catch (SQLException exn) {
      // Database access error / closed connection / closed statement
      // returning something else than a ResultSet / timeout have been reached
      log.error("FATAL : " + exn);
      throw new RuntimeException(exn);
    }
    return size;
  }

  @Override
  public List<Computer> findAll(int offset, int limit) {
    List<Computer> list = new ArrayList<Computer>();
    try (PreparedStatement stmt = connJdbc.getConnection()
        .prepareStatement("SELECT * FROM computer LIMIT ?, ?")) {
      stmt.setInt(1, offset);
      stmt.setInt(2, limit);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        list.add((ComputerMapping.getInstance().map(rs)));
      }

    } catch (SQLException exn) {
      // Database access error / closed connection / closed statement
      // returning something else than a ResultSet / timeout have been reached
      log.error("FATAL : " + exn);
      throw new RuntimeException(exn);
    }
    return list;
  }

  @Override
  public Computer find(Long id) {
    Computer comp = null;
    try (PreparedStatement stmt = connJdbc.getConnection()
        .prepareStatement("SELECT * FROM computer WHERE id = ?")) {
      stmt.setLong(1, id);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        comp = (ComputerMapping.getInstance().map(rs));
      }
    } catch (SQLException exn) {
      // Database access error / closed connection / closed statement
      // returning something else than a ResultSet / timeout have been reached
      log.error("FATAL : " + exn);
      throw new RuntimeException(exn);
    }
    return comp;
  }

  @Override
  public void create(Computer cpu) {
    try (PreparedStatement stmt = connJdbc.getConnection().prepareStatement(
        "INSERT INTO computer(name,introduced,discontinued,company_id) VALUES (?,?,?,?)")) {
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
    } catch (SQLException exn) {
      log.error("FATAL : " + exn);
      // Database access error / closed connection / closed statement
      // returning something else than a ResultSet / timeout have been reached
      throw new RuntimeException(exn);
    }
  }

  @Override
  public void update(Computer cpu) {
    try (PreparedStatement stmt = connJdbc.getConnection()
        .prepareStatement("UPDATE computer SET name = ?, introduced = ?, "
            + "discontinued = ?, company_id = ? WHERE id = ?")) {
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
    } catch (SQLException exn) {
      // Database access error / closed connection / closed statement
      // returning something else than a ResultSet / timeout have been reached
      log.error("FATAL : " + exn);
      throw new RuntimeException(exn);
    }
  }

  @Override
  public void delete(Long id) {
    try (PreparedStatement stmt = connJdbc.getConnection()
        .prepareStatement("DELETE FROM computer WHERE id = ?")) {
      find(id);
      stmt.setLong(1, id);
      stmt.executeUpdate();
    } catch (SQLException exn) {
      // Database access error / closed connection / closed statement
      // returning something else than a ResultSet / timeout have been reached
      log.error("FATAL : " + exn);
      throw new RuntimeException(exn);
    }
  }
}
