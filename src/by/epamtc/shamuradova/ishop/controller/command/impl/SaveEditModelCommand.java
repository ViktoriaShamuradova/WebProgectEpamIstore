package by.epamtc.shamuradova.ishop.controller.command.impl;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.shamuradova.ishop.bean.ModelEdition;
import by.epamtc.shamuradova.ishop.bean.entity.User;
import by.epamtc.shamuradova.ishop.constant.UserRole;
import by.epamtc.shamuradova.ishop.controller.command.Command;
import by.epamtc.shamuradova.ishop.service.ModelService;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;
import by.epamtc.shamuradova.ishop.service.exception.ValidationException;
import by.epamtc.shamuradova.ishop.service.factory.ServiceFactory;

public class SaveEditModelCommand implements Command {

	private static final String ALL_MODELS_PAGE = "controller?command=all_models_for_admin";
	private static final String MAIN_PAGE = "controller?command=GET_MAIN_ALL_MODELS_OR_BY_CATEGORY_PAGE";
	private static final String ERROR_PAGE = "controller?command=GET_ERROR_PAGE";

	private ModelService modelService;

	public SaveEditModelCommand() {
		modelService = ServiceFactory.getInstance().getModelService();
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");

		if (user.getRole().equalsIgnoreCase(UserRole.SHOPPER)) {
			session.invalidate();
			resp.sendRedirect(MAIN_PAGE);
		}

		try {

			ModelEdition modelEdition = new ModelEdition();
			modelEdition.setId(Integer.parseInt(req.getParameter("modelId")));
			modelEdition.setName(req.getParameter("modelName"));
			modelEdition.setPrice(new BigDecimal(req.getParameter("modelPrice").replace(',', '.')).setScale(2));
			modelEdition.setDescription(req.getParameter("modelDescription"));
			modelEdition.setCount(Integer.parseInt(req.getParameter("modelCount")));
			modelEdition.setCategory(req.getParameter("modelCategory"));
			modelEdition.setProducer(req.getParameter("modelProducer"));

			modelService.saveEditionModel(modelEdition);

			resp.sendRedirect(ALL_MODELS_PAGE);
		} catch (ValidationException e) {
			e.printStackTrace();
			resp.sendRedirect(ERROR_PAGE);
		} catch (ServiceException e) {
			e.printStackTrace();
			resp.sendRedirect(ERROR_PAGE);
		}

	}

}
