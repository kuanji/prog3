package modelo;

/**
 * Clase de coordenada: Almacenamos coordenadas de un plano de dos dimensiones.
 * 
 * @author Juan Carlos Lopez Gutierrez    48772256C
 * @version 1.0.0
 */

public class Coordenada {
	
	/**
	 * Con este campo numeramos cuantas coordenadas de han creado.
	 */
	
	private static int NUMERO_COORDENADAS = 0;
	
	/**
	 * Con este campo almacenamos el valor en el eje de abscisas.
	 */
	
	private int x;
	
	/**
	 * Con este campo almacenamos el valor en el eje de ordenadas.
	 */
	
	private int y;
	
	/**
	 * Constructor: asigna los valores pasados por parametro a sus respectivas variables.
	 * @param x Valor en abcisas.
	 * @param y Valor en ordenadas.
	 */
	
	public Coordenada(int x, int y){
		this.x = x;
		this.y = y;
		NUMERO_COORDENADAS++;
	}
	
	/**
	 * Constructor: crea una copia del objeto de la clase coordenada.
	 * @param c Objeto a copiar.
	 */
	
	public Coordenada(final Coordenada c){
		this.x = c.x;
		this.y = c.y;
		NUMERO_COORDENADAS++;
	}

	/**
	 * Getter.
	 * @return Valor de abcisas.
	 */
	
	public int getX() {
		return x;
	}
	
	/**
	 * Getter.
	 * @return Valor de ordenadas.
	 */

	public int getY() {
		return y;
	}

	/**
	 * Redefinimos la funcion toString para que nos muestre los datos de la manera requerida.
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
	 * Getter.
	 * @return Numero de objetos Coordenada creados.
	 */

	public static int getNumeroCoordenadas() {
		return NUMERO_COORDENADAS;
	}
	
	/**
	 * 
	 * @param c Objeto Coordenada que sumamos.
	 * @return Devolvemos la suma.
	 */

	public Coordenada suma(final Coordenada c) {
		
		return new Coordenada(this.x+c.x, this.y+c.y);
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
		Coordenada other = (Coordenada) obj;
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
	public int hashCode() {return 31*(31+x)+y;}
}
