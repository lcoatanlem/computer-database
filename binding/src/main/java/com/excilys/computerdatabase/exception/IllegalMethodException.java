package com.excilys.computerdatabase.exception;

/**
 * Runtime Exception when someone tries to use a non-implemented method.
 * 
 * @author lcoatanlem
 *
 */
public class IllegalMethodException extends RuntimeException {

  private static final long serialVersionUID = -5337961293264433685L;
  private static final String MESSAGE = "This method haven't been overriden in this DAO implementation.";

  public IllegalMethodException() {
    super(MESSAGE);
  }

  public IllegalMethodException(String message) {
    super(message);
  }

  public IllegalMethodException(Exception exn) {
    super(MESSAGE, exn);
  }

  public IllegalMethodException(String message, Exception exn) {
    super(message, exn);
  }

}
