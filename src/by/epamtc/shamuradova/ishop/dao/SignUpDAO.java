package by.epamtc.shamuradova.ishop.dao;

import by.epamtc.shamuradova.ishop.bean.RegInfo;
import by.epamtc.shamuradova.ishop.dao.exception.DAOException;

/**
 * Интерфейс SignUpDAO, для проверки данных пользователя  и регистрации
 * пользователя
 * 
 * Interface for checking user data and signing up user
 * 
 * @author Шамурадова Виктория 2020
 */


public interface SignUpDAO {

	public void signUp(RegInfo regInfo) throws DAOException; 

}
