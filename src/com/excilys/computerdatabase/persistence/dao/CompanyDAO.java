package com.excilys.computerdatabase.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.persistence.mapping.CompanyMapping;
import com.excilys.computerdatabase.persistence.mapping.Mapping;

public class CompanyDAO implements DAO<Company> {

	@Override
	public Set<Company> findAll() {
		Statement stmt;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT name FROM company;");
		} catch (SQLException e) {
			// Database acces error / closed connection / closed statement
			// returning something else than a ResultSet / timeout have been reached
			e.printStackTrace();
		}
		Mapping<Company> mapping = new CompanyMapping();
		return mapping.mapAll(rs);
	}
	
	public Company find(int id){
		Statement stmt;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT name FROM company where id = " + id + ";");
		} catch (SQLException e) {
			// Database acces error / closed connection / closed statement
			// returning something else than a ResultSet / timeout have been reached
			e.printStackTrace();
		}
		Mapping<Company> mapping = new CompanyMapping();
		return ((CompanyMapping) mapping).mapCompany(rs);
	}

}
