package modelo.excepciones;

/**
 * 
 * @author Juanki
 *
 */

public class ExcepcionCoordenada1DIncorrecta extends ExcepcionCoordenadaIncorrecta {
	
    /**
     * 
     */
    
	private int x;
	
	/**
	 * 
	 * @param x
	 */
	
	public ExcepcionCoordenada1DIncorrecta(int x){
		this.x = x;
	}
	
	/**
	 * 
	 */
	
	public String getMessage() {
		return "Error coordenada incorrecta: (" + x + ").";
	}
	
	/**
	 * 
	 * @return
	 */
	
	public int getX() {
		return this.x;
	}
}
