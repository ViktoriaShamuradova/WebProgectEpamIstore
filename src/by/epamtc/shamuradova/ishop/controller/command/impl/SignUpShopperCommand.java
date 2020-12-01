package by.epamtc.shamuradova.ishop.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtc.shamuradova.ishop.bean.RegInfo;
import by.epamtc.shamuradova.ishop.bean.entity.User;
import by.epamtc.shamuradova.ishop.constant.RequestNameParameters;
import by.epamtc.shamuradova.ishop.constant.SessionNameParameters;
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
 * The command for registering a new customer
 * 
 * @author Victoria Shamuradova 2020
 */

public class SignUpShopperCommand implements Command {

	private static final Logger logger = LogManager.getLogger(SignUpShopperCommand.class);

	private static final String SHOPPER_PAGE = "controller?command=ALL_MODELS_OR_BY_CATEGORY";
	private static final String REGISTRATION_PAGE_COMMAND = "controller?command=REGISTRATION_PAGE";
	private static final String ERROR_COMMAND = "controller?command=GET_ERROR_PAGE";

	private SignUpService signUpService;

	public SignUpShopperCommand() {
		signUpService = ServiceFactory.getInstance().getSignUpService();
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final HttpSession session = req.getSession(true);
		
		try {

			RegInfo regInfo = new RegInfo();
			regInfo.setName(req.getParameter(RequestNameParameters.USER_NAME));
			regInfo.setSurname(req.getParameter(RequestNameParameters.USER_SURNAME));
			regInfo.setLogin(req.getParameter(RequestNameParameters.USER_LOGIN));
			regInfo.setPassword(req.getParameter(RequestNameParameters.USER_PASSWORD).toCharArray());
			regInfo.setEmail(req.getParameter(RequestNameParameters.USER_EMAIL));
			regInfo.setStatus(UserStatus.NEW);
			regInfo.setRole(UserRole.SHOPPER);

			User user = signUpService.signUp(regInfo);

			logger.info("new user registered");

			session.setAttribute(SessionNameParameters.USER, user);
			resp.sendRedirect(SHOPPER_PAGE);
		} catch (ValidationException e) {
			session.setAttribute(SessionNameParameters.CURRENT_MESSAGE, e.getMessage());
			resp.sendRedirect(REGISTRATION_PAGE_COMMAND);
		} catch (ServiceException e) {
			resp.sendRedirect(ERROR_COMMAND);
		}
	}
}
