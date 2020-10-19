package by.epamtc.shamuradova.ishop.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.shamuradova.ishop.bean.Cart;
import by.epamtc.shamuradova.ishop.bean.CartContent;
import by.epamtc.shamuradova.ishop.bean.Model;
import by.epamtc.shamuradova.ishop.bean.ShopCart;
import by.epamtc.shamuradova.ishop.bean.User;
import by.epamtc.shamuradova.ishop.controller.command.Command;
import by.epamtc.shamuradova.ishop.service.CartService;
import by.epamtc.shamuradova.ishop.service.ModelService;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;
import by.epamtc.shamuradova.ishop.service.factory.ServiceFactory;

public class AddToCartCommand implements Command {

	private CartService cartService;
	private ModelService modelService;
	private static final String GET_ENTER_PAGE_COMMAND = "controller?command=enter_page";
	private static final String SHOPPER_PAGE = "controller?command=GET_SHOPPER_PAGE";

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
		if (session == null || user == null) {
			req.getRequestDispatcher(GET_ENTER_PAGE_COMMAND).forward(req, resp);

		}

		try {

			Model model = modelService.getModel(idModel);
			if (cartService.getCartByUserId(user.getId()) == null) {

				cartService.createCart(user.getId());
				cart = cartService.getCartByUserId(user.getId());

				CartContent content = new CartContent();
				content.setCartId(cart.getId());
				content.setCount(1);
				content.setModelId(model.getId());

				cartService.createCartItem(content);

			} else {
				cart = cartService.getCartByUserId(user.getId());
				CartContent content = new CartContent();
				content.setCartId(cart.getId());
				content.setCount(1);
				content.setModelId(model.getId());

				cartService.createCartItem(content);
			}

			ShopCart shopCart = cartService.formNewShopCart(user.getId());

			session.setAttribute("shopcart", shopCart);
			resp.sendRedirect(SHOPPER_PAGE);

		} catch (ServiceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
