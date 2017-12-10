package modelo;

import modelo.excepciones.ExcepcionArgumentosIncorrectos;
import modelo.excepciones.ExcepcionCoordenada1DIncorrecta;
import modelo.excepciones.ExcepcionCoordenadaIncorrecta;

/**
 * Clase de coordenada1D: Almacenamos una coordenada de una dimension.
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
	 * Constructor: rellena la parte privada del objeto.
	 * 
	 * @param x Valor en abcisas.
	 * @throws ExcepcionCoordenadaIncorrecta
	 */

	public Coordenada1D(int x) throws ExcepcionCoordenadaIncorrecta {
		if (x < 0) {
			throw new ExcepcionCoordenada1DIncorrecta(x);
		}
		this.x = x;
	}

	/**
	 * Constructor: crea una copia del objeto pasado coodenada1D.
	 * 
	 * @param c Objeto a copiar.
	 */

	public Coordenada1D(final Coordenada1D c) {
		if (c == null)
			throw new ExcepcionArgumentosIncorrectos();
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
	 * Suma de dos coordenadas.
	 * 
	 * @param c Objeto Coordenada1D que sumamos.
	 * @return Devolvemos la suma.
	 * @throws ExcepcionCoordenadaIncorrecta
	 */

	public Coordenada1D suma(final Coordenada c) throws ExcepcionCoordenadaIncorrecta {
	    if (c == null)
            throw new ExcepcionArgumentosIncorrectos();
	    return new Coordenada1D(this.x + ((Coordenada1D)c).x);
	}
	
	/**
	 * Devuelve el código para el Mapa hash. 
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		return result;
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
		Coordenada1D other = (Coordenada1D) obj;
		if (x != other.x)
			return false;
		return true;
	}
	
}
