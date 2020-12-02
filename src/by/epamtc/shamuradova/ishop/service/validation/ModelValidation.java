package by.epamtc.shamuradova.ishop.service.validation;

import java.math.BigDecimal;

import by.epamtc.shamuradova.ishop.bean.ModelEdition;
import by.epamtc.shamuradova.ishop.bean.entity.Model;
import by.epamtc.shamuradova.ishop.service.exception.ResourceNotFoundServiceException;
import by.epamtc.shamuradova.ishop.service.exception.ValidationException;

public class ModelValidation {

	private static final int MIN_PRICE = 10;

	private ModelValidation() {
	}

	public static void checkModel(Model model) throws ResourceNotFoundServiceException {
		if (model == null)
			throw new ResourceNotFoundServiceException("Model not found");

	}

	public static void validate(ModelEdition modelEdition) throws ValidationException {
		StringBuilder errorMessage = new StringBuilder();

		if (modelEdition.getName() == null || "".equals(modelEdition.getName().trim())) {
			errorMessage.append("Name CANT BE EMPTY ");
		}
		if (modelEdition.getPrice().compareTo(new BigDecimal(MIN_PRICE)) == -1) {
			errorMessage.append("price cant be below min price than " + MIN_PRICE);
		}
		if (modelEdition.getCount() < 0) {
			errorMessage.append("Count less than zero ");
		}
		if (modelEdition.getDescription() == null || "".equals(modelEdition.getDescription().trim())) {
			errorMessage.append("Description cant be empty ");
		}
		if (modelEdition.getCategory() == null || "".equals(modelEdition.getCategory().trim())) {
			errorMessage.append("Category cant be empty ");
		}
		if (modelEdition.getProducer() == null || "".equals(modelEdition.getProducer().trim())) {
			errorMessage.append("Producer cant be empty ");
		}
		if (errorMessage.length() != 0) {
			throw new ValidationException(errorMessage.toString());
		}
	}

}
