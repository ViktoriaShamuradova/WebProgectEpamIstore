package by.epamtc.shamuradova.ishop.service.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import by.epamtc.shamuradova.ishop.dao.impl.SQLCartDAOImpl;
import by.epamtc.shamuradova.ishop.dao.impl.SQLModelDAOImpl;
import by.epamtc.shamuradova.ishop.service.CartService;
import by.epamtc.shamuradova.ishop.service.exception.ResourceNotFoundServiceException;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;

public class CartServiceImpl implements CartService {

	private CartDAO cartDAO;
	private ModelDAO modelDAO;

	public CartServiceImpl() {
		cartDAO = new SQLCartDAOImpl();
		modelDAO = new SQLModelDAOImpl();
	}

	//запись о корзине может отсуствовать
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

	public static void main(String[] args) throws ServiceException {
		CartServiceImpl cartS = new CartServiceImpl();
//		System.out.println(cart.formNewShopCart(16));
		ShopCart cart = new ShopCart();
		ShopCart cart2 = new ShopCart();

		// create model, item
		Model m57 = new Model();
		m57.setId(57);
		m57.setPrice(new BigDecimal(5000));
		ShopCartItem item1 = new ShopCartItem(m57, 4);

		Model m58 = new Model();
		m58.setId(58);
		m58.setPrice(new BigDecimal(5000));
		ShopCartItem item2 = new ShopCartItem(m58, 1);

//create shopcart
		Map<Integer, ShopCartItem> ex = new HashMap();
		ex.put(57, item1);
		cart.setShopCartItems(ex);

		Map<Integer, ShopCartItem> ex2 = new HashMap();
		ex2.put(58, item2);
		cart2.setShopCartItems(ex2);

		// work, если товаров больше 1
//		cartS.updateCartReduce(cart, 57, 1, 19);
//
//		cartS.updateCartReduce(cart2, 58, 1, 20);

//		System.out.println(cart.getShopCartItems().contains(53));
//		System.out.println(cart.getShopCartItems().contains(52));
//		System.out.println(cart.getShopCartItems().contains(50));
//		System.out.println(ex.containsKey(50));
//		System.out.println(cart.getShopCartItems());
//		System.out.println(count);
//
//		ShopCart c = new ShopCart();
//		System.out.println(c.getShopCartItems().isEmpty());
	}

}
