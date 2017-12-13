package modelo;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import modelo.excepciones.ExcepcionCoordenada1DIncorrecta;
import modelo.excepciones.ExcepcionPosicionFueraTablero;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Juego1DP3Test {

    Regla30 regla;
    ArrayList<Patron> patrones;
    Tablero tablero;
    Juego juego;
    Coordenada dimension;
    static Patron patronsimple, patrontrio, patronsos;
    static String tablero1, tablero2, tablero3, tablero4;
    static Tablero tablerodelmain, tablerosos, tableroconmaspatrones;
 
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	    tablerodelmain = IniciaTablerosResultado(new Coordenada2D(43,22),"test/ficheros/tableros1Ddelmain.ent");
	    tablerosos = IniciaTablerosResultado(new Coordenada2D(60,30),"test/ficheros/tableros1Dsos.ent");
	    tableroconmaspatrones = IniciaTablerosResultado(new Coordenada2D(60,40),"test/ficheros/tableros1Dconmaspatrones.ent");
	    CreaPatrones();
	}


	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		dimension = new Coordenada1D(43);
		tablero = new Tablero1D(43);
		regla = new Regla30();
		juego = new Juego(tablero,regla);
		patrones = juego.getPatrones();	
	}

	
	/**
	 * Test method for {@link modelo.Juego#Juego(modelo.Tablero, modelo.ReglaConway)}.
	 */
	@Test
	public void testJuego() {

		assertNotNull ("Patrones usados iniciado", juego.getPatrones());
	}

	/**
	 * Test method for {@link modelo.Juego#cargaPatron(modelo.Patron, modelo.Coordenada)}.
	 */
	@Test
	public void testCargaPatron() {
		
			Coordenada cerr = null;
			try {
				cerr = new Coordenada1D(43);
				juego.cargaPatron(patrontrio, new Coordenada1D(41));
				fail("Se debió producir ExcepcionPosicionFueraTablero");
			} catch (ExcepcionPosicionFueraTablero e) {
				/*assertEquals("e.getDimensiones",dimension,e.getDimensiones());
				assertEquals("e.getCoordenada",cerr,e.getCoordenada());*/
			} catch (Exception e) {
					fail("Se esperaba ExcepcionPosicionFueraTablero, pero se capturo "+e.getClass().getSimpleName());
					
			}
			assertEquals("No carga el patrón",0,patrones.size());
			
			
			try {
				juego.cargaPatron(patrontrio, new Coordenada1D(40));
			} catch (Exception e) {
				fail("No se esperaba excepcion, pero se capturo "+e.getClass().getSimpleName());
			}
			assertEquals("Sí carga el patrón",1, patrones.size());
			assertSame("Agregación", patrontrio,patrones.get(0));
		
			try {
				juego.cargaPatron(patronsimple, new Coordenada1D(-1));
				fail("Se debió producir ExcepcionCoordenada1DIncorrecta");
			} catch (ExcepcionCoordenada1DIncorrecta e) {
				//assertEquals("e.getX",-1,e.getX());
			} catch (Exception e) {
					fail("Se esperaba ExcepcionCoordenadaIncorrecta, pero se capturo "+e.getClass().getSimpleName());				
			}
			assertEquals("No carga el patrón",1,patrones.size());
			
			try {
				juego.cargaPatron(patronsos, new Coordenada1D(0));
			} catch (Exception e) {
				fail("No se esperaba excepcion, pero se capturo "+e.getClass().getSimpleName());
			}
			assertEquals("Sí carga el patrón",2,patrones.size());
			assertSame("Agregación", patronsos,patrones.get(1));		
	}

	/**
	 * Test method for {@link modelo.Juego#actualiza()}.
	 */
	@Test
	public void testActualizaTableroDelMain() {
		
		Juego juego1=null;
		try {
			juego1 = new Juego(new Tablero1D(43),regla);
			juego1.cargaPatron(patronsimple, new Coordenada1D(22));

		} catch (Exception e) {
			fail("No se esperaba excepcion, pero se capturo "+e.getClass().getSimpleName());
		}
	
		TableroCeldasCuadradas tab2=null;
		try {
			tab2 = new TableroCeldasCuadradas(43,22);
		}  catch (Exception e) {
			fail("No se esperaba excepcion, pero se capturo "+e.getClass().getSimpleName());
		}
		
		//Tablero con las 22 iteraciones del alumno
		/*Fichero con los tableros que el alumno genera.Para que pueda
		analizar sus actualizaciones sucesivas en caso de error */
		PrintStream s = abrirFichero("test/ficheros/tableros1Ddelmaindelalumno.sal"); 
		for (int i=0; i<22; i++) { 
			for (int j=0; j<43; j++) {
				try {
					tab2.setCelda(new Coordenada2D(j,i), juego1.getTablero().getCelda(new Coordenada1D(j)));
				} catch (Exception e) {
					fail("No se esperaba excepcion, pero se capturo "+e.getClass().getSimpleName());
				}
			}
			s.print(juego1.getTablero().toString()); //Guarda tablero del alumno
			juego1.actualiza();
		}
		
		s.close();
		
		//Comprobación de las iteraciones de los tableros finales del alumno.
		Coordenada c;
		for (int j=0; j<22; j++)
			for (int i=0; i<43; i++) {
				try {
					c = new Coordenada2D(i,j);
					assertEquals("Estado ("+i+","+j+")",tablerodelmain.getCelda(c),tab2.getCelda(c));
				} catch (Exception e) {
					fail("No se esperaba excepcion, pero se capturo "+e.getClass().getSimpleName());
				}	
			}
	}
	
	@Test
	public void testActualizaSOS() {
		
		Juego juego1=null;
		try {
			juego1 = new Juego(new Tablero1D(60),regla);
			juego1.cargaPatron(patronsos, new Coordenada1D(45));
		} catch (Exception e) {
			fail("No se esperaba excepcion, pero se capturo "+e.getClass().getSimpleName());
		}
		
		TableroCeldasCuadradas tab2=null;
		try {
			tab2 = new TableroCeldasCuadradas(60,30);
		}  catch (Exception e) {
			fail("No se esperaba excepcion, pero se capturo "+e.getClass().getSimpleName());
		}
		
		/*Fichero con los tableros que el alumno genera.Para que en caso de error pueda
		hacer un seguimiento de sus actualizaciones */
		PrintStream s = abrirFichero("test/ficheros/tableros1Dsos.sal"); 
		StringBuilder sb = new StringBuilder();
		for (int j=0; j<30; j++) {
			
			for (int i=0; i<60; i++) {
				try {
					tab2.setCelda(new Coordenada2D(i,j), juego1.getTablero().getCelda(new Coordenada1D(i)));
				} catch (Exception e) {
					fail("No se esperaba excepcion, pero se capturo "+e.getClass().getSimpleName());
				}
			}
			s.print(juego1.getTablero().toString()); //Guarda tablero del alumno 
			sb.append(juego1.getTablero().toString());
			juego1.actualiza();	
		
		}
	
		//Comprobamos el contenido del tablero del alumno
		Coordenada c;
		for (int j=0; j<30; j++) //sucesivas iteraciones
			for (int i=0; i<60; i++) { //índice tabla
				try {
					c = new Coordenada2D(i,j);
					assertEquals("Estado ("+i+","+j+")",tablerosos.getCelda(c),tab2.getCelda(c));
				} catch (Exception e) {
					fail("No se esperaba excepcion, pero se capturo "+e.getClass().getSimpleName());
				}	
			}		
	}
	
	@Test
	public void testActualizaConMasPatrones() {
		Juego juego1=null;
		try {
			juego1 = new Juego(new Tablero1D(60),regla);
			
			juego1.cargaPatron(patronsimple, new Coordenada1D(0));
			juego1.cargaPatron(patrontrio, new Coordenada1D(8)); 
			juego1.cargaPatron(patronsos, new Coordenada1D(15));
			juego1.cargaPatron(patronsimple, new Coordenada1D(29));
			juego1.cargaPatron(patrontrio, new Coordenada1D(33)); 
		
			TableroCeldasCuadradas tab2=null;
			tab2 = new TableroCeldasCuadradas(60,40); 
				
		
			/*Fichero con los tableros que el alumno genera.Para que en caso de error pueda
			hacer un seguimiento de sus actualizaciones */
			PrintStream s = abrirFichero("test/ficheros/tableros1Dconmaspatrones.sal"); 
			StringBuilder sb = new StringBuilder();
			for (int j=0; j<40; j++) {
				
				for (int i=0; i<60; i++) {
					tab2.setCelda(new Coordenada2D(i,j), juego1.getTablero().getCelda(new Coordenada1D(i)));
				}
				s.print(juego1.getTablero().toString()); //Guarda tablero del alumno 
				sb.append(juego1.getTablero().toString());
				juego1.actualiza();	
			
			}	
			s.close();
			
		
		//Comprobación del contenido del tablero del alumno
		Coordenada c;
		for (int j=0; j<40; j++)
			for (int i=0; i<60; i++) {
				c = new Coordenada2D(i,j);
				assertEquals("Estado ("+i+","+j+")",tableroconmaspatrones.getCelda(c),tab2.getCelda(c));
			}
		} catch (Exception e) {
			fail("No se esperaba excepcion, pero se capturo "+e.getClass().getSimpleName());
		}
	}

	@Test
	public void testActualizaTodoVivo() {
	
		Juego juego1;
		try {
			juego1 = new Juego(new Tablero1D(12),regla);
			juego1.cargaPatron(patrontrio, new Coordenada1D(0));
			juego1.cargaPatron(patrontrio, new Coordenada1D(3)); 
			juego1.cargaPatron(patrontrio, new Coordenada1D(6));
			juego1.cargaPatron(patrontrio, new Coordenada1D(9));
	
			TableroCeldasCuadradas tab2=null, tabsol=null;
			tab2 = new TableroCeldasCuadradas(12,2);
			tabsol = new TableroCeldasCuadradas(12,2);
			for (int i=0; i<12; i++) {
				tabsol.setCelda(new Coordenada2D(i,0), EstadoCelda.VIVA);
				tabsol.setCelda(new Coordenada2D(i,1),EstadoCelda.MUERTA);
			}
		/*Fichero con los tableros que el alumno genera para que pueda hacer un seguimiento
		de sus actualizaciones en caso de error. */
			PrintStream s = abrirFichero("test/ficheros/tableros1Dtodovivo.sal"); 
			for (int j=0; j<2; j++) {
				
				for (int i=0; i<12; i++) {
					tab2.setCelda(new Coordenada2D(i,j), juego1.getTablero().getCelda(new Coordenada1D(i)));
				}
				s.print(juego1.getTablero().toString()); //Guarda tablero del alumno 
				//sb.append(juego1.getTablero().toString());
				juego1.actualiza();	
			}
			s.close();
		//Comprobación del contenido del tablero del alumno
			Coordenada c;
			for (int j=0; j<2; j++)
				for (int i=0; i<12; i++) {
					c = new Coordenada2D(i,j);
					assertEquals("Estado ("+i+","+j+")",tabsol.getCelda(c),tab2.getCelda(c));
				}
			} catch (Exception e) {
				fail("No se esperaba excepcion, pero se capturo "+e.getClass().getSimpleName());
			}
	}
	
	/**
	 * Test method for {@link modelo.Juego#getTablero()}.
	 */
	@Test
	public void testGetTablero() {
		
		assertSame("GetTablero",tablero,juego.getTablero());
	}

	/**
	 * Test method for {@link modelo.Juego#getPatrones()}.
	 */
	@Test
	public void testGetPatrones() {
		assertSame("GetPatrones",patrones,juego.getPatrones());
	}
	
	/*------------------------- FIN TESTS ----------------------*/
	
	/* FUNCIONES AUXILIARES */
	
	private static void CreaPatrones() {
		Tablero tableroPatron;
		try {
			tableroPatron = new Tablero1D(1);
		
		tableroPatron.setCelda(new Coordenada1D(0), EstadoCelda.VIVA);
		
		patronsimple = new Patron("Simple", tableroPatron);	

		// creamos otro patrón
		tableroPatron = new Tablero1D(3);
		tableroPatron.setCelda(new Coordenada1D(0), EstadoCelda.VIVA);
		tableroPatron.setCelda(new Coordenada1D(1), EstadoCelda.VIVA);
		tableroPatron.setCelda(new Coordenada1D(2), EstadoCelda.VIVA);

		patrontrio = new Patron("Trio", tableroPatron);		
		
			
		tableroPatron = new Tablero1D(9);
		tableroPatron.setCelda(new Coordenada1D(0), EstadoCelda.VIVA);
		tableroPatron.setCelda(new Coordenada1D(1), EstadoCelda.VIVA);
		tableroPatron.setCelda(new Coordenada1D(2), EstadoCelda.VIVA);

		tableroPatron.setCelda(new Coordenada1D(3), EstadoCelda.MUERTA);
		tableroPatron.setCelda(new Coordenada1D(4), EstadoCelda.MUERTA);
		tableroPatron.setCelda(new Coordenada1D(5), EstadoCelda.MUERTA);

		tableroPatron.setCelda(new Coordenada1D(6), EstadoCelda.VIVA);
		tableroPatron.setCelda(new Coordenada1D(7), EstadoCelda.VIVA);
		tableroPatron.setCelda(new Coordenada1D(8), EstadoCelda.VIVA);
		patronsos = new Patron("SOS", tableroPatron);
		
		} catch (Exception e) {
			fail("No se esperaba excepcion, pero se capturo "+e.getClass().getSimpleName());
		}
	}
	
	//Abre un fichero para escribir las tablas que genera el alumno.
	private PrintStream abrirFichero (String fichero) {
		PrintStream s=null;
		try {
			s = new PrintStream(fichero);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
	
  //Inicia un tablero con dimensiones dim a partir del contenido de un fichero
  private static Tablero IniciaTablerosResultado(Coordenada dim,String fichero) {
	   Scanner s;
	   Tablero tablero=null;
	   Coordenada2D dim2d= (Coordenada2D) dim;
	try {
		s = new Scanner(new File(fichero));
	
	   try {
		tablero = new TableroCeldasCuadradas(dim2d.getX(),dim2d.getY());
	
	   String linea;
	   for (int j=0; j<dim2d.getY(); j++) {
		   linea = s.nextLine();
		   for (int i=0; i<dim2d.getX()+1; i++){
			 
			 if (linea.charAt(i+1)==' ') tablero.setCelda(new Coordenada2D(i,j),EstadoCelda.MUERTA);
			 else if (linea.charAt(i+1)=='*') tablero.setCelda(new Coordenada2D(i,j), EstadoCelda.VIVA);
		   }
	   }
	    } catch (Exception e) {
			fail("No se esperaba excepcion, pero se capturo "+e.getClass().getSimpleName());
		}
	    s.close();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	    return tablero;
  }

}
  
  
