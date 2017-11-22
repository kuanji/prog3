package entradasalida;

import modelo.Coordenada;
import modelo.Regla;
import modelo.Tablero;

public class Factory {

	public Factory() {
		
	}
	
	public static IGeneradorFichero creaGeneradorFichero(Tablero tablero, String s) {
		return null;
	}
	
	public static Regla creaRegla(Tablero tablero) {
		return null;
	}
	
	public static Tablero creaTablero(Coordenada c) {
		return null;
	}
}
