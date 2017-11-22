package mains;

import java.io.File;
import modelo.Coordenada;
import modelo.Coordenada1D;
import modelo.Coordenada2D;
import modelo.EstadoCelda;
import modelo.Imprimible;
import modelo.Juego;
import modelo.Patron;
import modelo.Regla;
import modelo.Regla30;
import modelo.ReglaConway;
import modelo.Tablero;
import modelo.Tablero1D;
import modelo.Tablero2D;
import modelo.TableroCeldasCuadradas;
import modelo.excepciones.ExcepcionArgumentosIncorrectos;
import modelo.excepciones.ExcepcionCoordenada1DIncorrecta;
import modelo.excepciones.ExcepcionCoordenada2DIncorrecta;
import modelo.excepciones.ExcepcionCoordenadaIncorrecta;
import modelo.excepciones.ExcepcionEjecucion;
import modelo.excepciones.ExcepcionPosicionFueraTablero;
import entradasalida.Factory;
import entradasalida.IGeneradorFichero;
import entradasalida.IParserTablero;
import entradasalida.ParserTableros;
import entradasalida.excepciones.ExcepcionGeneracion;
import entradasalida.excepciones.ExcepcionLectura;
import entradasalida.imagen.GeneradorGIFTablero1D;
import entradasalida.imagen.GeneradorGifAnimadoTablero2D;
import entradasalida.textoplano.GeneradorFicheroPlano;
import entradasalida.textoplano.ParserTablero1D;
import entradasalida.textoplano.ParserTablero2D;

public class MainCompilacionP4 {
	
	
	public static void main(String [] args) {
		
		// EstadoCelda es un enum
		EstadoCelda ec = EstadoCelda.MUERTA;
		ec = EstadoCelda.VIVA;
		int h = ec.ordinal();

		Tablero t1d = null;
		try {
			t1d = new Tablero1D(4);
		} catch (ExcepcionCoordenadaIncorrecta e6) {
			e6.printStackTrace();
		}
		Tablero t2d = null;
		try {
			t2d = new TableroCeldasCuadradas(4,6);
		} catch (ExcepcionCoordenadaIncorrecta e6) {
			e6.printStackTrace();
		}
		Coordenada1D c1d = null;
		try {
			c1d = new Coordenada1D(1);
		} catch (ExcepcionCoordenadaIncorrecta e6) {
			e6.printStackTrace();
		}
		Coordenada2D c2d = null;
		try {
			c2d = new Coordenada2D(1,2);
		} catch (ExcepcionCoordenadaIncorrecta e6) {
			e6.printStackTrace();
		}
		Tablero t = t1d;
		Patron p1d = new Patron("1D", t1d);
		Patron p2d = new Patron("2D", t2d);
		Regla r1d = new Regla30();
		Regla r2d = new ReglaConway();
		Juego j = new Juego(t2d, r2d);
				
		try { 
			Coordenada c1 = new Coordenada1D(5);
			Coordenada c22 = new Coordenada1D((Coordenada1D)c1);
			boolean bb = c1.equals(c22);
			bb = c1.equals(new Integer(2));
			int xx = ((Coordenada1D) c1).getX();
			String ss = c1.toString();
			int hh = c1.hashCode();		
			Coordenada c33 = c1.suma(c22);
		} catch (ExcepcionCoordenadaIncorrecta ex) {}
		try { 
			Coordenada c1 = new Coordenada2D(1,5);
			Coordenada c22 = new Coordenada2D((Coordenada2D)c1);
			boolean bb = c1.equals(c22);
			bb = c1.equals(new Integer(2));
			int xx = ((Coordenada2D) c1).getX();
			int yy = ((Coordenada2D) c1).getY();
			String ss = c1.toString();
			int hh = c1.hashCode();		
			Coordenada c33 = c1.suma(c22);
		} catch (ExcepcionCoordenadaIncorrecta ex) {}
		try { c1d.suma(c1d); } catch (ExcepcionCoordenadaIncorrecta ex) {}
		try { Tablero t1 = new Tablero1D(10); } catch (ExcepcionCoordenadaIncorrecta ex) {}
		try { Tablero t2 = new TableroCeldasCuadradas(4,6); } catch (ExcepcionCoordenadaIncorrecta ex) {}
		try { t.getCelda(c1d); } catch (ExcepcionPosicionFueraTablero ex) {}
		try { t.setCelda(c1d, EstadoCelda.VIVA); } catch (ExcepcionPosicionFueraTablero ex) {}
		try { t.cargaPatron(p1d, c1d); } catch (ExcepcionPosicionFueraTablero ex) {}
		try { t.getPosicionesVecinasCCW(c1d); } catch (ExcepcionPosicionFueraTablero ex) {}
		try { p1d.getCelda(c1d); } catch (ExcepcionPosicionFueraTablero ex) {}
		try { p2d.getCelda(c1d); } catch (ExcepcionPosicionFueraTablero ex) {}
		try { r1d.calculaSiguienteEstadoCelda(t, c1d); } catch (ExcepcionPosicionFueraTablero ex) {}
		try { r2d.calculaSiguienteEstadoCelda(t, c1d); } catch (ExcepcionPosicionFueraTablero ex) {}
		try { j.cargaPatron(p2d, c2d); } catch (ExcepcionPosicionFueraTablero ex) {}
				
		try {
			//Coordenada
			Coordenada c1 = new Coordenada1D(new Coordenada1D(5));
			Coordenada c2 = new Coordenada2D(new Coordenada2D(1,2));
			c1.suma(c1);
			c2.suma(c2);
			c1.toString();
			c2.toString();
			Coordenada1D c1d1= (Coordenada1D)c1;
			Coordenada2D c2d1= (Coordenada2D)c2;
			c1d1.equals(c1);
			c2d1.equals(c2);
			c1d1.getX();
			c2d1.getX(); c2d1.getY();
				
			//Tablero
			Tablero t1 = new Tablero1D(10);
			Tablero t2 = new TableroCeldasCuadradas(4,6);
			Tablero2D t3 = (Tablero2D)t2;
			t1.getDimensiones(); t1.getPosiciones(); t1.getCelda(c1);
			t1.setCelda(c1, EstadoCelda.MUERTA);
			t1.cargaPatron(p1d, c1);
			t1.contiene(c1);
			Tablero1D t1d1 = (Tablero1D)t1;
			TableroCeldasCuadradas t2dc = (TableroCeldasCuadradas)t2;
			t1d1.getPosicionesVecinasCCW(c1); t1d1.toString();
			t2dc.getPosicionesVecinasCCW(c2); t2dc.toString();
			
			//Patron
			Patron p = new Patron("Test",t1);
			p.getNombre(); p.getCelda(c1); p.getPosiciones(); p.toString();
			
			//Regla
			Regla rc = new ReglaConway();
			Regla r30 = new Regla30();
			rc.calculaSiguienteEstadoCelda(t2,c2);
			r30.calculaSiguienteEstadoCelda(t1,c1);
				
			//Juego
			Juego j1 = new Juego(t1,r30);
			j1.cargaPatron(p1d,c1);
			j1.actualiza();
			j1.getTablero();
			j1.getPatrones();
					
			//EstadoCelda
			EstadoCelda estado = EstadoCelda.MUERTA;
			estado = EstadoCelda.VIVA;
			
			//Excepciones
			if (false) throw new ExcepcionCoordenada1DIncorrecta(10);
			if (false) throw new ExcepcionCoordenada2DIncorrecta(10,10);
			if (false) throw new ExcepcionCoordenadaIncorrecta();
			if (false) throw new ExcepcionArgumentosIncorrectos();
			if (false) throw new ExcepcionPosicionFueraTablero(c1,c1);
			if (false) throw new ExcepcionEjecucion("Error");
					
		} catch (ExcepcionCoordenada1DIncorrecta e) {
			e.getMessage();
			e.getX();
			e.printStackTrace();
		} catch (ExcepcionPosicionFueraTablero e1) {
			e1.getMessage();
			e1.getDimensiones();
			e1.getCoordenada();
			e1.printStackTrace();
		} catch (ExcepcionCoordenada2DIncorrecta e2) {
			e2.getMessage(); 
			e2.getX(); e2.getY();
			e2.printStackTrace();
		} catch (ExcepcionArgumentosIncorrectos e3) {
			e3.printStackTrace();
		} catch (ExcepcionEjecucion e4) {
			e4.printStackTrace();
		} catch (ExcepcionCoordenadaIncorrecta e5) {
			e5.printStackTrace();
		}
		
		try {	
			//Factory, IGeneradorFichero
			Tablero tablero = Factory.creaTablero(new Coordenada1D(10));
			Regla regla = Factory.creaRegla(tablero);		
			Juego juego = new Juego(tablero,regla);
			IGeneradorFichero igf = Factory.creaGeneradorFichero(tablero,"gif");
			igf.generaFichero(new File ("fichero.txt"), juego, 10);
			
			GeneradorGIFTablero1D generaunod = (GeneradorGIFTablero1D)igf;
			generaunod.generaFichero(new File("fichero.txt"),juego,20);
			
			IGeneradorFichero generaunod2 = (GeneradorGIFTablero1D)igf;
			generaunod2.generaFichero(new File("fichero.txt"),juego,20);
			
			tablero = Factory.creaTablero(new Coordenada2D(10,5));
			regla = Factory.creaRegla(tablero);
			juego = new Juego(tablero,regla);
			igf = Factory.creaGeneradorFichero(tablero, "gif");
			GeneradorGifAnimadoTablero2D generadosd = (GeneradorGifAnimadoTablero2D)igf;
			generadosd.generaFichero(new File("fichero.gif"), juego,20);
			
			IGeneradorFichero generadosd2 = (GeneradorGifAnimadoTablero2D)igf;
			generadosd2.generaFichero(new File("fichero.gif"), juego,20);
		
			igf = Factory.creaGeneradorFichero(tablero, "txt");
			GeneradorFicheroPlano gfp=(GeneradorFicheroPlano)igf;
			gfp.generaFichero(new File("fichero.txt"), juego, 10);
			
			IGeneradorFichero gfp2=(GeneradorFicheroPlano)igf;
			gfp2.generaFichero(new File("fichero.txt"), juego, 10);
	
			//ParserTablero, IParserTablero		
			ParserTableros.leeTablero("*****");
			
			IParserTablero parser = new ParserTablero2D();
			parser.leeTablero("    \n    "); 
			ParserTablero2D parserdosd= (ParserTablero2D)parser;
			tablero=parserdosd.leeTablero("  ***\n*****");
			parser = new ParserTablero1D();
			ParserTablero1D parserunod= (ParserTablero1D)parser;
			parserunod.leeTablero(" *  *");
			
			IParserTablero parserdosd2= (ParserTablero2D)parserdosd;
			IParserTablero parserunod2= (ParserTablero1D)parserunod;
			parserunod2.leeTablero(" *  *");
			
			//Imprimible
			Imprimible imp = new TableroCeldasCuadradas(10,2);
			imp.generaCadena();
			TableroCeldasCuadradas tabdosd = (TableroCeldasCuadradas)imp;
			tabdosd.generaCadena();
			imp = new Tablero1D(20);
			Tablero1D tabunod = (Tablero1D) imp;
			tabunod.generaCadena();
			
		} catch (ExcepcionGeneracion e) {
			e.printStackTrace();
		} catch (ExcepcionCoordenadaIncorrecta  e) {
			e.printStackTrace();
		} catch (ExcepcionLectura e) {
			e.printStackTrace(); // esto no debe dar nunca
		}
		System.out.println("¡EXITO!");
	}
}
