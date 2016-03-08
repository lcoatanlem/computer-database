package com.excilys.computerdatabase.persistence.dao;

import java.sql.Connection;
import java.util.Set;

import com.excilys.computerdatabase.exception.NotSuchCompanyException;
import com.excilys.computerdatabase.exception.NotSuchComputerException;
import com.excilys.computerdatabase.exception.UnavailableOperationException;
import com.excilys.computerdatabase.persistence.ConnectionJDBC;

/**
 * This interface permits to abstract the functions findAll and CRUD for every DAO.
 * When not implemented (default), it raises UnavailableOperationException().
 * @author lcoatanlem
 * @param <T> Computer || Company
 */
public interface DAO<T> {
	public Connection conn = ConnectionJDBC.getInstance();
	
	public Set<T> findAll();
	
	default void create(T t) throws UnavailableOperationException, NotSuchCompanyException {
		throw new UnavailableOperationException("Create not implemented");
	}
	default T read(Long id) throws UnavailableOperationException, NotSuchComputerException {
		throw new UnavailableOperationException("Read not implemented");
	}
	default void update(T t) throws UnavailableOperationException, NotSuchComputerException {
		throw new UnavailableOperationException("Update not implemented");
	}
	default void delete(T t) throws UnavailableOperationException, NotSuchComputerException {
		throw new UnavailableOperationException("Delete not implemented");
	}
}
