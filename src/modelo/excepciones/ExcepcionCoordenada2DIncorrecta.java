package modelo.excepciones;

/**
 * Clase ExcepcionArgumentosIncorrectos: Crea una excepcion cuando los argumentos pasados a un metodo no son los necesarios.
 * 
 * @author Juan Carlos Lopez Gutierrez    48772256C
 * @version 1.0.0
 *
 */

public class ExcepcionCoordenada2DIncorrecta extends ExcepcionCoordenadaIncorrecta {
	
    /**
     * Variable donde almacenamos el valor de las abcisas de la posicion del error.
     */
    
	private int x;
	
	/**
	 * Variable donde almacenamos el valor de las ordenadas de la posicion del error.
	 */
	
	private int y;
	
	/**
	 * Constructor de la Excepción donde completaos el valor de la parte privada.
	 * @param x valor que le asignamos a la x.
	 * @param y valor que le asignamos a la y.
	 */
	
	public ExcepcionCoordenada2DIncorrecta(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Devuelve el mensaje de error.
	 */
	
	public String getMessage() {
		return "Coordenada incorrecta: (" + x + "," + y + ").";
	}
	
	/**
	 * Getter.
	 * @return valor de la x.
	 */
	
	public int getX() {return this.x;}
	
	/**
	 * Getter.
	 * @return valor de la y.
	 */
	
	public int getY() {return this.y;}
}
