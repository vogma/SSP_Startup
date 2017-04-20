package entitys;

import java.sql.Date;

public class Match
{
	private int matchID; //done
	private int saison; //done
	private Date datum; //done
	private int zuschauer; //done
	private Stadion stadion; //done
	private int stadionID;// done
	private String endergebnis; //done
	private String halbzeitergebnis; //done
	private int punkteHeim; //done
	private int punkteGast; //done

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

	public Stadion getStadion()
	{
		return stadion;
	}

	public void setStadion(Stadion stadion)
	{
		this.stadion = stadion;
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
		return getEndergebnis()+" " +getHalbzeitergebnis()+" " +getMatchID()+" "+getSaison();
	}

}
