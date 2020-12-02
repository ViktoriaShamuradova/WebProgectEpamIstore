package by.epamtc.shamuradova.ishop.dao.pool;


/**
 * Класс инициализации пула соединений
 * 
 * Connection pool initializing class
 * 
 * @author Victoria Shamuradova  2020
 */
public class SourceInit {

	private static final SourceInit INSTANCE = new SourceInit();
	
	private SourceInit() {}

	public static SourceInit getInstance(){
		return INSTANCE;
	}
	
	public void init()  {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		connectionPool.initPoolData();
	}
	
	public void destroy()  {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		connectionPool.close();
	}
}
