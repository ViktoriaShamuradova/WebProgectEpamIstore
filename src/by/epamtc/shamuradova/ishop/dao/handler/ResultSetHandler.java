package by.epamtc.shamuradova.ishop.dao.handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import by.epamtc.shamuradova.ishop.bean.entity.AbstractEntity;
import by.epamtc.shamuradova.ishop.dao.exception.DAOException;

public interface ResultSetHandler {

	public AbstractEntity handleSingle(ResultSet resultSet)throws SQLException ;

	public List<AbstractEntity> handleList(ResultSet resultSet)throws SQLException ;

}
