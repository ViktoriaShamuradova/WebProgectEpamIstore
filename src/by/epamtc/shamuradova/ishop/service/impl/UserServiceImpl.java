package by.epamtc.shamuradova.ishop.service.impl;

import java.util.List;

import by.epamtc.shamuradova.ishop.bean.entity.User;
import by.epamtc.shamuradova.ishop.constant.UserRole;
import by.epamtc.shamuradova.ishop.dao.UserDAO;
import by.epamtc.shamuradova.ishop.dao.exception.DAOException;
import by.epamtc.shamuradova.ishop.dao.factory.DAOFactory;
import by.epamtc.shamuradova.ishop.service.UserService;
import by.epamtc.shamuradova.ishop.service.exception.AccessDeniedServiceException;
import by.epamtc.shamuradova.ishop.service.exception.ResourceNotFoundServiceException;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;
import by.epamtc.shamuradova.ishop.service.validation.UserValidation;

public class UserServiceImpl implements UserService {

	private UserDAO userDAO;

	public UserServiceImpl() {
		userDAO = DAOFactory.getInstance().getUserDAO();
	}

	@Override
	public List<User> getBlackList(User user, int page, int limit) throws ServiceException {
		try {
			UserValidation.checkRoleAdmin(user);
			List<User> users = userDAO.getBlackList(page, limit);

			if (users == null)
				throw new ResourceNotFoundServiceException("users not found");

			return users;
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
	public List<User> allUsers(User user, int page, int limit) throws ServiceException {
		try {
			UserValidation.checkRoleAdmin(user);

			List<User> users = userDAO.getUsers(page, limit);

			if (users == null)
				throw new ResourceNotFoundServiceException("users not found");

			return users;
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
	public void addUserInBlackList(User user, int userId) throws ServiceException {
		try {
			UserValidation.checkRoleAdmin(user);

			userDAO.toggleBlackListInUsers(userId, true);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void deleteUserBlackList(User user, int userId) throws ServiceException {
		try {
			UserValidation.checkRoleAdmin(user);
			userDAO.toggleBlackListInUsers(userId, false);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<User> getUsersByRole(User user, int page, int limit, int roleId) throws ServiceException {
		UserValidation.validateRoleId(roleId);
		try {
			UserValidation.checkRoleAdmin(user);

			List<User> users = userDAO.getUsersByRole(page, limit, roleId);

			if (users == null)
				throw new ResourceNotFoundServiceException("users not found");

			return users;
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

	@Override
	public User getUser(User user, int userId) throws ServiceException {
		try {
			User userRes = userDAO.getUserById(userId);

			if (userRes == null)
				throw new ResourceNotFoundServiceException("user not found");

			if (user.getRole().equals(UserRole.ADMIN) || user.getId() == userId) {
				return userRes;
			} else {
				throw new AccessDeniedServiceException(
						"You do not have the appropriate right to receive personal data about another user");
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}
