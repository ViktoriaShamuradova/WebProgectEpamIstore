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
import by.epamtc.shamuradova.ishop.dao.handler.ResultSetHandler2;
import by.epamtc.shamuradova.ishop.dao.handler.impl.ResultSetHandlerFactory;
import by.epamtc.shamuradova.ishop.dao.handler.impl.ResultSetHandlerUser;
import by.epamtc.shamuradova.ishop.dao.handler.impl.ResultSetHandlerUser2;
import by.epamtc.shamuradova.ishop.dao.pool.ConnectionPool;
import by.epamtc.shamuradova.ishop.dao.util.JDBCUtil;

public class UserDAOImpl implements UserDAO {

	private ConnectionPool pool;
	private ResultSetHandler2<User> resultSetHandlerUser;
	
	public UserDAOImpl() {
		pool= ConnectionPool.getInstance();
		resultSetHandlerUser = ResultSetHandlerFactory.getSingleResultSetHandler(new ResultSetHandlerUser2());
		
		
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
