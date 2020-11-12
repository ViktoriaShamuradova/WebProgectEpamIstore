package by.epamtc.shamuradova.ishop.dao.impl;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import by.epamtc.shamuradova.ishop.bean.ModelEdition;
import by.epamtc.shamuradova.ishop.bean.entity.Category;
import by.epamtc.shamuradova.ishop.bean.entity.Model;
import by.epamtc.shamuradova.ishop.bean.entity.Producer;
import by.epamtc.shamuradova.ishop.constant.ErrorMessage;
import by.epamtc.shamuradova.ishop.constant.SQLQuery;
import by.epamtc.shamuradova.ishop.dao.ModelDAO;
import by.epamtc.shamuradova.ishop.dao.exception.ConnectionPoolException;
import by.epamtc.shamuradova.ishop.dao.exception.DAOException;
import by.epamtc.shamuradova.ishop.dao.handler.impl.ResultSetHandlerFactory;
import by.epamtc.shamuradova.ishop.dao.pool.ConnectionPool;
import by.epamtc.shamuradova.ishop.dao.util.JDBCUtil;

/**
 * Класс, реализующий интерфейс ModelDAO, использующий sql- запросы
 * 
 * Class that implements the ModelDAO interface using sql queries
 *
 * @author Victoria Shamuradova 2020
 */

public class SQLModelDAOImpl implements ModelDAO {

	private ConnectionPool pool;

	public SQLModelDAOImpl() {
		pool = ConnectionPool.getInstance();

	}

	@Override
	public List<Model> listAllModels(int page, int limit) throws DAOException {
		Connection connection = null;

		try {
			connection = pool.getConnection();
			int offset = (page - 1) * limit;// сколько отступаем

			return JDBCUtil.select(connection, SQLQuery.LIST_MODELS,
					ResultSetHandlerFactory.getListResultSetHandler(ResultSetHandlerFactory.MODEL_RESULT_SET_HANDLER),
					limit, offset);

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
			return JDBCUtil.select(connection, sql,
					ResultSetHandlerFactory.getSingleResultSetHandler(ResultSetHandlerFactory.MODEL_RESULT_SET_HANDLER),
					idModel);

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(ErrorMessage.DATABASE_ERROR, e);

		} finally {
			freeConnection(connection);
		}
	}

	@Override
	public List<Model> listModelsByCategory(int categoryId, int page, int limit) throws DAOException {
		Connection connection = null;

		try {
			connection = pool.getConnection();
			int offset = (page - 1) * limit;

			return JDBCUtil.select(connection, SQLQuery.LIST_MODELS_BY_CATEGORY,
					ResultSetHandlerFactory.getListResultSetHandler(ResultSetHandlerFactory.MODEL_RESULT_SET_HANDLER),
					categoryId, limit, offset);

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

			return JDBCUtil.select(connection, SQLQuery.LIST_CATEGORY, ResultSetHandlerFactory
					.getListResultSetHandler(ResultSetHandlerFactory.CATEGORY_RESULT_SET_HANDLER));

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

			return JDBCUtil.select(connection, SQLQuery.LIST_PRODUCER, ResultSetHandlerFactory
					.getListResultSetHandler(ResultSetHandlerFactory.PRODUCER_RESULT_SET_HANDLER));

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

			return JDBCUtil.select(connection, SQLQuery.COUNT_MODELS, ResultSetHandlerFactory.COUNT_RESULT_SET_HANDLER);

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			freeConnection(connection);
		}
	}

	@Override
	public int countModelsByCategoryId(int categoryId) throws DAOException {
		Connection connection = null;
		try {
			connection = pool.getConnection();

			return JDBCUtil.select(connection, SQLQuery.COUNT_MODELS_BY_CATEGORY_ID,
					ResultSetHandlerFactory.COUNT_RESULT_SET_HANDLER, categoryId);

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			freeConnection(connection);
		}
	}

	@Override
	public void updateModel(ModelEdition modelEdition) throws DAOException {
		Connection connection = null;
		try {
			connection = pool.getConnection();

			JDBCUtil.insertDeleteUpdate(connection, SQLQuery.UPDATE_MODEL_BY_ID, modelEdition.getName(),
					modelEdition.getDescription(), modelEdition.getCategory(), modelEdition.getProducer(),
					modelEdition.getCount(), modelEdition.getPrice(), modelEdition.getId());

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			freeConnection(connection);
		}
	}

	@Override
	public void addNewModel(ModelEdition modelEdition) throws DAOException {
		Connection connection = null;
		try {

			connection = pool.getConnection();

			JDBCUtil.insertDeleteUpdate(connection, SQLQuery.ADD_NEW_MODEL, modelEdition.getName(),
					modelEdition.getDescription(), modelEdition.getPrice(), modelEdition.getCategory(),
					modelEdition.getProducer(), modelEdition.getCount());

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			freeConnection(connection);
		}
	}

	@Override
	public InputStream getModelImageById(int modelId) throws DAOException {
		Connection connection = null;
		try {
			connection = pool.getConnection();

			return JDBCUtil.select(connection, SQLQuery.MODEL_IMAGE_BY_ID,
					ResultSetHandlerFactory.getImageResultSetHandler(), modelId);

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			freeConnection(connection);
		}
	}
}
