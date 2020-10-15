package by.epamtc.shamuradova.ishop.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import by.epamtc.shamuradova.ishop.bean.Cart;
import by.epamtc.shamuradova.ishop.bean.CartContent;
import by.epamtc.shamuradova.ishop.constant.ErrorMessage;
import by.epamtc.shamuradova.ishop.constant.SQLQuery;
import by.epamtc.shamuradova.ishop.dao.CartDAO;
import by.epamtc.shamuradova.ishop.dao.exception.ConnectionPoolException;
import by.epamtc.shamuradova.ishop.dao.exception.DAOException;
import by.epamtc.shamuradova.ishop.dao.handler.ResultSetHandler;
import by.epamtc.shamuradova.ishop.dao.handler.impl.ResultSetHandlerCart;
import by.epamtc.shamuradova.ishop.dao.pool.ConnectionPool;
import by.epamtc.shamuradova.ishop.dao.util.JDBCUtil;

public class CartDAOImpl implements CartDAO {

	public CartDAOImpl() {

	}

	@Override
	public Cart getCartByUserId(int userId) throws DAOException {
		ResultSetHandler resultSetHandlerCart = new ResultSetHandlerCart();
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = null;
		Cart cart;

		try {
			pool.initPoolData();
			connection = pool.getConnection();

			cart = (Cart) JDBCUtil.selectSingle(connection, SQLQuery.CART_BY_ID, resultSetHandlerCart, userId);

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(ErrorMessage.DATABASE_ERROR, e);

		} finally {
			if (connection != null) {
				try {
					pool.free(connection);
				} catch (ConnectionPoolException e) {
					throw new DAOException(ErrorMessage.UNABLE_TO_FREE_CONNECTION);
				}
			}
		}

		return cart;
	}

	@Override
	public void addCart(int userId, Date date) throws DAOException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = null;

		try {
			pool.initPoolData();
			connection = pool.getConnection();

			JDBCUtil.insert(connection, SQLQuery.ADD_CART, userId, date);
		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {

			if (connection != null) {
				try {
					pool.free(connection);
				} catch (ConnectionPoolException e) {
					throw new DAOException(e);
				}
			}
		}

	}

	@Override
	public void addCartItem(CartContent cartContent) throws DAOException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = null;

		try {
			pool.initPoolData();
			connection = pool.getConnection();

			JDBCUtil.insert(connection, SQLQuery.ADD_CART_ITEM, cartContent.getCartId(), cartContent.getModelId(), cartContent.getCount());

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {

			if (connection != null) {
				try {
					pool.free(connection);
				} catch (ConnectionPoolException e) {
					throw new DAOException(e);
				}
			}
		}
		
	}
	public static void main(String[] args) throws DAOException {
		CartContent c = new CartContent();
		c.setCartId(6);
		c.setModelId(43);
		c.setCount(1);
		
		CartDAOImpl c1 = new CartDAOImpl();
		c1.addCartItem(c);
	}

}
