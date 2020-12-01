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

/**
 * Класс-комманда для удаления пользователя из черного списка
 * 
 * Command class for removing a user from the blacklist
 *
 * @author Victoria Shamuradova 2020
 */
public class DeleteUserFromBlackListCommand implements Command {

	private UserService userService;

	private static final String ERROR_COMMAND = "controller?command=GET_ERROR_PAGE";
	private static final String BLACK_LIST_COMMAND = "controller?command=BLACK_LIST";
	private static final String NAME_CURRENT_COMMAND = "controller?command=DELETE_USER_FROM_BLACK_LIST";

	private static final String USER = "user";
	private static final String USER_ID = "userId";
	private static final String CURRENT_COMMAND = "command";

	public DeleteUserFromBlackListCommand() {
		userService = ServiceFactory.getInstance().getUserService();
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			final HttpSession session = req.getSession(true);
			session.removeAttribute(CURRENT_COMMAND);
			session.setAttribute(CURRENT_COMMAND, NAME_CURRENT_COMMAND);
			User user = (User) session.getAttribute(USER);

			int userBlackListId = Integer.parseInt(req.getParameter(USER_ID));

			userService.deleteUserBlackList(user, userBlackListId);

			resp.sendRedirect(BLACK_LIST_COMMAND);

		} catch (ServiceException e) {
			e.printStackTrace();
			resp.sendRedirect(ERROR_COMMAND);
		}
	}
}
