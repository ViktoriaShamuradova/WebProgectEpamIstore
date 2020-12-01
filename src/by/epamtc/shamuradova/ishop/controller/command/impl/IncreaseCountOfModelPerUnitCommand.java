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
import by.epamtc.shamuradova.ishop.service.exception.ValidationException;
import by.epamtc.shamuradova.ishop.service.factory.ServiceFactory;

/**
 * Класс-комманда для увеличения товара в корзине на единицу
 * 
 * Class-command for increasing the item in the cart by one
 *
 * @author Victoria Shamuradova 2020
 */

public class IncreaseCountOfModelPerUnitCommand implements Command {

	private static final int PER_UNIT = 1;
	private static final String CURRENT_MESSAGE = "current_message";
	private static final String CURRENT_COMMAND = "command";
	private static final String SHOP_CART = "shopcart";
	private static final String MODEL_ID = "idModel";
	private static final String USER = "user";

	private static final String ERROR_COMMAND = "controller?command=GET_ERROR_PAGE";
	private static final String NAME_CURRENT_COMMAND = "controller?command=INCREASE_COUNT_OF_GOODS_PER_UNIT";
	private static final String CART_PAGE_COMMAND = "controller?command=cart_page";
	private static final String CART_PAGE = "/WEB-INF/jsp/page/cart.jsp";

	private CartService cartService;

	public IncreaseCountOfModelPerUnitCommand() {
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

			cartService.updateCartIncrease(shopCart, idModel, PER_UNIT, user);
			resp.sendRedirect(CART_PAGE_COMMAND);

		} catch (ValidationException e) {
			req.setAttribute(CURRENT_MESSAGE, e.getMessage());
			req.getRequestDispatcher(CART_PAGE).forward(req, resp);

		} catch (ServiceException e) {
			resp.sendRedirect(ERROR_COMMAND);
		}
	}
}
