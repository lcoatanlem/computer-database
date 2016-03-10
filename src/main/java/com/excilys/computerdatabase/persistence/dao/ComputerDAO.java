package main.java.com.excilys.computerdatabase.persistence.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import main.java.com.excilys.computerdatabase.exception.NotSuchCompanyException;
import main.java.com.excilys.computerdatabase.exception.NotSuchComputerException;
import main.java.com.excilys.computerdatabase.model.Computer;
import main.java.com.excilys.computerdatabase.persistence.mapping.ComputerMapping;
import main.java.com.excilys.computerdatabase.persistence.mapping.Mapping;

/**
 * Class DAO for Computers, every method from interface DAO is implemented.
 * @author lcoatanlem
 */
public class ComputerDAO implements DAO<Computer>{

	@Override
	public List<Computer> findAll() {
		List<Computer> liste = new ArrayList<Computer>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM computer");
			Mapping<Computer> mapping = new ComputerMapping();
			while(rs.next()){
				liste.add(((ComputerMapping) mapping).map(rs));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// Database acces error / closed connection / closed statement
			// returning something else than a ResultSet / timeout have been reached
			e.printStackTrace();
		}
		return liste;
	}

	@Override
	public Computer find(Long id) throws NotSuchComputerException {
		Computer comp = null;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM computer WHERE id=" + id + "");
			Mapping<Computer> mapping = new ComputerMapping();
			if (rs.next()){
				comp = ((ComputerMapping) mapping).map(rs);
			} else {
				throw new NotSuchComputerException("No computer for this ID...");
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// Database acces error / closed connection / closed statement
			// returning something else than a ResultSet / timeout have been reached
			e.printStackTrace();
		}
		return comp;	
	}

	@Override
	/**
	 * Creates a new line in the DB for the Computer t, checking company id given.
	 */
	public void create(Computer t) throws NotSuchCompanyException {
		try {
			if (t.getManufacturer() != null){
				if (t.getManufacturer().getId() != null){
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT id FROM company WHERE id = "
							+ t.getManufacturer().getId() + ";");
					if (!rs.next()){
						throw new NotSuchCompanyException();
					}
					stmt.close();
					rs.close();
				}
			}
			Statement stmt2 = conn.createStatement();
			stmt2.executeUpdate("INSERT INTO computer (name,introduced,discontinued,company_id) "
					+ "VALUES ('" + t.getName() + "', " + (t.getIntroduced()==null ? "null" : ("'" + Date.valueOf(t.getIntroduced())) + "'") + ", "
					+ (t.getDiscontinued()==null ? "null" : ("'" + Date.valueOf(t.getDiscontinued())) + "'") + ", " 
					+ (t.getManufacturer()==null ? "null" : t.getManufacturer().getId()) + ")");
			stmt2.close();
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
	public void update(Computer t) throws NotSuchComputerException, NotSuchCompanyException{
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM computer WHERE id = " + t.getId() + "");
			if (!rs.next()){
				throw new NotSuchComputerException();
			} else {
				if (t.getManufacturer() != null){
					if (t.getManufacturer().getId() != null){
						Statement stmt2 = conn.createStatement();
						ResultSet rs2 = stmt2.executeQuery("SELECT id FROM company WHERE id = "
								+ t.getManufacturer().getId() + ";");
						if (!rs2.next()){
							throw new NotSuchCompanyException();
						}
						rs2.close();
						stmt2.close();
					}
				}
			}
			rs.close();
			stmt.close();
			Statement stmt3 = conn.createStatement();
			stmt3.executeUpdate("UPDATE computer "
					+ "SET name='" + t.getName() + "', "
					+ "introduced=" + (t.getIntroduced()==null ? "null" : ("'" + Date.valueOf(t.getIntroduced())) + "'") + ", "
					+ "discontinued=" + (t.getDiscontinued()==null ? "null" : ("'" + Date.valueOf(t.getDiscontinued())) + "'") + ", "
					+ "company_id=" + (t.getManufacturer()==null ? "null" : t.getManufacturer().getId())
					+ " WHERE id=" + t.getId() + ";");
			stmt3.close();
		} catch (SQLException e) {
			// Database acces error / closed connection / closed statement
			// returning something else than a ResultSet / timeout have been reached
			e.printStackTrace();
		}
	}

	@Override
	/**
	 * Delete a computer in the DB. If the id of the given Computer doesn't exists, raises
	 * a NotSuchComputerException.
	 */
	public void delete(Long id) throws NotSuchComputerException {
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM computer WHERE id = " + id + "");
			if (rs.next()){
				Statement stmt2 = conn.createStatement();
				stmt2.executeUpdate("DELETE FROM computer WHERE id = " + id + "");
				stmt2.close();
			} else {
				throw new NotSuchComputerException();
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// Database acces error / closed connection / closed statement
			// returning something else than a ResultSet / timeout have been reached
			e.printStackTrace();
		}
	}



}
