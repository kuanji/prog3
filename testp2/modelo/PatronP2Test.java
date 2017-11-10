/**
 * 
 */
package modelo;

import static org.junit.Assert.*;


import java.util.HashSet;




import org.junit.Before;
import org.junit.Test;

import modelo.excepciones.ExcepcionCoordenadaIncorrecta;
import modelo.excepciones.ExcepcionPosicionFueraTablero;

/**
 * @author gonzalo
 *
 */
public class PatronP2Test {

	Tablero tablero;
	Patron patron;
	String snombre;
	
	int xtab;
	int ytab;
	

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		xtab = 3;
		ytab = 3;
		tablero = new TableroCeldasCuadradas(xtab,ytab);
		tablero.setCelda(new Coordenada2D(0,0),EstadoCelda.VIVA);
		tablero.setCelda(new Coordenada2D(1,1),EstadoCelda.VIVA);
		tablero.setCelda(new Coordenada2D(2,2),EstadoCelda.VIVA);
		snombre = new String ("Diagonal");
		patron = new Patron(snombre,tablero);
	}

	/**
	 * Test method for {@link modelo.Patron#getNombre()}.
	 */
	@Test
	public void testGetNombre() {
		
		assertEquals("Nombre ","Diagonal",patron.getNombre());
		
	}

	/**
	 * Test method for {@link modelo.Patron#getCelda(modelo.Coordenada)}.
	 * @throws ExcepcionCoordenadaIncorrecta 
	 * @throws ExcepcionPosicionFueraTablero 
	 */
	@Test
	public void testGetCelda() throws ExcepcionCoordenadaIncorrecta, ExcepcionPosicionFueraTablero {
		Coordenada c;
		for (int x=0; x<xtab; x++)
			for (int y=0; y<ytab; y++) {
				c = new Coordenada2D(x,y);
				if (x==y) assertEquals("Estado Celda VIVA ", EstadoCelda.VIVA, patron.getCelda(c));
				else assertEquals("Estado Celda MUERTA ", EstadoCelda.MUERTA,patron.getCelda(c));
			}
	}

	/**
	 * Test method for {@link modelo.Patron#getPosiciones()}.
	 * @throws ExcepcionCoordenadaIncorrecta 
	 */
	@Test
	public void testGetPosiciones() throws ExcepcionCoordenadaIncorrecta {
		HashSet<Coordenada> sctab =new HashSet<Coordenada>();
		
		for (int x=0; x<xtab; x++)
			for (int y=0; y<ytab; y++) {
				sctab.add(new Coordenada2D(x,y));
			}
		assertEquals("Estan todas posiciones en Patron", sctab, patron.getPosiciones());
		}

	/**
	 * Test method for {@link modelo.Patron#toString()}.
	 */
	@Test
	public void testToString() {
		StringBuilder salida=new StringBuilder();
		salida.append("Diagonal\n");
		salida.append("+---+\n");
		salida.append("|*  |\n");
		salida.append("| * |\n");
		salida.append("|  *|\n");
		salida.append("+---+\n");
		String sal = salida.toString();
		assertEquals("Impresión de Patron ", sal,patron.toString());
	}
	

}
