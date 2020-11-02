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

public class MoreOrdersCommand implements Command {

	private OrderService orderService;

	public MoreOrdersCommand() {
		orderService = ServiceFactory.getInstance().getOrderService();
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			HttpSession session = req.getSession();
			User user = (User) session.getAttribute("user");
			int pageNumber = Integer.parseInt(req.getParameter("pageNumber"));
			int pageCount = Integer.parseInt(req.getParameter("pageCount"));
			
			
			
			int idUser = user.getId();//редусмотреть nullpointer, если вылогинимся, может такое случиться, нужно фильтр под это создать

			List<Order> orders = orderService.listMyOrders(idUser, pageNumber,
					PerPage.ORDERS_PER_PAGE);
			

			req.setAttribute("orders", orders);
			req.setAttribute("pageNumber", pageNumber);
			req.setAttribute("pageCount", pageCount);

			req.getRequestDispatcher("/WEB-INF/jsp/all_orders.jsp").forward(req, resp);

		} catch (ServiceException e) {
			e.printStackTrace();
		}

	}

}
