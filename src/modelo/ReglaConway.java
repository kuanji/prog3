package modelo;

import java.util.ArrayList;

/**
 * Clase ReglaConway: En esta clase guardamos las reglas en las que se basa el juego de la vida y el metodo para ejecutar la misma.
 * 
 * @author Juan Carlos Lopez Gutierrez    48772256C
 * @version 1.0.0
 */
public class ReglaConway {

	/**
	 * Constructor: Crea la regla para poder usar los metodos de las misma.
	 */
	public ReglaConway() {}
	
	/**
	 * 
	 * @param tablero tablero el cual leemos para saber el siguiente estado de la coordenada.
	 * @param posicion posicion de las cual queremos saber el valor e el siguiente ciclo.
	 * @return Valor de la casilla en la siguiente actualizacion del tablero.
	 */
	public EstadoCelda calculaSiguenteEstadoCelda(Tablero tablero, Coordenada posicion) {
		int vecinasVivas = 0;
		ArrayList<Coordenada> vecinas = tablero.getPosicionesVecinasCCW(posicion);
		for(int i = 0; i < vecinas.size(); i++) {if(tablero.getCelda(vecinas.get(i)) == EstadoCelda.VIVA) {vecinasVivas++;}}
		
		if(tablero.getCelda(posicion) == EstadoCelda.VIVA){
			if(vecinasVivas < 2 || vecinasVivas > 3) {return EstadoCelda.MUERTA;}
			else {return EstadoCelda.VIVA;}
		}
		else {
			if(vecinasVivas == 3) {return EstadoCelda.VIVA;}
			else {return EstadoCelda.MUERTA;}
		}

	}
}
