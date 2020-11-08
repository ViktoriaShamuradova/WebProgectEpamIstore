package by.epamtc.shamuradova.ishop.service.exception;

import javax.servlet.http.HttpServletResponse;

/**
 * Исключение, которое перехватывает ошибки из слоя дао
 * 
 * 
 * @author Шамурадова Виктория
 */

public class ServiceException extends Exception {

	private static final long serialVersionUID = -1342724746395391734L;

	private final int code;

	public ServiceException(String s) {
		super(s);
		this.code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
	}

	public ServiceException(Throwable cause) {
		super(cause);
		this.code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
		this.code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
	}

	public int getCode() {
		return code;
	}

}
