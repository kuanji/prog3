/**
 * 
 */
package modelo;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.Set;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import modelo.excepciones.ExcepcionCoordenadaIncorrecta;
import modelo.excepciones.ExcepcionPosicionFueraTablero;

/**
 * @author gonzalo
 *
 */
public class TableroP2Test {

	Tablero tab;
	Coordenada2D dim;
	static Patron patronbloque, patronbarco, patronsapo;
    static Tablero tabpat;
    static String tablero;	
    static final String FICHERRORES = "test/ficheros/salidaerrores.txt";
    static final String FICHTABLERO = "test/ficheros/tablerotest.ent";
    static PrintStream salstd, errstd; //salida y error standard
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		tablero=cargarTableroDeFichero(FICHTABLERO);
		
		salstd = new PrintStream(System.out);
		errstd = new PrintStream(System.err);
		
        //Se crean los patrones
		tabpat = new TableroCeldasCuadradas(2,2);
		tabpat.setCelda(new Coordenada2D(0,0), EstadoCelda.VIVA);
		tabpat.setCelda(new Coordenada2D(1,0), EstadoCelda.VIVA);

		tabpat.setCelda(new Coordenada2D(0,1), EstadoCelda.VIVA);
		tabpat.setCelda(new Coordenada2D(1,1), EstadoCelda.VIVA);
		patronbloque = new Patron("Bloque", tabpat);
		
				
		tabpat = new TableroCeldasCuadradas(3,3);
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
		
		tabpat = new TableroCeldasCuadradas(4,2);
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
		tab = new TableroCeldasCuadradas(dim.getX(), dim.getY()); //Tablero del alumno
	}
	
	/**
	 * Test method for {@link modelo.Tablero#Tablero(modelo.Coordenada)}.
	 */
	@Test
	public void testTableroComposicion() {
		assertNotSame("Composicion Tabla-Coordenada",dim,tab.getDimensiones());
	}
	
	@Test
	public void testTablero() throws ExcepcionPosicionFueraTablero, ExcepcionCoordenadaIncorrecta {
				
		EstadoCelda e;
		for (int i=0; i<dim.getX();i++)
			for (int j=0; j<dim.getY(); j++) {
				e = tab.getCelda(new Coordenada2D(i,j));
				assertNotNull("Coordenada valida("+i+","+j+")",e);
				assertEquals("Celda Muerta ("+i+","+j+")",EstadoCelda.MUERTA,e);
			}
		
	}

	/**
	 * Test method for {@link modelo.Tablero#getDimensiones()}.
	 */
	@Test
	public void testGetDimensiones() {
		
		assertEquals("Comprobación dimensiones", dim,tab.getDimensiones());
	}

	/**
	 * Test method for {@link modelo.Tablero#getPosiciones()}.
	 * @throws ExcepcionCoordenadaIncorrecta 
	 */
	@Test
	public void testGetPosiciones() throws ExcepcionCoordenadaIncorrecta {
		Set<Coordenada> sc = (Set<Coordenada>)tab.getPosiciones();
		assertEquals("Total posiciones",40,sc.size());
		for (int i=0; i<dim.getX();i++)
			for (int j=0; j<dim.getY(); j++) {
				assertTrue ("Coordenada en set",sc.contains((new Coordenada2D(i,j))));
			}
	}

	/**
	 * Test method for {@link modelo.Tablero#getCelda(modelo.Coordenada)}.
	 * @throws ExcepcionCoordenadaIncorrecta 
	 * @throws ExcepcionPosicionFueraTablero 
	 */
	@Test
	public void testGetCeldasNoExisten() throws ExcepcionPosicionFueraTablero, ExcepcionCoordenadaIncorrecta {
		
			PrintStream ps = redireccionarSalidasStandardAFichero(FICHERRORES);
		
		    if (ps==null) fail("Error de apertura del fichero "+FICHERRORES);
		    assertNull ("No existe celda (8,5)",tab.getCelda(new Coordenada2D(8,5)));
		    assertNull ("No existe celda (8,0)",tab.getCelda(new Coordenada2D(8,0)));
		    assertNull ("No existe celda (-1,4)",tab.getCelda(new Coordenada2D(-1,4)));
		    assertNull ("No existe celda (7,-1)",tab.getCelda(new Coordenada2D(7,-1)));
		    
		    ps.close();
		    //Restauramos salida y error standard
		    restaurarSalidasStandard();
		    
		    //Comprobación mensajes de error
		try {
		    Scanner sc = new Scanner(new File(FICHERRORES));
		    comprobarMensajeDeError(sc,"(8,5)",true);
		    comprobarMensajeDeError(sc,"(8,0)",true);
		    comprobarMensajeDeError(sc,"(-1,4)",true);
		    comprobarMensajeDeError(sc,"(7,-1)",true);
		    sc.close();
		} catch (FileNotFoundException e) {
			fail("Error de apertura del fichero "+FICHERRORES);
			e.printStackTrace();
		}
		}
		
		@Test
		public void testGetCeldasExisten() throws ExcepcionPosicionFueraTablero, ExcepcionCoordenadaIncorrecta {
			PrintStream ps = redireccionarSalidasStandardAFichero(FICHERRORES);
		    assertNotNull ("Existe celda (7,4)",tab.getCelda(new Coordenada2D(7,4)));
		    assertNotNull ("Existe celda (0,0)",tab.getCelda(new Coordenada2D(0,0)));
		    assertNotNull ("Existe celda (7,0)",tab.getCelda(new Coordenada2D(7,0)));
		    assertNotNull ("Existe celda (0,4)",tab.getCelda(new Coordenada2D(0,4)));
		    ps.close();
		    restaurarSalidasStandard();
		    //Comprobación que no se generó mensajes de error
		    
			try {
				Scanner sc = new Scanner(new File(FICHERRORES));
				if (sc.hasNext()) fail("Se generó mensaje en salida");
			    sc.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		}
	
	
	/**
	 * Test method for {@link modelo.Tablero#setCelda(modelo.Coordenada, modelo.EstadoCelda)}.
	 * @throws ExcepcionCoordenadaIncorrecta 
	 * @throws ExcepcionPosicionFueraTablero 
	 */
	@Test
	public void testSetCelda() throws ExcepcionPosicionFueraTablero, ExcepcionCoordenadaIncorrecta {
		PrintStream ps = redireccionarSalidasStandardAFichero(FICHERRORES);
		
		tab.setCelda(new Coordenada2D(7,5),EstadoCelda.VIVA); //coordenada incorrecta
		tab.setCelda(new Coordenada2D(-2,3),EstadoCelda.MUERTA); //coordenada incorrecta
		
		for (int i=0; i<9; i++) {
			tab.setCelda(new Coordenada2D(i,0), EstadoCelda.VIVA);
		}
		for (int i=0; i<8; i++) {
			assertEquals("Celda ("+i+",0) puesta a VIVA", EstadoCelda.VIVA, tab.getCelda(new Coordenada2D(i,0)));
		}
		
		ps.close();
		restaurarSalidasStandard();
		//Comprobamos mensajes de error
		try {
			Scanner sc = new Scanner(new File (FICHERRORES));
			comprobarMensajeDeError(sc,"(7,5)",true);
			comprobarMensajeDeError(sc,"(-2,3)",true);
			comprobarMensajeDeError(sc,"(8,0)",true);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			fail ("No se pudo abrir como lectura el fichero "+FICHERRORES);
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * Test method for {@link modelo.Tablero#getPosicionesVecinasCCW(modelo.Coordenada)}.
	 * @throws ExcepcionCoordenadaIncorrecta 
	 * @throws ExcepcionPosicionFueraTablero 
	 */
	@Test
	public void testGetPosicionesVecinasCCWErronea() throws ExcepcionPosicionFueraTablero, ExcepcionCoordenadaIncorrecta {
		ArrayList<Coordenada> vecinas = null;
		vecinas = tab.getPosicionesVecinasCCW(new Coordenada2D(-1,0));
		assertTrue("vecinas = null || vecinas.size()==0",vecinas==null || vecinas.size()==0);
		vecinas = tab.getPosicionesVecinasCCW(new Coordenada2D(0,-1));
		assertTrue("vecinas = null || vecinas.size()==0",vecinas==null || vecinas.size()==0);
		vecinas = tab.getPosicionesVecinasCCW(new Coordenada2D(8,1));
		assertTrue("vecinas = null || vecinas.size()==0",vecinas==null || vecinas.size()==0);
		vecinas = tab.getPosicionesVecinasCCW(new Coordenada2D(5,5));
		assertTrue("vecinas = null || vecinas.size()==0",vecinas==null || vecinas.size()==0);
	}
	
	//Vecinas de las coordenadas de las esquinas
	@Test
	public void testGetPosicionesVecinasCCW00() throws ExcepcionPosicionFueraTablero, ExcepcionCoordenadaIncorrecta {
		ArrayList<Coordenada> vecinas = new ArrayList<Coordenada>();
		vecinas = tab.getPosicionesVecinasCCW(new Coordenada2D(0,0));
		assertEquals("Numero vecinas",3,vecinas.size());
		assertEquals("Vecina (0,1)",new Coordenada2D(0,1),vecinas.get(0));
		assertEquals("Vecina (1,1)",new Coordenada2D(1,1), vecinas.get(1));
		assertEquals("Vecina (1,0)",new Coordenada2D(1,0), vecinas.get(2));

	}

	@Test
	public void testGetPosicionesVecinasCCW70() throws ExcepcionPosicionFueraTablero, ExcepcionCoordenadaIncorrecta {
		ArrayList<Coordenada> vecinas = new ArrayList<Coordenada>();
		vecinas = tab.getPosicionesVecinasCCW(new Coordenada2D(7,0));
		assertEquals("Numero vecinas",3,vecinas.size());
		assertEquals("Vecina (6,0)",new Coordenada2D(6,0),vecinas.get(0));
		assertEquals("Vecina (6,1)",new Coordenada2D(6,1), vecinas.get(1));
		assertEquals("Vecina (7,1)",new Coordenada2D(7,1), vecinas.get(2));
	}
	
	@Test
	public void testGetPosicionesVecinasCCW04() throws ExcepcionPosicionFueraTablero, ExcepcionCoordenadaIncorrecta {
		ArrayList<Coordenada> vecinas = new ArrayList<Coordenada>();
		vecinas = tab.getPosicionesVecinasCCW(new Coordenada2D(0,4));
		assertEquals("Numero vecinas",3,vecinas.size());
		assertEquals("Vecina (1,4)",new Coordenada2D(1,4),vecinas.get(0));
		assertEquals("Vecina (1,3)",new Coordenada2D(1,3), vecinas.get(1));
		assertEquals("Vecina (0,3)",new Coordenada2D(0,3), vecinas.get(2));
	}
	
	@Test
	public void testGetPosicionesVecinasCCW74() throws ExcepcionPosicionFueraTablero, ExcepcionCoordenadaIncorrecta {
		ArrayList<Coordenada> vecinas = new ArrayList<Coordenada>();
		vecinas = tab.getPosicionesVecinasCCW(new Coordenada2D(7,4));
		assertEquals("Numero vecinas",3,vecinas.size());
		assertEquals("Vecina (6,3)",new Coordenada2D(6,3),vecinas.get(0));
		assertEquals("Vecina (6,4)",new Coordenada2D(6,4), vecinas.get(1));
		assertEquals("Vecina (7,3)",new Coordenada2D(7,3), vecinas.get(2));
	}
	
	//Vecinas de coordenadas laterales
	@Test
	public void testGetPosicionesVecinasCCW40() throws ExcepcionPosicionFueraTablero, ExcepcionCoordenadaIncorrecta {
		ArrayList<Coordenada> vecinas = new ArrayList<Coordenada>();
		vecinas = tab.getPosicionesVecinasCCW(new Coordenada2D(4,0));
		assertEquals("Numero vecinas",5,vecinas.size());
		assertEquals("Vecina (3,0)",new Coordenada2D(3,0),vecinas.get(0));
		assertEquals("Vecina (3,1)",new Coordenada2D(3,1), vecinas.get(1));
		assertEquals("Vecina (4,1)",new Coordenada2D(4,1), vecinas.get(2));
		assertEquals("Vecina (5,1)",new Coordenada2D(5,1), vecinas.get(3));
		assertEquals("Vecina (5,0)",new Coordenada2D(5,0), vecinas.get(4));
	}
	
	@Test
	public void testGetPosicionesVecinasCCW44() throws ExcepcionPosicionFueraTablero, ExcepcionCoordenadaIncorrecta {
		ArrayList<Coordenada> vecinas = new ArrayList<Coordenada>();
		vecinas = tab.getPosicionesVecinasCCW(new Coordenada2D(4,4));
		assertEquals("Numero vecinas",5,vecinas.size());
		assertEquals("Vecina (3,3)",new Coordenada2D(3,3),vecinas.get(0));
		assertEquals("Vecina (3,4)",new Coordenada2D(3,4), vecinas.get(1));
		assertEquals("Vecina (5,4)",new Coordenada2D(5,4), vecinas.get(2));
		assertEquals("Vecina (5,3)",new Coordenada2D(5,3), vecinas.get(3));
		assertEquals("Vecina (4,3)",new Coordenada2D(4,3), vecinas.get(4));
	}
	
	@Test
	public void testGetPosicionesVecinasCCW02() throws ExcepcionPosicionFueraTablero, ExcepcionCoordenadaIncorrecta {
		ArrayList<Coordenada> vecinas = new ArrayList<Coordenada>();
		vecinas = tab.getPosicionesVecinasCCW(new Coordenada2D(4,4));
		assertEquals("Numero vecinas",5,vecinas.size());
		assertEquals("Vecina (0,1)",new Coordenada2D(3,3),vecinas.get(0));
		assertEquals("Vecina (0,3)",new Coordenada2D(3,4), vecinas.get(1));
		assertEquals("Vecina (1,3)",new Coordenada2D(5,4), vecinas.get(2));
		assertEquals("Vecina (1,2)",new Coordenada2D(5,3), vecinas.get(3));
		assertEquals("Vecina (1,1)",new Coordenada2D(4,3), vecinas.get(4));
	}
	
	@Test
	public void testGetPosicionesVecinasCCW72() throws ExcepcionPosicionFueraTablero, ExcepcionCoordenadaIncorrecta {
		ArrayList<Coordenada> vecinas = new ArrayList<Coordenada>();
		vecinas = tab.getPosicionesVecinasCCW(new Coordenada2D(4,4));
		assertEquals("Numero vecinas",5,vecinas.size());
		assertEquals("Vecina (6,1)",new Coordenada2D(3,3),vecinas.get(0));
		assertEquals("Vecina (6,2)",new Coordenada2D(3,4), vecinas.get(1));
		assertEquals("Vecina (6,3)",new Coordenada2D(5,4), vecinas.get(2));
		assertEquals("Vecina (7,3)",new Coordenada2D(5,3), vecinas.get(3));
		assertEquals("Vecina (7,1)",new Coordenada2D(4,3), vecinas.get(4));
	}
	//Vecinas de una coordenada central
	@Test
	public void testGetPosicionesVecinasCCW32() throws ExcepcionCoordenadaIncorrecta, ExcepcionPosicionFueraTablero {
		ArrayList<Coordenada> vecinas = new ArrayList<Coordenada>();
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
	}
	/**
	 * Test method for {@link modelo.Tablero#cargaPatron(modelo.Patron, modelo.Coordenada)}.
	 * @throws ExcepcionCoordenadaIncorrecta 
	 * @throws ExcepcionPosicionFueraTablero 
	 */
	@Test
	public void testNoCargaPatron() throws ExcepcionPosicionFueraTablero, ExcepcionCoordenadaIncorrecta {
	    PrintStream ps = redireccionarSalidasStandardAFichero(FICHERRORES);
		//assertFalse("No carga patron",tab.cargaPatron(patronbloque, new Coordenada2D(-1,0)));
		//assertFalse("No carga patron",tab.cargaPatron(patronbloque, new Coordenada2D(0,5)));
		//assertFalse("No carga patron",tab.cargaPatron(patronbloque, new Coordenada2D(7,0)));
		//assertFalse("No carga patron",tab.cargaPatron(patronbloque, new Coordenada2D(6,4)));
		ps.close();
		restaurarSalidasStandard();
		Scanner sc;
		try {
			sc = new Scanner(new File(FICHERRORES));
			comprobarMensajeDeError(sc,"Error: La celda",false);
			comprobarMensajeDeError(sc,"Error: La celda",false);
			comprobarMensajeDeError(sc,"Error: La celda",false);
			comprobarMensajeDeError(sc,"Error: La celda",false);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			fail("Error apertura fichero "+FICHERRORES);
			e.printStackTrace();
		}
		
		//Comprobamos que no se ha modificado el tablero.
		for (int i=0; i<dim.getX();i++)
			for (int j=0; j<dim.getY(); j++) {
				assertEquals("Celda Muerta ("+i+","+j+")",EstadoCelda.MUERTA,tab.getCelda(new Coordenada2D(i,j)));
			}
	}

	@Test
	public void testCargaPatron() throws ExcepcionPosicionFueraTablero, ExcepcionCoordenadaIncorrecta {
		for (int i=0; i<7; i++)
			for (int j=0; j<4; j++) {
					//assertTrue("Si carga patron en ("+i+","+j+")",tab.cargaPatron(patronbloque, new Coordenada2D(i,j)));
					assertEquals("EstadoCelda=VIVA",EstadoCelda.VIVA,tab.getCelda(new Coordenada2D(i,j)));
					assertEquals("EstadoCelda=VIVA",EstadoCelda.VIVA,tab.getCelda(new Coordenada2D(i+1,j)));
					assertEquals("EstadoCelda=VIVA",EstadoCelda.VIVA,tab.getCelda(new Coordenada2D(i+1,j+1)));
					assertEquals("EstadoCelda=VIVA",EstadoCelda.VIVA,tab.getCelda(new Coordenada2D(i,j+1)));			
			}
		
	}
	/**
	 * Test method for {@link modelo.Tablero#contiene(modelo.Coordenada)}.
	 * @throws ExcepcionCoordenadaIncorrecta 
	 */
	@Test
	public void testContiene() throws ExcepcionCoordenadaIncorrecta {
		for (int i=-1; i<9; i++)
			for (int j=-1; j<6; j++) {
				if ((i>=0) && (i<8) && (j>=0) && (j<5))
					assertTrue("Si contiene a coordenada ("+i+","+j+")",tab.contiene(new Coordenada2D(i,j)));
				else
					assertFalse("No contiene a coordenada ("+i+","+j+")",tab.contiene(new Coordenada2D(i,j)));
			}
	}

	/**
	 * Test method for {@link modelo.Tablero#toString()}.
	 * @throws ExcepcionCoordenadaIncorrecta 
	 * @throws ExcepcionPosicionFueraTablero 
	 */
	@Test
	public void testToString() throws ExcepcionPosicionFueraTablero, ExcepcionCoordenadaIncorrecta {
		tab.cargaPatron(patronbloque,new Coordenada2D(1,1));
		tab.cargaPatron(patronbarco, new Coordenada2D(5,2));
		tab.cargaPatron(patronsapo, new Coordenada2D(0,3));
		tab.cargaPatron(patronbloque, new Coordenada2D(6,0));
		
		//Para que el alumno compare los ficheros si da error.
		PrintStream ps = abrirFichero("test/ficheros/tablerotest.sal"); 
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
	
	//Comprueba que los mensajes de error son correctos
	private void comprobarMensajeDeError (Scanner sc, String scoord, boolean exacto){
		String error;
		if (sc.hasNext()) {
	    	error=sc.nextLine();
	    	if (!error.isEmpty())
	    		if (exacto)
	    	       assertEquals("Error","Error: La celda "+scoord+" no existe",error.toString());
	    		else assertTrue("Error",error.contains(scoord));
	    }
		else fail("Falta/n algun/os error/es por salir");
	}
	
	//Redireccionamiento de las salidas standard a un fichero
	private PrintStream redireccionarSalidasStandardAFichero(String fic) {
	PrintStream ps=null;
	try {
		ps = new PrintStream(fic);
		System.setOut(ps);
	    System.setErr(ps);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}		
	 return (ps);
	}
	
	//Restaura las salidas standard
	private void restaurarSalidasStandard() {
		System.setOut(salstd);
	    System.setErr(errstd);
	}
}
