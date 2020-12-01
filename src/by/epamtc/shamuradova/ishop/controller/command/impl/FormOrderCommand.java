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

/**
 * Класс-комманда, предназначенная для формирования нового заказа
 * 
 * Class-command, designed to form a new order
 *
 * @author Victoria Shamuradova 2020
 */
public class FormOrderCommand implements Command {

	private static final String ERROR_COMMAND = "controller?command=GET_ERROR_PAGE";
	private static final String CREATE_ORDER_PAGE = "controller?command=order_detailes&idOrder=";
	private static final String NAME_CURRENT_COMMAND = "controller?command=FORM_ORDER";

	private static final String CURRENT_MESSAGE = "current_message";
	private static final String SHOP_CART = "shopcart";
	private static final String USER = "user";
	private static final String CURRENT_COMMAND = "command";

	private OrderService orderService;

	public FormOrderCommand() {
		orderService = ServiceFactory.getInstance().getOrderService();
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			final HttpSession session = req.getSession();
			session.removeAttribute(CURRENT_COMMAND);
			session.setAttribute(CURRENT_COMMAND, NAME_CURRENT_COMMAND);

			ShopCart shopCart = (ShopCart) session.getAttribute(SHOP_CART);
			User user = (User) session.getAttribute(USER);

			int orderId = orderService.makeOrder(shopCart, user);

			session.removeAttribute(SHOP_CART);
			session.setAttribute(CURRENT_MESSAGE, "Order created successfully. please wait for our reply");

			resp.sendRedirect(CREATE_ORDER_PAGE + orderId);

		} catch (ServiceException e) {
			e.printStackTrace();
			resp.sendRedirect(ERROR_COMMAND);
		}
	}
}
