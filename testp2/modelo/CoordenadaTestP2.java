package modelo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import modelo.excepciones.ExcepcionCoordenadaIncorrecta;

/**
@author jgonzalo
 **/
public class CoordenadaTestP2 {
	Coordenada2D c;
	
	@Before
	public void setUp() throws Exception {
		c = new Coordenada2D(3, 5);
				
	}

	@Test
	public final void testGetters() {
		assertEquals("x", 3, c.getX());
		assertEquals("y", 5, c.getY());
		
	}

	@Test
	public final void testInicializacion() {
	
		Coordenada2D c2 = new Coordenada2D(c);
		assertEquals("c2.x", c2.getX(), c.getX());
		assertEquals("c2.y", c2.getY(), c.getY());
		
	}
	
	@Test
	public final void testEquals() throws ExcepcionCoordenadaIncorrecta {
		Coordenada2D c4 = new Coordenada2D(3,5);
		Coordenada2D c5 = new Coordenada2D(3,6);
		Coordenada2D c6 = new Coordenada2D(4,5);
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
	public final void testSuma() throws ExcepcionCoordenadaIncorrecta {
		Coordenada2D c7 = new Coordenada2D(c.suma(c));
		assertEquals("c.suma(c).x",6,c7.getX());
		assertEquals("c.suma(c).y",10,c7.getY());
		assertEquals("c.suma(c7)","(9,15)",c.suma(c7).toString());
		
	}
	
}
