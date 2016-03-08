package com.excilys.computerdatabase.persistence.mapping;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.dao.CompanyDAO;
import com.excilys.computerdatabase.persistence.dao.DAO;

/**
 * Class to map a result set with either a Set<Computer> or a Computer.
 * @author lcoatanlem
 *
 */
public class ComputerMapping implements Mapping<Computer>{

	@Override
	/**
	 * Method to map all a result set to a Set<Computer>
	 * @param rs
	 * @return Set<Computer>
	 */
	public Set<Computer> mapAll(ResultSet rs) {
		Set<Computer> liste = new HashSet<Computer>();
		try {
			while (rs.next()){
				Long id = rs.getLong("id");
				String name = rs.getString("name");
				Date introduced = rs.getDate("introduced");
				Date discontinued = rs.getDate("discontinued");
				// We create a DAO to find the company in the DB
				DAO<Company> companyDao = new CompanyDAO();
				// We need to create a new company from the given company_id
				Company manufacturer = ((CompanyDAO) companyDao).find(rs.getInt(4));
				Computer cpu = new Computer(name);
				cpu.setId(id);
				cpu.setIntroduced(introduced);
				cpu.setDiscontinued(discontinued);
				cpu.setManufacturer(manufacturer);
				liste.add(cpu);
			}
		} catch (SQLException e) {
			// Database access error / closed ResultSet
			e.printStackTrace();
		}
		return liste;
	}
	
	/**
	 * Method to map a result set with a Computer
	 * @param rs
	 * @return Computer
	 */
	public Computer mapComputer(ResultSet rs) {
		Computer cpu = null;
		try {
			if (rs.next()){
				Long id = rs.getLong("id");
				String name = rs.getString("name");
				Date introduced = rs.getDate("introduced");
				Date discontinued = rs.getDate("discontinued");
				// We create a DAO to find the company in the DB
				DAO<Company> companyDao = new CompanyDAO();
				// We need to create a new company from the given company_id
				Company manufacturer = ((CompanyDAO) companyDao).find(rs.getInt(4));
				
				cpu = new Computer(name);
				cpu.setId(id);
				cpu.setIntroduced(introduced);
				cpu.setDiscontinued(discontinued);
				cpu.setManufacturer(manufacturer);
			}
		} catch (SQLException e) {
			// Database access error / closed ResultSet
			e.printStackTrace();
		}
		return cpu;
	}

}
