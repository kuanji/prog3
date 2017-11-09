package modelo;

import modelo.excepciones.ExcepcionPosicionFueraTablero;

/**
 * Clase Regla: Clase abstracta de la que cuelgan las distintas reglas.
 * 
 * @author Juan Carlos Lopez Gutierrez    48772256C
 * @version 1.0.0
 */

public abstract class Regla {
    
    /**
     * Constructor: Crea un objeto de la clase abstcata.
     */
    
    public Regla() {}
    
    /**
     * 
     * @param tablero tablero el cual leemos para saber el siguiente estado de la coordenada.
     * @param posicion posicion de las cual queremos saber el valor en el siguiente ciclo.
     * @return Valor de la casilla en la siguiente actualizacion del tablero.
     * @throws ExcepcionPosicionFueraTablero 
     */
    
    public abstract EstadoCelda calculaSiguienteEstadoCelda(Tablero tablero, Coordenada posicion) throws ExcepcionPosicionFueraTablero;
    
}
