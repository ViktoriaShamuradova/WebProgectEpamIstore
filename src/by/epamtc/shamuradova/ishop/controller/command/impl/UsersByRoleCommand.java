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

public class UsersByRoleCommand implements Command {

	private UserService userService;

	private static final int FIRST_PAGE = 1;


	private static final String NAME_CURRENT_COMMAND = "controller?command=users_by_role";
	private static final String CURRENT_PAGE = "/WEB-INF/jsp/users_list.jsp";
	private static final String ERROR_COMMAND = "controller?command=GET_ERROR_PAGE";

	public UsersByRoleCommand() {
		userService = ServiceFactory.getInstance().getUserService();
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			final HttpSession session = req.getSession();
		
			User user = (User) session.getAttribute(SessionNameParameters.USER);

			String pageNumberString = req.getParameter(RequestNameParameters.PAGE_NUMBER);
			int pageNumber = pageNumberString == null ? FIRST_PAGE : Integer.parseInt(pageNumberString);

			int roleId = Integer.parseInt(req.getParameter(RequestNameParameters.USER_ROLE_ID));
			List<User> users = userService.getUsersByRole(user, pageNumber, PerPage.USERS_ON_PAGE, roleId);
			int userCount = userService.countByRole(roleId);

			req.setAttribute(RequestNameParameters.USERS, users);
			req.setAttribute(RequestNameParameters.PAGE_NUMBER, pageNumber);
			req.setAttribute(RequestNameParameters.USER_COUNT, userCount);
			req.setAttribute(RequestNameParameters.PER_PAGE, PerPage.USERS_ON_PAGE);
			req.setAttribute(RequestNameParameters.CURRENT_COMMAND, NAME_CURRENT_COMMAND);
			req.setAttribute(RequestNameParameters.USER_ROLE_ID, roleId);
			req.setAttribute(RequestNameParameters.REDIRECT_TO, NAME_CURRENT_COMMAND);
			req.getRequestDispatcher(CURRENT_PAGE).forward(req, resp);

		} catch (ServiceException e) {
			e.printStackTrace();
			resp.sendRedirect(ERROR_COMMAND);
		}
	}
}
