package com.excilys.computerdatabase.exception;

/**
 * Exception throwed when someone attempts to use a non existing method on the implementation of a DAO.
 * @author lcoatanlem
 *
 */
public class UnavailableException extends Exception {

	private static final long serialVersionUID = 1L;

	public UnavailableException() {
	}

	public UnavailableException(String message) {
		super(message);
	}

	public UnavailableException(Throwable cause) {
		super(cause);
	}

	public UnavailableException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnavailableException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
