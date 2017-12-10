package entradasalida;

import java.io.File;

import entradasalida.excepciones.ExcepcionGeneracion;
import modelo.Juego;

/**
 * Interfaz IGeneradorFichero: Implementa la necesidad de que la clase pueda generar un fichero.
 * 
 * @author Juan Carlos Lopez Gutierrez 48772256C
 * @version 1.0.0
 */

public interface IGeneradorFichero {

    /**
     * Genera un fichero donde aparace el juego actualizado un número determinado de veces.
     * 
     * @param fl Nombre del archivo.
     * @param j Juego que se quiere imprimir en un fichero.
     * @param n Número de actualizaciones que se van a plasmar.
     * @throws ExcepcionGeneracion Error que salta cuando falla en algún momento la generacion del archivo.
     */
    
	public void generaFichero(File fl, Juego j, int n) throws ExcepcionGeneracion;
}
