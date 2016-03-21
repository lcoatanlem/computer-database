package com.excilys.computerdatabase.exception;

/**
 * Runtime Exception when someone tries to use a non-implemented method for a DAO.
 * @author lcoatanlem
 *
 */
public class DaoIllegalMethodException extends RuntimeException {

  private static final long serialVersionUID = 3771100996357267152L;
  private static final String MESSAGE = 
      "This method haven't been overriden in this DAO implementation.";

  public DaoIllegalMethodException() {
    super(MESSAGE);
  }

  public DaoIllegalMethodException(String message) {
    super(message);
  }

  public DaoIllegalMethodException(Exception exn) {
    super(MESSAGE, exn);
  }
  
  public DaoIllegalMethodException(String message, Exception exn) {
    super(message, exn);
  }

}
