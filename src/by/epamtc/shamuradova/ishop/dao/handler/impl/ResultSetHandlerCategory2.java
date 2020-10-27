package by.epamtc.shamuradova.ishop.dao.handler.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import by.epamtc.shamuradova.ishop.bean.Category;
import by.epamtc.shamuradova.ishop.constant.database_column_name.CategotyColumnName;
import by.epamtc.shamuradova.ishop.dao.handler.ResultSetHandler2;

public class ResultSetHandlerCategory2 implements ResultSetHandler2<Category>{

	public ResultSetHandlerCategory2() {}

	@Override
	public Category handle(ResultSet rs) throws SQLException {
		Category category = new Category();
		category.setName(rs.getString(CategotyColumnName.NAME));
		category.setId(rs.getInt(CategotyColumnName.ID));
		category.setModelCount(rs.getInt(CategotyColumnName.COUNT));
		category.setUrl(rs.getString(CategotyColumnName.URL));
		
		return category;
	}

}
