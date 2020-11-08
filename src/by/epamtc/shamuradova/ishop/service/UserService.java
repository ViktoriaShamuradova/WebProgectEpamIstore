package by.epamtc.shamuradova.ishop.service;

import java.util.List;

import by.epamtc.shamuradova.ishop.bean.entity.User;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;

public interface UserService {

	public List<User> getBlackList(User user, int page, int limit) throws ServiceException;

	public int countUsersInBlackList() throws ServiceException;

	public List<User> allUsers(User user, int page, int limit) throws ServiceException;

	public int countUsers() throws ServiceException;

	public void addUserInBlackList(User user, int userId) throws ServiceException;

	public void deleteUserBlackList(User user, int userId) throws ServiceException;

	public List<User> getUsersByRole(User user, int page, int limit, int roleId) throws ServiceException;

	public int countByRole(int roleId) throws ServiceException;

	public User getUser(User user, int userId) throws ServiceException;

}
