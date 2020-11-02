package by.epamtc.shamuradova.ishop.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import by.epamtc.shamuradova.ishop.bean.AuthData;
import by.epamtc.shamuradova.ishop.bean.entity.User;
import by.epamtc.shamuradova.ishop.constant.ErrorMessage;
import by.epamtc.shamuradova.ishop.dao.SignInDAO;
import by.epamtc.shamuradova.ishop.dao.exception.DAOException;
import by.epamtc.shamuradova.ishop.dao.impl.SignInDAOImpl2;
import by.epamtc.shamuradova.ishop.service.SignInService;
import by.epamtc.shamuradova.ishop.service.exception.BlackListException;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;
import by.epamtc.shamuradova.ishop.service.exception.ValidationException;
import by.epamtc.shamuradova.ishop.service.exception.WrongAuthDataException;
import by.epamtc.shamuradova.ishop.service.util.MD5Encryptor;

public class SignInServiceImpl implements SignInService {

	private SignInDAO signIn;

	public SignInServiceImpl() {
		signIn = new SignInDAOImpl2();
	}

	@Override
	public User signIn(AuthData data) throws ServiceException {
		try {

			validate(data);

			String hashPasswword;

			hashPasswword = MD5Encryptor.getHashCode(new String(data.getPassword()));

			data.setPassword(hashPasswword.toCharArray());
			hashPasswword = null;

			User user = signIn.signIn(data);
			
			if (user == null) {
				throw new WrongAuthDataException("incorrect login or password");
			}
			
			if (user.isBlackList()) {
				throw new BlackListException("your account has been deleted");
			}
		
			return user;

		} catch (NoSuchAlgorithmException | UnsupportedEncodingException | DAOException e) {
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
