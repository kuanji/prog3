package modelo;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Tablero1DP3Test {

	Tablero1D tab;
	Coordenada1D dim;
	static Patron patronsimple, patronduo, patronsos;
    static Tablero tabpat;
    static String tablero;	
   
    static final String FICHTABLERO = "test/ficheros/tablero1dtest.ent";
   
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	
		tablero=cargarTableroDeFichero(FICHTABLERO);
		
        //Se crean los patrones
		Coordenada1D c = new Coordenada1D(1);
		tabpat = new Tablero1D(c.getX());
		tabpat.setCelda(new Coordenada1D(0), EstadoCelda.VIVA);
		
		patronsimple = new Patron("Simple", tabpat);
		
		c = new Coordenada1D(2);
		tabpat = new Tablero1D(c.getX());
		tabpat.setCelda(new Coordenada1D(0), EstadoCelda.VIVA);
		tabpat.setCelda(new Coordenada1D(1), EstadoCelda.VIVA);
		patronduo = new Patron("Duo", tabpat);
		
		c = new Coordenada1D(9);
		tabpat = new Tablero1D(c.getX());
		tabpat.setCelda(new Coordenada1D(0), EstadoCelda.VIVA);
		tabpat.setCelda(new Coordenada1D(1), EstadoCelda.VIVA);
		tabpat.setCelda(new Coordenada1D(2), EstadoCelda.VIVA);
		tabpat.setCelda(new Coordenada1D(3), EstadoCelda.MUERTA);
		tabpat.setCelda(new Coordenada1D(4), EstadoCelda.MUERTA);
		tabpat.setCelda(new Coordenada1D(5), EstadoCelda.MUERTA);
		tabpat.setCelda(new Coordenada1D(6), EstadoCelda.VIVA);
		tabpat.setCelda(new Coordenada1D(7), EstadoCelda.VIVA);
		tabpat.setCelda(new Coordenada1D(8), EstadoCelda.VIVA);
		patronsos = new Patron("SOS", tabpat);
		
	}



	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		dim = new Coordenada1D(9);
		tab = new Tablero1D(9); //Tablero del alumno
	}


	
	//Vecinas de las coordenadas de los extremos
	@Test
	public void testGetPosicionesVecinasCCW0() {
		ArrayList<Coordenada> vecinas = new ArrayList<Coordenada>();
		try {
			
		vecinas = tab.getPosicionesVecinasCCW(new Coordenada1D(0));
		
		assertEquals("Numero vecinas",1,vecinas.size());
		assertEquals("Vecina (1)",new Coordenada1D(1),vecinas.get(0));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail ("No se esperaba excepción, pero se capturo "+e.getClass().getSimpleName()); ;
		}
	}
	
	@Test
	public void testGetPosicionesVecinasCCW8() {
		ArrayList<Coordenada> vecinas = new ArrayList<Coordenada>();
		try {
			vecinas = tab.getPosicionesVecinasCCW(new Coordenada1D(8));
			assertEquals("Numero vecinas",1,vecinas.size());
			assertEquals("Vecina (6,0)",new Coordenada1D(7),vecinas.get(0));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail ("No se esperaba excepción, pero se capturo "+e.getClass().getSimpleName()); ;
		}
	}
	
	// Coordenadas interiores
	@Test
	public void testGetPosicionesVecinasCCWInteriores() {
		ArrayList<Coordenada> vecinas = new ArrayList<Coordenada>();
		try {
		  for (int i=1; i<8; i++) {
			vecinas = tab.getPosicionesVecinasCCW(new Coordenada1D(i));
			assertEquals("Numero vecinas",2,vecinas.size());
			assertEquals("Vecina ("+(i-1)+")",new Coordenada1D(i-1),vecinas.get(0));
			assertEquals("Vecina ("+(i+1)+")",new Coordenada1D(i+1), vecinas.get(1));
		  }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail ("No se esperaba excepción, pero se capturo "+e.getClass().getSimpleName()); ;
		}
	}
	
	@Test
	public void testToString() {
		try {
		
		tab.cargaPatron(patronsimple,new Coordenada1D(1));
		tab.cargaPatron(patronduo, new Coordenada1D(4));
		tab.cargaPatron(patronduo, new Coordenada1D(7));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail ("No se esperaba excepción, pero se produjo "+e.getClass().getSimpleName());
		}
		//Para que el alumno compare los ficheros si da error.
		PrintStream ps = abrirFichero("test/ficheros/tablero1dtest.sal"); 
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
