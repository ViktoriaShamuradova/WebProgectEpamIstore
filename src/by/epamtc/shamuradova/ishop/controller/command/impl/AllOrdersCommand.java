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
import by.epamtc.shamuradova.ishop.constant.RequestNameParameters;
import by.epamtc.shamuradova.ishop.constant.SessionNameParameters;
import by.epamtc.shamuradova.ishop.constant.UserRole;
import by.epamtc.shamuradova.ishop.controller.command.Command;
import by.epamtc.shamuradova.ishop.service.OrderService;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;
import by.epamtc.shamuradova.ishop.service.factory.ServiceFactory;

/**
 * Класс-комманда для отображения всех заказов для админа и покупателя. В
 * зависимости от роли управление предается на отображение заказов разным
 * страницам jsp, так как предусмотрена разная пагинация
 * 
 * Command class for displaying all orders for admin and customer. Depending on
 * the role, management is transferred to display orders to different jsp pages,
 * since different pagination is provided
 *
 * @author Victoria Shamuradova 2020
 */
public class AllOrdersCommand implements Command {

	private OrderService orderService;

	private static final int FIRST_PAGE = 1;

	private static final String NAME_CURRENT_COMMAND = "controller?command=ALL_ORDERS";
	private static final String CURRENT_ADMIN_PAGE = "/WEB-INF/jsp/order_list_admin.jsp";
	private static final String CURRENT_SHOPPER_PAGE = "/WEB-INF/jsp/all_my_orders.jsp";
	private static final String ERROR_COMMAND = "controller?command=GET_ERROR_PAGE";

	public AllOrdersCommand() {
		orderService = ServiceFactory.getInstance().getOrderService();
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			final HttpSession session = req.getSession();

			User user = (User) session.getAttribute(SessionNameParameters.USER);

			List<String> statuses = orderService.getAllStatuses();
			req.setAttribute(RequestNameParameters.STATUSES, statuses);

			if (user.getRole().equalsIgnoreCase(UserRole.ADMIN)) {
				allOrdersForAdmin(req, resp, user);
			}
			if (user.getRole().equalsIgnoreCase(UserRole.SHOPPER)) {
				allOrdersForShopper(req, resp, user);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
			resp.sendRedirect(ERROR_COMMAND);
		}
	}

	private void allOrdersForAdmin(HttpServletRequest req, HttpServletResponse resp, User user)
			throws ServiceException, ServletException, IOException {

		String pageNumberString = req.getParameter(RequestNameParameters.PAGE_NUMBER);
		int pageNumber = pageNumberString == null ? FIRST_PAGE : Integer.parseInt(pageNumberString);
		String status = req.getParameter(RequestNameParameters.STATUS);
		List<Order> orders;
		int orderCount;

		if (status == null || status.trim().isEmpty()) {
			orders = orderService.getAllOrders(user, pageNumber, PerPage.ORDERS_ON_PAGE);
			orderCount = orderService.countOrders();
		} else {
			orders = orderService.getOrdersByStatus(pageNumber, PerPage.ORDERS_ON_PAGE, user, status);
			orderCount = orderService.countOrdersByStatus(status);
			req.setAttribute("status", status);
		}

		req.setAttribute(RequestNameParameters.ORDERS, orders);
		req.setAttribute(RequestNameParameters.PAGE_COUNT, getPageCount(orderCount, PerPage.ORDERS_ON_PAGE));
		req.setAttribute(RequestNameParameters.ORDER_PER_PAGE, PerPage.ORDERS_ON_PAGE);
		req.setAttribute(RequestNameParameters.PAGE_NUMBER, pageNumber);
		req.setAttribute(RequestNameParameters.ORDER_COUNT, orderCount);

		req.setAttribute(RequestNameParameters.CURRENT_COMMAND, NAME_CURRENT_COMMAND);

		RequestDispatcher dispatcher = req.getRequestDispatcher(CURRENT_ADMIN_PAGE);
		dispatcher.forward(req, resp);

	}

	private int getPageCount(int totalCount, int itemsPerCount) {

		int res = totalCount / itemsPerCount;
		if (res * itemsPerCount != totalCount) {
			res++;
		}
		return res;
	}

	private void allOrdersForShopper(HttpServletRequest req, HttpServletResponse resp, User user)
			throws ServiceException, ServletException, IOException {

		List<Order> orders = orderService.listMyOrders(user, 1, PerPage.ORDERS_ON_PAGE);
		int orderCount = orderService.countOrders(user.getId());

		req.setAttribute(RequestNameParameters.ORDERS, orders);
		req.setAttribute(RequestNameParameters.PAGE_COUNT, getPageCount(orderCount, PerPage.ORDERS_ON_PAGE));
		req.setAttribute(RequestNameParameters.PAGE_NUMBER, 1);
		req.setAttribute(RequestNameParameters.REDIRECT_TO, NAME_CURRENT_COMMAND);

		req.getRequestDispatcher(CURRENT_SHOPPER_PAGE).forward(req, resp);

	}
}
