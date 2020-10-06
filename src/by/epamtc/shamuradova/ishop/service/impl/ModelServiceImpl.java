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
 *page - какую страницу товаров нужно отобразить, limit - максимальное количество товаров, которое должно вернуться данным запросом
 * 
 *
 * @author Шамурадова Виктория 2020
 */
public class ModelServiceImpl implements ModelService{

	
	@Override
	public List<Model> listAllModels(int page, int count) throws ServiceException {
		ModelDAO modelDao = new ModelDAOImpl();
		
		try {
			return modelDao.listAllModels(page, count);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Model> listModelsByCategory(String categoryUrl, int page, int limit) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Category> listAllCategories() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Producer> listAllProducer() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}
