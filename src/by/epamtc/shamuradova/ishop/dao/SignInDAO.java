package by.epamtc.shamuradova.ishop.dao;

import by.epamtc.shamuradova.ishop.bean.AuthData;
import by.epamtc.shamuradova.ishop.bean.User;
import by.epamtc.shamuradova.ishop.dao.exception.DAOException;

public interface SignInDAO {
	User signIn(AuthData data) throws DAOException;

}
