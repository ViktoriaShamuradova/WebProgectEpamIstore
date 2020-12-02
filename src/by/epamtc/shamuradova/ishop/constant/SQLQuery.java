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
	public static final String ADD_NEW_MODEL = "INSERT INTO models(name, description, price, id_category, id_producer, count) VALUES (?,?,?, (select c.id from categories c where c.name=?),\r\n"
			+ "(select p.id from producers p where p.name=?),?)";
	// "INSERT INTO models(name, description, price, id_category, id_producer,
	// image_link, count) VALUES (?,?,?,?,?,?,?)";
	public static final String SIGN_IN = "select u.*, r.name as role, s.name as stat from users u, roles r, statuses s \r\n"
			+ "where r.id=u.id_user_role and s.id=u.id_user_status and login= ? and password = ?";
	public static final String SIGN_UP = "INSERT INTO users(name, surname, login, password, email, id_user_status, id_user_role) VALUES (?,?,?,?,?,?,?)";
	public static final String CHECK_LOGIN = "CALL check_login(?)";
	public static final String LIST_MODELS = "select m.*, c.name as category, p.name as producer from models m, producers p, categories c where c.id=m.id_category and p.id=m.id_producer limit ? offset ?";
	public static final String LIST_MODELS_BY_CATEGORY = "select m.*, c.name as category, pr.name as producer from models m, categories c, producers pr \r\n"
			+ "where c.id=? and pr.id=m.id_producer and c.id=m.id_category order by m.id limit ? offset ?";

	public static final String LIST_CATEGORY = "select c.name, SUM(m.count) AS count, c.id from categories c, models m WHERE c.id=m.id_category group by m.id_category";
	public static final String LIST_ORDER_ITEMS_BY_ID_ORDER = "select o.id as oid, o.id_order, o.id_model, o.count, m.* , c.name as category, p.name as producer "
			+ "from order_items o, models m, categories c, producers p "
			+ "where p.id=m.id_producer and c.id=m.id_category and o.id_model=m.id and o.id_order=?";

	public static final String LIST_PRODUCER = "select p.name, SUM(m.count) AS count, p.id from producers p, models m WHERE p.id=m.id_producer group by m.id_producer";
	public static final String CART_BY_ID = "select * from carts where id_user= ?";
	public static final String CART_ITEMS_BY_CART_ID = "select * from cart_items where id_cart= ?";
	public static final String USER_BY_LOGIN = "select u.*, r.name as role, s.name as stat from users u, roles r, statuses s where u.login=? and r.id=u.id_user_role and s.id=u.id_user_status";
	public static final String USER_BY_ID = "select u.*, r.name as role, s.name as stat from users u, roles r, statuses s where u.id=? and r.id=u.id_user_role and s.id=u.id_user_status";
	public static final String MODEL_BY_ID = "select m.*, c.name as category, p.name as producer from models m, producers p, categories c where m.id= ? and c.id=m.id_category and p.id=m.id_producer";
	public static final String ADD_CART = "INSERT INTO carts(id_user, created) VALUES (?,?)";
	public static final String ADD_CART_ITEM = "INSERT INTO cart_items(id_cart, id_model, count) VALUES (?,?,?)";
	public static final String TOTAL_COUNT_OF_MODELS_IN_CART = "CALL total_count_in_cart(?)";
	public static final String TOTAL_SUM_OF_MODELS_IN_CART = "CALL count_tottal_sum_for_cart(?)";
	public static final String LIST_SHOP_CART_ITEMS = "SELECT m.name, m.description, m.id, m.price, m.image_link, m.count, categories.name as category, p.name as producer, c.count as countCart\r\n"
			+ "FROM models m, cart_items c, producers p, categories \r\n"
			+ "where c.id_model=m.id and c.id_cart= ? and categories.id=m.id_category and p.id=m.id_producer";
	public static final String DELETE_CART_BY_ID = "delete from carts where id_user=?";
	public static final String DELETE_CARTITEM_BY_ID_MODEL_AND_CART_ID = "delete from cart_items where id_model=? and id_cart=?";
	public static final String ADD_ORDER = "INSERT INTO orders(id_user, created, id_status_order ) VALUES (?,?, (select s.id from status_order s where s.name=?))";
	public static final String ADD_ORDER_ITEM = "INSERT INTO order_items(id_order, id_model, count) VALUES (?,?,?)";
	public static final String ORDER_BY_ID_USER = "select * from orders where id_user= ?";
	public static final String ORDER_BY_ID_USER_AND_DATE = "select * from orders where id_user= ? and created=?";
	public static final String DELETE_CART_ITEM_BY_ID_CART = "delete from cart_items where id_cart=?";
	public static final String DELETE_ORDER_BY_ID = "delete from orders where id=?";
	public static final String ORDER_BY_ID = "select o.id, o.id_user, o.created, s.name as status from orders o, status_order s where o.id_status_order=s.id and o.id=?";
	public static final String DELETE_ORDER_ITEM_BY_ID_ORDER = "delete from order_items where id_order=?";
	public static final String LAST_INSERT_ID = "SELECT LAST_INSERT_ID();";
	public static final String LIST_ORDER_BY_ID_USER = "SELECT o.id, o.created,o.id_user, s.name as status from orders o, status_order s where o.id_user=? and o.id_status_order=s.id ORDER BY id DESC limit ? offset ?";
	public static final String COUNT_ORDERS_BY_ID_USER = "select count(*) from orders where id_user=?";
	public static final String COUNT_MODELS = "select count(*) from models";
	public static final String COUNT_MODELS_BY_CATEGORY_ID = "select count(*) from models INNER JOIN categories ON models.id_category=categories.id where categories.id=?";
	public static final String BLACK_LIST = "select u.id, u.name, u.surname, u.login, u.email, u.black_list, s.name as stat, r.name as role from  users u, statuses s, roles r where u.black_list=true and u.id_user_role=r.id and\r\n"
			+ "u.id_user_status=s.id order by surname limit ? offset ?";
	public static final String COUNT_USERS_IN_BLACK_LIST = "select count(*) from users where users.black_list=true";
	public static final String ALL_USERS = "select u.id, u.name, u.surname, u.login, u.email, u.black_list, s.name as stat, r.name as role from  users u, statuses s, roles r where  u.black_list=false and u.id_user_role=r.id and\r\n"
			+ "u.id_user_status=s.id order by surname limit ? offset ?";
	public static final String COUNT_ALL_USERS = "select count(*) from users where users.black_list='false'";
	public static final String UPDATE_USERS_BLACK_LIST = "update users set black_list=? where id=?";
	public static final String UPDATE_MODEL_BY_ID = "update models set name=?, description=?, id_category=(select c.id from categories c where c.name=?), id_producer=\r\n"
			+ "(select p.id from producers p where p.name=?), count=?, price=? where id=?";
	public static final String USERS_BY_ROLE = "select u.id, u.name, u.surname, u.login, u.email, u.black_list, s.name as stat, r.name as role from  users u, statuses s, roles r where  u.black_list=false and u.id_user_role=r.id and\r\n"
			+ "u.id_user_status=s.id and u.id_user_role=? order by surname limit ? offset ?";
	public static final String COUNT_USERS_BY_ROLE = "select count(*) from users where id_user_role=?";
	public static final String MODEL_IMAGE_BY_ID = "select image_link from models where id=?";
	public static final String CART_ITEM_BY_CART_ID_MODEL_ID = "select * from cart_items where id_cart=? and id_model=?";
	public static final String UPDATE_CART_ITEM_COUNT_BY_MODEL_ID_CART_ID = "update cart_items set count=? where id_model=? and id_cart=?";
	public static final String LIST_ORDER = "select o.id, o.id_user, o.created, s.name as status from orders o, status_order s where o.id_status_order=s.id order by o.created desc limit ? offset ?";
	public static final String LIST_STATUS = "select name from status_order";

	public static final String COUNT_ORDERS = "select count(*) from orders";
	public static final String UPDATE_ORDER_STATUS_BY_ID = "update orders set id_status_order=(select s.id from status_order s where s.name=?) where id=?";

}
