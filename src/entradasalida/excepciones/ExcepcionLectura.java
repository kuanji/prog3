package entradasalida.excepciones;

/**
 * Clase ExcepcionLectura: Clase que genera la excepción Lectura.
 * 
 * @author Juan Carlos Lopez Gutierrez 48772256C
 * @version 1.0.0
 */

public class ExcepcionLectura extends Exception{

    /**
     * Constructor por defecto.
     */
    
	public ExcepcionLectura() {
		
	}
	
	/**
	 * Constructor con un mensaje.
	 * 
	 * @param s Mensaje del error.
	 */
	
	public ExcepcionLectura(String s) {
		super(s);
	}
	
	/**
	 * Constructor de excepxión debida a una causa.
	 * 
	 * @param causa Causa del error.
	 */

	public ExcepcionLectura(Throwable causa) {
	    super(causa);
	}
}
