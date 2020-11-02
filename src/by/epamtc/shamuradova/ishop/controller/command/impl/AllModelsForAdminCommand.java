package by.epamtc.shamuradova.ishop.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import by.epamtc.shamuradova.ishop.bean.entity.Model;
import by.epamtc.shamuradova.ishop.bean.entity.User;
import by.epamtc.shamuradova.ishop.constant.PerPage;
import by.epamtc.shamuradova.ishop.constant.UserRole;
import by.epamtc.shamuradova.ishop.controller.command.Command;
import by.epamtc.shamuradova.ishop.service.ModelService;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;
import by.epamtc.shamuradova.ishop.service.factory.ServiceFactory;

public class AllModelsForAdminCommand implements Command {

	private ModelService modelService;

	private static final String MAIN_PAGE = "controller?command=GET_MAIN_ALL_MODELS_OR_BY_CATEGORY_PAGE";
	private static final int FIRST_PAGE = 1;
	private static final String ERROR_PAGE = "controller?command=GET_ERROR_PAGE";
	private static final String MODELS = "models";
	private static final String CURRENT_COMMAND = "command";
	private static final String NAME_CURRENT_COMMAND = "controller?command=ALL_MODELS_FOR_ADMIN";

	public AllModelsForAdminCommand() {
		modelService = ServiceFactory.getInstance().getModelService();
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			HttpSession session = req.getSession();
			User user = (User) session.getAttribute("user");
			Part part = req.getPart("file");
			part.getSize();

			if (user.getRole().equalsIgnoreCase(UserRole.SHOPPER)) {
				session.invalidate();
				resp.sendRedirect(MAIN_PAGE);
			}

			String pageNumberString = req.getParameter("pageNumber");
			int pageNumber = pageNumberString == null ? FIRST_PAGE : Integer.parseInt(pageNumberString);

			List<Model> models;

			models = modelService.listAllModels(pageNumber, PerPage.MODELS_ON_PAGE);

			int modelCount = modelService.countModels();

			req.setAttribute(MODELS, models);
			req.setAttribute("pageNumber", pageNumber);
			req.setAttribute("modelCount", modelCount);
			req.setAttribute("perPage", PerPage.MODELS_ON_PAGE);
			req.setAttribute(CURRENT_COMMAND, NAME_CURRENT_COMMAND);

			req.getRequestDispatcher("/WEB-INF/jsp/model_list_admin.jsp").forward(req, resp);

		} catch (ServiceException e) {
			e.printStackTrace();
			resp.sendRedirect(ERROR_PAGE);
		}

	}

}
