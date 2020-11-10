package by.epamtc.shamuradova.ishop.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.Test;

import by.epamtc.shamuradova.ishop.bean.entity.Cart;
import by.epamtc.shamuradova.ishop.dao.exception.ConnectionPoolException;
import by.epamtc.shamuradova.ishop.dao.exception.DAOException;
import by.epamtc.shamuradova.ishop.dao.handler.ResultSetHandler2;
import by.epamtc.shamuradova.ishop.dao.handler.impl.ResultSetHandlerFactory;
import by.epamtc.shamuradova.ishop.dao.impl.SQLCartDAOImpl;
import by.epamtc.shamuradova.ishop.dao.util.JDBCUtil;
import by.epamtc.shamuradova.ishop.dao.util.TestConnectionPool;

public class SQLCartDaoImplTest {

	private static SQLCartDAOImpl dao;
	private static TestConnectionPool pool;

	@BeforeClass
	public static void setUp() {
		pool = new TestConnectionPool();
		dao = new SQLCartDAOImpl(pool);
	}

	@Test
	public void deleteCartByIdUser() throws ConnectionPoolException, SQLException, DAOException {
		int idUser = 1;

		String sqlInsert = "INSERT INTO carts(id_user, created) VALUES (?,?)";
		String sqlSelect = "select * from carts where id_user = ?";

		Connection connection1 = pool.getConnection();
		JDBCUtil.insertDeleteUpdate(connection1, sqlInsert, idUser, new Date(System.currentTimeMillis()));
		pool.free(connection1);

		dao.deleteCartByidUser(idUser);

		Connection connection2 = pool.getConnection();
		ResultSetHandler2<Cart> handler = ResultSetHandlerFactory
				.getSingleResultSetHandler(ResultSetHandlerFactory.CART_RESULT_SET_HANDLER);
		Cart cart = JDBCUtil.select(connection2, sqlSelect, handler, idUser);
		pool.free(connection2);

		assertNull(cart);

	}

	@Test
	public void getCartByUserId() throws ConnectionPoolException, SQLException, DAOException {
		int idUser = 2;

		String sqlInsert = "INSERT INTO carts(id_user, created) VALUES (?,?)";
		Connection connection1 = pool.getConnection();
		JDBCUtil.insertDeleteUpdate(connection1, sqlInsert, idUser, new Date(System.currentTimeMillis()));
		pool.free(connection1);

		String sqlSelect = "select * from carts where id_user = ?";
		Connection connection2 = pool.getConnection();
		Cart expected = JDBCUtil.select(connection2, sqlSelect,
				ResultSetHandlerFactory.getSingleResultSetHandler(ResultSetHandlerFactory.CART_RESULT_SET_HANDLER),
				idUser);
		pool.free(connection2);

		Cart actual = dao.getCartByUserId(idUser);

		assertEquals(expected, actual);

	}
}
