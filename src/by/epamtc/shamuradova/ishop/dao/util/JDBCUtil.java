package by.epamtc.shamuradova.ishop.dao.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import by.epamtc.shamuradova.ishop.bean.entity.AbstractEntity;
import by.epamtc.shamuradova.ishop.constant.ErrorMessage;
import by.epamtc.shamuradova.ishop.dao.handler.ResultSetHandler;
import by.epamtc.shamuradova.ishop.dao.handler.ResultSetHandler2;

//разобраться с дженериками, фигня какая-то
public final class JDBCUtil {

	private JDBCUtil() {
	}

	public static <T> T select(Connection connection, String sql, ResultSetHandler2<T> resultSetHandler,
			Object... parameters) throws SQLException {

		PreparedStatement prStatement = null;
		ResultSet resultSet = null;

		try {
			prStatement = connection.prepareStatement(sql);
			setParameters(prStatement, parameters);
			ResultSet rs = prStatement.executeQuery();
			return resultSetHandler.handle(rs);

		} finally {
			closeStatement(prStatement);
			closeResultSet(resultSet);
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

//		try {
		callableStatement = connection.prepareCall(sql);
		setParameters(callableStatement, parameters);
		results = callableStatement.executeQuery();
		return results;

//		} finally {
//			closeStatement(callableStatement);
//		}
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

	private static void closeResultSet(ResultSet rs) throws SQLException {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new SQLException(ErrorMessage.UNABLE_TO_CLOSE_STATEMENT);
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
			closeResultSet(resultSet);
		}
	}

}
