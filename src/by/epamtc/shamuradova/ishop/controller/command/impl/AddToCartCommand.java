package by.epamtc.shamuradova.ishop.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.shamuradova.ishop.bean.CartContent;
import by.epamtc.shamuradova.ishop.bean.ShopCart;
import by.epamtc.shamuradova.ishop.bean.entity.Cart;
import by.epamtc.shamuradova.ishop.bean.entity.User;
import by.epamtc.shamuradova.ishop.constant.RequestNameParameters;
import by.epamtc.shamuradova.ishop.constant.SessionNameParameters;
import by.epamtc.shamuradova.ishop.controller.command.Command;
import by.epamtc.shamuradova.ishop.service.CartService;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;
import by.epamtc.shamuradova.ishop.service.factory.ServiceFactory;

/**
 * Комманда, предназначенная для добавления товара в корзину
 * 
 * Command for adding a product to the cart
 * 
 * @author Victoria Shamuradova 2020
 */
public class AddToCartCommand implements Command {

	private CartService cartService;

	private static final String ENTER_PAGE_COMMAND = "controller?command=enter_page";
	private static final String SHOPPER_PAGE = "controller?command=ALL_MODELS_OR_BY_CATEGORY";
	private static final String ERROR_COMMAND = "controller?command=GET_ERROR_PAGE";

	private static final int FIRST_PRODUCT = 1;

	public AddToCartCommand() {
		cartService = ServiceFactory.getInstance().getCartService();
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final HttpSession session = req.getSession();

		User user = (User) session.getAttribute(SessionNameParameters.USER);
		int idModel = Integer.parseInt(req.getParameter(RequestNameParameters.MODEL_ID));

		if (user == null) {
			req.getRequestDispatcher(ENTER_PAGE_COMMAND).forward(req, resp);
		}

		try {
			Cart cart = cartService.getCartByUserId(user);

			if (cart == null) {
				cartService.createCart(user);
				cart = cartService.getCartByUserId(user);
				cartService.createCartItem(formCartContent(cart.getId(), FIRST_PRODUCT, idModel));

			} else {
				cartService.updateCart(user, idModel);
			}

			ShopCart shopCart = cartService.formNewShopCart(user);

			session.setAttribute(SessionNameParameters.SHOP_CART, shopCart);
			resp.sendRedirect(SHOPPER_PAGE);

		} catch (ServiceException e) {
			e.printStackTrace();
			resp.sendRedirect(ERROR_COMMAND);
		}
	}

	private CartContent formCartContent(int cartId, int count, int modelId) {
		CartContent content = new CartContent();
		content.setCartId(cartId);
		content.setCount(count);
		content.setModelId(modelId);

		return content;
	}
}
