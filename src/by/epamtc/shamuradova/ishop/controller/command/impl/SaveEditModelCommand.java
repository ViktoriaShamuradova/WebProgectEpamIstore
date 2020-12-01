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
import by.epamtc.shamuradova.ishop.service.factory.ServiceFactory;

public class SaveEditModelCommand implements Command {

	private static final String MAIN_COMMAND = "controller?command=ALL_MODELS_OR_BY_CATEGORY";
	private static final String NAME_CURRENT_COMMAND = "controller?command=SAVE_EDIT_MODEL";
	private static final String ERROR_COMMAND = "controller?command=GET_ERROR_PAGE";

	private static final String CURRENT_COMMAND = "command";
	private static final String USER = "user";
	private static final String MODEL_ID = "modelId";
	private static final String MODEL_NAME = "modelName";
	private static final String MODEL_COUNT = "modelCount";
	private static final String MODEL_PRICE = "modelPrice";
	private static final String MODEL_CATEGORY = "modelCategory";
	private static final String MODEL_PRODUCER = "modelProducer";
	private static final String MODEL_DESCRIPTION = "modelDescription";

	private ModelService modelService;

	public SaveEditModelCommand() {
		modelService = ServiceFactory.getInstance().getModelService();
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {

			final HttpSession session = req.getSession();
			session.removeAttribute(CURRENT_COMMAND);
			session.setAttribute(CURRENT_COMMAND, NAME_CURRENT_COMMAND);
			User user = (User) session.getAttribute(USER);

			ModelEdition modelEdition = new ModelEdition();
			modelEdition.setId(Integer.parseInt(req.getParameter(MODEL_ID)));
			modelEdition.setName(req.getParameter(MODEL_NAME));
			modelEdition.setPrice(new BigDecimal(req.getParameter(MODEL_PRICE).replace(',', '.')).setScale(2));
			modelEdition.setDescription(req.getParameter(MODEL_DESCRIPTION));
			modelEdition.setCount(Integer.parseInt(req.getParameter(MODEL_COUNT)));
			modelEdition.setCategory(req.getParameter(MODEL_CATEGORY));
			modelEdition.setProducer(req.getParameter(MODEL_PRODUCER));

			modelService.saveEditionModel(user, modelEdition);

			resp.sendRedirect(MAIN_COMMAND);

		} catch (ServiceException e) {
			e.printStackTrace();
			resp.sendRedirect(ERROR_COMMAND);
		}
	}
}
