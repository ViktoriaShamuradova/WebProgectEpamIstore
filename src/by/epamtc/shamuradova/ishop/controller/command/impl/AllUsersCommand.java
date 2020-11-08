package by.epamtc.shamuradova.ishop.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.shamuradova.ishop.bean.entity.User;
import by.epamtc.shamuradova.ishop.constant.PerPage;
import by.epamtc.shamuradova.ishop.controller.command.Command;
import by.epamtc.shamuradova.ishop.service.UserService;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;
import by.epamtc.shamuradova.ishop.service.factory.ServiceFactory;

public class AllUsersCommand implements Command {

	private UserService userService;

	private static final int FIRST_PAGE = 1;

	private static final String CURRENT_COMMAND = "command";
	private static final String PAGE_NUMBER = "pageNumber";
	private static final String NAME_BEANS = "users";
	private static final String COUNT_BEAN = "userCount";
	private static final String PER_PAGE = "perPage";
		
	private static final String NAME_CURRENT_COMMAND = "controller?command=all_users";
	private static final String NAME_CURRENT_PAGE = "/WEB-INF/jsp/users_list.jsp";
	private static final String ERROR_PAGE = "controller?command=GET_ERROR_PAGE";

	public AllUsersCommand() {
		userService = ServiceFactory.getInstance().getUserService();
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			HttpSession session = req.getSession();
			User user = (User) session.getAttribute("user");

			String pageNumberString = req.getParameter(PAGE_NUMBER);
			int pageNumber = pageNumberString == null ? FIRST_PAGE : Integer.parseInt(pageNumberString);

			List<User> users = userService.allUsers(user, pageNumber, PerPage.USERS_ON_PAGE);
			int userCount = userService.countUsers();

			req.setAttribute(NAME_BEANS, users);
			req.setAttribute(PAGE_NUMBER, pageNumber);
			req.setAttribute(COUNT_BEAN, userCount);
			req.setAttribute(PER_PAGE, PerPage.USERS_ON_PAGE);
			req.setAttribute(CURRENT_COMMAND, NAME_CURRENT_COMMAND);

			req.getRequestDispatcher(NAME_CURRENT_PAGE).forward(req, resp);

		} catch (ServiceException e) {
			e.printStackTrace();
			resp.sendRedirect(ERROR_PAGE);
		}
	}
}
