package by.epamtc.shamuradova.ishop.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.shamuradova.ishop.bean.Model;
import by.epamtc.shamuradova.ishop.constant.ModelConstant;
import by.epamtc.shamuradova.ishop.controller.command.Command;
import by.epamtc.shamuradova.ishop.service.ModelService;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;
import by.epamtc.shamuradova.ishop.service.impl.ModelServiceImpl;

public class AllModelsMoreCommand implements Command {

	private static final String MODELS_PARAM = "models";
	private static final String ERROR_PAGE = "controller?command=GET_ERROR_PAGE";

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ModelService modelService = new ModelServiceImpl();
		List<Model> models;
		try {
			models = modelService.listAllModels(2, ModelConstant.MAX_COUNT_MODELS_ON_PAGE);
			req.setAttribute(MODELS_PARAM, models);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/model_list.jsp");
			dispatcher.forward(req, resp);
		}

		catch (ServiceException e) {
			e.printStackTrace();
			resp.sendRedirect(ERROR_PAGE);
		}

	}

}
