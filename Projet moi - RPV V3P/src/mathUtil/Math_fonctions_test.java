package mathUtil;

import org.junit.Test;
import junit.framework.TestCase;

public class Math_fonctions_test extends TestCase{

	@Test
	public void test_arrondi_dessous() {
		float value = 45.49999F;
		int resultat = Math_functions.round(value);
		assertEquals(resultat , 45);
		
		value = -2.59999F;
		resultat = Math_functions.round(value);
		assertEquals(resultat,-3);
	}
	
	@Test
	public void test_arrondi_dessus() {
		float value = 45.59999F;
		int resultat = Math_functions.round(value);
		assertEquals(resultat , 46);
	}
	
	@Test
	public void test_arrondi_5() {
		float value = 45.5F;
		int resultat = Math_functions.round(value);
		assertEquals(resultat , 46);
	}
	
	@Test
	public void test_arrondi_pas() {
		float value = 45.0F;
		int resultat = Math_functions.round(value);
		assertEquals(resultat , 45);
	}
}