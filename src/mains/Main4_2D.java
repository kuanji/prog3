package mains;

import java.io.File;

import entradasalida.Factory;
import entradasalida.IGeneradorFichero;
import modelo.EstadoCelda;
import modelo.Juego;
import modelo.Patron;
import modelo.d2.Coordenada2D;
import modelo.d2.ExcepcionCoordenada2DIncorrecta;
import modelo.d2.ReglaConway;
import modelo.d2.TableroCeldasCuadradas;
import modelo.excepciones.ExcepcionCoordenadaIncorrecta;
import modelo.excepciones.ExcepcionPosicionFueraTablero;

/**
@author drizo
 **/
public class Main4_2D {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Falta el nombre del fichero de salida");			
		} else {
			// creamos un patrón
			TableroCeldasCuadradas tableroPatron = null;
			TableroCeldasCuadradas tableroPatron2 = null;
			Patron patron = null;
			Patron patron2 = null;
			Patron patron3 = null;
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
				patron = new Patron("Glider", tableroPatron);	
		
				// creamos otro patrón
				tableroPatron2 = new TableroCeldasCuadradas(2,2);
				tableroPatron2.setCelda(new Coordenada2D(0,0), EstadoCelda.VIVA);
				tableroPatron2.setCelda(new Coordenada2D(1,0), EstadoCelda.VIVA);
		
				tableroPatron2.setCelda(new Coordenada2D(0,1), EstadoCelda.VIVA);
				tableroPatron2.setCelda(new Coordenada2D(1,1), EstadoCelda.VIVA);
				patron2 = new Patron("Bloque", tableroPatron2);		
				
				// otro más
				TableroCeldasCuadradas tableroPatron3 = new TableroCeldasCuadradas(3,1);
				tableroPatron3.setCelda(new Coordenada2D(0,0), EstadoCelda.VIVA);
				tableroPatron3.setCelda(new Coordenada2D(1,0), EstadoCelda.VIVA);
				tableroPatron3.setCelda(new Coordenada2D(2,0), EstadoCelda.VIVA);
	
				patron3 = new Patron("Parpadeador", tableroPatron3);				
			} catch (ExcepcionCoordenada2DIncorrecta e) {
				e.printStackTrace(); //ESTO NO DEBE SALIR NUNCA
			} catch (ExcepcionPosicionFueraTablero e) {
				e.printStackTrace(); //ESTO NO DEBE SALIR NUNCA
			} catch (ExcepcionCoordenadaIncorrecta e) {
				e.printStackTrace(); //ESTO NO DEBE SALIR NUNCA - nuevo
			}
	
			// lo cargamos en un tablero y jugamos
			try {
				TableroCeldasCuadradas t = new TableroCeldasCuadradas(10,5);
				ReglaConway r = new ReglaConway();
				Juego juego = new Juego(t, r);
				juego.cargaPatron(patron, new Coordenada2D(0,0));
				juego.cargaPatron(patron2, new Coordenada2D(8,3)); // aquí sí
				juego.cargaPatron(patron3, new Coordenada2D(7,0));
				//GeneradorFicheroPlano2D
				IGeneradorFichero generador = Factory.creaGeneradorFichero(t, FileUtils.getFileExtension(args[0]));
				generador.generaFichero(new File(args[0]), juego, 10);
			} catch (Exception e) {
				e.printStackTrace(); // NO DEBE SALTAR NUNCA, EL UNICO ERROR YA SE HA CAPTURADO
			}
		}
	}

}
