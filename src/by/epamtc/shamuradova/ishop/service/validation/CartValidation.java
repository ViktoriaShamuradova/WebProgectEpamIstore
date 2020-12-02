package by.epamtc.shamuradova.ishop.service.validation;

import by.epamtc.shamuradova.ishop.bean.entity.Cart;
import by.epamtc.shamuradova.ishop.service.exception.ResourceNotFoundServiceException;

public class CartValidation {

	private CartValidation() {
	}

	public static void checkCart(Cart cart) throws ResourceNotFoundServiceException {
		if (cart == null)
			throw new ResourceNotFoundServiceException("not found model");

	}
}
