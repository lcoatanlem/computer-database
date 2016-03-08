package com.excilys.computerdatabase.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

import com.excilys.computerdatabase.exception.NotSuchCompanyException;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.mapping.ComputerMapping;
import com.excilys.computerdatabase.persistence.mapping.Mapping;

public class ComputerDAO implements DAO<Computer>{

	@Override
	public Set<Computer> findAll() {
		Statement stmt;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT name,introduced,discontinued,company_id FROM computer;");
		} catch (SQLException e) {
			// Database acces error / closed connection / closed statement
			// returning something else than a ResultSet / timeout have been reached
			e.printStackTrace();
		}
		Mapping<Computer> mapping = new ComputerMapping();
		return mapping.mapAll(rs);
	}

	@Override
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
						+ t.getDiscontinued() + ", " + rs.getInt(2));
			} else {
				throw new NotSuchCompanyException();
			}
		} catch (SQLException e) {
			
		}
	}

	@Override
	public Computer read(Computer t) {
		return null;
	}

	@Override
	public void update(Computer t) {
	}

	@Override
	public void delete(Computer t) {
	}
	
	

}
