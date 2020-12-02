package by.epamtc.shamuradova.ishop.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.shamuradova.ishop.bean.ShopCart;
import by.epamtc.shamuradova.ishop.bean.entity.User;
import by.epamtc.shamuradova.ishop.constant.RequestNameParameters;
import by.epamtc.shamuradova.ishop.constant.SessionNameParameters;
import by.epamtc.shamuradova.ishop.controller.command.Command;
import by.epamtc.shamuradova.ishop.service.CartService;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;
import by.epamtc.shamuradova.ishop.service.factory.ServiceFactory;


/**
 * Комманда для yменьшения количества товара на единицу в корзине
 * 
 * Command for decreasing the quantity of goods by one in the cart
 * 
 * @author Victoria Shamuradova 2020
 */

public class ReduceCountOfModelPerUnitCommand implements Command {

	private static final int PER_UNIT = 1;

	private static final String ERROR_COMMAND = "controller?command=GET_ERROR_PAGE";
	private static final String NAME_NEXT_COMMAND = "controller?command=cart_page";

	private CartService cartService;

	public ReduceCountOfModelPerUnitCommand() {
		cartService = ServiceFactory.getInstance().getCartService();
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			final HttpSession session = req.getSession();
			
			ShopCart shopCart = (ShopCart) session.getAttribute(SessionNameParameters.SHOP_CART);
			int idModel = Integer.parseInt(req.getParameter(RequestNameParameters.MODEL_ID));
			User user = (User) session.getAttribute(SessionNameParameters.USER);
			
			cartService.updateCartReduce(shopCart, idModel, PER_UNIT, user);
			resp.sendRedirect(NAME_NEXT_COMMAND);

		} catch (ServiceException e) {
			e.printStackTrace();
			resp.sendRedirect(ERROR_COMMAND);
		}
	}
}
