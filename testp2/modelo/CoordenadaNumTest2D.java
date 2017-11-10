package modelo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import modelo.Coordenada;
import modelo.excepciones.ExcepcionCoordenadaIncorrecta;

/**
@author jgonzalo
@date 13/09/2013
 **/
public class CoordenadaNumTest2D {
	Coordenada c;
	
	@Before
	public void setUp() throws Exception {
		c = new Coordenada2D(3, 5);
				
	}

	
	@Test
	public final void testNumCoordenada() throws ExcepcionCoordenadaIncorrecta {
		Coordenada c3 = new Coordenada2D(100,25);
		assertEquals("c.suma(c7)","(103,30)",c.suma(c3).toString());
	}
	

}
