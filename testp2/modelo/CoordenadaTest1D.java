package modelo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import modelo.excepciones.ExcepcionCoordenadaIncorrecta;

/**
@author jgonzalo
 **/
public class CoordenadaTest1D {
	Coordenada1D c;
	
	@Before
	public void setUp() throws Exception {
		c = new Coordenada1D(3);
				
	}

	@Test
	public final void testGetters() {
		assertEquals("x", 3, c.getX());
		
	}

	@Test
	public final void testInicializacion() throws ExcepcionCoordenadaIncorrecta {
	
		Coordenada1D c2 = new Coordenada1D(c.getX());
		assertEquals("c2.x", c2.getX(), c.getX());
		
		try {
		    Coordenada1D c3 = new Coordenada1D(0);
		    assertEquals("",0,c3.getX());
		}catch (ExcepcionCoordenadaIncorrecta e) {
		    e.getMessage();
		}
		
	}
	
	@Test
	public final void testEquals() throws ExcepcionCoordenadaIncorrecta {
		Coordenada c4 = new Coordenada1D(3);
		Coordenada c5 = new Coordenada1D(3);
		Coordenada c6 = new Coordenada1D(4);
		String s = new String();
		assertFalse(c.equals(null));
		assertFalse(c.equals(s));
		assertTrue(c.equals(c5));
		assertFalse(c.equals(c6));
		assertTrue(c.equals(c));
		assertTrue(c.equals(c4));
		
	
	}
	
		
	@Test
	public final void testToString() {
		assertEquals("Coordenada.toString()","(3)",c.toString());
		
	}
	
	@Test
	public final void testSuma() throws ExcepcionCoordenadaIncorrecta {
		Coordenada1D c7 = new Coordenada1D(c.suma(c));
		assertEquals("c.suma(c).x",6,c7.getX());
		assertEquals("c.suma(c7)","(9)",c.suma(c7).toString());
		
	}
	
}
