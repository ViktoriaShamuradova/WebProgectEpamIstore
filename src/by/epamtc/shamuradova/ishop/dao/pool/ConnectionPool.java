package by.epamtc.shamuradova.ishop.dao.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtc.shamuradova.ishop.dao.exception.ConnectionPoolException;
import by.epamtc.shamuradova.ishop.dao.util.DBParameter;
import by.epamtc.shamuradova.ishop.dao.util.ResourceProperty;

/**
 * Объект этого класса представляет собой хранилище соединений с базой данных
 * 
 * An object of this class is a repository of database connections
 * 
 * @author Victoria Shamuradova 2020
 */

public class ConnectionPool {

	private static final Logger logger = LogManager.getLogger(ConnectionPool.class);

	private final static ConnectionPool instance = new ConnectionPool();

	/**
	 * Располагаются объекты Connection готовые к использованию Connection objects
	 * are located ready to use
	 */
	private BlockingQueue<Connection> freeConnections;

	/**
	 * Хранятся Connection, которые уже отданы на использование Connections that
	 * have already been given are stored
	 */
	private BlockingQueue<Connection> busyConnections;

	private int poolSize;

	private static final String PROPERTY = "resources.db";

	private static final String ERROR_FIND_DRIVER = "Could not find database driver class";
	private static final String ERROR_CLOSE_CONNECTION = "Could not close connection";
	private static final String ERROR_INTERRUPTED = "An interruption occurred";

	protected ConnectionPool() {
		initPoolData();
	}

	public static ConnectionPool getInstance() {
		return instance;
	}

	public void initPoolData() throws ConnectionPoolException {
		try {
			ResourceProperty resProperty = new ResourceProperty(PROPERTY);
			String driverName = resProperty.getValue(DBParameter.DB_DRIVER);
			String url = resProperty.getValue(DBParameter.DB_URL);
			String user = resProperty.getValue(DBParameter.DB_USER);
			String password = resProperty.getValue(DBParameter.DB_PASSWORD);

			poolSize = Integer.parseInt(resProperty.getValue(DBParameter.DB_POLL_SIZE));

			Class.forName(driverName);// загрузка драйвер

			freeConnections = new ArrayBlockingQueue<Connection>(poolSize);
			busyConnections = new ArrayBlockingQueue<Connection>(poolSize);

			addConnectionsInFreeConnections(url, user, password);
		} catch (ClassNotFoundException e) {
			logger.error(ERROR_FIND_DRIVER, e);
			throw new ConnectionPoolException(ERROR_FIND_DRIVER, e);
		}
	}

	public Connection getConnection() throws ConnectionPoolException {
		try {
			Connection connection = freeConnections.take();
			busyConnections.put(connection);
			return connection;
		} catch (InterruptedException e) {
			logger.error(ERROR_INTERRUPTED, e);
			throw new ConnectionPoolException(ERROR_INTERRUPTED, e);
		}
	}

	public void free(Connection connection) throws ConnectionPoolException {
		try {
			busyConnections.remove(connection);
			freeConnections.put(connection);
		} catch (InterruptedException e) {
			logger.error(ERROR_INTERRUPTED, e);
			throw new ConnectionPoolException(ERROR_INTERRUPTED, e);
		}
	}

	public void close() throws ConnectionPoolException {
		clearFreeConnections();
		clearBusyConnections();
	}

	private void addConnectionsInFreeConnections(String url, String user, String password)
			throws ConnectionPoolException {
		try {
			for (int i = 0; i < poolSize; i++) {
				Connection conn = DriverManager.getConnection(url, user, password);
				freeConnections.add(conn);
			}
		} catch (SQLException e) {
			logger.error(ERROR_FIND_DRIVER, e);
			throw new ConnectionPoolException(e);
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
					logger.error(ERROR_INTERRUPTED + " " + ERROR_CLOSE_CONNECTION, e);
					throw new ConnectionPoolException(e);
				} catch (SQLException e) {
					throw new ConnectionPoolException(ERROR_CLOSE_CONNECTION, e);
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
					logger.error(ERROR_INTERRUPTED + " " + ERROR_CLOSE_CONNECTION, e);
					throw new ConnectionPoolException(ERROR_INTERRUPTED + " " + ERROR_CLOSE_CONNECTION, e);
				} catch (SQLException e) {
					logger.error(ERROR_CLOSE_CONNECTION, e);
					throw new ConnectionPoolException(ERROR_CLOSE_CONNECTION, e);
				}
			}
		}
	}
}
