package by.epamtc.shamuradova.ishop.dao.handler.impl;

import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.epamtc.shamuradova.ishop.bean.StatusOrder;
import by.epamtc.shamuradova.ishop.bean.entity.Cart;
import by.epamtc.shamuradova.ishop.bean.entity.CartItem;
import by.epamtc.shamuradova.ishop.bean.entity.Category;
import by.epamtc.shamuradova.ishop.bean.entity.Model;
import by.epamtc.shamuradova.ishop.bean.entity.Order;
import by.epamtc.shamuradova.ishop.bean.entity.OrderItem;
import by.epamtc.shamuradova.ishop.bean.entity.Producer;
import by.epamtc.shamuradova.ishop.bean.entity.User;
import by.epamtc.shamuradova.ishop.constant.database_column_name.CartColumnName;
import by.epamtc.shamuradova.ishop.constant.database_column_name.CartItemColumnName;
import by.epamtc.shamuradova.ishop.constant.database_column_name.CategotyColumnName;
import by.epamtc.shamuradova.ishop.constant.database_column_name.ModelColumnName;
import by.epamtc.shamuradova.ishop.constant.database_column_name.OrderColumnName;
import by.epamtc.shamuradova.ishop.constant.database_column_name.OrderItemColumnName;
import by.epamtc.shamuradova.ishop.constant.database_column_name.ProducerColumnName;
import by.epamtc.shamuradova.ishop.constant.database_column_name.UserColumnName;
import by.epamtc.shamuradova.ishop.dao.handler.ResultSetHandler;

/**
 * Класс-фабрика, который содержит лбъекты анонимных классов, которые реализуют
 * интерфейс ResultSetHandler2<T>(определят правила формирования объекта типа
 * ResultSet в java объект) и параметризированы по соответствующему типу
 * 
 * В случае выплнения запроса может возникнуть несколько вариантов получения
 * объекта: коллекцию или один объект, в заисимости от того, что возвращает
 * запрос: лист ResultSet или один объект ResultSet
 * 
 * метод getListResultSetHandler - возвращает объкт типа
 * ResultSetHandler2<List<T>>, который из списка ResultSet преобразует в список
 * соответствующих объектов
 * 
 * метод getSingleResultSetHandler - возвращает объект типа
 * ResultSetHandler2<T>, который из объекта ResultSet преобразует в
 * соответствующий объект
 * 
 * 
 * A factory class that defines anonymous classes that implement the
 * ResultSetHandler2 <T> interface (define rules forming an object of type
 * ResultSet into a java object) and parameterized by the corresponding object
 *
 * If the request is fulfilled, several options for obtaining an object may
 * arise: a collection or one object, depending on whether what the request
 * returns: a ResultSet sheet or one ResultSet object
 *
 * getListResultSetHandler method - returns an anonymous class of type
 * ResultSetHandler2 <List <T>>, which transforms from the ResultSet list to the
 * list of matching objects
 *
 * getSingleResultSetHandler method - returns an anonymous class of type
 * ResultSetHandler2 <T>, which transforms from ResultSet object to the
 * corresponding object
 *
 * @author Виктория Шамурадова 2020
 */

public class ResultSetHandlerFactory {

	private ResultSetHandlerFactory() {
	}

	/**
	 * Анонимный класс, который обрабатывает одну строчку ResultSet-а и возвращает
	 * нужный объект
	 */
	public final static ResultSetHandler<Model> MODEL_RESULT_SET_HANDLER = new ResultSetHandler<Model>() {
		@Override
		public Model handle(ResultSet rs) throws SQLException {
			Model model = new Model();
			model.setCategory(rs.getString(ModelColumnName.CATEGORY));
			model.setCount(rs.getInt(ModelColumnName.COUNT));
			model.setDescription(rs.getString(ModelColumnName.DESCRIPTION));
			model.setId(rs.getInt(ModelColumnName.ID));
			model.setImageLink(rs.getString(ModelColumnName.IMAGE_LINK));
			model.setName(rs.getString(ModelColumnName.NAME));
			model.setPrice(rs.getBigDecimal(ModelColumnName.PRICE));
			model.setProducer(rs.getString(ModelColumnName.PRODUCER));
			return model;
		}
	};

	public final static ResultSetHandler<CartItem> CART_ITEM_RESULT_SET_HANDLER = new ResultSetHandler<CartItem>() {

		@Override
		public CartItem handle(ResultSet rs) throws SQLException {

			CartItem cartItem = new CartItem();

			cartItem.setId(rs.getInt(CartItemColumnName.ID));
			cartItem.setModelId(rs.getInt(CartItemColumnName.MODEL_ID));
			cartItem.setCartId(rs.getInt(CartItemColumnName.CART_ID));
			cartItem.setCount(rs.getInt(CartItemColumnName.COUNT));

			return cartItem;
		}

	};

	public final static ResultSetHandler<Order> ORDER_RESULT_SET_HANDLER = new ResultSetHandler<Order>() {
		@Override
		public Order handle(ResultSet resultSet) throws SQLException {
			Order order = new Order();
			order.setId(resultSet.getInt(OrderColumnName.ID));
			order.setCreated(resultSet.getDate(OrderColumnName.CREATED));
			order.setIdUser(resultSet.getInt(OrderColumnName.ID_USER));
			order.setStatus(StatusOrder.valueOf(resultSet.getString(OrderColumnName.STATUS)));

			return order;

		}
	};

	public final static ResultSetHandler<Producer> PRODUCER_RESULT_SET_HANDLER = new ResultSetHandler<Producer>() {
		@Override
		public Producer handle(ResultSet rs) throws SQLException {
			Producer producer = new Producer();

			producer.setName(rs.getString(ProducerColumnName.NAME));
			producer.setId(rs.getInt(ProducerColumnName.ID));
			return producer;
		}
	};

	public final static ResultSetHandler<Category> CATEGORY_RESULT_SET_HANDLER = new ResultSetHandler<Category>() {
		@Override
		public Category handle(ResultSet rs) throws SQLException {
			Category category = new Category();
			category.setName(rs.getString(CategotyColumnName.NAME));
			category.setId(rs.getInt(CategotyColumnName.ID));
			category.setModelCount(rs.getInt(CategotyColumnName.COUNT));


			return category;
		}
	};

	public final static ResultSetHandler<User> USER_RESULT_SET_HANDLER = new ResultSetHandler<User>() {
		@Override
		public User handle(ResultSet rs) throws SQLException {
			User user = new User();
			user.setRole(rs.getString(UserColumnName.ROLE));
			user.setStatus(rs.getString(UserColumnName.STATUS));
			user.setEmail(rs.getString(UserColumnName.EMAIL));
			user.setId(rs.getInt(UserColumnName.ID));
			user.setLogin(rs.getString(UserColumnName.LOGIN));
			user.setName(rs.getString(UserColumnName.NAME));
			user.setSurname(rs.getString(UserColumnName.SURNAME));
			user.setBlackList(rs.getBoolean(UserColumnName.BLACK_LIST));
			return user;
		}
	};

	public final static ResultSetHandler<OrderItem> ORDER_ITEM_RESULT_SET_HANDLER = new ResultSetHandler<OrderItem>() {
		@Override
		public OrderItem handle(ResultSet rs) throws SQLException {

			OrderItem orderItem = new OrderItem();
			orderItem.setIdOrder(rs.getInt(OrderItemColumnName.ID_ORDER));
			orderItem.setId(rs.getInt(OrderItemColumnName.ID));
			orderItem.setCount(rs.getInt(OrderItemColumnName.COUNT));

			Model model = MODEL_RESULT_SET_HANDLER.handle(rs);
			orderItem.setModel(model);

			return orderItem;
		}

	};

	public final static ResultSetHandler<Cart> CART_RESULT_SET_HANDLER = new ResultSetHandler<Cart>() {

		@Override
		public Cart handle(ResultSet resultSet) throws SQLException {
			Cart cart = new Cart();
			cart.setUserId(resultSet.getInt(CartColumnName.ID_USER));
			cart.setCreated(resultSet.getDate(CartColumnName.CREATED));
			cart.setId(resultSet.getInt(CartColumnName.ID));

			return cart;
		}
	};

	/**
	 * Анонимный класс, который обрабатывает одну строчку ResultSet-а и возвращает
	 * количество
	 * 
	 */
	public final static ResultSetHandler<Integer> COUNT_RESULT_SET_HANDLER = new ResultSetHandler<Integer>() {
		@Override
		public Integer handle(ResultSet rs) throws SQLException {
			if (rs.next()) {
				return rs.getInt(1);
			} else {
				return 0;
			}
		}
	};

	public static <T> ResultSetHandler<List<T>> getListResultSetHandler(ResultSetHandler<T> oneRowResultSetHandler) {

		return new ResultSetHandler<List<T>>() {
			/**
			 * Создается коллекция ArrayList соответствующего типа и добавляется в нее
			 * обработчик каждой строчки ResultSet-а, который возращает объект
			 * 
			 */
			@Override
			public List<T> handle(ResultSet rs) throws SQLException {
				List<T> list = new ArrayList<>();
				while (rs.next()) {
					list.add(oneRowResultSetHandler.handle(rs));
				}
				return list;
			}
		};
	}

	/**
	 * Испоьзуется этот метод, если понадобится один объект из ResultSet-а
	 * 
	 */
	public static <T> ResultSetHandler<T> getSingleResultSetHandler(ResultSetHandler<T> oneRowResultSetHandler) {

		return new ResultSetHandler<T>() {
			@Override
			public T handle(ResultSet rs) throws SQLException {
				if (rs.next()) {
					return oneRowResultSetHandler.handle(rs);
				} else {
					return null;
				}
			}
		};
	}

	/**
	 * метод, который возвращает поток InputStream, нужен для считывания двоичных
	 * объектов из базы данных типа BLOB
	 */
	public static ResultSetHandler<InputStream> getImageResultSetHandler() {
		return new ResultSetHandler<InputStream>() {

			@Override
			public InputStream handle(ResultSet rs) throws SQLException {
				if (rs.next()) {
					return rs.getBinaryStream(ModelColumnName.IMAGE_LINK);
				} else {
					return null;
				}
			}
		};
	}
}
