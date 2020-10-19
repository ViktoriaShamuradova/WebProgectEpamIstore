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
	public static final String SIGN_IN = "select u.*, r.name as role, s.name as stat from users u, roles r, statuses s \r\n"
			+ "where r.id=u.id_user_role and s.id=u.id_user_status and login= ?";
	public static final String SIGN_UP = "INSERT INTO users(name, surname, login, password, email, id_user_status, id_user_role) VALUES (?,?,?,?,?,?,?)";
	public static final String CHECK_LOGIN = "CALL check_login(?)";
	public static final String LIST_MODELS = "select m.*, c.name as category, p.name as producer from models m, producers p, categories c where c.id=m.id_category and p.id=m.id_producer limit ? offset ?";
	public static final String LIST_MODELS_BY_CATEGORY = "select m.*, c.name as category, pr.name as producer from models m, categories c, producers pr \r\n"
			+ "where c.url=? and pr.id=m.id_producer and c.id=m.id_category \r\n"
			+ "order by m.id limit ? offset ?";
	// public static final String LIST_CATEGORY = "select* from categories order by
	// id";
	public static final String LIST_CATEGORY = "select c.name, c.url, SUM(m.count) AS count, c.id from categories c, models m WHERE c.id=m.id_category group by m.id_category";
	// public static final String LIST_PRODUCER = "select* from producers c order by
	// id";
	public static final String LIST_PRODUCER = "select p.name, SUM(m.count) AS count, p.id from producers p, models m WHERE p.id=m.id_producer group by m.id_producer";
	public static final String CART_BY_ID = "select * from carts where id_user= ?";
	public static final String USER_BY_LOGIN = "select u.*, r.name as role, s.name as stat from users u, roles r, statuses s where u.login=? and r.id=u.id_user_role and s.id=u.id_user_status";
	public static final String MODEL_BY_ID = "select m.*, c.name as category, p.name as producer from models m, producers p, categories c where m.id= ? and c.id=m.id_category and p.id=m.id_producer";
	public static final String ADD_CART = "INSERT INTO carts(id_user, created) VALUES (?,?)";
	public static final String ADD_CART_ITEM = "INSERT INTO cart_item(id_cart, id_model, count) VALUES (?,?,?)";
	public static final String TOTAL_COUNT_OF_MODELS_IN_CART = "CALL total_count_in_cart(?)";
	public static final String TOTAL_SUM_OF_MODELS_IN_CART = "CALL count_tottal_sum_for_cart(?)";
	public static final String LIST_SHOP_CART_ITEMS ="SELECT m.name, m.description, m.id, m.price, m.image_link, categories.name as category, p.name as producer, c.count \r\n" + 
	"FROM models m, cart_item c, producers p, categories \r\n" + 
	"where c.id_model=m.id and c.id_cart= ? and categories.id=m.id_category and p.id=m.id_producer";
}
