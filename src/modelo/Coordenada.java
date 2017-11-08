package modelo;

import modelo.excepciones.ExcepcionCoordenadaIncorrecta;

/**
 * Clase de coordenada: Almacenamos coordenadas de un plano de dos dimensiones.
 * 
 * @author Juan Carlos Lopez Gutierrez 48772256C
 * @version 1.0.0
 */

public class Coordenada {
    
    /**
     * Constructor: Crea una clase abstcata.
     */
    
    public Coordenada() {}

	/**
	 * 
	 * @param c Objeto Coordenada que sumamos.
	 * @return Devolvemos la suma.
	 * @throws ExcepcionCoordenadaIncorrecta
	 */

	public Coordenada suma(final Coordenada c) throws ExcepcionCoordenadaIncorrecta {
			//return new Coordenada(this.x + c.x, this.y + c.y);
	    return null;
	}
}
