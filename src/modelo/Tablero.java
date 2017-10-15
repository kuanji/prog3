package modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Clase Tablero: esta clase sirve para crear tableros, modificarlos y obtener informacion de los mismos.
 * 
 * @author Juan Carlos Lopez Gutierrez    48772256C
 * @version 1.0.0
 *
 */
public class Tablero {
	
	/**
	 * Variable donde almacenamos la dimension del tablero.
	 */
	private Coordenada dimensiones;
	
	/**
	 * Variable donde almacenamos el estado de cada una de las celdas que componen el tablero.
	 */
	private HashMap<Coordenada, EstadoCelda> celdas = new HashMap<Coordenada, EstadoCelda>();
	
	/**
	 * Constructor: guarda las dimensiones en su respectiva variable e inicializa todas las celdas que componen el tablero como muertas.
	 * 
	 * @param dimensiones dimension del tablero
	 */
	public Tablero(Coordenada dimensiones) {
		this.dimensiones = new Coordenada(dimensiones);
		
		for(int i = 0; i < this.dimensiones.getX(); i++) {
			for(int j = 0; j < this.dimensiones.getY(); j++) {
				celdas.put(new Coordenada(i,j), EstadoCelda.MUERTA);
			}
		}
	}
	
	/**
	 * Getter.
	 * 
	 * @return devuelde las dimensiones del tablero.
	 */
	public Coordenada getDimensiones() {
		return new Coordenada(dimensiones);
	}
	
	/**
	 * Getter.
	 * 
	 * @return devuelve una Collection con todas las coordenadas que componen el tablero.
	 */
	public Collection<Coordenada> getPosiciones() {
		return celdas.keySet();
	}
	
	/**
	 * Getter.
	 * 
	 * @param posicion coordenada con la que vamos a tratar.
	 * @return devuelve el estado de la celda en la coordenada suministrada.
	 */
	public EstadoCelda getCelda(Coordenada posicion) {
		if(contiene(posicion))
			return celdas.get(posicion);
		else {
			muestraErrorPosicionInvalida(posicion);
			return null;}
	}
	
	/**
	 * Setter: Guardamos en la coordenada pasada el estado celda que pasamos por parametro.
	 * 
	 * @param posicion coordenada a tratar.
	 * @param e estado que queremos almacenar.
	 */
	public void setCelda(Coordenada posicion, EstadoCelda e) {
		if(contiene(posicion))
			celdas.put(posicion, e);
		else
			muestraErrorPosicionInvalida(posicion);
	}
	
	/**
	 * Getter.
	 * 
	 * @param posicion coordenada con las que vamos a tratar.
	 * @return devolvemos un array con las coordenadas que tiene al rededor la coordenada pasada.
	 */
	public ArrayList<Coordenada> getPosicionesVecinasCCW(Coordenada posicion) {
		ArrayList<Coordenada> vecinas = new ArrayList<Coordenada>();
		if(!contiene(posicion)) {
			muestraErrorPosicionInvalida(posicion);
		}
		else {
			if(contiene(new Coordenada(posicion.getX()-1,posicion.getY()-1))) {vecinas.add(new Coordenada(posicion.getX()-1,posicion.getY()-1));}
			if(contiene(new Coordenada(posicion.getX()-1,posicion.getY()))) {vecinas.add(new Coordenada(posicion.getX()-1,posicion.getY()));}
			if(contiene(new Coordenada(posicion.getX()-1,posicion.getY()+1))) {vecinas.add(new Coordenada(posicion.getX()-1,posicion.getY()+1));}
			if(contiene(new Coordenada(posicion.getX(),posicion.getY()+1))) {vecinas.add(new Coordenada(posicion.getX(),posicion.getY()+1));}
			if(contiene(new Coordenada(posicion.getX()+1,posicion.getY()+1))) {vecinas.add(new Coordenada(posicion.getX()+1,posicion.getY()+1));}
			if(contiene(new Coordenada(posicion.getX()+1,posicion.getY()))) {vecinas.add(new Coordenada(posicion.getX()+1,posicion.getY()));}
			if(contiene(new Coordenada(posicion.getX()+1,posicion.getY()-1))) {vecinas.add(new Coordenada(posicion.getX()+1,posicion.getY()-1));}
			if(contiene(new Coordenada(posicion.getX(),posicion.getY()-1))) {vecinas.add(new Coordenada(posicion.getX(),posicion.getY()-1));}
		}
		return vecinas;
	}
	
	/**
	 * Mostramos un error por pantalla diciendo que esa coordenada no existe.
	 * 
	 * @param c coordenada inexistente.
	 */
	private void muestraErrorPosicionInvalida(Coordenada c) {
		System.out.println("Error: La celda "+c.toString()+" no existe");
	}
	
	/**
	 * Intentamos cargar un patron en este tablero, si se carga, lo almacenamos en los aptrones usados, si no, emitimos un error.
	 * 
	 * @param patron patron que queremos guardar.
	 * @param coordenadaInicial coordenada de la primera posicion del patron (arriba a la izquierda).
	 * @return true si se ha completado, false si no.
	 */
	public boolean cargaPatron(Patron patron, Coordenada coordenadaInicial) {
		for(Coordenada c : patron.getPosiciones()) {
			if(!contiene(c.suma(coordenadaInicial))) {
				muestraErrorPosicionInvalida(c.suma(coordenadaInicial));
				return false;
			}
		}
		for(Coordenada c : patron.getPosiciones()) {this.celdas.put(c.suma(coordenadaInicial), patron.getCelda(c));}
		return true;
	}
	
	/**
	 * Comprobamos que exista una coordenada dentro del tablero.
	 * 
	 * @param posicion coordenada a evaluar.
	 * @return true si existe, false si no.
	 */
	public boolean contiene(Coordenada posicion) {return celdas.containsKey(posicion);}
	
	/**
	 * Redefine la funcion toString para almacenar la informacion de tablero en un string.
	 * 
	 * @return devuelve la informacion del tablero.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("+");
		for(int i = 0; i < dimensiones.getX(); i++)
			sb.append("-");
		sb.append("+\n");
		for(int j = 0; j < dimensiones.getY(); j++) {
			sb.append("|");
			for(int i = 0; i < dimensiones.getX(); i++) {
				if(celdas.get(new Coordenada(i,j)) == EstadoCelda.VIVA)
					sb.append("*");
				else
					sb.append(" ");
			}
			sb.append("|\n");
		}
		sb.append("+");
		for(int i = 0; i < dimensiones.getX(); i++)
			sb.append("-");
		sb.append("+\n");
		return sb.toString();
	}
}
