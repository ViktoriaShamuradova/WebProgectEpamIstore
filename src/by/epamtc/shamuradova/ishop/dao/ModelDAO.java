package by.epamtc.shamuradova.ishop.dao;

import java.util.List;

import by.epamtc.shamuradova.ishop.bean.Model;
import by.epamtc.shamuradova.ishop.dao.exception.DAOException;

public interface ModelDAO {

	public List<Model> listAllModels(int page, int imit) throws DAOException;

	public Model getModel() throws DAOException;
}
