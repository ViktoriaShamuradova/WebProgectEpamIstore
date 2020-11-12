package by.epamtc.shamuradova.ishop.service.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import by.epamtc.shamuradova.ishop.bean.CartContent;
import by.epamtc.shamuradova.ishop.bean.ShopCart;
import by.epamtc.shamuradova.ishop.bean.ShopCartItem;
import by.epamtc.shamuradova.ishop.bean.entity.Cart;
import by.epamtc.shamuradova.ishop.bean.entity.CartItem;
import by.epamtc.shamuradova.ishop.bean.entity.Model;
import by.epamtc.shamuradova.ishop.bean.entity.User;
import by.epamtc.shamuradova.ishop.constant.ErrorMessage;
import by.epamtc.shamuradova.ishop.dao.CartDAO;
import by.epamtc.shamuradova.ishop.dao.ModelDAO;
import by.epamtc.shamuradova.ishop.dao.exception.DAOException;
import by.epamtc.shamuradova.ishop.dao.factory.DAOFactory;
import by.epamtc.shamuradova.ishop.service.CartService;
import by.epamtc.shamuradova.ishop.service.exception.ResourceNotFoundServiceException;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;

public class CartServiceImpl implements CartService {

	private CartDAO cartDAO;
	private ModelDAO modelDAO;

	public CartServiceImpl() {
		cartDAO =  DAOFactory.getInstance().getCartDAO();
		modelDAO = DAOFactory.getInstance().getModelDAO();
	}

	@Override
	public Cart getCartByUserId(User user) throws ServiceException {
		try {
			Cart cart = cartDAO.getCartByUserId(user.getId());
		
			return cart;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void createCart(User user) throws ServiceException {
		Date date = new Date(System.currentTimeMillis());
		try {
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
		ShopCart shopCart = new ShopCart();

		List<ShopCartItem> items = null;
		int totalCount;
		BigDecimal totalSum;

		try {
			Cart cart = cartDAO.getCartByUserId(user.getId());

			if (cart == null)
				throw new ResourceNotFoundServiceException(ErrorMessage.NOT_FOUND);

			items = cartDAO.getShopCartItems(cart.getId());
			totalCount = cartDAO.getTotalCountOfModelsInCart(user.getId());
			totalSum = cartDAO.getTotalSumCart(user.getId());

			for (ShopCartItem item : items) {
				shopCart.addShopCartItem(item);
			}
			shopCart.setTotalCount(totalCount);
			shopCart.setTotalSum(totalSum);

		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return shopCart;
	}

	@Override
	public void updateCartReduce(ShopCart shopCart, int idModel, int countToReduce, User user) throws ServiceException {

		try {
			Cart cart = cartDAO.getCartByUserId(user.getId());

			if (cart == null)
				throw new ResourceNotFoundServiceException(ErrorMessage.NOT_FOUND);

			shopCart.removeModel(idModel, countToReduce);
			int count;

			if (shopCart.isEmpty()) {
				cartDAO.deleteCartByidUser(user.getId());// одновременно удалится и последняя запись в cart_item

			} else {
				// если содержит модель, то корректируем количество, иначе удаляем запись этой
				// модели в cart_item
				if (shopCart.containsIdModel(idModel)) {
					ShopCartItem item = shopCart.getShopCartItem(idModel);
					count = item.getCount();
					cartDAO.updateCartItemCountByModelIdCartId(idModel, count, cart.getId());

				} else {
					cartDAO.deleteCartItemByIdModel(idModel);
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
			if (model == null)
				throw new ResourceNotFoundServiceException(ErrorMessage.NOT_FOUND);

			shopCart.addShopCartItem(model, count);

			Cart cart = cartDAO.getCartByUserId(user.getId());
			if (cart == null)
				throw new ResourceNotFoundServiceException(ErrorMessage.NOT_FOUND);

			cartDAO.updateCartItemCountByModelIdCartId(modelId, count, cart.getId());

		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void updateCart(User user, int modelId) throws ServiceException {

		try {
			Cart cart = getCartByUserId(user);
			if (cart == null)
				throw new ResourceNotFoundServiceException(ErrorMessage.NOT_FOUND);

			Model model = modelDAO.getModelById(modelId);
			if (model == null)
				throw new ResourceNotFoundServiceException(ErrorMessage.NOT_FOUND);

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
