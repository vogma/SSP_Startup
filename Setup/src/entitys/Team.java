/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitys;

import java.util.List;

/**
 *
 * @author Marco
 */
public class Team {

    private String teamName, stadion, teamIconUrl, stadium;
    private int teamID;
    private List<Player> player;

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public Team() {
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getStadion() {
        return stadion;
    }

    public void setStadion(String stadion) {
        this.stadion = stadion;
    }

    public String getTeamIconUrl() {
        return teamIconUrl;
    }

    public void setTeamIconUrl(String teamIconUrl) {
        this.teamIconUrl = teamIconUrl;
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public List<Player> getPlayer(int index) {
        return player;
    }

    public List<Player> getAllPlayer() {
        return this.player;
    }

    public void setPlayer(List<Player> player) {
        this.player = player;
    }

    public void addPlayer(Player player) {
        this.player.add(player);
    }

    @Override
    public String toString() {
        return "Teamname: " + getTeamName() + " TeamID: " + getTeamID() + " IconUrl: "+getTeamIconUrl() +" Player: " + getAllPlayer();
    }

}
