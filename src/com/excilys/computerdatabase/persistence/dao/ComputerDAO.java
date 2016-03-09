package com.excilys.computerdatabase.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.excilys.computerdatabase.exception.NotSuchCompanyException;
import com.excilys.computerdatabase.exception.NotSuchComputerException;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.mapping.ComputerMapping;
import com.excilys.computerdatabase.persistence.mapping.Mapping;

/**
 * Class DAO for Computers, every method from interface DAO is implemented.
 * @author lcoatanlem
 */
public class ComputerDAO implements DAO<Computer>{

	@Override
	/**
	 * findAll returns a mapped Set<Computer>.
	 */
	public Set<Computer> findAll() {
		Statement stmt;
		ResultSet rs = null;
		Set<Computer> liste = new HashSet<Computer>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM computer;");
			Mapping<Computer> mapping = new ComputerMapping();
			liste.add(((ComputerMapping) mapping).map(rs));
		} catch (SQLException e) {
			// Database acces error / closed connection / closed statement
			// returning something else than a ResultSet / timeout have been reached
			e.printStackTrace();
		}
		return liste;
	}

	@Override
	/**
	 * Creates a new line in the DB for the Computer t.
	 */
	public void create(Computer t) throws NotSuchCompanyException {
		Statement stmt;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT id FROM company WHERE name = " + t.getManufacturer().getName() + ";");
			if (rs.next()){
				Statement stmt2 = conn.createStatement();
				stmt2.executeUpdate("INSERT INTO computer(name,introduced,discontinued,company_id) "
						+ "VALUES (" + t.getName() + ", " + t.getIntroduced() + ", "
						+ t.getDiscontinued() + ", " + rs.getLong("id"));
			} else {
				throw new NotSuchCompanyException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	/**
	 * Reads, and gives full information about a specific Computer (using id).
	 */
	public Computer read(Long id) throws NotSuchComputerException {
		Statement stmt;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM computer WHERE id=" + id + ";");
		} catch (SQLException e) {
			// Database acces error / closed connection / closed statement
			// returning something else than a ResultSet / timeout have been reached
			e.printStackTrace();
		}
		Mapping<Computer> mapping = new ComputerMapping();
		return ((ComputerMapping) mapping).map(rs);		
	}

	@Override
	/**
	 * Update a computer in the DB. If the id of the given Computer doesn't exists, raises
	 * a NotSuchComputerException.
	 */
	public void update(Computer t) throws NotSuchComputerException {
		Statement stmt;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM computer WHERE id = " + t.getId() + ";");
			if (rs.next()){
				Statement stmt2 = conn.createStatement();
				stmt2.executeUpdate("UPDATE computer "
						+ "SET name=" + t.getName() + ", "
						+ "introduced=" + t.getIntroduced() + ", "
						+ "discontinued=" + t.getDiscontinued() + ", "
						+ "company_id= " + t.getManufacturer().getId()
						+ ", WHERE id=" + t.getId() + ";");
			} else {
				throw new NotSuchComputerException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	/**
	 * Delete a computer in the DB. If the id of the given Computer doesn't exists, raises
	 * a NotSuchComputerException.
	 */
	public void delete(Computer t) throws NotSuchComputerException {
		Statement stmt;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM computer WHERE id = " + t.getId() + ";");
			if (rs.next()){
				Statement stmt2 = conn.createStatement();
				stmt2.executeUpdate("DELETE FROM computer WHERE id = " + t.getId() + ";");
			} else {
				throw new NotSuchComputerException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	

}
