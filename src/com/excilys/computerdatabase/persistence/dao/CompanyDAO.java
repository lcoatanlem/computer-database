package com.excilys.computerdatabase.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.persistence.mapping.CompanyMapping;
import com.excilys.computerdatabase.persistence.mapping.Mapping;


/**
 * Class DAO for Companies, methods findAll() and find(int id) are defined, all others methods from
 * interface DAO (CRUD) will raise UnavailableException(message).
 * @author lcoatanlem
 */
public class CompanyDAO implements DAO<Company> {
	
	@Override
	/**
	 * findAll returns a mapped Set<Company>
	 */
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

	/**
	 * Method to find a Company in DB from an id. Will return a mapped Company.
	 * @param id
	 * @return Company
	 */
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
