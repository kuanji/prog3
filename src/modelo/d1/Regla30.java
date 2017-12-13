package modelo.d1;

import java.util.ArrayList;

import modelo.Coordenada;
import modelo.EstadoCelda;
import modelo.Regla;
import modelo.Tablero;
import modelo.excepciones.ExcepcionArgumentosIncorrectos;
import modelo.excepciones.ExcepcionPosicionFueraTablero;

/**
 * Clase Regla30: En esta clase guardamos las reglas en las que se aplican a los tableros de una unica dimension.
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
	 * Calcula el estado de la celda en la siguiente iteración.
	 * 
	 * @param tablero tablero el cual leemos para saber el siguiente estado de la coordenada.
	 * @param posicion posicion de las cual queremos saber el valor en el siguiente ciclo.
	 * @return Valor de la casilla en la siguiente actualizacion del tablero.
	 * @throws ExcepcionPosicionFueraTablero Error, la posicion no existe.
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
                if(tablero.getCelda(vecinas.get(0)) == EstadoCelda.MUERTA && tablero.getCelda(posicion) == EstadoCelda.MUERTA)
                    return EstadoCelda.MUERTA;
                if(tablero.getCelda(vecinas.get(0)) == EstadoCelda.VIVA && tablero.getCelda(posicion) == EstadoCelda.VIVA)
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
