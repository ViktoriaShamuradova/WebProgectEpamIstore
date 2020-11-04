package by.epamtc.shamuradova.ishop.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.shamuradova.ishop.bean.CartContent;
import by.epamtc.shamuradova.ishop.bean.ShopCart;
import by.epamtc.shamuradova.ishop.bean.entity.Cart;
import by.epamtc.shamuradova.ishop.bean.entity.Model;
import by.epamtc.shamuradova.ishop.bean.entity.User;
import by.epamtc.shamuradova.ishop.controller.command.Command;
import by.epamtc.shamuradova.ishop.service.CartService;
import by.epamtc.shamuradova.ishop.service.ModelService;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;
import by.epamtc.shamuradova.ishop.service.factory.ServiceFactory;

public class AddToCartCommand implements Command {

	private CartService cartService;
	private ModelService modelService;

	private static final String GET_ENTER_PAGE_COMMAND = "controller?command=enter_page";
	private static final String SHOPPER_PAGE = "controller?command=ALL_MODELS_OR_BY_CATEGORY";

	private static final int FIRST_PRODUCT = 1;

	public AddToCartCommand() {
		cartService = ServiceFactory.getInstance().getCartService();
		modelService = ServiceFactory.getInstance().getModelService();
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		int idModel = Integer.parseInt(req.getParameter("modelId"));

		Cart cart = null;

		if (user == null) {
			req.getRequestDispatcher(GET_ENTER_PAGE_COMMAND).forward(req, resp);
		}

		try {
			Model model = modelService.getModel(idModel);

			if (cartService.getCartByUserId(user.getId()) == null) {
				cartService.createCart(user.getId());
				cart = cartService.getCartByUserId(user.getId());

				cartService.createCartItem(formCartContent(cart.getId(), FIRST_PRODUCT, model.getId()));

			} else {
				cart = cartService.getCartByUserId(user.getId());
				cartService.updateCart(user.getId(), model.getId());
			}

			ShopCart shopCart = cartService.formNewShopCart(user.getId());

			session.setAttribute("shopcart", shopCart);
			resp.sendRedirect(SHOPPER_PAGE);

		} catch (ServiceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
