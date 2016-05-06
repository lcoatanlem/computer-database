package com.excilys.computerdatabase.service;

import java.util.List;

import com.excilys.computerdatabase.utils.Query;

public interface IService<T> {

  /**
   * Get all the objects satisfying the query constraints.
   * @param query the object containing the query constraints
   * @return the list containing the result
   */
  List<T> list(Query query);

  /**
   * Count all the objects satisfying the query constraints.
   * @param query the object containing the query constraints
   * @return the number of elements satisfying the query constraints
   */
  int count(Query query);

  /**
   * Delete the row referenced by id.
   * @param id the row reference
   */
  void delete(Long id);
}