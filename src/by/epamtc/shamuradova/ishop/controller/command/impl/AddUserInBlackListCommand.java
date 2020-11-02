package by.epamtc.shamuradova.ishop.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.shamuradova.ishop.bean.entity.User;
import by.epamtc.shamuradova.ishop.constant.UserRole;
import by.epamtc.shamuradova.ishop.controller.command.Command;
import by.epamtc.shamuradova.ishop.service.UserService;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;
import by.epamtc.shamuradova.ishop.service.factory.ServiceFactory;

public class AddUserInBlackListCommand implements Command {

	private UserService userService;
	private static final String MAIN_PAGE = "controller?command=GET_MAIN_ALL_MODELS_OR_BY_CATEGORY_PAGE";
	
	public AddUserInBlackListCommand() {
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
			
			int userId = Integer.parseInt(req.getParameter("userId"));
			userService.addUserInBlackList(userId);
			
			resp.sendRedirect( "controller?command=all_users");
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
