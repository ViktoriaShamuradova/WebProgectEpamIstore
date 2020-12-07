package by.epamtc.shamuradova.ishop.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.shamuradova.ishop.constant.RequestNameParameters;
import by.epamtc.shamuradova.ishop.controller.command.Command;

/**
 * Класс, предназначенный для изменения локали
 * 
 * A class for changing the locale
 * 
 * @author Victoria Shamuradova 2020
 */

public class SetLocaleCommand implements Command {

	private static final String RUSSIAN_LOCALE = "ru";
	private static final String ENGLISH_LOCALE = "en";

	private static final String SESSION_PARAMETER_LOCALE = "locale";
	private static final String REQUEST_PARAMETER_LOCALE = "locale";

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final HttpSession session = req.getSession(true);

		String locale = req.getParameter(REQUEST_PARAMETER_LOCALE).toLowerCase();

		switch (locale) {
		case RUSSIAN_LOCALE:
			session.setAttribute(SESSION_PARAMETER_LOCALE, RUSSIAN_LOCALE);
			break;
		case ENGLISH_LOCALE:
			session.setAttribute(SESSION_PARAMETER_LOCALE, ENGLISH_LOCALE);
			break;
		}

		String redirectTo = (String) req.getParameter(RequestNameParameters.REDIRECT_TO);
		resp.sendRedirect(redirectTo);
	}
}
