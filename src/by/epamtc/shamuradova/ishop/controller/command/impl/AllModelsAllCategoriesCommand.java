package by.epamtc.shamuradova.ishop.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.shamuradova.ishop.bean.Category;
import by.epamtc.shamuradova.ishop.bean.Model;
import by.epamtc.shamuradova.ishop.constant.ModelConstant;
import by.epamtc.shamuradova.ishop.controller.command.Command;
import by.epamtc.shamuradova.ishop.service.ModelService;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;
import by.epamtc.shamuradova.ishop.service.factory.ServiceFactory;
import by.epamtc.shamuradova.ishop.service.impl.ModelServiceImpl;

public class AllModelsAllCategoriesCommand implements Command {

	private static final int FIRST_PAGE = 1;
	private static final String ERROR_PAGE = "controller?command=GET_ERROR_PAGE";
	private static final String MODELS_PARAM = "models";
	private static final String CATEGORY_PARAM = "categories";

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ModelService modelService = new ModelServiceImpl();
		List<Model> models;
		List<Category> categories;
		
		try {
			models = modelService.listAllModels(FIRST_PAGE, ModelConstant.MAX_COUNT_MODELS_ON_PAGE);
			categories= ServiceFactory.getInstance().getModelService().listAllCategories();
			
			req.setAttribute(MODELS_PARAM, models);
			req.setAttribute(CATEGORY_PARAM, categories);
			
			System.out.println(req.getQueryString());
			System.out.println(req.getRequestURI());
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/main.jsp"); 
			dispatcher.forward(req, resp);
		} 
		
		catch (ServiceException e) {
			e.printStackTrace();
			resp.sendRedirect(ERROR_PAGE);
		}

	}

}
