package by.epamtc.shamuradova.ishop.service.validation;

import by.epamtc.shamuradova.ishop.bean.entity.User;
import by.epamtc.shamuradova.ishop.constant.UserRole;
import by.epamtc.shamuradova.ishop.service.exception.AccessDeniedServiceException;
import by.epamtc.shamuradova.ishop.service.exception.ValidationException;

public class UserValidation {

	private UserValidation() {
	}

	public static void validateRoleId(int roleId) throws ValidationException {
		if (!UserRole.rolesId.containsValue(roleId))
			throw new ValidationException("not valid " + roleId);
	}

	public static void checkRoleAdmin(User user) throws AccessDeniedServiceException {
		if (user.getRole().equals(UserRole.SHOPPER))
			throw new AccessDeniedServiceException();

	}
	public static void checkRoleShopper(User user) throws AccessDeniedServiceException {
		if (user.getRole().equals(UserRole.ADMIN))
			throw new AccessDeniedServiceException();

	}

}
