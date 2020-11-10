package by.epamtc.shamuradova.ishop.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.shamuradova.ishop.controller.command.Command;

public class EnterPageCommand implements Command {

	private static final String CURRENT_MESSAGE = "current_message";
	private static final String CURRENT_COMMAND = "controller?command=ENTER_PAGE";
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String message = (String)session.getAttribute(CURRENT_MESSAGE);
		session.removeAttribute(CURRENT_MESSAGE);
		req.setAttribute(CURRENT_MESSAGE, message);
		
		req.setAttribute("redirectTo", CURRENT_COMMAND);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/enter.jsp");
		dispatcher.forward(req, resp);
		
	}
}
