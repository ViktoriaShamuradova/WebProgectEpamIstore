package by.epamtc.shamuradova.ishop.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.shamuradova.ishop.controller.command.Command;

public class SignOutCommand implements Command {
	
	private static final String MAIN_COMMAND = "controller?command=ALL_MODELS_OR_BY_CATEGORY";
	private static final String NAME_CURRENT_COMMAND = "controller?command=SIGN_OUT";
	
	private static final String CURRENT_COMMAND = "command";
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final HttpSession session = req.getSession();
		session.removeAttribute(CURRENT_COMMAND);
		session.setAttribute(CURRENT_COMMAND, NAME_CURRENT_COMMAND);
		
		if (session != null) {
			session.invalidate();
		}
		resp.sendRedirect(MAIN_COMMAND);			
	}
}
