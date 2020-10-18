package by.epamtc.shamuradova.ishop.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.epamtc.shamuradova.ishop.bean.Cart;
import by.epamtc.shamuradova.ishop.bean.CartContent;
import by.epamtc.shamuradova.ishop.bean.Model;
import by.epamtc.shamuradova.ishop.bean.ShopCartItem;
import by.epamtc.shamuradova.ishop.constant.ErrorMessage;
import by.epamtc.shamuradova.ishop.constant.SQLQuery;
import by.epamtc.shamuradova.ishop.constant.database_column_name.ModelColumnName;
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

			JDBCUtil.insert(connection, SQLQuery.ADD_CART_ITEM, cartContent.getCartId(), cartContent.getModelId(),
					cartContent.getCount());

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
	public int getTotalSumCart(int idUser) throws DAOException {

		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = null;

		try {
			pool.initPoolData();
			connection = pool.getConnection();

			ResultSet res = JDBCUtil.call(connection, SQLQuery.TOTAL_SUM_OF_MODELS_IN_CART, idUser);

			if (res.next()) {
				return res.getInt("total_sum");
			} else
				throw new DAOException("total sum error");
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
	public int getTotalCountOfModelsInCart(int idUser) throws DAOException {

		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = null;

		try {
			pool.initPoolData();
			connection = pool.getConnection();

			ResultSet res = JDBCUtil.call(connection, SQLQuery.TOTAL_COUNT_OF_MODELS_IN_CART, idUser);

			if (res.next()) {
				return res.getInt("count");
			} else
				throw new DAOException("total count error");
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
	public List<ShopCartItem> getShopCartItems(int cartId) throws DAOException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = null;
		PreparedStatement prStatement = null;
		ResultSet resultSet = null;
		List<ShopCartItem> items = new ArrayList();

		try {
			pool.initPoolData();
			connection = pool.getConnection();

			prStatement = connection.prepareStatement(SQLQuery.LIST_SHOP_CART_ITEMS);
			prStatement.setObject(1, cartId);
			resultSet = prStatement.executeQuery();

			while (resultSet.next()) {
				Model model = new Model();
				model.setCategory(resultSet.getString(ModelColumnName.CATEGORY));
				model.setDescription(resultSet.getString(ModelColumnName.DESCRIPTION));
				model.setId(resultSet.getInt(ModelColumnName.ID));
				model.setImageLink(resultSet.getString(ModelColumnName.IMAGE_LINK));
				model.setName(resultSet.getString(ModelColumnName.NAME));
				model.setPrice(resultSet.getBigDecimal(ModelColumnName.PRICE));
				model.setProducer(resultSet.getString(ModelColumnName.PRODUCER));

				ShopCartItem item = new ShopCartItem(model, resultSet.getInt("count"));
				items.add(item);
			}

			return items;
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
			if (prStatement != null) {
				try {
					prStatement.close();
				} catch (SQLException e) {
					throw new DAOException(ErrorMessage.UNABLE_TO_CLOSE_STATEMENT, e);
				}
			}
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					throw new DAOException(ErrorMessage.UNABLE_TO_CLOSE_STATEMENT);
				}
			}
		}
	}

	public static void main(String[] args) throws DAOException {
		CartDAOImpl cart = new CartDAOImpl();
		System.out.println(cart.getTotalSumCart(15));
		System.out.println(cart.getTotalCountOfModelsInCart(15));
		System.out.println(cart.getShopCartItems(6));
	}

}
