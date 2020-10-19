package by.epamtc.shamuradova.ishop.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.shamuradova.ishop.bean.AuthData;
import by.epamtc.shamuradova.ishop.bean.ShopCart;
import by.epamtc.shamuradova.ishop.bean.User;
import by.epamtc.shamuradova.ishop.constant.UserRole;
import by.epamtc.shamuradova.ishop.controller.command.Command;
import by.epamtc.shamuradova.ishop.service.CartService;
import by.epamtc.shamuradova.ishop.service.SignInService;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;
import by.epamtc.shamuradova.ishop.service.factory.ServiceFactory;

/**
 * Команда предназначенная для входа пользователя в систему
 * 
 * @author Виктория Шамурадова 2020
 */

//yбрать маг значения
public class SignInCommand implements Command {
	private static final String GET_ADMIN_COMMAND = "controller?command=GET_ADMIN_PAGE";
	private static final String GET_SHOPPER_COMMAND = "controller?command=GET_SHOPPER_PAGE";

	private SignInService signInService;
	private CartService cartService;

	public SignInCommand() {
		signInService = ServiceFactory.getInstance().getSignInService();
		cartService = ServiceFactory.getInstance().getCartService();
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final HttpSession session = req.getSession(true);

		AuthData authData = new AuthData();
		authData.setLogin(req.getParameter("login"));
		authData.setPassword(req.getParameter("password").toCharArray());

		User user = null;

		// определяем к чему отнрсится юзер
		try {
			user = signInService.signIn(authData);
			session.setAttribute("user", user);

			String url = null;

			switch (user.getRole()) {
			case UserRole.ADMIN:
				url = GET_ADMIN_COMMAND;
				break;
			case UserRole.SHOPPER:
				url = GET_SHOPPER_COMMAND;
				break;
			}

			// проверяем, есть ли у юзера в бд запись о наличии корзины. Если есть, то формируем корзину и вставляем в сессию для еек отображения

			if (cartService.getCartByUserId(user.getId()) != null) {
				ShopCart shopCart = cartService.formNewShopCart(user.getId());
				session.setAttribute("shopcart", shopCart);
			}

			resp.sendRedirect(url);

		} catch (ServiceException e) {
			throw new ServletException(e);
		}
	}

}
