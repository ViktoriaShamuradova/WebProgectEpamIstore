package by.epamtc.shamuradova.ishop.service;

import by.epamtc.shamuradova.ishop.bean.CartContent;
import by.epamtc.shamuradova.ishop.bean.ShopCart;
import by.epamtc.shamuradova.ishop.bean.entity.Cart;
import by.epamtc.shamuradova.ishop.bean.entity.User;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;

public interface CartService {

	public Cart getCartByUserId(User user) throws ServiceException;

	public void createCart(User user) throws ServiceException;

	public void createCartItem(CartContent content) throws ServiceException;

	public ShopCart formNewShopCart(User user) throws ServiceException;

	public void updateCartReduce(ShopCart shopCart, int idModel, int count, User user) throws ServiceException;

	public void updateCartIncrease(ShopCart shopCart, int idModel, int count, User user) throws ServiceException;

	public void updateCart(User user, int modelId) throws ServiceException;
}
