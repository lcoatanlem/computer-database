package com.excilys.computerdatabase.persistence.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.excilys.computerdatabase.model.Company;

public class CompanyMapping implements Mapping<Company>{

	@Override
	public Set<Company> mapAll(ResultSet rs) {
		Set<Company> liste = new HashSet<Company>();
		try {
			while (rs.next()){
				String name = rs.getString(1);
				liste.add(new Company(name));
			}
		} catch (SQLException e) {
			// Database access error / closed ResultSet
			e.printStackTrace();
		}
		return liste;
	}
	
	public Company mapCompany(ResultSet rs){
		String name = null;
		try {
			if (rs.next()){
				name = rs.getString(1);
			}
		} catch (SQLException e) {
			// Database access error / closed ResultSet
			e.printStackTrace();
		}
		return (new Company(name));
	}

}
