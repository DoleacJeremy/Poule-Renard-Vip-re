package core;

import java.awt.Color;

public enum EnumCampCell {
	viper,
	hen,
	fox;
	

	public static EnumCampCell getPreyCamp(EnumCampCell camp) {
		EnumCampCell campProie = null;
		switch(camp) {
			case viper : 
				campProie = EnumCampCell.fox;
				break;
			case fox : 
				campProie = EnumCampCell.hen;
				break;
			case hen : 
				campProie = EnumCampCell.viper;
			break;
		}
		return(campProie);
	}
	public static EnumCampCell getPredatorCamp(EnumCampCell camp) {
		EnumCampCell campProie = null;
		switch(camp) {
			case viper : 
				campProie = EnumCampCell.hen;
				break;
			case hen : 
				campProie = EnumCampCell.fox;
				break;
			case fox : 
				campProie = EnumCampCell.viper;
				break;
		}
		return(campProie);
	}
	
	public static Color getColor(EnumCampCell camp) {
		switch(camp) {
			case viper : 
				return(Color.GREEN);
			case hen : 
				return(Color.BLACK);
			case fox : 
				return(Color.ORANGE);
		}
		return(Color.WHITE);
	}
}