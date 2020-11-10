package by.epamtc.shamuradova.ishop.dao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import by.epamtc.shamuradova.ishop.dao.exception.ConnectionPoolException;
import by.epamtc.shamuradova.ishop.dao.pool.ConnectionPool;

public class TestConnectionPool extends ConnectionPool {

	@Override
	public Connection getConnection() throws ConnectionPoolException {
		try {
			ResourceProperty resProperty = new ResourceProperty("resources.db");
			String driverName = resProperty.getValue("db.driver");
			String url = resProperty.getValue("db.url");
			String user = resProperty.getValue("db.user");
			String password = resProperty.getValue("db.password");

			Class.forName(driverName);

			return DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void free(Connection connection) throws ConnectionPoolException {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
