package modelo;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;

import modelo.excepciones.ExcepcionCoordenada1DIncorrecta;
import modelo.excepciones.ExcepcionPosicionFueraTablero;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TableroP3Test {
	Tablero tab;
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

				try {
					e = tab.getCelda(new Coordenada1D(i));
					assertNotNull("Coordenada valida("+i+")",e);
					assertEquals("Celda Muerta ("+i+")",EstadoCelda.MUERTA,e);
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					fail ("No se esperaba excepción, pero se capturo "+ex.getClass().getSimpleName()); 
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
		assertEquals("Total posiciones",9,sc.size());
		for (int i=0; i<dim.getX();i++)
				try {
					assertTrue ("Coordenada en set",sc.contains((new Coordenada1D(i))));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					fail("No debía producirse ninguna excepción pero se capturo "+e.getClass().getSimpleName());
				}
	}

	/**
	 * Test method for {@link modelo.Tablero#getCelda(modelo.Coordenada)}.
	 */
	@Test
	public void testGetCeldasNoExisten() {
		Coordenada c = null;
	    try {
	    	c = new Coordenada1D(9);
			assertNull("No existe celda (9)",tab.getCelda(c));
			fail("Error. Debió producirse ExcepcionFueraTablero");
		} catch (ExcepcionPosicionFueraTablero e) {
			/*assertEquals(dim,e.getDimensiones());
			assertEquals(c,e.getCoordenada());*/
		} catch (Exception ex) {
			fail("Se tuvo que capturar la excepcion ExcepcionPosicionFueraTableropero se capturo "+ex.getClass().getSimpleName());
		}	
	}
		
		@Test
		public void testGetCeldasExisten() {
			
		    try {
				assertNotNull ("Existe celda (7)",tab.getCelda(new Coordenada1D(7)));
				assertNotNull ("Existe celda (1)",tab.getCelda(new Coordenada1D(1)));
				assertNotNull ("Existe celda (8)",tab.getCelda(new Coordenada1D(8)));
				assertNotNull ("Existe celda (0)",tab.getCelda(new Coordenada1D(0)));
		    } catch (Exception e) {
				// TODO Auto-generated catch block
				fail ("No debió producirse ninguna excepcion");
			}
		   
		    
		}
	
	
	/**
	 * Test method for {@link modelo.Tablero#setCelda(modelo.Coordenada, modelo.EstadoCelda)}.
	 */
	@Test
	public void testSetCelda() {
	
		Coordenada c=null, cerr=null;
		
		try {
			c = new Coordenada1D(9);
			tab.setCelda(c,EstadoCelda.VIVA);
			fail("Error. Debió producirse ExcepcionFueraTablero");
		} catch (ExcepcionPosicionFueraTablero ex) {
			/*assertEquals (tab.getDimensiones(),ex.getDimensiones());
			assertEquals (c,ex.getCoordenada());*/
		} catch (Exception e){
			fail("Se esperaba ExcepcionPosicionFueraTablero, pero se capturo "+e.getClass().getSimpleName());
		}
		
		try {		
			cerr = new Coordenada1D(9);
			for (int i=0; i<10; i++) {
					c = new Coordenada1D(i);
					tab.setCelda(c, EstadoCelda.VIVA);
			}
			fail("Error. Debió producirse ExcepcionFueraTablero");
		} catch (ExcepcionPosicionFueraTablero e1) {
				ExcepcionPosicionFueraTablero ex1 =  (ExcepcionPosicionFueraTablero) e1;
				/*assertEquals (dim,ex1.getDimensiones());
				assertEquals (cerr,ex1.getCoordenada());*/
		} catch (Exception e) {
				fail("Se esperaba ExcepcionPosicionFueraTablero, pero se capturo "+e.getClass().getSimpleName());
		}	
		
		for (int i=0; i<8; i++) {
				try {
					assertEquals("Celda ("+i+") puesta a VIVA", EstadoCelda.VIVA, tab.getCelda(new Coordenada1D(i)));
				} catch (Exception e2) {
					fail ("No se esperaba excepción, pero se capturo "+e2.getClass().getSimpleName()); 
				}
		}
			
	}
	
		
	@Test
	public void testNoCargaPatron() {
	    Coordenada c=null;
		try {
			tab.cargaPatron(patronsimple, new Coordenada1D(-1));
			fail ("No se produjo ExcepcionCoordenadaIncorrecta");
		} catch (ExcepcionCoordenada1DIncorrecta ex) {
			// TODO Auto-generated catch block	
			//assertEquals ("ex.getX",-1, ex.getX());
		} catch (Exception e) {
			fail("Se esperaba ExcepcionCoordenadaIncorrecta, pero se capturo "+e.getClass().getSimpleName());
		    
		}
		
		try {
				c = new Coordenada1D(9);
				tab.cargaPatron(patronduo, new Coordenada1D(8));
				fail ("No se produjo ExcepcionPosicionFueraTablero");
		} catch (ExcepcionPosicionFueraTablero e) {
				/*assertEquals("e.getDimensiones",dim,e.getDimensiones());
				assertEquals("e.getCoordenada",c,e.getCoordenada());*/
		} catch (Exception e1) {
				fail("Se esperaba ExcepcionPosicionFueraTablero, pero se capturo "+e1.getClass().getSimpleName());
			    
		}
			
		try {
				c = new Coordenada1D(9);
				tab.cargaPatron(patronsos, new Coordenada1D(1));
				fail ("No se produjo ExcepcionPosicionFueraTablero");
		} catch (ExcepcionPosicionFueraTablero e) {
				/*assertEquals("e.getDimensiones",dim,e.getDimensiones());
				assertEquals("e.getCoordenada",c,e.getCoordenada());*/
		} catch (Exception e1) {
				fail("Se esperaba ExcepcionPosicionFueraTablero, pero se capturo "+e1.getClass().getSimpleName());
			   
		}
			
		try {
				
				tab.cargaPatron(patronsos, new Coordenada1D(5));
				fail ("No se produjo ExcepcionPosicionFueraTablero");
		} catch (ExcepcionPosicionFueraTablero e) {
				//assertEquals("e.getDimensiones",dim,e.getDimensiones());
				
		} catch (Exception e1) {
				fail("Se esperaba ExcepcionPosicionFueraTablero, pero se capturo "+e1.getClass().getSimpleName());
				
		}
					
		//Comprobamos que no se ha modificado el tablero.
		for (int i=0; i<dim.getX();i++)
		
				try {
					assertEquals("Celda Muerta ("+i+")",EstadoCelda.MUERTA,tab.getCelda(new Coordenada1D(i)));
				} catch (Exception e) {
					fail ("No se esperaba excepción, pero se capturo "+e.getClass().getSimpleName());
				}
	}
	
	@Test
	public void testCargaPatron() {
		for (int i=0; i<7; i++)
		
				try {
					tab.cargaPatron(patronduo, new Coordenada1D(i));
					assertEquals("EstadoCelda=VIVA",EstadoCelda.VIVA,tab.getCelda(new Coordenada1D(i)));
					assertEquals("EstadoCelda=VIVA",EstadoCelda.VIVA,tab.getCelda(new Coordenada1D(i+1)));		
				} catch (Exception e) {
						// TODO Auto-generated catch block
					fail ("No se esperaba excepción, pero se capturo "+e.getClass().getSimpleName());
				}
	}

	/**
	 * Test method for {@link modelo.Tablero#contiene(modelo.Coordenada)}.
	 */
	@Test
	public void testContiene() {
		Coordenada1D c=null;
		for (int i=-1; i<10; i++)
				try {
					c = new Coordenada1D(i);
					if ((i>=0) && (i<9))
						assertTrue("Si contiene a coordenada ("+i+")",tab.contiene(c));
					else
						assertFalse("No contiene a coordenada ("+i+")",tab.contiene(c));
				} catch (ExcepcionCoordenada1DIncorrecta e) {
					//assertEquals("("+i+")",-1,e.getX());
				} catch (Exception e) {
					fail("Se esperaba ExcepcionCoordenada1DIncorrecta, pero se capturo "+e.getClass().getSimpleName());
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
