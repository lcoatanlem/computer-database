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

public class ComputerMapping implements Mapping<Computer>{

	@Override
	public Set<Computer> mapAll(ResultSet rs) {
		Set<Computer> liste = new HashSet<Computer>();
		try {
			while (rs.next()){
				String name = rs.getString(1);
				Date introduced = rs.getDate(2);
				Date discontinued = rs.getDate(3);
				// We create a DAO to find the company in the DB
				DAO<Company> companyDao = new CompanyDAO();
				// We need to create a new company from the given company_id
				Company manufacturer = ((CompanyDAO) companyDao).find(rs.getInt(4));
				liste.add(new Computer(name,introduced,discontinued,manufacturer));
			}
		} catch (SQLException e) {
			// Database access error / closed ResultSet
			e.printStackTrace();
		}
		return liste;
	}

}
