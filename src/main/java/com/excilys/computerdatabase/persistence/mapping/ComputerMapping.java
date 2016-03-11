package main.java.com.excilys.computerdatabase.persistence.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import main.java.com.excilys.computerdatabase.exception.NotSuchCompanyException;
import main.java.com.excilys.computerdatabase.model.Company;
import main.java.com.excilys.computerdatabase.model.Computer;
import main.java.com.excilys.computerdatabase.persistence.dao.DAO;
import main.java.com.excilys.computerdatabase.persistence.dao.impl.CompanyDAOImpl;

/**
 * Class to map a result set with a Computer.
 * @author lcoatanlem
 *
 */
public class ComputerMapping {

	/**
	 * Method to map a result set with a Computer
	 * @param rs
	 * @return Computer
	 */
	public static Computer map(ResultSet rs) {
		Computer cpu = null;
		try {
			Long id = rs.getLong("id");
			String name = rs.getString("name");
			LocalDate introduced = (rs.getDate("introduced") == null) ? null : rs.getDate("introduced").toLocalDate();
			LocalDate discontinued = (rs.getDate("discontinued") == null) ? null : rs.getDate("discontinued").toLocalDate();
			Long company_id = rs.getLong("company_id");
			Company manufacturer = new Company();
			if (company_id != null){
				// We create a DAO to find the company in the DB
				DAO<Company> companyDao = new CompanyDAOImpl();
				// We need to create a new company from the given company_id if it is not null
				try {
					manufacturer = ((CompanyDAOImpl) companyDao).find(company_id);
				} catch (NotSuchCompanyException e) {
					company_id = null;
				}
			}
			cpu = new Computer(name);
			cpu.setId(id);
			cpu.setIntroduced(introduced);
			cpu.setDiscontinued(discontinued);
			cpu.setManufacturer(manufacturer);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return cpu;
	}

}
