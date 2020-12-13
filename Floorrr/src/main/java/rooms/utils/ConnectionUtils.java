package rooms.utils;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {
	private static String USER_NAME = "root";
	private static String USER_PASSWORD = "7870qQ11@";
	private static String URL = "jdbc:mysql://localhost/rooms?useUnicode=true&serverTimezone=UTC";

	public static Connection openConnection()
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
		return DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD);
	}
}
