package by.epamtc.shamuradova.ishop.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.shamuradova.ishop.controller.command.Command;

public class ErrorPageCommand implements Command{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("statusCode", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/error_page.jsp");
		dispatcher.forward(req, resp);
		
	}

	

}
