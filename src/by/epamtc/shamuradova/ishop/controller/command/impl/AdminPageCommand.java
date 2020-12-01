package by.epamtc.shamuradova.ishop.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.shamuradova.ishop.constant.RequestNameParameters;
import by.epamtc.shamuradova.ishop.constant.SessionNameParameters;
import by.epamtc.shamuradova.ishop.controller.command.Command;

/**
 * Класс-комманда, который передает управление странице admin_page.jsp, которая
 * нужна для отображения страницы для админа
 * 
 * The command class that transfers control to the admin page.jsp page. Needed
 * to display the page for the admin
 *
 * @author Victoria Shamuradova 2020
 */
public class AdminPageCommand implements Command {

	private static final String NAME_CURRENT_COMMAND = "controller?command=GET_ADMIN_PAGE";
	private static final String ADMIN_PAGE = "/WEB-INF/jsp/page/admin_page.jsp";

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final HttpSession session = req.getSession();
			
		String message = (String) session.getAttribute(SessionNameParameters.CURRENT_MESSAGE);
		session.removeAttribute(SessionNameParameters.CURRENT_MESSAGE);

		req.setAttribute(RequestNameParameters.CURRENT_MESSAGE, message);
		req.setAttribute(RequestNameParameters.REDIRECT_TO, NAME_CURRENT_COMMAND);

		RequestDispatcher dispatcher = req.getRequestDispatcher(ADMIN_PAGE);
		dispatcher.forward(req, resp);
	}
}
