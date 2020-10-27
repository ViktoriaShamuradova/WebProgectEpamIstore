package by.epamtc.shamuradova.ishop.dao.handler.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import by.epamtc.shamuradova.ishop.dao.handler.ResultSetHandler2;

public class GetterCountResultSetHandler implements ResultSetHandler2<Integer> {

	public GetterCountResultSetHandler() {
	}

	@Override
	public Integer handle(ResultSet rs) throws SQLException {
		if (rs.next()) {
			return rs.getInt(1);
		} else {
			return 0;
		}
	}

}
