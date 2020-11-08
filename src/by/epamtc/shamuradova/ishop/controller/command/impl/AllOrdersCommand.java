package by.epamtc.shamuradova.ishop.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
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

public class AllOrdersCommand implements Command {

	private OrderService orderService;

	private static final int FIRST_PAGE = 1;
	
	private static final String CURRENT_COMMAND = "controller?command=ALL_ORDERS";
	private static final String CURRENT_PAGE = "/WEB-INF/jsp/order_list_admin.jsp";
	private static final String ERROR_PAGE = "controller?command=GET_ERROR_PAGE";
	
	private static final String REQ_ATTRIBUTE_BEAN = "orders";
	private static final String REQ_ATTRIBUTE_COUNT = "pageCount";
	private static final String REQ_ATTRIBUTE_PER_PAGE = "ordersPerPage";
	private static final String REQ_ATTRIBUTE_PAGE_NUMBER = "pageNumber";
	private static final String REQ_ATTRIBUTE_BEAN_COUNT = "ordersCount";

	public AllOrdersCommand() {
		orderService = ServiceFactory.getInstance().getOrderService();
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {

			HttpSession session = req.getSession();
			User user = (User) session.getAttribute("user");

			String pageNumberString = req.getParameter("pageNumber");
			int pageNumber = pageNumberString == null ? FIRST_PAGE : Integer.parseInt(pageNumberString);

			List<Order> orders = orderService.getAllOrders(user, pageNumber, PerPage.ORDERS_ON_PAGE);
			int orderCount = orderService.countOrders();

			req.setAttribute(REQ_ATTRIBUTE_BEAN, orders);
			req.setAttribute(REQ_ATTRIBUTE_COUNT, getPageCount(orderCount, PerPage.ORDERS_ON_PAGE));
			req.setAttribute(REQ_ATTRIBUTE_PER_PAGE, PerPage.ORDERS_ON_PAGE);
			req.setAttribute(REQ_ATTRIBUTE_PAGE_NUMBER, pageNumber);
			req.setAttribute(REQ_ATTRIBUTE_BEAN_COUNT, orderCount);

			req.setAttribute("command", CURRENT_COMMAND);

			RequestDispatcher dispatcher = req.getRequestDispatcher(CURRENT_PAGE);
			dispatcher.forward(req, resp);

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
