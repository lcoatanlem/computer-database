package com.excilys.computerdatabase.persistence.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdatabase.exception.NotSuchCompanyException;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.persistence.ConnectionJDBC;
import com.excilys.computerdatabase.persistence.dao.DAO;
import com.excilys.computerdatabase.persistence.mapping.CompanyMapping;


/**
 * Class DAO for Companies, methods findAll() and find(Long id) are defined, all others methods from
 * interface DAO (CRUD) will raise UnavailableException(message).
 * @author lcoatanlem
 */
public class CompanyDAOImpl implements DAO<Company> {
	private ConnectionJDBC connJDBC;

	public CompanyDAOImpl(){
		connJDBC = ConnectionJDBC.getInstance();
	}

	@Override
	public int sizeTable(){
		int size = 0;
		try(Statement stmt = connJDBC.getConnection().createStatement()){
			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM company");
			if (rs.next()){
				size = rs.getInt(1);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return size;
	}
	
	
	@Override
	public List<Company> findAll(int begin, int range) {
		List<Company> liste = new ArrayList<Company>();
		try (PreparedStatement stmt = connJDBC.getConnection().prepareStatement("SELECT * FROM company LIMIT ?, ?")){
			stmt.setInt(1, begin);
			stmt.setInt(2, range);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()){
				liste.add(CompanyMapping.map(rs));
			}
			rs.close();
		} catch (SQLException e) {
			// Database access error / closed connection / closed statement
			// returning something else than a ResultSet / timeout have been reached
			throw new RuntimeException(e);
		}
		return liste; 
	}

	@Override
	public Company find(Long id) throws NotSuchCompanyException{
		Company comp = null;
		if (id != null){
			try (PreparedStatement stmt = connJDBC.getConnection().prepareStatement("SELECT * FROM company WHERE id = ?")){
				stmt.setLong(1, id);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()){
					comp = (CompanyMapping.map(rs));
				} else {
					throw new NotSuchCompanyException();
				}
				rs.close();
			} catch (SQLException e) {
				// Database access error / closed connection / closed statement
				// returning something else than a ResultSet / timeout have been reached
				throw new RuntimeException(e);
			}
		}
		return comp;
	}
}
