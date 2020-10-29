package by.epamtc.shamuradova.ishop.service.impl;

import java.util.List;

import by.epamtc.shamuradova.ishop.bean.User;
import by.epamtc.shamuradova.ishop.dao.UserDAO;
import by.epamtc.shamuradova.ishop.dao.exception.DAOException;
import by.epamtc.shamuradova.ishop.dao.impl.UserDAOImpl;
import by.epamtc.shamuradova.ishop.service.UserService;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;

public class UserServiceImpl implements UserService {

	private UserDAO userDAO;

	public UserServiceImpl() {
		userDAO = new UserDAOImpl();
	}

	@Override
	public List<User> getBlackList(int page, int limit) throws ServiceException {
		try {
			return userDAO.getBlackList(page, limit);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public int countUsersInBlackList() throws ServiceException {
		try {
			return userDAO.countUsersInBlackList();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void deleteUserFromBlackList(int userId) throws ServiceException {

		try {
			userDAO.deleteUserFromBlackList(userId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}	
	}

}
