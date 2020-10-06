package by.epamtc.shamuradova.ishop.dao.handler.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.epamtc.shamuradova.ishop.bean.AbstractEntity;
import by.epamtc.shamuradova.ishop.bean.Model;
import by.epamtc.shamuradova.ishop.constant.database_column_name.ModelColumnName;
import by.epamtc.shamuradova.ishop.dao.handler.ResultSetHandler;

public class ResultSetHandlerModel implements ResultSetHandler {

	public AbstractEntity handleSingle(ResultSet rs) throws SQLException {

		Model model = null;
		if (rs.next()) {
			model = initialized(rs);
		}

		return model;
	}

	@Override
	public List<AbstractEntity> handleList(ResultSet rs) throws SQLException {
		List<AbstractEntity> list = new ArrayList<>();

		while (rs.next()) {
			list.add(initialized(rs));
		}
		
		return list;
	}

	private Model initialized(ResultSet rs) throws SQLException {
		Model model = new Model();
		model.setCategory(rs.getString(ModelColumnName.CATEGORY));
		model.setCount(rs.getInt(ModelColumnName.COUNT));
		model.setDescription(rs.getString(ModelColumnName.DESCRIPTION));
		model.setId(rs.getInt(ModelColumnName.ID));
		model.setImageLink(rs.getString(ModelColumnName.IMAGE_LINK));
		model.setName(rs.getString(ModelColumnName.NAME));
		model.setPrice(rs.getBigDecimal(ModelColumnName.PRICE));
		model.setProducer(rs.getString(ModelColumnName.PRODUCER));
		return model;
	}


}
