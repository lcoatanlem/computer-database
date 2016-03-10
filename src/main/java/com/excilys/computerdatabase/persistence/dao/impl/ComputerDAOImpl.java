package main.java.com.excilys.computerdatabase.persistence.dao.impl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import main.java.com.excilys.computerdatabase.exception.NotSuchCompanyException;
import main.java.com.excilys.computerdatabase.exception.NotSuchComputerException;
import main.java.com.excilys.computerdatabase.model.Computer;
import main.java.com.excilys.computerdatabase.persistence.dao.DAO;
import main.java.com.excilys.computerdatabase.persistence.mapping.ComputerMapping;

/**
 * Class DAO for Computers, every method from interface DAO is implemented.
 * @author lcoatanlem
 */
public class ComputerDAOImpl implements DAO<Computer>{

	@Override
	public List<Computer> findAll(int begin, int range) {
		List<Computer> liste = new ArrayList<Computer>();
		try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM computer LIMIT ?, ?")){
			stmt.setInt(1, begin);
			stmt.setInt(2, range);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				liste.add((ComputerMapping.map(rs)));
			}
			rs.close();
		} catch (SQLException | IllegalArgumentException | NotSuchCompanyException e) {
			// Database acces error / closed connection / closed statement
			// returning something else than a ResultSet / timeout have been reached
			throw new RuntimeException(e);
		}
		return liste;
	}

	@Override
	public Computer find(Long id) throws NotSuchComputerException {
		Computer comp = null;
		try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM computer WHERE id = ?")){
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()){
				comp = (ComputerMapping.map(rs));
			} else {
				throw new NotSuchComputerException("No computer for this ID...");
			}
			rs.close();
		} catch (SQLException | IllegalArgumentException | NotSuchCompanyException e) {
			// Database acces error / closed connection / closed statement
			// returning something else than a ResultSet / timeout have been reached
			throw new RuntimeException(e);
		}
		return comp;	
	}

	@Override
	/**
	 * Creates a new line in the DB for the Computer t, checking company id given.
	 */
	public void create(Computer t) throws NotSuchCompanyException {
		try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO computer(name,introduced,discontinued,company_id) VALUES ('?',?,?,?)")){
			if (t.getManufacturer() != null){
				if (t.getManufacturer().getId() != null){
					try{
						CompanyDAOImpl companyDAO = new CompanyDAOImpl();
						companyDAO.find(t.getManufacturer().getId());
					} catch (NotSuchCompanyException e) {

					}
				}
			}
			stmt.setString(1, t.getName());
			stmt.setDate(2, (t.getIntroduced()==null ? null : Date.valueOf(t.getIntroduced())));
			stmt.setDate(3, (t.getDiscontinued()==null ? null : Date.valueOf(t.getDiscontinued())));
			stmt.setLong(4, (t.getManufacturer()==null ? null : t.getManufacturer().getId()));
			stmt.executeUpdate();
		} catch (SQLException e) {
			// Database acces error / closed connection / closed statement
			// returning something else than a ResultSet / timeout have been reached
			e.printStackTrace();
		}
	}

	@Override
	/**
	 * Update a computer in the DB. If the id of the given Computer doesn't exists, raises
	 * a NotSuchComputerException.
	 */
	public void update(Computer t) throws NotSuchComputerException, NotSuchCompanyException, SQLException{
		try (PreparedStatement stmt = conn.prepareStatement("UPDATE computer SET name = '?', introduced = ?, discontinued = ? company_id = ? WHERE id = ?")){
			try{
				find(t.getId());
			} catch (NotSuchComputerException e){
				throw new NotSuchComputerException(e);
			} 
			if (t.getManufacturer() != null){
				if (t.getManufacturer().getId() != null){
					try{
						CompanyDAOImpl companyDAO = new CompanyDAOImpl();
						companyDAO.find(t.getManufacturer().getId());
					} catch (NotSuchCompanyException e) {

					}
				}
			}
			stmt.setString(1, t.getName());
			stmt.setDate(2, (t.getIntroduced()==null ? null : Date.valueOf(t.getIntroduced())));
			stmt.setDate(3, (t.getDiscontinued()==null ? null : Date.valueOf(t.getDiscontinued())));
			stmt.setLong(4, (t.getManufacturer()==null ? null : t.getManufacturer().getId()));
			stmt.setLong(5, t.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			// Database acces error / closed connection / closed statement
			// returning something else than a ResultSet / timeout have been reached
			throw new SQLException(e);
		}
	}

	@Override
	/**
	 * Delete a computer in the DB. If the id of the given Computer doesn't exists, raises
	 * a NotSuchComputerException.
	 */
	public void delete(Long id) throws NotSuchComputerException, SQLException {
		try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM computer WHERE id = ?")){
			try{
				find(id);
			} catch (NotSuchComputerException e){
				throw new NotSuchComputerException(e);
			}
			stmt.setLong(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// Database acces error / closed connection / closed statement
			// returning something else than a ResultSet / timeout have been reached
			throw new SQLException(e);
		}
	}



}
