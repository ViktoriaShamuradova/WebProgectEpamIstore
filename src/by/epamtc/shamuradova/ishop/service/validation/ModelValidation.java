package by.epamtc.shamuradova.ishop.service.validation;

import java.math.BigDecimal;

import by.epamtc.shamuradova.ishop.bean.ModelEdition;
import by.epamtc.shamuradova.ishop.bean.entity.Model;
import by.epamtc.shamuradova.ishop.constant.ErrorMessage;
import by.epamtc.shamuradova.ishop.service.exception.ResourceNotFoundServiceException;
import by.epamtc.shamuradova.ishop.service.exception.ValidationException;

public class ModelValidation {

	private static final int MIN_PRICE = 10;

	private ModelValidation() {
	}

	public static void checkModel(Model model) throws ResourceNotFoundServiceException {
		if (model == null)
			throw new ResourceNotFoundServiceException(ErrorMessage.NOT_FOUND);

	}

	public static void validate(ModelEdition modelEdition) throws ValidationException {
		StringBuilder errorMessage = new StringBuilder();

		if (modelEdition.getName() == null || "".equals(modelEdition.getName().trim())) {
			errorMessage.append("Name " + ErrorMessage.CANT_BE_EMPTY + " ");
		}
		if (modelEdition.getPrice().compareTo(new BigDecimal(MIN_PRICE)) == -1) {
			errorMessage.append("price " + ErrorMessage.CANT_BE_BELOW_MIN_PRICE + " than " + MIN_PRICE);
		}
		if (modelEdition.getCount() < 0) {
			errorMessage.append("Count " + ErrorMessage.LESS_THAN_ZERO + " ");
		}
		if (modelEdition.getDescription() == null || "".equals(modelEdition.getDescription().trim())) {
			errorMessage.append("Description " + ErrorMessage.CANT_BE_EMPTY + " ");
		}
		if (modelEdition.getCategory() == null || "".equals(modelEdition.getCategory().trim())) {
			errorMessage.append("Category " + ErrorMessage.CANT_BE_EMPTY + " ");
		}
		if (modelEdition.getProducer() == null || "".equals(modelEdition.getProducer().trim())) {
			errorMessage.append("Producer" + ErrorMessage.CANT_BE_EMPTY + " ");
		}
		if (errorMessage.length() != 0) {
			throw new ValidationException(errorMessage.toString());
		}
	}

}
