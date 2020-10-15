package by.epamtc.shamuradova.ishop.dao;

import java.sql.Date;

import by.epamtc.shamuradova.ishop.bean.Cart;
import by.epamtc.shamuradova.ishop.bean.CartContent;
import by.epamtc.shamuradova.ishop.dao.exception.DAOException;

public interface CartDAO {

	public Cart getCartByUserId(int userId) throws DAOException;

	// public void addCartItem(int idCart, int idModel, int count) throws
	// DAOException;

	public void addCart(int idUser, Date date) throws DAOException;
	public void addCartItem(CartContent cartContents)throws DAOException;

}
