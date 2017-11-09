package modelo.excepciones;

/**
 * 
 * @author Juanki
 *
 */

public class ExcepcionEjecucion extends RuntimeException {
    
    /**
     * 
     * @param mensaje
     */
    
	public ExcepcionEjecucion(String mensaje){
		super(mensaje);
	}
	
	/**
	 * 
	 * @param causa
	 */
	
	public ExcepcionEjecucion(Throwable causa){
		super(causa);
	}
}
