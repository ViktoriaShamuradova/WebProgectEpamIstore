package by.epamtc.shamuradova.ishop.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.shamuradova.ishop.bean.entity.User;
import by.epamtc.shamuradova.ishop.constant.RequestNameParameters;
import by.epamtc.shamuradova.ishop.constant.SessionNameParameters;
import by.epamtc.shamuradova.ishop.controller.command.Command;
import by.epamtc.shamuradova.ishop.service.UserService;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;
import by.epamtc.shamuradova.ishop.service.factory.ServiceFactory;

public class UserDetailesCommand implements Command {

	private static final String ERROR_COMMAND = "controller?command=GET_ERROR_PAGE";
	private static final String NAME_CURRENT_COMMAND = "controller?command=USER_DETAILES";
	private static final String USER_DETAILES_PAGE = "/WEB-INF/jsp/user_detailes.jsp";

	
	private UserService userService;

	public UserDetailesCommand() {
		userService = ServiceFactory.getInstance().getUserService();
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			final HttpSession session = req.getSession();
			
			User user = (User) session.getAttribute(SessionNameParameters.USER);

			int userId = Integer.parseInt(req.getParameter(RequestNameParameters.USER_ID));
			User userResult = userService.getUser(user, userId);
			req.setAttribute(RequestNameParameters.USER_RESULT, userResult);
			req.setAttribute(RequestNameParameters.REDIRECT_TO, NAME_CURRENT_COMMAND);
			RequestDispatcher dispatcher = req.getRequestDispatcher(USER_DETAILES_PAGE);
			dispatcher.forward(req, resp);
		} catch (ServiceException e) {
			e.printStackTrace();
			resp.sendRedirect(ERROR_COMMAND);
		}
	}
}
