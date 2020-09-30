package by.epamtc.shamuradova.ishop.constant;

/**
 * Подготовленные запросы в БД
 * 
 * 
 * @author Шамурадова Виктория, 2020
 *
 */
public class SQLQuery {

	public static final String ADD_NEW_USER = "INSERT INTO users(name, surname, login, password, email, id_user_status, id_user_role) VALUES (?,?,?,?,?,?,?)";
	public static final String ADD_NEW_MODEL = "INSERT INTO models(name, description, price, id_category, id_producer, image_link, count) VALUES (?,?,?,?,?,?,?)";
	public static final String SIGN_IN = "SELECT * FROM users WHERE login = ?";
	public static final String SIGN_UP = "INSERT INTO users(name, surname, login, password, email, id_user_status, id_user_role) VALUES (?,?,?,?,?,?,?)";
	public static final String CHECK_LOGIN = "CALL check_login(?)";

}
