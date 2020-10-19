package by.epamtc.shamuradova.ishop.dao;

import by.epamtc.shamuradova.ishop.bean.User;
import by.epamtc.shamuradova.ishop.dao.exception.DAOException;

public interface UserDAO {
	
	public User getUserByLogin(String login) throws DAOException;

}
