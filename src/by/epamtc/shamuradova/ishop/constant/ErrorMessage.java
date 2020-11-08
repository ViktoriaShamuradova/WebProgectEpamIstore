package by.epamtc.shamuradova.ishop.constant;

public final class ErrorMessage {

	public static final String USER_LOGIN_IS_ALREADY_EXISTS = "user login is alredy exists";
	public static final String LOGIN_FORMAT = "Invalid login format";
	public static final String PASSWORD_FORMAT = "Invalid password format";
	public static final String INCORRECT_PASSWORD_OR_LOGIN = "Incorrect password or login";
	
	public static final String UNABLE_TO_CLOSE_STATEMENT = "unable to close statement";
	public static final String UNABLE_TO_CLOSE_RESULTSET = "unable to close ResultSet";
	public static final String DATABASE_ERROR = "some problems in database";
	public static final String CONNECTION_ERROR = "Error connecting to the data source ";
	public static final String DRIVER_LOAD_ERROR = "Can't find driver class";
	public static final String UNABLE_TO_FREE_CONNECTION = "unable to free connection";
	public static final String UNABLE_TO_CLOSE_CONNECTION = "unable to close connection";
	
	public static final String CANT_BE_EMPTY = "can`t be null or empty";
	
	
	public static final String CANT_BE_BELOW_MIN_PRICE = "price of model can't be below min price ";
	public static final String LESS_THAN_ZERO = "less than zero";
	public static final String NOT_VALID_DATA = "not valid data";
	
	public static final String NOT_RIGHTS = "you do not have rights to this resource";
	public static final String NOT_FOUND = "resource not found";

	private ErrorMessage() {

	}

}
