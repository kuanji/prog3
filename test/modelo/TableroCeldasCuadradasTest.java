/**
 * 
 */
package modelo;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Scanner;

/**
 * @author alcala
 *
 */
public class TableroCeldasCuadradasTest {


	Tablero tab;
	Coordenada2D dim;
	static Patron patronbloque, patronbarco, patronsapo;
    static Tablero tabpat;
    static String tablero;	
    //static final String FICHERRORES = "test/ficheros/salidaerrores.txt";
    static final String FICHTABLERO = "test/ficheros/tablero2dtest.ent";
   // static PrintStream salstd, errstd; //salida y error standard
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	
		tablero=cargarTableroDeFichero(FICHTABLERO);
		
        //Se crean los patrones
		Coordenada2D c = new Coordenada2D(2,2);
		tabpat = new TableroCeldasCuadradas(c.getX(),c.getY());
		tabpat.setCelda(new Coordenada2D(0,0), EstadoCelda.VIVA);
		tabpat.setCelda(new Coordenada2D(1,0), EstadoCelda.VIVA);

		tabpat.setCelda(new Coordenada2D(0,1), EstadoCelda.VIVA);
		tabpat.setCelda(new Coordenada2D(1,1), EstadoCelda.VIVA);
		patronbloque = new Patron("Bloque", tabpat);
		
		c = new Coordenada2D(3,3);
		tabpat = new TableroCeldasCuadradas(c.getX(), c.getY());
		tabpat.setCelda(new Coordenada2D(0,0), EstadoCelda.VIVA);
		tabpat.setCelda(new Coordenada2D(1,0), EstadoCelda.VIVA);
		tabpat.setCelda(new Coordenada2D(2,0), EstadoCelda.MUERTA);

		tabpat.setCelda(new Coordenada2D(0,1), EstadoCelda.VIVA);
		tabpat.setCelda(new Coordenada2D(1,1), EstadoCelda.MUERTA);
		tabpat.setCelda(new Coordenada2D(2,1), EstadoCelda.VIVA);

		tabpat.setCelda(new Coordenada2D(0,2), EstadoCelda.MUERTA);
		tabpat.setCelda(new Coordenada2D(1,2), EstadoCelda.VIVA);
		tabpat.setCelda(new Coordenada2D(2,2), EstadoCelda.MUERTA);
		patronbarco = new Patron("Barco", tabpat);
		
		c = new Coordenada2D(4,2);
		tabpat = new TableroCeldasCuadradas(c.getX(),c.getY());
		tabpat.setCelda(new Coordenada2D(0,0), EstadoCelda.MUERTA);
		tabpat.setCelda(new Coordenada2D(1,0), EstadoCelda.VIVA);
		tabpat.setCelda(new Coordenada2D(2,0), EstadoCelda.VIVA);
		tabpat.setCelda(new Coordenada2D(3,0), EstadoCelda.VIVA);

		tabpat.setCelda(new Coordenada2D(0,1), EstadoCelda.VIVA);
		tabpat.setCelda(new Coordenada2D(1,1), EstadoCelda.VIVA);
		tabpat.setCelda(new Coordenada2D(2,1), EstadoCelda.VIVA);
		tabpat.setCelda(new Coordenada2D(3,1), EstadoCelda.MUERTA);
		patronsapo = new Patron("Sapo", tabpat);
		
	}



	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		dim = new Coordenada2D(8,5);
		tab = new TableroCeldasCuadradas(dim.getX(),dim.getY()); //Tablero del alumno
	}


	
	
	//Vecinas de las coordenadas de las esquinas
	@Test
	public void testGetPosicionesVecinasCCW00() {
		ArrayList<Coordenada> vecinas = new ArrayList<Coordenada>();
		try {
			vecinas = tab.getPosicionesVecinasCCW(new Coordenada2D(0,0));
		
		assertEquals("Numero vecinas",3,vecinas.size());
		assertEquals("Vecina (0,1)",new Coordenada2D(0,1),vecinas.get(0));
		assertEquals("Vecina (1,1)",new Coordenada2D(1,1), vecinas.get(1));
		assertEquals("Vecina (1,0)",new Coordenada2D(1,0), vecinas.get(2));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail ("No se esperaba excepción, pero se capturo "+e.getClass().getSimpleName()); ;
		}
	}
	@Test
	public void testGetPosicionesVecinasCCW70() {
		ArrayList<Coordenada> vecinas = new ArrayList<Coordenada>();
		try {
			vecinas = tab.getPosicionesVecinasCCW(new Coordenada2D(7,0));
			assertEquals("Numero vecinas",3,vecinas.size());
			assertEquals("Vecina (6,0)",new Coordenada2D(6,0),vecinas.get(0));
			assertEquals("Vecina (6,1)",new Coordenada2D(6,1), vecinas.get(1));
			assertEquals("Vecina (7,1)",new Coordenada2D(7,1), vecinas.get(2));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail ("No se esperaba excepción, pero se capturo "+e.getClass().getSimpleName()); ;
		}
	}
	
	@Test
	public void testGetPosicionesVecinasCCW04() {
		ArrayList<Coordenada> vecinas = new ArrayList<Coordenada>();
		try {
			vecinas = tab.getPosicionesVecinasCCW(new Coordenada2D(0,4));
			assertEquals("Numero vecinas",3,vecinas.size());
			assertEquals("Vecina (1,4)",new Coordenada2D(1,4),vecinas.get(0));
			assertEquals("Vecina (1,3)",new Coordenada2D(1,3), vecinas.get(1));
			assertEquals("Vecina (0,3)",new Coordenada2D(0,3), vecinas.get(2));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail ("No se esperaba excepción, pero se capturo "+e.getClass().getSimpleName()); ;
		}
	}
	
	@Test
	public void testGetPosicionesVecinasCCW74() {
		ArrayList<Coordenada> vecinas = new ArrayList<Coordenada>();
		try {
			vecinas = tab.getPosicionesVecinasCCW(new Coordenada2D(7,4));
			assertEquals("Numero vecinas",3,vecinas.size());
			assertEquals("Vecina (6,3)",new Coordenada2D(6,3),vecinas.get(0));
			assertEquals("Vecina (6,4)",new Coordenada2D(6,4), vecinas.get(1));
			assertEquals("Vecina (7,3)",new Coordenada2D(7,3), vecinas.get(2));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail ("No se esperaba excepción, pero se capturo "+e.getClass().getSimpleName()); ;
		}
	}
	
	//Vecinas de coordenadas laterales
	@Test
	public void testGetPosicionesVecinasCCW40() {
		ArrayList<Coordenada> vecinas = new ArrayList<Coordenada>();
		try {
			vecinas = tab.getPosicionesVecinasCCW(new Coordenada2D(4,0));
			assertEquals("Numero vecinas",5,vecinas.size());
			assertEquals("Vecina (3,0)",new Coordenada2D(3,0),vecinas.get(0));
			assertEquals("Vecina (3,1)",new Coordenada2D(3,1), vecinas.get(1));
			assertEquals("Vecina (4,1)",new Coordenada2D(4,1), vecinas.get(2));
			assertEquals("Vecina (5,1)",new Coordenada2D(5,1), vecinas.get(3));
			assertEquals("Vecina (5,0)",new Coordenada2D(5,0), vecinas.get(4));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail ("No se esperaba excepción, pero se capturo "+e.getClass().getSimpleName()); ;
		}
	}
	
	@Test
	public void testGetPosicionesVecinasCCW44() {
		ArrayList<Coordenada> vecinas = new ArrayList<Coordenada>();
		try {
		vecinas = tab.getPosicionesVecinasCCW(new Coordenada2D(4,4));
			assertEquals("Numero vecinas",5,vecinas.size());
			assertEquals("Vecina (3,3)",new Coordenada2D(3,3),vecinas.get(0));
			assertEquals("Vecina (3,4)",new Coordenada2D(3,4), vecinas.get(1));
			assertEquals("Vecina (5,4)",new Coordenada2D(5,4), vecinas.get(2));
			assertEquals("Vecina (5,3)",new Coordenada2D(5,3), vecinas.get(3));
			assertEquals("Vecina (4,3)",new Coordenada2D(4,3), vecinas.get(4));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail ("No se esperaba excepción, pero se capturo "+e.getClass().getSimpleName()); ;
		}
	}
	
	@Test
	public void testGetPosicionesVecinasCCW02() {
		ArrayList<Coordenada> vecinas = new ArrayList<Coordenada>();
		try {
			vecinas = tab.getPosicionesVecinasCCW(new Coordenada2D(4,4));
			assertEquals("Numero vecinas",5,vecinas.size());
			assertEquals("Vecina (0,1)",new Coordenada2D(3,3),vecinas.get(0));
			assertEquals("Vecina (0,3)",new Coordenada2D(3,4), vecinas.get(1));
			assertEquals("Vecina (1,3)",new Coordenada2D(5,4), vecinas.get(2));
			assertEquals("Vecina (1,2)",new Coordenada2D(5,3), vecinas.get(3));
			assertEquals("Vecina (1,1)",new Coordenada2D(4,3), vecinas.get(4));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail ("No se esperaba excepción, pero se capturo "+e.getClass().getSimpleName()); ;
		}
	}
	
	@Test
	public void testGetPosicionesVecinasCCW72() {
		ArrayList<Coordenada> vecinas = new ArrayList<Coordenada>();
		try {
			vecinas = tab.getPosicionesVecinasCCW(new Coordenada2D(4,4));
			assertEquals("Numero vecinas",5,vecinas.size());
			assertEquals("Vecina (6,1)",new Coordenada2D(3,3),vecinas.get(0));
			assertEquals("Vecina (6,2)",new Coordenada2D(3,4), vecinas.get(1));
			assertEquals("Vecina (6,3)",new Coordenada2D(5,4), vecinas.get(2));
			assertEquals("Vecina (7,3)",new Coordenada2D(5,3), vecinas.get(3));
			assertEquals("Vecina (7,1)",new Coordenada2D(4,3), vecinas.get(4));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail ("No se esperaba excepción, pero se capturo "+e.getClass().getSimpleName()); ;
		}
	}
	
	//Vecinas de una coordenada central
	@Test
	public void testGetPosicionesVecinasCCW32() {
		ArrayList<Coordenada> vecinas = new ArrayList<Coordenada>();
		try {
			vecinas = tab.getPosicionesVecinasCCW(new Coordenada2D(3,2));
			assertEquals("Numero vecinas",8,vecinas.size());
			assertEquals("Vecina (2,1)",new Coordenada2D(2,1),vecinas.get(0));
			assertEquals("Vecina (2,2)",new Coordenada2D(2,2), vecinas.get(1));
			assertEquals("Vecina (2,3)",new Coordenada2D(2,3), vecinas.get(2));
			assertEquals("Vecina (3,3)",new Coordenada2D(3,3), vecinas.get(3));
			assertEquals("Vecina (4,3)",new Coordenada2D(4,3), vecinas.get(4));
			assertEquals("Vecina (4,2)",new Coordenada2D(4,2), vecinas.get(5));
			assertEquals("Vecina (4,1)",new Coordenada2D(4,1), vecinas.get(6));
			assertEquals("Vecina (3,1)",new Coordenada2D(3,1), vecinas.get(7));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail ("No se esperaba excepción, pero se capturo "+e.getClass().getSimpleName());
		}
	}
	
	
	@Test
	public void testToString() {
		try {
			tab.cargaPatron(patronbloque,new Coordenada2D(1,1));
		
		
		tab.cargaPatron(patronbarco, new Coordenada2D(5,2));
		tab.cargaPatron(patronsapo, new Coordenada2D(0,3));
		tab.cargaPatron(patronbloque, new Coordenada2D(6,0));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail ("No se esperaba excepción, pero se produjo "+e.getClass().getSimpleName());
		}
		//Para que el alumno compare los ficheros si da error.
		PrintStream ps = abrirFichero("test/ficheros/tablero2dtest.sal"); 
		ps.print(tab.toString());
		
		assertEquals("Compara salida tableros",tablero,tab.toString());
		ps.close();
		}
	
/*------------------- FIN TESTS ---------------------------------*/
	
	
	//FUNCIONES AUXILIARES
	
	//Abre un fichero para escribir las tablas que genera el alumno.
	private PrintStream abrirFichero (String fichero) {
		PrintStream s=null;
		try {
			s = new PrintStream(fichero);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		return s;
	}
	
	/* Carga el tablero resultado desde un fichero */
	private static String cargarTableroDeFichero(String fichero)
	{
		Scanner s;
		StringBuilder sb = new StringBuilder();
		try {
			
			s = new Scanner(new File(fichero));
			while (s.hasNext()) {
				sb.append(s.nextLine());
				sb.append("\n");
			}
			s.close();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		return (sb.toString());
	}
}
		
		
	

	

