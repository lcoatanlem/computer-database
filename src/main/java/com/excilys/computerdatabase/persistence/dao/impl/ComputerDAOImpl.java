package com.excilys.computerdatabase.persistence.dao.impl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import com.excilys.computerdatabase.exception.NotSuchCompanyException;
import com.excilys.computerdatabase.exception.NotSuchComputerException;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.ConnectionJDBC;
import com.excilys.computerdatabase.persistence.dao.DAO;
import com.excilys.computerdatabase.persistence.mapping.ComputerMapping;

/**
 * Class DAO for Computers, every method from interface DAO is implemented.
 * @author lcoatanlem
 */
public class ComputerDAOImpl implements DAO<Computer>{
	private ConnectionJDBC connJDBC;
	
	public ComputerDAOImpl(){
		connJDBC = ConnectionJDBC.getInstance();
	}

	@Override
	public int sizeTable(){
		int size = 0;
		try(Statement stmt = connJDBC.getConnection().createStatement()){
			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM computer");
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
	public List<Computer> findAll(int begin, int range) {
		List<Computer> liste = new ArrayList<Computer>();
		try (PreparedStatement stmt = connJDBC.getConnection().prepareStatement("SELECT * FROM computer LIMIT ?, ?")){
			stmt.setInt(1, begin);
			stmt.setInt(2, range);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				liste.add((ComputerMapping.map(rs)));
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
	public Computer find(Long id) throws NotSuchComputerException {
		Computer comp = null;
		try (PreparedStatement stmt = connJDBC.getConnection().prepareStatement("SELECT * FROM computer WHERE id = ?")){
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()){
				comp = (ComputerMapping.map(rs));
			} else {
				throw new NotSuchComputerException("No computer for this ID...");
			}
			rs.close();
		} catch (SQLException e) {
			// Database access error / closed connection / closed statement
			// returning something else than a ResultSet / timeout have been reached
			throw new RuntimeException(e);
		}
		return comp;	
	}

	@Override
	/**
	 * Creates a new line in the DB for the Computer t, checking company id given, if it doesn't exists, set Manufacturer to null and raises an exception.
	 */
	public void create(Computer t) throws NotSuchCompanyException {
		try (PreparedStatement stmt = connJDBC.getConnection().prepareStatement("INSERT INTO computer(name,introduced,discontinued,company_id) VALUES (?,?,?,?)")){
			if (t.getManufacturer() != null){
				if (t.getManufacturer().getId() != null){
					CompanyDAOImpl companyDAO = new CompanyDAOImpl();
					try {
						companyDAO.find(t.getManufacturer().getId());
					} catch (NotSuchCompanyException e) {
						t.setManufacturer(null);
						throw new NotSuchCompanyException("Company doesn't exists, have been set to null.");
					}
				}
			}
			stmt.setString(1, t.getName());
			stmt.setDate(2, (t.getIntroduced()==null ? null : Date.valueOf(t.getIntroduced())));
			stmt.setDate(3, (t.getDiscontinued()==null ? null : Date.valueOf(t.getDiscontinued())));
			Long id_cpn = (t.getManufacturer()==null ? null : t.getManufacturer().getId());
			if (id_cpn == null){
				stmt.setNull(4, Types.NULL);
			} else {
				stmt.setLong(4, id_cpn);
			}
			stmt.executeUpdate();
		} catch (SQLException e) {
			// Database access error / closed connection / closed statement
			// returning something else than a ResultSet / timeout have been reached
			throw new RuntimeException(e);
		}
	}

	@Override
	/**
	 * Update a computer in the DB. If the id of the given Computer doesn't exists, raises a NoSuchComputerException.
	 * Check the company id given, and if it doesn't exists, set Manufacturer to null and raises an exception.
	 */
	public void update(Computer t) throws NotSuchComputerException, NotSuchCompanyException {
		try (PreparedStatement stmt = connJDBC.getConnection().prepareStatement("UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?")){
			find(t.getId());
			if (t.getManufacturer() != null){
				if (t.getManufacturer().getId() != null){
					CompanyDAOImpl companyDAO = new CompanyDAOImpl();
					try {
						companyDAO.find(t.getManufacturer().getId());
					} catch (NotSuchCompanyException e) {
						t.setManufacturer(null);
						throw new NotSuchCompanyException("Company doesn't exists, have been set to null.");
					}
				}
			}
			stmt.setString(1, t.getName());
			stmt.setDate(2, (t.getIntroduced()==null ? null : Date.valueOf(t.getIntroduced())));
			stmt.setDate(3, (t.getDiscontinued()==null ? null : Date.valueOf(t.getDiscontinued())));
			Long id_cpn = (t.getManufacturer()==null ? null : t.getManufacturer().getId());
			if (id_cpn == null){
				stmt.setNull(4, Types.NULL);
			} else {
				stmt.setLong(4, id_cpn);
			}
			stmt.setLong(5, t.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			// Database access error / closed connection / closed statement
			// returning something else than a ResultSet / timeout have been reached
			throw new RuntimeException(e);
		}
	}

	@Override
	/**
	 * Delete a computer in the DB. If the id of the given Computer doesn't exists, raises a NotSuchComputerException.
	 */
	public void delete(Long id) throws NotSuchComputerException {
		try (PreparedStatement stmt = connJDBC.getConnection().prepareStatement("DELETE FROM computer WHERE id = ?")){
			find(id);
			stmt.setLong(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// Database access error / closed connection / closed statement
			// returning something else than a ResultSet / timeout have been reached
			throw new RuntimeException(e);
		}
	}



}
