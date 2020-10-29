package by.epamtc.shamuradova.ishop.dao;

import java.util.List;

import by.epamtc.shamuradova.ishop.bean.User;
import by.epamtc.shamuradova.ishop.dao.exception.DAOException;

public interface UserDAO {

	public User getUserByLogin(String login) throws DAOException;

	public List<User> getBlackList(int page, int limit) throws DAOException;

	public int countUsersInBlackList() throws DAOException;

	public void deleteUserFromBlackList(int userId) throws DAOException;

}
