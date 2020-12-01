package by.epamtc.shamuradova.ishop.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtc.shamuradova.ishop.bean.AuthData;
import by.epamtc.shamuradova.ishop.bean.entity.User;
import by.epamtc.shamuradova.ishop.constant.ErrorMessage;
import by.epamtc.shamuradova.ishop.dao.SignInDAO;
import by.epamtc.shamuradova.ishop.dao.exception.DAOException;
import by.epamtc.shamuradova.ishop.dao.factory.DAOFactory;
import by.epamtc.shamuradova.ishop.dao.impl.SQLCartDAOImpl;
import by.epamtc.shamuradova.ishop.service.SignInService;
import by.epamtc.shamuradova.ishop.service.exception.AccessDeniedServiceException;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;
import by.epamtc.shamuradova.ishop.service.exception.ValidationException;
import by.epamtc.shamuradova.ishop.service.util.MD5Encryptor;

public class SignInServiceImpl implements SignInService {

	private SignInDAO signIn;
	private static final Logger logger = LogManager.getLogger(SQLCartDAOImpl.class);
	
	private static final String LOGIN = "Login";
	private static final String PASSWORD = "Password";
	private static final String  MESSAGE_ABOUT_INCORRECT_DATA= "Incorrect login or password";
	private static final String  MESSAGE_ABOUT_DELETED_ACCOUNT= "Your account has been deleted";

	public SignInServiceImpl() {
		signIn = DAOFactory.getInstance().getSignInDAO();
	}

	@Override
	public User signIn(AuthData data) throws ServiceException {
		try {

			validate(data);

			String hashPasswword = MD5Encryptor.getHashCode(new String(data.getPassword()));

			data.setPassword(hashPasswword.toCharArray());
			hashPasswword = null;

			User user = signIn.signIn(data);
			
			if (user == null) {
				logger.info(MESSAGE_ABOUT_INCORRECT_DATA);
				throw new ValidationException(MESSAGE_ABOUT_INCORRECT_DATA);
			}
			
			if (user.isBlackList()) {
				logger.info(MESSAGE_ABOUT_DELETED_ACCOUNT);
				throw new AccessDeniedServiceException(MESSAGE_ABOUT_DELETED_ACCOUNT);
			}
		
			return user;

		} catch (NoSuchAlgorithmException | UnsupportedEncodingException | DAOException e) {
			throw new ServiceException(e);
		}
	}

	private void validate(AuthData data) throws ValidationException {
		StringBuilder error = new StringBuilder();

		String login = data.getLogin();
		String password = new String(data.getPassword());

		if (login == null || login.trim().isEmpty()) {
			error.append(SignInServiceImpl.LOGIN + " " + ErrorMessage.CANT_BE_EMPTY + " ");
		}
		if (password == null || password.trim().isEmpty()) {
			error.append(SignInServiceImpl.PASSWORD + " " + ErrorMessage.CANT_BE_EMPTY + " ");
		}
		password = null;
		if (error.length() != 0) {
			throw new ValidationException(error.toString());
		}
	}
}
