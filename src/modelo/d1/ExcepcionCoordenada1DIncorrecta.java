package modelo.d1;

import modelo.excepciones.ExcepcionCoordenadaIncorrecta;

/**
 * Clase ExcepcionArgumentosIncorrectos: Crea una excepcion cuando los argumentos pasados a un metodo no son los necesarios.
 * 
 * @author Juan Carlos Lopez Gutierrez    48772256C
 * @version 1.0.0
 *
 */

public class ExcepcionCoordenada1DIncorrecta extends ExcepcionCoordenadaIncorrecta {
	
    /**
     * Variable donde almacenamos el valor de las abcisas de la posicion del error.
     */
    
	private int x;
	
	/**
     * Constructor de la Excepción donde completaos el valor de la parte privada.
     * @param x valor que le asignamos a la x.
	 */
	
	public ExcepcionCoordenada1DIncorrecta(int x){
		this.x = x;
	}
	
	/**
	 * Devuelve el mensaje de error.
	 */
	
	public String getMessage() {
		return "Error coordenada incorrecta: (" + x + ").";
	}
	
	/**
	 * Getter.
	 * @return valor de la x.
	 */
	
	public int getX() {
		return this.x;
	}
}
