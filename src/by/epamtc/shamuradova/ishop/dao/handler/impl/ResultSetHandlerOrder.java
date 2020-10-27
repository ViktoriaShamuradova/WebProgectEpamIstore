package by.epamtc.shamuradova.ishop.dao.handler.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.epamtc.shamuradova.ishop.bean.AbstractEntity;
import by.epamtc.shamuradova.ishop.bean.Order;
import by.epamtc.shamuradova.ishop.constant.database_column_name.OrderColumnName;
import by.epamtc.shamuradova.ishop.dao.handler.ResultSetHandler;

public class ResultSetHandlerOrder implements ResultSetHandler {

	public ResultSetHandlerOrder() {}

	@Override
	public AbstractEntity handleSingle(ResultSet resultSet) throws SQLException {
		Order order = null;
		if (resultSet.next()) {
			order = initialized(resultSet);
		}
		return order;
	}

	@Override
	public List<AbstractEntity> handleList(ResultSet resultSet) throws SQLException {
		List<AbstractEntity> list = new ArrayList<>();

		while (resultSet.next()) {
			list.add(initialized(resultSet));
		}

		return list;
	}

	public Order initialized(ResultSet resultSet) throws SQLException {
		Order order = new Order();
		order.setId(resultSet.getInt(OrderColumnName.ID));
		order.setCreated(resultSet.getDate(OrderColumnName.CREATED));
		order.setIdUser(resultSet.getInt(OrderColumnName.ID_USER));

		return order;

	}

}
