package modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Tablero {
	
	private Coordenada dimensiones;
	
	private HashMap<Coordenada, EstadoCelda> celdas = new HashMap<Coordenada, EstadoCelda>();
	
	public Tablero(Coordenada dimensiones) {
		this.dimensiones = new Coordenada(dimensiones);
		
		for(int i = 0; i < this.dimensiones.getX(); i++) {
			for(int j = 0; j < this.dimensiones.getY(); j++) {
				celdas.put(new Coordenada(i,j), EstadoCelda.MUERTA);
			}
		}
	}
	
	public Coordenada getDimensiones() {
		return new Coordenada(dimensiones);
	}
	
	public Collection<Coordenada> getPosiciones() {
		return celdas.keySet();
	}
	
	public EstadoCelda getCelda(Coordenada posicion) {
		if(contiene(posicion))
			return celdas.get(posicion);
		else {
			muestraErrorPosicionInvalida(posicion);
			return null;}
	}
	
	public void setCelda(Coordenada posicion, EstadoCelda e) {
		if(contiene(posicion))
			celdas.put(posicion, e);
		else
			muestraErrorPosicionInvalida(posicion);
	}
	
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
	
	private void muestraErrorPosicionInvalida(Coordenada c) {
		System.out.println("Error: La celda "+c.toString()+" no existe");
	}
	
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
	
	public boolean contiene(Coordenada posicion) {return celdas.containsKey(posicion);}
	
	public String toString() {
		String tablero = "";
		tablero = tablero + "+";
		for(int i = 0; i < dimensiones.getX(); i++)
			tablero = tablero + "-";
		tablero = tablero + "+\n";
		for(int j = 0; j < dimensiones.getY(); j++) {
			tablero = tablero + "|";
			for(int i = 0; i < dimensiones.getX(); i++) {
				if(celdas.get(new Coordenada(i,j)) == EstadoCelda.VIVA)
					tablero = tablero + "*";
				else
					tablero = tablero + " ";
			}
			tablero = tablero + "|\n";
		}
		tablero = tablero + "+";
		for(int i = 0; i < dimensiones.getX(); i++)
			tablero = tablero + "-";
		tablero = tablero + "+\n";
		return tablero;
	}
}
