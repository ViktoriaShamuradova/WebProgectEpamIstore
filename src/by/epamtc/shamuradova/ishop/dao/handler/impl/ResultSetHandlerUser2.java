package by.epamtc.shamuradova.ishop.dao.handler.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import by.epamtc.shamuradova.ishop.bean.entity.User;
import by.epamtc.shamuradova.ishop.constant.database_column_name.ModelColumnName;
import by.epamtc.shamuradova.ishop.constant.database_column_name.UserColumnName;
import by.epamtc.shamuradova.ishop.dao.handler.ResultSetHandler2;

public class ResultSetHandlerUser2 implements ResultSetHandler2<User> {

	public ResultSetHandlerUser2() {
	}

	@Override
	public User handle(ResultSet rs) throws SQLException {
		User user = new User();

		user.setRole(rs.getString(UserColumnName.ROLE));
		user.setStatus(rs.getString(UserColumnName.STATUS));
		user.setEmail(rs.getString(UserColumnName.EMAIL));
		user.setId(rs.getInt(UserColumnName.ID));
		user.setLogin(rs.getString(UserColumnName.LOGIN));
		user.setName(rs.getString(UserColumnName.NAME));
		user.setSurname(rs.getString(UserColumnName.SURNAME));
		user.setBlackList(rs.getBoolean(UserColumnName.BLACK_LIST));

		return user;
	}

}
