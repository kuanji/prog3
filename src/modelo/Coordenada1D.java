package modelo;

import modelo.excepciones.ExcepcionArgumentosIncorrectos;
import modelo.excepciones.ExcepcionCoordenadaIncorrecta;

/**
 * Clase de coordenada: Almacenamos coordenadas de un plano de dos dimensiones.
 * 
 * @author Juan Carlos Lopez Gutierrez 48772256C
 * @version 1.0.0
 */

public class Coordenada1D extends Coordenada{

	/**
	 * Con este campo almacenamos el valor en el eje de abscisas.
	 */

	private int x;

	/**
	 * Constructor: asigna los valores pasados por parametro a sus respectivas
	 * variables.
	 * 
	 * @param x
	 *            Valor en abcisas.
	 * @throws ExcepcionCoordenadaIncorrecta
	 */

	public Coordenada1D(int x) throws ExcepcionCoordenadaIncorrecta {
		if (this.x > 0) {
			throw new ExcepcionCoordenadaIncorrecta("Error coordenada negativa.");
		}
		this.x = x;
	}

	/**
	 * Constructor: crea una copia del objeto de la clase coordenada.
	 * 
	 * @param c
	 *            Objeto a copiar.
	 */

	public Coordenada1D(final Coordenada1D c) {
		if (c == null)
			throw new ExcepcionArgumentosIncorrectos("Coorndenada a null.");
		this.x = c.x;
	}

	/**
	 * Getter.
	 * 
	 * @return Valor de abcisas.
	 */

	public int getX() {
		return x;
	}

	/**
	 * Redefinimos la funcion toString para que nos muestre los datos de la manera
	 * requerida.
	 * 
	 * @return devuelve la informacion de la coordenada.
	 */

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		sb.append(x);
		sb.append(")");
		return sb.toString();
	}

	/**
	 * 
	 * @param c Objeto Coordenada que sumamos.
	 * @return Devolvemos la suma.
	 * @throws ExcepcionCoordenadaIncorrecta
	 */

	public Coordenada1D suma(final Coordenada1D c) throws ExcepcionCoordenadaIncorrecta {
	    if (c == null)
            throw new ExcepcionArgumentosIncorrectos("Coordenada a null.");
			return new Coordenada1D(this.x + c.x);
	}

	/**
	 * Comparamos si los dos objetos son iguales.
	 */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordenada1D other = (Coordenada1D) obj;
		if (x != other.x)
			return false;
		return true;
	}

	/**
	 * Devuelve el código para el Mapa hash.
	 */

	@Override
	public int hashCode() {
		return 31 * (31 + x);
	}
}
