package com.excilys.computerdatabase.exception;

/**
 * Runtime Exception when someone tries to use a non-implemented method for a
 * DAO.
 * 
 * @author lcoatanlem
 *
 */
public class DaoIllegalObjectException extends RuntimeException {

  private static final long serialVersionUID = 1333867142060442709L;
  private static final String MESSAGE = "The object you try to use is invalid.";

  public DaoIllegalObjectException() {
    super(MESSAGE);
  }

  public DaoIllegalObjectException(String message) {
    super(message);
  }

  public DaoIllegalObjectException(Exception exn) {
    super(MESSAGE, exn);
  }

  public DaoIllegalObjectException(String message, Exception exn) {
    super(message, exn);
  }

}
