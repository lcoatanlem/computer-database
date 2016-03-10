package main.java.com.excilys.computerdatabase.persistence.dao;

import java.sql.Connection;
import java.util.List;
import main.java.com.excilys.computerdatabase.exception.NotSuchCompanyException;
import main.java.com.excilys.computerdatabase.exception.NotSuchComputerException;
import main.java.com.excilys.computerdatabase.exception.UnavailableOperationException;
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
	 * Method to return every element in the DB.
	 * @return List<Company> || List<Computer
	 */
	public List<T> findAll();
	/**
	 * Method to find a specific T in the DB from an id. Will return a mapped T.
	 * @param id
	 * @return Company || Computer
	 * @throws NotSuchCompanyException when we try to find with an invalid ID
	 * @throws NotSuchComputerException when we try to find with an invalid ID
	 */
	public T find(Long id) throws NotSuchCompanyException, NotSuchComputerException;
	
	default void create(T t) throws UnavailableOperationException, NotSuchCompanyException {
		throw new UnavailableOperationException("Create not implemented");
	}
	default void update(T t) throws UnavailableOperationException, NotSuchComputerException, NotSuchCompanyException {
		throw new UnavailableOperationException("Update not implemented");
	}
	default void delete(Long id) throws UnavailableOperationException, NotSuchComputerException {
		throw new UnavailableOperationException("Delete not implemented");
	}
}
