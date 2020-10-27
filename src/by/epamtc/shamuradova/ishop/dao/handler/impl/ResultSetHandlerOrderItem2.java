package by.epamtc.shamuradova.ishop.dao.handler.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import by.epamtc.shamuradova.ishop.bean.Model;
import by.epamtc.shamuradova.ishop.bean.OrderItem;
import by.epamtc.shamuradova.ishop.constant.database_column_name.OrderItemColumnName;
import by.epamtc.shamuradova.ishop.dao.handler.ResultSetHandler2;

public class ResultSetHandlerOrderItem2 implements ResultSetHandler2<OrderItem> {

	public ResultSetHandlerOrderItem2() {

	}

	@Override
	public OrderItem handle(ResultSet resultSet) throws SQLException {
		ResultSetHandlerModel2 resultSetHandlerModel = new ResultSetHandlerModel2();
		
		OrderItem orderItem = new OrderItem();
		orderItem.setIdOrder(resultSet.getInt(OrderItemColumnName.ID_ORDER));
		orderItem.setId(resultSet.getInt(OrderItemColumnName.ID));
		orderItem.setCount(resultSet.getInt(OrderItemColumnName.COUNT));
		
		Model model = resultSetHandlerModel.handle(resultSet);
		orderItem.setModel(model);
		
		return orderItem;
	}

}
