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
import by.epamtc.shamuradova.ishop.service.impl.ModelServiceImpl;

public class ModelsByCategoryCommand implements Command {

	private static final int FIRST_PAGE = 1;
	private static final String ERROR_PAGE = "controller?command=GET_ERROR_PAGE";
	private static final String MODELS_PARAM = "models";
	private static final String CURRENT_CATEGORY_URL_PARAM = "category";

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String category = req.getParameter("category");

		ModelService modelService = new ModelServiceImpl();
		List<Model> models;
		try {

			models = modelService.listModelsByCategory(category, FIRST_PAGE, ModelConstant.MAX_COUNT_MODELS_ON_PAGE);
			req.setAttribute(MODELS_PARAM, models);
			req.setAttribute(CURRENT_CATEGORY_URL_PARAM, category);//сохранить текущую выбранную категорию, чтобы подсветить, чтобы загружать далее по категориям
			RequestDispatcher dispatcher = req.getRequestDispatcher("/main.jsp");
			dispatcher.forward(req, resp);
		} catch (ServiceException e) {
			e.printStackTrace();
			resp.sendRedirect(ERROR_PAGE);
		}

	}

	public static void main(String[] args) throws ServiceException {

		ModelService modelService = new ModelServiceImpl();
		List<Model> models = modelService.listModelsByCategory("watch", 1, 5);
		System.out.println(models);
	}
}
