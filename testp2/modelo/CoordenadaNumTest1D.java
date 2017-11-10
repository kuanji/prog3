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
public class CoordenadaNumTest1D {
	Coordenada c,c2;
	
	@Before
	public void setUp() throws Exception {
		c = new Coordenada1D(3);
		c2 = new Coordenada1D(5);
				
	}

	
	@Test
	public final void testNumCoordenada() throws ExcepcionCoordenadaIncorrecta {
		Coordenada c3 = new Coordenada1D(100);
		Coordenada c4 = new Coordenada1D(25);
		assertEquals("c.suma(c7)","(103)",c.suma(c3).toString());
		assertEquals("c.suma(c7)","(30)",c2.suma(c4).toString());
	}
	

}
