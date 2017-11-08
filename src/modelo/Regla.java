package modelo;

import modelo.excepciones.ExcepcionPosicionFueraTablero;

public abstract class Regla {
    
    /**
     * Constructor: Crea un objeto del tipo de la clase abstracta.
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
