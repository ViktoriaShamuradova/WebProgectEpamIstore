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

/**
 * Комманда, предназначенная для загрузки следующей "порции" заказов для
 * пользователя Shopper, отображается конкретная страница, считываемая с запроса
 * С запроса считываются:
 * int pageNumber - конкретная страница для отображения заказов
 * int pageCount - количество всех страниц, нужен для того, чтобы отображать или не отображать кнопку "загрузить дальше"
 * 
 * В объект класса HttpServletRequest передаются pageNumber, pageCount, List<Order>
 * 
 * @author Шамурадова Виктория
 * 
 */

public class MoreOrdersCommand implements Command {

	private OrderService orderService;
	
	private static final String PAGE_NUMBER = "pageNumber";
	private static final String NAME_BEANS = "orders";
	private static final String PAGE_COUNT = "pageCount";
	
	private static final String NAME_CURRENT_PAGE = "/WEB-INF/jsp/all_my_orders.jsp";

	public MoreOrdersCommand() {
		orderService = ServiceFactory.getInstance().getOrderService();
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			HttpSession session = req.getSession();
			User user = (User) session.getAttribute("user");
			
			int pageNumber = Integer.parseInt(req.getParameter(PAGE_NUMBER));
			int pageCount = Integer.parseInt(req.getParameter(PAGE_COUNT));

			List<Order> orders = orderService.listMyOrders(user, pageNumber, PerPage.ORDERS_ON_PAGE);

			req.setAttribute(NAME_BEANS, orders);
			req.setAttribute(PAGE_NUMBER, pageNumber);
			req.setAttribute(PAGE_COUNT, pageCount);

			req.getRequestDispatcher(NAME_CURRENT_PAGE).forward(req, resp);
		
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
}
