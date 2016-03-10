package main.java.com.excilys.computerdatabase.persistence.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;
import main.java.com.excilys.computerdatabase.model.Company;

/**
 * Class to map a result set with a Company.
 * @author lcoatanlem
 *
 */
public class CompanyMapping implements Mapping<Company>{
	
	/**
	 * Method to map a result set with a Company.
	 * @param rs
	 * @return Company
	 */
	public Company map(ResultSet rs){
		Company comp = new Company();
		try{
			comp.setId(rs.getLong("id"));
			comp.setName(rs.getString("name"));
		} catch (SQLException e) {
			// Database access error / closed ResultSet
			e.printStackTrace();
		}
		return (comp);
	}

}
