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
	 * @param rs the iteration of the ResultSet we want to map
	 * @return Company
	 */
	public static Company map(ResultSet rs) {
		Company comp = new Company();
		try {
			comp.setId(rs.getLong("id"));
			comp.setName(rs.getString("name"));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return (comp);
	}

}
