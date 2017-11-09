package modelo;

import java.util.ArrayList;
import java.util.HashMap;

import modelo.excepciones.ExcepcionArgumentosIncorrectos;
import modelo.excepciones.ExcepcionCoordenadaIncorrecta;
import modelo.excepciones.ExcepcionEjecucion;
import modelo.excepciones.ExcepcionPosicionFueraTablero;

/**
 * Clase Juego: Esta es la clase que administra todas las demas para que se ejecute el juego.
 * 
 * @author Juan Carlos Lopez Gutierrez    48772256C
 * @version 1.0.0
 *
 */
public class Juego {
	
	/**
	 * Variable donde guardamos los patrones que se han introducido en el tablero donde se va a ejecutar el juego.
	 */
	private ArrayList<Patron> patronesUsados = new ArrayList<Patron>();
	/**
	 * Variable donde guardamos el tablero donde se va a ejecutar el juego.
	 */
	private Tablero tablero;
	/**
	 * Variable donde almacenamos las reglas por las que se va a regir el juego.
	 */
	private Regla regla;

	/**
	 * Constructor: guardamos los parametros en sus respectivas variables.
	 * 
	 * @param tablero tablero donde vamos a ajecutar el juego.
	 * @param regla reglas que vamos a seguir.
	 */
	public Juego(Tablero tablero, Regla regla) {
	    if(tablero == null)
            throw new ExcepcionArgumentosIncorrectos();
	    if(regla == null)
            throw new ExcepcionArgumentosIncorrectos();
		this.tablero = tablero;
		this.regla = regla;
	}
	
	/**
	 * Carga un patron en el tablero del juego.
	 * 
	 * @param p patron a introducir.
	 * @param posicionInicial coordenada de la primera celda del patron (arriba a la izquierda).
	 * @throws ExcepcionPosicionFueraTablero 
	 */
	public void cargaPatron(Patron p, Coordenada posicionInicial) throws ExcepcionPosicionFueraTablero {
		tablero.cargaPatron(p, posicionInicial);
		patronesUsados.add(p);
	}
	
	/**
	 * Metodo que ejecuta el juego un ciclo aplicando las reglas a cada celda del tablero y finalmente actualizando el mismo.
	 * @throws ExcepcionCoordenadaIncorrecta 
	 */
	public void actualiza() throws ExcepcionPosicionFueraTablero, ExcepcionCoordenadaIncorrecta {
	    try {
	        HashMap<Coordenada, EstadoCelda> celdas = new HashMap<Coordenada, EstadoCelda>();
	        for(Coordenada c : this.tablero.getPosiciones())
	            celdas.put(c, regla.calculaSiguienteEstadoCelda(this.tablero, c));
	        this.tablero.celdas = celdas;
	    }catch (ExcepcionPosicionFueraTablero ex) {
	        throw new ExcepcionEjecucion(ex);
	    }
	}
	
	/**
	 * Getter.
	 * 
	 * @return devuelve el el tablero en su estado actual.
	 */
	public Tablero getTablero() {return tablero;}
	
	/**
	 * Getter.
	 * 
	 * @return devuelve un array con los patrones introducidos en el tablero.
	 */
	public ArrayList <Patron> getPatrones() {return patronesUsados;}
}
