package by.epamtc.shamuradova.ishop.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.shamuradova.ishop.bean.ShopCart;
import by.epamtc.shamuradova.ishop.bean.User;
import by.epamtc.shamuradova.ishop.controller.command.Command;
import by.epamtc.shamuradova.ishop.service.OrderService;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;
import by.epamtc.shamuradova.ishop.service.factory.ServiceFactory;
//проверить на нал
public class FormOrderCommand implements Command {

	private static final String CREATE_ORDER_PAGE = "controller?command=create_order&idOrder=";
	private OrderService orderService;
	private static final String CURRENT_MESSAGE = "current_message";

	public FormOrderCommand() {
		orderService = ServiceFactory.getInstance().getOrderService();
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		ShopCart shopCart = (ShopCart) session.getAttribute("shopcart");
		User user = (User) session.getAttribute("user");

		try {
			int orderId = orderService.makeOrder(shopCart, user.getId());
			// или просто установить значение нал
			session.removeAttribute("shopcart");
			session.setAttribute(CURRENT_MESSAGE, "Order created successfully. please wait for our reply");
			
			//или в сессию положить. попробовать пока так
			resp.sendRedirect(CREATE_ORDER_PAGE + orderId);

		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
