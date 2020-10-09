package by.epamtc.shamuradova.ishop.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.epamtc.shamuradova.ishop.bean.RegInfo;
import by.epamtc.shamuradova.ishop.constant.ErrorMessage;
import by.epamtc.shamuradova.ishop.constant.SQLQuery;
import by.epamtc.shamuradova.ishop.constant.UserRole;
import by.epamtc.shamuradova.ishop.constant.UserStatus;
import by.epamtc.shamuradova.ishop.dao.SignUpDAO;
import by.epamtc.shamuradova.ishop.dao.exception.ConnectionPoolException;
import by.epamtc.shamuradova.ishop.dao.exception.DAOException;
import by.epamtc.shamuradova.ishop.dao.handler.ResultSetHandler;
import by.epamtc.shamuradova.ishop.dao.handler.impl.ResultSetHandlerUser;
import by.epamtc.shamuradova.ishop.dao.pool.ConnectionPool;
import by.epamtc.shamuradova.ishop.dao.util.JDBCUtil;

/**
 * Класс для проверки данных пользователя в базе данных и регистрации
 * пользователя
 * 
 * Class for checking user data in database and signing up user
 * 
 * @author Шамурадова Виктория 2020
 */

public class SignUpImplDAO implements SignUpDAO {

	public SignUpImplDAO() {
	}

	@Override
	public void signUp(RegInfo regInfo) throws DAOException {
		ResultSetHandler resultSetHandlerUser = new ResultSetHandlerUser();
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = null;

		try {
			pool.initPoolData();
			connection = pool.getConnection();

			if (!checkLoginInBase(regInfo.getLogin(), connection)) {
				String sql = SQLQuery.SIGN_UP;
				int statusId = UserStatus.statusesId.get(regInfo.getStatus().toUpperCase());
				int roleId = UserRole.rolesId.get(regInfo.getRole().toUpperCase());
				String password = new String(regInfo.getPassword());
				JDBCUtil.insert(connection, sql, regInfo.getName(), regInfo.getSurname(), regInfo.getLogin(), password,
						regInfo.getEmail(), statusId, roleId);
				password = null;
				regInfo.deletePassword();

			} else {
				throw new DAOException(ErrorMessage.USER_LOGIN_IS_ALREADY_EXISTS + " " + regInfo.getLogin());
			}

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {

			if (connection != null) {
				try {
					pool.free(connection);
				} catch (ConnectionPoolException e) {
					throw new DAOException(e);
				}
			}
		}
	}

	private boolean checkLoginInBase(String login, Connection connection) throws DAOException {
		String sql = SQLQuery.CHECK_LOGIN;
		ResultSet results = null;
		try {
			results = JDBCUtil.call(connection, sql, login);

			if (results.next()) {
				String answer = results.getString(1);
				if (answer.equals("yes"))
					return true;
				else
					return false;
			}
			return false;
		} catch (SQLException e) {
			throw new DAOException(ErrorMessage.DATABASE_ERROR, e);
		} finally {
			if (results != null) {
				try {
					results.close();
				} catch (SQLException e) {
					throw new DAOException(ErrorMessage.UNABLE_TO_CLOSE_RESULTSET, e);
				}
			}
		}
	}

}
