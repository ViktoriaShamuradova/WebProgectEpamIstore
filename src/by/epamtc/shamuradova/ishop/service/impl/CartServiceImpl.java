package by.epamtc.shamuradova.ishop.service.impl;

import java.sql.Date;

import by.epamtc.shamuradova.ishop.bean.Cart;
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
	
}
