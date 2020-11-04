package by.epamtc.shamuradova.ishop.dao.handler.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.epamtc.shamuradova.ishop.bean.entity.AbstractEntity;
import by.epamtc.shamuradova.ishop.bean.entity.Category;
import by.epamtc.shamuradova.ishop.constant.database_column_name.CategotyColumnName;
import by.epamtc.shamuradova.ishop.dao.handler.ResultSetHandler;

public class ResultSetHandlerCategory implements ResultSetHandler {


	@Override
	public AbstractEntity handleSingle(ResultSet resultSet) throws SQLException {
		Category category = null;
		if(resultSet.next()) {
			category = initialized(resultSet);
		}
		return category;
	}

	@Override
	public List<AbstractEntity> handleList(ResultSet resultSet) throws SQLException {
		List<AbstractEntity> list = new ArrayList<>();
		while (resultSet.next()) {
			list.add(initialized(resultSet));
		}
		
		return list;
	}
	
	
	private Category initialized(ResultSet rs) throws SQLException {
		Category category = new Category();
		category.setName(rs.getString(CategotyColumnName.NAME));
		category.setId(rs.getInt(CategotyColumnName.ID));
		category.setModelCount(rs.getInt(CategotyColumnName.COUNT));
		category.setUrl(rs.getString(CategotyColumnName.URL));
		
		return category;
	}
}
