/**
 * 
 */
package modelo;

import static org.junit.Assert.*;

import modelo.excepciones.ExcepcionCoordenadaIncorrecta;

import org.junit.Before;
import org.junit.Test;

/**
 * @author gonzalo
 *
 */
public class Coordenada1DTest {

Coordenada1D c;
	
	@Before
	public void setUp() throws Exception {
		c = new Coordenada1D(5);
				
	}

	@Test
	public final void testGetters() {
		
		assertEquals("x", 5, c.getX());
		
	}

	@Test
	public final void testInicializacion() {
	
		Coordenada1D c2 = new Coordenada1D(c);
		assertEquals("c2.x", 5,c2.getX());
		
		
	}
	
	@Test
	public final void testEquals() {
		Coordenada1D c4;
		try {
			c4 = new Coordenada1D(3);
		
		Coordenada1D c5 = new Coordenada1D(6);
		Coordenada1D c6 = new Coordenada1D(5);
		String s = new String();
		assertFalse(c.equals(null));
		assertFalse(c.equals(s));
		assertFalse(c.equals(c4));
		assertFalse(c.equals(c5));
		assertTrue(c.equals(c));
		assertTrue(c.equals(c6));
		} catch (ExcepcionCoordenadaIncorrecta e) {
			// TODO Auto-generated catch block
			fail("Error. La ExcepcionCoordenadaIncorrecta no debía haberse producido");
		}
	
	}
	
		
	@Test
	public final void testToString() {
		assertEquals("Coordenada.toString()","(5)",c.toString());
		
	}
	
	@Test
	public final void testSuma() {
		Coordenada1D c7;
		Coordenada c1 = c;
		try {
			c7 = new Coordenada1D(c.suma(c1));
			assertEquals("c.suma(c).x",10,c7.getX());
			assertEquals("c.suma(c7)","(15)",c.suma(c7).toString());
		} catch (ExcepcionCoordenadaIncorrecta e) {
			// TODO Auto-generated catch block
			fail("Error. La ExcepcionCoordenadaIncrrecta no debió producirse.");
		}
	}
	
}
