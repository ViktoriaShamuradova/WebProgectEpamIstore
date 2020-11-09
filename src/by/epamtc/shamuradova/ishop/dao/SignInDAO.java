package by.epamtc.shamuradova.ishop.dao;

import by.epamtc.shamuradova.ishop.bean.AuthData;
import by.epamtc.shamuradova.ishop.bean.entity.User;
import by.epamtc.shamuradova.ishop.dao.exception.DAOException;


/** Интерфейс SignInDAO, содержащий методы для проверки данных пользователя и авторизации пользователя
* 
*  Interface for checking user data and signing in user
*
* @author Шамурадова Виктория 2020
*/

public interface SignInDAO {
	User signIn(AuthData data) throws DAOException;

}
