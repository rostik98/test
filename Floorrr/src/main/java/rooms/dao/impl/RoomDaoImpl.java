package rooms.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import rooms.dao.RoomDao;
import rooms.domain.Coordinates;
import rooms.domain.Room;
import rooms.utils.ConnectionUtils;

public class RoomDaoImpl implements RoomDao {

	private static String READ_ALL = "select x, y from room t inner join coordinates tt on tt.room_id = t.id where t.Name = ?";
	private static String CREATE = "insert into room(`name`) value (?)";
	private static String CREATEC = "insert into coordinates(`x`,`y`,`room_id`) value (?,?,?)";
	private static String READ_BY_ID = "select * from room where id = ?";
	private static String READ_BY_NAME = "select * from room where name = ?";
	private static String DELETE_BY_ID = "delete from room where id = ?";

	private Connection conn;
	private PreparedStatement preparedStatement, preparedStatementc, preparedStatementa;

	/**
	 * @param conn
	 * @throws SQLException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public RoomDaoImpl()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, SQLException {
		conn = ConnectionUtils.openConnection();
	}

	@Override
	public Room create(Room r, Coordinates c) throws SQLException {
		preparedStatement = conn.prepareStatement(CREATE);
		preparedStatementc = conn.prepareStatement(READ_BY_NAME);
		preparedStatementc.setString(1, r.getName());
		ResultSet executeQuery = preparedStatementc.executeQuery();

		preparedStatement.setString(1, r.getName());
		if (!executeQuery.next()) {
			preparedStatement.executeUpdate();
		}
		insertCoords(r, c);
		return r;

	}

	private void insertCoords(Room r, Coordinates c) throws SQLException {
		preparedStatement = conn.prepareStatement(CREATEC);
		preparedStatementc = conn.prepareStatement(READ_BY_NAME);
		preparedStatementa = conn.prepareStatement(READ_ALL);
		preparedStatementc.setString(1, r.getName());
		ResultSet executeQuery = preparedStatementc.executeQuery();
		executeQuery.next();
		int int1 = executeQuery.getInt("id");
		preparedStatement.setInt(1, c.getX());
		preparedStatement.setInt(2, c.getY());
		preparedStatement.setInt(3, int1);
		preparedStatementa.setString(1, r.getName());

		preparedStatement.executeUpdate();

	}

	@Override
	public Room read(int id) throws SQLException {
		preparedStatement = conn.prepareStatement(READ_BY_ID);
		preparedStatement.setInt(1, id);
		ResultSet result = preparedStatement.executeQuery();
		result.next();
		int id1 = result.getInt("id");
		String name = result.getString("name");
		return new Room(id1, name);
	}

	@Override
	public void delete(int id) throws SQLException {
		preparedStatement = conn.prepareStatement(DELETE_BY_ID);
		preparedStatement.setInt(1, id);
		preparedStatement.executeUpdate();
	}

	@Override
	public List<Coordinates> readAll(String name) throws SQLException {
		List<Coordinates> list = new LinkedList<>();
		preparedStatement = conn.prepareStatement(READ_ALL);
		preparedStatement.setString(1, name);
		ResultSet result = preparedStatement.executeQuery();
		while (result.next()) {
			list.add(new Coordinates(result.getInt("x"), result.getInt("y")));
		}
		return list;
	}

}
