package by.epamtc.shamuradova.ishop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import by.epamtc.shamuradova.ishop.bean.Model;
import by.epamtc.shamuradova.ishop.dao.ModelDAO;
import by.epamtc.shamuradova.ishop.dao.exception.DAOException;
import by.epamtc.shamuradova.ishop.dao.pool.ConnectionPool;

public class ModelDAOImpl implements ModelDAO{

	

	@Override
	public List<Model> listAllModels(int page, int imit)throws DAOException {
		
		
		
		return null;
	}

	
	
	@Override
	public Model getModel() throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		
		return null;
	}

}
