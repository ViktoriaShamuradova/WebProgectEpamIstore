package by.epamtc.shamuradova.ishop.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.shamuradova.ishop.bean.entity.Category;
import by.epamtc.shamuradova.ishop.bean.entity.Model;
import by.epamtc.shamuradova.ishop.bean.entity.User;
import by.epamtc.shamuradova.ishop.constant.PerPage;
import by.epamtc.shamuradova.ishop.constant.UserRole;
import by.epamtc.shamuradova.ishop.controller.command.Command;
import by.epamtc.shamuradova.ishop.service.ModelService;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;
import by.epamtc.shamuradova.ishop.service.factory.ServiceFactory;

public class ModelsByCategoryCommand implements Command {

	private static final int FIRST_PAGE = 1;
	private static final String ERROR_PAGE = "controller?command=GET_ERROR_PAGE";
	private static final String MODELS_PARAM = "models";
	private static final String CURRENT_CATEGORY_URL_PARAM = "category";

	private static final String MAIN_PAGE = "/main.jsp";
	private static final String SHOPPER_PAGE = "/WEB-INF/jsp/shopper_page.jsp";

	private ModelService modelService;

	public ModelsByCategoryCommand() {
		modelService = ServiceFactory.getInstance().getModelService();
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			HttpSession session = req.getSession();
			User user = (User) session.getAttribute("user");
			if (user == null) {
				setCategoriesAndModels(req, resp);

				RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
				dispatcher.forward(req, resp);

			} else if (user.getRole().equals(UserRole.SHOPPER)) {
				setCategoriesAndModels(req, resp);

				RequestDispatcher dispatcher = req.getRequestDispatcher(SHOPPER_PAGE);
				dispatcher.forward(req, resp);
			}

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

	private int getTotalModelCount(String category) throws ServiceException {
		if (category == null || category.isEmpty()) {
			return modelService.countModels();
		}
		return modelService.countModelsByCategoryUrl(category);
	}

	private int getPageCount(int totalCount, int itemsPerCount) {
		return (int) Math.ceil((double) totalCount / itemsPerCount);
	}

	private void setCategoriesAndModels(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
		String category = req.getParameter("category");
		String pageNumberString = req.getParameter("pageNumber");
		int pageNumber = pageNumberString == null ? FIRST_PAGE : Integer.parseInt(pageNumberString);

		List<Model> models = getModels(category, pageNumber);
		int modelsCount = getTotalModelCount(category);

		List<Category> categories = modelService.listAllCategories();

		req.setAttribute("categories", categories);
		req.setAttribute(MODELS_PARAM, models);
		req.setAttribute(CURRENT_CATEGORY_URL_PARAM, category);// сохранить текущую выбранную категорию, чтобы
																// загружать далее по категориям
		req.setAttribute("pageCount", getPageCount(modelsCount, PerPage.MODELS_ON_PAGE));
		req.setAttribute("modelsPerPage", PerPage.MODELS_ON_PAGE);
		req.setAttribute("pageNumber", pageNumber);
		req.setAttribute("modelsCount", modelsCount);

	}

}
