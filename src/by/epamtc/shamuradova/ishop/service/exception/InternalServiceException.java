package by.epamtc.shamuradova.ishop.service.exception;

import javax.servlet.http.HttpServletResponse;

/**
 * Класс наследуется от AbstractApplicationException
 * 
 * 500 -любая другая ошибка со стороны пользователя
 * 
 * @author Шамурадова Виктория
 * 
 */
public class InternalServiceException extends AbstractApplicationException {

	private static final long serialVersionUID = 243332714199858856L;

	public InternalServiceException(String message) {
		super(message, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	}

	public InternalServiceException(String message, Throwable cause) {
		super(message, cause, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	}

	public InternalServiceException(Throwable cause) {
		super(cause, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

	}

	public InternalServiceException() {
		super(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	}

}
