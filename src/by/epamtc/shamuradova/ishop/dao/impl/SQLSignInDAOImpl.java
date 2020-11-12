package by.epamtc.shamuradova.ishop.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import by.epamtc.shamuradova.ishop.bean.AuthData;
import by.epamtc.shamuradova.ishop.bean.entity.User;
import by.epamtc.shamuradova.ishop.constant.ErrorMessage;
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
 * @author Шамурадова Виктория 2020
 */
public class SQLSignInDAOImpl implements SignInDAO {

	private ConnectionPool pool;

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
	}
}
