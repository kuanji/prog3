package entradasalida.textoplano;

import entradasalida.IParserTablero;
import entradasalida.excepciones.ExcepcionLectura;
import modelo.Coordenada1D;
import modelo.EstadoCelda;
import modelo.Tablero;
import modelo.TableroCeldasCuadradas;
import modelo.excepciones.ExcepcionArgumentosIncorrectos;
import modelo.excepciones.ExcepcionCoordenadaIncorrecta;
import modelo.excepciones.ExcepcionEjecucion;
import modelo.excepciones.ExcepcionPosicionFueraTablero;

public class ParserTablero2D implements IParserTablero{

	public ParserTablero2D() {}
	
	public Tablero leeTablero(String s) throws ExcepcionLectura {
		
		int i = 0, i_max = 0, j = 0, cont;
		
		if(s == null)
			throw new ExcepcionArgumentosIncorrectos();
		if(s.isEmpty())
			throw new ExcepcionLectura();
		else {
			for(cont = 0; cont < s.length(); cont++) {
				i++;
				j++;
				if(s.charAt(cont) != ' ' && s.charAt(cont) != '*' && s.charAt(cont) != '\n')
					throw new ExcepcionLectura();
				else if(s.charAt(cont) == '\n' && i_max == 0) {
					i_max = cont-1;
					j++;
					i = 0;
				}
				else if((s.charAt(cont) == '\n' && i-1 != i_max) || i-1 > i_max)
					throw new ExcepcionLectura();
			}
		}
		try {
			TableroCeldasCuadradas tablero = new TableroCeldasCuadradas(i, j);
			
			for(cont = 0; cont < s.length(); cont++) {
				
			}
			
			return tablero;
		} catch (ExcepcionCoordenadaIncorrecta ex) {
			throw new ExcepcionEjecucion(ex);
		}catch(ExcepcionPosicionFueraTablero ex){
			throw new ExcepcionEjecucion(ex);
		}
	}
}
