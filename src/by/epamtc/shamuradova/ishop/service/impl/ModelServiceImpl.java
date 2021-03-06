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
import by.epamtc.shamuradova.ishop.dao.ModelDAO;
import by.epamtc.shamuradova.ishop.dao.exception.DAOException;
import by.epamtc.shamuradova.ishop.dao.factory.DAOFactory;
import by.epamtc.shamuradova.ishop.service.ModelService;
import by.epamtc.shamuradova.ishop.service.exception.ResourceNotFoundServiceException;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;
import by.epamtc.shamuradova.ishop.service.validation.ModelValidation;
import by.epamtc.shamuradova.ishop.service.validation.UserValidation;

/**
 * Класс, реализующий интерфейс ModelService, в котром содержатся методы для
 * объектов Model, Category В этом классе проводится валидация
 * 
 * @param int page - какую страницу товаров нужно отобразить,
 * @param int limit - максимальное количество товаров, которое должно вернуться
 *            данным запросом
 * 
 *            A class that implements the ModelService interface, which contains
 *            methods for the Model object, Category
 *
 * @param int page - какую страницу товаров нужно отобразить,
 * @param int limit - максимальное количество товаров, которое должно вернуться
 *            данным запросом
 *
 * @author Victoria Shamuradova 2020
 */

public class ModelServiceImpl implements ModelService {

	private static final String IMAGE_LINK = "controller?command=GET_IMAGE_BY_MODEL_ID&modelId=";

	private ModelDAO modelDao;

	public ModelServiceImpl() {
		modelDao = DAOFactory.getInstance().getModelDAO();

	}

	@Override
	public List<Model> listAllModels(int page, int limit) throws ServiceException {
		try {
			List<Model> models = modelDao.listAllModels(page, limit);

			if (models == null)
				throw new ResourceNotFoundServiceException("models not found");

			addImageLinkToModel(models);

			return models;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Model> listModelsByCategory(int categoryId, int page, int limit) throws ServiceException {
		try {
			List<Model> models = modelDao.listModelsByCategory(categoryId, page, limit);

			if (models == null)
				throw new ResourceNotFoundServiceException("models  not found");

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
				throw new ResourceNotFoundServiceException("categories  not found");

			return categories;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Model getModel(int idModel) throws ServiceException {
		try {
			Model model = modelDao.getModelById(idModel);
			if (model == null)
				throw new ResourceNotFoundServiceException("model  not found");
			return model;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public int countModels() throws ServiceException {
		try {
			return modelDao.countModels();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public int countModelsByCategoryId(int categoryId) throws ServiceException {
		try {

			return modelDao.countModelsByCategoryId(categoryId);

		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void saveEditionModel(User user, ModelEdition modelEdition) throws ServiceException {
		ModelValidation.validate(modelEdition);
		UserValidation.checkRoleAdmin(user);
		try {
			modelDao.updateModel(modelEdition);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void saveNewModel(User user, ModelEdition modelEdition) throws ServiceException {
		ModelValidation.validate(modelEdition);
		UserValidation.checkRoleAdmin(user);
		try {
			modelDao.addNewModel(modelEdition);
		} catch (DAOException e) {
			throw new ServiceException(e);
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
			throw new ServiceException(e);
		}
	}

	private void addImageLinkToModel(List<Model> models) {
		for (Model model : models) {
			model.setImageLink(IMAGE_LINK + model.getId());
		}
	}

	@Override
	public List<Producer> listAllProducer() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
}
