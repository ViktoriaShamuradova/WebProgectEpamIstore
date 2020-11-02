package by.epamtc.shamuradova.ishop.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.epamtc.shamuradova.ishop.bean.RegInfo;
import by.epamtc.shamuradova.ishop.bean.entity.User;
import by.epamtc.shamuradova.ishop.constant.ErrorMessage;
import by.epamtc.shamuradova.ishop.constant.PatternContainer;
import by.epamtc.shamuradova.ishop.constant.UserRole;
import by.epamtc.shamuradova.ishop.constant.UserStatus;
import by.epamtc.shamuradova.ishop.dao.SignUpDAO;
import by.epamtc.shamuradova.ishop.dao.UserDAO;
import by.epamtc.shamuradova.ishop.dao.exception.DAOException;
import by.epamtc.shamuradova.ishop.dao.impl.SignUpImplDAO;
import by.epamtc.shamuradova.ishop.dao.impl.UserDAOImpl;
import by.epamtc.shamuradova.ishop.service.SignUpService;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;
import by.epamtc.shamuradova.ishop.service.exception.ValidationException;
import by.epamtc.shamuradova.ishop.service.util.MD5Encryptor;

public class SignUpServiceImpl implements SignUpService {

	private SignUpDAO signUpDAO ;
	
	public SignUpServiceImpl() {
		signUpDAO = new SignUpImplDAO();
	}
	
	@Override
	public User signUp(RegInfo regInfo) throws ServiceException {
		try {
			validate(regInfo);

			checkLoginFormat(regInfo.getLogin(), PatternContainer.LOGIN_PATTERN);

			String password = new String(regInfo.getPassword());
			checkPasswordFormat(password, PatternContainer.PASSWORD_PATTERN);
			password = null;

			String hashPassword = MD5Encryptor.getHashCode(new String(regInfo.getPassword()));
			regInfo.setPassword(hashPassword.toCharArray());
			signUpDAO.signUp(regInfo);
			
			UserDAO userDAO = new UserDAOImpl();
			User user = userDAO.getUserByLogin(regInfo.getLogin());
			
			return user;
			
			

		} catch (DAOException | ValidationException |UnsupportedEncodingException |NoSuchAlgorithmException e) {
			throw new ServiceException(e);
		}

	}

	private void checkLoginFormat(String login, Pattern loginPattern) throws ServiceException {
		Matcher matcher = PatternContainer.LOGIN_PATTERN.matcher(login);
		if (!matcher.find()) {
			throw new ServiceException(ErrorMessage.LOGIN_FORMAT);
		}
	}

	private void checkPasswordFormat(String password, Pattern passwordPattern) throws ServiceException {
		Matcher matcher = PatternContainer.PASSWORD_PATTERN.matcher(password);
		password = null;
		if (!matcher.find()) {
			throw new ServiceException(ErrorMessage.PASSWORD_FORMAT);
		}
	}

	private void validate(RegInfo info) throws ValidationException {
		StringBuilder errorMessage = new StringBuilder();

		if (info.getName() == null || "".equals(info.getName().trim())) {
			errorMessage.append("Name " + ErrorMessage.CANT_BE_EMPTY + " ");
		}
		if (info.getSurname() == null || "".equals(info.getSurname().trim())) {
			errorMessage.append("Surname " + ErrorMessage.CANT_BE_EMPTY + " ");
		}
		if (info.getLogin() == null || "".equals(info.getLogin().trim())) {
			errorMessage.append("Login " + ErrorMessage.CANT_BE_EMPTY + " ");
		}
		if (info.getEmail() == null || "".equals(info.getEmail().trim())) {
			errorMessage.append("Email " + ErrorMessage.CANT_BE_EMPTY + " ");
		}
		if (info.getPassword() == null || "".equals(info.getPassword())) {
			errorMessage.append("Password " + ErrorMessage.CANT_BE_EMPTY + " ");
		}
		if (errorMessage.length() != 0) {
			throw new ValidationException(errorMessage.toString());
		}
	}

	public static void main(String[] args) throws ServiceException {
		char[] password = new char[] { '1', '2', '3', '4', '5', '6','7', '8','9' };
		String pass = new String(password);
		RegInfo regInfo = new RegInfo("Shamuraaa", "Shamaaa", "ShamurShamaaaaa44", "sham@gmail.com", password, UserStatus.NEW, UserRole.SHOPPER);
		SignUpServiceImpl sign = new SignUpServiceImpl();
		System.out.println(sign.signUp(regInfo));
	}

}
