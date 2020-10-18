package by.epamtc.shamuradova.ishop.dao;

import java.util.List;

import by.epamtc.shamuradova.ishop.bean.Category;
import by.epamtc.shamuradova.ishop.bean.Model;
import by.epamtc.shamuradova.ishop.bean.Producer;
import by.epamtc.shamuradova.ishop.dao.exception.DAOException;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;

public interface ModelDAO {

	public List<Model> listAllModels(int page, int limit) throws DAOException;

	public Model getModelById(int idModel) throws DAOException;

	public List<Model> listModelsByCategory(String categoryUrl, int page, int limit) throws DAOException;

	public List<Category> listAllCategories() throws DAOException;

	public List<Producer> listAllProducer() throws DAOException;

}
