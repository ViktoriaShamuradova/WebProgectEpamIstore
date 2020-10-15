package by.epamtc.shamuradova.ishop.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.shamuradova.ishop.bean.Cart;
import by.epamtc.shamuradova.ishop.bean.Model;
import by.epamtc.shamuradova.ishop.bean.User;
import by.epamtc.shamuradova.ishop.controller.command.Command;
import by.epamtc.shamuradova.ishop.service.CartService;
import by.epamtc.shamuradova.ishop.service.ModelService;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;
import by.epamtc.shamuradova.ishop.service.factory.ServiceFactory;

public class AddToCartCommand implements Command {

	private CartService cartService;
	private static final String GET_ENTER_PAGE_COMMAND = "controller?command=enter_page";

	public AddToCartCommand() {
		cartService = ServiceFactory.getInstance().getCartService();
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		
		if (session == null || user == null) {
			req.getRequestDispatcher(GET_ENTER_PAGE_COMMAND).forward(req, resp);

		}
		
		try {
			if (cartService.getCartByUserId(user.getId()) == null) {
				cartService.createCart(user.getId());
				Cart cart = cartService.getCartByUserId(user.getId());
				cartService.createCartItem()
				
				
			}
			
			
			
			
		} catch (ServiceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			//создаем корзину, корзину итем
			//если есть такой айди
//		}
//
//		int modelId = Integer.parseInt(req.getParameter("modelId"));
//		serviceAddModelToCard.addModel(card.getId(), modelId);
//		// Проверить существует ли товар. Если нет - исключение
//
//		// Проверить существует ли корзина для данного пользователя, если нет - создать
			
			

			ModelService modelService = ServiceFactory.getInstance().getModelService();
			List<Model> models = null;
			try {
				models = modelService.listAllModels(1, 12);
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			req.setAttribute("models", models);

	}

}
