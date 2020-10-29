package by.epamtc.shamuradova.ishop.service.impl;

import java.util.List;

import by.epamtc.shamuradova.ishop.bean.Category;
import by.epamtc.shamuradova.ishop.bean.Model;
import by.epamtc.shamuradova.ishop.bean.Producer;
import by.epamtc.shamuradova.ishop.dao.ModelDAO;
import by.epamtc.shamuradova.ishop.dao.exception.DAOException;
import by.epamtc.shamuradova.ishop.dao.impl.ModelDAOImpl;
import by.epamtc.shamuradova.ishop.service.ModelService;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;

/**
 * page - какую страницу товаров нужно отобразить, limit - максимальное
 * количество товаров, которое должно вернуться данным запросом
 * 
 *
 * @author Шамурадова Виктория 2020
 */
public class ModelServiceImpl implements ModelService {

	private ModelDAO modelDao;

	public ModelServiceImpl() {
		modelDao = new ModelDAOImpl();
	}

	@Override
	public List<Model> listAllModels(int page, int count) throws ServiceException {

		try {
			return modelDao.listAllModels(page, count);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Model> listModelsByCategory(String categoryUrl, int page, int limit) throws ServiceException {

		try {
			return modelDao.listModelsByCategory(categoryUrl, page, limit);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public List<Category> listAllCategories() throws ServiceException {

		try {
			return modelDao.listAllCategories();
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
			return modelDao.getModelById(idModel);
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
	public int countModelsByCategoryUrl(String categoryUrl) throws ServiceException {
		if (categoryUrl != null) {
			try {

				return modelDao.countModelsByVategoryUrl(categoryUrl);

			} catch (DAOException e) {
				throw new ServiceException(e);
			}
		} else {
			return 0;
		}

	}

}
