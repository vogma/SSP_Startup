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

public class StartSetup
{

	private static DocumentBuilderFactory documentBuilderFactory;
	private static DocumentBuilder documentBuilder;
	private static Document document;

	public static void main(String[] args)
	{
		try
		{
			System.out.println(StartSetup.class.getResource("init.sql").getPath());
			createXMLParser();
			List<Match> matches = parseMatches(StartSetup.class.getResourceAsStream("/matches.xml"));
			List<Team> teams = parseTeams(StartSetup.class.getResourceAsStream("/teams.xml"));
			List<Player> player = parsePlayer();
			List<Stadion> stadions = parseStadions(StartSetup.class.getResourceAsStream("/stadions.xml"));
			runDatabaseSetupScript();
			PersistenceManager.persistStadions(stadions);
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

	private static List<Stadion> parseStadions(InputStream inputStream) throws SAXException, IOException
	{
		List<Stadion> stadions = new ArrayList<>();

		document = documentBuilder.parse(inputStream);
		NodeList nodeList = document.getElementsByTagName("Stadion");
		for (int index = 0; index < nodeList.getLength(); index++)
		{
			Stadion stadion = new Stadion();
			Element element = (Element) nodeList.item(index);
			stadion.setStadionAddress(element.getElementsByTagName("stadionAddress").item(0).getTextContent());
			stadion.setStadionCapacity(Integer.parseInt(element.getElementsByTagName("stadionCapacity").item(0).getTextContent()));
			stadion.setStadionGPS(element.getElementsByTagName("stadionGPS").item(0).getTextContent());
			stadion.setStadionImageURL(element.getElementsByTagName("stadionImageURL").item(0).getTextContent());
			stadion.setStadionName(element.getElementsByTagName("stadionName").item(0).getTextContent());
			stadion.setTeamID(Integer.parseInt(element.getElementsByTagName("teamID").item(0).getTextContent()));
			stadion.setTeamName(element.getElementsByTagName("teamName").item(0).getTextContent());
			stadions.add(stadion);
		}
		return stadions;
	}

	private static void createXMLParser() throws ParserConfigurationException
	{
		documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilder = documentBuilderFactory.newDocumentBuilder();
	}

	private static List<Player> parsePlayer()
	{
		// TODO Auto-generated method stub
		return null;
	}

	private static List<Team> parseTeams(InputStream inputStream)
	{
		return null;
	}

	private static List<Match> parseMatches(InputStream inputStream) throws SAXException, IOException
	{
		document = documentBuilder.parse(inputStream);

		NodeList nodes = document.getElementsByTagName("Matchdata");
		for (int index = 0; index < nodes.getLength(); index++)
		{

			Match match = new Match();
			Element element = (Element) nodes.item(index);

			int matchID = Integer.parseInt(element.getElementsByTagName("matchID").item(0).getTextContent());
			match.setMatchID(matchID);

			int saison = Integer.parseInt(element.getElementsByTagName("leagueSaison").item(0).getTextContent());
			match.setSaison(saison);

			NodeList matchResults = element.getElementsByTagName("matchResult");

			for (int i = 0; i < matchResults.getLength(); i++)
			{
				Element el = (Element) matchResults.item(i);
				if (el.getElementsByTagName("resultName").item(0).getTextContent().equals("Endergebnis"))
				{
					String pointsTeam1 = el.getElementsByTagName("pointsTeam1").item(0).getTextContent();
					String pointsTeam2 = el.getElementsByTagName("pointsTeam2").item(0).getTextContent();
					String endergebnis = pointsTeam1 + ":" + pointsTeam2;
					match.setEndergebnis(endergebnis);
				}
				if (el.getElementsByTagName("resultName").item(0).getTextContent().equals("Halbzeit"))
				{
					String pointsTeam1 = el.getElementsByTagName("pointsTeam1").item(0).getTextContent();
					String pointsTeam2 = el.getElementsByTagName("pointsTeam2").item(0).getTextContent();
					String halbzeitergebnis = pointsTeam1 + ":" + pointsTeam2;
					match.setHalbzeitergebnis(halbzeitergebnis);
				}
			}

			NodeList goalsList = element.getElementsByTagName("Goal");
			for (int i = 0; i < goalsList.getLength(); i++)
			{
				Element goalElement = (Element) goalsList.item(i);

			}
		}
		return new ArrayList<>();
	}
}
