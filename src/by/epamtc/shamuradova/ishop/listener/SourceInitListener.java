package by.epamtc.shamuradova.ishop.listener;

import java.io.IOException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import by.epamtc.shamuradova.ishop.dao.exception.ConnectionPoolException;
import by.epamtc.shamuradova.ishop.dao.pool.SourceInit;

/**
 * Слушатель контекста сервлета. Инициализирует пул соединений, при создании
 * контекста и закрывает пул соединений при закрытии контекста.
 * 
 * Servlet context listener. Initializes connection pool when servlet context is
 * created and destroys connection pool when servlet context is destroys.
 * 
 * @author Шамурадова Виктория, 2020
 */
public class SourceInitListener implements ServletContextListener {

	public SourceInitListener() {}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		SourceInit sourceInit = SourceInit.getInstance();
		try {
			sourceInit.destroy();
		} catch (ConnectionPoolException | IOException e) {
			// здесь дб логгер и что-то сделать с искл
			e.printStackTrace();
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		SourceInit sourceInit = SourceInit.getInstance();
		try {
			sourceInit.init();
		} catch (ConnectionPoolException e) {
		}
	}

}
