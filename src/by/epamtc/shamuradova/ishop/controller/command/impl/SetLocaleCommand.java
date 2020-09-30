package by.epamtc.shamuradova.ishop.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.shamuradova.ishop.controller.command.Command;

public class SetLocaleCommand implements Command {

	private static final String RUSSIAN_LOCALE = "ru";
	private static final String ENGLISH_LOCALE = "en";

	private static final String SESSION_PARAMETER_LOCALE = "locale";
	private static final String REQUEST_PARAMETER_LOCALE = "locale";
	private static final String REDIRECT_TO = "redirect_to";

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(true);
		String locale = req.getParameter(REQUEST_PARAMETER_LOCALE).toLowerCase();		

		switch (locale) {
			case RUSSIAN_LOCALE:
				session.setAttribute(SESSION_PARAMETER_LOCALE, RUSSIAN_LOCALE);
				break;
			case ENGLISH_LOCALE:
				session.setAttribute(SESSION_PARAMETER_LOCALE, ENGLISH_LOCALE);
				break;
		}
		String redirectTo = req.getParameter(REDIRECT_TO);
		req.getRequestDispatcher(redirectTo).forward(req, resp); //решить проблему, на какую страницу переходить

	}

}
