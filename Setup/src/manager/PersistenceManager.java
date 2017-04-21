package manager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import entitys.Stadion;

public class PersistenceManager
{
	public static void persistStadions(List<Stadion> stadions) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		try (Connection connection = ConnectionManager.getConnection())
		{
			for (Stadion stadion : stadions)
			{
				PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO liga.stadion (s_name,s_gps,s_adress,s_bildURL,s_kapazitaet) VALUES (?,?,?,?,?);");
				preparedStatement.setString(1, stadion.getStadionName());
				preparedStatement.setString(2, stadion.getStadionGPS());
				preparedStatement.setString(3, stadion.getStadionAddress());
				preparedStatement.setString(4, stadion.getStadionImageURL());
				preparedStatement.setInt(5, stadion.getStadionCapacity());
				preparedStatement.execute();
			}
		}
	}
}
