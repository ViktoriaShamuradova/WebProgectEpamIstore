package by.epamtc.shamuradova.ishop.service;

import java.util.List;

import by.epamtc.shamuradova.ishop.bean.Category;
import by.epamtc.shamuradova.ishop.bean.Model;
import by.epamtc.shamuradova.ishop.bean.Producer;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;
//page - какую страницу мделей нужно отобразить
//limit - максимальное колва моделей должно вернуться запросом

public interface ModelService {

	public Model getModel(int idModel) throws ServiceException;

	public List<Model> listAllModels(int page, int limit) throws ServiceException;

	public List<Model> listModelsByCategory(String categoryUrl, int page, int limit) throws ServiceException;

	public List<Category> listAllCategories() throws ServiceException;

	public List<Producer> listAllProducer() throws ServiceException;
}
