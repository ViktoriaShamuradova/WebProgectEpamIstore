package by.epamtc.shamuradova.ishop.controller.command.impl;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.shamuradova.ishop.bean.ModelEdition;
import by.epamtc.shamuradova.ishop.bean.entity.User;
import by.epamtc.shamuradova.ishop.controller.command.Command;
import by.epamtc.shamuradova.ishop.service.ModelService;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;
import by.epamtc.shamuradova.ishop.service.exception.ValidationException;
import by.epamtc.shamuradova.ishop.service.factory.ServiceFactory;

public class SaveNewModelCommand implements Command {

	private static final String MAIN_PAGE = "controller?command=ALL_MODELS_OR_BY_CATEGORY";
	private static final String ERROR_PAGE = "controller?command=GET_ERROR_PAGE";

	private ModelService modelService;

	public SaveNewModelCommand() {
		modelService = ServiceFactory.getInstance().getModelService();
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");

		try {

			ModelEdition modelEdition = new ModelEdition();

			modelEdition.setName(req.getParameter("modelName"));
			modelEdition.setPrice(new BigDecimal(req.getParameter("modelPrice").replace(',', '.')).setScale(2));
			modelEdition.setDescription(req.getParameter("modelDescription"));
			modelEdition.setCount(Integer.parseInt(req.getParameter("modelCount")));
			modelEdition.setCategory(req.getParameter("modelCategory"));
			modelEdition.setProducer(req.getParameter("modelProducer"));

			modelService.saveNewModel(user, modelEdition);

			resp.sendRedirect(MAIN_PAGE);

		} catch (ValidationException e) {
			e.printStackTrace();
			resp.sendRedirect(ERROR_PAGE);
		} catch (ServiceException e) {
			e.printStackTrace();
			resp.sendRedirect(ERROR_PAGE);
		}
	}
}
