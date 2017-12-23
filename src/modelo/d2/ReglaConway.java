package modelo.d2;

import java.util.ArrayList;

import modelo.EstadoCelda;
import modelo.Regla;
import modelo.Tablero;
import modelo.excepciones.ExcepcionArgumentosIncorrectos;
import modelo.excepciones.ExcepcionPosicionFueraTablero;

/**
 * Clase ReglaConway: En esta clase guardamos las reglas en las que se aplican a los tableros de dos dimensiones.
 * 
 * @author Juan Carlos Lopez Gutierrez    48772256C
 * @version 1.0.0
 */
public class ReglaConway extends Regla<Coordenada2D>{

	/**
	 * Constructor: Crea la regla para poder usar los metodos de las misma.
	 */
	public ReglaConway() {}
	
	/**
	 * Calcula el estado de la celda en la siguiente iteración.
	 * 
	 * @param tablero tablero el cual leemos para saber el siguiente estado de la coordenada.
	 * @param posicion posicion de las cual queremos saber el valor en el siguiente ciclo.
	 * @return Valor de la casilla en la siguiente actualizacion del tablero.
	 * @throws ExcepcionPosicionFueraTablero 
	 */
	public EstadoCelda calculaSiguienteEstadoCelda(Tablero<Coordenada2D> tablero, Coordenada2D posicion) throws ExcepcionPosicionFueraTablero {
        if(tablero == null)
            throw new ExcepcionArgumentosIncorrectos();
        if(posicion == null)
            throw new ExcepcionArgumentosIncorrectos();
    	int vecinasVivas = 0;
    	ArrayList<Coordenada2D> vecinas = tablero.getPosicionesVecinasCCW(posicion);
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
