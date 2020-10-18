package by.epamtc.shamuradova.ishop.service.impl;

import java.sql.Date;
import java.util.List;

import by.epamtc.shamuradova.ishop.bean.Cart;
import by.epamtc.shamuradova.ishop.bean.CartContent;
import by.epamtc.shamuradova.ishop.bean.ShopCart;
import by.epamtc.shamuradova.ishop.bean.ShopCartItem;
import by.epamtc.shamuradova.ishop.dao.CartDAO;
import by.epamtc.shamuradova.ishop.dao.exception.DAOException;
import by.epamtc.shamuradova.ishop.dao.impl.CartDAOImpl;
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
		long millis = System.currentTimeMillis();
		Date date = new Date(millis);
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

	@Override
	public ShopCart formShopCart(int userId) throws ServiceException {
		ShopCart shopCart = new ShopCart();
		CartDAO cartDAO = new CartDAOImpl();
		List<ShopCartItem> items = null;
		int totalCount;
		int totalSum;

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
	public static void main(String[]args) throws ServiceException {
		CartServiceImpl cart = new CartServiceImpl();
		System.out.println(cart.formShopCart(15));
	}

}
