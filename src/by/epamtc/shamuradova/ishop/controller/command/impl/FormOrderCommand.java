package by.epamtc.shamuradova.ishop.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.shamuradova.ishop.bean.ShopCart;
import by.epamtc.shamuradova.ishop.bean.entity.User;
import by.epamtc.shamuradova.ishop.controller.command.Command;
import by.epamtc.shamuradova.ishop.service.OrderService;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;
import by.epamtc.shamuradova.ishop.service.factory.ServiceFactory;

public class FormOrderCommand implements Command {
	
	private static final String ERROR_PAGE = "controller?command=GET_ERROR_PAGE";
	private static final String CREATE_ORDER_PAGE = "controller?command=order_detailes&idOrder=";
	private static final String CURRENT_MESSAGE = "current_message";

	private OrderService orderService;

	public FormOrderCommand() {
		orderService = ServiceFactory.getInstance().getOrderService();
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		ShopCart shopCart = (ShopCart) session.getAttribute("shopcart");
		User user = (User) session.getAttribute("user");

		try {
			int orderId = orderService.makeOrder(shopCart, user);

			session.removeAttribute("shopcart");
			session.setAttribute(CURRENT_MESSAGE, "Order created successfully. please wait for our reply");

			resp.sendRedirect(CREATE_ORDER_PAGE + orderId);

		} catch (ServiceException e) {
			e.printStackTrace();
			resp.sendRedirect(ERROR_PAGE);
		}
	}
}
