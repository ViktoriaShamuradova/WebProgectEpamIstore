package by.epamtc.shamuradova.ishop.service;

import by.epamtc.shamuradova.ishop.bean.Cart;
import by.epamtc.shamuradova.ishop.bean.CartContent;
import by.epamtc.shamuradova.ishop.bean.ShopCart;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;

public interface CartService {

	public Cart getCartByUserId(int userId) throws ServiceException;

	public void createCart(int idUser) throws ServiceException;

	public void createCartItem(CartContent content) throws ServiceException;

	public ShopCart formShopCart(int idUser) throws ServiceException;
}
