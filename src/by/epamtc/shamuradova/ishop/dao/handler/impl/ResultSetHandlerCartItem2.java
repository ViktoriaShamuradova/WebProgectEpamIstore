package by.epamtc.shamuradova.ishop.dao.handler.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import by.epamtc.shamuradova.ishop.bean.entity.CartItem;
import by.epamtc.shamuradova.ishop.constant.database_column_name.CartItemColumnName;
import by.epamtc.shamuradova.ishop.dao.handler.ResultSetHandler2;

public class ResultSetHandlerCartItem2 implements ResultSetHandler2<CartItem> {

	public ResultSetHandlerCartItem2() {

	}

	@Override
	public CartItem handle(ResultSet rs) throws SQLException {

		CartItem cartItem = new CartItem();

		cartItem.setId(rs.getInt(CartItemColumnName.ID));
		cartItem.setModelId(rs.getInt(CartItemColumnName.MODEL_ID));
		cartItem.setCartId(rs.getInt(CartItemColumnName.CART_ID));
		cartItem.setCount(rs.getInt(CartItemColumnName.COUNT));

		return cartItem;
	}

}
