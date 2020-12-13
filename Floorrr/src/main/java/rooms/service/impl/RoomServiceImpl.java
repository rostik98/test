package rooms.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import rooms.dao.RoomDao;
import rooms.dao.impl.RoomDaoImpl;
import rooms.domain.Coordinates;
import rooms.domain.Room;
import rooms.service.RoomService;

public class RoomServiceImpl implements RoomService {

	private RoomDao roomDao;

	public RoomServiceImpl()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, SQLException {
		roomDao = new RoomDaoImpl();
	}

	@Override
	public Room create(Room t, Coordinates c) throws SQLException {
		return roomDao.create(t, c);
	}

	@Override
	public Room read(int id) throws SQLException {
		return roomDao.read(id);
	}

	@Override
	public void delete(int id) throws SQLException {
		roomDao.delete(id);
	}

	@Override
	public List<Coordinates> readAll(String name) throws SQLException {
		return roomDao.readAll(name);
	}

}
