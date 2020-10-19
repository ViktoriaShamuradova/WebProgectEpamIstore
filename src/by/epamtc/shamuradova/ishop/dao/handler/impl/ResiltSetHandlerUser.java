package by.epamtc.shamuradova.ishop.dao.handler.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.epamtc.shamuradova.ishop.bean.AbstractEntity;
import by.epamtc.shamuradova.ishop.bean.User;
import by.epamtc.shamuradova.ishop.constant.database_column_name.UserColumnName;
import by.epamtc.shamuradova.ishop.dao.handler.ResultSetHandler;

public class ResiltSetHandlerUser implements ResultSetHandler{

	public ResiltSetHandlerUser() {
		
	}

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
		user.setName(rs.getString(UserColumnName.NAME));
		user.setSurname(rs.getString(UserColumnName.SURNAME));
		user.setLogin(rs.getString(UserColumnName.LOGIN));
		user.setEmail(rs.getString(UserColumnName.EMAIL));
		user.setStatus(rs.getString(UserColumnName.STATUS));
		user.setRole(rs.getString(UserColumnName.ROLE));
		user.setId(rs.getInt(UserColumnName.ID));
		return user;
	}

}
