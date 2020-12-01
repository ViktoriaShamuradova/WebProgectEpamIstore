package by.epamtc.shamuradova.ishop.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.shamuradova.ishop.bean.entity.User;
import by.epamtc.shamuradova.ishop.constant.RequestNameParameters;
import by.epamtc.shamuradova.ishop.constant.SessionNameParameters;
import by.epamtc.shamuradova.ishop.constant.UserRole;
import by.epamtc.shamuradova.ishop.controller.command.Command;
import by.epamtc.shamuradova.ishop.service.OrderService;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;
import by.epamtc.shamuradova.ishop.service.factory.ServiceFactory;

public class ChangeStatusOrderCommand implements Command {

	private OrderService orderService;

	private static final String ERROR_COMMAND = "controller?command=GET_ERROR_PAGE";
	private static final String ADMIN_COMMAND = "controller?command=ALL_ORDERS";
	private static final String SHOPPER_COMMAND = "controller?command=MY_ORDERS";
	
	public ChangeStatusOrderCommand() {
		orderService = ServiceFactory.getInstance().getOrderService();
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			final HttpSession session = req.getSession();
				
			User user = (User) session.getAttribute(SessionNameParameters.USER);

			int orderId = Integer.parseInt(req.getParameter(RequestNameParameters.ORDER_ID));

			orderService.changeStatusOrder(user, orderId);
			
			if (user.getRole().equals(UserRole.ADMIN)) {
				resp.sendRedirect(ADMIN_COMMAND);
			} else {
				resp.sendRedirect(SHOPPER_COMMAND);
			}
	
		} catch (ServiceException e) {
			e.printStackTrace();
			resp.sendRedirect(ERROR_COMMAND);
		}
	}
}
