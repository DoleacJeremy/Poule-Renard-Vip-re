package core;

import mathUtil.Math_functions;

public class CellDistance implements Comparable<CellDistance>{
	private int positionTab;
	private int distance;
	
	//constructor
	public CellDistance(int tab , float distance) {
		this.positionTab = tab;
		this.distance = Math_functions.round(distance*14_000);
	}
	
	//getters/setters
	public int getPositionTab() {
		return(this.positionTab);
	}
	public int getDistance() {
		return(this.distance);
	}
	

	@Override
	public int compareTo(CellDistance o) {
		int resultat = (this.distance-o.getDistance());
		return(Math_functions.round(resultat));
	}
}