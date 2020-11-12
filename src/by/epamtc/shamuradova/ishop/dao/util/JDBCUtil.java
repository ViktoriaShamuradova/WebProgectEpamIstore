package by.epamtc.shamuradova.ishop.dao.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import by.epamtc.shamuradova.ishop.constant.ErrorMessage;
import by.epamtc.shamuradova.ishop.dao.handler.ResultSetHandler;


/** Класс, который умеет выполнять запросы и преобразовывать результаты запросов в объект нужного типа
 * 
 * A class that can execute queries and convert query results into an object of the desired type
 *
 * @author Шамурадова Виктория 2020
 */

public final class JDBCUtil {

	private JDBCUtil() {
	}
	/** 
	 *  параметризированный метод, выполняющий select запросы
	 *  
	 *  a parameterized method that executes select queries
	 *  
	 * @param Connection connection - объект для соединения с базой данных
	 * @param String sql- sql запрос
	 * @param ResultSetHandler2<T> - объект, который преобразовывает результат запроса в java-объект
	 * @param Object... parameters - параметры для 
	 * @throws SQLException
	 */
	public static <T> T select(Connection connection, String sql, ResultSetHandler<T> resultSetHandler,
			Object... parameters) throws SQLException {

		PreparedStatement prStatement = null;

		try {
			prStatement = connection.prepareStatement(sql);
			setParameters(prStatement, parameters);
			ResultSet rs = prStatement.executeQuery();
			return resultSetHandler.handle(rs);

		} finally {
			closeStatement(prStatement);
		}
	}

	public static void insertDeleteUpdate(Connection connection, String sql, Object... parameters) throws SQLException {

		PreparedStatement prStatement = connection.prepareStatement(sql);
		try {
			setParameters(prStatement, parameters);
			prStatement.executeUpdate();
		} finally {
			closeStatement(prStatement);
		}

	}

	public static ResultSet call(Connection connection, String sql, Object... parameters) throws SQLException {
		CallableStatement callableStatement = null;
		ResultSet results = null;

		callableStatement = connection.prepareCall(sql);
		setParameters(callableStatement, parameters);
		results = callableStatement.executeQuery();
		return results;

	}

	private static void setParameters(PreparedStatement prStatement, Object[] parameters) throws SQLException {
		if (parameters != null) {
			for (int i = 0; i < parameters.length; i++) {
				prStatement.setObject(i + 1, parameters[i]);
			}
		}
	}

	private static void closeStatement(Statement statement) throws SQLException {
		if (statement != null && !statement.isClosed()) {
			try {
				statement.close();
			} catch (SQLException e) {
				throw new SQLException(ErrorMessage.UNABLE_TO_CLOSE_STATEMENT, e);
			}
		}
	}

	public static int selectSingleWithOUTPUT(Connection connection, String sql) throws SQLException {
		PreparedStatement prStatement = null;
		ResultSet resultSet = null;

		try {
			prStatement = connection.prepareStatement(sql);
			resultSet = prStatement.executeQuery();

			int id = 0;
			if (resultSet.next()) {
				id = resultSet.getInt("LAST_INSERT_ID()");

			}
			return id;

		} finally {
			closeStatement(prStatement);
		}
	}
}
