package modelo;

import java.util.ArrayList;

public class ReglaConway {

	public ReglaConway() {}
	
	public EstadoCelda calculaSiguenteEstadoCelda(Tablero tablero, Coordenada posicion) {
		int vecinasVivas = 0;
		ArrayList<Coordenada> vecinas = tablero.getPosicionesVecinasCCW(posicion);
		for(int i = 0; i < vecinas.size(); i++) {if(tablero.getCelda(vecinas.get(i)) == EstadoCelda.VIVA) {vecinasVivas++;}}
		
		if(tablero.getCelda(posicion) == EstadoCelda.VIVA){
			if(vecinasVivas < 2 || vecinasVivas > 3) {return EstadoCelda.MUERTA;}
			else {return EstadoCelda.VIVA;}
		}
		else {
			if(vecinasVivas == 3) {return EstadoCelda.VIVA;}
			else {return EstadoCelda.MUERTA;}
		}

	}
}
