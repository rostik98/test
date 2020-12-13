package rooms.shared;

import java.sql.SQLException;
import java.util.List;

import rooms.domain.Coordinates;

public interface AbstractCrud<T> {

	T create(T t, Coordinates c) throws SQLException;

	T read(int id) throws SQLException;

	void delete(int id) throws SQLException;

	List<Coordinates> readAll(String name) throws SQLException;

}
