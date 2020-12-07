package by.epamtc.shamuradova.ishop.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import by.epamtc.shamuradova.ishop.dao.pool.SourceInit;

/**
 * Слушатель контекста сервлета. Инициализирует пул соединений,
 * при создании контекста и закрывает пул соединений при закрытии контекста.
 * 
 * Servlet context listener. Initializes connection pool when servlet context
 * is created and destroys connection pool when servlet context is destroys.
 * 
 * @author Victoria Shamuradova 2020
 */


public class SourceInitListener implements ServletContextListener{

	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		SourceInit sourceInit = SourceInit.getInstance();
		sourceInit.destroy();
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
	//коннекшен пул инициализирует себя сам в конструкторе
	}

}
