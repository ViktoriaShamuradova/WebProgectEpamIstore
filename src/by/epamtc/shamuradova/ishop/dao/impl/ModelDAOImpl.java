package by.epamtc.shamuradova.ishop.dao.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
	private ResultSetHandler2<InputStream> resultSetHandlerImage;

	private ConnectionPool pool;

	public ModelDAOImpl() {
		pool = ConnectionPool.getInstance();

		resultSetHandlerModel = ResultSetHandlerFactory.getSingleResultSetHandler(new ResultSetHandlerModel2());
		resultSetHandlerCategories = ResultSetHandlerFactory.getListResultSetHandler(new ResultSetHandlerCategory2());
		resultSetHandlerModels = ResultSetHandlerFactory.getListResultSetHandler(new ResultSetHandlerModel2());
		resultSetHandlerProducers = ResultSetHandlerFactory.getListResultSetHandler(new ResultSetHandlerProducer2());
		getterCountResultSetHandler = ResultSetHandler2Factory.getInstatnce().getGetterCountResultSetHandler();
		resultSetHandlerImage = ResultSetHandlerFactory.getImageResultSetHandler();
	}

	@Override
	public List<Model> listAllModels(int page, int limit) throws DAOException {
		Connection connection = null;

		try {
			connection = pool.getConnection();
			int offset = (page - 1) * limit;// сколько отступаем

			return JDBCUtil.select(connection, SQLQuery.LIST_MODELS, resultSetHandlerModels, limit, offset);

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

			return JDBCUtil.select(connection, SQLQuery.COUNT_MODELS_BY_CATEGORY_URL, getterCountResultSetHandler,
					categoryUrl);

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
					modelEdition.getDescription(), modelEdition.getPrice(),

					modelEdition.getCategory(), modelEdition.getProducer(), modelEdition.getCount());

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

			return JDBCUtil.select(connection, SQLQuery.MODEL_IMAGE_BY_ID, resultSetHandlerImage, modelId);

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			freeConnection(connection);
		}
	}

	public static void main(String[] args) throws ConnectionPoolException, SQLException, DAOException, IOException {

		ModelDAOImpl dao = new ModelDAOImpl();

//		dao.pool.initPoolData();
//		Connection conn = dao.pool.getConnection();
//
//		File file = new File("./WebContent/front/img/laptop.jpg");
//		FileInputStream input = new FileInputStream(file);
//
//		PreparedStatement st = conn.prepareStatement("update models set image_link=? where id=41");
//
//		st.setBinaryStream(1, input);
//
//		st.executeUpdate();
//		dao.freeConnection(conn);
//		System.out.println(file.getAbsolutePath());
//
//		// еперь читаем
//		Connection conn2 = dao.pool.getConnection();
//		PreparedStatement st2 = conn.prepareStatement("select image_link from models where id=41");
//		ResultSet rs = st2.executeQuery();
//
//		ByteArrayOutputStream os = new ByteArrayOutputStream();
//		if (rs.next()) {
//			InputStream in = rs.getBinaryStream("image_link");
//
//			byte[] buffer = new byte[1024];
//			while (in.read(buffer) > 0) {
//				os.write(buffer);
//			}
//		}
//		dao.freeConnection(conn2);
		InputStream in = dao.getModelImageById(41);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		
		byte[] buffer = new byte[1024];
		while (in.read(buffer) > 0) {
			os.write(buffer);
		}
		
		byte[] imageByte = os.toByteArray();
		for (int i = 0; i < imageByte.length; i++) {
			System.out.println(imageByte[i]);
		}

	}

}
