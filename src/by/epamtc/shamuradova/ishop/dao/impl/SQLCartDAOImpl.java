package by.epamtc.shamuradova.ishop.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import by.epamtc.shamuradova.ishop.bean.CartContent;
import by.epamtc.shamuradova.ishop.bean.ShopCartItem;
import by.epamtc.shamuradova.ishop.bean.entity.Cart;
import by.epamtc.shamuradova.ishop.bean.entity.CartItem;
import by.epamtc.shamuradova.ishop.bean.entity.Model;
import by.epamtc.shamuradova.ishop.constant.SQLQuery;
import by.epamtc.shamuradova.ishop.dao.CartDAO;
import by.epamtc.shamuradova.ishop.dao.exception.ConnectionPoolException;
import by.epamtc.shamuradova.ishop.dao.exception.DAOException;
import by.epamtc.shamuradova.ishop.dao.handler.ResultSetHandler;
import by.epamtc.shamuradova.ishop.dao.handler.impl.ResultSetHandlerFactory;
import by.epamtc.shamuradova.ishop.dao.pool.ConnectionPool;
import by.epamtc.shamuradova.ishop.dao.util.JDBCUtil;

/**
 * Класс, реализующий интерфейс CartDAO, использующий sql- запросы
 * 
 * Class that implements the CartDAO interface using sql queries
 *
 * @author Victoria Shamuradova 2020
 */

public class SQLCartDAOImpl implements CartDAO {

	private ConnectionPool pool;
	private static final Logger logger = LogManager.getLogger(SQLCartDAOImpl.class);

	public SQLCartDAOImpl(ConnectionPool connectionPool) {
		pool = connectionPool;
	}

	public SQLCartDAOImpl() {
		this(ConnectionPool.getInstance());
	}

	/**
	 * Execute the SQL statement, removes CartItem by id Cart
	 *
	 * @param idCart unique cart identifier in database
	 * @throws DAOException if occurred severe problem with database
	 */
	@Override
	public void deleteCartItemByIdCart(int idCart) throws DAOException {

		Connection connection = null;
		try {
			connection = pool.getConnection();

			JDBCUtil.insertDeleteUpdate(connection, SQLQuery.DELETE_CART_ITEM_BY_ID_CART, idCart);

		} catch (ConnectionPoolException | SQLException e) {
			logger.error("Database error! Could not delete CartItem.", e);
			throw new DAOException("Database error! Could not delete CartItem.", e);
		} finally {
			pool.free(connection);
		}
	}

	/**
	 * Execute the SQL statement, get Cart by user id
	 *
	 * @param userId unique user identifier in database
	 * @return Cart
	 * @throws DAOException if occurred severe problem with database
	 */
	@Override
	public Cart getCartByUserId(int userId) throws DAOException {
		Connection connection = null;
		ResultSetHandler<Cart> cartHandler = ResultSetHandlerFactory
				.getSingleResultSetHandler(ResultSetHandlerFactory.CART_RESULT_SET_HANDLER);

		try {
			connection = pool.getConnection();

			return JDBCUtil.select(connection, SQLQuery.CART_BY_ID, cartHandler, userId);

		} catch (ConnectionPoolException | SQLException e) {
			logger.error("Database error! Could not get Cart", e);
			throw new DAOException("Could not get Cart", e);

		} finally {
			pool.free(connection);
		}
	}

	/**
	 * Execute the SQL statement, get list of CartItems by cart id
	 *
	 * @param cartId unique cart identifier in database
	 * @return List<CartItem>
	 * @throws DAOException if occurred severe problem with database
	 */
	@Override
	public List<CartItem> getCartItemsByCartId(int cartId) throws DAOException {
		Connection connection = null;
		ResultSetHandler<List<CartItem>> cartHandler = ResultSetHandlerFactory
				.getListResultSetHandler(ResultSetHandlerFactory.CART_ITEM_RESULT_SET_HANDLER);
		try {
			connection = pool.getConnection();

			return JDBCUtil.select(connection, SQLQuery.CART_ITEMS_BY_CART_ID, cartHandler, cartId);

		} catch (ConnectionPoolException | SQLException e) {
			logger.error("Database error! Could not get list of CartItems", e);
			throw new DAOException("Could not get list of CartItems", e);
		} finally {
			pool.free(connection);
		}
	}

	/**
	 * Execute the SQL statement, add Cart in db
	 *
	 * @param userId unique user identifier in database
	 * @param date
	 * @throws DAOException if occurred severe problem with database
	 */
	@Override
	public void addCart(int userId, Date date) throws DAOException {
		Connection connection = null;
		try {

			connection = pool.getConnection();

			JDBCUtil.insertDeleteUpdate(connection, SQLQuery.ADD_CART, userId, date);
		} catch (ConnectionPoolException | SQLException e) {
			logger.error("Database error! Could not add Cart", e);
			throw new DAOException("Could not add Cart", e);
		} finally {
			pool.free(connection);
		}
	}

	/**
	 * Execute the SQL statement, add CartItem
	 *
	 * @param CartContent represents data on CartItem
	 * @throws DAOException if occurred severe problem with database
	 */
	@Override
	public void addCartItem(CartContent cartContent) throws DAOException {

		Connection connection = null;
		try {

			connection = pool.getConnection();

			JDBCUtil.insertDeleteUpdate(connection, SQLQuery.ADD_CART_ITEM, cartContent.getCartId(),
					cartContent.getModelId(), cartContent.getCount());

		} catch (ConnectionPoolException | SQLException e) {
			logger.error("Database error! Could not add CartItem", e);
			throw new DAOException("Could not add CartItem", e);
		} finally {
			pool.free(connection);
		}
	}

	/**
	 * Execute the SQL statement, update CartItem
	 *
	 * @param modelId unique model identifier in database
	 * @param cartId  unique cart identifier in database
	 * @param cartId  represent count of CartItem
	 * @throws DAOException if occurred severe problem with database
	 */
	@Override
	public void updateCartItemCountByModelIdCartId(int modelId, int count, int cartId) throws DAOException {
		Connection connection = null;
		try {

			connection = pool.getConnection();

			JDBCUtil.insertDeleteUpdate(connection, SQLQuery.UPDATE_CART_ITEM_COUNT_BY_MODEL_ID_CART_ID, count, modelId,
					cartId);

		} catch (ConnectionPoolException | SQLException e) {
			logger.error("Database error! Could not update CartItem", e);
			throw new DAOException("Could not update CartItem", e);
		} finally {
			pool.free(connection);
		}
	}

	/**
	 * Execute the SQL statement, get total sum of cart
	 *
	 * @param idUser unique user identifier in database
	 * @return BigDecimal sum
	 * @throws DAOException if occurred severe problem with database
	 */
	@Override
	public BigDecimal getTotalSumCart(int idUser) throws DAOException {

		Connection connection = null;
		try {
			connection = pool.getConnection();

			ResultSet res = JDBCUtil.call(connection, SQLQuery.TOTAL_SUM_OF_MODELS_IN_CART, idUser);

			if (res.next()) {
				return res.getBigDecimal("total_sum");
			} else
				throw new DAOException("total sum error");
		} catch (ConnectionPoolException | SQLException e) {
			logger.error("Database error! Could not get total sum of cart", e);
			throw new DAOException("Could not get total sum of cart", e);
		} finally {
			pool.free(connection);
		}
	}

	/**
	 * Execute the SQL statement, get total count of cart
	 *
	 * @param idUser unique user identifier in database
	 * @return int count
	 * @throws DAOException if occurred severe problem with database
	 */
	@Override
	public int getTotalCountOfModelsInCart(int idUser) throws DAOException {

		Connection connection = null;
		try {
			connection = pool.getConnection();

			ResultSet res = JDBCUtil.call(connection, SQLQuery.TOTAL_COUNT_OF_MODELS_IN_CART, idUser);

			if (res.next()) {
				return res.getInt("count");
			} else
				return 0;
		} catch (ConnectionPoolException | SQLException e) {
			logger.error("Database error! Could not get total count of cart", e);
			throw new DAOException("Could not get total count of cart", e);
		} finally {
			pool.free(connection);
		}
	}

	/**
	 * Execute the SQL statement, get list of ShopCartItem by cart id
	 *
	 * @param cartId unique cart identifier in database
	 * @return List<ShopCartItem>
	 * @throws DAOException if occurred severe problem with database
	 */
	@Override
	public List<ShopCartItem> getShopCartItems(int cartId) throws DAOException {
		Connection connection = null;
		PreparedStatement prStatement = null;
		ResultSet resultSet = null;
		List<ShopCartItem> items = new ArrayList<>();
		ResultSetHandler<Model> resultSetHandlerModel = ResultSetHandlerFactory.MODEL_RESULT_SET_HANDLER;

		try {
			connection = pool.getConnection();

			prStatement = connection.prepareStatement(SQLQuery.LIST_SHOP_CART_ITEMS);
			prStatement.setInt(1, cartId);
			resultSet = prStatement.executeQuery();

			while (resultSet.next()) {
				Model model = resultSetHandlerModel.handle(resultSet);

				ShopCartItem item = new ShopCartItem(model, resultSet.getInt("countCart"));
				items.add(item);
			}

			return items;
		} catch (ConnectionPoolException | SQLException e) {
			logger.error("Database error! Could not get list of ShopCartItem", e);
			throw new DAOException("Could not get list of ShopCartItem", e);
		} finally {
			pool.free(connection);
			if (prStatement != null) {
				try {
					prStatement.close();
				} catch (SQLException e) {
					logger.error("Database error! Could not close statement", e);
					throw new DAOException("Could not get list of ShopCartItem", e);
				}
			}
		}
	}

	/**
	 * Execute the SQL statement, removes Cart by id user
	 *
	 * @param idUser unique user identifier in database
	 * @throws DAOException if occurred severe problem with database
	 */
	@Override
	public void deleteCartByidUser(int idUser) throws DAOException {
		Connection connection = null;

		try {
			connection = pool.getConnection();

			JDBCUtil.insertDeleteUpdate(connection, SQLQuery.DELETE_CART_BY_ID, idUser);

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);

		} finally {
			pool.free(connection);
		}
	}

	/**
	 * Execute the SQL statement, get CartItem by cart id, model id
	 *
	 * @param cartId  unique cart identifier in database
	 * @param modelId unique model identifier in database
	 * @return CartItem
	 * @throws DAOException if occurred severe problem with database
	 */
	@Override
	public CartItem getCartItemByCartIdModelId(int cartId, int modelId) throws DAOException {
		Connection connection = null;
		ResultSetHandler<CartItem> cartItemHandler = ResultSetHandlerFactory
				.getSingleResultSetHandler(ResultSetHandlerFactory.CART_ITEM_RESULT_SET_HANDLER);
		try {
			connection = pool.getConnection();

			return JDBCUtil.select(connection, SQLQuery.CART_ITEM_BY_CART_ID_MODEL_ID, cartItemHandler, cartId,
					modelId);

		} catch (ConnectionPoolException | SQLException e) {
			logger.error("Database error! Could not get CartItem", e);
			throw new DAOException("Could not get CartItem. Could not get CartItem", e);

		} finally {
			pool.free(connection);
		}
	}

	/**
	 * Execute the SQL statement, delete CartItem by cart id, model id
	 *
	 * @param cartId  unique cart identifier in database
	 * @param modelId unique model identifier in database
	 * @return CartItem
	 * @throws DAOException if occurred severe problem with database
	 */
	@Override
	public void deleteCartItemByIdModelCartId(int idModel, int cartId) throws DAOException {
		Connection connection = null;

		try {
			connection = pool.getConnection();
			JDBCUtil.insertDeleteUpdate(connection, SQLQuery.DELETE_CARTITEM_BY_ID_MODEL_AND_CART_ID, idModel, cartId);

		} catch (ConnectionPoolException | SQLException e) {
			logger.error("Database error! Could not get CartItem", e);
			throw new DAOException("Database error! Could not get CartItem. Could not get CartItem", e);

		} finally {
			pool.free(connection);
		}
	}

}
