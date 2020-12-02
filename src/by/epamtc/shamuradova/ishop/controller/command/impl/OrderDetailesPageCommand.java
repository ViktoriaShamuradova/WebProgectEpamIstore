package by.epamtc.shamuradova.ishop.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.shamuradova.ishop.bean.entity.Order;
import by.epamtc.shamuradova.ishop.bean.entity.User;
import by.epamtc.shamuradova.ishop.constant.RequestNameParameters;
import by.epamtc.shamuradova.ishop.constant.SessionNameParameters;
import by.epamtc.shamuradova.ishop.controller.command.Command;
import by.epamtc.shamuradova.ishop.service.OrderService;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;
import by.epamtc.shamuradova.ishop.service.factory.ServiceFactory;

/**
 * Комманда, предназначенная для отображения данных заказа
 * 
 * Command for displaying order data
 * 
 * @author Victoria Shamuradova 2020
 * 
 */

public class OrderDetailesPageCommand implements Command {
	
	private static final String ERROR_COMMAND = "controller?command=GET_ERROR_PAGE";
	private static final String NAME_CURRENT_COMMAND = "controller?command=ORDER_DETAILES";
	private static final String ORDER_DETAILES_PAGE = "/WEB-INF/jsp/order_detailes.jsp";

	private OrderService orderService;

	public OrderDetailesPageCommand() {
		orderService = ServiceFactory.getInstance().getOrderService();
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			final HttpSession session = req.getSession();
		
			String message = (String) session.getAttribute(SessionNameParameters.CURRENT_MESSAGE);
			session.removeAttribute(SessionNameParameters.CURRENT_MESSAGE);
			req.setAttribute(RequestNameParameters.CURRENT_MESSAGE, message);

			User user = (User) session.getAttribute(SessionNameParameters.USER);

			Order order = orderService.findOrderById(user, Integer.parseInt(req.getParameter(RequestNameParameters.ORDER_ID)));

			req.setAttribute(RequestNameParameters.REDIRECT_TO, NAME_CURRENT_COMMAND);
			req.setAttribute(RequestNameParameters.ORDER, order);
			RequestDispatcher dispatcher = req.getRequestDispatcher(ORDER_DETAILES_PAGE);
			dispatcher.forward(req, resp);

		} catch (ServiceException e) {
			e.printStackTrace();
			resp.sendRedirect(ERROR_COMMAND);
		}
	}
}
