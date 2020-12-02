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
import by.epamtc.shamuradova.ishop.constant.RequestNameParameters;
import by.epamtc.shamuradova.ishop.constant.SessionNameParameters;
import by.epamtc.shamuradova.ishop.controller.command.Command;
import by.epamtc.shamuradova.ishop.service.OrderService;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;
import by.epamtc.shamuradova.ishop.service.factory.ServiceFactory;

/**
 * Комманда, предназначенная для загрузки следующей "порции" заказов для
 * пользователя Shopper, отображается конкретная страница, считываемая с запроса
 * С запроса считываются: int pageNumber - конкретная страница для отображения
 * заказов int pageCount - количество всех страниц, нужен для того, чтобы
 * отображать или не отображать кнопку "загрузить дальше"
 * 
 * В объект класса HttpServletRequest передаются pageNumber, pageCount,
 * List<Order>
 * 
 * Command designed to load the next "portion" of orders for User Shopper, the
 * specific page read from the request is displayed Read from the request: int
 * pageNumber - specific page to display orders int pageCount - the number of
 * all pages, needed to display or not display the "load next" button
 *
 * The object of the HttpServletRequest class is passed pageNumber, pageCount,
 * List <Order>
 * 
 * @author Victoria Shamuradova 2020
 * 
 */

public class MoreOrdersCommand implements Command {

	private OrderService orderService;

	private static final String NAME_CURRENT_PAGE = "/WEB-INF/jsp/all_my_orders.jsp";
	private static final String NAME_CURRENT_COMMAND = "controller?command=LOAD_MORE_ORDERS";
	private static final String ERROR_COMMAND = "controller?command=GET_ERROR_PAGE";

	public MoreOrdersCommand() {
		orderService = ServiceFactory.getInstance().getOrderService();
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			final HttpSession session = req.getSession();
			
			User user = (User) session.getAttribute(SessionNameParameters.USER);

			int pageNumber = Integer.parseInt(req.getParameter(RequestNameParameters.PAGE_NUMBER));
			int pageCount = Integer.parseInt(req.getParameter(RequestNameParameters.PAGE_COUNT));

			List<Order> orders = orderService.listMyOrders(user, pageNumber, PerPage.ORDERS_ON_PAGE);

			req.setAttribute(RequestNameParameters.ORDERS, orders);
			req.setAttribute(RequestNameParameters.PAGE_NUMBER, pageNumber);
			req.setAttribute(RequestNameParameters.PAGE_COUNT, pageCount);
			req.setAttribute(RequestNameParameters.REDIRECT_TO, NAME_CURRENT_COMMAND);

			req.getRequestDispatcher(NAME_CURRENT_PAGE).forward(req, resp);

		} catch (ServiceException e) {
			e.printStackTrace();
			resp.sendRedirect(ERROR_COMMAND);
		}
	}
}
