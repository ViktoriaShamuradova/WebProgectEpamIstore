package by.epamtc.shamuradova.ishop.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.epamtc.shamuradova.ishop.constant.RequestNameParameters;
import by.epamtc.shamuradova.ishop.controller.command.Command;

/**
 * Класс, который передает управление странице error.jsp. Нужен для обработки
 * ServiceException.
 * 
 * The class that transfers control to the error.jsp page. Required to handle
 * ServiceException
 *
 * @author Victoria Shamuradova 2020
 */
public class ErrorPageCommand implements Command {

	private static final String STATUS_CODE = "statusCode";

	private static final String NAME_CURRENT_COMMAND = "controller?command=GET_ERROR_PAGE";
	private static final String ERROR_PAGE = "/WEB-INF/jsp/page/error.jsp";

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		req.setAttribute(STATUS_CODE, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		req.setAttribute(RequestNameParameters.REDIRECT_TO, NAME_CURRENT_COMMAND);

		RequestDispatcher dispatcher = req.getRequestDispatcher(ERROR_PAGE);
		dispatcher.forward(req, resp);

	}
}
