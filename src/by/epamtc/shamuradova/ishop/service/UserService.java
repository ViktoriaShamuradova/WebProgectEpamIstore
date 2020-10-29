package by.epamtc.shamuradova.ishop.service;

import java.util.List;

import by.epamtc.shamuradova.ishop.bean.User;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;

public interface UserService {

	public List<User> getBlackList(int page, int limit) throws ServiceException;

	public int countUsersInBlackList() throws ServiceException;

	public void deleteUserFromBlackList(int userId) throws ServiceException;

}
