package com.excilys.computerdatabase.service;

import com.excilys.computerdatabase.persistence.mapping.query.Query;

import java.util.List;

public interface Service<T> {

  /**
   * Get all the objects satisfying the query constraints.
   * 
   * @param query
   *          the object containing the query constraints
   * @return the list containing the result
   */
  List<T> list(Query query);

  /**
   * Count all the objects satisfying the query constraints.
   * 
   * @param query
   *          the object containing the query constraints
   * @return the number of elements satisfying the query constraints
   */
  int count(Query query);

  /**
   * Delete the row referenced by id.
   * 
   * @param id
   *          the row reference
   */
  void delete(Long id);
}