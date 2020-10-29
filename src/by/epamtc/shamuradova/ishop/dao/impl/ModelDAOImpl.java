package by.epamtc.shamuradova.ishop.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import by.epamtc.shamuradova.ishop.bean.Category;
import by.epamtc.shamuradova.ishop.bean.Model;
import by.epamtc.shamuradova.ishop.bean.Producer;
import by.epamtc.shamuradova.ishop.constant.ErrorMessage;
import by.epamtc.shamuradova.ishop.constant.SQLQuery;
import by.epamtc.shamuradova.ishop.dao.ModelDAO;
import by.epamtc.shamuradova.ishop.dao.exception.ConnectionPoolException;
import by.epamtc.shamuradova.ishop.dao.exception.DAOException;
import by.epamtc.shamuradova.ishop.dao.handler.ResultSetHandler2;
import by.epamtc.shamuradova.ishop.dao.handler.factory.ResultSetHandler2Factory;
import by.epamtc.shamuradova.ishop.dao.handler.impl.ResultSetHandlerCategory2;
import by.epamtc.shamuradova.ishop.dao.handler.impl.ResultSetHandlerFactory;
import by.epamtc.shamuradova.ishop.dao.handler.impl.ResultSetHandlerModel2;
import by.epamtc.shamuradova.ishop.dao.handler.impl.ResultSetHandlerProducer2;
import by.epamtc.shamuradova.ishop.dao.pool.ConnectionPool;
import by.epamtc.shamuradova.ishop.dao.util.JDBCUtil;

public class ModelDAOImpl implements ModelDAO {

	private ResultSetHandler2<List<Producer>> resultSetHandlerProducers;
	private ResultSetHandler2<List<Model>> resultSetHandlerModels;
	private ResultSetHandler2<List<Category>> resultSetHandlerCategories;
	
	private ResultSetHandler2<Model> resultSetHandlerModel;
	private ResultSetHandler2<Integer> getterCountResultSetHandler;

	private ConnectionPool pool;

	public ModelDAOImpl() {
		pool = ConnectionPool.getInstance();

		resultSetHandlerModel = ResultSetHandlerFactory.getSingleResultSetHandler(new ResultSetHandlerModel2());
		resultSetHandlerCategories = ResultSetHandlerFactory.getListResultSetHandler(new ResultSetHandlerCategory2());
		resultSetHandlerModels = ResultSetHandlerFactory.getListResultSetHandler(new  ResultSetHandlerModel2());
		resultSetHandlerProducers = ResultSetHandlerFactory.getListResultSetHandler(new ResultSetHandlerProducer2());
		getterCountResultSetHandler = ResultSetHandler2Factory.getInstatnce().getGetterCountResultSetHandler();
	}

	@Override
	public List<Model> listAllModels(int page, int limit) throws DAOException {
		Connection connection = null;

		try {
			connection = pool.getConnection();
			int offset = (page - 1) * limit;// сколько отступаем

			return JDBCUtil.select(connection, SQLQuery.LIST_MODELS, resultSetHandlerModels, limit,
					offset);

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException("can not execute query", e);
		} finally {
			freeConnection(connection);
		}
	}

	@Override
	public Model getModelById(int idModel) throws DAOException {
		Connection connection = null;
		try {
			connection = pool.getConnection();
			String sql = SQLQuery.MODEL_BY_ID;
			return JDBCUtil.select(connection, sql, resultSetHandlerModel, idModel);

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(ErrorMessage.DATABASE_ERROR, e);

		} finally {
			freeConnection(connection);
		}
	}

	@Override
	public List<Model> listModelsByCategory(String categoryUrl, int page, int limit) throws DAOException {
		Connection connection = null;

		try {
			connection = pool.getConnection();
			int offset = (page - 1) * limit;

			return JDBCUtil.select(connection, SQLQuery.LIST_MODELS_BY_CATEGORY, resultSetHandlerModels, categoryUrl,
					limit, offset);

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			freeConnection(connection);
		}
	}

	@Override
	public List<Category> listAllCategories() throws DAOException {
		Connection connection = null;

		try {
			connection = pool.getConnection();

			return JDBCUtil.select(connection, SQLQuery.LIST_CATEGORY, resultSetHandlerCategories);

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			freeConnection(connection);
		}
	}

	
	@Override
	public List<Producer> listAllProducer() throws DAOException {
		Connection connection = null;

		try {
			connection = pool.getConnection();

			return JDBCUtil.select(connection, SQLQuery.LIST_PRODUCER, resultSetHandlerProducers);

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			freeConnection(connection);
		}
	}
	
	private void freeConnection(Connection connection) throws DAOException {
		if (connection != null) {
			try {
				pool.free(connection);
			} catch (ConnectionPoolException e) {
				throw new DAOException(ErrorMessage.UNABLE_TO_FREE_CONNECTION);
			}
		}
	}
	
	@Override
	public int countModels() throws DAOException {
		Connection connection = null;
		try {
			connection = pool.getConnection();

			return JDBCUtil.select(connection, SQLQuery.COUNT_MODELS, getterCountResultSetHandler);

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			freeConnection(connection);
		}
	}
	
	
	@Override
	public int countModelsByVategoryUrl(String categoryUrl) throws DAOException {
		Connection connection = null;
		try {
			connection = pool.getConnection();

			return JDBCUtil.select(connection, SQLQuery.COUNT_MODELS_BY_CATEGORY_URL, getterCountResultSetHandler, categoryUrl);

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			freeConnection(connection);
		}
	}

}
