package by.epamtc.shamuradova.ishop.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.shamuradova.ishop.bean.entity.User;
import by.epamtc.shamuradova.ishop.constant.PerPage;
import by.epamtc.shamuradova.ishop.constant.UserRole;
import by.epamtc.shamuradova.ishop.controller.command.Command;
import by.epamtc.shamuradova.ishop.service.UserService;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;
import by.epamtc.shamuradova.ishop.service.factory.ServiceFactory;

public class BlackListCommand implements Command {

	private UserService userService;

	private static final int FIRST_PAGE = 1;
	private static final String ERROR_PAGE = "controller?command=GET_ERROR_PAGE";
	private static final String BLACK_LIST = "blackList";
	private static final String CURRENT_COMMAND = "command";
	private static final String NAME_CURRENT_COMMAND= "controller?command=BLACK_LIST";
	
	private static final String MAIN_PAGE = "controller?command=ALL_MODELS_OR_BY_CATEGORY";

	public BlackListCommand() {
		userService = ServiceFactory.getInstance().getUserService();
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			HttpSession session = req.getSession();
			User user = (User)session.getAttribute("user");
			
			if(user.getRole().equalsIgnoreCase(UserRole.SHOPPER)) {
				session.invalidate();
				resp.sendRedirect(MAIN_PAGE);
			}
			
			String pageNumberString = req.getParameter("pageNumber");
			int pageNumber = pageNumberString == null ? FIRST_PAGE : Integer.parseInt(pageNumberString);
			
			List<User> users = userService.getBlackList(pageNumber, PerPage.USERS_ON_PAGE);
			int userCount = userService.countUsersInBlackList();

			req.setAttribute(BLACK_LIST, users);
			req.setAttribute("pageNumber", pageNumber);
			req.setAttribute("userCount", userCount);
			req.setAttribute("perPage", PerPage.USERS_ON_PAGE);
			req.setAttribute(CURRENT_COMMAND, NAME_CURRENT_COMMAND);
			
			req.getRequestDispatcher("/WEB-INF/jsp/black_list.jsp").forward(req, resp);

		} catch (ServiceException e) {
			e.printStackTrace();
			resp.sendRedirect(ERROR_PAGE);
		}
	}
}
