package com.excilys.computerdatabase.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdatabase.exception.NotSuchCompanyException;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.persistence.mapping.CompanyMapping;
import com.excilys.computerdatabase.persistence.mapping.Mapping;


/**
 * Class DAO for Companies, methods findAll() and find(Long id) are defined, all others methods from
 * interface DAO (CRUD) will raise UnavailableException(message).
 * @author lcoatanlem
 */
public class CompanyDAO implements DAO<Company> {
	
	@Override
	public List<Company> findAll() {
		List<Company> liste = new ArrayList<Company>();
		try {
			Statement stmt = conn.createStatement();			
			ResultSet rs = stmt.executeQuery("SELECT * FROM company;");
			Mapping<Company> mapper = new CompanyMapping();
			while (rs.next()){
				liste.add(((CompanyMapping) mapper).map(rs));
			}
		} catch (SQLException e) {
			// Database acces error / closed connection / closed statement
			// returning something else than a ResultSet / timeout have been reached
			e.printStackTrace();
		}
		return liste;
	}

/**
 * Method to find a Company in DB from an id. Will return a mapped Company.
 * @param id
 * @return Company
 * @throws NotSuchCompanyException when we try to find with an invalid ID
 */
	public Company find(Long id) throws NotSuchCompanyException{
		Company comp = null;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM company where id = " + id + ";");
			Mapping<Company> mapping = new CompanyMapping();
			if (rs.next()){
				comp = ((CompanyMapping) mapping).map(rs);
			}else{
				throw new NotSuchCompanyException("No company for this ID...");
			}
		} catch (SQLException e) {
			// Database acces error / closed connection / closed statement
			// returning something else than a ResultSet / timeout have been reached
			e.printStackTrace();
		}
		return comp;
	}
	
	/*public static void main(String[] args){
		CompanyDAO cdao = new CompanyDAO();
		cdao.findAll();
	}*/
	
}
