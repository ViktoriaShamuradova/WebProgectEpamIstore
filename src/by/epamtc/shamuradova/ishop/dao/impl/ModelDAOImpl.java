package by.epamtc.shamuradova.ishop.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.epamtc.shamuradova.ishop.bean.AbstractEntity;
import by.epamtc.shamuradova.ishop.bean.Category;
import by.epamtc.shamuradova.ishop.bean.Model;
import by.epamtc.shamuradova.ishop.bean.Producer;
import by.epamtc.shamuradova.ishop.constant.ErrorMessage;
import by.epamtc.shamuradova.ishop.constant.SQLQuery;
import by.epamtc.shamuradova.ishop.dao.ModelDAO;
import by.epamtc.shamuradova.ishop.dao.exception.ConnectionPoolException;
import by.epamtc.shamuradova.ishop.dao.exception.DAOException;
import by.epamtc.shamuradova.ishop.dao.handler.ResultSetHandler;
import by.epamtc.shamuradova.ishop.dao.handler.impl.ResultSetHandlerCategory;
import by.epamtc.shamuradova.ishop.dao.handler.impl.ResultSetHandlerModel;
import by.epamtc.shamuradova.ishop.dao.handler.impl.ResultSetHandlerProducer;
import by.epamtc.shamuradova.ishop.dao.pool.ConnectionPool;
import by.epamtc.shamuradova.ishop.dao.util.JDBCUtil;

public class ModelDAOImpl implements ModelDAO {

	public ModelDAOImpl() {
	}

	@Override
	public List<Model> listAllModels(int page, int limit) throws DAOException {
		ResultSetHandler resultSetHandlerModel = new ResultSetHandlerModel();
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = null;

		List<Model> models = new ArrayList<>();
		List<AbstractEntity> entityList;

		try {
			pool.initPoolData();
			connection = pool.getConnection();
			int offset = (page - 1) * limit;

			entityList = JDBCUtil.selectList(connection, SQLQuery.LIST_MODELS, resultSetHandlerModel, limit, offset);

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			if (connection != null) {
				try {
					pool.free(connection);
				} catch (ConnectionPoolException e) {
					throw new DAOException(ErrorMessage.UNABLE_TO_FREE_CONNECTION);
				}
			}
		}
		for (AbstractEntity entity : entityList) {
			models.add((Model) entity);
		}

		return models;
	}

	// какую модель???
	@Override
	public Model getModel() throws DAOException {
		ResultSetHandler resultSetHandlerModel = new ResultSetHandlerModel();
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = null;
		Model model;
		try {
			pool.initPoolData();
			connection = pool.getConnection();
			String sql = null;

			model = (Model) JDBCUtil.selectSingle(connection, sql, resultSetHandlerModel, null);

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(ErrorMessage.DATABASE_ERROR, e);

		} finally {
			if (connection != null) {
				try {
					pool.free(connection);
				} catch (ConnectionPoolException e) {
					throw new DAOException(ErrorMessage.UNABLE_TO_FREE_CONNECTION);
				}
			}
		}

		return model;
	}

	@Override
	public List<Model> listModelsByCategory(String categoryUrl, int page, int limit) throws DAOException {
		ResultSetHandler resultSetHandlerModel = new ResultSetHandlerModel();
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = null;
		List<Model> models = new ArrayList<>();
		List<AbstractEntity> entityList;

		try {
			pool.initPoolData();
			connection = pool.getConnection();
			int offset = 0;

			entityList = JDBCUtil.selectList(connection, SQLQuery.LIST_MODELS_BY_CATEGORY, resultSetHandlerModel,
					categoryUrl, limit, offset);

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			if (connection != null) {
				try {
					pool.free(connection);
				} catch (ConnectionPoolException e) {
					throw new DAOException(ErrorMessage.UNABLE_TO_FREE_CONNECTION);
				}
			}
		}
		for (AbstractEntity entity : entityList) {
			models.add((Model) entity);
		}

		return models;
	}

	@Override
	public List<Category> listAllCategories() throws DAOException {
		ResultSetHandlerCategory resultSetHandlerCategory = new ResultSetHandlerCategory();
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = null;

		List<Category> categories = new ArrayList<>();
		List<AbstractEntity> entityList;

		try {
			pool.initPoolData();
			connection = pool.getConnection();

			entityList = JDBCUtil.selectList(connection, SQLQuery.LIST_CATEGORY, resultSetHandlerCategory);

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			if (connection != null) {
				try {
					pool.free(connection);
				} catch (ConnectionPoolException e) {
					throw new DAOException(ErrorMessage.UNABLE_TO_FREE_CONNECTION);
				}
			}
		}
		for (AbstractEntity entity : entityList) {
			categories.add((Category) entity);
		}

		return categories;
	}

	@Override
	public List<Producer> listAllProducer() throws DAOException {
		ResultSetHandler resultSetHandlerProducer = new ResultSetHandlerProducer();
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = null;

		List<Producer> producers = new ArrayList<>();
		List<AbstractEntity> entityList;

		try {
			pool.initPoolData();
			connection = pool.getConnection();

			entityList = JDBCUtil.selectList(connection, SQLQuery.LIST_PRODUCER, resultSetHandlerProducer);

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			if (connection != null) {
				try {
					pool.free(connection);
				} catch (ConnectionPoolException e) {
					throw new DAOException(ErrorMessage.UNABLE_TO_FREE_CONNECTION);
				}
			}
		}
		for (AbstractEntity entity : entityList) {
			producers.add((Producer) entity);
		}

		return producers;
	}

	public static void main(String[] args) throws DAOException {
		ModelDAOImpl model = new ModelDAOImpl();
		System.out.println(model.listModelsByCategory("mobile", 1, 5));
		//System.out.println(model.listAllProducer());
	}
}
