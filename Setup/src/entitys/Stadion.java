package entitys;

/**
 *
 * @author Marco Vogel
 */
public class Stadion {

    private int teamID;
    private String teamName;
    private String stadionName;
    private String stadionGPS;
    private String stadionAddress;
    private String stadionImageURL;
    private int stadionCapacity;

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getStadionName() {
        return stadionName;
    }

    public void setStadionName(String stadionName) {
        this.stadionName = stadionName;
    }

    public String getStadionGPS() {
        return stadionGPS;
    }

    public void setStadionGPS(String stadionGPS) {
        this.stadionGPS = stadionGPS;
    }

    public String getStadionAddress() {
        return stadionAddress;
    }

    public void setStadionAddress(String stadionAddress) {
        this.stadionAddress = stadionAddress;
    }

    public String getStadionImageURL() {
        return stadionImageURL;
    }

    public void setStadionImageURL(String stadionImageURL) {
        this.stadionImageURL = stadionImageURL;
    }

    public int getStadionCapacity() {
        return stadionCapacity;
    }

    public void setStadionCapacity(int stadionCapacity) {
        this.stadionCapacity = stadionCapacity;
    }

    @Override
    public String toString() {
        return getTeamName()+" : "+getStadionName()+" "+getStadionAddress() +" "+getStadionImageURL() +" "+getStadionGPS()+" "+getTeamID()+" "+getStadionCapacity();
    }
    
    
    
    

}
