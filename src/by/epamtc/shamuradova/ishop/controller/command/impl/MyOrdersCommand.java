package by.epamtc.shamuradova.ishop.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.shamuradova.ishop.bean.entity.Order;
import by.epamtc.shamuradova.ishop.bean.entity.User;
import by.epamtc.shamuradova.ishop.constant.PerPage;
import by.epamtc.shamuradova.ishop.controller.command.Command;
import by.epamtc.shamuradova.ishop.service.OrderService;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;
import by.epamtc.shamuradova.ishop.service.factory.ServiceFactory;

public class MyOrdersCommand implements Command {

	private OrderService orderService;
	
	private static final String CURRENT_MESSAGE = "current_message";
	
	private static final String PAGE_NUMBER = "pageNumber";
	private static final String NAME_BEANS = "orders";
	private static final String PAGE_COUNT = "pageCount";
	
	private static final String NAME_CURRENT_PAGE = "/WEB-INF/jsp/all_my_orders.jsp";
	private static final String ERROR_PAGE = "controller?command=GET_ERROR_PAGE";

	public MyOrdersCommand() {
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
	
			List<Order> orders = orderService.listMyOrders(user, 1, PerPage.ORDERS_ON_PAGE);
			int orderCount = orderService.countOrders(user.getId());

			req.setAttribute(NAME_BEANS, orders);
			req.setAttribute(PAGE_COUNT, getPageCount(orderCount, PerPage.ORDERS_ON_PAGE));
			req.setAttribute(PAGE_NUMBER, 1);
			
			req.getRequestDispatcher(NAME_CURRENT_PAGE).forward(req, resp);

		} catch (ServiceException e) {
			e.printStackTrace();
			resp.sendRedirect(ERROR_PAGE);
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
