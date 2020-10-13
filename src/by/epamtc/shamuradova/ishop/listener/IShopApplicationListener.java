package by.epamtc.shamuradova.ishop.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

import by.epamtc.shamuradova.ishop.service.ModelService;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;
import by.epamtc.shamuradova.ishop.service.factory.ServiceFactory;

public class IShopApplicationListener implements ServletRequestListener{

	private ModelService modelService;
	private static final String REQ_CATEGORY_PARAM = "categories";
	private static final String REQ_PRODUCER_PARAM = "producers";
	
//когда запустили приложение при запуске автоматически будет выполнен запрос и эти листы сохранятся в оп и не будут обращаться к бд
// листнере может и конн пул инициализировать?
	@Override
	public void requestInitialized(ServletRequestEvent sr) {
		modelService = ServiceFactory.getInstance().getModelService();
		HttpServletRequest req = (HttpServletRequest) sr.getServletRequest();
		try {
			req.setAttribute(REQ_CATEGORY_PARAM, modelService.listAllCategories());
			req.setAttribute(REQ_PRODUCER_PARAM, modelService.listAllProducer());
			System.out.println("in listener");
		} catch (ServiceException e) {
			
		}
		
	}

@Override
public void requestDestroyed(ServletRequestEvent sre) {
	
}

	

}
