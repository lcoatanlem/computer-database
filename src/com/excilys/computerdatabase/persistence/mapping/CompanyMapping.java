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
				Long id = rs.getLong("id");
				String name = rs.getString("name");
				Company comp = new Company();
				comp.setId(id);
				comp.setName(name);
				liste.add(comp);
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
		Long id = null;
		String name = null;
		Company comp = new Company();
		try {
			if (rs.next()){
				id = rs.getLong("id");
				name = rs.getString("name");
				comp.setId(id);
				comp.setName(name);
			}
		} catch (SQLException e) {
			// Database access error / closed ResultSet
			e.printStackTrace();
		}
		return (comp);
	}

}
