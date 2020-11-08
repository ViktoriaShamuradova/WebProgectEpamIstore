package by.epamtc.shamuradova.ishop.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.shamuradova.ishop.bean.entity.User;
import by.epamtc.shamuradova.ishop.controller.command.Command;
import by.epamtc.shamuradova.ishop.service.UserService;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;
import by.epamtc.shamuradova.ishop.service.factory.ServiceFactory;

public class UserDetailesCommand implements Command {

	private static final String ERROR_PAGE = "controller?command=GET_ERROR_PAGE";
	private static final String USER_DETAILES_PAGE = "/WEB-INF/jsp/user_detailes.jsp";
	
	private static final String REQ_ATTRIBUTE = "userRes";

	private UserService userService;

	public UserDetailesCommand() {
		userService = ServiceFactory.getInstance().getUserService();
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");

		int userId = Integer.parseInt(req.getParameter("userId"));

		try {
			User userResult = userService.getUser(user, userId);
			req.setAttribute(REQ_ATTRIBUTE, userResult);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher(USER_DETAILES_PAGE);
			dispatcher.forward(req, resp);
		} catch (ServiceException e) {
			e.printStackTrace();
			resp.sendRedirect(ERROR_PAGE);
		}
	}
}
