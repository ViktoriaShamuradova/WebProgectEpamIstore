package by.epamtc.shamuradova.ishop.controller.command.impl;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.shamuradova.ishop.bean.entity.Category;
import by.epamtc.shamuradova.ishop.bean.entity.Model;
import by.epamtc.shamuradova.ishop.bean.entity.User;
import by.epamtc.shamuradova.ishop.constant.PerPage;
import by.epamtc.shamuradova.ishop.constant.RequestNameParameters;
import by.epamtc.shamuradova.ishop.constant.SessionNameParameters;
import by.epamtc.shamuradova.ishop.constant.UserRole;
import by.epamtc.shamuradova.ishop.controller.command.Command;
import by.epamtc.shamuradova.ishop.service.ModelService;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;
import by.epamtc.shamuradova.ishop.service.factory.ServiceFactory;

/**
 * Класс, который в зависимости от наличия или роли объекта User, переданный в
 * сессии, связываеся с соответствующим jsp объектом, передавая в объект типа
 * HttpServletRequest список моделей и категорий моделей
 * 
 * A class that, depending on the presence or role of the User object, passed to
 * session, connecting with the corresponding jsp object, passing it to an
 * object of type HttpServletRequest list of models and model categories
 * 
 * @author Victoria Shamuradova 2020
 */
public class ModelsByCategoryCommand implements Command {

	private static final int FIRST_PAGE = 1;

	private static final String ERROR_PAGE = "controller?command=GET_ERROR_PAGE";
	private static final String NAME_CURRENT_COMMAND = "controller?command=ALL_MODELS_OR_BY_CATEGORY";

	private static final String MAIN_PAGE = "/WEB-INF/jsp/page/main.jsp";
	private static final String SHOPPER_PAGE = "/WEB-INF/jsp/page/shopper_page.jsp";
	private static final String ADMIN_PAGE = "/WEB-INF/jsp/model_list_admin.jsp";

	private ModelService modelService;

	public ModelsByCategoryCommand() {
		modelService = ServiceFactory.getInstance().getModelService();
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			final HttpSession session = req.getSession(true);

			String message = (String) session.getAttribute(SessionNameParameters.CURRENT_MESSAGE);
			session.removeAttribute(SessionNameParameters.CURRENT_MESSAGE);
			req.setAttribute(RequestNameParameters.CURRENT_MESSAGE, message);

			User user = (User) session.getAttribute(SessionNameParameters.USER);

			req.setAttribute(RequestNameParameters.REDIRECT_TO, NAME_CURRENT_COMMAND);

			if (user == null) {
				setCategories(req, resp);
				setModels(req, resp);

				req.getRequestDispatcher(MAIN_PAGE).forward(req, resp);

			} else if (user.getRole().equals(UserRole.SHOPPER)) {
				setCategories(req, resp);
				setModels(req, resp);

				req.getRequestDispatcher(SHOPPER_PAGE).forward(req, resp);

			} else if (user.getRole().equals(UserRole.ADMIN)) {
				setModels(req, resp);
				req.setAttribute(RequestNameParameters.CURRENT_COMMAND, NAME_CURRENT_COMMAND);

				req.getRequestDispatcher(ADMIN_PAGE).forward(req, resp);
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
		return modelService.listModelsByCategory(Integer.parseInt(category), pageNumber, PerPage.MODELS_ON_PAGE);
	}

	private int getTotalModelCount(String category) throws ServiceException {
		if (category == null || category.isEmpty()) {
			return modelService.countModels();
		}
		return modelService.countModelsByCategoryId(Integer.parseInt(category));
	}

	private int getPageCount(int totalCount, int itemsPerCount) {
		return (int) Math.ceil((double) totalCount / itemsPerCount);
	}

	private void setModels(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
		String category = req.getParameter(RequestNameParameters.MODEL_CATEGORY);
		String pageNumberString = req.getParameter(RequestNameParameters.PAGE_NUMBER);
		int pageNumber = pageNumberString == null ? FIRST_PAGE : Integer.parseInt(pageNumberString);

		List<Model> models = getModels(category, pageNumber);
		int modelsCount = getTotalModelCount(category);

		req.setAttribute(RequestNameParameters.MODELS, models);

		req.setAttribute(RequestNameParameters.PAGE_COUNT, getPageCount(modelsCount, PerPage.MODELS_ON_PAGE));
		req.setAttribute(RequestNameParameters.MODEL_PER_PAGE, PerPage.MODELS_ON_PAGE);
		req.setAttribute(RequestNameParameters.PAGE_NUMBER, pageNumber);
		req.setAttribute(RequestNameParameters.MODELS_COUNT, modelsCount);
	}
	/**
	 * Метод, который помещает в объект HttpServletRequest список всех категорий
	 * моделей, и текущую категорию
	 */
	private void setCategories(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
		String category = req.getParameter(RequestNameParameters.CATEGORY);
		List<Category> categories = modelService.listAllCategories();
		req.setAttribute(RequestNameParameters.CATEGORIES, categories);
		req.setAttribute(RequestNameParameters.MODEL_CURRENT_CATEGORY, category); // сохранить текущую выбранную категорию, чтобы
		// загружать далее по категориям
	}
}
