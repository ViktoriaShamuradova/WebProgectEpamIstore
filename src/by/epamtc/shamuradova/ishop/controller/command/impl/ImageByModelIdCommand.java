package by.epamtc.shamuradova.ishop.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.shamuradova.ishop.controller.command.Command;
import by.epamtc.shamuradova.ishop.service.ModelService;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;
import by.epamtc.shamuradova.ishop.service.factory.ServiceFactory;

public class ImageByModelIdCommand implements Command {

	private ModelService modelService;

	private static final String NAME_CURRENT_COMMAND = "controller?command=GET_IMAGE_BY_MODEL_ID";
	private static final String ERROR_COMMAND = "controller?command=GET_ERROR_PAGE";
	private static final String MODEL_ID = "modelId";
	private static final String NAME_CONTENT_TYPE = "image/jpeg";
	private static final String CURRENT_COMMAND = "command";

	public ImageByModelIdCommand() {
		this.modelService = ServiceFactory.getInstance().getModelService();
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			final HttpSession session = req.getSession(true);
			session.removeAttribute(CURRENT_COMMAND);
			session.setAttribute(CURRENT_COMMAND, NAME_CURRENT_COMMAND);
			
			int modelId = Integer.parseInt(req.getParameter(MODEL_ID));

			byte[] image = modelService.getImageByModelId(modelId);

			resp.setContentType(NAME_CONTENT_TYPE);
			resp.setContentLength(image.length);
			resp.getOutputStream().write(image);

		} catch (ServiceException e) {
			e.printStackTrace();
			resp.sendRedirect(ERROR_COMMAND);
		}
	}
}
