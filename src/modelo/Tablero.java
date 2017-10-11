package modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Tablero {
	
	private Coordenada dimensiones;
	
	private HashMap<Coordenada, EstadoCelda> celdas;
	
	public Tablero(Coordenada dimensiones) {
		this.dimensiones = new Coordenada(dimensiones);
		
		for(int x = 0; x < dimensiones.getX(); x++) {
			for(int y = 0; y < dimensiones.getY(); y++) {
				celdas.put(new Coordenada(x,y), EstadoCelda.MUERTA);
			}
		}
	}
	
	public Coordenada getDimensiones() {
		return new Coordenada(dimensiones);
	}
	
	public Collection<Coordenada> getPosiciones() {
		// return celdas.values();
	}
	
	public EstadoCelda getCelda(Coordenada posicion) {
		return celdas.get(posicion);
	}
	
	public void setCelda(Coordenada posicion, EstadoCelda e) {
		celdas.put(posicion, e);
	}
	
	public ArrayList<Coordenada> setPosicionesVecinasCCW(Coordenada posicion) {
		
	}
	
	private void muestraErrorPosicionInvalida(Coordenada c) {
		
	}
	
	public boolean cargaPatron(Patron patron, Coordenada coordenadaInicial) {
		return true;
	}
	
	public boolean contiene(Coordenada posicion) {
		return true;
	}
	
	public String toString() {
		
	}
}
