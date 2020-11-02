package by.epamtc.shamuradova.ishop.service;

import java.util.List;

import by.epamtc.shamuradova.ishop.bean.ShopCart;
import by.epamtc.shamuradova.ishop.bean.entity.Order;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;


public interface OrderService {

	public int makeOrder(ShopCart shopCart, int idUser) throws ServiceException;

	public Order findOrderById(int id, int idUser) throws ServiceException;
	
	public List<Order> listMyOrders(int idUser, int page, int limit) throws ServiceException;

	public int countOrders(int idUser) throws ServiceException;

}
