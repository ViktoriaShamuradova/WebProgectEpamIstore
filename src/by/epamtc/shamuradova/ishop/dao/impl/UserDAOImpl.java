package by.epamtc.shamuradova.ishop.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import by.epamtc.shamuradova.ishop.bean.User;
import by.epamtc.shamuradova.ishop.constant.ErrorMessage;
import by.epamtc.shamuradova.ishop.constant.SQLQuery;
import by.epamtc.shamuradova.ishop.dao.UserDAO;
import by.epamtc.shamuradova.ishop.dao.exception.ConnectionPoolException;
import by.epamtc.shamuradova.ishop.dao.exception.DAOException;
import by.epamtc.shamuradova.ishop.dao.handler.ResultSetHandler;
import by.epamtc.shamuradova.ishop.dao.handler.ResultSetHandler2;
import by.epamtc.shamuradova.ishop.dao.handler.factory.ResultSetHandler2Factory;
import by.epamtc.shamuradova.ishop.dao.handler.impl.ResultSetHandlerFactory;
import by.epamtc.shamuradova.ishop.dao.handler.impl.ResultSetHandlerUser;
import by.epamtc.shamuradova.ishop.dao.handler.impl.ResultSetHandlerUser2;
import by.epamtc.shamuradova.ishop.dao.pool.ConnectionPool;
import by.epamtc.shamuradova.ishop.dao.util.JDBCUtil;

public class UserDAOImpl implements UserDAO {

	private ConnectionPool pool;

	private ResultSetHandler2<User> resultSetHandlerUser;
	private ResultSetHandler2<List<User>> resultSetHandlerUsers;
	private ResultSetHandler2<Integer> getterCountResultSetHandler;

	public UserDAOImpl() {
		pool = ConnectionPool.getInstance();

		resultSetHandlerUser = ResultSetHandlerFactory.getSingleResultSetHandler(new ResultSetHandlerUser2());
		resultSetHandlerUsers = ResultSetHandlerFactory.getListResultSetHandler(new ResultSetHandlerUser2());
		getterCountResultSetHandler = ResultSetHandler2Factory.getInstatnce().getGetterCountResultSetHandler();
	}

	@Override
	public User getUserByLogin(String login) throws DAOException {
		Connection connection = null;
		try {
			connection = pool.getConnection();
			String sql = SQLQuery.USER_BY_LOGIN;

			return JDBCUtil.select(connection, sql, resultSetHandlerUser, login);

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(ErrorMessage.DATABASE_ERROR, e);

		} finally {
			freeConnection(connection);
		}
	}

	@Override
	public List<User> getBlackList(int page, int limit) throws DAOException {
		Connection connection = null;
		try {
			connection = pool.getConnection();
			int offset = (page - 1) * limit;

			String sql = SQLQuery.BLACK_LIST;

			return JDBCUtil.select(connection, sql, resultSetHandlerUsers, limit, offset);

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(ErrorMessage.DATABASE_ERROR, e);

		} finally {
			freeConnection(connection);
		}
	}

	private void freeConnection(Connection connection) throws DAOException {
		if (connection != null) {
			try {
				pool.free(connection);
			} catch (ConnectionPoolException e) {
				throw new DAOException(ErrorMessage.UNABLE_TO_FREE_CONNECTION);
			}
		}
	}

	@Override
	public int countUsersInBlackList() throws DAOException {
		Connection connection = null;
		try {
			connection = pool.getConnection();

			return JDBCUtil.select(connection, SQLQuery.COUNT_USERS_IN_BLACK_LIST, getterCountResultSetHandler);

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			freeConnection(connection);
		}
	}
	
	@Override
	public void deleteUserFromBlackList(int userId) throws DAOException {
		Connection connection = null;
		try {
			connection = pool.getConnection();

			JDBCUtil.insertDeleteUpdate(connection, SQLQuery.DELETE_USER_FROM_BLACK_LIST_BY_USER_ID, userId);

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			freeConnection(connection);
		}
		
	}
	public static void main(String[] args) throws DAOException {
		UserDAOImpl dao = new UserDAOImpl();
		dao.deleteUserFromBlackList(1);
		//System.out.println();
	}

	

}
