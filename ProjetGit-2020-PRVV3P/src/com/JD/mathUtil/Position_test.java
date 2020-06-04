package com.JD.mathUtil;

import static org.junit.Assert.*;

import org.junit.Test;

public class Position_test {

	@Test
	public void test_get() {
		Position p1 = new Position(1,2);
		Position p2 = new Position(-1,0);

		assertEquals(p1.getX(),1);
		assertEquals(p1.getY(),2);

		assertEquals(p2.getX(),-1);
		assertEquals(p2.getY(),0);
	}
	
	@Test
	public void test_distance_int_normal() {
		Position un = new Position(1,1);
		Position deux = new Position(2,3);
		
		float distance_un_deux = 2.2360679F;
		assertEquals(Position.distance(un,deux) , distance_un_deux , 0F);
	}
	
	@Test
	public void test_distance_int_zero() {
		Position deux = new Position(2,3);
		Position trois = new Position(0,0);
		float d_deux_trois = 3.6055512F;
		
		assertEquals(Position.distance(deux,trois) , d_deux_trois,0F);
		assertEquals(Position.distance(trois,trois),Position.distance(deux,deux));
		assertEquals(0.0F , Position.distance(deux,deux) , 0F);
	}
	
	@Test
	public void test_distance_int_negatif() {
		Position un = new Position(1,1);
		Position deux = new Position(0,0);
		Position trois = new Position(-1,-1);
		Position quatre = new Position(2,2);

		assertEquals(Position.distance(un,deux) , Position.distance(deux,trois) , 0F);
		assertEquals(Position.distance(un,trois) , Position.distance(deux,quatre) , 0F);
	}
	
	@Test
	public void test_distance_float_normal() {
		Position un = new Position(1.11F,1.11F);
		Position deux = new Position(2.29F,3.45F);
		float d_un_deux = 2.6206869F;
		
		assertTrue(Position.distance(un,deux) == d_un_deux);
	}
	
	@Test
	public void test_distance_float_zero() {
		Position deux = new Position(2.0F,3.0F);
		Position trois = new Position(0,0);
		float d_deux_trois = 3.6055512F;
		
		assertTrue(Position.distance(deux,trois) == d_deux_trois);
		assertEquals(Position.distance(trois,trois),Position.distance(deux,deux));
		assertTrue(0.0F == Position.distance(deux,deux));
	}
	
	@Test
	public void test_distance_float_negatif() {
		Position un = new Position(1.5165F,1.256F);
		Position trois = new Position(0,0);
		Position quatre = new Position(-1.5165F,-1.256F);

		assertEquals(Position.distance(un,trois) , Position.distance(quatre,trois));
	}
	
	@Test
	public void test_distance_float_moitnegatif() {
		Position deux = new Position(2.5F,3.59F);
		Position quatre = new Position(-1.50F,-1.23875F);
		float d_deux_quatre = 6.2703131F;

		assertTrue(Position.distance(deux,quatre) == d_deux_quatre);	
		assertEquals(Position.distance(deux,quatre) , Position.distance(quatre,deux));		
	}
	
	@Test
	public void test_egalite() {
		Position un = new Position(2,3);
		Position deux = new Position(-1,-1);
		Position trois = new Position(2,3);
		Position quatre = new Position(-2,-3);

		assertNotEquals(un,deux);
		assertEquals(un,trois);
		assertNotEquals(un,quatre);
		
	}
	
	@Test
	public void test_translationde_juste() {
		Position point1 = new Position(0,0);
		Position resultat = new Position(1,1);
		Vecteur vecteur = new Vecteur(resultat);
		
		point1.translate(vecteur);
		assertEquals(resultat , point1);
		
		point1 = new Position(0,0);
		resultat = new Position(2,3);
		vecteur = new Vecteur(resultat);

		point1.translate(vecteur);
		assertEquals(resultat , point1);
	}
	
	@Test
	public void test_translationde_pasJuste() {
		Position point1 = new Position(0,0);
		Position resultat = new Position(1,1);
		Vecteur vecteur = new Vecteur(new Position(2,1));
		
		point1.translate(vecteur);
		assertNotEquals(resultat , point1);
		
		point1 = new Position(0,0);
		resultat = new Position(2,3);
		vecteur = new Vecteur(new Position(2,1));

		point1.translate(vecteur);
		assertNotEquals(resultat , point1);
	}
	
	@Test
	public void test_pastranslationde() {
		Position point1 = new Position(0,0);
		Position resultat = new Position(0,0);
		Vecteur vecteur = new Vecteur(resultat);
		
		point1.translate(vecteur);
		assertEquals(resultat , point1);
	}
	
	@Test
	public void test_translationdeNegative_pasJuste() {
		Position point1 = new Position(0,0);
		Position resultat = new Position(-1,-1);
		Vecteur vecteur = new Vecteur(new Position(-2,-1));
		
		point1.translate(vecteur);
		assertNotEquals(resultat , point1);
		
		point1 = new Position(0,0);
		resultat = new Position(-2,-3);
		vecteur = new Vecteur(new Position(-2,-1));

		point1.translate(vecteur);
		assertNotEquals(resultat , point1);
	}
	
	@Test
	public void test_translationdeNegative_juste() {
		Position point1 = new Position(0,0);
		Position resultat = new Position(-1,-1);
		Vecteur vecteur = new Vecteur(resultat);
		
		point1.translate(vecteur);
		assertEquals(resultat , point1);
		
		point1 = new Position(0,0);
		resultat = new Position(-2,-3);
		vecteur = new Vecteur(resultat);

		point1.translate(vecteur);
		assertEquals(resultat , point1);
	}
	
	@Test
	public void test_translationdepas0_juste() {
		Position point1 = new Position(1,1);
		Position resultat = new Position(2,11);
		Vecteur vecteur = new Vecteur(new Position(1,10));
		
		point1.translate(vecteur);
		assertEquals(resultat , point1);
		
		point1 = new Position(1,1);
		resultat = new Position(0,0);
		vecteur = new Vecteur(new Position(-1,-1));

		point1.translate(vecteur);
		assertEquals(resultat , point1);
	}	
}