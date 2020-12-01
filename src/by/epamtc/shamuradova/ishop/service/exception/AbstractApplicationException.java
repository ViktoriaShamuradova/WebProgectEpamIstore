package by.epamtc.shamuradova.ishop.service.exception;

/**
 * Исключение, которое выбраcывается в случае ошибок со стороны пользователя
 * приложения
 * 
 * The exception that is thrown in case of errors by the user applications
 * 
 * @author Victoria Shamuradova 2020
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
