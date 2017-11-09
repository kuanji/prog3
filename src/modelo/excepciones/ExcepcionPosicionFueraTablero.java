package modelo.excepciones;

import modelo.Coordenada;

/**
 * Clase ExcepcionArgumentosIncorrectos: Crea una excepcion cuando los argumentos pasados a un metodo no son los necesarios.
 * 
 * @author Juan Carlos Lopez Gutierrez    48772256C
 * @version 1.0.0
 *
 */

public class ExcepcionPosicionFueraTablero extends Exception{
	
    /**
     * Tamaño del tablero
     */
    
	private Coordenada dimensiones;
	
	/**
	 * Coordenada fuera del tablero
	 */
	
	private Coordenada coordenada;
	
	/**
	 * Constructor de la Excepción que almacena la informacion del error.
	 * @param dimensiones dimension del tablero donde se ha producido el error.
	 * @param coordenada posicion de la coordenada fuera del tablero.
	 */
	
	public ExcepcionPosicionFueraTablero(Coordenada dimensiones, Coordenada coordenada){
		this.dimensiones = dimensiones;
		this.coordenada = coordenada;
	}
	
	/**
	 * Devuelve la informacion del error generado.
	 */
	
	public String getMessage(){
	    StringBuilder sb = new StringBuilder();
        sb.append("La coordenada ");
        sb.append(coordenada.toString());
        sb.append(" se encuentra ");
        sb.append(super.getMessage());
        sb.append(" en un tablero de dimensiones ");
        sb.append(dimensiones.toString());
        sb.append(".");
        return sb.toString();
    }
	
	/**
	 * Getter.
	 * @return dimension del tablero donde se ha producido el error.
	 */
	
	public Coordenada getDimensiones() {return this.dimensiones;}
	
	/**
	 * Getter.
	 * @return devuelve la coordenada fuera de tablero.
	 */
	
	public Coordenada getCoordenada() {return this.coordenada;}
	
}
