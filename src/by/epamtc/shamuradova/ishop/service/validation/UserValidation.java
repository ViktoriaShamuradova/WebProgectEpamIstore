package by.epamtc.shamuradova.ishop.service.validation;

import by.epamtc.shamuradova.ishop.constant.ErrorMessage;
import by.epamtc.shamuradova.ishop.constant.UserRole;
import by.epamtc.shamuradova.ishop.service.exception.ValidationException;

public class UserValidation {

	private UserValidation() {
	}

	public static  void validateRoleId(int roleId) throws ValidationException {

		if (!UserRole.rolesId.containsValue(roleId))
			throw new ValidationException(ErrorMessage.NOT_VALID_DATA + " " + roleId);
	}

}
