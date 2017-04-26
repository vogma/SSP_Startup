package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import entitys.Match;
import entitys.Player;
import entitys.Stadion;
import entitys.Team;
import manager.ConnectionManager;
import manager.PersistenceManager;

public class StartSetup {

	private static InputStream matchStream;
	private static InputStream teamStream;
	private static InputStream playerStream;
	private static InputStream stadionStream;

	private static List<Match> matches;
	private static List<Team> teams;
	private static List<Player> player;
	private static List<Stadion> stadion;

	public static void main(String[] args) {
		try {
			initStreams();
			parseXML();
			runDatabaseSetupScript();
			persistData();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private static void initStreams() {
		matchStream = StartSetup.class.getResourceAsStream("/matches.xml");
		teamStream = StartSetup.class.getResourceAsStream("/teams.xml");
		playerStream = StartSetup.class.getResourceAsStream("/teams.xml");
		stadionStream = StartSetup.class.getResourceAsStream("/stadions.xml");
	}

	private static void persistData()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		PersistenceManager.persistStadions(stadion);
		PersistenceManager.persistTeams(teams);
		PersistenceManager.persistPlayer(player);
		PersistenceManager.persistMatches(matches);
	}

	private static void parseXML() throws SAXException, IOException, ParserConfigurationException {
		XMLParser xmlParser = new XMLParser();
		matches = xmlParser.parseMatches(matchStream);
		teams = xmlParser.parseTeams(teamStream);
		player = xmlParser.parsePlayer(playerStream);
		stadion = xmlParser.parseStadions(stadionStream);

		teams = linkTeamsWithStadion(teams, stadion);
		matches = linkMatchesWithTables(matches, teams, stadion);

	}

	private static List<Match> linkMatchesWithTables(List<Match> matches, List<Team> teams, List<Stadion> stadionList) {

		for (Match match : matches) {
			for (Stadion stadion : stadionList) {
				if (match.getTeamName1().equals(stadion.getTeamName())) {
					if ("".equals(match.getStadion()) || match.getStadionID() == 0) {
						match.setStadion(stadion.getStadionName());
						match.setStadionID(stadion.getStadionID());
					}
					break;
				}
			}
			for (Team team : teams) {
				if (match.getTeamName1().equals(team.getTeamName())) {
					match.setHeimID(team.getTeamID());
				} else if (match.getTeamName2().equals(team.getTeamName())) {
					match.setGastID(team.getTeamID());
				}
			}
		}

		return matches;
	}

	private static List<Team> linkTeamsWithStadion(List<Team> teams, List<Stadion> stadion) {
		for (Team team : teams) {
			for (Stadion stadionItem : stadion) {
				if (team.getTeamName().equals(stadionItem.getTeamName())) {
					team.setStadionID(stadionItem.getStadionID());
				}
			}
		}
		return teams;
	}

	private static void runDatabaseSetupScript() throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, SQLException, FileNotFoundException {
		try (Connection connection = ConnectionManager.getConnection()) {
			ScriptRunner scriptRunner = new ScriptRunner(connection);
			Reader reader = new BufferedReader(
					new InputStreamReader(StartSetup.class.getResourceAsStream("/init.sql")));
			scriptRunner.runScript(reader);
		}
	}

}
