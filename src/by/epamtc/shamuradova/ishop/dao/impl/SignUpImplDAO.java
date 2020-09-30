package by.epamtc.shamuradova.ishop.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.epamtc.shamuradova.ishop.bean.RegInfo;
import by.epamtc.shamuradova.ishop.constant.ErrorMessage;
import by.epamtc.shamuradova.ishop.constant.SQLQuery;
import by.epamtc.shamuradova.ishop.dao.SignUpDAO;
import by.epamtc.shamuradova.ishop.dao.exception.ConnectionPoolException;
import by.epamtc.shamuradova.ishop.dao.exception.DAOException;
import by.epamtc.shamuradova.ishop.dao.pool.ConnectionPool;

/**
 * Класс для проверки данных пользователя в базе данных и регистрации
 * пользователя
 * 
 * Class for checking user data in database and signing up user
 * 
 * @author Шамурадова Виктория 2020
 */

public class SignUpImplDAO implements SignUpDAO {

	@Override
	public void signUp(RegInfo regInfo) throws DAOException {

		ConnectionPool connectionPool = ConnectionPool.getInstance();
		PreparedStatement preparedStatement = null;
		Connection connection = null;

		try {
			connectionPool.initPoolData();
			connection = connectionPool.getConnection();

			if (!checkLoginInBase(regInfo.getLogin(), connection)) {
				preparedStatement = connection.prepareStatement(SQLQuery.SIGN_UP);
				preparedStatement.setString(1, regInfo.getName());
				preparedStatement.setString(2, regInfo.getSurname());
				preparedStatement.setString(3, regInfo.getLogin());
				String password = new String(regInfo.getPassword());
				regInfo.deletePassword();
				preparedStatement.setString(4, password);
				password = null;
				preparedStatement.setString(5, regInfo.getEmail());
				preparedStatement.setInt(6, regInfo.getIdUserStatus());
				preparedStatement.setInt(7, regInfo.getIdUserRole());;
				preparedStatement.executeUpdate();

			} else {
				throw new DAOException(ErrorMessage.USER_LOGIN_IS_ALREADY_EXISTS + " " + regInfo.getLogin());
			}

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {

			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					throw new DAOException(ErrorMessage.UNABLE_TO_CLOSE_STATEMENT, e);
				}
			}
			if (connection != null) {
				try {
					connectionPool.free(connection);
				} catch (ConnectionPoolException e) {
					throw new DAOException(e);
				}
			}
		}

	}

	private boolean checkLoginInBase(String login, Connection connection) throws DAOException {
		CallableStatement callableStatement = null;
		try {
			callableStatement = connection.prepareCall(SQLQuery.CHECK_LOGIN);
			callableStatement.setString(1, login);
			ResultSet results = callableStatement.executeQuery();
			if (results.next()) {
				String answer = results.getString(1);
				if (answer.equals("yes"))
					return true;
				else
					return false;
			}
			return false;
		} catch (SQLException e) {
			throw new DAOException(ErrorMessage.DATABASE_ERROR);
		} finally {
			try {
				if (callableStatement != null && !callableStatement.isClosed()) {
					callableStatement.close();
				}
			} catch (SQLException e) {
				throw new DAOException(ErrorMessage.DATABASE_ERROR);
			}
		}
	}
}
