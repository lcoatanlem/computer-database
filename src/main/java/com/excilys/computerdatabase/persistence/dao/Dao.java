package com.excilys.computerdatabase.persistence.dao;

import com.excilys.computerdatabase.exception.DaoIllegalMethodException;

import java.util.List;

/**
 * This interface permits to abstract the functions findAll and CRUD for every DAO.
 * When not implemented (default), it raises DaoIllegalMethodException().
 * @author lcoatanlem
 * @param <T> Computer || Company
 */
public interface Dao<T> {
  /**
   * Method to return the elements from offset to (offset+limit) into a List.
   * @return List of Companies || List of Computers
   */
  public List<T> findAll(int offset, int limit);
  
  /**
   * Method to find a specific T in the DB from an id. Will return a mapped T.
   * @param id of the Object
   * @return T
   */
  public T find(Long id);

  /**
   * Method to count the number of entries in the database.
   * @return the count
   */
  public int countEntries();

  default void create(T comp) {
    throw new DaoIllegalMethodException();
  }
  
  default void update(T comp) {
    throw new DaoIllegalMethodException();
  }
  
  default void delete(Long id) {
    throw new DaoIllegalMethodException();
  }
}
