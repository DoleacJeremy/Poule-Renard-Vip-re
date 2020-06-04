package com.JD.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.JD.mathUtil.Math_functions;
import com.JD.mathUtil.Position;
import com.JD.mathUtil.Vecteur;

public class ConcreteCellBheaviorNormalV1 implements ICellBheavior {
	private int maxFieldOfViewCell;
	private int vectorOutputSize;
	private int nbCellToWorkWith;
	private int frameSize;
	private int border = 0;
	
	public ConcreteCellBheaviorNormalV1(int distanceVueCell , int tailleVecteurOutput , int nbElementAPrendreEnCompte , int tailleFenetre) {
		this.maxFieldOfViewCell = distanceVueCell;
		this.vectorOutputSize = tailleVecteurOutput;
		this.nbCellToWorkWith = nbElementAPrendreEnCompte;
		this.frameSize = tailleFenetre;
	}

	//method that allowd to determine the optimal direction to escape predators
	@Override
	public Vecteur escapePredator(Position position , List<Cell> predatorList) {
		Vecteur predatorVector = new Vecteur(new Position(0,0));
		int maxSize = this.nbCellToWorkWith;
		
		
		if(!predatorList.isEmpty()) {
			//get the this.nbCellToWorkWith nearest predator
			ArrayList<CellDistance> cellToSort = new ArrayList<CellDistance>();
			for(int i = 0 ; i < predatorList.size() ; i++) {
				float distance = Position.distance(predatorList.get(i).getPosition(),position);
				if((int)distance <= this.maxFieldOfViewCell) {
					CellDistance cell = new CellDistance(i, distance);
					cellToSort.add(cell);
				}
			}
			// sort the cells from the nearer to the farther
			if(!cellToSort.isEmpty()) {
				Collections.sort(cellToSort);
			}
			
			
			if(predatorList.size() <= maxSize) {
				maxSize = predatorList.size();
			}
			for(int i = 0 ; i < maxSize ; i++) {
				// iterate through the predator and add the escape vector of each with more intensity the nearest a predator is
				Vecteur escapeVectorPredator = new Vecteur(predatorList.get(i).getPosition(), position);
				float predatorDistance = Position.distance(predatorList.get(i).getPosition() , position);
				escapeVectorPredator.setDistance((1/predatorDistance)*100);
				predatorVector.addVector(escapeVectorPredator);
			}
		}

		predatorVector.setDistance(this.vectorOutputSize);
		return(predatorVector);
	}

	//method that allowd to determine the optimal direction to not hit a wall
	@Override
	public Vecteur escapeWall(Position position, List<Position> wallList) {
		Vecteur escapeVector = new Vecteur(new Position(0,0));
		int maxSize = this.nbCellToWorkWith;
		
		if(maxSize > wallList.size())
			maxSize = wallList.size();
		//calculate for each wall position the escape vector
		for(int i = 0 ; i < maxSize ; i++) {
			int xEscape = position.getX();
			int yEscape = position.getY();
			if(Math.random()*14 > 7) {
				xEscape += position.getX()-wallList.get(i).getX();
			}else {
				xEscape += position.getY()-wallList.get(i).getY();
			}
			
			Position wallEscapePosition = new Position(xEscape,yEscape);
			Vecteur wallEscapeVector = new Vecteur(wallList.get(i), wallEscapePosition);
			float wallDistance = Position.distance(wallList.get(i) , wallEscapePosition);
			float vectorIntensityRescale = (float) (1/(Math.log10(wallDistance+2)*Math.exp(wallDistance)))*1000;
			wallEscapeVector.setDistance(vectorIntensityRescale);
			escapeVector.addVector(wallEscapeVector);
		}
		
		escapeVector.setDistance(this.vectorOutputSize);
		return(escapeVector);
	}

	//method that allowd to determine the optimal direction to eat prey
	@Override
	public Vecteur huntPrey(Position position , List<Cell> preyList) {
		Vecteur vecteurProie = new Vecteur(new Position(0,0));

		if(!preyList.isEmpty()) {
			//get and sort the nearest prey
			ArrayList<CellDistance> cellATrier = new ArrayList<CellDistance>();
			for(int i = 0 ; i < preyList.size() ; i++) {
				float distance = Position.distance(preyList.get(i).getPosition(),position);
				CellDistance cell = new CellDistance(i, distance);
				cellATrier.add(cell);
			}
			if(!cellATrier.isEmpty()) {
				Collections.sort(cellATrier);
			}
			
			//vreate the hunt vector only on the nearest prey only
			Position proiePlusProche = preyList.get(cellATrier.get(0).getPositionTab()).getPosition();
			vecteurProie = new Vecteur(position , proiePlusProche);
		}

		vecteurProie.setDistance(this.vectorOutputSize*0.7F);
		return(vecteurProie);
	}

	//method that allowd to determine the optimal direction to respect social distancing
	@Override
	public Vecteur escapeAllies(Position position , List<Cell> allieList) {
		Vecteur vecteurAlier = new Vecteur(new Position(0,0));
		int distancementSocial = 10;

		if(allieList.isEmpty())
			return(vecteurAlier);
		
		// récup les plus proches cellules qui sont a moins de distanceVueCell de distance
		ArrayList<CellDistance> cellATrier = new ArrayList<CellDistance>();
		for (int i = 0; i < allieList.size(); i++) {
			int distance = Math_functions.round(Position.distance(allieList.get(i).getPosition(), position));
			if (distance <= distancementSocial) {
				CellDistance cell = new CellDistance(i, distance);
				cellATrier.add(cell);
			}
		}

		if (cellATrier.size() > 1) {
			// trie et récupére la cellule la plus proche
			Collections.sort(cellATrier);
			int indicePlusProchePoto = cellATrier.get(1).getPositionTab();

			// génération du vecteur de fuite
			vecteurAlier = new Vecteur(allieList.get(indicePlusProchePoto).getPosition(), position);
			if (vecteurAlier.getLenght() == 0) {
				Position origine = new Position(0, 0);
				Position destination = new Position((int)(Math.random() * 14_000_000)-7_000_000 , (int)(Math.random() * 14_000_000)-7_000_000);
				vecteurAlier = new Vecteur(origine, destination);
			}
		}

		vecteurAlier.setDistance(this.vectorOutputSize*10F);
		return(vecteurAlier);
	}
	
	//method that allowd to determine the optimal general direction 
	@Override
	public Vecteur directionMovement(Position position , List<Cell> predatorList , List<Position> wallList , List<Cell> preyList , List<Cell> allieList) {
		Vecteur move = new Vecteur(new Position(0, 0));

		// create the diferant vectors and add them
		Vecteur movePrey = this.huntPrey(position, preyList);
		Vecteur movePredator = this.escapePredator(position, predatorList);
		Vecteur moveAlie = this.escapeAllies(position, allieList);
		Vecteur moveWall = this.escapeWall(position, wallList);

		move.addVector(movePrey);
		move.addVector(moveAlie);
		move.addVector(movePredator);
		move.addVector(moveWall);

		this.isTouchingWall(position, move);
		move.setDistance(1);
			
		return(move);
	}

	// check if the cell is touching a wall or is about to
	private void isTouchingWall(Position position , Vecteur direction) {
		boolean toucherX = position.getX() < 7 || position.getX() > this.frameSize - 7;
		boolean toucherY = position.getY() < 7 || position.getY() > this.frameSize - 7;
		
		if (toucherX || this.border > 0) {
			direction.invertedX();
			if(this.border == 0)
				this.border = 10;
		}
		if (toucherY || this.border > 0) {
			direction.invertedY();
			if(this.border == 0)
				this.border = 10;
		}
		

		toucherX = position.getX() < 5 || position.getX() > this.frameSize - 5;
		toucherY = position.getY() < 5 || position.getY() > this.frameSize - 5;
		if (toucherX) {
			direction.removeX();
		}
		if (toucherY) {
			direction.removeY();
		}
		
		this.border--;
	}
	

}