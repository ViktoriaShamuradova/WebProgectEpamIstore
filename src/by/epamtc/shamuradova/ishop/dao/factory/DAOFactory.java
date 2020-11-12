package by.epamtc.shamuradova.ishop.dao.factory;

import by.epamtc.shamuradova.ishop.dao.CartDAO;
import by.epamtc.shamuradova.ishop.dao.ModelDAO;
import by.epamtc.shamuradova.ishop.dao.OrderDAO;
import by.epamtc.shamuradova.ishop.dao.SignInDAO;
import by.epamtc.shamuradova.ishop.dao.SignUpDAO;
import by.epamtc.shamuradova.ishop.dao.UserDAO;
import by.epamtc.shamuradova.ishop.dao.impl.SQLCartDAOImpl;
import by.epamtc.shamuradova.ishop.dao.impl.SQLModelDAOImpl;
import by.epamtc.shamuradova.ishop.dao.impl.SQLOrderDAOImpl;
import by.epamtc.shamuradova.ishop.dao.impl.SQLSignInDAOImpl;
import by.epamtc.shamuradova.ishop.dao.impl.SQLSignUpDAOImpl;
import by.epamtc.shamuradova.ishop.dao.impl.SQLUserDAOImpl;

public class DAOFactory {

	private static final DAOFactory INSTANCE = new DAOFactory();

	private CartDAO cartDAO;
	private ModelDAO modelDAO;
	private OrderDAO orderDAO;
	private UserDAO userDAO;
	private SignInDAO signInDAO;
	private SignUpDAO signUpDAO;

	private DAOFactory() {
	}

	public static DAOFactory getInstance() {
		return INSTANCE;
	}

	public CartDAO getCartDAO() {
		if (cartDAO == null) {
			synchronized (INSTANCE) {
				if (cartDAO == null) {
					cartDAO = new SQLCartDAOImpl();
				}
			}
		}
		return cartDAO;
	}

	public ModelDAO getModelDAO() {
		if (modelDAO == null) {
			synchronized (INSTANCE) {
				if (modelDAO == null) {
					modelDAO = new SQLModelDAOImpl();
				}
			}
		}
		return modelDAO;
	}

	public OrderDAO getOrderDAO() {
		if (orderDAO == null) {
			synchronized (INSTANCE) {
				if (orderDAO == null) {
					orderDAO = new SQLOrderDAOImpl();
				}
			}
		}
		return orderDAO;
	}

	public UserDAO getUserDAO() {
		if (userDAO == null) {
			synchronized (INSTANCE) {
				if (userDAO == null) {
					userDAO = new SQLUserDAOImpl();
				}
			}
		}
		return userDAO;
	}

	public SignInDAO getSignInDAO() {
		if (signInDAO == null) {
			synchronized (INSTANCE) {
				if (signInDAO == null) {
					signInDAO = new SQLSignInDAOImpl();
				}
			}
		}
		return signInDAO;
	}

	public SignUpDAO getSignUpDAO() {
		if (signUpDAO == null) {
			synchronized (INSTANCE) {
				if (signUpDAO == null) {
					signUpDAO = new SQLSignUpDAOImpl();
				}
			}
		}
		return signUpDAO;
	}

}
