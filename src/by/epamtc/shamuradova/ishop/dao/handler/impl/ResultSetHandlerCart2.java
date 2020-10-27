package by.epamtc.shamuradova.ishop.dao.handler.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import by.epamtc.shamuradova.ishop.bean.Cart;
import by.epamtc.shamuradova.ishop.constant.database_column_name.CartColumnName;
import by.epamtc.shamuradova.ishop.dao.handler.ResultSetHandler2;

public class ResultSetHandlerCart2 implements ResultSetHandler2<Cart>{

	public ResultSetHandlerCart2() {
	}

	@Override
	public Cart handle(ResultSet resultSet) throws SQLException {
		Cart cart = new Cart();
		cart.setUserId(resultSet.getInt(CartColumnName.ID_USER));
		cart.setCreated(resultSet.getDate(CartColumnName.CREATED));
		cart.setId(resultSet.getInt(CartColumnName.ID));
		
		return cart;
		
	}

}
