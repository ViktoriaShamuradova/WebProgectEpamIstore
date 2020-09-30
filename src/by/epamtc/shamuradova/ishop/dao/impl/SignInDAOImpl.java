package by.epamtc.shamuradova.ishop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.epamtc.shamuradova.ishop.bean.AuthData;
import by.epamtc.shamuradova.ishop.bean.User;
import by.epamtc.shamuradova.ishop.constant.DataBaseColumnName;
import by.epamtc.shamuradova.ishop.constant.ErrorMessage;
import by.epamtc.shamuradova.ishop.constant.SQLQuery;
import by.epamtc.shamuradova.ishop.dao.SignInDAO;
import by.epamtc.shamuradova.ishop.dao.exception.ConnectionPoolException;
import by.epamtc.shamuradova.ishop.dao.exception.DAOException;
import by.epamtc.shamuradova.ishop.dao.pool.ConnectionPool;

public class SignInDAOImpl implements SignInDAO {

	@Override
	public User signIn(AuthData data) throws DAOException {

		ConnectionPool connectionPool = ConnectionPool.getInstance();
		PreparedStatement preparedStatement = null;
		Connection connection = null;

		try {
			connectionPool.initPoolData();
			connection = connectionPool.getConnection();

			preparedStatement = connection.prepareStatement(SQLQuery.SIGN_IN);
			preparedStatement.setString(1, data.getLogin());

			ResultSet resultSet = preparedStatement.executeQuery();
			User user = null;
			if (resultSet.next()) {
				if (data.getPassword().equals(resultSet.getString(DataBaseColumnName.PASSWORD))) {
					int id = resultSet.getInt(DataBaseColumnName.ID);
					String name = resultSet.getString(DataBaseColumnName.NAME);
					String surname = resultSet.getString(DataBaseColumnName.SURNAME);
					String email = resultSet.getString(DataBaseColumnName.EMAIL);
					int idUserStatus = resultSet.getInt(DataBaseColumnName.ID_USER_STATUS);
					int idUserRole = resultSet.getInt(DataBaseColumnName.ID_USER_ROLE);
					String login = data.getLogin();
					String password = new String(data.getPassword());
					user = new User(id, name, surname, login, password, email, idUserStatus, idUserRole);
					password=null;
					data.deletePassword();
					return user;
				} else {
					throw new DAOException(ErrorMessage.INCORRECT_PASSWORD_OR_LOGIN);
				}
			} else {
				throw new DAOException(ErrorMessage.INCORRECT_PASSWORD_OR_LOGIN);
			}

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {

			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					throw new DAOException(ErrorMessage.UNABLE_TO_CLOSE_STATEMENT);
				}
			}
			if (connection != null) {
				try {
					connectionPool.free(connection);
				} catch (ConnectionPoolException e) {
					throw new DAOException(ErrorMessage.UNABLE_TO_FREE_CONNECTION);
				}
			}
		}

	}

}
