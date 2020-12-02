package by.epamtc.shamuradova.ishop.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.shamuradova.ishop.bean.ShopCart;
import by.epamtc.shamuradova.ishop.bean.entity.User;
import by.epamtc.shamuradova.ishop.constant.SessionNameParameters;
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
	private static final String CREATE_ORDER_PAGE = "controller?command=order_detailes&orderId=";

	private OrderService orderService;

	public FormOrderCommand() {
		orderService = ServiceFactory.getInstance().getOrderService();
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			final HttpSession session = req.getSession();
			
			ShopCart shopCart = (ShopCart) session.getAttribute(SessionNameParameters.SHOP_CART);
			User user = (User) session.getAttribute(SessionNameParameters.USER);

			int orderId = orderService.makeOrder(shopCart, user);

			session.removeAttribute(SessionNameParameters.SHOP_CART);
			session.setAttribute(SessionNameParameters.CURRENT_MESSAGE, "Order created successfully. please wait for our reply");

			resp.sendRedirect(CREATE_ORDER_PAGE + orderId);

		} catch (ServiceException e) {
			e.printStackTrace();
			resp.sendRedirect(ERROR_COMMAND);
		}
	}
}
