package by.epamtc.shamuradova.ishop.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.shamuradova.ishop.constant.RequestNameParameters;
import by.epamtc.shamuradova.ishop.controller.command.Command;

/**
 * Комманда, предназначенная на для перехода на страницу cart.jsp
 * 
 * The command intended to go to the cart.jsp page
 * 
 * @author Victoria Shamuradova 2020
 */
public class CartPageCommand implements Command {

	private static final String CART_PAGE = "/WEB-INF/jsp/page/cart.jsp";
	private static final String NAME_CURRENT_COMMAND = "controller?command=CART_PAGE";


	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setAttribute(RequestNameParameters.REDIRECT_TO, NAME_CURRENT_COMMAND);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher(CART_PAGE);
		dispatcher.forward(req, resp);
	}
}
