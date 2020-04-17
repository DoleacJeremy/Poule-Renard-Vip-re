package core;

import java.util.Comparator;

public class SortByPosition implements Comparator<Cell>{

	@Override
	public int compare(Cell c1, Cell c2) {

		int c1Value = (c1.getPosition().getX()+1)*c1.getPosition().getY()+c1.getPosition().getX()+c1.getPosition().getY();
		int c2Value = (c2.getPosition().getX()+1)*c2.getPosition().getY()+c2.getPosition().getX()+c2.getPosition().getY();
		
		return(c1Value - c2Value);
	} 
}