package by.epamtc.shamuradova.ishop.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.shamuradova.ishop.bean.RegInfo;
import by.epamtc.shamuradova.ishop.bean.User;
import by.epamtc.shamuradova.ishop.constant.ParameterName;
import by.epamtc.shamuradova.ishop.constant.UserRole;
import by.epamtc.shamuradova.ishop.constant.UserStatus;
import by.epamtc.shamuradova.ishop.controller.command.Command;
import by.epamtc.shamuradova.ishop.service.SignUpService;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;
import by.epamtc.shamuradova.ishop.service.impl.SignUpServiceImpl;

/**
 * Команда предназначенная для регистрации нового покупателя
 * 
 * 
 * @author Шамурадова Виктория 2020
 */
//наверное нужно, чтобы дао возвращад юзера, чтобы его поместить в сессию
public class SignUpShopperCommand implements Command {

	private static final String SHOPPER_PAGE = "controller?command=GET_SHOPPER_PAGE";
	private static final String ERROR_PAGE = "controller?command=GET_ERROR_PAGE";

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RegInfo regInfo = new RegInfo();
		regInfo.setName(req.getParameter(ParameterName.USER_NAME));
		regInfo.setSurname(req.getParameter(ParameterName.USER_SURNAME));
		regInfo.setLogin(req.getParameter(ParameterName.USER_LOGIN));
		regInfo.setPassword(req.getParameter(ParameterName.USER_PASSWORD).toCharArray());
		regInfo.setEmail(req.getParameter(ParameterName.USER_EMAIL));
		regInfo.setStatus(UserStatus.NEW);
		regInfo.setRole(UserRole.SHOPPER);

		SignUpService signUpService = new SignUpServiceImpl();

		User user = new User();
		user.setLogin(regInfo.getLogin());
		final HttpSession session = req.getSession(true);

		session.setAttribute("user", user);

		try {
			signUpService.signUp(regInfo);
			resp.sendRedirect(SHOPPER_PAGE);

		} catch (ServiceException e) {// ЕСЛИ ОШИБКА БД, ТО СТРАНИЦА С ИЗВИНЕНИЯМИ, ЕСЛИ ЛОГИН - ВЫБЕРИТЕ ДРУГОЙ, ЕСЛИ
										// ФОРМАТБ ТАКЖЕ С ПАРОЛЕМ
			e.printStackTrace();
			resp.sendRedirect(ERROR_PAGE);

		}

	}

}
