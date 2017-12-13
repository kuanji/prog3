package modelo;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ReglaConwayP2Test {

	private TableroCeldasCuadradas tablero;
	private static ReglaConway regla;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		regla = new ReglaConway();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		/*
		 * MMM
		 * MMM
		 * MMM
		 */
	
		tablero = new TableroCeldasCuadradas(3,3);
		
		
	}

	/**
	 * Test method for {@link modelo.ReglaConway#calculaSiguienteEstadoCelda(modelo.Tablero, modelo.Coordenada2D)}.
	 */
	@Test
	public void testCalculaSiguienteEstadoCeldaTodasMuertas() {
		Coordenada2D pos;
		for (int x=0; x<3;x++)
			for(int y=0; y<3;y++) {
				try {
					pos=new Coordenada2D(x,y);
					assertEquals ("Todas muertas", EstadoCelda.MUERTA, regla.calculaSiguienteEstadoCelda(tablero, pos));
				
				} catch (Exception e) {
				// TODO Auto-generated catch block
				fail ("No se esperaba excepción, pero se capturo "+e.getClass().getSimpleName());
				}
			}
	}
	
	@Test
	public void testCalculaSiguienteEstadoCeldaMuerta1Viva() {
		Coordenada2D pos1;
		try {
			pos1 = new Coordenada2D(0,0);
			Coordenada2D pos2 =new Coordenada2D(0,1);
			Coordenada2D pos3 =new Coordenada2D(1,1);
			Coordenada2D pos4 =new Coordenada2D(2,0);
			Coordenada2D pos5 =new Coordenada2D(2,1);
			Coordenada2D pos6 =new Coordenada2D(0,2);
			Coordenada2D pos7 =new Coordenada2D(1,2);
		/*
		 * MVM
		 * MMM
		 * MMM
		 */
			tablero.setCelda(new Coordenada2D(1,0), EstadoCelda.VIVA);
			assertEquals ("Muerta(0,0)con 1 Viva = Muerta ",EstadoCelda.MUERTA,regla.calculaSiguienteEstadoCelda(tablero,pos1));
			assertEquals ("Muerta(0,1)con 1 Viva = Muerta ",EstadoCelda.MUERTA,regla.calculaSiguienteEstadoCelda(tablero,pos2));
			assertEquals ("Muerta(1,1)con 1 Viva = Muerta ",EstadoCelda.MUERTA,regla.calculaSiguienteEstadoCelda(tablero,pos3));		
			assertEquals ("Muerta(2,0)con 1 Viva = Muerta ",EstadoCelda.MUERTA,regla.calculaSiguienteEstadoCelda(tablero,pos4));
			assertEquals ("Muerta(2,1)con 1 Viva = Muerta ",EstadoCelda.MUERTA,regla.calculaSiguienteEstadoCelda(tablero,pos5));	
		
		/*
		 * MVM
		 * VMM
		 * MMM
		 */
			tablero.setCelda(pos2, EstadoCelda.VIVA);
			assertEquals ("Muerta(0,2)con 1 Viva = Muerta ",EstadoCelda.MUERTA,regla.calculaSiguienteEstadoCelda(tablero,pos6));
			assertEquals ("Muerta(1,2)con 1 Viva = Muerta ",EstadoCelda.MUERTA,regla.calculaSiguienteEstadoCelda(tablero,pos7));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail ("No se esperaba excepción, pero se capturo "+e.getClass().getSimpleName());
		}
	}
	
	@Test
	public void testCalculaSiguienteEstadoCeldaMuerta2Vivas() {
		Coordenada2D pos1;
		try {
			pos1 = new Coordenada2D(0,0);
			Coordenada2D pos2 =new Coordenada2D(1,1);
		
		/*
		 * MVM
		 * VMM
		 * MMM
		 */
			tablero.setCelda(new Coordenada2D(1,0), EstadoCelda.VIVA);
			tablero.setCelda(new Coordenada2D(0,1), EstadoCelda.VIVA);
			assertEquals ("Muerta(0,0)con 2 Vivas = Muerta ",EstadoCelda.MUERTA,regla.calculaSiguienteEstadoCelda(tablero,pos1));
			assertEquals ("Muerta(1,1)con 2 Vivas = Muerta ",EstadoCelda.MUERTA,regla.calculaSiguienteEstadoCelda(tablero,pos2));		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail ("No se esperaba excepción, pero se capturo "+e.getClass().getSimpleName());
			}
		}
		
	@Test
	public void testCalculaSiguienteEstadoCeldaMuerta3Vivas() {
		Coordenada2D pos1;
		try {
			pos1 = new Coordenada2D(1,0);
			Coordenada2D pos2 =new Coordenada2D(1,2);
		/*
		 * MMM
		 * VVV
		 * MMM
		 */
			tablero.setCelda(new Coordenada2D(0,1), EstadoCelda.VIVA);
			tablero.setCelda(new Coordenada2D(1,1), EstadoCelda.VIVA);
			tablero.setCelda(new Coordenada2D(2,1),EstadoCelda.VIVA);
			assertEquals ("Muerta(1,0)con 3 Viva = Viva ",EstadoCelda.VIVA,regla.calculaSiguienteEstadoCelda(tablero,pos1));
			assertEquals ("Muerta(1,2)con 3 Viva = Viva ",EstadoCelda.VIVA,regla.calculaSiguienteEstadoCelda(tablero,pos2));		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail ("No se esperaba excepción, pero se capturo "+e.getClass().getSimpleName());
		}
	}	
	
	@Test
	public void testCalculaSiguienteEstadoCeldaMuerta4Vivas() {
		Coordenada2D pos1;
		try {
			pos1 = new Coordenada2D(1,0);
		/*
		 * MMV
		 * VVV
		 * MMM
		 */
			tablero.setCelda(new Coordenada2D(0,1), EstadoCelda.VIVA);
			tablero.setCelda(new Coordenada2D(1,1), EstadoCelda.VIVA);
			tablero.setCelda(new Coordenada2D(2,1),EstadoCelda.VIVA);
			tablero.setCelda(new Coordenada2D(2,0), EstadoCelda.VIVA);
		
			assertEquals ("Muerta(1,0)con 4 Viva = Muerta ",EstadoCelda.MUERTA,regla.calculaSiguienteEstadoCelda(tablero,pos1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail ("No se esperaba excepción, pero se capturo "+e.getClass().getSimpleName());
		}
	}	
	
	@Test
	public void testCalculaSiguienteEstadoCeldaViva0145Vivas() {
		Coordenada2D pos1;
		try {
			pos1 = new Coordenada2D(0,1);
			Coordenada2D pos2 =new Coordenada2D(1,1);	
			Coordenada2D pos3 =new Coordenada2D(2,1);
			Coordenada2D pos4 =new Coordenada2D(1,0);
			Coordenada2D pos5 =new Coordenada2D(0,0);
			Coordenada2D pos6 =new Coordenada2D(2,0);
		/*
		 * MMM
		 * VMM
		 * MMM
		 */
			tablero.setCelda(pos1, EstadoCelda.VIVA);
			assertEquals ("Viva(0,1)con 0 Vivas = Muerta ",EstadoCelda.MUERTA,regla.calculaSiguienteEstadoCelda(tablero,pos1));
		
		/*
		 * MMM
		 * VVM
		 * MMM
		 */
			tablero.setCelda(pos2, EstadoCelda.VIVA);
			assertEquals ("Viva(0,1)con 1 Viva = Muerta ",EstadoCelda.MUERTA,regla.calculaSiguienteEstadoCelda(tablero,pos1));
			assertEquals ("Viva(1,1)con 1 Viva = Muerta ",EstadoCelda.MUERTA,regla.calculaSiguienteEstadoCelda(tablero,pos2));
		
		/*
		 * VVM
		 * VVV
		 * MMM
		 */
			tablero.setCelda(pos3, EstadoCelda.VIVA);
			tablero.setCelda(pos4, EstadoCelda.VIVA);
			tablero.setCelda(pos5,EstadoCelda.VIVA);
			assertEquals ("Viva(1,0)con 4 Vivas = Muerta ",EstadoCelda.MUERTA,regla.calculaSiguienteEstadoCelda(tablero,pos4));
		
		/*
		 * VVV
		 * VVV
		 * MMM
		 */
			tablero.setCelda(pos6,EstadoCelda.VIVA);
			assertEquals ("Viva(1,0)con 5 Vivas = Muerta ",EstadoCelda.MUERTA,regla.calculaSiguienteEstadoCelda(tablero,pos4));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail ("No se esperaba excepción, pero se capturo "+e.getClass().getSimpleName());
		}
	}	
	
	@Test
	public void testCalculaSiguienteEstadoCeldaViva23Vivas() {
		Coordenada2D pos1;
		try {
			pos1 = new Coordenada2D(0,1);	
			Coordenada2D pos2 =new Coordenada2D(1,1);	
			Coordenada2D pos3 =new Coordenada2D(2,1);
			Coordenada2D pos4 =new Coordenada2D(1,2);
		/*
		 * MMM
		 * VVV
		 * MMM
		 */
				
			tablero.setCelda(pos1, EstadoCelda.VIVA);
			tablero.setCelda(pos2, EstadoCelda.VIVA);
			tablero.setCelda(pos3, EstadoCelda.VIVA);
			assertEquals ("Viva(1,1)con 2 Vivas = Viva ",EstadoCelda.VIVA,regla.calculaSiguienteEstadoCelda(tablero,pos2));
		
		/*
		 * MMM
		 * VVV
		 * MVM
		 */
			tablero.setCelda(pos4, EstadoCelda.VIVA);
			assertEquals ("Viva(1,2)con 3 Vivas = Viva ",EstadoCelda.VIVA,regla.calculaSiguienteEstadoCelda(tablero,pos4));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail ("No se esperaba excepción, pero se capturo "+e.getClass().getSimpleName());
		}
	}	

}
