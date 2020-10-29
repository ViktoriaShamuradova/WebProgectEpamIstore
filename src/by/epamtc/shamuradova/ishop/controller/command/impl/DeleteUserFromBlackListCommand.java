package by.epamtc.shamuradova.ishop.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.shamuradova.ishop.bean.User;
import by.epamtc.shamuradova.ishop.constant.UserRole;
import by.epamtc.shamuradova.ishop.controller.command.Command;
import by.epamtc.shamuradova.ishop.service.UserService;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;
import by.epamtc.shamuradova.ishop.service.factory.ServiceFactory;

public class DeleteUserFromBlackListCommand implements Command {

	private UserService userService;
	private static final String ERROR_PAGE = "controller?command=GET_ERROR_PAGE";

	public DeleteUserFromBlackListCommand() {
		userService = ServiceFactory.getInstance().getUserService();
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final HttpSession session = req.getSession(true);
		User user = (User) session.getAttribute("user");
		try {
			if (user == null || user.getRole().equals(UserRole.SHOPPER)) {
				resp.sendRedirect("controller?command=GET_MAIN_ALL_MODELS_OR_BY_CATEGORY_PAGE");
			} else {
				int userBlackListId = Integer.parseInt(req.getParameter("userId"));

				userService.deleteUserFromBlackList(userBlackListId);

				resp.sendRedirect("controller?command=BLACK_LIST");
			}
		} catch (ServiceException e) {
			e.printStackTrace();
			resp.sendRedirect(ERROR_PAGE);
		}
	}
}
