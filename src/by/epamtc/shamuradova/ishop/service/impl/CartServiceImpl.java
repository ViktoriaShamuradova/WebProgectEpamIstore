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
import by.epamtc.shamuradova.ishop.bean.entity.Model;
import by.epamtc.shamuradova.ishop.dao.CartDAO;
import by.epamtc.shamuradova.ishop.dao.ModelDAO;
import by.epamtc.shamuradova.ishop.dao.exception.DAOException;
import by.epamtc.shamuradova.ishop.dao.impl.CartDAOImpl;
import by.epamtc.shamuradova.ishop.dao.impl.ModelDAOImpl;
import by.epamtc.shamuradova.ishop.service.CartService;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;

public class CartServiceImpl implements CartService {

	public CartServiceImpl() {
	}

	@Override
	public Cart getCartByUserId(int userId) throws ServiceException {
		CartDAO cartDAO = new CartDAOImpl();
		try {
			return cartDAO.getCartByUserId(userId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void createCart(int idUser) throws ServiceException {
		CartDAO cartDAO = new CartDAOImpl();

		Date date = new Date(System.currentTimeMillis());
		try {
			cartDAO.addCart(idUser, date);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public void createCartItem(CartContent content) throws ServiceException {
		CartDAO cartDAO = new CartDAOImpl();
		try {
			cartDAO.addCartItem(content);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

	}

//исправить, правильнее исправлять сумму и колво в shopCart, а не дополнтельно запрашивать в бд, справить добавление в shopCart
	@Override
	public ShopCart formNewShopCart(int userId) throws ServiceException {
		ShopCart shopCart = new ShopCart();
		CartDAO cartDAO = new CartDAOImpl();
		List<ShopCartItem> items = null;
		int totalCount;
		BigDecimal totalSum;

		try {
			Cart cart = cartDAO.getCartByUserId(userId);
			items = cartDAO.getShopCartItems(cart.getId());
			totalCount = cartDAO.getTotalCountOfModelsInCart(userId);
			totalSum = cartDAO.getTotalSumCart(userId);

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

	// work
	@Override
	public void updateCartReduce(ShopCart shopCart, int idModel, int countToReduce, int idUser)
			throws ServiceException {
		shopCart.removeModel(idModel, countToReduce);

		int count;

		CartDAO cartDAO = new CartDAOImpl();

		try {
			if (shopCart.isEmpty()) {
				cartDAO.deleteCartByidUser(idUser);// одновременно удалится и последняя запись в cart_item

			} else {
				// если содержит модель, то корректируем количество, иначе удаляем запись этой
				// модели в cart_item
				if (shopCart.containsIdModel(idModel)) {
					ShopCartItem item = shopCart.getShopCartItem(idModel);
					count = item.getCount();
					cartDAO.updateCartItemCountByIdModel(idModel, count);

				} else {
					cartDAO.deleteCartItemByIdModel(idModel);
				}
			}

		} catch (DAOException e) {
			throw new ServiceException(e);
		}

	}

//если не работает, завтра проверить по частям
	@Override
	public void updateCartIncrease(ShopCart shopCart, int idModel, int count) throws ServiceException {
		ModelDAO modelDAO = new ModelDAOImpl();

		try {
			Model model = modelDAO.getModelById(idModel);
			shopCart.addShopCartItem(model, count);

			CartDAO cartDAO = new CartDAOImpl();

			cartDAO.updateCartItemCountByIdModel(idModel, count);

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
		cartS.updateCartReduce(cart, 57, 1, 19);

		cartS.updateCartReduce(cart2, 58, 1, 20);

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
