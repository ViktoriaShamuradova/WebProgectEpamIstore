package by.epamtc.shamuradova.ishop.dao.handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.epamtc.shamuradova.ishop.bean.AbstractEntity;
import by.epamtc.shamuradova.ishop.bean.Model;

//определяет правила преобразования rs в соответствующий объект
public interface ResultSetHandler2<T> {

	public T handle(ResultSet rs) throws SQLException;

}
