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

public class AddUserInBlackListCommand implements Command {

	private UserService userService;
	
	private static final String ALL_USERS_PAGE =  "controller?command=all_users";
	private static final String ERROR_PAGE = "controller?command=GET_ERROR_PAGE";
	
	public AddUserInBlackListCommand() {
		userService = ServiceFactory.getInstance().getUserService();
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			HttpSession session = req.getSession();
			User user = (User)session.getAttribute("user");
	
			int userId = Integer.parseInt(req.getParameter("userId"));
			userService.addUserInBlackList(user, userId);
			
			resp.sendRedirect(ALL_USERS_PAGE);
			
		} catch (ServiceException e) {
			e.printStackTrace();
			resp.sendRedirect(ERROR_PAGE);	
		}
	}
}
