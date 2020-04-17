package core;

import java.util.List;

import mathUtil.Position;
import mathUtil.Vecteur;

public interface ICellBheavior {

	public Vecteur escapePredator(Position position , List<Cell> predatorList);
	public Vecteur escapeWall(Position position, List<Position> wallList);
	public Vecteur huntPrey(Position position , List<Cell> preyList);
	public Vecteur escapeAllies(Position position , List<Cell> allieList);
	
	public Vecteur directionMovement(Position position , List<Cell> predatorList , List<Position> wallList , List<Cell> preyList , List<Cell> allieList);
}