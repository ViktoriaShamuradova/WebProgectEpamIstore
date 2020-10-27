package by.epamtc.shamuradova.ishop.dao.handler.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import by.epamtc.shamuradova.ishop.bean.Model;
import by.epamtc.shamuradova.ishop.constant.database_column_name.ModelColumnName;
import by.epamtc.shamuradova.ishop.dao.handler.ResultSetHandler2;

public class ResultSetHandlerModel2  implements ResultSetHandler2<Model> {

	public ResultSetHandlerModel2() {
	}
	
	@Override
	public Model handle(ResultSet rs) throws SQLException {
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
