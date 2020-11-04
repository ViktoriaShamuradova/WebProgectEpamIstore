package by.epamtc.shamuradova.ishop.service;

import by.epamtc.shamuradova.ishop.bean.CartContent;
import by.epamtc.shamuradova.ishop.bean.ShopCart;
import by.epamtc.shamuradova.ishop.bean.entity.Cart;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;

public interface CartService {

	public Cart getCartByUserId(int userId) throws ServiceException;

	public void createCart(int idUser) throws ServiceException;

	public void createCartItem(CartContent content) throws ServiceException;

	public ShopCart formNewShopCart(int idUser) throws ServiceException;

	public void updateCartReduce(ShopCart shopCart, int idModel, int count, int idUser) throws ServiceException;

	public void updateCartIncrease(ShopCart shopCart, int idModel, int count, int userId) throws ServiceException;

	public void updateCart(int userId, int modelId) throws ServiceException;
}
