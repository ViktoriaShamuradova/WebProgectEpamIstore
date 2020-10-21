package by.epamtc.shamuradova.ishop.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.shamuradova.ishop.bean.ShopCart;
import by.epamtc.shamuradova.ishop.bean.User;
import by.epamtc.shamuradova.ishop.controller.command.Command;
import by.epamtc.shamuradova.ishop.service.CartService;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;
import by.epamtc.shamuradova.ishop.service.factory.ServiceFactory;

public class IncreaseCountOfModelPerUnitCommand implements Command {

	private static final int PER_UNIT = 1;
	private CartService cartService;

	public IncreaseCountOfModelPerUnitCommand() {
		cartService = ServiceFactory.getInstance().getCartService();
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		ShopCart shopCart = (ShopCart) session.getAttribute("shopcart");
		int idModel = Integer.parseInt(req.getParameter("idModel"));
		User user = (User)session.getAttribute("user");
		
		try {
			cartService.updateCartIncrease(shopCart, idModel, PER_UNIT);
			resp.sendRedirect("controller?command=cart_page");

		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
