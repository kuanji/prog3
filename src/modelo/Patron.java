package modelo;

import java.util.Collection;

public class Patron {

	private String nombre;
	
	public Tablero tablero;
	
	public Patron(String nombre, Tablero tablero) {
		this.nombre = nombre;
		this.tablero = tablero;
	}
	
	public String getNombre() {return this.nombre;}
	
	public EstadoCelda getCelda(Coordenada coordenada) {return tablero.getCelda(coordenada);}
	
	public Collection<Coordenada> getPosiciones(){return tablero.getPosiciones();}

	@Override
	public String toString() {
		return nombre+":\n"+tablero.toString();
	}
}
