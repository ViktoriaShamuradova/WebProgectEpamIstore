package by.epamtc.shamuradova.ishop.dao;

import java.io.InputStream;
import java.util.List;

import by.epamtc.shamuradova.ishop.bean.ModelEdition;
import by.epamtc.shamuradova.ishop.bean.entity.Category;
import by.epamtc.shamuradova.ishop.bean.entity.Model;
import by.epamtc.shamuradova.ishop.bean.entity.Producer;
import by.epamtc.shamuradova.ishop.dao.exception.DAOException;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;

public interface ModelDAO {

	public List<Model> listAllModels(int page, int limit) throws DAOException;

	public Model getModelById(int modelId) throws DAOException;

	public List<Model> listModelsByCategory(String categoryUrl, int page, int limit) throws DAOException;

	public List<Category> listAllCategories() throws DAOException;

	public List<Producer> listAllProducer() throws DAOException;

	public int countModels() throws DAOException;

	public int countModelsByVategoryUrl(String categoryUrl) throws DAOException;

	public void updateModel(ModelEdition modelEdition) throws DAOException;

	public void addNewModel(ModelEdition modelEdition) throws DAOException;

	public InputStream getModelImageById(int modelId) throws DAOException;

}
