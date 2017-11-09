package modelo.excepciones;

/**
 * Clase ExcepcionArgumentosIncorrectos: Crea una excepcion cuando los argumentos pasados a un metodo no son los necesarios.
 * 
 * @author Juan Carlos Lopez Gutierrez    48772256C
 * @version 1.0.0
 *
 */

public class ExcepcionArgumentosIncorrectos extends ExcepcionEjecucion {
    
    /**
     * Constructor de la Excepción.
     */
    
	public ExcepcionArgumentosIncorrectos(){
		super("Argumentos incorrectos.");
	}
}
