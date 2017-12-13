/**
 * 
 */
package entradasalida;

import static org.junit.Assert.*;

import java.util.ArrayList;

import entradasalida.excepciones.ExcepcionGeneracion;
import entradasalida.gif.*;
import entradasalida.txt.GeneradorFicheroPlano;
import modelo.Coordenada;
import modelo.Regla;
import modelo.Tablero;
import modelo.TableroNoImprimible;
import modelo.d1.Coordenada1D;
import modelo.d1.Regla30;
import modelo.d1.Tablero1D;
import modelo.d2.Coordenada2D;
import modelo.d2.ReglaConway;
import modelo.d2.Tablero2D;
import modelo.d2.TableroCeldasCuadradas;
import modelo.excepciones.ExcepcionArgumentosIncorrectos;
import modelo.excepciones.ExcepcionCoordenadaIncorrecta;
import modelo.excepciones.ExcepcionEjecucion;
import modelo.excepciones.ExcepcionPosicionFueraTablero;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author gonzalo
 *
 */
public class FactoryTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/*
	 * PRUEBAS PARA creaGeneradorFichero
	 */
	// Excepción por tablero null
	@Test
	public void testCreaGeneradorFicheroExcepcion1() {
		IGeneradorFichero gf = null;
		try {
			gf = Factory.creaGeneradorFichero(null, "txt");
		} catch (ExcepcionArgumentosIncorrectos e) {
			assertNull(gf);
		} catch (Exception e) {
			fail("Se esperaba ExcepcionArgumentosIncorrectos, pero se capturo " + e.getClass().getSimpleName());
		}

	}

	// Excepción por tipo fichero null
	@Test
	public void testCreaGeneradorFicheroExcepcion2() {
		IGeneradorFichero gf = null;
		Tablero t = null;
		try {
			t = new Tablero1D(10);
			gf = Factory.creaGeneradorFichero(t, null);
		} catch (ExcepcionArgumentosIncorrectos e) {
			assertNull(gf);
		} catch (Exception e) {
			fail("Se esperaba ExcepcionArgumentosIncorrectos, pero se capturo " + e.getClass().getSimpleName());
		}

	}

	// Excepción por tipo fichero erróneo
	@Test
	public void testCreaGeneradorFicheroExcepcion3() {
		IGeneradorFichero gf = null;
		Tablero t = null;
		try {
			t = new Tablero1D(10);
			gf = Factory.creaGeneradorFichero(t, "tst");
		} catch (ExcepcionGeneracion e) {
			assertNull(gf);
		} catch (Exception e) {
			fail("Se esperaba ExcepcionArgumentosIncorrectos, pero se capturo " + e.getClass().getSimpleName());
		}

	}

	// Excepción por tablero no imprimible en creaGeneradorFichero
	@Test
	public void testCreaGeneradorFicheroExcepcion4() {
		IGeneradorFichero gf = null;
		Tablero t = null;
		try {
			t = new TableroNoImprimible(new Coordenada2D(10, 30));
			gf = Factory.creaGeneradorFichero(t, "txt");
		} catch (ExcepcionEjecucion e) {
			assertNull(gf);
		} catch (Exception e) {
			fail("Se esperaba ExcepcionEjecucion, pero se capturo " + e.getClass().getSimpleName());
		}

	}

	@Test
	public void testCreaGeneradorFicheroTableroPlano() {
		IGeneradorFichero gf1, gf2, gf3;
		gf1 = gf2 = gf3 = null;
		Tablero t = null;
		try {
			t = new Tablero1D(10);
			gf1 = Factory.creaGeneradorFichero(t, "txt");
			t = new TableroCeldasCuadradas(5, 7);
			gf2 = Factory.creaGeneradorFichero(t, "txt");
			t = new OtroTablero2D(6, 6);
			gf3 = Factory.creaGeneradorFichero(t, "txt");

		} catch (Exception e) {
			fail("No se esperaba excepcion, pero se capturo " + e.getClass().getSimpleName());
		}
		if (!(gf1 instanceof GeneradorFicheroPlano)) {
			fail("Se esperaba un GeneradorFicheroPlano pero se obtuvo " + gf1.getClass().getSimpleName());
		}
		if (!(gf2 instanceof GeneradorFicheroPlano)) {
			fail("Se esperaba un GeneradorFicheroPlano pero se obtuvo " + gf2.getClass().getSimpleName());
		}

		if (!(gf3 instanceof GeneradorFicheroPlano)) {
			fail("Se esperaba un GeneradorFicheroPlano pero se obtuvo " + gf3.getClass().getSimpleName());
		}
	}

	@Test
	public void testCreaGeneradorFicheroGIFTablero1D() {
		IGeneradorFichero gf = null;
		Tablero t = null;
		try {
			t = new Tablero1D(10);
			gf = Factory.creaGeneradorFichero(t, "gif");
		} catch (Exception e) {
			fail("No se esperaba excepcion, pero se capturo " + e.getClass().getSimpleName());
		}

		if (!(gf instanceof GeneradorTableroCoordenada1D)) {
			fail("Se esperaba un GeneradorGIFTablero1D pero se obtuvo " + gf.getClass().getSimpleName());
		}
	}

	@Test
	public void testCreaGeneradorFicheroGifAnimadoTablero2D() {
		IGeneradorFichero gf1, gf2;
		gf1 = gf2 = null;
		Tablero t = null;
		try {
			t = new TableroCeldasCuadradas(10, 7);
			gf1 = Factory.creaGeneradorFichero(t, "gif");
			t = new OtroTablero2D(5, 7);
			gf2 = Factory.creaGeneradorFichero(t, "gif");
		} catch (Exception e) {
			fail("No se esperaba excepcion, pero se capturo " + e.getClass().getSimpleName());
		}

		if (!(gf1 instanceof GeneradorTableroCoordenada2D)) {
			fail("Se esperaba un GeneradorGifAnimadoTablero2D pero se obtuvo " + gf1.getClass().getSimpleName());
		}
		if (!(gf2 instanceof GeneradorTableroCoordenada2D)) {
			fail("Se esperaba un GeneradorGifAnimadoTablero2D pero se obtuvo " + gf2.getClass().getSimpleName());
		}
	}

	/*
	 * PRUEBAS PARA creaRegla
	 */
	@Test(expected = ExcepcionArgumentosIncorrectos.class)
	public void testCreaReglaExcepcion1() {
		Factory.creaRegla(null);
	}

	@Test(expected = ExcepcionEjecucion.class)
	public void testCreaReglaExcepcion2() {
		try {
			Factory.creaRegla(new TableroNoImprimible(new Coordenada2D(9, 9)));
		} catch (ExcepcionCoordenadaIncorrecta e) {
			fail("No debió dar ExcepcionCoordenadaIncorrecta");
		}
	}

	@Test
	public void testCreaRegla30() {
		Regla regla = null;
		try {
			regla = Factory.creaRegla(new Tablero1D(20));
			if (!(regla instanceof Regla30)) {
				fail("Se esperaba una Regla30 pero se obtuvo " + regla.getClass().getSimpleName());
			}
		} catch (Exception e) {
			fail("No se esperaba excepcion, pero se capturo " + e.getClass().getSimpleName());
		}
	}

	@Test
	public void testCreaReglaConway() {
		Regla regla = null;
		try {
			regla = Factory.creaRegla(new TableroCeldasCuadradas(20, 30));
			if (!(regla instanceof ReglaConway)) {
				fail("Se esperaba una ReglaConway pero se obtuvo " + regla.getClass().getSimpleName());
			}
			regla = Factory.creaRegla(new OtroTablero2D(20, 30));
			if (!(regla instanceof ReglaConway)) {
				fail("Se esperaba una ReglaConway pero se obtuvo " + regla.getClass().getSimpleName());
			}
		} catch (Exception e) {
			fail("No se esperaba excepcion, pero se capturo " + e.getClass().getSimpleName());
		}
	}

	/*
	 * PRUEBAS PARA creaTablero
	 */
	@Test(expected = ExcepcionArgumentosIncorrectos.class)
	public void testCreaTableroExcepcion1() {
		try {
			Factory.creaTablero(null);
		} catch (ExcepcionCoordenadaIncorrecta e) {
			fail("No debió dar ExcepcionCoordenadaIncorrecta");
		}
	}

	@Test(expected = ExcepcionEjecucion.class)
	public void testCreaTableroExcepcion2() {
		try {
			Factory.creaTablero(new OtraCoordenada());
		} catch (ExcepcionCoordenadaIncorrecta e) {
			fail("No debió dar ExcepcionCoordenadaIncorrecta");
		}
	}

	@Test
	public void testCreaTablero() {
		Tablero tablero = null;
		try {
			tablero = Factory.creaTablero(new Coordenada1D(30));
			if (!(tablero instanceof Tablero1D)) {
				fail("Se esperaba un Tablero1D pero se obtuvo " + tablero.getClass().getSimpleName());
			}

			tablero = Factory.creaTablero(new Coordenada2D(30, 40));
			if (!(tablero instanceof TableroCeldasCuadradas)) {
				fail("Se esperaba un TableroCeldasCuadradas pero se obtuvo " + tablero.getClass().getSimpleName());
			}
		} catch (Exception e) {
			fail("No se esperaba excepcion, pero se capturo " + e.getClass().getSimpleName());
		}
	}

	// CLASES AUXILIARES
	final private class OtroTablero2D extends Tablero2D {

		public OtroTablero2D(int ancho, int alto) throws ExcepcionCoordenadaIncorrecta {
			super(ancho, alto);
			// TODO Auto-generated constructor stub
		}

		@Override
		public ArrayList<Coordenada> getPosicionesVecinasCCW(Coordenada posicion) throws ExcepcionPosicionFueraTablero {
			// TODO Auto-generated method stub
			return null;
		}
	}

	final private class OtraCoordenada extends Coordenada {

		@Override
		public Coordenada suma(final Coordenada otra) throws ExcepcionCoordenadaIncorrecta {
			// TODO Auto-generated method stub
			return null;
		}

	}
}
