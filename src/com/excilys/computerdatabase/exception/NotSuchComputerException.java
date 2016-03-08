package com.excilys.computerdatabase.exception;

/**
 * Exception throwed when someone attempts to use a non existing computer.
 * @author lcoatanlem
 *
 */
public class NotSuchComputerException extends Exception {

	private static final long serialVersionUID = 1L;

	public NotSuchComputerException() {
	}

	public NotSuchComputerException(String message) {
		super(message);
	}

	public NotSuchComputerException(Throwable cause) {
		super(cause);
	}

	public NotSuchComputerException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotSuchComputerException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
