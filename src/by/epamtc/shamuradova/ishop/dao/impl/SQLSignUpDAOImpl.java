package by.epamtc.shamuradova.ishop.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtc.shamuradova.ishop.bean.RegInfo;
import by.epamtc.shamuradova.ishop.constant.SQLQuery;
import by.epamtc.shamuradova.ishop.constant.UserRole;
import by.epamtc.shamuradova.ishop.constant.UserStatus;
import by.epamtc.shamuradova.ishop.dao.SignUpDAO;
import by.epamtc.shamuradova.ishop.dao.exception.ConnectionPoolException;
import by.epamtc.shamuradova.ishop.dao.exception.DAOException;
import by.epamtc.shamuradova.ishop.dao.pool.ConnectionPool;
import by.epamtc.shamuradova.ishop.dao.util.JDBCUtil;

/**
 * Класс, реализующий инерфейс SignUpDAO, для проверки данных пользователя в базе данных и регистрации
 * пользователя
 * 
 * Class for checking user data in database and signing up user
 * 
 * @author Victoria Shamuradova 2020
 */

public class SQLSignUpDAOImpl implements SignUpDAO {

	private ConnectionPool pool;
	private static final Logger logger = LogManager.getLogger(SQLCartDAOImpl.class);

	public SQLSignUpDAOImpl() {
		pool = ConnectionPool.getInstance();
	}

	@Override
	public void signUp(RegInfo regInfo) throws DAOException {

		Connection connection = null;
		try {
			connection = pool.getConnection();

			if (!checkLoginInBase(regInfo.getLogin(), connection)) {
				String sql = SQLQuery.SIGN_UP;
				int statusId = UserStatus.statusesId.get(regInfo.getStatus().toUpperCase());
				int roleId = UserRole.rolesId.get(regInfo.getRole().toUpperCase());
				String password = new String(regInfo.getPassword());
				JDBCUtil.insertDeleteUpdate(connection, sql, regInfo.getName(), regInfo.getSurname(),
						regInfo.getLogin(), password, regInfo.getEmail(), statusId, roleId);
				password = null;
				regInfo.deletePassword();

			} else {
				logger.error("User login is already exist");
				throw new DAOException("User login is already exist");
			}

		} catch (ConnectionPoolException | SQLException e) {
			logger.error("Database error! Could not to sign up.", e);
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
			logger.error("Database error!", e);
			throw new DAOException(e);
		} finally {
			if (results != null) {
				try {
					results.close();
				} catch (SQLException e) {
					logger.error("Database error! Unable to close ResultSet", e);
					throw new DAOException(e);
				}
			}
		}
	}
}
