package modelo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
@author jgonzalo
 **/
public class CoordenadaTest {
	Coordenada c;
	
	@Before
	public void setUp() throws Exception {
		c = new Coordenada(3, 5);
				
	}

	@Test
	public final void testGetters() {
		assertEquals("x", 3, c.getX());
		assertEquals("y", 5, c.getY());
		
	}

	@Test
	public final void testInicializacion() {
	
		Coordenada c2 = new Coordenada(c);
		assertEquals("c2.x", c2.getX(), c.getX());
		assertEquals("c2.y", c2.getY(), c.getY());
		
	}
	
	@Test
	public final void testEquals() {
		Coordenada c4 = new Coordenada(3,5);
		Coordenada c5 = new Coordenada(3,6);
		Coordenada c6 = new Coordenada(4,5);
		String s = new String();
		assertFalse(c.equals(null));
		assertFalse(c.equals(s));
		assertFalse(c.equals(c5));
		assertFalse(c.equals(c6));
		assertTrue(c.equals(c));
		assertTrue(c.equals(c4));
		
	
	}
	
		
	@Test
	public final void testToString() {
		assertEquals("Coordenada.toString()","(3,5)",c.toString());
		
	}
	
	@Test
	public final void testSuma() {
		Coordenada c7 = new Coordenada(c.suma(c));
		assertEquals("c.suma(c).x",6,c7.getX());
		assertEquals("c.suma(c).y",10,c7.getY());
		assertEquals("c.suma(c7)","(9,15)",c.suma(c7).toString());
		
	}
	
}
