package by.epamtc.shamuradova.ishop.dao.exception;

public class ConnectionPoolException extends Exception{

	private static final long serialVersionUID = 682715988025143232L;

	public ConnectionPoolException(String message, Exception e) {
		super(message, e);
	}

}
