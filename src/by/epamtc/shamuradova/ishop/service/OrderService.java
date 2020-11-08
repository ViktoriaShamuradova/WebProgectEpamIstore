package by.epamtc.shamuradova.ishop.service;

import java.util.List;

import by.epamtc.shamuradova.ishop.bean.ShopCart;
import by.epamtc.shamuradova.ishop.bean.entity.Order;
import by.epamtc.shamuradova.ishop.bean.entity.User;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;

public interface OrderService {

	public int makeOrder(ShopCart shopCart, User user) throws ServiceException;

	public Order findOrderById(User user, int id) throws ServiceException;

	public List<Order> listMyOrders(User user, int page, int limit) throws ServiceException;

	public int countOrders(int idUser) throws ServiceException;

	public int countOrders() throws ServiceException;

	public List<Order> getAllOrders(User user, int page, int limit) throws ServiceException;

	public void changeStatusOrder(User user, int orderId) throws ServiceException;

}
