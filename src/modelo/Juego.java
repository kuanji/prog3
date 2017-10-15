package modelo;

import java.util.ArrayList;

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
	 * Variable donde almacenamos las reglas que tiene el juego.
	 */
	private ReglaConway regla;

	/**
	 * Constructor: guardamos los parametros en sus respectivas variables.
	 * 
	 * @param tablero tablero donde vamos a ajecutar el juego.
	 * @param regla reglas que vamos a seguir.
	 */
	public Juego(Tablero tablero, ReglaConway regla) {
		this.tablero = tablero;
		this.regla = regla;
	}
	
	/**
	 * Carga p en el tablero del juego.
	 * 
	 * @param p patron a introducir.
	 * @param posicionInicial coordenada de la primera celda del patron (arriba a la izquierda).
	 */
	public void cargaPatron(Patron p, Coordenada posicionInicial) {
		if(!tablero.cargaPatron(p, posicionInicial)) {System.out.println("Error cargando plantilla "+p.getNombre()+" en "+posicionInicial.toString());}
		else {patronesUsados.add(p);}
	}
	
	/**
	 * Metodo que ejecuta el juego un ciclo aplicando las reglas a cada celda del tablero y finalmente actualizando el mismo.
	 */
	public void actualiza() {
		Tablero tablero = new Tablero(this.tablero.getDimensiones());
		
		for(int i = 0; i < tablero.getDimensiones().getX(); i++) {
			for(int j = 0; j < tablero.getDimensiones().getY(); j++) {
				tablero.setCelda(new Coordenada(i,j), regla.calculaSiguenteEstadoCelda(this.tablero, new Coordenada(i,j)));
			}
		}
		this.tablero = tablero;
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
	 * @return devuelve un array con los aptrones introducidos en el tablero.
	 */
	public ArrayList <Patron> getPatrones() {return patronesUsados;}
}
