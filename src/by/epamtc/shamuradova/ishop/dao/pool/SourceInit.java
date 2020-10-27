package by.epamtc.shamuradova.ishop.dao.pool;

import java.io.IOException;

import by.epamtc.shamuradova.ishop.dao.exception.ConnectionPoolException;

/**
 * Класс инициализации пула соединений
 * 
 * Connection pool initializing class
 * 
 * @author Шамурадова Виктория, 2020
 */
public class SourceInit {

	private static final SourceInit INSTANCE = new SourceInit();
	
	private SourceInit() {}

	public static SourceInit getInstance(){
		return INSTANCE;
	}
	
	public void init() throws ConnectionPoolException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		connectionPool.initPoolData();
	}
	
	public void destroy() throws IOException, ConnectionPoolException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		connectionPool.close();
	}
}
