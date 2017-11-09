package modelo.excepciones;

/**
 * 
 * @author Juanki
 *
 */

public class ExcepcionCoordenada2DIncorrecta extends ExcepcionCoordenadaIncorrecta {
	
    /**
     * 
     */
    
	private int x, y;
	
	/**
	 * 
	 * @param x
	 * @param y
	 */
	
	public ExcepcionCoordenada2DIncorrecta(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * 
	 */
	
	public String getMessage() {
		return "Coordenada incorrecta: (" + x + "," + y + ").";
	}
	
	/**
	 * 
	 * @return
	 */
	
	public int getX() {return this.x;}
	
	/**
	 * 
	 * @return
	 */
	
	public int getY() {return this.y;}
}
