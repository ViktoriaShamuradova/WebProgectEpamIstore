package by.epamtc.shamuradova.ishop.service.validation;

import by.epamtc.shamuradova.ishop.bean.ShopCart;
import by.epamtc.shamuradova.ishop.service.exception.ValidationException;

public class OrderValidation {

	private OrderValidation() {
	}

	public static void validate(ShopCart shopCart) throws ValidationException {
		StringBuilder errorMessage = new StringBuilder();

		if (shopCart == null || shopCart.getShopCartItems().isEmpty()) {
			errorMessage.append("Name cant be empty ");
		}

		if (errorMessage.length() != 0) {
			throw new ValidationException(errorMessage.toString());
		}
	}

}
