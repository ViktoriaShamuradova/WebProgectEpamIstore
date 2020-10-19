package by.epamtc.shamuradova.ishop.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

import by.epamtc.shamuradova.ishop.bean.User;
import by.epamtc.shamuradova.ishop.constant.ErrorMessage;
import by.epamtc.shamuradova.ishop.constant.SQLQuery;
import by.epamtc.shamuradova.ishop.dao.UserDAO;
import by.epamtc.shamuradova.ishop.dao.exception.ConnectionPoolException;
import by.epamtc.shamuradova.ishop.dao.exception.DAOException;
import by.epamtc.shamuradova.ishop.dao.handler.ResultSetHandler;
import by.epamtc.shamuradova.ishop.dao.handler.impl.ResultSetHandlerUser;
import by.epamtc.shamuradova.ishop.dao.pool.ConnectionPool;
import by.epamtc.shamuradova.ishop.dao.util.JDBCUtil;

public class UserDAOImpl implements UserDAO {

	@Override
	public User getUserByLogin(String login) throws DAOException {
		ResultSetHandler resultSetHandlerUser = new ResultSetHandlerUser();
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = null;
		User user;
		try {
			pool.initPoolData();
			connection = pool.getConnection();
			String sql = SQLQuery.USER_BY_LOGIN;

			user = (User) JDBCUtil.selectSingle(connection, sql, resultSetHandlerUser, login);
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
	public static void main(String[] args) throws DAOException {
		String login = "TestTest55";
		UserDAOImpl user = new UserDAOImpl();
		User u = user.getUserByLogin(login);
		System.out.println(Arrays.toString(u.getPassword()) + " "+ u.getId());
		
	}

}
