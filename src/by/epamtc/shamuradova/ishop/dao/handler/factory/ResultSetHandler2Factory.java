package by.epamtc.shamuradova.ishop.dao.handler.factory;

import by.epamtc.shamuradova.ishop.dao.handler.ResultSetHandler2;
import by.epamtc.shamuradova.ishop.dao.handler.impl.GetterCountResultSetHandler;

public class ResultSetHandler2Factory {

	private static final ResultSetHandler2Factory INSTANCE = new ResultSetHandler2Factory();

	private ResultSetHandler2<Integer> getterCountResultSetHandler = null;

	private ResultSetHandler2Factory() {
	}

	public static ResultSetHandler2Factory getInstatnce() {
		return INSTANCE;
	}

	public ResultSetHandler2<Integer> getGetterCountResultSetHandler() {
		if (getterCountResultSetHandler == null) {

			synchronized (INSTANCE) {
				if (getterCountResultSetHandler == null) {
					getterCountResultSetHandler = new GetterCountResultSetHandler();
				}
			}
		}
		return getterCountResultSetHandler;
	}

}
