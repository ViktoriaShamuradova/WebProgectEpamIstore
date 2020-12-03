package by.epamtc.shamuradova.ishop.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.shamuradova.ishop.bean.entity.User;
import by.epamtc.shamuradova.ishop.constant.PerPage;
import by.epamtc.shamuradova.ishop.constant.RequestNameParameters;
import by.epamtc.shamuradova.ishop.constant.SessionNameParameters;
import by.epamtc.shamuradova.ishop.controller.command.Command;
import by.epamtc.shamuradova.ishop.service.UserService;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;
import by.epamtc.shamuradova.ishop.service.factory.ServiceFactory;

/**
 * Класс-комманда для отображения всех данных по всем пользователям для админа
 * 
 * Class-command for displaying all data for all users for the admin
 * 
 * @author Victoria Shamuradova 2020
 */
public class AllUsersCommand implements Command {

	private UserService userService;

	private static final int FIRST_PAGE = 1;

	private static final String NAME_CURRENT_COMMAND = "controller?command=all_users";
	private static final String NAME_CURRENT_PAGE = "/WEB-INF/jsp/users_list.jsp";
	private static final String ERROR_COMMAND = "controller?command=GET_ERROR_PAGE";

	public AllUsersCommand() {
		userService = ServiceFactory.getInstance().getUserService();
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			final HttpSession session = req.getSession();

			User user = (User) session.getAttribute(SessionNameParameters.USER);

			setUsers(req, resp, user);

			req.setAttribute(RequestNameParameters.REDIRECT_TO, NAME_CURRENT_COMMAND);

			req.getRequestDispatcher(NAME_CURRENT_PAGE).forward(req, resp);

		} catch (ServiceException e) {
			e.printStackTrace();
			resp.sendRedirect(ERROR_COMMAND);
		}
	}

	private void setUsers(HttpServletRequest req, HttpServletResponse resp, User user) throws ServiceException {
		String pageNumberString = req.getParameter(RequestNameParameters.PAGE_NUMBER);
		int pageNumber = pageNumberString == null ? FIRST_PAGE : Integer.parseInt(pageNumberString);
		String roleIdStr = req.getParameter(RequestNameParameters.USER_ROLE_ID);
		List<User> users;
		int userCount;

		if (roleIdStr == null || roleIdStr.isEmpty()) {
			users = userService.allUsers(user, pageNumber, PerPage.USERS_ON_PAGE);
			userCount = userService.countUsers();

		} else {
			int roleId = Integer.parseInt(roleIdStr);
			users = userService.getUsersByRole(user, pageNumber, PerPage.USERS_ON_PAGE, roleId);
			req.setAttribute(RequestNameParameters.USER_ROLE_ID, roleId);
			userCount = userService.countByRole(roleId);
		}

		req.setAttribute(RequestNameParameters.USERS, users);
		req.setAttribute(RequestNameParameters.PAGE_NUMBER, pageNumber);
		req.setAttribute(RequestNameParameters.USER_COUNT, userCount);
		req.setAttribute(RequestNameParameters.PER_PAGE, PerPage.USERS_ON_PAGE);
		req.setAttribute(RequestNameParameters.CURRENT_COMMAND, NAME_CURRENT_COMMAND);

	}
}
