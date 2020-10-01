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
import by.epamtc.shamuradova.ishop.service.impl.ModelServiceImpl;

public class AllModelsCommand implements Command {

	private static final int FIRST_PAGE = 1;

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ModelService modelService = new ModelServiceImpl();
		List<Model> models = modelService.listAllModels(FIRST_PAGE, ModelConstant.MAX_COUNT_MODELS_ON_PAGE);
		req.setAttribute("models", models);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/shopper_page.jsp");
		dispatcher.forward(req, resp);

	}

}
