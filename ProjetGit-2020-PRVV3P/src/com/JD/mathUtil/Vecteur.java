package com.JD.mathUtil;

public class Vecteur {
	private Position destination;
	
	
	public Vecteur(Position pDestination) {
		this.destination = pDestination;
	}
	public Vecteur(Position pOrigin , Position pDestination) {
		float x = pDestination.getAbsX()-pOrigin.getAbsX();
		float y = pDestination.getAbsY()-pOrigin.getAbsY();
		this.destination = new Position(x,y);
	}
	
	//return the lenght of the vector
	public float getLenght(){
		int x = Math.abs(this.destination.getX());
		int y = Math.abs(this.destination.getY());
		return((float)Math.sqrt(y*y+x*x));
	}
	
	
	//getter/setters
	public int getX(){
		int x = this.destination.getX();
		return(x);
	}
	public int getY(){
		int y = this.destination.getY();
		return(y);
	}
	public Position getDestination(){
		return(this.destination);
	}
	public void setDistance(float distance) {
		float rapport = distance/this.getLenght();
		float x = this.destination.getAbsX()*rapport;
		float y = this.destination.getAbsY()*rapport;
		this.destination = new Position(x,y);
	}
	
	// add itself to a point and return the new point
	public Position translationPoint(Position p){
		Position result = new Position(p.getX()+this.getX(),p.getY()+this.getY());
		return(result);
	}
	//add a vector to itself
	public void addVector(Vecteur v) {
		this.destination = this.translationPoint(v.getDestination());
	}

	// change the direction of only one of the X or Y axis
	public void invertedX() {
		this.destination.setX(this.destination.getX()*(-1));
	}
	public void invertedY() {
		this.destination.setY(this.destination.getY()*(-1));
	}

	// remove the direction of only one of the X or Y axis
	public void removeX() {
		float distance = this.getLenght();
		this.destination.setX(0);
		this.setDistance(distance);
	}
	public void removeY() {
		float distance = this.getLenght();
		this.destination.setY(0);
		this.setDistance(distance);
	}
	
	
	
	
	
	@Override
	public String toString() {
		return "Vecteur ["+destination+"]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((destination == null) ? 0 : destination.hashCode());
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
		Vecteur other = (Vecteur) obj;
		if (destination == null) {
			if (other.destination != null)
				return false;
		} else if (!destination.equals(other.destination))
			return false;
		return true;
	}
}