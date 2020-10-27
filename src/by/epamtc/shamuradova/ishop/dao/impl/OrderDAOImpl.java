package by.epamtc.shamuradova.ishop.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import by.epamtc.shamuradova.ishop.bean.Model;
import by.epamtc.shamuradova.ishop.bean.Order;
import by.epamtc.shamuradova.ishop.bean.OrderItem;
import by.epamtc.shamuradova.ishop.bean.ShopCart;
import by.epamtc.shamuradova.ishop.bean.ShopCartItem;
import by.epamtc.shamuradova.ishop.constant.SQLQuery;
import by.epamtc.shamuradova.ishop.dao.OrderDAO;
import by.epamtc.shamuradova.ishop.dao.exception.ConnectionPoolException;
import by.epamtc.shamuradova.ishop.dao.exception.DAOException;
import by.epamtc.shamuradova.ishop.dao.handler.ResultSetHandler2;
import by.epamtc.shamuradova.ishop.dao.handler.factory.ResultSetHandler2Factory;
import by.epamtc.shamuradova.ishop.dao.handler.impl.ResultSetHandlerFactory;
import by.epamtc.shamuradova.ishop.dao.handler.impl.ResultSetHandlerOrder2;
import by.epamtc.shamuradova.ishop.dao.handler.impl.ResultSetHandlerOrderItem2;
import by.epamtc.shamuradova.ishop.dao.pool.ConnectionPool;
import by.epamtc.shamuradova.ishop.dao.util.JDBCUtil;

public class OrderDAOImpl implements OrderDAO {

	private ConnectionPool connectionPool;

	private ResultSetHandler2<Integer> getterCountResultSetHandler;
	private ResultSetHandler2<Order> resultSetHandlerOrder;
	
	private ResultSetHandler2<List<Order>> resultSetHandlerOrders;
	private ResultSetHandler2<List<OrderItem>> resultSetHandlerOrderItems;

	public OrderDAOImpl() {
		connectionPool = ConnectionPool.getInstance();

		getterCountResultSetHandler = ResultSetHandler2Factory.getInstatnce().getGetterCountResultSetHandler();
		resultSetHandlerOrder = ResultSetHandlerFactory.getSingleResultSetHandler(new ResultSetHandlerOrder2());
		
		resultSetHandlerOrders = ResultSetHandlerFactory.getListResultSetHandler(new ResultSetHandlerOrder2());
		resultSetHandlerOrderItems=ResultSetHandlerFactory.getListResultSetHandler(new ResultSetHandlerOrderItem2());
	}

	@Override
	public void addOrderItem(int idUser, Collection<ShopCartItem> items) throws DAOException {
		Connection connection = null;

		try {
			connection = connectionPool.getConnection();
			for (ShopCartItem item : items) {
				JDBCUtil.insertDeleteUpdate(connection, SQLQuery.ADD_ORDER_ITEM, idUser, item.getModel().getId(),
						item.getCount());

			}
		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			freeConnection(connection);
		}

	}

//озвращает последний id in order
	@Override
	public int addOrder(int idUser, ShopCart cart) throws DAOException {
		Connection connection = null;
		try {
			connection = connectionPool.getConnection();
			JDBCUtil.insertDeleteUpdate(connection, SQLQuery.ADD_ORDER, idUser, new Date(System.currentTimeMillis()));
			return JDBCUtil.selectSingleWithOUTPUT(connection, SQLQuery.LAST_INSERT_ID);

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			freeConnection(connection);
		}
	}

	@Override
	public Order getOrderByID(int idUser) throws DAOException {

		Connection connection = null;
		try {
			connection = connectionPool.getConnection();

			return JDBCUtil.select(connection, SQLQuery.ORDER_BY_ID_USER, resultSetHandlerOrder, idUser);

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			freeConnection(connection);
		}
	}

	@Override
	public void deleteOrderById(int id) throws DAOException {
		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			JDBCUtil.insertDeleteUpdate(connection, SQLQuery.DELETE_ORDER_BY_ID, id);

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);

		} finally {
			freeConnection(connection);
		}
	}

	@Override
	public void deleteOrderItemByIdOrder(int idOrder) throws DAOException {
		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			JDBCUtil.insertDeleteUpdate(connection, SQLQuery.DELETE_ORDER_ITEM_BY_ID_ORDER, idOrder);

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);

		} finally {
			freeConnection(connection);

		}

	}

	@Override
	public Order findOrderById(int id) throws DAOException {
		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			return JDBCUtil.select(connection, SQLQuery.ORDER_BY_ID, resultSetHandlerOrder, id);

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);

		} finally {
			freeConnection(connection);

		}

	}

	@Override
	public List<OrderItem> getOrderItemsByIdOrder(int idOrder) throws DAOException {

		Connection connection = null;
		try {
			connection = connectionPool.getConnection();

			return JDBCUtil.select(connection, SQLQuery.LIST_ORDER_ITEMS_BY_ID_ORDER, resultSetHandlerOrderItems, idOrder);

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);

		} finally {
			freeConnection(connection);
		}
	}

	@Override
	public List<Order> getListOrders(int idUser, int limit, int offset) throws DAOException {
		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			return JDBCUtil.select(connection, SQLQuery.LIST_ORDER_BY_ID_USER, resultSetHandlerOrders, idUser, limit,
					offset);

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);

		} finally {
			freeConnection(connection);
		}
	}

	@Override
	public int countOrdersByIdUser(int idUser) throws DAOException {
		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			return JDBCUtil.select(connection, SQLQuery.COUNT_ORDERS_BY_ID_USER, getterCountResultSetHandler, idUser);

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			freeConnection(connection);
		}
	}

	private void freeConnection(Connection connection) throws DAOException {
		if (connection != null) {
			try {
				connectionPool.free(connection);
			} catch (ConnectionPoolException e) {
				throw new DAOException(e);
			}
		}
	}

	public static void main(String[] args) throws DAOException {
		OrderDAOImpl dao = new OrderDAOImpl();

		ShopCart cart = new ShopCart();

		// create model, item
		Model m57 = new Model();
		m57.setId(57);
		m57.setPrice(new BigDecimal(5000));
		ShopCartItem item1 = new ShopCartItem(m57, 4);

		Model m58 = new Model();
		m58.setId(58);
		m58.setPrice(new BigDecimal(5000));
		ShopCartItem item2 = new ShopCartItem(m58, 1);

//create shopcart
		Map<Integer, ShopCartItem> ex = new HashMap();
		ex.put(57, item1);
		ex.put(58, item2);
		cart.setShopCartItems(ex);

		// dao.addOrder(17, cart);

		// System.out.println(dao.addOrder(18, cart));
		System.out.println(dao.countOrdersByIdUser(17));

	}

}
