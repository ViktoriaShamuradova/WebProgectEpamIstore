package by.epamtc.shamuradova.ishop.service.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
//исправить, правильнее исправлять сумму и колво в shopCart, а не дополнтельно запрашивать в бд
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
	//здессь посмотри транзакции бд
	@Override
	public void updateCartReduce(ShopCart shopCart, int idModel, int count, int idUser) throws ServiceException {
		shopCart.removeModel(idModel, count);
		
		CartDAO cartDAO = new CartDAOImpl();
		
		
		if(shopCart.getShopCartItems().isEmpty()) {
			cartDAO.deleteCartByidUser(idUser);
			cartDAO.deleteCartItemByIdModel(idModel);
		}
		
		
		// DAO 1
		// DAO 2
		// DAO 3
		
		
		
	}

	public static void main(String[] args) throws ServiceException {
//		CartServiceImpl cart = new CartServiceImpl();
//		System.out.println(cart.formNewShopCart(16));
		Map<Integer, String> ex= new HashMap();
		ex.put(1, "v1");
		ex.put(2, "v2");
		ex.put(3, "v3");
		ex.remove("v3");
		ex.remove(3);
		System.out.println(ex);
		
	}
		
}

	


