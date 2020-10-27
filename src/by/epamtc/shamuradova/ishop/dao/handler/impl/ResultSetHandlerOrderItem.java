package by.epamtc.shamuradova.ishop.dao.handler.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.epamtc.shamuradova.ishop.bean.AbstractEntity;
import by.epamtc.shamuradova.ishop.bean.Model;
import by.epamtc.shamuradova.ishop.bean.OrderItem;
import by.epamtc.shamuradova.ishop.constant.database_column_name.OrderItemColumnName;
import by.epamtc.shamuradova.ishop.dao.handler.ResultSetHandler;

public class ResultSetHandlerOrderItem implements ResultSetHandler {

	public ResultSetHandlerOrderItem() {
	}

	@Override
	public AbstractEntity handleSingle(ResultSet resultSet) throws SQLException {
		OrderItem orderItem = null;
		if (resultSet.next()) {
			orderItem = initialized(resultSet);
		}
		return orderItem;
	}

	@Override
	public List<AbstractEntity> handleList(ResultSet resultSet) throws SQLException {
		List<AbstractEntity> list = new ArrayList<>();

		while (resultSet.next()) {
			list.add(initialized(resultSet));
		}

		return list;
	}

	public OrderItem initialized(ResultSet resultSet) throws SQLException {
		ResultSetHandlerModel resultSetHandlerModel = new ResultSetHandlerModel();
		
		OrderItem orderItem = new OrderItem();
		orderItem.setIdOrder(resultSet.getInt(OrderItemColumnName.ID_ORDER));
		orderItem.setId(resultSet.getInt(OrderItemColumnName.ID));
		orderItem.setCount(resultSet.getInt(OrderItemColumnName.COUNT));
		
		Model model = resultSetHandlerModel.initialized(resultSet);
		orderItem.setModel(model);
		
		return orderItem;

	}

}
