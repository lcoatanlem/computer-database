package com.excilys.computerdatabase.persistence.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.excilys.computerdatabase.model.Company;

/**
 * Class to map a result set with either a Set<Company> or a Company.
 * @author lcoatanlem
 *
 */
public class CompanyMapping implements Mapping<Company>{

	@Override
	/**
	 * Method to map all a result set to a Set<Company>
	 * @param rs
	 * @return Set<Company>
	 */
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
	
	/**
	 * Method to map a result set with a Company
	 * @param rs
	 * @return Company
	 */
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
