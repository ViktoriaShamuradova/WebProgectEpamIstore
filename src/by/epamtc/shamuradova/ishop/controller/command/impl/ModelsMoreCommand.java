package by.epamtc.shamuradova.ishop.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.shamuradova.ishop.bean.Category;
import by.epamtc.shamuradova.ishop.bean.Model;
import by.epamtc.shamuradova.ishop.constant.PerPage;
import by.epamtc.shamuradova.ishop.controller.command.Command;
import by.epamtc.shamuradova.ishop.service.ModelService;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;
import by.epamtc.shamuradova.ishop.service.impl.ModelServiceImpl;

public class ModelsMoreCommand implements Command {

	private static final String MODELS_PARAM = "models";
	private static final String ERROR_PAGE = "controller?command=GET_ERROR_PAGE";

	private ModelService modelService;

	public ModelsMoreCommand() {
		modelService = new ModelServiceImpl();
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String category=req.getParameter("category");
			int pageNumber = Integer.parseInt(req.getParameter("pageNumber"));
			int pageCount = Integer.parseInt(req.getParameter("pageCount"));
			
			List<Model> models = getModels(category, pageNumber );
			
			List<Category> categories = modelService.listAllCategories();

			req.setAttribute(MODELS_PARAM, models);
			req.setAttribute("categories", categories);
			req.setAttribute("pageNumber", pageNumber);
			req.setAttribute("pageCount", pageCount);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/main.jsp");
			dispatcher.forward(req, resp);
		} catch (ServiceException e) {
			e.printStackTrace();
			resp.sendRedirect(ERROR_PAGE);
		}

	}

	private List<Model> getModels(String category, int pageNumber) throws ServiceException {
		if (category == null || category.isEmpty()) {
			return modelService.listAllModels(pageNumber, PerPage.MODELS_ON_PAGE);
		}

		return modelService.listModelsByCategory(category, pageNumber, PerPage.MODELS_ON_PAGE);
	}

}
