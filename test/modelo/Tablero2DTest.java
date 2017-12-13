/**
 * 
 */
package modelo;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;

import modelo.excepciones.ExcepcionCoordenada2DIncorrecta;
import modelo.excepciones.ExcepcionCoordenadaIncorrecta;
import modelo.excepciones.ExcepcionPosicionFueraTablero;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author gonzalo
 *
 */
public class Tablero2DTest {

	Tablero2D tab;
	Coordenada2D dim;
	static Patron patronbloque, patronbarco, patronsapo;
    static Tablero2D tabpat;
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


	@Test
	public void testTableroComposicion() {
		assertNotSame("Composicion Tabla-Coordenada",dim,tab.getDimensiones());
	}
	
	@Test
	public void testTablero2D() {
				
		EstadoCelda e;
		for (int i=0; i<dim.getX();i++)
			for (int j=0; j<dim.getY(); j++) {
				try {
					e = tab.getCelda(new Coordenada2D(i,j));
					assertNotNull("Coordenada valida("+i+","+j+")",e);
					assertEquals("Celda Muerta ("+i+","+j+")",EstadoCelda.MUERTA,e);
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					fail("No debía producirse ninguna excepción pero se capturo "+ex.getClass().getSimpleName());
				}
				
			}
		
	}

	@Test
	public void testGetDimensiones() {
		
		assertEquals("Comprobación dimensiones", dim,tab.getDimensiones());
	}

	
	@Test
	public void testGetPosiciones() {
		Set<Coordenada> sc = (Set<Coordenada>)tab.getPosiciones();
		assertEquals("Total posiciones",40,sc.size());
		for (int i=0; i<dim.getX();i++)
			for (int j=0; j<dim.getY(); j++) {
				try {
					assertTrue ("Coordenada en set",sc.contains((new Coordenada2D(i,j))));
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					fail("No debía producirse ninguna excepción pero se capturo "+ex.getClass().getSimpleName());
				}
			}
	}

	
	@Test
	public void testGetCeldasNoExisten() {
		Coordenada c = null;
	    try {
	    	c = new Coordenada2D(8,5);
			assertNull("No existe celda (8,5)",tab.getCelda(c));
			fail("Error. Debió producirse ExcepcionFueraTablero");
		} catch (ExcepcionPosicionFueraTablero e) {
			/*assertEquals(dim,e.getDimensiones());
			assertEquals(c,e.getCoordenada());*/
		} catch (Exception ex) {
			fail("Se tuvo que capturar la excepcion ExcepcionPosicionFueraTableropero se capturo "+ex.getClass().getSimpleName());
		}	
			
		try {
			c =new Coordenada2D(8,0);
		    assertNull ("No existe celda (8,0)",tab.getCelda(c));
		    fail("Error. Debió producirse ExcepcionFueraTablero");
		} catch (ExcepcionPosicionFueraTablero e1) {
			/*assertEquals(dim, e1.getDimensiones());
			assertEquals(c, e1.getCoordenada());*/
		} catch (Exception ex) {
			fail("Se tuvo que capturar la excepcion ExcepcionPosicionFueraTableropero se capturo "+ex.getClass().getSimpleName());
		}
				
	}
		
		@Test
		public void testGetCeldasExisten() {
			
		    try {
				assertNotNull ("Existe celda (7,4)",tab.getCelda(new Coordenada2D(7,4)));
				assertNotNull ("Existe celda (0,0)",tab.getCelda(new Coordenada2D(0,0)));
				assertNotNull ("Existe celda (7,0)",tab.getCelda(new Coordenada2D(7,0)));
				assertNotNull ("Existe celda (0,4)",tab.getCelda(new Coordenada2D(0,4)));
		    } catch (Exception e) {
				// TODO Auto-generated catch block
				fail ("No debió producirse ninguna excepcion");
			}
		   
		    
		}
	
	
	@Test
	public void testSetCelda() {
	
		Coordenada c=null, cerr=null;
		
		try {
			c = new Coordenada2D(7,5);		
			tab.setCelda(c,EstadoCelda.VIVA);
			fail("Error. Debió producirse ExcepcionFueraTablero");
		} catch (ExcepcionPosicionFueraTablero ex) {
				/*assertEquals (tab.getDimensiones(),ex.getDimensiones());
				assertEquals (c,ex.getCoordenada());*/
		} catch (Exception e){
			fail("Se esperaba ExcepcionPosicionFueraTablero, pero se capturo "+e.getClass().getSimpleName());
		}
		
		try {
			cerr = new Coordenada2D(8,0);
			for (int i=0; i<10; i++) {
		
					c = new Coordenada2D(i,0);
					tab.setCelda(c, EstadoCelda.VIVA);
				
			}
			fail("Error. Debió producirse ExcepcionFueraTablero");
		} catch (ExcepcionPosicionFueraTablero ex) {
					/*
					assertEquals (dim,ex.getDimensiones());
					assertEquals (cerr,ex.getCoordenada());*/
		} catch (Exception e) {
				fail("Se esperaba ExcepcionPosicionFueraTablero, pero se capturo "+e.getClass().getSimpleName());				
		}		
				
		for (int i=0; i<8; i++) {
				try {
					assertEquals("Celda ("+i+",0) puesta a VIVA", EstadoCelda.VIVA, tab.getCelda(new Coordenada2D(i,0)));
				} catch (Exception e2) {
					fail ("No se esperaba excepción, pero se capturo "+e2.getClass().getSimpleName()); 
				}
		}
		
	}
	
	
	
	@Test
	public void testNoCargaPatron() {
	    
		try {
			tab.cargaPatron(patronbloque, new Coordenada2D(-1,0));
			fail ("No se produjo ExcepcionCoordenadaIncorrecta");
		} catch (ExcepcionCoordenadaIncorrecta e) {
			// TODO Auto-generated catch block
			ExcepcionCoordenada2DIncorrecta ex = (ExcepcionCoordenada2DIncorrecta) e;
			/*assertEquals ("ex.getX",-1, ex.getX());
			assertEquals ("ex.getY",0,ex.getY());*/
		} catch (Exception e) {
			fail("Se esperaba ExcepcionCoordenadaIncorrecta, pero se capturo "+e.getClass().getSimpleName());
		    
		}
		
		try {
				tab.cargaPatron(patronbloque, new Coordenada2D(0,5));
				fail ("No se produjo ExcepcionPosicionFueraTablero");
		} catch (ExcepcionPosicionFueraTablero e) {
				//assertEquals("e.getDimensiones",dim,e.getDimensiones());
				
		} catch (Exception e1) {
				fail("Se esperaba ExcepcionPosicionFueraTablero, pero se capturo "+e1.getClass().getSimpleName());
			    
		}
			
		try {
				tab.cargaPatron(patronbloque, new Coordenada2D(7,0));
				fail ("No se produjo ExcepcionPosicionFueraTablero");
		} catch (ExcepcionPosicionFueraTablero e) {
				//assertEquals("e.getDimensiones",dim,e.getDimensiones());
				
		} catch (Exception e1) {
				fail("Se esperaba ExcepcionPosicionFueraTablero, pero se capturo "+e1.getClass().getSimpleName());
			   
		}
			
		try {
				tab.cargaPatron(patronbloque, new Coordenada2D(6,4));
				fail ("No se produjo ExcepcionPosicionFueraTablero");
		} catch (ExcepcionPosicionFueraTablero e) {
				//assertEquals("e.getDimensiones",dim,e.getDimensiones());
				
		} catch (Exception e1) {
				fail("Se esperaba ExcepcionPosicionFueraTablero, pero se capturo "+e1.getClass().getSimpleName());
				
		}
					
		//Comprobamos que no se ha modificado el tablero.
		for (int i=0; i<dim.getX();i++)
			for (int j=0; j<dim.getY(); j++) {
				try {
					assertEquals("Celda Muerta ("+i+","+j+")",EstadoCelda.MUERTA,tab.getCelda(new Coordenada2D(i,j)));
				} catch (Exception e) {
					fail ("No se esperaba excepción, pero se capturo "+e.getClass().getSimpleName());
				}
			}
	}
	
	@Test
	public void testCargaPatron() {
		for (int i=0; i<7; i++)
			for (int j=0; j<4; j++) {
				try {
					tab.cargaPatron(patronbloque, new Coordenada2D(i,j));
					assertEquals("EstadoCelda=VIVA",EstadoCelda.VIVA,tab.getCelda(new Coordenada2D(i,j)));
					assertEquals("EstadoCelda=VIVA",EstadoCelda.VIVA,tab.getCelda(new Coordenada2D(i+1,j)));
					assertEquals("EstadoCelda=VIVA",EstadoCelda.VIVA,tab.getCelda(new Coordenada2D(i+1,j+1)));
					assertEquals("EstadoCelda=VIVA",EstadoCelda.VIVA,tab.getCelda(new Coordenada2D(i,j+1)));			
				} catch (Exception e) {
						// TODO Auto-generated catch block
					fail ("No se esperaba excepción, pero se capturo "+e.getClass().getSimpleName());
				}
			}
	}

	
	@Test
	public void testContiene() {
		Coordenada2D c=null;
		for (int i=-1; i<9; i++)
			for (int j=-1; j<6; j++) {
				try {
					c = new Coordenada2D(i,j);
					if ((i>=0) && (i<8) && (j>=0) && (j<5))
						assertTrue("Si contiene a coordenada ("+i+","+j+")",tab.contiene(c));
					else
						assertFalse("No contiene a coordenada ("+i+","+j+")",tab.contiene(c));
				} catch (ExcepcionCoordenada2DIncorrecta e) {
					//assertTrue("("+i+","+j+")",e.getX()==-1 || e.getY()==-1);
				} catch (Exception e) {
					fail("Se esperaba ExcepcionCoordenada2DIncorrecta, pero se capturo "+e.getClass().getSimpleName());
				}
				
			}
	}

	
	
/*------------------- FIN TESTS ---------------------------------*/
	
	
	//FUNCIONES AUXILIARES
	
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
