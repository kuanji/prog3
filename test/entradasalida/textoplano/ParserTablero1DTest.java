/**
 * 
 */
package entradasalida.textoplano;

import static org.junit.Assert.*;

import modelo.EstadoCelda;
import modelo.d1.Coordenada1D;
import modelo.d1.Tablero1D;
import modelo.excepciones.ExcepcionArgumentosIncorrectos;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import entradasalida.excepciones.ExcepcionLectura;
import entradasalida.txt.ParserTablero1D;

/**
 * @author gonzalo
 *
 */
public class ParserTablero1DTest {

	/**
	 * @throws java.lang.Exception
	 */
	static ParserTablero1D pt;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		pt = new ParserTablero1D();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Test(expected = ExcepcionArgumentosIncorrectos.class)
	public void testLeeTablero1DExcepcion1() {
		try {
			pt.leeTablero(null);
		} catch (ExcepcionLectura e) {
			fail("Error no debió producirse ExcepcionLectura");
		}
	}

	@Test(expected = ExcepcionLectura.class)
	public void testLeeTablero1DExcepcion2() throws ExcepcionLectura {

		pt.leeTablero("@");
	}

	@Test(expected = ExcepcionLectura.class)
	public void testLeeTablero1DExcepcion3() throws ExcepcionLectura {

		pt.leeTablero("");
	}

	@Test
	public void testLeeTablero1D1() {
		try {
			Tablero1D stab = new Tablero1D(1);
			Tablero1D tab = (Tablero1D) pt.leeTablero(" ");
			int dim = ((Coordenada1D) tab.getDimensiones()).getX();
			if (dim != 1)
				fail("La dimensión debe de ser 1 y muestra " + dim);
			else {
				for (int i = 0; i < dim; i++) {
					assertEquals("Comparacion tableros1D " + i, stab.getCelda(new Coordenada1D(i)),
							tab.getCelda(new Coordenada1D(i)));
				}
			}

		} catch (Exception e) {
			fail("No se esperaba excepcion, pero se capturo " + e.getClass().getSimpleName());
		}

	}

	@Test
	public void testLeeTablero1D2() {

		try {

			Tablero1D tab = (Tablero1D) pt.leeTablero("*");
			int dim = ((Coordenada1D) tab.getDimensiones()).getX();
			if (dim != 1)
				fail("La dimensión debe de ser 1 y muestra " + dim);
			else {
				for (int i = 0; i < dim; i++) {
					assertEquals("Comparacion tableros1D " + i, EstadoCelda.VIVA, tab.getCelda(new Coordenada1D(i)));
				}
			}
		} catch (Exception e) {
			fail("No se esperaba excepcion, pero se capturo " + e.getClass().getSimpleName());
		}
	}

	@Test
	public void testLeeTablero1D3() {

		try {

			String st = new String("*******************************************************");
			Tablero1D tab = (Tablero1D) pt.leeTablero(st);
			int dim = ((Coordenada1D) tab.getDimensiones()).getX();
			if (dim != st.length())
				fail("La dimensión debe de ser " + st.length() + " y muestra " + dim);

			for (int i = 0; i < dim; i++) {

				assertEquals("Comparacion tableros1D " + i, EstadoCelda.VIVA, tab.getCelda(new Coordenada1D(i)));
			}
		} catch (Exception e) {
			fail("No se esperaba excepcion, pero se capturo " + e.getClass().getSimpleName());
		}
	}

	@Test
	public void testLeeTablero1D4() {

		try {

			String st = new String("                                                       ");
			Tablero1D tab = (Tablero1D) pt.leeTablero(st);
			int dim = ((Coordenada1D) tab.getDimensiones()).getX();
			if (dim != st.length())
				fail("La dimensión debe de ser " + st.length() + " y muestra " + dim);

			for (int i = 0; i < dim; i++) {

				assertEquals("Comparacion tableros1D " + i, EstadoCelda.MUERTA, tab.getCelda(new Coordenada1D(i)));
			}
		} catch (Exception e) {
			fail("No se esperaba excepcion, pero se capturo " + e.getClass().getSimpleName());
		}
	}

	@Test
	public void testLeeTablero1D5() {

		try {

			String st = new String("*        *       *      *     *    *   *  * **");
			Tablero1D tab = (Tablero1D) pt.leeTablero(st);
			int dim = ((Coordenada1D) tab.getDimensiones()).getX();
			if (dim != st.length())
				fail("La dimensión debe de ser " + st.length() + " y muestra " + dim);
			int n = 0, j = 9;
			for (int i = 0; i < dim; i++) {
				if (i == n) {
					assertEquals("Comparacion tableros1D " + i, EstadoCelda.VIVA, tab.getCelda(new Coordenada1D(i)));
					n = i + j;
					j--;
				} else
					assertEquals("Comparacion tableros1D " + i, EstadoCelda.MUERTA, tab.getCelda(new Coordenada1D(i)));
			}

		} catch (Exception e) {
			fail("No se esperaba excepcion, pero se capturo " + e.getClass().getSimpleName());
		}
	}

}
