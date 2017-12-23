package mains;

import java.io.File;

import modelo.d1.Coordenada1D;
import modelo.d2.Coordenada2D;
import modelo.Imprimible;
import modelo.Juego;
import modelo.Patron;
import modelo.Regla;
import modelo.Tablero;
import modelo.d1.Tablero1D;
import modelo.d2.TableroCeldasCuadradas;
import modelo.excepciones.ExcepcionCoordenadaIncorrecta;
import modelo.excepciones.ExcepcionPosicionFueraTablero;
import entradasalida.Factory;
import entradasalida.IGeneradorFichero;
import entradasalida.IParserTablero;
import entradasalida.ParserTableros;
import entradasalida.excepciones.ExcepcionGeneracion;
import entradasalida.excepciones.ExcepcionLectura;
import entradasalida.gif.GeneradorTableroCoordenada1D;
import entradasalida.gif.GeneradorTableroCoordenada2D;
import entradasalida.txt.GeneradorFicheroPlano;
import entradasalida.txt.ParserTablero1D;
import entradasalida.txt.ParserTablero2D;

public class MainCompilacionP5 {
	
	
	public static void main(String [] args) {
		
			//Factory, IGeneradorFichero, Patron
			Coordenada1D c1d=null;
			try {
				c1d = new Coordenada1D(10);
			} catch (ExcepcionCoordenadaIncorrecta e) {
				
				e.printStackTrace();
			}
			Tablero<Coordenada1D> tablero1d=null;
			try {
				tablero1d = Factory.creaTablero(c1d);
			} catch (ExcepcionCoordenadaIncorrecta e) {
				
				e.printStackTrace();
			}
			Regla<Coordenada1D> regla1d = Factory.creaRegla(tablero1d);		
			Juego<Coordenada1D> juego1d = new Juego<Coordenada1D>(tablero1d,regla1d);
			IGeneradorFichero igf=null;
			try {
				igf = Factory.creaGeneradorFichero(tablero1d,"gif");
			} catch (ExcepcionGeneracion e) {
				
				e.printStackTrace();
			}
			try {
				igf.generaFichero(new File ("fichero.txt"), juego1d, 10);
			} catch (ExcepcionGeneracion e) {
				
				e.printStackTrace();
			}
			
			Patron<Coordenada1D> patron1d = new Patron<Coordenada1D>("Patron1d",tablero1d);
			try {
				juego1d.cargaPatron(patron1d, c1d);
			} catch (ExcepcionPosicionFueraTablero e1) {
				
				e1.printStackTrace();
			}
		
			
			GeneradorTableroCoordenada1D generaunod = (GeneradorTableroCoordenada1D)igf;
			try {
				generaunod.generaFichero(new File("fichero.txt"),juego1d,20);
			} catch (ExcepcionGeneracion e) {
				
				e.printStackTrace();
			}
			Coordenada2D c2d=null;
			try {
				c2d =new Coordenada2D(10,5);
			} catch (ExcepcionCoordenadaIncorrecta e) {
			
				e.printStackTrace();
			}
			Tablero<Coordenada2D> tablero2d=null;
			try {
				tablero2d = Factory.creaTablero(c2d);
			} catch (ExcepcionCoordenadaIncorrecta e) {
				
				e.printStackTrace();
			}
			Regla<Coordenada2D> regla2d = Factory.creaRegla(tablero2d);
			Juego<Coordenada2D> juego2d = new Juego<Coordenada2D>(tablero2d,regla2d);
			try {
				igf = Factory.creaGeneradorFichero(tablero2d, "gif");
			} catch (ExcepcionGeneracion e) {
				
				e.printStackTrace();
			}
			
			Patron<Coordenada2D> patron2d = new Patron<Coordenada2D>("Patron1d",tablero2d);
			try {
				juego2d.cargaPatron(patron2d, c2d);
			} catch (ExcepcionPosicionFueraTablero e1) {
				
				e1.printStackTrace();
			}
			
			
			GeneradorTableroCoordenada2D generadosd = (GeneradorTableroCoordenada2D)igf;
			try {
				generadosd.generaFichero(new File("fichero.gif"), juego2d,20);
			} catch (ExcepcionGeneracion e) {
			
				e.printStackTrace();
			}
		
			try {
				igf = Factory.creaGeneradorFichero(tablero2d, "txt");
			} catch (ExcepcionGeneracion e) {
				
				e.printStackTrace();
			}
			GeneradorFicheroPlano gfp=(GeneradorFicheroPlano)igf;
			try {
				gfp.generaFichero(new File("fichero.txt"), juego2d, 10);
			} catch (ExcepcionGeneracion e) {
				
				e.printStackTrace();
			}
	
			//ParserTablero, IParserTablero		
			try {
				ParserTableros.leeTablero("*****");
			} catch (ExcepcionLectura e) {
			
				e.printStackTrace();
			}
			
			IParserTablero parser = new ParserTablero2D();
			try {
				parser.leeTablero("   \n    ");
			} catch (ExcepcionLectura e) {
			
				e.printStackTrace();
			} 
			ParserTablero2D parserdosd= (ParserTablero2D)parser;
			try {
				tablero2d=parserdosd.leeTablero("  ***\n*****");
			} catch (ExcepcionLectura e) {
				
				e.printStackTrace();
			}
			parser = new ParserTablero1D();
			ParserTablero1D parserunod= (ParserTablero1D)parser;
			try {
				parserunod.leeTablero(" *  *");
			} catch (ExcepcionLectura e) {
				
				e.printStackTrace();
			}
			
			//Imprimible
			Imprimible imp=null;
			try {
				imp = new TableroCeldasCuadradas(10,2);
			} catch (ExcepcionCoordenadaIncorrecta e) {
			
				e.printStackTrace();
			}
			imp.generaCadena();
			TableroCeldasCuadradas tabdosd = (TableroCeldasCuadradas)imp;
			tabdosd.generaCadena();
			try {
				imp = new Tablero1D(20);
			} catch (ExcepcionCoordenadaIncorrecta e) {
			
				e.printStackTrace();
			}
			Tablero1D tabunod = (Tablero1D) imp;
			tabunod.generaCadena();
//			Comprobacion redistribucion de clases en P5
			entradasalida.txt.GeneradorTableroCoordenada1D gtxtc1d=null;
			entradasalida.txt.GeneradorTableroCoordenada2D gtxtc2d=null;
			entradasalida.gif.GeneradorTableroCoordenada1D ggifc1d;
			entradasalida.gif.GeneradorTableroCoordenada2D ggifc2d;
		    gfp = (GeneradorFicheroPlano) gtxtc1d;
		    gfp = (GeneradorFicheroPlano) gtxtc2d;
		    modelo.d1.ExcepcionCoordenada1DIncorrecta ec1di;
		    modelo.d1.Regla30 r30;
		    modelo.d2.ExcepcionCoordenada2DIncorrecta ec2di;
		    modelo.d2.ReglaConway rcw;
		    modelo.d2.Tablero2D t2d;
	}

}
