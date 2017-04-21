package manager;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class ConnectionManager
{

	private static Connection connection;

	private ConnectionManager()
	{
	}

	public static Connection getConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		String connectionCommand = "jdbc:mysql://localhost/?user=root&password=root";
		connection = DriverManager.getConnection(connectionCommand);
		return connection;
	}
}
