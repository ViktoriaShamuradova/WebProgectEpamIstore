package by.epamtc.shamuradova.ishop.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

import by.epamtc.shamuradova.ishop.bean.AuthData;
import by.epamtc.shamuradova.ishop.bean.User;
import by.epamtc.shamuradova.ishop.constant.ErrorMessage;
import by.epamtc.shamuradova.ishop.constant.SQLQuery;
import by.epamtc.shamuradova.ishop.dao.SignInDAO;
import by.epamtc.shamuradova.ishop.dao.exception.ConnectionPoolException;
import by.epamtc.shamuradova.ishop.dao.exception.DAOException;
import by.epamtc.shamuradova.ishop.dao.handler.ResultSetHandler2;
import by.epamtc.shamuradova.ishop.dao.handler.impl.ResultSetHandlerFactory;
import by.epamtc.shamuradova.ishop.dao.handler.impl.ResultSetHandlerUser2;
import by.epamtc.shamuradova.ishop.dao.pool.ConnectionPool;
import by.epamtc.shamuradova.ishop.dao.util.JDBCUtil;

//работает!!!!!
public class SignInDAOImpl2 implements SignInDAO {
	
	
	private ResultSetHandler2<User> resultSetHandlerUser;
	private ConnectionPool pool;
	
	public SignInDAOImpl2() {
		pool= ConnectionPool.getInstance();
		resultSetHandlerUser = ResultSetHandlerFactory.getSingleResultSetHandler(new ResultSetHandlerUser2());
	}

	@Override
	public User signIn(AuthData data) throws DAOException {
		Connection connection = null;
		
		try {
			connection = pool.getConnection();
			String sql = SQLQuery.SIGN_IN;
			
			User user = JDBCUtil.select(connection, sql, resultSetHandlerUser, data.getLogin(), String.valueOf(data.getPassword()));
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
	public static void main(String[] args) {
		
		char[] pass = new char[] {'a','b','c'};
		System.out.println(String.valueOf(pass));
		
		
	}
}
