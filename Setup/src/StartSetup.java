import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import entitys.Match;
import entitys.Player;
import entitys.Stadion;
import entitys.Team;
import manager.ConnectionManager;

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
			//List<Match> matches = xmlParser.parseMatches(matchStream);
			//List<Team> teams = xmlParser.parseTeams(teamStream);
			List<Player> player = xmlParser.parsePlayer(teamStream);
			//List<Stadion> stadions = xmlParser.parseStadions(stadionStream);
			
			//runDatabaseSetupScript();
			
			//PersistenceManager.persistStadions(stadions);
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}

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
