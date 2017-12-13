package modelo;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Regla30Test {

	private Tablero1D tablero;
	private static Regla30 regla;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		regla = new Regla30();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		/*
		 * MMM 
		*/
	
		tablero = new Tablero1D(3);
	
	}

	
	@Test
	public void testCalculaSiguienteEstadoCeldaTodasMuertas() {
		Coordenada1D pos;
		for (int x=0; x<3;x++)
				try {
					pos=new Coordenada1D(x);
					assertEquals ("Todas muertas", EstadoCelda.MUERTA, regla.calculaSiguienteEstadoCelda(tablero, pos));
				
				} catch (Exception e) {
				// TODO Auto-generated catch block
				fail ("No se esperaba excepción, pero se capturo "+e.getClass().getSimpleName());
				}
	}
	
	@Test
	public void testCalculaSiguienteEstadoCeldaExtremoIzquierda() {
		Coordenada1D pos,pos1;
	
		//Extremo izquierda MM...  MV...  VM... VV...
		try {
			pos = new Coordenada1D(0);
			for (int j=0; j<2; j++) {
				for (int i=0; i<2; i++) {
					pos1 = new Coordenada1D(1);
					assertEquals ("Coordenada(0) = Muerta ",EstadoCelda.MUERTA,regla.calculaSiguienteEstadoCelda(tablero,pos));
					tablero.setCelda(pos1, EstadoCelda.VIVA);
				}
				tablero.setCelda(pos,EstadoCelda.VIVA);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail ("No se esperaba excepción, pero se capturo "+e.getClass().getSimpleName());
		}
	}
		
		@Test
		public void testCalculaSiguienteEstadoCeldaExtremoDerecha() {
			Coordenada1D pos,pos1;
			
		
		//Extremo derecha ...MM  ...VM ...MV ...VV	
			try {	
			pos = new Coordenada1D(2);
			for (int j=0; j<2; j++) {
				for (int i=0; i<2; i++) {
						pos1 = new Coordenada1D(1);
						assertEquals ("Coordenada(2) = Muerta ",EstadoCelda.MUERTA,regla.calculaSiguienteEstadoCelda(tablero,pos));
						tablero.setCelda(pos1, EstadoCelda.VIVA);
				}
				tablero.setCelda(pos,EstadoCelda.VIVA);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail ("No se esperaba excepción, pero se capturo "+e.getClass().getSimpleName());
		}
		
	}
	
	@Test
	public void testCalculaSiguienteEstadoCeldasCentralesPosMuerta() {
		Coordenada1D pos;
		ArrayList<Coordenada1D> vc = new ArrayList<Coordenada1D>();
		
		/*
		 * MMM  MMV  VMM VMV
		*/
		try {
			vc.add(new Coordenada1D(0));
			vc.add(new Coordenada1D(2));	
			pos = new Coordenada1D(1);
		
			for (Coordenada1D pos1:vc) {
				if (tablero.getCelda(new Coordenada1D(0))==tablero.getCelda(new Coordenada1D(2)))  
				assertEquals ("(000)o(101) = Muerta ",EstadoCelda.MUERTA,regla.calculaSiguienteEstadoCelda(tablero,pos));
			else 
				assertEquals ("(001)o(100) = Viva ",EstadoCelda.VIVA,regla.calculaSiguienteEstadoCelda(tablero,pos));
			tablero.setCelda(pos1, EstadoCelda.VIVA);
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail ("No se esperaba excepción, pero se capturo "+e.getClass().getSimpleName());
		}
	}
		
	@Test
	public void testCalculaSiguienteEstadoCeldasCentralesPosViva() {
		Coordenada1D pos;
		ArrayList<Coordenada1D> vc = new ArrayList<Coordenada1D>();
		
		/*
		 * MVM  MVV  VVM  VVV
		*/
		try {
			vc.add(new Coordenada1D(0));
			vc.add(new Coordenada1D(2));	
			pos = new Coordenada1D(1);
			tablero.setCelda(pos,EstadoCelda.VIVA);
			for (Coordenada1D pos1:vc) {
				if (tablero.getCelda(new Coordenada1D(0))==EstadoCelda.VIVA)  
				assertEquals ("(110)o(111) = Muerta ",EstadoCelda.MUERTA,regla.calculaSiguienteEstadoCelda(tablero,pos));
			else 
				assertEquals ("(010)o(011) = Viva ",EstadoCelda.VIVA,regla.calculaSiguienteEstadoCelda(tablero,pos));
			tablero.setCelda(pos1, EstadoCelda.VIVA);
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail ("No se esperaba excepción, pero se capturo "+e.getClass().getSimpleName());
		}
	}
	
}
