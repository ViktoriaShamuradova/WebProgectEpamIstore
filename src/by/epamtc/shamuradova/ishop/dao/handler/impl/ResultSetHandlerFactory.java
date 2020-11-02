package by.epamtc.shamuradova.ishop.dao.handler.impl;

import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.epamtc.shamuradova.ishop.constant.database_column_name.ModelColumnName;
import by.epamtc.shamuradova.ishop.dao.handler.ResultSetHandler2;

//перенести сюда все хэндлеры
public class ResultSetHandlerFactory {

	private ResultSetHandlerFactory() {
	}

	public static <T> ResultSetHandler2<List<T>> getListResultSetHandler(ResultSetHandler2<T> oneRowResultSetHandler) {
		return new ResultSetHandler2<List<T>>() {

			@Override
			public List<T> handle(ResultSet rs) throws SQLException {
				List<T> list = new ArrayList<>();
				while (rs.next()) {
					list.add(oneRowResultSetHandler.handle(rs));
				}
				return list;
			}
		};
	}

	public static <T> ResultSetHandler2<T> getSingleResultSetHandler(ResultSetHandler2<T> oneRowResultSetHandler) {
		return new ResultSetHandler2<T>() {

			@Override
			public T handle(ResultSet rs) throws SQLException {
				if (rs.next()) {
					return oneRowResultSetHandler.handle(rs);
				} else {
					return null;
				}
			}
		};
	}

	public static ResultSetHandler2<InputStream> getImageResultSetHandler(){
		return new ResultSetHandler2<InputStream>() {

			@Override
			public InputStream handle(ResultSet rs) throws SQLException {
				if(rs.next()) {
					return rs.getBinaryStream(ModelColumnName.IMAGE_LINK);
				}else {
					return null;
				}
			}		
		};
	}

}
