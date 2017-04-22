import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import entitys.Stadion;
import entitys.Team;
import manager.ConnectionManager;
import manager.PersistenceManager;

public class StartSetup
{

	private static InputStream matchStream;
	private static InputStream teamStream;
	private static InputStream stadionStream;

	public static void main(String[] args)
	{

		try
		{
			matchStream = StartSetup.class.getResourceAsStream("/matches.xml");
			teamStream = StartSetup.class.getResourceAsStream("/teams.xml");
			stadionStream = StartSetup.class.getResourceAsStream("/stadions.xml");

			XMLParser xmlParser = new XMLParser();
			// List<Match> matches = xmlParser.parseMatches(matchStream);
			List<Team> teams = xmlParser.parseTeams(teamStream);
			// List<Player> player = xmlParser.parsePlayer(teamStream);
			List<Stadion> stadion = xmlParser.parseStadions(stadionStream);

			teams = linkTeamsWithStadion(teams, stadion);
			
			runDatabaseSetupScript();

			PersistenceManager.persistStadions(stadion);
			PersistenceManager.persistTeams(teams);
			// PersistenceManager.persistPlayer(player);

		} catch (Exception ex)
		{
			ex.printStackTrace();
		}

	}

	private static List<Team> linkTeamsWithStadion(List<Team> teams, List<Stadion> stadion)
	{
		for (Team team : teams)
		{
			for (Stadion stadionItem : stadion)
			{
				if (team.getTeamName().equals(stadionItem.getTeamName()))
				{
					team.setStadionID(stadionItem.getStadionID());
				}
			}
		}
		return teams;
	}

	private static void runDatabaseSetupScript() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, FileNotFoundException
	{
		try (Connection connection = ConnectionManager.getConnection())
		{
			ScriptRunner scriptRunner = new ScriptRunner(connection);
			Reader reader = new BufferedReader(new InputStreamReader(StartSetup.class.getResourceAsStream("/init.sql")));
			scriptRunner.runScript(reader);
		}
	}

}
