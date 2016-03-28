package com.excilys.computerdatabase.exception;

/**
 * Runtime Exception when there is a problem with the database.
 * 
 * @author lcoatanlem
 *
 */
public class DaoException extends RuntimeException {

  private static final long serialVersionUID = -1275289109336507002L;
  private static final String MESSAGE = "Error accessing to the database.";

  public DaoException() {
    super(MESSAGE);
  }

  public DaoException(String message) {
    super(message);
  }

  public DaoException(Exception exn) {
    super(MESSAGE, exn);
  }

  public DaoException(String message, Exception exn) {
    super(message, exn);
  }

}