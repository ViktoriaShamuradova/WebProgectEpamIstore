package by.epamtc.shamuradova.ishop.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.shamuradova.ishop.bean.Order;
import by.epamtc.shamuradova.ishop.bean.User;
import by.epamtc.shamuradova.ishop.constant.OrderConstant;
import by.epamtc.shamuradova.ishop.controller.command.Command;
import by.epamtc.shamuradova.ishop.service.OrderService;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;
import by.epamtc.shamuradova.ishop.service.factory.ServiceFactory;

public class MyOrdersCommand implements Command {

	private OrderService orderService;

	public MyOrdersCommand() {
		orderService = ServiceFactory.getInstance().getOrderService();
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			HttpSession session = req.getSession();
			User user = (User) session.getAttribute("user");
			int idUser = user.getId();

			List<Order> orders = orderService.listMyOrders(idUser, 1, OrderConstant.ORDERS_PER_PAGE);
			int orderCount = orderService.countOrders(idUser);

			req.setAttribute("orders", orders);
			req.setAttribute("pageCount", getPageCount(orderCount, OrderConstant.ORDERS_PER_PAGE));
			req.setAttribute("pageNumber", 1);
			
			req.getRequestDispatcher("/WEB-INF/jsp/all_orders.jsp").forward(req, resp);

		} catch (ServiceException e) {
			e.printStackTrace();
		}

	}

	private int getPageCount(int totalCount, int itemsPerCount) {

		int res = totalCount / itemsPerCount;
		if (res * itemsPerCount != totalCount) {
			res++;
		}
		return res;
	}

}
