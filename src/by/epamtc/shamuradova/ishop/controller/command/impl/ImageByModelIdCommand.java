package by.epamtc.shamuradova.ishop.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.shamuradova.ishop.constant.RequestNameParameters;
import by.epamtc.shamuradova.ishop.controller.command.Command;
import by.epamtc.shamuradova.ishop.service.ModelService;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;
import by.epamtc.shamuradova.ishop.service.factory.ServiceFactory;

public class ImageByModelIdCommand implements Command {

	private ModelService modelService;

	private static final String ERROR_COMMAND = "controller?command=GET_ERROR_PAGE";
	private static final String NAME_CONTENT_TYPE = "image/jpeg";

	public ImageByModelIdCommand() {
		this.modelService = ServiceFactory.getInstance().getModelService();
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int modelId = Integer.parseInt(req.getParameter(RequestNameParameters.MODEL_ID));

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
