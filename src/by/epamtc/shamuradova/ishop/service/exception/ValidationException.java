package by.epamtc.shamuradova.ishop.service.exception;

import javax.servlet.http.HttpServletResponse;

/**
 * Класс наследуется от AbstractApplicationException Выбрасывается данное
 * исключение из-за неверного синтаксиса 400
 * 
 * @author Шамурадова Виктория
 * 
 */

public class ValidationException extends AbstractApplicationException {

	private static final long serialVersionUID = -1535734461602330291L;

	public ValidationException(String message) {
		super(message, HttpServletResponse.SC_BAD_REQUEST);
	}

	public ValidationException(String message, Throwable e) {
		super(message, e, HttpServletResponse.SC_BAD_REQUEST);
	}

	public ValidationException(Throwable e) {
		super(e, HttpServletResponse.SC_BAD_REQUEST);
	}

}
