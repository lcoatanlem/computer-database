package com.excilys.computerdatabase.persistence.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.excilys.computerdatabase.exception.NotSuchCompanyException;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.dao.CompanyDAO;
import com.excilys.computerdatabase.persistence.dao.DAO;

/**
 * Class to map a result set with a Computer.
 * @author lcoatanlem
 *
 */
public class ComputerMapping implements Mapping<Computer>{

	/**
	 * Method to map a result set with a Computer
	 * @param rs
	 * @return Computer
	 */
	public Computer map(ResultSet rs) {
		Computer cpu = null;
		try {
			Long id = rs.getLong("id");
			String name = rs.getString("name");
			LocalDate introduced;
			if (rs.getDate("introduced") == null){
				introduced = null;
			} else {
				introduced = rs.getDate("introduced").toLocalDate();
			}
			LocalDate discontinued;
			if (rs.getDate("discontinued") == null){
				discontinued = null;
			} else {
				discontinued = rs.getDate("discontinued").toLocalDate();
			}
			Long company_id = rs.getLong("company_id");
			Company manufacturer = new Company();
			if (company_id != 0L){
				// We create a DAO to find the company in the DB
				DAO<Company> companyDao = new CompanyDAO();
				// We need to create a new company from the given company_id if it is not null
				manufacturer = ((CompanyDAO) companyDao).find(company_id);
			}
			cpu = new Computer();
			cpu.setName(name);
			cpu.setId(id);
			cpu.setIntroduced(introduced);
			cpu.setDiscontinued(discontinued);
			cpu.setManufacturer(manufacturer);
		} catch (SQLException | NotSuchCompanyException e) {
			// Database access error / closed ResultSet / DB not consistant
			e.printStackTrace();
		}
		return cpu;
	}

}
