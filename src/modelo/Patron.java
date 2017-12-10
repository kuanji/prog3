package modelo;

import java.util.Collection;

import modelo.excepciones.ExcepcionArgumentosIncorrectos;
import modelo.excepciones.ExcepcionPosicionFueraTablero;

/**
 * Clase Patron: Clase que almacena un patron y lo administra.
 * 
 * @author Juan Carlos Lopez Gutierrez    48772256C
 * @version 1.0.0
 */
public class Patron {

	/**
	 * Variable donde guardamos el nombre del patron.
	 */
	private String nombre;
	
	/**
	 * Variable tablero donde almacenamos el patron.
	 */
	Tablero tablero;
	
	/**
	 * Constructor: asigna los valores a sus respectivas variables privadas.
	 * 
	 * @param nombre nombre del patron
	 * @param tablero tablero donde almacenaremos el patron
	 */
	public Patron(String nombre, Tablero tablero) {
	    if(nombre == null)
	        throw new ExcepcionArgumentosIncorrectos();
	    if(tablero == null)
            throw new ExcepcionArgumentosIncorrectos();
		this.nombre = nombre;
		this.tablero = tablero;
	}
	
	/**
	 * Getter.
	 * 
	 * @return nombre del patron.
	 */
	public String getNombre() {return this.nombre;}
	
	/**
	 * Busca el estado de una celda en el hashMap de la variable tablero.
	 * 
	 * @param coordenada coordenada de la celda que queremos buscar.
	 * @return estado de la celda en esa posicion.
	 * @throws ExcepcionPosicionFueraTablero Error que salta cuando se intenta obtener el estado de una celda que no existe.
	 */
	public EstadoCelda getCelda(Coordenada coordenada) throws ExcepcionPosicionFueraTablero {
	    if(coordenada == null)
            throw new ExcepcionArgumentosIncorrectos();
	        return tablero.getCelda(coordenada);
	}
	
	/**
	 * Getter.
	 * 
	 * @return devuelve una Collection con todas las coordenadas que componen el patron.
	 */
	public Collection<Coordenada> getPosiciones(){return tablero.getPosiciones();}

	/**
	 * Redefinimos la funcion toString para que nos devuelva un string con la informacion del patron.
	 * 
	 * @return devuelve la informacion del patron.
	 */
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(nombre);
		sb.append("\n");
		sb.append(tablero.toString());
		return sb.toString();
	}
}
