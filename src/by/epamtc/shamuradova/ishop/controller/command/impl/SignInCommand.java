package by.epamtc.shamuradova.ishop.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.shamuradova.ishop.bean.AuthData;
import by.epamtc.shamuradova.ishop.bean.ShopCart;
import by.epamtc.shamuradova.ishop.bean.entity.User;
import by.epamtc.shamuradova.ishop.constant.UserRole;
import by.epamtc.shamuradova.ishop.controller.command.Command;
import by.epamtc.shamuradova.ishop.service.CartService;
import by.epamtc.shamuradova.ishop.service.SignInService;
import by.epamtc.shamuradova.ishop.service.exception.BlackListException;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;
import by.epamtc.shamuradova.ishop.service.exception.ValidationException;
import by.epamtc.shamuradova.ishop.service.exception.WrongAuthDataException;
import by.epamtc.shamuradova.ishop.service.factory.ServiceFactory;

/**
 * Команда предназначенная для входа пользователя в систему
 * 
 * @author Виктория Шамурадова 2020
 */

public class SignInCommand implements Command {

	private static final String GET_ADMIN_COMMAND = "controller?command=GET_ADMIN_PAGE";
	private static final String GET_SHOPPER_COMMAND = "controller?command=ALL_MODELS_OR_BY_CATEGORY";

	private static final String ENTER_PAGE_COMMAND = "controller?command=ENTER_PAGE";
	private static final String MAIN_PAGE = "controller?command=GET_MAIN_ALL_MODELS_OR_BY_CATEGORY_PAGE";
	private static final String CURRENT_MESSAGE = "current_message";

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

			// проверяем, есть ли у юзера в бд запись о наличии корзины. Если есть, то
			// формируем корзину и вставляем в сессию, чтобы ее отобразать
			if (cartService.getCartByUserId(user.getId()) != null) {
				ShopCart shopCart = cartService.formNewShopCart(user.getId());
				session.setAttribute("shopcart", shopCart);
			}
			session.setAttribute(CURRENT_MESSAGE, "Welcome " + user.getName());
			resp.sendRedirect(url);

		} catch (ValidationException e) {
			session.setAttribute(CURRENT_MESSAGE, "login or password not valid, please try again");
			session.removeAttribute("user");
			resp.sendRedirect(ENTER_PAGE_COMMAND);
		} catch (WrongAuthDataException e) {
			session.setAttribute(CURRENT_MESSAGE, "login or password is wrong");
			session.removeAttribute("user");
			resp.sendRedirect(ENTER_PAGE_COMMAND);
		} catch (BlackListException e) {
			session.setAttribute(CURRENT_MESSAGE, e.getMessage());
			session.removeAttribute("user");
			resp.sendRedirect(MAIN_PAGE);
		} catch (ServiceException e) {
			session.setAttribute(CURRENT_MESSAGE, "Sorry, we have some problems, visit us later");
			session.removeAttribute("user");
			resp.sendRedirect(MAIN_PAGE);
		}
	}

}
