package by.epamtc.shamuradova.ishop.service.exception;

import javax.servlet.http.HttpServletResponse;

/**
 * Класс наследуется от AbstractApplicationException
 * 
 * 403 - запрещен доступ к ресурсу
 * 
 * @author Шамурадова Виктория
 * 
 */

public class AccessDeniedServiceException extends AbstractApplicationException {

	private static final long serialVersionUID = 6366557251443220625L;

	public AccessDeniedServiceException(String message) {
		super(message, HttpServletResponse.SC_FORBIDDEN);
	}

	public AccessDeniedServiceException() {
		super(HttpServletResponse.SC_FORBIDDEN);
	}
}
