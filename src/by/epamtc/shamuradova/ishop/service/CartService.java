package by.epamtc.shamuradova.ishop.service;

import by.epamtc.shamuradova.ishop.bean.Cart;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;

public interface CartService {

	public Cart getCartByUserId(int userId) throws ServiceException;
	public void createCart(int idUser) throws ServiceException;
}
