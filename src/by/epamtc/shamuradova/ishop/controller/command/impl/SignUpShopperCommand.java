package by.epamtc.shamuradova.ishop.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.shamuradova.ishop.bean.RegInfo;
import by.epamtc.shamuradova.ishop.bean.entity.User;
import by.epamtc.shamuradova.ishop.constant.ParameterName;
import by.epamtc.shamuradova.ishop.constant.UserRole;
import by.epamtc.shamuradova.ishop.constant.UserStatus;
import by.epamtc.shamuradova.ishop.controller.command.Command;
import by.epamtc.shamuradova.ishop.service.SignUpService;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;
import by.epamtc.shamuradova.ishop.service.exception.ValidationException;
import by.epamtc.shamuradova.ishop.service.factory.ServiceFactory;

/**
 * Команда предназначенная для регистрации нового покупателя
 * 
 * 
 * @author Шамурадова Виктория 2020
 */

public class SignUpShopperCommand implements Command {

	private static final String SHOPPER_PAGE = "controller?command=GET_SHOPPER_PAGE";

	private static final String REGISTRATION_PAGE_COMMAND = "controller?command=REGISTRATION_PAGE";
	private static final String ERROR_PAGE = "controller?command=GET_ERROR_PAGE";

	private static final String CURRENT_MESSAGE = "current_message";
	
	private SignUpService signUpService;

	public SignUpShopperCommand() {
		signUpService = ServiceFactory.getInstance().getSignUpService();
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final HttpSession session = req.getSession(true);
	
		try {
			
			RegInfo regInfo = new RegInfo();
			regInfo.setName(req.getParameter(ParameterName.USER_NAME));
			regInfo.setSurname(req.getParameter(ParameterName.USER_SURNAME));
			regInfo.setLogin(req.getParameter(ParameterName.USER_LOGIN));
			regInfo.setPassword(req.getParameter(ParameterName.USER_PASSWORD).toCharArray());
			regInfo.setEmail(req.getParameter(ParameterName.USER_EMAIL));
			regInfo.setStatus(UserStatus.NEW);
			regInfo.setRole(UserRole.SHOPPER);
			
			User user = signUpService.signUp(regInfo);
			
			session.setAttribute("user", user);
			resp.sendRedirect(SHOPPER_PAGE);

		}catch(ValidationException e) {
			session.setAttribute(CURRENT_MESSAGE, e.getMessage());
			resp.sendRedirect(REGISTRATION_PAGE_COMMAND);
		} catch (ServiceException e) {							
			e.printStackTrace();
			resp.sendRedirect(ERROR_PAGE);

		}
	}
}
