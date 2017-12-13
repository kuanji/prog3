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
public class CoordenadaTest {
	Coordenada c1d, c2d;
	
	@Before
	public void setUp() throws Exception {
		c1d = new Coordenada1D(7);
		c2d = new Coordenada2D(8,6);
				
	}

	
	@Test
	public final void testSuma() {
		Coordenada c7;
		try {
			c7 = c1d.suma(c1d);
		    assertEquals("c1d.suma(c7)","(21)",c1d.suma(c7).toString());
		    c7 = c2d.suma(c2d);
		    assertEquals("c2d.suma(c7)","(24,18)",c2d.suma(c7).toString());
		} catch (ExcepcionCoordenadaIncorrecta e) {
			// TODO Auto-generated catch block
			fail("Error. ExcepcionCoordenadaIncorrecta no debió producirse.");
		}
	}
	
}
