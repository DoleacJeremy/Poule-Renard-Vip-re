package core;

import java.util.ArrayList;
import java.util.List;

public class SingletonStat {
	private static SingletonStat singletonStats;
	
	private ArrayList<Integer> nbHenList = new ArrayList<Integer>();
	private ArrayList<Integer>  nbViperList = new ArrayList<Integer>();
	private ArrayList<Integer>  nbFoxList = new ArrayList<Integer>();
	private ArrayList<Integer> nbMilisList = new ArrayList<Integer>();
	private ArrayList<Integer> turnList = new ArrayList<Integer>();
	
	private boolean addValues;
	
	private SingletonStat() {}
	
	public static SingletonStat getSingletonStat() {
		if(singletonStats == null) {
			singletonStats = new SingletonStat();
		}	
		return(singletonStats);
	}
	
// getters
	public int getLastHen() {
		return(this.nbHenList.get(this.nbHenList.size()-1));
	}
	public int getLastViper() {
		return(this.nbViperList.get(this.nbViperList.size()-1));
	}
	public int getLastFox() {
		return(this.nbFoxList.get(this.nbFoxList.size()-1));
	}
	public int getLastMilis() {
		return(this.nbMilisList.get(this.nbMilisList.size()-1));
	}
	public int getLastTours() {
		return(this.turnList.get(this.turnList.size()-1));
	}

	// getters
	public List<Integer> getHens() {
		return(this.nbHenList);
	}
	public List<Integer> getVipers() {
		return(this.nbViperList);
	}
	public List<Integer> getFoxs() {
		return(this.nbFoxList);
	}
	public List<Integer> getMilis() {
		return(this.nbMilisList);
	}
	public List<Integer> getTours() {
		return(this.turnList);
	}

	// setters
	public void setMilis(int secondes) {
		if(this.addValues)
			this.nbMilisList.add(secondes);
	}
	public void setTours(int tours) {
		if(this.addValues)
			this.turnList.add(tours);
	}

	//set an entier turn only if the values are different
	public void setCampValues(int Hen , int Viper , int Fox) {
		this.addValues = true;
		if(this.nbFoxList.size() > 0) {
			int lastIndex = this.nbFoxList.size()-1;
			boolean HenStagnation = this.nbHenList.get(lastIndex) == Hen;
			boolean ViperStagnation = this.nbViperList.get(lastIndex) == Viper;
			boolean FoxStagnation = this.nbFoxList.get(lastIndex) == Fox;

			if(HenStagnation && ViperStagnation && FoxStagnation)
				this.addValues = false;
		}
		
		if(this.addValues) {
			this.nbHenList.add(Hen);
			this.nbViperList.add(Viper);
			this.nbFoxList.add(Fox);
		}
	}

	// tell if this turn is the last one saved (don't save 2 times the same set of data in a row)
	public boolean isLastTurn(int turn) {
		return(this.turnList.get(this.turnList.size()-1) == turn);
	}
	public String getLog() {
		String retour = "[tour " + this.getLastTours()+"]   ";
		retour += "["+this.getLastMilis()+" ms]   ";
		retour += "Hen : " + this.getLastHen();
		retour += " Vipers : " + this.getLastViper();
		retour += " Fox : " + this.getLastFox();
		return(retour);
	}
}