package modelo;

import modelo.excepciones.ExcepcionArgumentosIncorrectos;
import modelo.excepciones.ExcepcionCoordenada2DIncorrecta;
import modelo.excepciones.ExcepcionCoordenadaIncorrecta;

/**
 * Clase de coordenada: Almacenamos coordenadas de un plano de dos dimensiones.
 * 
 * @author Juan Carlos Lopez Gutierrez 48772256C
 * @version 1.0.0
 */

public class Coordenada2D extends Coordenada{

	/**
	 * Con este campo almacenamos el valor en el eje de abscisas.
	 */

	private int x;

	/**
	 * Con este campo almacenamos el valor en el eje de ordenadas.
	 */

	private int y;

	/**
	 * Constructor: rellena la parte privada del objeto.
	 * 
	 * @param x
	 *            Valor en abcisas.
	 * @param y
	 *            Valor en ordenadas.
	 * @throws ExcepcionCoordenadaIncorrecta
	 */

	public Coordenada2D(int x, int y) throws ExcepcionCoordenadaIncorrecta {
		if (x < 0 || y < 0) {
			throw new ExcepcionCoordenada2DIncorrecta(x,y);
		}
		this.x = x;
		this.y = y;
	}

	/**
	 * Constructor: crea una copia del objeto pasado coordenada2D.
	 * 
	 * @param c
	 *            Objeto a copiar.
	 */

	public Coordenada2D(final Coordenada2D c) {
		if (c == null)
			throw new ExcepcionArgumentosIncorrectos();
		this.x = c.x;
		this.y = c.y;
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
	 * Getter.
	 * 
	 * @return Valor de ordenadas.
	 */

	public int getY() {
		return y;
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
		sb.append(",");
		sb.append(y);
		sb.append(")");
		return sb.toString();
	}

	/**
	 * 
	 * @param c Objeto Coordenada que sumamos.
	 * @return Devolvemos la suma.
	 * @throws ExcepcionCoordenadaIncorrecta
	 */

	public Coordenada2D suma(final Coordenada c) throws ExcepcionCoordenadaIncorrecta {
	    if (c == null)
            throw new ExcepcionArgumentosIncorrectos();
			return new Coordenada2D(this.x + ((Coordenada2D)c).x, this.y + ((Coordenada2D)c).y);
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
		if (this.getClass() != obj.getClass())
			return false;
		Coordenada2D other = (Coordenada2D) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	/**
	 * Devuelve el código para el Mapa hash.
	 */

	@Override
	public int hashCode() {
		return 31 * (31 + x) + y;
	}
}
