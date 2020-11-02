package by.epamtc.shamuradova.ishop.service.impl;

import java.util.List;

import by.epamtc.shamuradova.ishop.bean.entity.User;
import by.epamtc.shamuradova.ishop.dao.UserDAO;
import by.epamtc.shamuradova.ishop.dao.exception.DAOException;
import by.epamtc.shamuradova.ishop.dao.impl.UserDAOImpl;
import by.epamtc.shamuradova.ishop.service.UserService;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;
import by.epamtc.shamuradova.ishop.service.validation.UserValidation;

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
	public List<User> allUsers(int page, int limit) throws ServiceException {
		try {
			return userDAO.getUsers(page, limit);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public int countUsers() throws ServiceException {
		try {
			return userDAO.countUsers();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void addUserInBlackList(int userId) throws ServiceException {
		try {
			userDAO.toggleBlackListInUsers(userId, true);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public void deleteUserBlackList(int userId) throws ServiceException {
		try {
			userDAO.toggleBlackListInUsers(userId, false);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public List<User> getUsersByRole(int page, int limit, int roleId) throws ServiceException {
		UserValidation.validateRoleId(roleId);
		try {
			return userDAO.getUsersByRole(page, limit, roleId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public int countByRole(int roleId) throws ServiceException {
		UserValidation.validateRoleId(roleId);
		try {
			return userDAO.countUsersByRole(roleId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

}
