package com.excilys.computerdatabase.exception;

/**
 * Runtime Exception when someone tries to use a wrong date format.
 * 
 * @author lcoatanlem
 *
 */
public class DateFormatException extends RuntimeException {

  private static final long serialVersionUID = -1275289109336507002L;
  private static final String MESSAGE = "This date format is not used in the program.";

  public DateFormatException() {
    super(MESSAGE);
  }

  public DateFormatException(String message) {
    super(message);
  }

  public DateFormatException(Exception exn) {
    super(MESSAGE, exn);
  }

  public DateFormatException(String message, Exception exn) {
    super(message, exn);
  }

}