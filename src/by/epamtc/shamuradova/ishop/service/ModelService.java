package by.epamtc.shamuradova.ishop.service;

import java.util.List;

import by.epamtc.shamuradova.ishop.bean.ModelEdition;
import by.epamtc.shamuradova.ishop.bean.entity.Category;
import by.epamtc.shamuradova.ishop.bean.entity.Model;
import by.epamtc.shamuradova.ishop.bean.entity.Producer;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;

public interface ModelService {

	public Model getModel(int idModel) throws ServiceException;

	public List<Model> listAllModels(int page, int limit) throws ServiceException;

	public List<Model> listModelsByCategory(String categoryUrl, int page, int limit) throws ServiceException;

	public List<Category> listAllCategories() throws ServiceException;

	public List<Producer> listAllProducer() throws ServiceException;

	public int countModels() throws ServiceException;

	public int countModelsByCategoryUrl(String categoryUrl) throws ServiceException;

	public void saveEditionModel(ModelEdition modelEdition) throws ServiceException;

	public void saveNewModel(ModelEdition modelEdition) throws ServiceException;

	public byte[] getImageByModelId(int modelId) throws ServiceException;
}
