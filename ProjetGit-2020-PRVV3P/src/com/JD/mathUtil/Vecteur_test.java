package com.JD.mathUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Test;

public class Vecteur_test {

	@Test
	public void test_longeur() {
		Vecteur v = new Vecteur(new Position(1,1));
		Vecteur w = new Vecteur(new Position(0,1));
		float longueur = 1.4142135F;

		assertEquals(1.0F , w.getLenght() , 0F);
		assertEquals(longueur , v.getLenght() , 0F);
	}
	
	@Test
	public void test_longeur_zero() {
		Vecteur v = new Vecteur(new Position(0,0));
		
		assertEquals(0 , v.getLenght() , 0F);
	}
	
	@Test
	public void test_longeur_un() {
		Vecteur v = new Vecteur(new Position(0,1));
		Vecteur w = new Vecteur(new Position(1,0));

		assertEquals(1 , v.getLenght() , 0F);
		assertEquals(1 , w.getLenght() , 0F);
	}
	
	@Test
	public void test_longeur_negative() {
		Vecteur v = new Vecteur(new Position(-1,-2));
		float longueur = 2.2360679F;

		assertEquals(longueur , v.getLenght() , 0F);
	}
	
	@Test
	public void test_get(){
		Vecteur v = new Vecteur(new Position(51,53));
		Vecteur w = new Vecteur(new Position(-15,0));
		
		assertEquals(v.getX() , 51);
		assertEquals(v.getY() , 53);
		assertEquals(w.getX() , -15);
		assertEquals(w.getY() , 0);
	}
	
	@Test
	public void test_translation_point(){
		Vecteur v1 = new Vecteur(new Position(1,0));
		Vecteur v2 = new Vecteur(new Position(-1,0));
		Vecteur w1 = new Vecteur(new Position(0,1));
		Vecteur w2 = new Vecteur(new Position(0,-1));
		
		Position p = new Position(0,0);
		assertEquals(new Position(1,0),v1.translationPoint(p));
		assertEquals(new Position(-1,0),v2.translationPoint(p));
		assertEquals(new Position(0,1),w1.translationPoint(p));
		assertEquals(new Position(0,-1),w2.translationPoint(p));
	}
	
	@Test 
	public void test_ajoutVecteur_normal() {
		Vecteur v1 = new Vecteur(new Position(1,1));
		Vecteur v2 = new Vecteur(new Position(1,1));
		Vecteur solution = new Vecteur(new Position(2,2));
		
		v1.addVector(v2);
		assertEquals(solution , v1);
	}
	
	@Test 
	public void test_ajoutVecteur_0() {
		Vecteur v1 = new Vecteur(new Position(1,1));
		Vecteur v2 = new Vecteur(new Position(0,0));
		Vecteur solution = new Vecteur(new Position(1,1));
		
		v1.addVector(v2);
		assertEquals(solution , v1);
	}
	
	@Test 
	public void test_ajoutVecteur_luismeme() {
		Vecteur v1 = new Vecteur(new Position(1,1));
		Vecteur solution = new Vecteur(new Position(2,2));
		
		v1.addVector(v1);
		assertEquals(solution , v1);
	}
	
	@Test 
	public void test_ajoutVecteur_pasequals() {
		Vecteur v1 = new Vecteur(new Position(1,1));
		Vecteur v2 = new Vecteur(new Position(1,1));
		Vecteur solution = new Vecteur(new Position(3,2));
		
		v1.addVector(v2);
		assertNotEquals(solution , v1);
	}
	
	@Test 
	public void test_ajoutVecteur_XetY() {
		
		Vecteur v1 = new Vecteur(new Position(1,0));
		Vecteur v2 = new Vecteur(new Position(1,1));
		Vecteur solution = new Vecteur(new Position(2,1));
		
		v1.addVector(v2);
		assertEquals(solution , v1);
		
		v1 = new Vecteur(new Position(0,1));
		v2 = new Vecteur(new Position(1,1));
		solution = new Vecteur(new Position(1,2));
		
		v1.addVector(v2);
		assertEquals(solution , v1);
		
	}
}