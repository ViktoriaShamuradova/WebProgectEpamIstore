package by.epamtc.shamuradova.ishop.dao.handler.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.epamtc.shamuradova.ishop.bean.AbstractEntity;
import by.epamtc.shamuradova.ishop.bean.Model;
import by.epamtc.shamuradova.ishop.bean.User;
import by.epamtc.shamuradova.ishop.constant.database_column_name.ModelColumnName;
import by.epamtc.shamuradova.ishop.constant.database_column_name.UserColumnName;
import by.epamtc.shamuradova.ishop.dao.handler.ResultSetHandler;

public class ResultSetHandlerUser implements ResultSetHandler {

	@Override
	public AbstractEntity handleSingle(ResultSet resultSet) throws SQLException {
		User user = null;

		if (resultSet.next()) {
			user = initialized(resultSet);
		}

		return user;
	}

	@Override
	public List<AbstractEntity> handleList(ResultSet resultSet) throws SQLException {
		List<AbstractEntity> list = new ArrayList<>();

		while (resultSet.next()) {
			list.add(initialized(resultSet));
		}

		return list;
	}

	private User initialized(ResultSet rs) throws SQLException {
		User user = new User();
		
		user.setRole(rs.getString(UserColumnName.ROLE));
		user.setStatus(rs.getString(UserColumnName.STATUS));
		user.setEmail(rs.getString(UserColumnName.EMAIL));
		user.setId(rs.getInt(UserColumnName.ID));
		user.setLogin(rs.getString(UserColumnName.LOGIN));
		user.setName(rs.getString(ModelColumnName.NAME));
		user.setPassword((rs.getString(UserColumnName.PASSWORD)).toCharArray());
		user.setSurname(rs.getString(UserColumnName.SURNAME));
		
		return user;
	}

}
