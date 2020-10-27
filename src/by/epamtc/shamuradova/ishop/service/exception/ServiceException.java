package by.epamtc.shamuradova.ishop.service.exception;

public class ServiceException extends Exception {

	public ServiceException(String message, Throwable e) {
		super(message, e);
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable e) {
		super(e);
	}

}
