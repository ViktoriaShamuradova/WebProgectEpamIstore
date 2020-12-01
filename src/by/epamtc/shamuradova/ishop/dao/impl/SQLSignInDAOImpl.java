package by.epamtc.shamuradova.ishop.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtc.shamuradova.ishop.bean.AuthData;
import by.epamtc.shamuradova.ishop.bean.entity.User;
import by.epamtc.shamuradova.ishop.constant.SQLQuery;
import by.epamtc.shamuradova.ishop.dao.SignInDAO;
import by.epamtc.shamuradova.ishop.dao.exception.ConnectionPoolException;
import by.epamtc.shamuradova.ishop.dao.exception.DAOException;
import by.epamtc.shamuradova.ishop.dao.handler.impl.ResultSetHandlerFactory;
import by.epamtc.shamuradova.ishop.dao.pool.ConnectionPool;
import by.epamtc.shamuradova.ishop.dao.util.JDBCUtil;

/**
 * Класс реализующий интерфейс SignInDAO для проверки данных пользователя в базе
 * данных и авторизации пользователя
 * 
 * Class for checking user data in database and signing in user
 * 
 * @author Victoria Shamuradova 2020
 */
public class SQLSignInDAOImpl implements SignInDAO {

	private ConnectionPool pool;
	private static final Logger logger = LogManager.getLogger(SQLCartDAOImpl.class);
	
	private static final String CONNECTION_ERROR = "Database error! Unable to free connection.";
	private static final String OTHER_DATABASE_ERROR = "Database error! Could not sign in.";

	public SQLSignInDAOImpl() {
		pool = ConnectionPool.getInstance();
	}

	@Override
	public User signIn(AuthData data) throws DAOException {
		Connection connection = null;

		try {
			connection = pool.getConnection();
			String sql = SQLQuery.SIGN_IN;

			User user = JDBCUtil.select(connection, sql,
					ResultSetHandlerFactory.getSingleResultSetHandler(ResultSetHandlerFactory.USER_RESULT_SET_HANDLER),
					data.getLogin(), String.valueOf(data.getPassword()));
			data.deletePassword();
			return user;
		} catch (ConnectionPoolException | SQLException e) {
			logger.error(OTHER_DATABASE_ERROR, e);
			throw new DAOException(e);	
		} finally {
			if (connection != null) {
				try {
					pool.free(connection);
				} catch (ConnectionPoolException e) {
					logger.error(CONNECTION_ERROR, e);
					throw new DAOException(e);		
				}
			}
		}
	}
}
