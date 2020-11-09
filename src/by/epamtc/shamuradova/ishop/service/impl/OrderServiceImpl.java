package by.epamtc.shamuradova.ishop.service.impl;

import java.util.Collection;
import java.util.List;

import by.epamtc.shamuradova.ishop.bean.ShopCart;
import by.epamtc.shamuradova.ishop.bean.ShopCartItem;
import by.epamtc.shamuradova.ishop.bean.StatusOrder;
import by.epamtc.shamuradova.ishop.bean.StatusOrderLine;
import by.epamtc.shamuradova.ishop.bean.entity.Cart;
import by.epamtc.shamuradova.ishop.bean.entity.Order;
import by.epamtc.shamuradova.ishop.bean.entity.OrderItem;
import by.epamtc.shamuradova.ishop.bean.entity.User;
import by.epamtc.shamuradova.ishop.constant.UserRole;
import by.epamtc.shamuradova.ishop.dao.CartDAO;
import by.epamtc.shamuradova.ishop.dao.OrderDAO;
import by.epamtc.shamuradova.ishop.dao.exception.DAOException;
import by.epamtc.shamuradova.ishop.dao.impl.SQLCartDAOImpl;
import by.epamtc.shamuradova.ishop.dao.impl.SQLOrderDAOImpl;
import by.epamtc.shamuradova.ishop.service.OrderService;
import by.epamtc.shamuradova.ishop.service.exception.AccessDeniedServiceException;
import by.epamtc.shamuradova.ishop.service.exception.InternalServiceException;
import by.epamtc.shamuradova.ishop.service.exception.ResourceNotFoundServiceException;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;
import by.epamtc.shamuradova.ishop.service.validation.OrderValidation;
import by.epamtc.shamuradova.ishop.service.validation.UserValidation;

public class OrderServiceImpl implements OrderService {

	private OrderDAO orderDAO;
	private CartDAO cartDAO;
	private StatusOrderLine statusOrderLine;

	public OrderServiceImpl() {
		orderDAO = new SQLOrderDAOImpl();
		cartDAO = new SQLCartDAOImpl();
		statusOrderLine = new StatusOrderLine();
	}

	@Override
	public int makeOrder(ShopCart shopCart, User user) throws ServiceException {
		
		OrderValidation.validate(shopCart);
		
		try {
			Cart cart = cartDAO.getCartByUserId(user.getId());			
			int idOrder = createOrder(shopCart, user.getId());

			if (cartDAO.deleteCartByidUser(user.getId())) {
				try {

					cartDAO.deleteCartItemByIdCart(cart.getId());
				} catch (DAOException e) {
					cartDAO.addCart(user.getId(), cart.getCreated());
					orderDAO.deleteOrderById(idOrder);
					orderDAO.deleteOrderItemByIdOrder(idOrder);
					throw new InternalServiceException("not make order", e);
				}
			} else {
				orderDAO.deleteOrderById(idOrder);
				orderDAO.deleteOrderItemByIdOrder(idOrder);
				throw new InternalServiceException("not make order");
			}
			return idOrder;
		} catch (DAOException e) {
			throw new InternalServiceException("not make order", e);
		}
	}

	private int createOrder(ShopCart shopCart, int userId) throws ServiceException, DAOException {

		try {
			int idOrder = orderDAO.addOrder(userId, StatusOrder.NEW);

			Collection<ShopCartItem> items = shopCart.getShopCartItems();
			orderDAO.addOrderItem(idOrder, items);

			return idOrder;
		} catch (DAOException e) {

			throw new InternalServiceException("not make order ", e);
		}
	}

	@Override
	public Order findOrderById(User user, int orderId) throws ServiceException {
		try {
			Order order = orderDAO.findOrderById(orderId);

			if (order == null) {
				throw new ResourceNotFoundServiceException("Order not found by id: " + orderId);
			}
			
			if(user.getRole().equals(UserRole.SHOPPER)) {
				if (order.getIdUser() != user.getId()) {
					throw new AccessDeniedServiceException(
							"Account with id=" + user.getId() + " is not owner for order with id=" + orderId);
				}
			}
			
			List<OrderItem> orderItems = orderDAO.getOrderItemsByIdOrder(orderId);
			order.setItems(orderItems);

			return order;

		} catch (DAOException e) {
			throw new InternalServiceException(e);
		}
	}

	
	@Override
	public List<Order> listMyOrders(User user, int page, int limit) throws ServiceException {
		try {

			UserValidation.checkRoleShopper(user);
			int offset = (page - 1) * limit;

			List<Order> orders = orderDAO.getListOrdersByUserId(user.getId(), limit, offset);

			return orders;
		} catch (DAOException e) {
			throw new InternalServiceException(e);
		}
	}

	@Override
	public int countOrders(int idUser) throws ServiceException {
		try {
			return orderDAO.countOrdersByIdUser(idUser);
		} catch (DAOException e) {
			throw new InternalServiceException(e);
		}
	}

	@Override
	public int countOrders() throws ServiceException {
		try {
			return orderDAO.countOrders();
		} catch (DAOException e) {
			throw new InternalServiceException(e);
		}
	}

	@Override
	public List<Order> getAllOrders(User user, int page, int limit) throws ServiceException {
		try {
			UserValidation.checkRoleAdmin(user);
			int offset = (page - 1) * limit;
			return orderDAO.getListOrders(limit, offset);

		} catch (DAOException e) {
			throw new InternalServiceException(e);
		}
	}

	
	@Override
	public void changeStatusOrder(User user, int orderId) throws ServiceException {
		Order order = null;
		StatusOrder currentStatus = null;
		
		try {
			order = orderDAO.getOrderById(orderId);
		} catch (DAOException e) {
			throw new InternalServiceException(e);
		}
		
		if (order == null) throw new ResourceNotFoundServiceException("Order not found by id " + orderId);
			
		if(user.getRole().equals(UserRole.SHOPPER)) {
			if (order.getIdUser() != user.getId())
				throw new AccessDeniedServiceException("Account with id=" + user.getId() + " is not owner for orders");	
		}	
		
		currentStatus = order.getStatus();
		order.setStatus(statusOrderLine.nextStatus(currentStatus));

		try {
			orderDAO.updateOrderStatus(order.getId(), order.getStatus());
		} catch (DAOException e) {
			order.setStatus(currentStatus);
			throw new InternalServiceException(e);
		}
	}

	public static void main(String[] args) throws ServiceException {
		OrderServiceImpl orderService = new OrderServiceImpl();
//		int idUser = 17;
//
//		ShopCart shopCart = new ShopCart();
//
//		// create model, item
//		Model m45 = new Model();
//		m45.setId(45);
//		m45.setPrice(new BigDecimal(5000));
//		ShopCartItem item1 = new ShopCartItem(m45, 2);
//
////create shopcart
//		Map<Integer, ShopCartItem> ex = new HashMap();
//		ex.put(45, item1);
//
//		shopCart.setShopCartItems(ex);
//
//		// System.out.println(orderService.makeOrder(shopCart, idUser));
		// System.out.println(orderService.listMyOrders(17, 3, 5));
		
	}

}
