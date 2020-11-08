package by.epamtc.shamuradova.ishop.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.shamuradova.ishop.bean.entity.Order;
import by.epamtc.shamuradova.ishop.bean.entity.User;
import by.epamtc.shamuradova.ishop.controller.command.Command;
import by.epamtc.shamuradova.ishop.service.OrderService;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;
import by.epamtc.shamuradova.ishop.service.factory.ServiceFactory;

/**
 * Комманда, предназначенная для отображения данных заказа
 * 
 * @author Шамурадова Виктория
 * 
 */

public class OrderDetailesPageCommand implements Command {
	private static final String CURRENT_MESSAGE = "current_message";
	
	private static final String NAME_BEAN = "order";

	private static final String ERROR_PAGE = "controller?command=GET_ERROR_PAGE";
	private static final String NAME_NEXT_PAGE = "/WEB-INF/jsp/order_detailes.jsp";

	private OrderService orderService;

	public OrderDetailesPageCommand() {
		orderService = ServiceFactory.getInstance().getOrderService();
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			HttpSession session = req.getSession();

			String message = (String) session.getAttribute(CURRENT_MESSAGE);
			session.removeAttribute(CURRENT_MESSAGE);
			req.setAttribute(CURRENT_MESSAGE, message);

			User user = (User) session.getAttribute("user");

			Order order = orderService.findOrderById(user, Integer.parseInt(req.getParameter("idOrder")));
			req.setAttribute(NAME_BEAN, order);
			RequestDispatcher dispatcher = req.getRequestDispatcher(NAME_NEXT_PAGE);
			dispatcher.forward(req, resp);

		} catch (ServiceException e) {
			e.printStackTrace();
			resp.sendRedirect(ERROR_PAGE);
		}
	}
}
