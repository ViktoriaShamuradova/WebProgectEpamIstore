package by.epamtc.shamuradova.ishop.service.impl;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import by.epamtc.shamuradova.ishop.bean.ShopCart;
import by.epamtc.shamuradova.ishop.bean.ShopCartItem;
import by.epamtc.shamuradova.ishop.bean.entity.Cart;
import by.epamtc.shamuradova.ishop.bean.entity.Model;
import by.epamtc.shamuradova.ishop.bean.entity.Order;
import by.epamtc.shamuradova.ishop.bean.entity.OrderItem;
import by.epamtc.shamuradova.ishop.dao.CartDAO;
import by.epamtc.shamuradova.ishop.dao.OrderDAO;
import by.epamtc.shamuradova.ishop.dao.exception.DAOException;
import by.epamtc.shamuradova.ishop.dao.impl.CartDAOImpl;
import by.epamtc.shamuradova.ishop.dao.impl.OrderDAOImpl;
import by.epamtc.shamuradova.ishop.service.OrderService;
import by.epamtc.shamuradova.ishop.service.exception.AccessDeniedServiceException;
import by.epamtc.shamuradova.ishop.service.exception.ResourceNotFoundServiceException;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;

public class OrderServiceImpl implements OrderService {

	private OrderDAO orderDAO;
	private CartDAO cartDAO;

	public OrderServiceImpl() {
		orderDAO = new OrderDAOImpl();
		cartDAO = new CartDAOImpl();
	}

//воего рода транзакция, если не получается удалить cart, то удаляем order, а корзина должна остаться на месте
	@Override
	public int makeOrder(ShopCart shopCart, int idUser) throws ServiceException {
		if (shopCart == null || shopCart.getShopCartItems().isEmpty()) {
			throw new ServiceException("shop cart is null or empty");
		}

		try {
			int idOrder = createOrder(shopCart, idUser);
			Cart cart = cartDAO.getCartByUserId(idUser);

			// та же история
			if (cartDAO.deleteCartByidUser(idUser)) {
				try {

					cartDAO.deleteCartItemByIdCart(cart.getId());
				} catch (DAOException e) {
					cartDAO.addCart(idUser, cart.getCreated());
					orderDAO.deleteOrderById(idOrder);
					orderDAO.deleteOrderItemByIdOrder(idOrder);
					throw new ServiceException("not make order", e);
				}

			} else {
				orderDAO.deleteOrderById(idOrder);
				orderDAO.deleteOrderItemByIdOrder(idOrder);
				throw new ServiceException("not make order");
			}
			return idOrder;
		} catch (DAOException e) {
			throw new ServiceException("not make order", e);
		}
	}

	private int createOrder(ShopCart shopCart, int idUser) throws ServiceException, DAOException {

		try {
			int idOrder = orderDAO.addOrder(idUser, shopCart);

			Collection<ShopCartItem> items = shopCart.getShopCartItems();
			orderDAO.addOrderItem(idOrder, items);

			return idOrder;
		} catch (DAOException e) {

			throw new ServiceException("not make order ", e);
		}
	}

//просим дао дать ордер по id, если такого нет: ошибка 404
//имеет ли доступ к данному объекту order user: ошибка 403
	@Override
	public Order findOrderById(int id, int idUser) throws ServiceException {
		try {
			Order order = orderDAO.findOrderById(id);

			if (order == null) {
				throw new ResourceNotFoundServiceException("Order not found by id: " + id);
			}

			if (order.getIdUser() != idUser) {
				throw new AccessDeniedServiceException(
						"Account with id=" + idUser + " is not owner for order with id=" + id);
			}
			List<OrderItem> orderItems = orderDAO.getOrderItemsByIdOrder(id);
			order.setItems(orderItems);

			return order;

		} catch (DAOException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public List<Order> listMyOrders(int idUser, int page, int limit) throws ServiceException {
		try {

			int offset = (page - 1) * limit;
			return orderDAO.getListOrders(idUser, limit, offset);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public int countOrders(int idUser) throws ServiceException {
		try {

			return orderDAO.countOrdersByIdUser(idUser);

		} catch (DAOException e) {
			throw new ServiceException(e);
		}

	}

	public static void main(String[] args) throws ServiceException {
		OrderServiceImpl orderService = new OrderServiceImpl();
		int idUser = 17;

		ShopCart shopCart = new ShopCart();

		// create model, item
		Model m45 = new Model();
		m45.setId(45);
		m45.setPrice(new BigDecimal(5000));
		ShopCartItem item1 = new ShopCartItem(m45, 2);

//create shopcart
		Map<Integer, ShopCartItem> ex = new HashMap();
		ex.put(45, item1);

		shopCart.setShopCartItems(ex);

		// System.out.println(orderService.makeOrder(shopCart, idUser));
		System.out.println(orderService.listMyOrders(17, 3, 5));
	}

}
