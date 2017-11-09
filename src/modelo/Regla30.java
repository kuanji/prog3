package modelo;

import java.util.ArrayList;

import modelo.excepciones.ExcepcionArgumentosIncorrectos;
import modelo.excepciones.ExcepcionPosicionFueraTablero;

/**
 * Clase ReglaConway: En esta clase guardamos las reglas en las que se basa el juego de la vida y el metodo para ejecutar la misma.
 * 
 * @author Juan Carlos Lopez Gutierrez    48772256C
 * @version 1.0.0
 */
public class Regla30 extends Regla{

	/**
	 * Constructor: Crea la regla para poder usar los metodos de las misma.
	 */
	public Regla30() {}
	
	/**
	 * 
	 * @param tablero tablero el cual leemos para saber el siguiente estado de la coordenada.
	 * @param posicion posicion de las cual queremos saber el valor en el siguiente ciclo.
	 * @return Valor de la casilla en la siguiente actualizacion del tablero.
	 * @throws ExcepcionPosicionFueraTablero 
	 */
	public EstadoCelda calculaSiguienteEstadoCelda(Tablero tablero, Coordenada posicion) throws ExcepcionPosicionFueraTablero {
        if(tablero == null)
            throw new ExcepcionArgumentosIncorrectos();
        if(posicion == null)
            throw new ExcepcionArgumentosIncorrectos();
    	ArrayList<Coordenada> vecinas = tablero.getPosicionesVecinasCCW(posicion);
    	if(vecinas.size() > 1) {
        	if(tablero.getCelda(vecinas.get(0)) == EstadoCelda.VIVA && tablero.getCelda(vecinas.get(1)) == EstadoCelda.VIVA)
        	        return EstadoCelda.MUERTA;
        	else if(tablero.getCelda(vecinas.get(1)) == EstadoCelda.MUERTA) {
                if(tablero.getCelda(vecinas.get(0)) == EstadoCelda.MUERTA && tablero.celdas.get(posicion) == EstadoCelda.MUERTA)
                    return EstadoCelda.MUERTA;
                if(tablero.getCelda(vecinas.get(0)) == EstadoCelda.VIVA && tablero.celdas.get(posicion) == EstadoCelda.VIVA)
                    return EstadoCelda.MUERTA;
                else
                    return EstadoCelda.VIVA;
            }
        	else
        	    return EstadoCelda.VIVA;
    	}
    	else
    	    return EstadoCelda.MUERTA;
	}
}
