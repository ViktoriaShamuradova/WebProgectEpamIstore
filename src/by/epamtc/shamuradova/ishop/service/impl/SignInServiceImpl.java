package by.epamtc.shamuradova.ishop.service.impl;

import by.epamtc.shamuradova.ishop.bean.AuthData;
import by.epamtc.shamuradova.ishop.bean.User;
import by.epamtc.shamuradova.ishop.constant.ErrorMessage;
import by.epamtc.shamuradova.ishop.dao.SignInDAO;
import by.epamtc.shamuradova.ishop.dao.exception.DAOException;
import by.epamtc.shamuradova.ishop.dao.impl.SignInDAOImpl;
import by.epamtc.shamuradova.ishop.service.SignInService;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;
import by.epamtc.shamuradova.ishop.service.exception.ValidationException;

//все это дело как-то хешировать

public class SignInServiceImpl implements SignInService {

	@Override
	public User signIn(AuthData data) throws ServiceException {

		try {
			validate(data);
			// фабрика
			SignInDAO signIn = new SignInDAOImpl();
			User user = signIn.signIn(data);
			return user;

		} catch (ValidationException e) {
			throw new ServiceException(e);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

	}

	// уборать маг значения
	private void validate(AuthData data) throws ValidationException {
		StringBuilder error = new StringBuilder();

		String login = data.getLogin();
		String password = new String(data.getPassword());

		if (login == null || login.trim().isEmpty()) {
			error.append("login" + " " + ErrorMessage.CANT_BE_EMPTY + " ");
		}
		if (password == null || password.trim().isEmpty()) {
			error.append("password" + " " + ErrorMessage.CANT_BE_EMPTY + " ");
		}
		password = null;
		if (error.length() != 0) {
			throw new ValidationException(new String(error));
		}
	}

}
