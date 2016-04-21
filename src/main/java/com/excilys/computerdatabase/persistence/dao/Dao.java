package com.excilys.computerdatabase.persistence.dao;

import com.excilys.computerdatabase.exception.IllegalMethodException;
import com.excilys.computerdatabase.mapping.query.Query;

import java.util.List;

/**
 * This interface permits to abstract the functions findAll and CRUD for every
 * DAO. When not implemented (default), it raises IllegalMethodException().
 * 
 * @author lcoatanlem
 * @param <T>
 *          Computer || Company
 */
public interface Dao<T> {
  /**
   * Method to return the elements from a Query into a List.
   * 
   * @return List of Companies || List of Computers
   */
  default List<T> findAll(Query query) {
    throw new IllegalMethodException();
  }

  /**
   * Method to count the number of entries in the database.
   */
  default int count(Query query) {
    throw new IllegalMethodException();
  }

  default T read(Long id) {
    throw new IllegalMethodException();
  }

  default void create(T obj) {
    throw new IllegalMethodException();
  }

  default void update(T obj) {
    throw new IllegalMethodException();
  }

  default void delete(Long id) {
    throw new IllegalMethodException();
  }
}