package main;
import java.io.IOException;
import java.io.InputStream;
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

public class XMLParser
{
	private static DocumentBuilderFactory documentBuilderFactory;
	private static DocumentBuilder documentBuilder;
	private static Document document;

	public XMLParser() throws ParserConfigurationException
	{
		documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilder = documentBuilderFactory.newDocumentBuilder();
	}

	public List<Stadion> parseStadions(InputStream inputStream) throws SAXException, IOException
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
			stadion.setStadionID(Integer.parseInt(element.getElementsByTagName("stadionID").item(0).getTextContent()));
			stadions.add(stadion);
		}
		return stadions;
	}

	public List<Match> parseMatches(InputStream matchStream) throws SAXException, IOException
	{
		int c = 0;
		document = documentBuilder.parse(matchStream);

		List<Match> matches = new ArrayList<>();

		NodeList nodes = document.getElementsByTagName("Matchdata");
		for (int index = 0; index < nodes.getLength(); index++)
		{

			Match match = new Match();
			Element element = (Element) nodes.item(index);

			int matchID = Integer.parseInt(element.getElementsByTagName("matchID").item(0).getTextContent());
			match.setMatchID(matchID);

			int saison = Integer.parseInt(element.getElementsByTagName("leagueSaison").item(0).getTextContent());
			match.setSaison(saison);

			try
			{
				match.setZuschauer(Integer.parseInt(element.getElementsByTagName("NumberOfViewers").item(0).getTextContent()));
			} catch (Exception ex)
			{
				// Log error
			}

			match.setPunkteHeim(Integer.parseInt(element.getElementsByTagName("pointsTeam1").item(0).getTextContent()));
			match.setPunkteGast(Integer.parseInt(element.getElementsByTagName("pointsTeam2").item(0).getTextContent()));
			match.setTeamName1(element.getElementsByTagName("nameTeam1").item(0).getTextContent());
			match.setTeamName2(element.getElementsByTagName("nameTeam2").item(0).getTextContent());
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
			matches.add(match);
			c++;
			NodeList goalsList = element.getElementsByTagName("Goal");
			for (int i = 0; i < goalsList.getLength(); i++)
			{
				Element goalElement = (Element) goalsList.item(i);

			}
		}
		System.out.println(c);
		return matches;
	}

	public List<Player> parsePlayer(InputStream teamStream) throws SAXException, IOException
	{
		int teamID;

		List<Player> playerList = new ArrayList<>();
		document = documentBuilder.parse(teamStream);
		NodeList nodeList = document.getElementsByTagName("Team");
		for (int index = 0; index < nodeList.getLength(); index++)
		{
			Element element = (Element) nodeList.item(index);

			teamID = Integer.parseInt(element.getElementsByTagName("teamID").item(0).getTextContent());

			NodeList nl = element.getElementsByTagName("player");
			for (int i = 0; i < nl.getLength(); i++)
			{
				Player player = new Player();
				player.setName(nl.item(i).getTextContent());
				player.setTeamID(teamID);
				playerList.add(player);
			}

		}
	
		return playerList;
	}

	public List<Team> parseTeams(InputStream teamStream) throws SAXException, IOException
	{

		List<Team> teams = new ArrayList<>();
		document = documentBuilder.parse(teamStream);
		NodeList nodeList = document.getElementsByTagName("Team");
		for (int index = 0; index < nodeList.getLength(); index++)
		{
			Team team = new Team();

			Element element = (Element) nodeList.item(index);
			team.setTeamName(element.getElementsByTagName("teamName").item(0).getTextContent());
			team.setTeamID(Integer.parseInt(element.getElementsByTagName("teamID").item(0).getTextContent()));
			team.setTeamIconUrl(element.getElementsByTagName("teamIconURL").item(0).getTextContent());

			teams.add(team);
		}
		return teams;
	}
}
