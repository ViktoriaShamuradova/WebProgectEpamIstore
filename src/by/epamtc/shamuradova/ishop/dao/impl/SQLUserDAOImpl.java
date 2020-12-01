package by.epamtc.shamuradova.ishop.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtc.shamuradova.ishop.bean.entity.User;
import by.epamtc.shamuradova.ishop.constant.SQLQuery;
import by.epamtc.shamuradova.ishop.dao.UserDAO;
import by.epamtc.shamuradova.ishop.dao.exception.ConnectionPoolException;
import by.epamtc.shamuradova.ishop.dao.exception.DAOException;
import by.epamtc.shamuradova.ishop.dao.handler.impl.ResultSetHandlerFactory;
import by.epamtc.shamuradova.ishop.dao.pool.ConnectionPool;
import by.epamtc.shamuradova.ishop.dao.util.JDBCUtil;

/**
 * Класс, реализующий интерфейс UserDAO, использующий sql- запросы
 * 
 * Class that implements the UserDAO interface using sql queries
 *
 * @author Victoria Shamuradova 2020
 */
public class SQLUserDAOImpl implements UserDAO {

	private ConnectionPool pool;
	private static final Logger logger = LogManager.getLogger(SQLUserDAOImpl.class);

	public SQLUserDAOImpl() {
		pool = ConnectionPool.getInstance();
	}

	@Override
	public User getUserByLogin(String login) throws DAOException {
		Connection connection = null;
		try {
			connection = pool.getConnection();
			String sql = SQLQuery.USER_BY_LOGIN;

			return JDBCUtil.select(connection, sql,
					ResultSetHandlerFactory.getSingleResultSetHandler(ResultSetHandlerFactory.USER_RESULT_SET_HANDLER),
					login);
		} catch (ConnectionPoolException | SQLException e) {
			logger.error("Database error! Could not get User.", e);
			throw new DAOException("Could not get User.", e);
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

			return JDBCUtil.select(connection, sql,
					ResultSetHandlerFactory.getSingleResultSetHandler(ResultSetHandlerFactory.USER_RESULT_SET_HANDLER),
					userId);

		} catch (ConnectionPoolException | SQLException e) {
			logger.error("Database error! Could not get User.", e);
			throw new DAOException("Could not get User.", e);
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

			return JDBCUtil.select(connection, sql,
					ResultSetHandlerFactory.getListResultSetHandler(ResultSetHandlerFactory.USER_RESULT_SET_HANDLER),
					limit, offset);
		} catch (ConnectionPoolException | SQLException e) {
			logger.error("Database error! Could not get List of User BlackList.", e);
			throw new DAOException("Could not get List of User BlackList.", e);

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

			return JDBCUtil.select(connection, SQLQuery.ALL_USERS,
					ResultSetHandlerFactory.getListResultSetHandler(ResultSetHandlerFactory.USER_RESULT_SET_HANDLER),
					limit, offset);
		} catch (ConnectionPoolException | SQLException e) {
			logger.error("Database error! Could not get List of User.", e);
			throw new DAOException("Could not get List of User.", e);
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
			logger.error("Database error! Could not toggle BlackList.", e);
			throw new DAOException("Could not toggle BlackList.", e);
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

			return JDBCUtil.select(connection, SQLQuery.USERS_BY_ROLE,
					ResultSetHandlerFactory.getListResultSetHandler(ResultSetHandlerFactory.USER_RESULT_SET_HANDLER),
					roleId, limit, offset);
		} catch (ConnectionPoolException | SQLException e) {
			logger.error("Database error! Could not get List of User.", e);
			throw new DAOException("Could not get List of User.", e);
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
			connection = pool.getConnection();

			return JDBCUtil.select(connection, sql, ResultSetHandlerFactory.COUNT_RESULT_SET_HANDLER, parameters);
		} catch (ConnectionPoolException | SQLException e) {
			logger.error("Database error! Could not get count of Users.", e);
			throw new DAOException("Could not get count of Users", e);
		} finally {
			freeConnection(connection);
		}
	}

	private void freeConnection(Connection connection) throws DAOException {
		if (connection != null) {
			try {
				pool.free(connection);
			} catch (ConnectionPoolException e) {
				logger.error("Database error! Unable to free connection", e);
				throw new DAOException(e);
			}
		}
	}
}
