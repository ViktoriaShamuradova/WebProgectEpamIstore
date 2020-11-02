package by.epamtc.shamuradova.ishop.service;

import by.epamtc.shamuradova.ishop.bean.AuthData;
import by.epamtc.shamuradova.ishop.bean.entity.User;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;

public interface SignInService {

	User signIn(AuthData data) throws ServiceException;

}
