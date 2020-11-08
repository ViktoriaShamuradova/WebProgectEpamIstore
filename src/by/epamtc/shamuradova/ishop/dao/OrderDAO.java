package by.epamtc.shamuradova.ishop.dao;

import java.util.Collection;
import java.util.List;

import by.epamtc.shamuradova.ishop.bean.ShopCart;
import by.epamtc.shamuradova.ishop.bean.ShopCartItem;
import by.epamtc.shamuradova.ishop.bean.StatusOrder;
import by.epamtc.shamuradova.ishop.bean.entity.Order;
import by.epamtc.shamuradova.ishop.bean.entity.OrderItem;
import by.epamtc.shamuradova.ishop.dao.exception.DAOException;

public interface OrderDAO {

	public int addOrder(int userId, StatusOrder status) throws DAOException; // вернет id order или сделать другой метод,
																		// передав ОБЯЗАТЕЛЬНО туда коннекшен

	

	public void addOrderItem(int userId, Collection<ShopCartItem> items) throws DAOException;

	public void deleteOrderById(int id) throws DAOException;

	public void deleteOrderItemByIdOrder(int idOrder) throws DAOException;

	public Order findOrderById(int id) throws DAOException;

	public List<OrderItem> getOrderItemsByIdOrder(int idOrder) throws DAOException;

	public List<Order> getListOrdersByUserId(int userId, int limit, int offset) throws DAOException;

	public int countOrdersByIdUser(int userId) throws DAOException;

	public List<Order> getListOrders(int limit, int offset) throws DAOException;

	public int countOrders() throws DAOException;

	public Order getOrderById(int orderId) throws DAOException;

	public void updateOrderStatus(int orderId, StatusOrder status) throws DAOException;

}
