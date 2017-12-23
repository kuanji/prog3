package modelo;

import java.util.ArrayList;
import java.util.HashMap;

import modelo.excepciones.ExcepcionArgumentosIncorrectos;
import modelo.excepciones.ExcepcionEjecucion;
import modelo.excepciones.ExcepcionPosicionFueraTablero;

/**
 * Clase Juego: Esta es la clase que administra todas las demas para que se ejecute el juego.
 * 
 * @author Juan Carlos Lopez Gutierrez    48772256C
 * @version 1.0.0
 *
 */
public class Juego<TipoCoordenada extends Coordenada>{
	
	/**
	 * Variable donde guardamos los patrones que se han introducido en el tablero donde se va a ejecutar el juego.
	 */
	private ArrayList<Patron<TipoCoordenada>> patronesUsados = new ArrayList<Patron<TipoCoordenada>>();
	/**
	 * Variable donde guardamos el tablero donde se va a ejecutar el juego.
	 */
	private Tablero<TipoCoordenada> tablero;
	/**
	 * Variable donde almacenamos las reglas por las que se va a regir el juego.
	 */
	private Regla<TipoCoordenada> regla;

	/**
	 * Constructor: guardamos los parametros en sus respectivas variables.
	 * 
	 * @param tablero tablero donde vamos a ajecutar el juego.
	 * @param regla reglas que vamos a seguir.
	 */
	public Juego(Tablero<TipoCoordenada> tablero, Regla<TipoCoordenada> regla) {
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
	 * @throws ExcepcionPosicionFueraTablero Error que salta cuando se intenta plasmar un patron en una celda que no existe.
	 */
	public void cargaPatron(Patron<TipoCoordenada> p, Coordenada posicionInicial) throws ExcepcionPosicionFueraTablero {
		tablero.cargaPatron(p, (TipoCoordenada) posicionInicial);
		patronesUsados.add(p);
	}
	
	/**
	 * Metodo que ejecuta el juego un ciclo aplicando las reglas a cada celda del tablero y finalmente actualizando el mismo.
	 */
	
	public void actualiza(){
	    try {
	        HashMap<Coordenada, EstadoCelda> celdas = new HashMap<Coordenada, EstadoCelda>();
	        for(Coordenada c : this.tablero.getPosiciones())
	            celdas.put(c, regla.calculaSiguienteEstadoCelda(this.tablero, (TipoCoordenada) c));
	        for(Coordenada c : celdas.keySet()) {
	            this.tablero.setCelda((TipoCoordenada) c, celdas.get(c));
	        }
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
	public ArrayList <Patron<TipoCoordenada>> getPatrones() {return patronesUsados;}
}
