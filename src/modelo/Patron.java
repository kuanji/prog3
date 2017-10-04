package modelo;

import java.util.Collection;

public class Patron {

	private String nombre;
	
	public Patron(String nombre, Tablero tablero) {
		
		
	}
	
	public String getNombre() {return this.nombre;}
	
	public EstadoCelda getCelda(Coordenada coordenada) {}
	
	public Collection<Coordenada> getPosiciones(){}

	@Override
	public String toString() {
		return "Patron [nombre=" + nombre + "]";
	}
}
