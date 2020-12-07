package by.epamtc.shamuradova.ishop.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.shamuradova.ishop.bean.entity.Model;
import by.epamtc.shamuradova.ishop.controller.command.Command;
import by.epamtc.shamuradova.ishop.service.ModelService;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;
import by.epamtc.shamuradova.ishop.service.factory.ServiceFactory;

/**
 * Класс-комманда для редактирования характеристик модели или добавления данных
 * новой модели и перенаправление управления на страницу jsp
 * 
 * Command class for editing model characteristics or adding new model data and
 * redirect control to jsp page
 *
 * @author Victoria Shamuradova 2020
 */

public class FormEditModelCommand implements Command {

	private ModelService modelService;

	private static final String ERROR_COMMAND = "controller?command=GET_ERROR_PAGE";
	private static final String NAME_CURRENT_COMMAND = "controller?command=FORM_EDITING_MODEL";
	private static final String MODEL_EDIT_PAGE = "/WEB-INF/jsp/model_edit.jsp";

	private static final String REDIRECT_TO = "redirectTo";
	private static final String CURRENT_COMMAND = "command";
	private static final String MODEL_ID = "modelId";
	private static final String MODEL = "model";

	public FormEditModelCommand() {
		modelService = ServiceFactory.getInstance().getModelService();
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			final HttpSession session = req.getSession(true);
			session.removeAttribute(CURRENT_COMMAND);
			session.setAttribute(CURRENT_COMMAND, NAME_CURRENT_COMMAND);

			String modelIdString = req.getParameter(MODEL_ID);

			req.setAttribute(REDIRECT_TO, NAME_CURRENT_COMMAND);

			if (modelIdString == null || "".equals(modelIdString.trim())) {
				req.getRequestDispatcher(MODEL_EDIT_PAGE).forward(req, resp);

			} else {
				// иначе подгружаем данные этой модели и переходим на страницу
				int modelId = Integer.parseInt(req.getParameter(MODEL_ID));
				Model model = modelService.getModel(modelId);

				req.setAttribute(MODEL, model);
				req.getRequestDispatcher(MODEL_EDIT_PAGE).forward(req, resp);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
			resp.sendRedirect(ERROR_COMMAND);
		}
	}
	public static void main(String[] args) {
		HashMap h;
		ArrayList
	}
}
