package by.epamtc.shamuradova.ishop.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.shamuradova.ishop.bean.Order;
import by.epamtc.shamuradova.ishop.bean.User;
import by.epamtc.shamuradova.ishop.controller.command.Command;
import by.epamtc.shamuradova.ishop.service.OrderService;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;
import by.epamtc.shamuradova.ishop.service.factory.ServiceFactory;

public class OrderDetailesPageCommand implements Command {
	private static final String CURRENT_MESSAGE = "current_message";
	private OrderService orderService;

	public OrderDetailesPageCommand() {
		orderService = ServiceFactory.getInstance().getOrderService();
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session =  req.getSession();
		
		String message = (String) session.getAttribute(CURRENT_MESSAGE);
		session.removeAttribute(CURRENT_MESSAGE);
		req.setAttribute(CURRENT_MESSAGE, message);
		
		
		User user = (User) session.getAttribute("user");
		
		try {
			
			Order order = orderService.findOrderById(Integer.parseInt(req.getParameter("idOrder")), user.getId());
			req.setAttribute("order", order);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/order_detailes.jsp");
			dispatcher.forward(req, resp);
			
		} catch (NumberFormatException | ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
