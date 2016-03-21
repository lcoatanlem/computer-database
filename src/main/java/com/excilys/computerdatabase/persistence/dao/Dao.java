package com.excilys.computerdatabase.persistence.dao;

import java.util.List;

import com.excilys.computerdatabase.exception.NoSuchCompanyException;
import com.excilys.computerdatabase.exception.NoSuchComputerException;

/**
 * This interface permits to abstract the functions findAll and CRUD for every DAO.
 * When not implemented (default), it raises UnavailableOperationException().
 * @author lcoatanlem
 * @param <T> Computer || Company
 */
public interface Dao<T> {
  /**
   * Method to return the elements from begin to (begin+range) into a List.
   * @return List of Companies || List of Computers
   */
  public List<T> findAll(int begin, int range);
  
  /**
   * Method to find a specific T in the DB from an id. Will return a mapped T.
   * @param id of the Object
   * @return Company || Computer
   * @throws NoSuchCompanyException when we try to find with an invalid ID
   * @throws NoSuchComputerException when we try to find with an invalid ID
   */
  public T find(Long id) throws NoSuchCompanyException, NoSuchComputerException;

  public int sizeTable();

  default void create(T t) throws UnsupportedOperationException, NoSuchCompanyException {
    throw new UnsupportedOperationException("Create not implemented");
  }
  
  default void update(T t) throws UnsupportedOperationException, NoSuchComputerException, NoSuchCompanyException {
    throw new UnsupportedOperationException("Update not implemented");
  }
  
  default void delete(Long id) throws UnsupportedOperationException, NoSuchComputerException {
    throw new UnsupportedOperationException("Delete not implemented");
  }
}
