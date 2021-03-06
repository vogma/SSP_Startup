package manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import entitys.Match;
import entitys.Player;
import entitys.Stadion;
import entitys.Team;

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

	public static void persistPlayer(List<Player> playerList) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		try (Connection connection = ConnectionManager.getConnection())
		{
			for (Player player : playerList)
			{
				PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO liga.player (p_name,p_verein_id) VALUES (?,?);");
				preparedStatement.setString(1, player.getName());
				preparedStatement.setInt(2, player.getTeamID());
				preparedStatement.execute();
			}
		}

	}

	public static void persistTeams(List<Team> teams) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		try (Connection connection = ConnectionManager.getConnection())
		{
			for (Team team : teams)
			{
				PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO liga.verein (verein_id,v_name,v_logoURL,stadion_stadion_id) VALUES (?,?,?,?);");
				preparedStatement.setInt(1, team.getTeamID());
				preparedStatement.setString(2, team.getTeamName());
				preparedStatement.setString(3, team.getTeamIconUrl());
				preparedStatement.setInt(4, team.getStadionID());
				preparedStatement.execute();
			}
		}

	}

	public static void persistMatches(List<Match> matches) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		try (Connection connection = ConnectionManager.getConnection())
		{
			for (Match match : matches)
			{
				PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO liga.match (match_id,m_saison,m_stadion,m_stadionID,m_endergebnis,m_halbzeitergebnis,m_punkteHeim,m_punkteGast,m_heimID,m_gastID) VALUES (?,?,?,?,?,?,?,?,?,?);");
				preparedStatement.setInt(1, match.getMatchID());
				preparedStatement.setInt(2, match.getSaison());
				preparedStatement.setString(3, match.getStadion());
				preparedStatement.setInt(4, match.getStadionID());
				preparedStatement.setString(5, match.getEndergebnis());
				preparedStatement.setString(6, match.getHalbzeitergebnis());
				preparedStatement.setInt(7, match.getPunkteHeim());
				preparedStatement.setInt(8, match.getPunkteGast());
				preparedStatement.setInt(9, match.getHeimID());
				preparedStatement.setInt(10, match.getGastID());
				preparedStatement.execute();
			}
		}
	}
}











