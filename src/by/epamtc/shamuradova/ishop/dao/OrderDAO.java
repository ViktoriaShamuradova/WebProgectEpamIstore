package by.epamtc.shamuradova.ishop.dao;

import java.util.Collection;
import java.util.List;

import by.epamtc.shamuradova.ishop.bean.Order;
import by.epamtc.shamuradova.ishop.bean.OrderItem;
import by.epamtc.shamuradova.ishop.bean.ShopCart;
import by.epamtc.shamuradova.ishop.bean.ShopCartItem;
import by.epamtc.shamuradova.ishop.dao.exception.DAOException;

public interface OrderDAO {

	public int addOrder(int idUser, ShopCart cart) throws DAOException; //вернет id order или сделать другой метод, передав ОБЯЗАТЕЛЬНО туда коннекшен

	public Order getOrderByID(int idUser) throws DAOException;

	public void addOrderItem(int idUser, Collection<ShopCartItem> items) throws DAOException;

	public void deleteOrderById(int id) throws DAOException;

	public void deleteOrderItemByIdOrder(int idOrder) throws DAOException;

	public Order findOrderById(int id) throws DAOException;

	public List<OrderItem> getOrderItemsByIdOrder(int idOrder) throws DAOException;

	public List<Order> getListOrders(int idUser, int limit, int offset) throws DAOException;

	public int countOrdersByIdUser(int idUser) throws DAOException;


}
