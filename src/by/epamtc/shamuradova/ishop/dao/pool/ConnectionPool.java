package by.epamtc.shamuradova.ishop.dao.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import by.epamtc.shamuradova.ishop.constant.ErrorMessage;
import by.epamtc.shamuradova.ishop.dao.exception.ConnectionPoolException;
import by.epamtc.shamuradova.ishop.dao.util.DBParameter;
import by.epamtc.shamuradova.ishop.dao.util.ResourceProperty;

public class ConnectionPool {

	private BlockingQueue<Connection> freeConnections;
	private BlockingQueue<Connection> busyConnections;

	private int poolSize;

	private static final String PROPERTY = "resources.db";

	private final static ConnectionPool instance = new ConnectionPool();

	private ConnectionPool() {
	}

	public static ConnectionPool getInstance() {
		return instance;
	}

	public void initPoolData() throws ConnectionPoolException {

		ResourceProperty resProperty = new ResourceProperty(PROPERTY);
		String driverName = resProperty.getValue(DBParameter.DB_DRIVER);
		String url = resProperty.getValue(DBParameter.DB_URL);
		String user = resProperty.getValue(DBParameter.DB_USER);
		String password = resProperty.getValue(DBParameter.DB_PASSWORD);

		try {
			poolSize = Integer.parseInt(resProperty.getValue(DBParameter.DB_POLL_SIZE));
		} catch (NumberFormatException e) {
			poolSize = 5;
		}

		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			throw new ConnectionPoolException(ErrorMessage.DRIVER_LOAD_ERROR, e);
		}

		freeConnections = new ArrayBlockingQueue<Connection>(poolSize);
		busyConnections = new ArrayBlockingQueue<Connection>(poolSize);

		addConnectionsInFreeConnections(url, user, password);

	}

	public Connection getConnection() throws ConnectionPoolException {
		Connection connection = null;
		try {
			connection = freeConnections.take();
			busyConnections.put(connection);
		} catch (InterruptedException e) {
			throw new ConnectionPoolException(ErrorMessage.CONNECTION_ERROR, e);
		}
		return connection;

	}

	public void free(Connection connection) throws ConnectionPoolException {
		busyConnections.remove(connection);
		try {
			freeConnections.put(connection);
		} catch (InterruptedException e) {
			throw new ConnectionPoolException(ErrorMessage.CONNECTION_ERROR, e);
		}
	}

	public void close() throws ConnectionPoolException {
		clearBusyConnections();
		clearFreeConnections();

	}

	private void addConnectionsInFreeConnections(String url, String user, String password)
			throws ConnectionPoolException {
		try {
			for (int i = 0; i < poolSize; i++) {
				Connection conn = DriverManager.getConnection(url, user, password);
				freeConnections.add(conn);
			}
		} catch (SQLException e) {
			throw new ConnectionPoolException(ErrorMessage.DATABASE_ERROR, e);
		}

	}

	private void clearBusyConnections() throws ConnectionPoolException {
		if (busyConnections.size() != 0) {
			for (int i = 0; i < busyConnections.size(); i++) {
				try {
					Connection connection = busyConnections.take();
					if (connection.isClosed() != true) {
						connection.close();
					}
				} catch (InterruptedException e) {
					throw new ConnectionPoolException(ErrorMessage.UNABLE_TO_CLOSE_CONNECTION, e);
				} catch (SQLException e) {
					throw new ConnectionPoolException(ErrorMessage.DATABASE_ERROR, e);
				}
			}
		}
	}

	private void clearFreeConnections() throws ConnectionPoolException {
		if (freeConnections.size() != 0) {
			for (int i = 0; i < freeConnections.size(); i++) {
				try {
					Connection connection = freeConnections.take();
					if (connection.isClosed() != true) {
						connection.close();
					}
				} catch (InterruptedException e) {
					throw new ConnectionPoolException(ErrorMessage.UNABLE_TO_CLOSE_CONNECTION, e);
				} catch (SQLException e) {
					throw new ConnectionPoolException(ErrorMessage.DATABASE_ERROR, e);
				}
			}
		}
	}

	public static void main(String[] args) throws ConnectionPoolException, SQLException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		connectionPool.initPoolData();
		Connection connection = connectionPool.getConnection();
		Statement state = connection.createStatement();

		// 3. Execute query
		ResultSet res = state.executeQuery("select * from users");

		// 4. Process the result set
		while (res.next()) {
			System.out.println(res.getString("name") + ", " + res.getNString("email"));
		}
	}

}
