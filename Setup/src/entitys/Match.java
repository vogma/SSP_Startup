package entitys;

import java.sql.Date;

public class Match
{
	private int matchID; 
	private int saison; 
	private Date datum; 
	private int zuschauer; 
	private String stadion; 
	private int stadionID;
	private String endergebnis; 
	private String halbzeitergebnis; 
	private int punkteHeim; 
	private int punkteGast; 
	private String teamName1;
	private String teamName2;
	private int heimID;
	private int gastID;

	public int getHeimID()
	{
		return heimID;
	}

	public void setHeimID(int heimID)
	{
		this.heimID = heimID;
	}

	public int getGastID()
	{
		return gastID;
	}

	public void setGastID(int gastID)
	{
		this.gastID = gastID;
	}

	public String getTeamName1()
	{
		return teamName1;
	}

	public void setTeamName1(String teamName1)
	{
		this.teamName1 = teamName1;
	}

	public String getTeamName2()
	{
		return teamName2;
	}

	public void setTeamName2(String teamName2)
	{
		this.teamName2 = teamName2;
	}

	public int getMatchID()
	{
		return matchID;
	}

	public void setMatchID(int matchID)
	{
		this.matchID = matchID;
	}

	public int getSaison()
	{
		return saison;
	}

	public void setSaison(int saison)
	{
		this.saison = saison;
	}

	public Date getDatum()
	{
		return datum;
	}

	public void setDatum(Date datum)
	{
		this.datum = datum;
	}

	public int getZuschauer()
	{
		return zuschauer;
	}

	public void setZuschauer(int zuschauer)
	{
		this.zuschauer = zuschauer;
	}

	public String getStadion()
	{
		return stadion;
	}

	public void setStadion(String stadionName)
	{
		this.stadion = stadionName;
	}

	public int getStadionID()
	{
		return stadionID;
	}

	public void setStadionID(int stadionID)
	{
		this.stadionID = stadionID;
	}

	public String getEndergebnis()
	{
		return endergebnis;
	}

	public void setEndergebnis(String endergebnis)
	{
		this.endergebnis = endergebnis;
	}

	public String getHalbzeitergebnis()
	{
		return halbzeitergebnis;
	}

	public void setHalbzeitergebnis(String halbzeitergebnis)
	{
		this.halbzeitergebnis = halbzeitergebnis;
	}

	public int getPunkteHeim()
	{
		return punkteHeim;
	}

	public void setPunkteHeim(int punkteHeim)
	{
		this.punkteHeim = punkteHeim;
	}

	public int getPunkteGast()
	{
		return punkteGast;
	}

	public void setPunkteGast(int punkteGast)
	{
		this.punkteGast = punkteGast;
	}

	@Override
	public String toString()
	{
		return getEndergebnis() + "\n" + getHalbzeitergebnis() + "\n " + getMatchID() + " \n" + getSaison() + "\n" + getStadionID() + "\n" + getStadion() + "\n" + getZuschauer() + "\n_________________________";
	}

}
