package main.java.com.excilys.computerdatabase.persistence.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;
import main.java.com.excilys.computerdatabase.model.Company;

/**
 * Class to map a result set with a Company.
 * @author lcoatanlem
 *
 */
public class CompanyMapping {
	/**
	 * 
	 * @param rs the iteration of the ResultSet we want to map
	 * @return Company
	 * @throws SQLException columnLabel invalid, database access error, called on a closed ResultSet
	 */
	public static Company map(ResultSet rs) throws SQLException{
		Company comp = new Company();
		comp.setId(rs.getLong("id"));
		comp.setName(rs.getString("name"));
		return (comp);
	}

}
