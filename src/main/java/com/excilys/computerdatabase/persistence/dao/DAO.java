package main.java.com.excilys.computerdatabase.persistence.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import main.java.com.excilys.computerdatabase.exception.NotSuchCompanyException;
import main.java.com.excilys.computerdatabase.exception.NotSuchComputerException;
import main.java.com.excilys.computerdatabase.persistence.ConnectionJDBC;

/**
 * This interface permits to abstract the functions findAll and CRUD for every DAO.
 * When not implemented (default), it raises UnavailableOperationException().
 * @author lcoatanlem
 * @param <T> Computer || Company
 */
public interface DAO<T> {
	public Connection conn = ConnectionJDBC.getInstance();

	/**
	 * Method to return the elements from begin to (begin+range) into a List.
	 * @return List<Company> || List<Computer>
	 */
	public List<T> findAll(int begin, int range);
	/**
	 * Method to find a specific T in the DB from an id. Will return a mapped T.
	 * @param id
	 * @return Company || Computer
	 * @throws NotSuchCompanyException when we try to find with an invalid ID
	 * @throws NotSuchComputerException when we try to find with an invalid ID
	 */
	public T find(Long id) throws NotSuchCompanyException, NotSuchComputerException;

	default void create(T t) throws UnsupportedOperationException, NotSuchCompanyException {
		throw new UnsupportedOperationException("Create not implemented");
	}
	default void update(T t) throws UnsupportedOperationException, NotSuchComputerException, NotSuchCompanyException, SQLException {
		throw new UnsupportedOperationException("Update not implemented");
	}
	default void delete(Long id) throws UnsupportedOperationException, NotSuchComputerException, SQLException {
		throw new UnsupportedOperationException("Delete not implemented");
	}
}
