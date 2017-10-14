package modelo;

import java.util.ArrayList;

public class Juego {
	
	private ArrayList<Patron> patronesUsados = new ArrayList<Patron>();
	private Tablero tablero;
	private ReglaConway regla;

	public Juego(Tablero tablero, ReglaConway regla) {
		this.tablero = tablero;
		this.regla = regla;
	}
	
	public void cargaPatron(Patron p, Coordenada posicionInicial) {
		if(!tablero.cargaPatron(p, posicionInicial)) {System.out.println("Error cargando plantilla "+p.getNombre()+" en "+posicionInicial.toString());}
		else {patronesUsados.add(p);}
	}
	
	public void actualiza() {
		Tablero tablero = new Tablero(this.tablero.getDimensiones());
		
		for(int i = 0; i < tablero.getDimensiones().getX(); i++) {
			for(int j = 0; j < tablero.getDimensiones().getY(); j++) {
				tablero.setCelda(new Coordenada(i,j), regla.calculaSiguenteEstadoCelda(this.tablero, new Coordenada(i,j)));
			}
		}
		this.tablero = tablero;
	}
	
	public Tablero getTablero() {return tablero;}
	
	public ArrayList <Patron> getPatrones() {return patronesUsados;}
}
