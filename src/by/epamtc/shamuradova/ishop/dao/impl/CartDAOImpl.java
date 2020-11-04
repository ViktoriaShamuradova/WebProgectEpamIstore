package by.epamtc.shamuradova.ishop.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.epamtc.shamuradova.ishop.bean.CartContent;
import by.epamtc.shamuradova.ishop.bean.ShopCartItem;
import by.epamtc.shamuradova.ishop.bean.entity.Cart;
import by.epamtc.shamuradova.ishop.bean.entity.CartItem;
import by.epamtc.shamuradova.ishop.bean.entity.Model;
import by.epamtc.shamuradova.ishop.constant.ErrorMessage;
import by.epamtc.shamuradova.ishop.constant.SQLQuery;
import by.epamtc.shamuradova.ishop.dao.CartDAO;
import by.epamtc.shamuradova.ishop.dao.exception.ConnectionPoolException;
import by.epamtc.shamuradova.ishop.dao.exception.DAOException;
import by.epamtc.shamuradova.ishop.dao.handler.ResultSetHandler2;
import by.epamtc.shamuradova.ishop.dao.handler.impl.ResultSetHandlerCart2;
import by.epamtc.shamuradova.ishop.dao.handler.impl.ResultSetHandlerCartItem2;
import by.epamtc.shamuradova.ishop.dao.handler.impl.ResultSetHandlerFactory;
import by.epamtc.shamuradova.ishop.dao.handler.impl.ResultSetHandlerModel2;
import by.epamtc.shamuradova.ishop.dao.pool.ConnectionPool;
import by.epamtc.shamuradova.ishop.dao.util.JDBCUtil;

public class CartDAOImpl implements CartDAO {

	private ConnectionPool pool;
	private ResultSetHandler2<Cart> resultSetHandlerCart;
	private ResultSetHandler2<CartItem> resultSetHandlerCartItem;
	private ResultSetHandler2<List<CartItem>> resultSetHandlerCartIems;

	public CartDAOImpl() {
		pool = ConnectionPool.getInstance();
		resultSetHandlerCart = ResultSetHandlerFactory.getSingleResultSetHandler(new ResultSetHandlerCart2());
		resultSetHandlerCartItem = ResultSetHandlerFactory.getSingleResultSetHandler(new ResultSetHandlerCartItem2());
		resultSetHandlerCartIems = ResultSetHandlerFactory.getListResultSetHandler(new ResultSetHandlerCartItem2());
	}

	@Override
	public void deleteCartItemByIdCart(int idCart) throws DAOException {

		Connection connection = null;

		try {
			connection = pool.getConnection();

			JDBCUtil.insertDeleteUpdate(connection, SQLQuery.DELETE_CART_ITEM_BY_ID_CART, idCart);

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);

		} finally {
			freeConnection(connection);
		}

	}

	@Override
	public Cart getCartByUserId(int userId) throws DAOException {
		Connection connection = null;

		try {
			connection = pool.getConnection();

			return JDBCUtil.select(connection, SQLQuery.CART_BY_ID, resultSetHandlerCart, userId);

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(ErrorMessage.DATABASE_ERROR, e);

		} finally {
			freeConnection(connection);
		}
	}

	@Override
	public List<CartItem> getCartItemsByCartId(int cartId) throws DAOException {
		Connection connection = null;

		try {
			connection = pool.getConnection();

			return JDBCUtil.select(connection, SQLQuery.CART_ITEMS_BY_CART_ID, resultSetHandlerCartIems, cartId);

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(ErrorMessage.DATABASE_ERROR, e);

		} finally {
			freeConnection(connection);
		}
	}

	@Override
	public void addCart(int userId, Date date) throws DAOException {

		Connection connection = null;

		try {

			connection = pool.getConnection();

			JDBCUtil.insertDeleteUpdate(connection, SQLQuery.ADD_CART, userId, date);
		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			freeConnection(connection);
		}
	}

	@Override
	public void addCartItem(CartContent cartContent) throws DAOException {

		Connection connection = null;
		try {

			connection = pool.getConnection();

			JDBCUtil.insertDeleteUpdate(connection, SQLQuery.ADD_CART_ITEM, cartContent.getCartId(),
					cartContent.getModelId(), cartContent.getCount());

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			freeConnection(connection);
		}
	}
	
	@Override
	public void updateCartItemCountByModelIdCartId(int modelId, int count, int cartId) throws DAOException {

		Connection connection = null;
		try {

			connection = pool.getConnection();

			JDBCUtil.insertDeleteUpdate(connection, SQLQuery.UPDATE_CART_ITEM_COUNT_BY_MODEL_ID_CART_ID,count, modelId, cartId);

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			freeConnection(connection);
		}
		
	}

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
			throw new DAOException(e);
		} finally {
			freeConnection(connection);
		}
	}

	@Override
	public int getTotalCountOfModelsInCart(int idUser) throws DAOException {

		Connection connection = null;
		try {
			connection = pool.getConnection();

			ResultSet res = JDBCUtil.call(connection, SQLQuery.TOTAL_COUNT_OF_MODELS_IN_CART, idUser);

			if (res.next()) {
				return res.getInt("count");
			} else
				throw new DAOException("total count error");
		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			freeConnection(connection);
		}
	}

//correct
	@Override
	public List<ShopCartItem> getShopCartItems(int cartId) throws DAOException {
		Connection connection = null;
		PreparedStatement prStatement = null;
		ResultSet resultSet = null;
		List<ShopCartItem> items = new ArrayList<>();
		ResultSetHandler2<Model> resultSetHandlerModel = new ResultSetHandlerModel2();

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
			throw new DAOException(e);
		} finally {
			freeConnection(connection);
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

	@Override
	public boolean deleteCartByidUser(int idUser) throws DAOException {
		Connection connection = null;

		try {
			connection = pool.getConnection();

			JDBCUtil.insertDeleteUpdate(connection, SQLQuery.DELETE_CART_BY_ID, idUser);
			return true;
		} catch (ConnectionPoolException | SQLException e) {
			return false;

		} finally {
			freeConnection(connection);
		}
	}

	@Override
	public CartItem getCartItemByCartIdModelId(int cartId, int modelId) throws DAOException {
		Connection connection = null;

		try {
			connection = pool.getConnection();

			return JDBCUtil.select(connection, SQLQuery.CART_ITEM_BY_CART_ID_MODEL_ID, resultSetHandlerCartItem, cartId,
					modelId);

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(ErrorMessage.DATABASE_ERROR, e);

		} finally {
			freeConnection(connection);
		}
	}

	
	@Override
	public void deleteCartItemByIdModel(int idModel) throws DAOException {
		Connection connection = null;

		try {
			connection = pool.getConnection();
			JDBCUtil.insertDeleteUpdate(connection, SQLQuery.DELETE_CARTITEM_BY_ID_MODEL, idModel);

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);

		} finally {
			freeConnection(connection);
		}
	}

	private void freeConnection(Connection connection) throws DAOException {
		if (connection != null) {
			try {
				pool.free(connection);
			} catch (ConnectionPoolException e) {
				throw new DAOException(e);
			}
		}
	}

	public static void main(String[] args) throws DAOException {
		CartDAOImpl cart = new CartDAOImpl();
		cart.deleteCartItemByIdModel(56);
		System.out.println(cart.getTotalSumCart(15));
		System.out.println(cart.getTotalCountOfModelsInCart(15));
		System.out.println(cart.getShopCartItems(6));
	}

	

	

}
