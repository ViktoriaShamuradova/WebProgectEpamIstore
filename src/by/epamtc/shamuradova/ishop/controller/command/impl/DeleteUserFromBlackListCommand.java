package by.epamtc.shamuradova.ishop.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.shamuradova.ishop.bean.entity.User;
import by.epamtc.shamuradova.ishop.controller.command.Command;
import by.epamtc.shamuradova.ishop.service.UserService;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;
import by.epamtc.shamuradova.ishop.service.factory.ServiceFactory;

public class DeleteUserFromBlackListCommand implements Command {

	private UserService userService;
	private static final String ERROR_PAGE = "controller?command=GET_ERROR_PAGE";
	private static final String CURRENT_PAGE = "controller?command=BLACK_LIST";

	public DeleteUserFromBlackListCommand() {
		userService = ServiceFactory.getInstance().getUserService();
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			final HttpSession session = req.getSession(true);
			User user = (User) session.getAttribute("user");

			int userBlackListId = Integer.parseInt(req.getParameter("userId"));

			userService.deleteUserBlackList(user, userBlackListId);

			resp.sendRedirect(CURRENT_PAGE);

		} catch (ServiceException e) {
			e.printStackTrace();
			resp.sendRedirect(ERROR_PAGE);
		}
	}
}
