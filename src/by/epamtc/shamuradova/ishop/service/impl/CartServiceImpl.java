package by.epamtc.shamuradova.ishop.service.impl;

import java.sql.Date;
import java.util.List;

import by.epamtc.shamuradova.ishop.bean.CartContent;
import by.epamtc.shamuradova.ishop.bean.ShopCart;
import by.epamtc.shamuradova.ishop.bean.ShopCartItem;
import by.epamtc.shamuradova.ishop.bean.entity.Cart;
import by.epamtc.shamuradova.ishop.bean.entity.CartItem;
import by.epamtc.shamuradova.ishop.bean.entity.Model;
import by.epamtc.shamuradova.ishop.bean.entity.User;
import by.epamtc.shamuradova.ishop.dao.CartDAO;
import by.epamtc.shamuradova.ishop.dao.ModelDAO;
import by.epamtc.shamuradova.ishop.dao.exception.DAOException;
import by.epamtc.shamuradova.ishop.dao.factory.DAOFactory;
import by.epamtc.shamuradova.ishop.service.CartService;
import by.epamtc.shamuradova.ishop.service.exception.ResourceNotFoundServiceException;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;
import by.epamtc.shamuradova.ishop.service.validation.CartValidation;
import by.epamtc.shamuradova.ishop.service.validation.ModelValidation;
import by.epamtc.shamuradova.ishop.service.validation.UserValidation;

/**
 * Класс, реализующий интерфейс CartService, в котром содержатся методы для
 * объектов Cart, CartItem, ShopCart, ShopCartItem. В этом классе проводится их
 * валидация
 * 
 * A class that implements the CartService interface, which contains methods for
 * Cart, CartItem, ShopCart, ShopCartItem objects. In this class, they are
 * validated.
 *
 * @author Victoria Shamuradova 2020
 */
public class CartServiceImpl implements CartService {

	private CartDAO cartDAO;
	private ModelDAO modelDAO;

	public CartServiceImpl() {
		cartDAO = DAOFactory.getInstance().getCartDAO();
		modelDAO = DAOFactory.getInstance().getModelDAO();
	}

	@Override
	public Cart getCartByUserId(User user) throws ServiceException {
		try {
			UserValidation.checkRoleShopper(user);
			Cart cart = cartDAO.getCartByUserId(user.getId());
			return cart;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void createCart(User user) throws ServiceException {
		try {
			UserValidation.checkRoleShopper(user);
			Date date = new Date(System.currentTimeMillis());
			cartDAO.addCart(user.getId(), date);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void createCartItem(CartContent content) throws ServiceException {
		try {
			cartDAO.addCartItem(content);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public ShopCart formNewShopCart(User user) throws ServiceException {
		try {
			UserValidation.checkRoleShopper(user);

			ShopCart shopCart = new ShopCart();

			Cart cart = cartDAO.getCartByUserId(user.getId());

			CartValidation.checkCart(cart);

			List<ShopCartItem> items = cartDAO.getShopCartItems(cart.getId());

			for (ShopCartItem item : items) {
				shopCart.addShopCartItem(item);
			}

			return shopCart;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void updateCartReduce(ShopCart shopCart, int idModel, int countToReduce, User user) throws ServiceException {

		try {
			UserValidation.checkRoleShopper(user);

			ModelValidation.checkModel(modelDAO.getModelById(idModel));

			if (shopCart.getShopCartItem(idModel) == null) {
				throw new ResourceNotFoundServiceException("not found");
			}

			Cart cart = cartDAO.getCartByUserId(user.getId());

			shopCart.removeModel(idModel, countToReduce);

			if (shopCart.isEmpty()) {
				cartDAO.deleteCartByidUser(user.getId());

			} else {
				// если содержит модель, то корректируем количество, иначе удаляем запись этой
				// модели в cart_item
				if (shopCart.containsIdModel(idModel)) {
					ShopCartItem item = shopCart.getShopCartItem(idModel);
					int count = item.getCount();
					cartDAO.updateCartItemCountByModelIdCartId(idModel, count, cart.getId());

				} else {
					cartDAO.deleteCartItemByIdModelCartId(idModel, cart.getId());
				}
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void updateCartIncrease(ShopCart shopCart, int modelId, int count, User user) throws ServiceException {

		try {
			Model model = modelDAO.getModelById(modelId);
			ModelValidation.checkModel(model);

			if (shopCart.getShopCartItem(modelId) == null) {
				throw new ResourceNotFoundServiceException("not found");
			}

			shopCart.addShopCartItem(model, count);
			int countAfterIncrease = shopCart.getShopCartItem(modelId).getCount();

			Cart cart = cartDAO.getCartByUserId(user.getId());
			CartValidation.checkCart(cart);

			cartDAO.updateCartItemCountByModelIdCartId(modelId, countAfterIncrease, cart.getId());

		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void updateCart(User user, int modelId) throws ServiceException {

		try {
			Cart cart = getCartByUserId(user);
			CartValidation.checkCart(cart);

			ModelValidation.checkModel(modelDAO.getModelById(modelId));

			CartItem c = cartDAO.getCartItemByCartIdModelId(cart.getId(), modelId);

			if (c == null) {
				cartDAO.addCartItem(new CartContent(cart.getId(), modelId, 1));
			} else {
				c.setCount(c.getCount() + 1);
				cartDAO.updateCartItemCountByModelIdCartId(modelId, c.getCount(), cart.getId());
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}
