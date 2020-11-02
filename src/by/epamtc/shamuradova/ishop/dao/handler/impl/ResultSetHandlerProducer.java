package by.epamtc.shamuradova.ishop.dao.handler.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.epamtc.shamuradova.ishop.bean.entity.AbstractEntity;
import by.epamtc.shamuradova.ishop.bean.entity.Producer;
import by.epamtc.shamuradova.ishop.constant.database_column_name.ProducerColumnName;
import by.epamtc.shamuradova.ishop.dao.handler.ResultSetHandler;

public class ResultSetHandlerProducer implements ResultSetHandler {

	@Override
	public AbstractEntity handleSingle(ResultSet resultSet) throws SQLException {
		Producer producer = null;
		if (resultSet.next()) {
			producer = initialized(resultSet);
		}
		return producer;
	}

	@Override
	public List<AbstractEntity> handleList(ResultSet resultSet) throws SQLException {
		List<AbstractEntity> list = new ArrayList<>();
		while (resultSet.next()) {
			list.add(initialized(resultSet));
		}
		return list;
	}

	private Producer initialized(ResultSet rs) throws SQLException {
		Producer producer = new Producer();
		producer.setName(rs.getString(ProducerColumnName.NAME));
		producer.setId(rs.getInt(ProducerColumnName.ID));
		producer.setModelCount(rs.getInt(ProducerColumnName.COUNT));

		return producer;
	}
}
