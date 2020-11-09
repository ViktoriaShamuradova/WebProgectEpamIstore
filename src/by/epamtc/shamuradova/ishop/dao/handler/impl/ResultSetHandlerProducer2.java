package by.epamtc.shamuradova.ishop.dao.handler.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import by.epamtc.shamuradova.ishop.bean.entity.Producer;
import by.epamtc.shamuradova.ishop.constant.database_column_name.ProducerColumnName;
import by.epamtc.shamuradova.ishop.dao.handler.ResultSetHandler2;

public class ResultSetHandlerProducer2 implements ResultSetHandler2<Producer> {

	public ResultSetHandlerProducer2() {
	}

	@Override
	public Producer handle(ResultSet rs) throws SQLException {
		Producer producer = new Producer();
		producer.setName(rs.getString(ProducerColumnName.NAME));
		producer.setId(rs.getInt(ProducerColumnName.ID));
		return producer;
	}

}
