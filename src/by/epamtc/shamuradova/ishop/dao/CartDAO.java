package by.epamtc.shamuradova.ishop.dao;

import java.sql.Date;
import java.util.List;

import by.epamtc.shamuradova.ishop.bean.Cart;
import by.epamtc.shamuradova.ishop.bean.CartContent;
import by.epamtc.shamuradova.ishop.bean.ShopCartItem;
import by.epamtc.shamuradova.ishop.dao.exception.DAOException;

public interface CartDAO {

	public Cart getCartByUserId(int userId) throws DAOException;

	// public void addCartItem(int idCart, int idModel, int count) throws
	// DAOException;

	public void addCart(int idUser, Date date) throws DAOException;

	public void addCartItem(CartContent cartContents) throws DAOException;

	public int getTotalSumCart(int idUser) throws DAOException;

	public List<ShopCartItem> getShopCartItems(int cartId) throws DAOException;

	public int getTotalCountOfModelsInCart(int idUser) throws DAOException;

}
