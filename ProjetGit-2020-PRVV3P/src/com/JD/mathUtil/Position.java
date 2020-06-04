package com.JD.mathUtil;

public class Position implements Comparable<Position>{
	private Float x;
	private Float y;
	
	
	public Position(float xe , float ye) {
		this.x = xe;
		this.y = ye;
	}
	
	
	
	//getters
	public int getX(){
		return(Math_functions.round(this.x));
	}
	public int getY(){
		return(Math_functions.round(this.y));
	}
	public void setX(int x) {
		this.x = x+0F;
	}
	public void setY(int y) {
		this.y = y+0F;
	}
	public float getAbsX(){
		return(this.x);
	}
	public float getAbsY(){
		return(this.y);
	}
	
	
	
	
	
	// calculate distance between 2 points
	public static Float distance(Position p1 , Position p2){
		Float distance = 0.0F;
		if(p1 != null && p2 != null) {
			float x = p1.getAbsX()-p2.getAbsX();
			float y = p1.getAbsY()-p2.getAbsY();
			double temp = (float)Math.abs((x*x)+(y*y));
			if(temp != 0)
				distance = (float)Math.sqrt(temp);
		}
		return(distance);
	}
	
	//add a vector to this position
	public void translate(Vecteur vector) {
		this.x = this.getAbsX()+vector.getX();
		this.y = this.getAbsY()+vector.getY();
	}
	
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(x);
		result = prime * result + Float.floatToIntBits(y);
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
		Position other = (Position) obj;
		if (Float.floatToIntBits(x) != Float.floatToIntBits(other.x))
			return false;
		if (Float.floatToIntBits(y) != Float.floatToIntBits(other.y))
			return false;
		return true;
	}
	@Override
	public String toString() {
		String retour = "";
		
		retour += "["+this.getX()+" , "+this.getY()+"]";
		
		return(retour);
	}
	@Override
	public int compareTo(Position o) {
		float d1 = Position.distance(this, o);
		int resultat = (int)(d1*14000000);
		return(resultat);
	}
	
	
	
}