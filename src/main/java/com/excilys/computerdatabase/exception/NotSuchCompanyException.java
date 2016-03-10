package main.java.com.excilys.computerdatabase.exception;

/**
 * Exception throwed when someone attempts to use a non existing company.
 * @author lcoatanlem
 *
 */
public class NotSuchCompanyException extends Exception {

	private static final long serialVersionUID = 1L;

	public NotSuchCompanyException() {
	}

	public NotSuchCompanyException(String message) {
		super(message);
	}

	public NotSuchCompanyException(Throwable cause) {
		super(cause);
	}

	public NotSuchCompanyException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotSuchCompanyException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
