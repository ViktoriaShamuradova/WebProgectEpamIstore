package by.epamtc.shamuradova.ishop.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.shamuradova.ishop.bean.entity.Model;
import by.epamtc.shamuradova.ishop.constant.PerPage;
import by.epamtc.shamuradova.ishop.controller.command.Command;
import by.epamtc.shamuradova.ishop.service.ModelService;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;
import by.epamtc.shamuradova.ishop.service.factory.ServiceFactory;


public class ShopperPageCommand implements Command {

	private static final int FIRST_PAGE = 1;
	private static final String ERROR_PAGE = "controller?command=GET_ERROR_PAGE";
	private static final String MODELS_PARAM = "models";
	private static final String CATEGORY_PARAM = "categories";
	private static final String CURRENT_MESSAGE = "current_message";
	
	private ModelService modelService = ServiceFactory.getInstance().getModelService();

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Model> models;
		
		try {
			HttpSession session =  req.getSession();
			
			String message = (String) session.getAttribute(CURRENT_MESSAGE);
			session.removeAttribute(CURRENT_MESSAGE);
			models = modelService.listAllModels(FIRST_PAGE, PerPage.MODELS_ON_PAGE);
			

			req.setAttribute(MODELS_PARAM, models);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/shopper_page.jsp");
			dispatcher.forward(req, resp);
		}

		catch (ServiceException e) {
			e.printStackTrace();
			resp.sendRedirect(ERROR_PAGE);
		}

	}

}
