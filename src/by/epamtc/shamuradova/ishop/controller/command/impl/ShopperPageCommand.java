package by.epamtc.shamuradova.ishop.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.shamuradova.ishop.bean.Model;
import by.epamtc.shamuradova.ishop.constant.ModelConstant;
import by.epamtc.shamuradova.ishop.controller.command.Command;
import by.epamtc.shamuradova.ishop.service.ModelService;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;
import by.epamtc.shamuradova.ishop.service.factory.ServiceFactory;


public class ShopperPageCommand implements Command {

	private static final int FIRST_PAGE = 1;
	private static final String ERROR_PAGE = "controller?command=GET_ERROR_PAGE";
	private static final String MODELS_PARAM = "models";
	private static final String CATEGORY_PARAM = "categories";
	private ModelService modelService = ServiceFactory.getInstance().getModelService();

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Model> models;
		
		try {
			models = modelService.listAllModels(FIRST_PAGE, ModelConstant.MAX_COUNT_MODELS_ON_PAGE);
			

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
