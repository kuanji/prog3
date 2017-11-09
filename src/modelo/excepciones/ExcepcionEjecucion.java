package modelo.excepciones;

/**
 * Clase ExcepcionArgumentosIncorrectos: Crea una excepcion de error de ejecucion.
 * 
 * @author Juan Carlos Lopez Gutierrez    48772256C
 * @version 1.0.0
 *
 */

public class ExcepcionEjecucion extends RuntimeException {
    
    /**
     * Constructor de la Excepción con un mensaje.
     * @param mensaje mensaje de error.
     */
    
	public ExcepcionEjecucion(String mensaje){
		super(mensaje);
	}
	
	/**
	 * Constructor de la Excepción con una causa.
	 * @param causa causa del error.
	 */
	
	public ExcepcionEjecucion(Throwable causa){
		super(causa);
	}
}
