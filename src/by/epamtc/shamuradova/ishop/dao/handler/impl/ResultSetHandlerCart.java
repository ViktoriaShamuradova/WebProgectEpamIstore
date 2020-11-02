package by.epamtc.shamuradova.ishop.dao.handler.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.epamtc.shamuradova.ishop.bean.entity.AbstractEntity;
import by.epamtc.shamuradova.ishop.bean.entity.Cart;
import by.epamtc.shamuradova.ishop.constant.database_column_name.CartColumnName;
import by.epamtc.shamuradova.ishop.dao.handler.ResultSetHandler;

public class ResultSetHandlerCart implements ResultSetHandler{


	@Override
	public AbstractEntity handleSingle(ResultSet resultSet) throws SQLException {
		Cart cart = null;
		if(resultSet.next()) {
			cart = initialized(resultSet);
		}
		return cart;
	}

	@Override
	public List<AbstractEntity> handleList(ResultSet resultSet) throws SQLException {
		List<AbstractEntity> list = new ArrayList<>();

		while (resultSet.next()) {
			list.add(initialized(resultSet));
		}
		
		return list;
	}
	
	public Cart initialized(ResultSet resultSet) throws SQLException  {
		Cart cart = new Cart();
		cart.setUserId(resultSet.getInt(CartColumnName.ID_USER));
		cart.setCreated(resultSet.getDate(CartColumnName.CREATED));
		cart.setId(resultSet.getInt(CartColumnName.ID));
		
		return cart;
		
	}

}
