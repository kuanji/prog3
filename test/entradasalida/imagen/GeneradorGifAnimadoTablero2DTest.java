/**
 * 
 */
package entradasalida.imagen;

import static org.junit.Assert.*;

import java.io.File;

import modelo.Juego;
import modelo.Tablero;
import modelo.d2.Coordenada2D;
import modelo.d2.ReglaConway;
import modelo.d2.TableroCeldasCuadradas;
import modelo.excepciones.ExcepcionArgumentosIncorrectos;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import entradasalida.IGeneradorFichero;
import modelo.MetodosAuxiliares;
import entradasalida.ParserTableros;
import entradasalida.excepciones.ExcepcionGeneracion;
import entradasalida.gif.GeneradorTableroCoordenada2D;

/**
 * @author gonzalo
 *
 */
public class GeneradorGifAnimadoTablero2DTest extends MetodosAuxiliares {

	/**
	 * Test method for
	 * {@link entradasalida.gif.GeneradorTableroCoordenada2D#generaFichero(java.io.File, modelo.Juego, int)}.
	 */

	static Tablero t;
	static IGeneradorFichero generador;
	Juego juego;
	Tablero tablero;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		t = new TableroCeldasCuadradas(20, 30);
		generador = new GeneradorTableroCoordenada2D();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for
	 * {@link entradasalida.txt.GeneradorFicheroPlano#generaFichero(java.io.File, modelo.Juego, int)}.
	 * 
	 * @throws Exception
	 */
	// El fichero es null
	@Test(expected = ExcepcionArgumentosIncorrectos.class)
	public void testGeneraFicheroExcepcion1() throws Exception {
		generador.generaFichero(null, new Juego(t, new ReglaConway()), 1);
	}

	// El juego es null
	@Test(expected = ExcepcionArgumentosIncorrectos.class)
	public void testGeneraFicheroExcepcion2() throws Exception {
		generador.generaFichero(new File("fff.gif"), null, 1);
	}

	// El número de iteraciones es 0
	@Test(expected = ExcepcionGeneracion.class)
	public void testGeneraFicheroExcepcion3() throws Exception {
		IGeneradorFichero generador = new GeneradorTableroCoordenada2D();
		generador.generaFichero(new File("fff.gif"), new Juego(t, new ReglaConway()), 0);
	}

	// El número de iteraciones es negativo
	@Test(expected = ExcepcionGeneracion.class)
	public void testGeneraFicheroExcepcion4() throws Exception {

		generador.generaFichero(new File("fff.gif"), new Juego(t, new ReglaConway()), -3);
	}

	@Test
	public void testGeneraFicheroGifAnimadoDelMain() {
		String fin = new String("test/ficheros/p4-2d.gif");
		String fout = new String("test/ficheros/p4-2d_alu.gif");
		try {
			run(new Coordenada2D(10, 5), new Coordenada2D(0, 0), " * \n  *\n***", fout, 5);
			if (!new File(fout).exists())
				fail("Error, el fichero de salida " + fout + " no se creó.");

			boolean comp = ComparaFicheros(fin, fout);
			assertTrue("Los ficheros " + fin + " y " + fout + " no son iguales ", comp);

		} catch (Exception e) {
			fail("No se esperaba excepcion, pero se capturo " + e.getClass().getSimpleName());
		}
	}

	@Test
	public void testGeneraFicheroGifAnimado1() {
		String fin = new String("test/ficheros/p4-2d_test1.gif");
		String fout = new String("test/ficheros/p4-2d_test1_alu.gif");
		try {
			tablero = ParserTableros.leeTablero(cargaTablero("test/ficheros/tablero2Dtest1.ent"));

			juego = new Juego(tablero, new ReglaConway());

			generador.generaFichero(new File("test/ficheros/p4-2d_test1_alu.gif"), juego, 10);
			if (!new File(fout).exists())
				fail("Error, el fichero de salida " + fout + " no se creó.");

			boolean comp = ComparaFicheros(fin, fout);
			assertTrue("Los ficheros " + fin + " y " + fout + " no son iguales ", comp);

		} catch (Exception e) {
			fail("No se esperaba excepcion, pero se capturo " + e.getClass().getSimpleName());
		}

	}

	@Test
	public void testGeneraFicheroGifAnimado2() {
		String fin = new String("test/ficheros/p4-2d_test2.gif");
		String fout = new String("test/ficheros/p4-2d_test2_alu.gif");
		try {
			tablero = ParserTableros.leeTablero(cargaTablero("test/ficheros/tablero2Dtest2.ent"));

			juego = new Juego(tablero, new ReglaConway());
			generador.generaFichero(new File(fout), juego, 10);
			if (!new File(fout).exists())
				fail("Error, el fichero de salida " + fout + " no se creó.");

			boolean comp = ComparaFicheros(fin, fout);
			assertTrue("Los ficheros " + fin + " y " + fout + " no son iguales ", comp);

		} catch (Exception e) {
			fail("No se esperaba excepcion, pero se capturo " + e.getClass().getSimpleName());
		}

	}

	@Test
	public void testGeneraFicheroGifAnimado3() {
		String fin = new String("test/ficheros/p4-2d_test3.gif");
		String fout = new String("test/ficheros/p4-2d_test3_alu.gif");
		try {
			tablero = ParserTableros.leeTablero(cargaTablero("test/ficheros/tablero2Dtest3.ent"));

			juego = new Juego(tablero, new ReglaConway());
			generador.generaFichero(new File(fout), juego, 10);
			if (!new File(fout).exists())
				fail("Error, el fichero de salida " + fout + " no se creó.");

			boolean comp = ComparaFicheros(fin, fout);
			assertTrue("Los ficheros " + fin + " y " + fout + " no son iguales ", comp);

		} catch (Exception e) {
			fail("No se esperaba excepcion, pero se capturo " + e.getClass().getSimpleName());
		}

	}

}
