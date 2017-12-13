package entradasalida;

import static org.junit.Assert.*;

import modelo.Tablero;
import modelo.Tablero1D;
import modelo.TableroCeldasCuadradas;
import modelo.excepciones.ExcepcionArgumentosIncorrectos;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import entradasalida.excepciones.ExcepcionLectura;
import modelo.MetodosAuxiliares;

public class ParserTablerosTest extends MetodosAuxiliares {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test(expected = ExcepcionArgumentosIncorrectos.class)
	public void testLeeTableroExcepcion1() {
		try {
			ParserTableros.leeTablero(null);
		} catch (ExcepcionLectura e) {
			fail("Error no debió producirse ExcepcionLectura");
		}
	}

	@Test(expected = ExcepcionLectura.class)
	public void testLeeTableroExcepcion2() throws ExcepcionLectura {

		ParserTableros.leeTablero("");
	}

	@Test(expected = ExcepcionLectura.class)
	public void testLeeTableroExcepcion3() throws ExcepcionLectura {

		ParserTableros.leeTablero("**  *** ********* ***** ** @");
	}

	@Test(expected = ExcepcionLectura.class)
	public void testLeeTableroExcepcion4() throws ExcepcionLectura {

		ParserTableros.leeTablero("@*  *** ********* ***** ** ");
	}

	@Test(expected = ExcepcionLectura.class)
	public void testLeeTableroExcepcion5() throws ExcepcionLectura {

		ParserTableros.leeTablero("*  *** ***@****** ***** ** ");
	}

	@Test(expected = ExcepcionLectura.class)
	public void testLeeTableroExcepcion6() throws ExcepcionLectura {

		ParserTableros.leeTablero("**  *** \n   ************");
	}

	@Test(expected = ExcepcionLectura.class)
	public void testLeeTableroExcepcion7() throws Exception {
		ParserTableros.leeTablero(cargaTablero("test/ficheros/tablero2Dmal.ent"));

	}

	@Test
	public void testLeeTablero() {
		Tablero tablero = null;

		try {
			tablero = ParserTableros.leeTablero("**  *******   ** ***  *****   ");
			if (!(tablero instanceof Tablero1D)) {
				fail("Se esperaba un Tablero1D pero se obtuvo " + tablero.getClass().getSimpleName());
			}
			tablero = ParserTableros.leeTablero("**  *******\n   ** ***  ");
			if (!(tablero instanceof TableroCeldasCuadradas)) {
				fail("Se esperaba un TableroCeldasCuadradas pero se obtuvo " + tablero.getClass().getSimpleName());
			}
		} catch (Exception e) {
			fail("No se esperaba excepcion, pero se capturo " + e.getClass().getSimpleName());
		}
	}

}
