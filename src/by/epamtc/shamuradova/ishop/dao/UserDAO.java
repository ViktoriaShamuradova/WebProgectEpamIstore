package by.epamtc.shamuradova.ishop.dao;

import java.util.List;

import by.epamtc.shamuradova.ishop.bean.entity.User;
import by.epamtc.shamuradova.ishop.dao.exception.DAOException;

public interface UserDAO {

	public User getUserByLogin(String login) throws DAOException;

	public List<User> getBlackList(int page, int limit) throws DAOException;

	public int countUsersInBlackList() throws DAOException;

	public List<User> getUsers(int page, int limit) throws DAOException;

	public int countUsers() throws DAOException;

	public void toggleBlackListInUsers(int userId, boolean b) throws DAOException;

	public List<User> getUsersByRole(int page, int limit, int roleId) throws DAOException;

	public int countUsersByRole(int roleId) throws DAOException;

}
