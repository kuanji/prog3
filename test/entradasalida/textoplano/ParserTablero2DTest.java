package entradasalida.textoplano;

import static org.junit.Assert.*;

import modelo.EstadoCelda;
import modelo.d2.Coordenada2D;
import modelo.d2.Tablero2D;
import modelo.excepciones.ExcepcionArgumentosIncorrectos;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import entradasalida.ParserTableros;
import entradasalida.excepciones.ExcepcionLectura;
import entradasalida.txt.ParserTablero2D;

public class ParserTablero2DTest {

	static ParserTablero2D pt;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		pt = new ParserTablero2D();
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test(expected = ExcepcionArgumentosIncorrectos.class)
	public void testLeeTablero2DExcepcion1() {
		try {
			pt.leeTablero(null);
		} catch (ExcepcionLectura e) {
			fail("Error no debió producirse ExcepcionLectura");
		}
	}

	@Test(expected = ExcepcionLectura.class)
	public void testLeeTablero2DExcepcion2() throws ExcepcionLectura {

		ParserTableros.leeTablero("");
	}

	@Test(expected = ExcepcionLectura.class)
	public void testLeeTableroExcepcion3() throws ExcepcionLectura {

		ParserTableros.leeTablero("**  \n*** ********* ***** ** ");
	}

	@Test(expected = ExcepcionLectura.class)
	public void testLeeTablero2DExcepcion4() throws ExcepcionLectura {

		ParserTableros.leeTablero("**  \n*** \n****\n    \n **G\n ** ");
	}

	@Test
	public void testLeeTablero2D1() {

		try {
			Tablero2D tab = (Tablero2D) pt.leeTablero("  \n  ");
			Coordenada2D c2 = (Coordenada2D) tab.getDimensiones();

			if (!(c2.equals(new Coordenada2D(2, 2))))
				fail("La dimensión debe de ser " + new Coordenada2D(2, 2).toString() + " y muestra " + c2.toString());
			else {
				int dimx = ((Coordenada2D) tab.getDimensiones()).getX();
				int dimy = ((Coordenada2D) tab.getDimensiones()).getY();
				for (int i = 0; i < dimy; i++) {
					for (int j = 0; j < dimx; j++) {
						c2 = new Coordenada2D(j, i);
						assertEquals("Comparacion tableros2D " + c2.toString(), EstadoCelda.MUERTA,
								tab.getCelda(new Coordenada2D(j, i)));
					}
				}
			}

		} catch (Exception e) {
			fail("No se esperaba excepcion, pero se capturo " + e.getClass().getSimpleName());
		}

	}

	@Test
	public void testLeeTablero2D2() {

		try {
			Tablero2D tab = (Tablero2D) pt.leeTablero("**\n**");
			Coordenada2D c2 = (Coordenada2D) tab.getDimensiones();

			if (!(c2.equals(new Coordenada2D(2, 2))))
				fail("La dimensión debe de ser " + new Coordenada2D(2, 2).toString() + " y muestra " + c2.toString());
			else {
				int dimx = ((Coordenada2D) tab.getDimensiones()).getX();
				int dimy = ((Coordenada2D) tab.getDimensiones()).getY();
				for (int i = 0; i < dimy; i++) {
					for (int j = 0; j < dimx; j++) {
						c2 = new Coordenada2D(j, i);
						assertEquals("Comparacion tableros2D " + c2.toString(), EstadoCelda.VIVA,
								tab.getCelda(new Coordenada2D(j, i)));
					}
				}
			}

		} catch (Exception e) {
			fail("No se esperaba excepcion, pero se capturo " + e.getClass().getSimpleName());
		}
	}

	@Test
	public void testLeeTablero2D3() {

		try {

			String st = new String("*    \n *   \n  *  \n   * \n    *");
			Tablero2D tab = (Tablero2D) pt.leeTablero(st);
			Coordenada2D c2 = (Coordenada2D) tab.getDimensiones();

			if (!(c2.equals(new Coordenada2D(5, 5))))
				fail("La dimensión debe de ser " + new Coordenada2D(5, 5).toString() + " y muestra " + c2.toString());
			else {
				int dimx = ((Coordenada2D) tab.getDimensiones()).getX();
				int dimy = ((Coordenada2D) tab.getDimensiones()).getY();
				for (int i = 0; i < dimy; i++) {
					for (int j = 0; j < dimx; j++) {
						c2 = new Coordenada2D(j, i);
						if (i == j)
							assertEquals("Comparacion tableros2D " + c2.toString(), EstadoCelda.VIVA,
									tab.getCelda(new Coordenada2D(j, i)));
						else
							assertEquals("Comparacion tableros2D " + c2.toString(), EstadoCelda.MUERTA,
									tab.getCelda(new Coordenada2D(j, i)));
					}
				}
			}

		} catch (Exception e) {
			fail("No se esperaba excepcion, pero se capturo " + e.getClass().getSimpleName());
		}
	}

}
