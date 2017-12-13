/**
 * 
 */
package entradasalida.textoplano;

import static org.junit.Assert.*;

import java.io.File;
import modelo.Coordenada1D;
import modelo.Coordenada2D;
import modelo.Juego;
import modelo.Regla;
import modelo.Regla30;
import modelo.ReglaConway;
import modelo.Tablero;
import modelo.Tablero1D;
import modelo.excepciones.ExcepcionArgumentosIncorrectos;
import modelo.TableroNoImprimible;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import entradasalida.Factory;
import entradasalida.IGeneradorFichero;
import modelo.MetodosAuxiliares;
import entradasalida.ParserTableros;
import entradasalida.excepciones.ExcepcionGeneracion;
import entradasalida.excepciones.ExcepcionLectura;

/**
 * @author gonzalo
 *
 */
public class GeneradorFicheroPlanoTest extends MetodosAuxiliares {
	static Tablero t;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		t = new Tablero1D(20);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for
	 * {@link entradasalida.textoplano.GeneradorFicheroPlano#generaFichero(java.io.File, modelo.Juego, int)}.
	 * 
	 * @throws Exception
	 */
	// El fichero es null
	@Test(expected = ExcepcionArgumentosIncorrectos.class)
	public void testGeneraFicheroExcepcion1() throws Exception {
		IGeneradorFichero generador = new GeneradorFicheroPlano();
		generador.generaFichero(null, new Juego(t, new Regla30()), 1);
	}

	// El juego es null
	@Test(expected = ExcepcionArgumentosIncorrectos.class)
	public void testGeneraFicheroExcepcion2() throws Exception {
		IGeneradorFichero generador = new GeneradorFicheroPlano();
		generador.generaFichero(new File("fff.txt"), null, 1);
	}

	// El número de iteraciones es 0
	@Test(expected = ExcepcionGeneracion.class)
	public void testGeneraFicheroExcepcion3() throws Exception {
		IGeneradorFichero generador = new GeneradorFicheroPlano();
		generador.generaFichero(new File("fff.txt"), new Juego(t, new Regla30()), 0);
	}

	// El número de iteraciones es negativo
	@Test(expected = ExcepcionGeneracion.class)
	public void testGeneraFicheroExcepcion4() throws Exception {
		IGeneradorFichero generador = new GeneradorFicheroPlano();
		generador.generaFichero(new File("fff.txt"), new Juego(t, new Regla30()), -3);
	}

	// El tablero del juego no es imprimible
	@Test(expected = ExcepcionGeneracion.class)
	public void testGeneraFicheroExcepcion5() throws Exception {
		IGeneradorFichero generador = new GeneradorFicheroPlano();
		Tablero t = new TableroNoImprimible(new Coordenada2D(2, 2));
		generador.generaFichero(new File("fff.txt"), new Juego(t, new ReglaConway()), 3);
	}

	// No existe el directorio donde crear el fichero
	@Test(expected = ExcepcionGeneracion.class)
	public void testGeneraFicheroExcepcion6() throws Exception {
		IGeneradorFichero generador = new GeneradorFicheroPlano();
		generador.generaFichero(new File("nohaydirectorio/nosepuedecrear.txt"), new Juego(t, new Regla30()), 3);
	}

	@Test
	public void testGeneraFicheroDelMain4() {

		try {
			run(new Coordenada1D(80), new Coordenada1D(40), "*", "test/ficheros/p4-1d_alu.txt", 30);
			boolean comp = ComparaFicheros("test/ficheros/p4-1d.txt", "test/ficheros/p4-1d_alu.txt");
			assertTrue("Los ficheros p4-1d.txt y p4-1d_alu.txt son distintos", comp);

		} catch (Exception e) {
			fail("No se esperaba excepcion, pero se capturo " + e.getClass().getSimpleName());
		}

	}

	@Test
	public void testGeneraFicheroDelMain5() {

		try {
			run(new Coordenada2D(10, 5), new Coordenada2D(0, 0), " * \n  *\n***", "test/ficheros/p4-2d_alu.txt", 5);
			assertTrue(ComparaFicheros("test/ficheros/p4-2d.txt", "test/ficheros/p4-2d_alu.txt"));

		} catch (Exception e) {
			fail("No se esperaba excepcion, pero se capturo " + e.getClass().getSimpleName());
		}

	}

	@Test
	public void testGeneraFichero6() {

		try {
			Tablero tablero = ParserTableros.leeTablero(cargaTablero("test/ficheros/tablero2Dtest2.ent"));
			Regla regla = Factory.creaRegla(tablero);
			Juego juego = new Juego(tablero, regla);
			IGeneradorFichero generador = Factory.creaGeneradorFichero(tablero, "txt");
			generador.generaFichero(new File("test/ficheros/p4-2d_test2_alu.txt"), juego, 5);
			boolean comp = ComparaFicheros("test/ficheros/p4-2d_test2.txt", "test/ficheros/p4-2d_test2_alu.txt");
			assertTrue("Los ficheros p4-2d_test2.txt y p4-2d_test2_alu.txt no son iguales", comp);
		} catch (ExcepcionLectura e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e) {
			fail("No se esperaba excepcion, pero se capturo " + e.getClass().getSimpleName());
		}
	}

	@Test
	public void testGeneraFichero7() {

		try {
			Tablero tablero = ParserTableros.leeTablero(cargaTablero("test/ficheros/tablero2Dtest3.ent"));
			Regla regla = Factory.creaRegla(tablero);
			Juego juego = new Juego(tablero, regla);
			IGeneradorFichero generador = Factory.creaGeneradorFichero(tablero, "txt");
			generador.generaFichero(new File("test/ficheros/p4-2d_test3_alu.txt"), juego, 7);
			boolean comp = ComparaFicheros("test/ficheros/p4-2d_test3.txt", "test/ficheros/p4-2d_test3_alu.txt");
			assertTrue("Los ficheros p4-2d_test3.txt y p4-2d_test3_alu.txt no son iguales", comp);
		} catch (Exception e) {
			fail("No se esperaba excepcion, pero se capturo " + e.getClass().getSimpleName());
		}
	}
}
