package com.excilys.computerdatabase.persistence.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.excilys.computerdatabase.model.Company;

/**
 * Class to map a result set with a Company.
 * @author lcoatanlem
 *
 */
public class CompanyMapping implements Mapping<Company>{
	
	/**
	 * Method to map a result set with a Company
	 * @param rs
	 * @return Company
	 */
	public Company map(ResultSet rs){
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
