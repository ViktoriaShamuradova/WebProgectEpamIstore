package by.epamtc.shamuradova.ishop.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.shamuradova.ishop.controller.command.Command;

public class SignOutCommand implements Command {
	
	private static final String MAIN_PAGE = "controller?command=ALL_MODELS_OR_BY_CATEGORY";
	

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final HttpSession session = req.getSession();
		
		if (session != null) {
			session.invalidate();
		}
		resp.sendRedirect(MAIN_PAGE);
			
	}

}
