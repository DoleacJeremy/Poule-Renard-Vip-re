package core;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.LinkedList;

public class CellGroup {
	private ArrayList<Cell> cellList;
	private int size;
	private SingletonStat stats = SingletonStat.getSingletonStat();
	
	
	// constructor
	public CellGroup(int size, int nbCell) {
		this.size = size;
		this.cellList = new ArrayList<Cell>();

		System.out.println("[CellGroup>18]   Stating cell generation");
		this.addCell(nbCell, EnumCampCell.viper);
		this.addCell(nbCell, EnumCampCell.hen);
		this.addCell(nbCell, EnumCampCell.fox);
		
		SortByPosition sortingObject = new SortByPosition();
		this.cellList.sort(sortingObject);
		System.out.println("[CellGroup>18]   Cell generation done");
	}

	
	// make all cells move and check is they are colliding another cell
	public boolean computeOneTurn() {
		this.colisions();
		boolean turnStatus = this.move();
		return(turnStatus);
	}
	// make all cells move
	public boolean move() {
		ArrayList<Cell> viperCamp = this.getAllCellOfCamp(EnumCampCell.viper);
		ArrayList<Cell> henCamp = this.getAllCellOfCamp(EnumCampCell.hen);
		ArrayList<Cell> foxCamp = this.getAllCellOfCamp(EnumCampCell.fox);
		this.stats.setCampValues(henCamp.size(), viperCamp.size(), foxCamp.size());
		
		// calculate how to move for all cells
		for(Cell cell : this.cellList) {
			if(cell.getCamp() == EnumCampCell.viper)
				cell.chooseDirection(foxCamp, viperCamp , henCamp);
			if(cell.getCamp() == EnumCampCell.hen)
				cell.chooseDirection(viperCamp, henCamp , foxCamp);
			if(cell.getCamp() == EnumCampCell.fox)
				cell.chooseDirection(henCamp, foxCamp ,viperCamp);
		}

		// make all cells move
		for(Cell cell : this.cellList) {
			cell.moveToDirection();
		}
		
		// if only one camp is left then the game is finished 
		// (technically it is over when the fist camp is obliterrated but it keeps playing for the sake of the spector)
		boolean endCondition = (viperCamp.isEmpty() && henCamp.isEmpty())||
							   (viperCamp.isEmpty() && foxCamp.isEmpty())||
							   (foxCamp.isEmpty() && henCamp.isEmpty());
		return(endCondition);
	}
	//check if a cell is coliding another one and remove the prey when in contact with the predator
	public void colisions() {
		LinkedList<Integer> cellToRemoveList = new LinkedList<Integer>();
		
		// iteration of all cells
		for (int i = 0; i < this.cellList.size() ; i++) {
			Cell baseCell = this.cellList.get(i);
			// for each cell it seach for another cell that touch it (other than itself)
			for (int j = 0; j < this.cellList.size(); j++) {
				if (j != i) {
					Cell otherCell = this.cellList.get(j);
					// if base cell and other are touching, one of them is going to die
					if (baseCell.isTouching(otherCell) && baseCell.getCamp() != otherCell.getCamp()) {
						if (baseCell.getCampPredateur() == otherCell.getCamp()) {
							// base cell is being eaten and end of the loop
							if(!cellToRemoveList.contains(i))
								cellToRemoveList.add(i);
							break;
						}
						if (baseCell.getCampProie() == otherCell.getCamp()) {
							// orher cell is being eaten
							if(!cellToRemoveList.contains(j))
								cellToRemoveList.add(j);
						}
					}
				}
			}

			// separated removal of the cells because of ConcurentExeption on the list
			int correctiveIndice = 0;
			Collections.sort(cellToRemoveList);
			for(int indiceCell : cellToRemoveList) {
				this.cellList.remove(indiceCell-correctiveIndice);
				correctiveIndice++;
			}
			cellToRemoveList = new LinkedList<Integer>();
			
		}
	}

	//getter and setters
	public ArrayList<Cell> getCells() {
		return (this.cellList);
	}
	public ArrayList<Cell> getAllCellOfCamp(EnumCampCell cell) {
		ArrayList<Cell> cellList = new ArrayList<Cell>();
		for(Cell cellI : this.cellList) {
			if(cellI.getCamp() == cell)
				cellList.add(cellI);
		}
		return (cellList);
	}
	public void addCell(int number, EnumCampCell camp) {
		while (number > 0) {
			this.cellList.add(new Cell(this.size, camp));
			number--;
		}
	}
	
	// draw all cells on a JPanel using the Graphic componant
	public void drawCellGroup(Graphics g) {
		ArrayList<Cell> cellList = this.getCells();
		this.dessiner(g, cellList);
	}
	// I am using a diferent method because the fact that it is coputed in a thread is causing ConcurrentModificationException
	private void dessiner(Graphics g , ArrayList<Cell> carrers) {
		try {
			for(Cell c : carrers) {
					g.setColor(c.getColor());
					g.drawRect(c.getPosition().getX()-5, c.getPosition().getY()-5, 10, 10);
			}
		}catch(ConcurrentModificationException e) {
			try{Thread.sleep(100);}catch(Exception eSleep){}
			this.dessiner(g, carrers);
		}
	}

}