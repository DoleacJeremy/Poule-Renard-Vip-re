package core;

import java.awt.Color;
import java.util.ArrayList;

import mathUtil.Position;
import mathUtil.Vecteur;

public class Cell implements Comparable<Cell> {
	private Position position;
	private EnumCampCell camp;
	private Vecteur direction;
	private int size;
	private ICellBheavior algoIA;

	// constructor
	public Cell(int size, EnumCampCell camp) {
		this.camp = camp;
		this.size = size;

		this.position = new Position((float) (Math.random() * (size - 40) + 20), (float) (Math.random() * (size - 40)) + 20);
		this.direction = new Vecteur(new Position((float) (Math.random() * (size - 10) + 5), (float) (Math.random() * (size - 10) + 5)));
		this.direction.setDistance(1);

		this.algoIA = new ConcreteCellBheaviorNormalV1(70, 10, 15, this.size);
	}

	// getters/setters
	public Position getPosition() {
		return (this.position);
	}

	
	// camp related
	public EnumCampCell getCamp() {
		return (this.camp);
	}
	public EnumCampCell getCampProie() {
		return (EnumCampCell.getPreyCamp(this.camp));
	}
	public EnumCampCell getCampPredateur() {
		return (EnumCampCell.getPredatorCamp(this.camp));
	}
	public Color getColor() {
		return (EnumCampCell.getColor(this.camp));
	}

	// check if this cell is touching another cell
	public boolean isTouching(Cell other) {
		Position positionOther = other.getPosition();
		Position positionCurrent = this.position;
		int cellSize = 10;

		boolean x = positionOther.getX() - cellSize < positionCurrent.getX()
				&& positionOther.getX() + cellSize > positionCurrent.getX();

		boolean y = positionOther.getY() - cellSize < positionCurrent.getY()
				&& positionOther.getY() + cellSize > positionCurrent.getY();

		return (x && y);
	}
	// get the nearest wall facing the current cell
	private ArrayList<Position> getWalls(int nbWals) {
		ArrayList<Position> wallsList = new ArrayList<Position>();

		int xWallPos = this.size;
		int yWallPos = this.size;
		if (this.position.getX() < 500)
			xWallPos = 0;
		if (this.position.getY() < 500)
			yWallPos = 0;

		// create the position of each wall according to the size of the map and the position of the cell
		if (this.position.getX() > this.position.getY()) {
			for (int i = this.position.getX() - nbWals / 2; i < this.position.getX() + nbWals / 2; i++)
				wallsList.add(new Position(i, yWallPos));
		} else {
			for (int i = this.position.getY() - nbWals / 2; i < this.position.getY() + nbWals / 2; i++)
				wallsList.add(new Position(xWallPos, i));
		}

		return (wallsList);
	}
	
	
	
	
	// use the algorithem to get the direction in wich the cell will move 
	public void chooseDirection(ArrayList<Cell> listeProie, ArrayList<Cell> listeAlier,ArrayList<Cell> listePredateur) {
		// using an external object to switch/modify it easily 
		this.direction = this.algoIA.directionMovement(position, listePredateur, this.getWalls(10), listeProie, listeAlier);
	}
	// move the cell according to the previous calulations
	public void moveToDirection() {
		this.position.translate(this.direction);
	}
	
	
	
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((camp == null) ? 0 : camp.hashCode());
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cell other = (Cell) obj;
		if (camp != other.camp)
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		return true;
	}

	@Override
	public int compareTo(Cell o) {
		float d1 = Position.distance(this.position, o.getPosition());

		int resultat = (int) (d1 * 14000000);
		if (resultat == 0)
			resultat = 1;

		return (resultat);
	}
}