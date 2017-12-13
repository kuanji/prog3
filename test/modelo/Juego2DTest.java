package modelo;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import modelo.excepciones.ExcepcionCoordenada2DIncorrecta;
import modelo.excepciones.ExcepcionPosicionFueraTablero;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Juego2DTest {

    ReglaConway regla;
    ArrayList<Patron> patrones;
    Tablero tablero;
    Juego juego;
    Coordenada dimension;
    static Patron patronglider, patronbloque, patronparpadeador, patronbarco, patronsapo;
    static String tablero1, tablero2, tablero3, tablero4;
    static Tablero tablerodelmain, tableroparpadeadores, tableroconmaspatrones;
 
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	    tablerodelmain = IniciaTablerosResultado(new Coordenada2D(10,5),"test/ficheros/tablero2Ddelmain.ent");
	    tableroparpadeadores = IniciaTablerosResultado(new Coordenada2D(10,1),"test/ficheros/tablero2Ddelmain.ent");
	    tableroconmaspatrones = IniciaTablerosResultado(new Coordenada2D(10,15),"test/ficheros/tablero2Dconmaspatrones.ent");
	    CreaPatrones();
	}


	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		dimension = new Coordenada2D(10,15);
		tablero = new TableroCeldasCuadradas(10,15);
		regla = new ReglaConway();
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
		
		
			try {
				juego.cargaPatron(patronglider, new Coordenada2D(8,10));
				fail("Se debió producir ExcepcionPosicionFueraTablero");
			} catch (ExcepcionPosicionFueraTablero e) {
				//assertEquals("e.getDimensiones",dimension,e.getDimensiones());
				
			} catch (Exception e) {
					fail("Se esperaba ExcepcionPosicionFueraTablero, pero se capturo "+e.getClass().getSimpleName());
					
			}
			assertEquals("No carga el patrón",0,patrones.size());
			
			
			try {
				juego.cargaPatron(patronglider, new Coordenada2D(7,11));
			} catch (Exception e) {
				fail("No se esperaba excepcion, pero se capturo "+e.getClass().getSimpleName());
			}
			assertEquals("Sí carga el patrón",1, patrones.size());
			assertSame("Agregación", patronglider,patrones.get(0));
		
			try {
				juego.cargaPatron(patronbloque, new Coordenada2D(-1,2));
				fail("Se debió producir ExcepcionCoordenada2DIncorrecta");
			} catch (ExcepcionCoordenada2DIncorrecta e) {
				/*assertEquals("e.getX",-1,e.getX());
				assertEquals("e.getY",2,e.getY());*/
			} catch (Exception e) {
					fail("Se esperaba ExcepcionCoordenadaIncorrecta, pero se capturo "+e.getClass().getSimpleName());
					
			}
			assertEquals("No carga el patrón",1,patrones.size());
			
			try {
				juego.cargaPatron(patronbloque, new Coordenada2D(0,2));
			} catch (Exception e) {
				fail("No se esperaba excepcion, pero se capturo "+e.getClass().getSimpleName());
			}
			assertEquals("Sí carga el patrón",2,patrones.size());
			assertSame("Agregación", patronbloque,patrones.get(1));
		
			try {
				juego.cargaPatron(patronparpadeador, new Coordenada2D(0,15));
				fail("Se debió producir ExcepcionPosicionFueraTablero");
			} catch (ExcepcionPosicionFueraTablero e) {
				//assertEquals("e.getDimensiones",dimension,e.getDimensiones());
			} catch (Exception e) {
					fail("Se esperaba ExcepcionPosicionFueraTablero, pero se capturo "+e.getClass().getSimpleName());
					
			}
			
			try {
				juego.cargaPatron(patronparpadeador, new Coordenada2D(0,-1));
				fail("Se debió producir ExcepcionCoordenada2DIncorrecta");
			} catch (ExcepcionCoordenada2DIncorrecta e) {
				/*assertEquals("e.getX",0,e.getX());
				assertEquals("e.getY",-1,e.getY());*/
			} catch (Exception e) {
					fail("Se esperaba ExcepcionCoordenadaIncorrecta, pero se capturo "+e.getClass().getSimpleName());
					
			}
			assertEquals("No carga el patrón",2,patrones.size());
			
			try {
				juego.cargaPatron(patronparpadeador, new Coordenada2D(0,14));
			} catch (Exception e) {
				fail("No se esperaba excepcion, pero se capturo "+e.getClass().getSimpleName());
			}
			assertEquals("Sí carga el patrón",3,patrones.size());
			assertSame("Agregación", patronparpadeador,patrones.get(2));
		
	}

	/**
	 * Test method for {@link modelo.Juego#actualiza()}.
	 */
	@Test
	public void testActualizaTableroDelMain() {
		
		Juego juego1=null;
		try {
			juego1 = new Juego(new TableroCeldasCuadradas(10,5),regla);
			juego1.cargaPatron(patronglider, new Coordenada2D(0,0));
			juego1.cargaPatron(patronbloque, new Coordenada2D(8,3)); 
			juego1.cargaPatron(patronparpadeador, new Coordenada2D(7,0));
		} catch (Exception e) {
			fail("No se esperaba excepcion, pero se capturo "+e.getClass().getSimpleName());
		}
	
		
		/*Fichero con los tableros que el alumno genera.Para que pueda
		analizar sus actualizaciones sucesivas en caso de error */
		PrintStream s = abrirFichero("test/ficheros/tablerosdelmaindelalumno.sal"); 
		for (int i=0; i<5; i++) {

			s.print(juego1.getTablero().toString()); //Guarda tablero del alumno
			juego1.actualiza();
		}
		s.close();
		
		//Comprobación del tablero final del alumno.
		Coordenada c;
		for (int i=0; i<10; i++)
			for (int j=0; j<5; j++) {
				try {
					c = new Coordenada2D(i,j);
					assertEquals("Estado ("+i+","+j+")",tablerodelmain.getCelda(c),juego1.getTablero().getCelda(c));
				} catch (Exception e) {
					fail("No se esperaba excepcion, pero se capturo "+e.getClass().getSimpleName());
				}	
			}
	}
	
	@Test
	public void testActualizaParpadeadoresSolapados() {
		
		Juego juego1=null;
		try {
			juego1 = new Juego(new TableroCeldasCuadradas(10,1),regla);
			juego1.cargaPatron(patronparpadeador, new Coordenada2D(1,0));
			juego1.cargaPatron(patronparpadeador, new Coordenada2D(3,0)); 
			juego1.cargaPatron(patronparpadeador, new Coordenada2D(4,0));
		} catch (Exception e) {
			fail("No se esperaba excepcion, pero se capturo "+e.getClass().getSimpleName());
		}
		
		/*Fichero con los tableros que el alumno genera.Para que en caso de error pueda
		hacer un seguimiento de sus actualizaciones */
		PrintStream s = abrirFichero("test/ficheros/tableros2Dparpadeadores.sal"); 
		for (int i=0; i<4; i++) {

			s.print(juego1.getTablero().toString()); //Guarda tablero del alumno 
			juego1.actualiza();
		}
		s.close();
		
		//Comprobamos el contenido del tablero del alumno
		Coordenada c;
		for (int i=0; i<10; i++)
			for (int j=0; j<1; j++) {
				try {
					c = new Coordenada2D(i,j);
					assertEquals("Estado ("+i+","+j+") = MUERTA",EstadoCelda.MUERTA,juego1.getTablero().getCelda(c));
				} catch (Exception e) {
					fail("No se esperaba excepcion, pero se capturo "+e.getClass().getSimpleName());
			}
		}
	}
	
	@Test
	public void testActualizaConMasPatrones() {
		
		try {
			juego.cargaPatron(patronglider, new Coordenada2D(0,0));
			juego.cargaPatron(patronbloque, new Coordenada2D(8,3)); 
			juego.cargaPatron(patronparpadeador, new Coordenada2D(7,0));
			juego.cargaPatron(patronglider, new Coordenada2D(7,12));
			juego.cargaPatron(patronbloque, new Coordenada2D(3,8)); 
			juego.cargaPatron(patronparpadeador, new Coordenada2D(0,14));
			juego.cargaPatron(patronbarco, new Coordenada2D(0,4)); 
			juego.cargaPatron(patronsapo, new Coordenada2D(3,12));
			juego.cargaPatron(patronbarco, new Coordenada2D(6,8)); 
			juego.cargaPatron(patronsapo, new Coordenada2D(3,3));
	
		/*Fichero con los tableros que el alumno genera en cada actualización para que
		pueda hacer un seguimiento en caso de error. */
		PrintStream s = abrirFichero("test/ficheros/tableros2Dconmaspatrones.sal"); 
		for (int i=0; i<9; i++) {
			s.print(juego.getTablero().toString()); //Guarda tablero del alumno 
			juego.actualiza();
		}
		s.close();
		
		//Comprobación del contenido del tablero del alumno
		Coordenada c;
		for (int i=0; i<10; i++)
			for (int j=0; j<15; j++) {
				c = new Coordenada2D(i,j);
				assertEquals("Estado ("+i+","+j+")",tableroconmaspatrones.getCelda(c),juego.getTablero().getCelda(c));
			}
		} catch (Exception e) {
			fail("No se esperaba excepcion, pero se capturo "+e.getClass().getSimpleName());
		}
	}

	@Test
	public void testActualizaTodoVivo() {
	
		Juego juego1;
		try {
			juego1 = new Juego(new TableroCeldasCuadradas(4,4),regla);
			juego1.cargaPatron(patronbloque, new Coordenada2D(0,0));
			juego1.cargaPatron(patronbloque, new Coordenada2D(2,0)); 
			juego1.cargaPatron(patronbloque, new Coordenada2D(0,2));
			juego1.cargaPatron(patronbloque, new Coordenada2D(2,2));
	
	
		/*Fichero con los tableros que el alumno genera para que pueda hacer un seguimiento
		de sus actualizaciones en caso de error. */
			PrintStream s = abrirFichero("test/ficheros/tableros2Dtodovivo.sal"); 
			for (int i=0; i<3; i++) {
				s.print(juego1.getTablero().toString()); //Guarda tablero del alumno 
				juego1.actualiza();
			}
			s.close();
		
		//Comprobación del contenido del tablero del alumno
			Coordenada c;
			for (int i=0; i<4; i++)
				for (int j=0; j<4; j++) {
					c = new Coordenada2D(i,j);
					assertEquals("Estado ("+i+","+j+")=MUERTA",EstadoCelda.MUERTA,juego1.getTablero().getCelda(c));
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
			tableroPatron = new TableroCeldasCuadradas(3,3);
		
		tableroPatron.setCelda(new Coordenada2D(0,0), EstadoCelda.MUERTA);
		tableroPatron.setCelda(new Coordenada2D(1,0), EstadoCelda.VIVA);
		tableroPatron.setCelda(new Coordenada2D(2,0), EstadoCelda.MUERTA);

		tableroPatron.setCelda(new Coordenada2D(0,1), EstadoCelda.MUERTA);
		tableroPatron.setCelda(new Coordenada2D(1,1), EstadoCelda.MUERTA);
		tableroPatron.setCelda(new Coordenada2D(2,1), EstadoCelda.VIVA);

		tableroPatron.setCelda(new Coordenada2D(0,2), EstadoCelda.VIVA);
		tableroPatron.setCelda(new Coordenada2D(1,2), EstadoCelda.VIVA);
		tableroPatron.setCelda(new Coordenada2D(2,2), EstadoCelda.VIVA);
		patronglider = new Patron("Glider", tableroPatron);	

		// creamos otro patrón
		tableroPatron = new TableroCeldasCuadradas(2,2);
		tableroPatron.setCelda(new Coordenada2D(0,0), EstadoCelda.VIVA);
		tableroPatron.setCelda(new Coordenada2D(1,0), EstadoCelda.VIVA);

		tableroPatron.setCelda(new Coordenada2D(0,1), EstadoCelda.VIVA);
		tableroPatron.setCelda(new Coordenada2D(1,1), EstadoCelda.VIVA);

		patronbloque = new Patron("Bloque", tableroPatron);		
		
		// otro más
		tableroPatron = new TableroCeldasCuadradas(3,1);
		tableroPatron.setCelda(new Coordenada2D(0,0), EstadoCelda.VIVA);
		tableroPatron.setCelda(new Coordenada2D(1,0), EstadoCelda.VIVA);
		tableroPatron.setCelda(new Coordenada2D(2,0), EstadoCelda.VIVA);

		patronparpadeador = new Patron("Parpadeador", tableroPatron);	
		
		tableroPatron = new TableroCeldasCuadradas(3,3);
		tableroPatron.setCelda(new Coordenada2D(0,0), EstadoCelda.VIVA);
		tableroPatron.setCelda(new Coordenada2D(1,0), EstadoCelda.VIVA);
		tableroPatron.setCelda(new Coordenada2D(2,0), EstadoCelda.MUERTA);

		tableroPatron.setCelda(new Coordenada2D(0,1), EstadoCelda.VIVA);
		tableroPatron.setCelda(new Coordenada2D(1,1), EstadoCelda.MUERTA);
		tableroPatron.setCelda(new Coordenada2D(2,1), EstadoCelda.VIVA);

		tableroPatron.setCelda(new Coordenada2D(0,2), EstadoCelda.MUERTA);
		tableroPatron.setCelda(new Coordenada2D(1,2), EstadoCelda.VIVA);
		tableroPatron.setCelda(new Coordenada2D(2,2), EstadoCelda.MUERTA);
		patronbarco = new Patron("Barco", tableroPatron);
		
		tableroPatron = new TableroCeldasCuadradas(4,2);
		tableroPatron.setCelda(new Coordenada2D(0,0), EstadoCelda.MUERTA);
		tableroPatron.setCelda(new Coordenada2D(1,0), EstadoCelda.VIVA);
		tableroPatron.setCelda(new Coordenada2D(2,0), EstadoCelda.VIVA);
		tableroPatron.setCelda(new Coordenada2D(3,0), EstadoCelda.VIVA);

		tableroPatron.setCelda(new Coordenada2D(0,1), EstadoCelda.VIVA);
		tableroPatron.setCelda(new Coordenada2D(1,1), EstadoCelda.VIVA);
		tableroPatron.setCelda(new Coordenada2D(2,1), EstadoCelda.VIVA);
		tableroPatron.setCelda(new Coordenada2D(3,1), EstadoCelda.MUERTA);
		patronsapo = new Patron("Sapo", tableroPatron);
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
	
	   s.nextLine();
	   String linea;
	   for (int j=0; j<dim2d.getY(); j++) {
		   linea = s.nextLine();
		   for (int i=0; i<dim2d.getX()+1; i++)
		   {
			 
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
