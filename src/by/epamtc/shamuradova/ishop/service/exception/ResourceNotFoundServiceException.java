package by.epamtc.shamuradova.ishop.service.exception;

import javax.servlet.http.HttpServletResponse;

/**
 * Класс наследуется от AbstractApplicationException
 * выбрасывается данное исключение, когда предены неправильные параметры для поиска объекта
 * и устанавливается ошибка 404 "resource not found"
 * 
 * @author Шамурадова Виктория
 * 
 */

public class ResourceNotFoundServiceException extends AbstractApplicationException {

	private static final long serialVersionUID = -6096208723434451987L;

	public ResourceNotFoundServiceException(String message, Throwable e) {
		super(message, e, HttpServletResponse.SC_NOT_FOUND);
	}

	public ResourceNotFoundServiceException(String message) {
		super(message, HttpServletResponse.SC_NOT_FOUND);
	}

	public ResourceNotFoundServiceException(Throwable e) {
		super(e, HttpServletResponse.SC_NOT_FOUND);
	}

}
