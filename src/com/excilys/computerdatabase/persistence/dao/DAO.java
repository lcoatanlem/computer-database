package com.excilys.computerdatabase.persistence.dao;

import java.sql.Connection;
import java.util.Set;

import com.excilys.computerdatabase.exception.UnavailableException;
import com.excilys.computerdatabase.persistence.ConnectionJDBC;

/**
 * This interface permits to abstract the functions findAll and CRUD for every DAO.
 * When not implemented (default), it raises UnavailableException().
 * @author lcoatanlem
 * @param <T> Computer || Company
 */
public interface DAO<T> {
	public Connection conn = ConnectionJDBC.getInstance();
	
	default Set<T> findAll() throws UnavailableException {
		throw new UnavailableException("findAll not implemented");
	}
	default void create(T t) throws UnavailableException {
		throw new UnavailableException("create not implemented");
	}
	default T read(T t) throws UnavailableException {
		throw new UnavailableException("read not implemented");
	}
	default void update(T t) throws UnavailableException {
		throw new UnavailableException("update not implemented");
	}
	default void delete(T t) throws UnavailableException {
		throw new UnavailableException("delete not implemented");
	}
}
