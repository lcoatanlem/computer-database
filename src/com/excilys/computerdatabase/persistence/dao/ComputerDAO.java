package com.excilys.computerdatabase.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

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
			rs = stmt.executeQuery("SELECT name,intoduced,discontinued,company_id FROM computer;");
		} catch (SQLException e) {
			// Database acces error / closed connection / closed statement
			// returning something else than a ResultSet / timeout have been reached
			e.printStackTrace();
		}
		Mapping<Computer> mapping = new ComputerMapping();
		return mapping.mapAll(rs);
	}

	@Override
	public void create(Computer t) {		
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
