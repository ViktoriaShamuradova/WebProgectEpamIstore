package by.epamtc.shamuradova.ishop.dao.handler;

import java.sql.ResultSet;
import java.sql.SQLException;

/**Параметризированный интерфейс ResultSetHandler2<T>, который определяет правила преобразования объекта типа ResultSet
 * в соответствующий тип
 * 
 * @param ResultSet - объект, содержащий в себе результат запроса из базы данных 
 *
 * The ResultSetHandler2 <T> interface, which defines the rules for transforming an object of type ResultSet
 * to the corresponding object
 * 
 * @param ResultSet - an object containing the query result from the database
 * 
 * @author Victoria Shamuradova 2020
 */


public interface ResultSetHandler<T> {

	public T handle(ResultSet rs) throws SQLException;

}
