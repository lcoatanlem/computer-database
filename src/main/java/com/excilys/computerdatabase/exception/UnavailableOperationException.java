package main.java.com.excilys.computerdatabase.exception;

/**
 * Exception throwed when someone attempts to use a non existing method on the implementation of a DAO.
 * @author lcoatanlem
 *
 */
public class UnavailableOperationException extends Exception {

	private static final long serialVersionUID = 1L;

	public UnavailableOperationException() {
	}

	public UnavailableOperationException(String message) {
		super(message);
	}

	public UnavailableOperationException(Throwable cause) {
		super(cause);
	}

	public UnavailableOperationException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnavailableOperationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
