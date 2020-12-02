package by.epamtc.shamuradova.ishop.controller.command.impl;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.shamuradova.ishop.bean.ModelEdition;
import by.epamtc.shamuradova.ishop.bean.entity.User;
import by.epamtc.shamuradova.ishop.constant.RequestNameParameters;
import by.epamtc.shamuradova.ishop.constant.SessionNameParameters;
import by.epamtc.shamuradova.ishop.controller.command.Command;
import by.epamtc.shamuradova.ishop.service.ModelService;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;
import by.epamtc.shamuradova.ishop.service.factory.ServiceFactory;

public class SaveEditModelCommand implements Command {

	private static final String MAIN_COMMAND = "controller?command=ALL_MODELS_OR_BY_CATEGORY";
	private static final String ERROR_COMMAND = "controller?command=GET_ERROR_PAGE";
	
	private ModelService modelService;

	public SaveEditModelCommand() {
		modelService = ServiceFactory.getInstance().getModelService();
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {

			final HttpSession session = req.getSession();
			
			User user = (User) session.getAttribute(SessionNameParameters.USER);

			ModelEdition modelEdition = new ModelEdition();
			modelEdition.setId(Integer.parseInt(req.getParameter(RequestNameParameters.MODEL_ID)));
			modelEdition.setName(req.getParameter(RequestNameParameters.MODEL_NAME));
			modelEdition.setPrice(new BigDecimal(req.getParameter(RequestNameParameters.MODEL_PRICE).replace(',', '.')).setScale(2));
			modelEdition.setDescription(req.getParameter(RequestNameParameters.MODEL_DESCRIPTION));
			modelEdition.setCount(Integer.parseInt(req.getParameter(RequestNameParameters.MODEL_COUNT)));
			modelEdition.setCategory(req.getParameter(RequestNameParameters.MODEL_CATEGORY));
			modelEdition.setProducer(req.getParameter(RequestNameParameters.MODEL_PRODUCER));

			modelService.saveEditionModel(user, modelEdition);

			resp.sendRedirect(MAIN_COMMAND);

		} catch (ServiceException e) {
			e.printStackTrace();
			resp.sendRedirect(ERROR_COMMAND);
		}
	}
}
