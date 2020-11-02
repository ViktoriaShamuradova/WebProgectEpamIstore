package by.epamtc.shamuradova.ishop.dao.handler.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import by.epamtc.shamuradova.ishop.bean.entity.Order;
import by.epamtc.shamuradova.ishop.constant.database_column_name.OrderColumnName;
import by.epamtc.shamuradova.ishop.dao.handler.ResultSetHandler2;

public class ResultSetHandlerOrder2 implements ResultSetHandler2<Order>{

	public ResultSetHandlerOrder2() {

	}

	@Override
	public Order handle(ResultSet resultSet) throws SQLException {
		Order order = new Order();
		order.setId(resultSet.getInt(OrderColumnName.ID));
		order.setCreated(resultSet.getDate(OrderColumnName.CREATED));
		order.setIdUser(resultSet.getInt(OrderColumnName.ID_USER));

		return order;

	}

}
