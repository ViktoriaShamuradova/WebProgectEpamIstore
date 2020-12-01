package by.epamtc.shamuradova.ishop.service.exception;

/**
 * Исключение, которое перехватывает ошибки из слоя дао
 * Exception that catches errors from the dao layer and sets error code 500
 * 
 * @author Victoria Shamuradova, 2020
 */

public class ServiceException extends Exception {

	private static final long serialVersionUID = -1342724746395391734L;

	public ServiceException(String s) {
		super(s);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

}
