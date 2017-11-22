package mains;

import java.io.File;

import entradasalida.Factory;
import entradasalida.IGeneradorFichero;
import modelo.Coordenada1D;
import modelo.EstadoCelda;
import modelo.Juego;
import modelo.Patron;
import modelo.Regla30;
import modelo.Tablero1D;

public class Main4_1D {
	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Falta el nombre del fichero de salida");			
		} else {
			try {
				Tablero1D tablero = new Tablero1D(80);
				Regla30 regla = new Regla30();
				Tablero1D tableroPatrong = new Tablero1D(1);
				tableroPatrong.setCelda(new Coordenada1D(0), EstadoCelda.VIVA);
				Patron p = new Patron("Simple", tableroPatrong);
				
				Juego juego = new Juego(tablero, regla);
				juego.cargaPatron(p, new Coordenada1D(40));
				IGeneradorFichero generador = Factory.creaGeneradorFichero(tablero, FileUtils.getFileExtension(args[0]));
				generador.generaFichero(new File(args[0]), juego, 30);
			} catch (Exception e) {
				e.printStackTrace(); //NO DEBERIA DAR NUNCA
			}
		}
	}

}
