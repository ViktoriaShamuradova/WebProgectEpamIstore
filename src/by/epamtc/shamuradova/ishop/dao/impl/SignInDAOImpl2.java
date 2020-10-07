package by.epamtc.shamuradova.ishop.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import by.epamtc.shamuradova.ishop.bean.AuthData;
import by.epamtc.shamuradova.ishop.bean.User;
import by.epamtc.shamuradova.ishop.constant.ErrorMessage;
import by.epamtc.shamuradova.ishop.constant.SQLQuery;
import by.epamtc.shamuradova.ishop.dao.SignInDAO;
import by.epamtc.shamuradova.ishop.dao.exception.ConnectionPoolException;
import by.epamtc.shamuradova.ishop.dao.exception.DAOException;
import by.epamtc.shamuradova.ishop.dao.handler.ResultSetHandler;
import by.epamtc.shamuradova.ishop.dao.handler.impl.ResultSetHandlerUser;
import by.epamtc.shamuradova.ishop.dao.pool.ConnectionPool;
import by.epamtc.shamuradova.ishop.dao.util.JDBCUtil;

//работает!!!!!
public class SignInDAOImpl2 implements SignInDAO {
	
	
	private ResultSetHandler resultSetHandlerUser;
	
	public SignInDAOImpl2() {
		resultSetHandlerUser = new ResultSetHandlerUser();
	}

	@Override
	public User signIn(AuthData data) throws DAOException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = null;
		User user;
		try {
			pool.initPoolData();
			connection = pool.getConnection();
			String sql = SQLQuery.SIGN_IN;
			
			user = (User)JDBCUtil.selectSingle(connection, sql, resultSetHandlerUser, data.getLogin());
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

		return user;
		
	}
	public static void main(String[] args) throws ConnectionPoolException, SQLException, DAOException {
		String pass = new String("1234567a");
		char[] password = pass.toCharArray();
		AuthData data = new AuthData("ViktoriaShamuradova", password);
		SignInDAOImpl2 dao = new SignInDAOImpl2();
		System.out.println(dao.signIn(data));

	}

}
