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

/**
 * @author gonzalo
 *
 */
public class TableroP2Test {

	Tablero tab;
	Coordenada dim;
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
		tabpat = new Tablero(new Coordenada(2,2));
		tabpat.setCelda(new Coordenada(0,0), EstadoCelda.VIVA);
		tabpat.setCelda(new Coordenada(1,0), EstadoCelda.VIVA);

		tabpat.setCelda(new Coordenada(0,1), EstadoCelda.VIVA);
		tabpat.setCelda(new Coordenada(1,1), EstadoCelda.VIVA);
		patronbloque = new Patron("Bloque", tabpat);
		
				
		tabpat = new Tablero(new Coordenada(3,3));
		tabpat.setCelda(new Coordenada(0,0), EstadoCelda.VIVA);
		tabpat.setCelda(new Coordenada(1,0), EstadoCelda.VIVA);
		tabpat.setCelda(new Coordenada(2,0), EstadoCelda.MUERTA);

		tabpat.setCelda(new Coordenada(0,1), EstadoCelda.VIVA);
		tabpat.setCelda(new Coordenada(1,1), EstadoCelda.MUERTA);
		tabpat.setCelda(new Coordenada(2,1), EstadoCelda.VIVA);

		tabpat.setCelda(new Coordenada(0,2), EstadoCelda.MUERTA);
		tabpat.setCelda(new Coordenada(1,2), EstadoCelda.VIVA);
		tabpat.setCelda(new Coordenada(2,2), EstadoCelda.MUERTA);
		patronbarco = new Patron("Barco", tabpat);
		
		tabpat = new Tablero(new Coordenada(4,2));
		tabpat.setCelda(new Coordenada(0,0), EstadoCelda.MUERTA);
		tabpat.setCelda(new Coordenada(1,0), EstadoCelda.VIVA);
		tabpat.setCelda(new Coordenada(2,0), EstadoCelda.VIVA);
		tabpat.setCelda(new Coordenada(3,0), EstadoCelda.VIVA);

		tabpat.setCelda(new Coordenada(0,1), EstadoCelda.VIVA);
		tabpat.setCelda(new Coordenada(1,1), EstadoCelda.VIVA);
		tabpat.setCelda(new Coordenada(2,1), EstadoCelda.VIVA);
		tabpat.setCelda(new Coordenada(3,1), EstadoCelda.MUERTA);
		patronsapo = new Patron("Sapo", tabpat);
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		dim = new Coordenada(8,5);
		tab = new Tablero(dim); //Tablero del alumno
	}
	
	/**
	 * Test method for {@link modelo.Tablero#Tablero(modelo.Coordenada)}.
	 */
	@Test
	public void testTableroComposicion() {
		assertNotSame("Composicion Tabla-Coordenada",dim,tab.getDimensiones());
	}
	
	@Test
	public void testTablero() {
				
		EstadoCelda e;
		for (int i=0; i<dim.getX();i++)
			for (int j=0; j<dim.getY(); j++) {
				e = tab.getCelda(new Coordenada(i,j));
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
	 */
	@Test
	public void testGetPosiciones() {
		Set<Coordenada> sc = (Set<Coordenada>)tab.getPosiciones();
		assertEquals("Total posiciones",40,sc.size());
		for (int i=0; i<dim.getX();i++)
			for (int j=0; j<dim.getY(); j++) {
				assertTrue ("Coordenada en set",sc.contains((new Coordenada(i,j))));
			}
	}

	/**
	 * Test method for {@link modelo.Tablero#getCelda(modelo.Coordenada)}.
	 */
	@Test
	public void testGetCeldasNoExisten() {
		
			PrintStream ps = redireccionarSalidasStandardAFichero(FICHERRORES);
		
		    if (ps==null) fail("Error de apertura del fichero "+FICHERRORES);
		    assertNull ("No existe celda (8,5)",tab.getCelda(new Coordenada(8,5)));
		    assertNull ("No existe celda (8,0)",tab.getCelda(new Coordenada(8,0)));
		    assertNull ("No existe celda (-1,4)",tab.getCelda(new Coordenada(-1,4)));
		    assertNull ("No existe celda (7,-1)",tab.getCelda(new Coordenada(7,-1)));
		    
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
		public void testGetCeldasExisten() {
			PrintStream ps = redireccionarSalidasStandardAFichero(FICHERRORES);
		    assertNotNull ("Existe celda (7,4)",tab.getCelda(new Coordenada(7,4)));
		    assertNotNull ("Existe celda (0,0)",tab.getCelda(new Coordenada(0,0)));
		    assertNotNull ("Existe celda (7,0)",tab.getCelda(new Coordenada(7,0)));
		    assertNotNull ("Existe celda (0,4)",tab.getCelda(new Coordenada(0,4)));
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
	 */
	@Test
	public void testSetCelda() {
		PrintStream ps = redireccionarSalidasStandardAFichero(FICHERRORES);
		
		tab.setCelda(new Coordenada(7,5),EstadoCelda.VIVA); //coordenada incorrecta
		tab.setCelda(new Coordenada(-2,3),EstadoCelda.MUERTA); //coordenada incorrecta
		
		for (int i=0; i<9; i++) {
			tab.setCelda(new Coordenada(i,0), EstadoCelda.VIVA);
		}
		for (int i=0; i<8; i++) {
			assertEquals("Celda ("+i+",0) puesta a VIVA", EstadoCelda.VIVA, tab.getCelda(new Coordenada(i,0)));
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
	 */
	@Test
	public void testGetPosicionesVecinasCCWErronea() {
		ArrayList<Coordenada> vecinas = null;
		vecinas = tab.getPosicionesVecinasCCW(new Coordenada(-1,0));
		assertTrue("vecinas = null || vecinas.size()==0",vecinas==null || vecinas.size()==0);
		vecinas = tab.getPosicionesVecinasCCW(new Coordenada(0,-1));
		assertTrue("vecinas = null || vecinas.size()==0",vecinas==null || vecinas.size()==0);
		vecinas = tab.getPosicionesVecinasCCW(new Coordenada(8,1));
		assertTrue("vecinas = null || vecinas.size()==0",vecinas==null || vecinas.size()==0);
		vecinas = tab.getPosicionesVecinasCCW(new Coordenada(5,5));
		assertTrue("vecinas = null || vecinas.size()==0",vecinas==null || vecinas.size()==0);
	}
	
	//Vecinas de las coordenadas de las esquinas
	@Test
	public void testGetPosicionesVecinasCCW00() {
		ArrayList<Coordenada> vecinas = new ArrayList<Coordenada>();
		vecinas = tab.getPosicionesVecinasCCW(new Coordenada(0,0));
		assertEquals("Numero vecinas",3,vecinas.size());
		assertEquals("Vecina (0,1)",new Coordenada(0,1),vecinas.get(0));
		assertEquals("Vecina (1,1)",new Coordenada(1,1), vecinas.get(1));
		assertEquals("Vecina (1,0)",new Coordenada(1,0), vecinas.get(2));

	}

	@Test
	public void testGetPosicionesVecinasCCW70() {
		ArrayList<Coordenada> vecinas = new ArrayList<Coordenada>();
		vecinas = tab.getPosicionesVecinasCCW(new Coordenada(7,0));
		assertEquals("Numero vecinas",3,vecinas.size());
		assertEquals("Vecina (6,0)",new Coordenada(6,0),vecinas.get(0));
		assertEquals("Vecina (6,1)",new Coordenada(6,1), vecinas.get(1));
		assertEquals("Vecina (7,1)",new Coordenada(7,1), vecinas.get(2));
	}
	
	@Test
	public void testGetPosicionesVecinasCCW04() {
		ArrayList<Coordenada> vecinas = new ArrayList<Coordenada>();
		vecinas = tab.getPosicionesVecinasCCW(new Coordenada(0,4));
		assertEquals("Numero vecinas",3,vecinas.size());
		assertEquals("Vecina (1,4)",new Coordenada(1,4),vecinas.get(0));
		assertEquals("Vecina (1,3)",new Coordenada(1,3), vecinas.get(1));
		assertEquals("Vecina (0,3)",new Coordenada(0,3), vecinas.get(2));
	}
	
	@Test
	public void testGetPosicionesVecinasCCW74() {
		ArrayList<Coordenada> vecinas = new ArrayList<Coordenada>();
		vecinas = tab.getPosicionesVecinasCCW(new Coordenada(7,4));
		assertEquals("Numero vecinas",3,vecinas.size());
		assertEquals("Vecina (6,3)",new Coordenada(6,3),vecinas.get(0));
		assertEquals("Vecina (6,4)",new Coordenada(6,4), vecinas.get(1));
		assertEquals("Vecina (7,3)",new Coordenada(7,3), vecinas.get(2));
	}
	
	//Vecinas de coordenadas laterales
	@Test
	public void testGetPosicionesVecinasCCW40() {
		ArrayList<Coordenada> vecinas = new ArrayList<Coordenada>();
		vecinas = tab.getPosicionesVecinasCCW(new Coordenada(4,0));
		assertEquals("Numero vecinas",5,vecinas.size());
		assertEquals("Vecina (3,0)",new Coordenada(3,0),vecinas.get(0));
		assertEquals("Vecina (3,1)",new Coordenada(3,1), vecinas.get(1));
		assertEquals("Vecina (4,1)",new Coordenada(4,1), vecinas.get(2));
		assertEquals("Vecina (5,1)",new Coordenada(5,1), vecinas.get(3));
		assertEquals("Vecina (5,0)",new Coordenada(5,0), vecinas.get(4));
	}
	
	@Test
	public void testGetPosicionesVecinasCCW44() {
		ArrayList<Coordenada> vecinas = new ArrayList<Coordenada>();
		vecinas = tab.getPosicionesVecinasCCW(new Coordenada(4,4));
		assertEquals("Numero vecinas",5,vecinas.size());
		assertEquals("Vecina (3,3)",new Coordenada(3,3),vecinas.get(0));
		assertEquals("Vecina (3,4)",new Coordenada(3,4), vecinas.get(1));
		assertEquals("Vecina (5,4)",new Coordenada(5,4), vecinas.get(2));
		assertEquals("Vecina (5,3)",new Coordenada(5,3), vecinas.get(3));
		assertEquals("Vecina (4,3)",new Coordenada(4,3), vecinas.get(4));
	}
	
	@Test
	public void testGetPosicionesVecinasCCW02() {
		ArrayList<Coordenada> vecinas = new ArrayList<Coordenada>();
		vecinas = tab.getPosicionesVecinasCCW(new Coordenada(4,4));
		assertEquals("Numero vecinas",5,vecinas.size());
		assertEquals("Vecina (0,1)",new Coordenada(3,3),vecinas.get(0));
		assertEquals("Vecina (0,3)",new Coordenada(3,4), vecinas.get(1));
		assertEquals("Vecina (1,3)",new Coordenada(5,4), vecinas.get(2));
		assertEquals("Vecina (1,2)",new Coordenada(5,3), vecinas.get(3));
		assertEquals("Vecina (1,1)",new Coordenada(4,3), vecinas.get(4));
	}
	
	@Test
	public void testGetPosicionesVecinasCCW72() {
		ArrayList<Coordenada> vecinas = new ArrayList<Coordenada>();
		vecinas = tab.getPosicionesVecinasCCW(new Coordenada(4,4));
		assertEquals("Numero vecinas",5,vecinas.size());
		assertEquals("Vecina (6,1)",new Coordenada(3,3),vecinas.get(0));
		assertEquals("Vecina (6,2)",new Coordenada(3,4), vecinas.get(1));
		assertEquals("Vecina (6,3)",new Coordenada(5,4), vecinas.get(2));
		assertEquals("Vecina (7,3)",new Coordenada(5,3), vecinas.get(3));
		assertEquals("Vecina (7,1)",new Coordenada(4,3), vecinas.get(4));
	}
	//Vecinas de una coordenada central
	@Test
	public void testGetPosicionesVecinasCCW32() {
		ArrayList<Coordenada> vecinas = new ArrayList<Coordenada>();
		vecinas = tab.getPosicionesVecinasCCW(new Coordenada(3,2));
		assertEquals("Numero vecinas",8,vecinas.size());
		assertEquals("Vecina (2,1)",new Coordenada(2,1),vecinas.get(0));
		assertEquals("Vecina (2,2)",new Coordenada(2,2), vecinas.get(1));
		assertEquals("Vecina (2,3)",new Coordenada(2,3), vecinas.get(2));
		assertEquals("Vecina (3,3)",new Coordenada(3,3), vecinas.get(3));
		assertEquals("Vecina (4,3)",new Coordenada(4,3), vecinas.get(4));
		assertEquals("Vecina (4,2)",new Coordenada(4,2), vecinas.get(5));
		assertEquals("Vecina (4,1)",new Coordenada(4,1), vecinas.get(6));
		assertEquals("Vecina (3,1)",new Coordenada(3,1), vecinas.get(7));
	}
	/**
	 * Test method for {@link modelo.Tablero#cargaPatron(modelo.Patron, modelo.Coordenada)}.
	 */
	@Test
	public void testNoCargaPatron() {
	    PrintStream ps = redireccionarSalidasStandardAFichero(FICHERRORES);
		assertFalse("No carga patron",tab.cargaPatron(patronbloque, new Coordenada(-1,0)));
		assertFalse("No carga patron",tab.cargaPatron(patronbloque, new Coordenada(0,5)));
		assertFalse("No carga patron",tab.cargaPatron(patronbloque, new Coordenada(7,0)));
		assertFalse("No carga patron",tab.cargaPatron(patronbloque, new Coordenada(6,4)));
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
				assertEquals("Celda Muerta ("+i+","+j+")",EstadoCelda.MUERTA,tab.getCelda(new Coordenada(i,j)));
			}
	}

	@Test
	public void testCargaPatron() {
		for (int i=0; i<7; i++)
			for (int j=0; j<4; j++) {
					assertTrue("Si carga patron en ("+i+","+j+")",tab.cargaPatron(patronbloque, new Coordenada(i,j)));
					assertEquals("EstadoCelda=VIVA",EstadoCelda.VIVA,tab.getCelda(new Coordenada(i,j)));
					assertEquals("EstadoCelda=VIVA",EstadoCelda.VIVA,tab.getCelda(new Coordenada(i+1,j)));
					assertEquals("EstadoCelda=VIVA",EstadoCelda.VIVA,tab.getCelda(new Coordenada(i+1,j+1)));
					assertEquals("EstadoCelda=VIVA",EstadoCelda.VIVA,tab.getCelda(new Coordenada(i,j+1)));			
			}
		
	}
	/**
	 * Test method for {@link modelo.Tablero#contiene(modelo.Coordenada)}.
	 */
	@Test
	public void testContiene() {
		for (int i=-1; i<9; i++)
			for (int j=-1; j<6; j++) {
				if ((i>=0) && (i<8) && (j>=0) && (j<5))
					assertTrue("Si contiene a coordenada ("+i+","+j+")",tab.contiene(new Coordenada(i,j)));
				else
					assertFalse("No contiene a coordenada ("+i+","+j+")",tab.contiene(new Coordenada(i,j)));
			}
	}

	/**
	 * Test method for {@link modelo.Tablero#toString()}.
	 */
	@Test
	public void testToString() {
		tab.cargaPatron(patronbloque,new Coordenada(1,1));
		tab.cargaPatron(patronbarco, new Coordenada(5,2));
		tab.cargaPatron(patronsapo, new Coordenada(0,3));
		tab.cargaPatron(patronbloque, new Coordenada(6,0));
		
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
