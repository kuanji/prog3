package modelo;

import modelo.excepciones.ExcepcionCoordenadaIncorrecta;

/**
 * Clase de coordenada: Clase abstracta de la que cuelgan las clases CoordenadaX 
 * 
 * @author Juan Carlos Lopez Gutierrez 48772256C
 * @version 1.0.0
 */

public abstract class Coordenada {
    
    /**
     * Constructor: Crea un objeto de la clase abstcata.
     */
    
    public Coordenada() {}

	/**
	 * Suma de dos coordenadas.
	 * 
	 * @param c Objeto Coordenada que sumamos.
	 * @return La suma de la coordenada que llama al método con el parámetro coordenada.
	 * @throws ExcepcionCoordenadaIncorrecta
	 */

	public abstract Coordenada suma(final Coordenada c) throws ExcepcionCoordenadaIncorrecta;
}
