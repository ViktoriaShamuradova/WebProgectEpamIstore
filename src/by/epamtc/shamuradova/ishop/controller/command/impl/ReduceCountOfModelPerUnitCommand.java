package by.epamtc.shamuradova.ishop.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.shamuradova.ishop.bean.ShopCart;
import by.epamtc.shamuradova.ishop.bean.entity.User;
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
	private static final String USER = "user";
	private static final String SHOP_CART = "shopcart";
	private static final String MODEL_ID = "idModel";
	private static final String CURRENT_COMMAND = "command";

	private static final String ERROR_COMMAND = "controller?command=GET_ERROR_PAGE";
	private static final String NAME_NEXT_COMMAND = "controller?command=cart_page";
	private static final String NAME_CURRENT_COMMAND = "controller?command=REDUCE_COUNT_OF_GOODS_PER_UNIT";

	private CartService cartService;

	public ReduceCountOfModelPerUnitCommand() {
		cartService = ServiceFactory.getInstance().getCartService();
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			final HttpSession session = req.getSession();
			session.removeAttribute(CURRENT_COMMAND);
			session.setAttribute(CURRENT_COMMAND, NAME_CURRENT_COMMAND);

			ShopCart shopCart = (ShopCart) session.getAttribute(SHOP_CART);
			int idModel = Integer.parseInt(req.getParameter(MODEL_ID));
			User user = (User) session.getAttribute(USER);
			
			cartService.updateCartReduce(shopCart, idModel, PER_UNIT, user);
			resp.sendRedirect(NAME_NEXT_COMMAND);

		} catch (ServiceException e) {
			e.printStackTrace();
			resp.sendRedirect(ERROR_COMMAND);
		}
	}
}
