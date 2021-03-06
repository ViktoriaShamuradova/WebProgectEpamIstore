package by.epamtc.shamuradova.ishop.dao;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import by.epamtc.shamuradova.ishop.bean.CartContent;
import by.epamtc.shamuradova.ishop.bean.ShopCartItem;
import by.epamtc.shamuradova.ishop.bean.entity.Cart;
import by.epamtc.shamuradova.ishop.bean.entity.CartItem;
import by.epamtc.shamuradova.ishop.dao.exception.DAOException;

/** Интерфейс CartDAO, содержащий методы для работы с объектами типов Cart, CartItem
* 
* ModelDAO interface, containing methods for working with objects of the Cart, CartItem
*
* @author Victoria Shamuradova 2020
*/

public interface CartDAO {

	public Cart getCartByUserId(int userId) throws DAOException;

	public void addCart(int idUser, Date date) throws DAOException;

	public void addCartItem(CartContent cartContents) throws DAOException;

	public BigDecimal getTotalSumCart(int idUser) throws DAOException;

	public List<ShopCartItem> getShopCartItems(int cartId) throws DAOException;

	public int getTotalCountOfModelsInCart(int idUser) throws DAOException;

	public void deleteCartByidUser(int idUser) throws DAOException;

	public void deleteCartItemByIdModelCartId(int idModel, int cartId) throws DAOException;

	public void deleteCartItemByIdCart(int idCart) throws DAOException;

	public List<CartItem> getCartItemsByCartId(int cartId) throws DAOException;

	public CartItem getCartItemByCartIdModelId(int cartId, int modelId) throws DAOException;

	public void updateCartItemCountByModelIdCartId(int modelId, int count, int cartId) throws DAOException;

}
