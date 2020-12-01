package by.epamtc.shamuradova.ishop.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtc.shamuradova.ishop.bean.ShopCartItem;
import by.epamtc.shamuradova.ishop.bean.StatusOrder;
import by.epamtc.shamuradova.ishop.bean.entity.Order;
import by.epamtc.shamuradova.ishop.bean.entity.OrderItem;
import by.epamtc.shamuradova.ishop.constant.SQLQuery;
import by.epamtc.shamuradova.ishop.dao.OrderDAO;
import by.epamtc.shamuradova.ishop.dao.exception.ConnectionPoolException;
import by.epamtc.shamuradova.ishop.dao.exception.DAOException;
import by.epamtc.shamuradova.ishop.dao.handler.impl.ResultSetHandlerFactory;
import by.epamtc.shamuradova.ishop.dao.pool.ConnectionPool;
import by.epamtc.shamuradova.ishop.dao.util.JDBCUtil;

/**
 * Класс, реализующий интерфейс OrderDAO, использующий sql- запросы
 * 
 * Class that implements the OrderDAO interface using sql queries
 *
 * @author Victoria Shamuradova 2020
 */

public class SQLOrderDAOImpl implements OrderDAO {

	private ConnectionPool connectionPool;
	private static final Logger logger = LogManager.getLogger(SQLCartDAOImpl.class);

	public SQLOrderDAOImpl() {
		connectionPool = ConnectionPool.getInstance();
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
			logger.error("Database error! Could not add OrderItem.", e);
			throw new DAOException("Could not add OrderItem.", e);
		} finally {
			freeConnection(connection);
		}
	}

//возвращает последний id in order
	@Override
	public int addOrder(int idUser, StatusOrder status) throws DAOException {
		Connection connection = null;
		try {
			connection = connectionPool.getConnection();
			JDBCUtil.insertDeleteUpdate(connection, SQLQuery.ADD_ORDER, idUser, new Date(System.currentTimeMillis()),
					status.toString());
			return JDBCUtil.selectSingleWithOUTPUT(connection, SQLQuery.LAST_INSERT_ID);

		} catch (ConnectionPoolException | SQLException e) {
			logger.error("Database error! Could not add Order.", e);
			throw new DAOException("Could not add Order.", e);
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
			logger.error("Database error! Could not delete Order", e);
			throw new DAOException("Could not delete Order", e);
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
			logger.error("Database error! Could not delete OrderItem.", e);
			throw new DAOException("Could not delete OrderItem.", e);
		} finally {
			freeConnection(connection);
		}
	}

	@Override
	public Order getOrderById(int id) throws DAOException {
		Connection connection = null;

		try {
			connection = connectionPool.getConnection();
			return JDBCUtil.select(connection, SQLQuery.ORDER_BY_ID,
					ResultSetHandlerFactory.getSingleResultSetHandler(ResultSetHandlerFactory.ORDER_RESULT_SET_HANDLER),
					id);

		} catch (ConnectionPoolException | SQLException e) {
			logger.error("Database error! Could not get Order.", e);
			throw new DAOException("Could not get Order.", e);
		} finally {
			freeConnection(connection);
		}
	}

	@Override
	public List<OrderItem> getOrderItemsByIdOrder(int idOrder) throws DAOException {
		Connection connection = null;
		
		try {
			connection = connectionPool.getConnection();
			return JDBCUtil.select(connection, SQLQuery.LIST_ORDER_ITEMS_BY_ID_ORDER, ResultSetHandlerFactory
					.getListResultSetHandler(ResultSetHandlerFactory.ORDER_ITEM_RESULT_SET_HANDLER), idOrder);

		} catch (ConnectionPoolException | SQLException e) {
			logger.error("Database error! Could not get List of OrderItems.", e);
			throw new DAOException("Could not get List of OrderItems", e);
		} finally {
			freeConnection(connection);
		}
	}

	@Override
	public List<Order> getListOrdersByUserId(int userId, int limit, int offset) throws DAOException {
		Connection connection = null;

		try {
			connection = connectionPool.getConnection();
			return JDBCUtil.select(connection, SQLQuery.LIST_ORDER_BY_ID_USER,
					ResultSetHandlerFactory.getListResultSetHandler(ResultSetHandlerFactory.ORDER_RESULT_SET_HANDLER),
					userId, limit, offset);

		} catch (ConnectionPoolException | SQLException e) {
			logger.error("Database error! Could not get List of Orders.", e);
			throw new DAOException("Could not get List of Orders", e);
		} finally {
			freeConnection(connection);
		}
	}

	@Override
	public List<Order> getListOrders(int limit, int offset) throws DAOException {
		Connection connection = null;

		try {
			connection = connectionPool.getConnection();
			return JDBCUtil.select(connection, SQLQuery.LIST_ORDER,
					ResultSetHandlerFactory.getListResultSetHandler(ResultSetHandlerFactory.ORDER_RESULT_SET_HANDLER),
					limit, offset);

		} catch (ConnectionPoolException | SQLException e) {
			logger.error("Database error! Could not get List of Orders.", e);
			throw new DAOException("Could not get List of Orders", e);
		} finally {
			freeConnection(connection);
		}
	}

	@Override
	public int countOrdersByIdUser(int idUser) throws DAOException {
		Connection connection = null;
		
		try {
			connection = connectionPool.getConnection();
			return JDBCUtil.select(connection, SQLQuery.COUNT_ORDERS_BY_ID_USER,
					ResultSetHandlerFactory.COUNT_RESULT_SET_HANDLER, idUser);

		} catch (ConnectionPoolException | SQLException e) {
			logger.error("Database error! Could not get count of Orders.", e);
			throw new DAOException("Could not get count of Orders", e);
		} finally {
			freeConnection(connection);
		}
	}

	@Override
	public void updateOrderStatus(int orderId, StatusOrder status) throws DAOException {
		Connection connection = null;
		
		try {
			connection = connectionPool.getConnection();
			JDBCUtil.insertDeleteUpdate(connection, SQLQuery.UPDATE_ORDER_STATUS_BY_ID, status.toString(), orderId);
		} catch (ConnectionPoolException | SQLException e) {
			logger.error("Database error! Could not update Order Status.", e);
			throw new DAOException("Could not update Order Status", e);
		} finally {
			freeConnection(connection);
		}
	}

	@Override
	public int countOrders() throws DAOException {
		Connection connection = null;
		try {
			connection = connectionPool.getConnection();
			return JDBCUtil.select(connection, SQLQuery.COUNT_ORDERS, ResultSetHandlerFactory.COUNT_RESULT_SET_HANDLER);
		} catch (ConnectionPoolException | SQLException e) {
			logger.error("Database error! Could not get count of Order.", e);
			throw new DAOException("Could not get count of Order", e);
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
}
