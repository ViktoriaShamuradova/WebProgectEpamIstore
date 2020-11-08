package by.epamtc.shamuradova.ishop.service.exception;

/**
 * Исключение, которое выбраывается в случае ошибок со стороны пользователя
 * приложения
 * 
 *
 * @author Шамурадова Виктория
 */

public abstract class AbstractApplicationException extends IllegalArgumentException {


	private static final long serialVersionUID = -4772480153460108399L;
	
	private final int code;

	public AbstractApplicationException(String message, int code) {
		super(message);
		this.code = code;

	}
	
	public AbstractApplicationException(int code) {
		super();
		this.code = code;

	}
	public AbstractApplicationException(Throwable cause, int code) {
		super(cause);
		this.code = code;

	}
	
	public AbstractApplicationException(String message, Throwable cause, int code) {
		super(message, cause);
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
