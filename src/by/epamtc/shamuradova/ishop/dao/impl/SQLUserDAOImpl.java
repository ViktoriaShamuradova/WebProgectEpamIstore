package by.epamtc.shamuradova.ishop.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import by.epamtc.shamuradova.ishop.bean.entity.User;
import by.epamtc.shamuradova.ishop.constant.ErrorMessage;
import by.epamtc.shamuradova.ishop.constant.SQLQuery;
import by.epamtc.shamuradova.ishop.dao.UserDAO;
import by.epamtc.shamuradova.ishop.dao.exception.ConnectionPoolException;
import by.epamtc.shamuradova.ishop.dao.exception.DAOException;
import by.epamtc.shamuradova.ishop.dao.handler.ResultSetHandler2;
import by.epamtc.shamuradova.ishop.dao.handler.impl.ResultSetHandlerFactory;
import by.epamtc.shamuradova.ishop.dao.pool.ConnectionPool;
import by.epamtc.shamuradova.ishop.dao.util.JDBCUtil;

/* Класс, реализующий интерфейс UserDAO, использующий sql- запросы
 * 
 * Class that implements the UserDAO interface using sql queries
 *
 * @author Шамурадова Виктория 2020
 */
public class SQLUserDAOImpl implements UserDAO {

	private ConnectionPool pool;

	private ResultSetHandler2<User> resultSetHandlerUser;
	private ResultSetHandler2<List<User>> resultSetHandlerUsers;
	private ResultSetHandler2<Integer> countResultSetHandler;

	public SQLUserDAOImpl() {
		pool = ConnectionPool.getInstance();

		resultSetHandlerUser = ResultSetHandlerFactory.getSingleResultSetHandler(ResultSetHandlerFactory.USER_RESULT_SET_HANDLER);
		resultSetHandlerUsers = ResultSetHandlerFactory.getListResultSetHandler(ResultSetHandlerFactory.USER_RESULT_SET_HANDLER);
		countResultSetHandler = ResultSetHandlerFactory.COUNT_RESULT_SET_HANDLER;
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
	public User getUserById(int userId) throws DAOException {
		Connection connection = null;
		try {
			connection = pool.getConnection();
			String sql = SQLQuery.USER_BY_ID;

			return JDBCUtil.select(connection, sql, resultSetHandlerUser, userId);

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

	@Override
	public int countUsersInBlackList() throws DAOException {
		return getCount(SQLQuery.COUNT_USERS_IN_BLACK_LIST);
	}

	@Override
	public List<User> getUsers(int page, int limit) throws DAOException {
		Connection connection = null;
		try {
			connection = pool.getConnection();
			int offset = (page - 1) * limit;

			return JDBCUtil.select(connection, SQLQuery.ALL_USERS, resultSetHandlerUsers, limit, offset);

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(ErrorMessage.DATABASE_ERROR, e);

		} finally {
			freeConnection(connection);
		}
	}

	@Override
	public int countUsers() throws DAOException {
		return getCount(SQLQuery.COUNT_ALL_USERS);
	}

	@Override
	public void toggleBlackListInUsers(int userId, boolean b) throws DAOException {
		Connection connection = null;
		try {
			connection = pool.getConnection();

			JDBCUtil.insertDeleteUpdate(connection, SQLQuery.UPDATE_USERS_BLACK_LIST, b, userId);

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			freeConnection(connection);
		}

	}

	@Override
	public List<User> getUsersByRole(int page, int limit, int roleId) throws DAOException {
		Connection connection = null;
		try {
			connection = pool.getConnection();
			int offset = (page - 1) * limit;

			return JDBCUtil.select(connection, SQLQuery.USERS_BY_ROLE, resultSetHandlerUsers, roleId, limit, offset);

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(ErrorMessage.DATABASE_ERROR, e);

		} finally {
			freeConnection(connection);
		}
	}

	@Override
	public int countUsersByRole(int roleId) throws DAOException {
		return getCount(SQLQuery.COUNT_USERS_BY_ROLE, roleId);

	}

	private int getCount(String sql, Object... parameters) throws DAOException {
		Connection connection = null;
		try {
			pool.initPoolData();
			connection = pool.getConnection();

			return JDBCUtil.select(connection, sql, countResultSetHandler, parameters);

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
				throw new DAOException(ErrorMessage.UNABLE_TO_FREE_CONNECTION);
			}
		}
	}
}
