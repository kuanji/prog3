package entradasalida.excepciones;

/**
 * Clase ExcepcionGeneracion: Clase que genera la excepción Generacion.
 * 
 * @author Juan Carlos Lopez Gutierrez 48772256C
 * @version 1.0.0
 */

public class ExcepcionGeneracion extends Exception{

    /**
     * Constructor por defecto.
     */
    
	public ExcepcionGeneracion() {
		
	}
	
	/**
	 * Constructor con un mensaje.
	 * 
	 * @param s Mensaje del error.
	 */
	
	public ExcepcionGeneracion(String s) {
		super(s);
	}
	
	/**
	 * Constructor de excepción debida a una casusa.
	 * 
	 * @param causa Causa de la excepción.
	 */
	
	public ExcepcionGeneracion(Throwable causa) {
		super(causa);
	}
}
