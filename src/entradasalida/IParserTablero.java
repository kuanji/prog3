package entradasalida;

import entradasalida.excepciones.ExcepcionLectura;
import modelo.Tablero;

/**
 * Interfaz IParserTablero: Implementa la necesidad de que la clase pueda leer un nuevo tablero.
 * 
 * @author Juan Carlos Lopez Gutierrez 48772256C
 * @version 1.0.0
 */

public interface IParserTablero {

    /**
     * Crea un tablero nuevo a raiz de una cadena de caracteres.
     * 
     * @param s String que contiene en forma de cadena de carácteres el nuevo tablero.
     * @return Tablero generado.
     * @throws ExcepcionLectura Error que salta cuando la cadena pasada está vacía.
     */
    
	public Tablero leeTablero(String s) throws ExcepcionLectura;
}
