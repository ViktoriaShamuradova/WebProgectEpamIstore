package by.epamtc.shamuradova.ishop.service;

import java.util.List;

import by.epamtc.shamuradova.ishop.bean.ModelEdition;
import by.epamtc.shamuradova.ishop.bean.entity.Category;
import by.epamtc.shamuradova.ishop.bean.entity.Model;
import by.epamtc.shamuradova.ishop.bean.entity.Producer;
import by.epamtc.shamuradova.ishop.bean.entity.User;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;

/** Интерфейс ModelService, в котром содержатся методы для объекта Model, Category 
 * 
 * @param int page - какую страницу товаров нужно отобразить, 
 * @param int limit - максимальное количество товаров, которое должно вернуться данным запросом
 * 
 * ModelService interface, which contains methods for the Model object, Category
 *
 * @param int page - какую страницу товаров нужно отобразить, 
 * @param int limit - максимальное количество товаров, которое должно вернуться данным запросом
 *
 * @author Victoria Shamuradova 2020
 */


public interface ModelService {

	public Model getModel(int idModel) throws ServiceException;

	public List<Model> listAllModels(int page, int limit) throws ServiceException;

	public List<Model> listModelsByCategory(int categoryId, int page, int limit) throws ServiceException;

	public List<Category> listAllCategories() throws ServiceException;

	public List<Producer> listAllProducer() throws ServiceException;

	public int countModels() throws ServiceException;

	public int countModelsByCategoryId(int categoryId) throws ServiceException;

	public void saveEditionModel(User user, ModelEdition modelEdition) throws ServiceException;

	public void saveNewModel(User user, ModelEdition modelEdition) throws ServiceException;

	public byte[] getImageByModelId(int modelId) throws ServiceException;
}
