package by.epamtc.shamuradova.ishop.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import by.epamtc.shamuradova.ishop.bean.ModelEdition;
import by.epamtc.shamuradova.ishop.bean.entity.Category;
import by.epamtc.shamuradova.ishop.bean.entity.Model;
import by.epamtc.shamuradova.ishop.bean.entity.Producer;
import by.epamtc.shamuradova.ishop.bean.entity.User;
import by.epamtc.shamuradova.ishop.constant.ErrorMessage;
import by.epamtc.shamuradova.ishop.dao.ModelDAO;
import by.epamtc.shamuradova.ishop.dao.exception.DAOException;
import by.epamtc.shamuradova.ishop.dao.impl.ModelDAOImpl;
import by.epamtc.shamuradova.ishop.service.ModelService;
import by.epamtc.shamuradova.ishop.service.exception.InternalServiceException;
import by.epamtc.shamuradova.ishop.service.exception.ResourceNotFoundServiceException;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;
import by.epamtc.shamuradova.ishop.service.validation.ModelValidation;
import by.epamtc.shamuradova.ishop.service.validation.UserValidation;

/**
 * page - какую страницу товаров нужно отобразить, limit - максимальное
 * количество товаров, которое должно вернуться данным запросом
 * 
 *
 * @author Шамурадова Виктория 2020
 */
public class ModelServiceImpl implements ModelService {

	private static final String IMAGE_LINK = "controller?command=GET_IMAGE_BY_MODEL_ID&modelId=";

	private ModelDAO modelDao;

	public ModelServiceImpl() {
		modelDao = new ModelDAOImpl();

	}

	@Override
	public List<Model> listAllModels(int page, int count) throws ServiceException {
		try {
			List<Model> models = modelDao.listAllModels(page, count);

			if (models == null)
				throw new ResourceNotFoundServiceException("models " + ErrorMessage.NOT_FOUND);

			addImageLinkToModel(models);

			return models;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Model> listModelsByCategory(String categoryUrl, int page, int limit) throws ServiceException {
		try {
			List<Model> models = modelDao.listModelsByCategory(categoryUrl, page, limit);

			if (models == null)
				throw new ResourceNotFoundServiceException("models " + ErrorMessage.NOT_FOUND);

			addImageLinkToModel(models);

			return models;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Category> listAllCategories() throws ServiceException {

		try {
			List<Category> categories = modelDao.listAllCategories();

			if (categories == null)
				throw new ResourceNotFoundServiceException("categories " + ErrorMessage.NOT_FOUND);

			return categories;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Producer> listAllProducer() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Model getModel(int idModel) throws ServiceException {
		try {
			Model model = modelDao.getModelById(idModel);
			if (model == null)
				throw new ResourceNotFoundServiceException("model " + ErrorMessage.NOT_FOUND);
			return model;
		} catch (DAOException e) {
			throw new InternalServiceException(e);
		}
	}

	@Override
	public int countModels() throws ServiceException {
		try {
			return modelDao.countModels();
		} catch (DAOException e) {
			throw new InternalServiceException(e);
		}
	}

	@Override
	public int countModelsByCategoryUrl(String categoryUrl) throws ServiceException {
		if (categoryUrl != null) {
			try {

				return modelDao.countModelsByVategoryUrl(categoryUrl);

			} catch (DAOException e) {
				throw new InternalServiceException(e);
			}
		} else {
			return 0;
		}
	}

	@Override
	public void saveEditionModel(User user, ModelEdition modelEdition) throws ServiceException {
		ModelValidation.validate(modelEdition);
		UserValidation.checkRoleAdmin(user);
		try {
			modelDao.updateModel(modelEdition);
		} catch (DAOException e) {
			throw new InternalServiceException(e);
		}
	}

	@Override
	public void saveNewModel(User user, ModelEdition modelEdition) throws ServiceException {
		ModelValidation.validate(modelEdition);
		UserValidation.checkRoleAdmin(user);
		try {
			modelDao.addNewModel(modelEdition);
		} catch (DAOException e) {
			throw new InternalServiceException(e);
		}
	}

	@Override
	public byte[] getImageByModelId(int modelId) throws ServiceException {
		try {
			InputStream in = modelDao.getModelImageById(modelId);
			ByteArrayOutputStream os = new ByteArrayOutputStream();

			byte[] buffer = new byte[1024];

			while (in.read(buffer) > 0) {
				os.write(buffer);
			}

			return os.toByteArray();
		} catch (DAOException | IOException e) {
			throw new InternalServiceException(e);
		}
	}

	private void addImageLinkToModel(List<Model> models) {
		for (Model model : models) {
			model.setImageLink(IMAGE_LINK + model.getId());
		}
	}
}
