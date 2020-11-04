package by.epamtc.shamuradova.ishop.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.shamuradova.ishop.bean.entity.Model;
import by.epamtc.shamuradova.ishop.bean.entity.User;
import by.epamtc.shamuradova.ishop.constant.UserRole;
import by.epamtc.shamuradova.ishop.controller.command.Command;
import by.epamtc.shamuradova.ishop.service.ModelService;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;
import by.epamtc.shamuradova.ishop.service.factory.ServiceFactory;

public class FormEditModelCommand implements Command {

	private ModelService modelService;

	private static final String MAIN_PAGE = "controller?command=ALL_MODELS_OR_BY_CATEGORY";
	private static final String ERROR_PAGE = "controller?command=GET_ERROR_PAGE";

	public FormEditModelCommand() {
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

			String modelIdString = req.getParameter("modelId");
			if (modelIdString == null) {
				req.getRequestDispatcher("/WEB-INF/jsp/model_edit.jsp").forward(req, resp);
				
			}else if("".equals(modelIdString.trim())) {
				req.getRequestDispatcher("/WEB-INF/jsp/model_edit.jsp").forward(req, resp);
				
			} else {
			
				// иначе подгружаем данные этой модели и переходим на страницу
				int modelId = Integer.parseInt(req.getParameter("modelId"));
				Model model = modelService.getModel(modelId);
				req.setAttribute("model", model);
				req.getRequestDispatcher("/WEB-INF/jsp/model_edit.jsp").forward(req, resp);
			}
		} catch( ServiceException e) {
			e.printStackTrace();
			resp.sendRedirect(ERROR_PAGE);
		}

	}

}
