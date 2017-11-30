package entradasalida.textoplano;

import entradasalida.IParserTablero;
import entradasalida.excepciones.ExcepcionLectura;
import modelo.Coordenada1D;
import modelo.Coordenada2D;
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
		
		int tam_i, tam_j = 0;
		
		String[] filas;
		
		if(s == null)
			throw new ExcepcionArgumentosIncorrectos();
		if(s.isEmpty())
			throw new ExcepcionLectura();
		else {
			filas = s.split("\n");
			
			tam_i = filas[0].length();
			
			for(int cont = 1; cont < filas.length; cont++){
				if(filas[cont].length() != tam_i)
					throw new ExcepcionLectura();
				for(int i = 0; i < filas[cont].length(); i++){
					if(filas[cont].charAt(i) != ' ' && filas[cont].charAt(i) != '*')
						throw new ExcepcionLectura();
				}
				tam_j++;
			}
		}
		try {
			TableroCeldasCuadradas tablero = new TableroCeldasCuadradas(tam_i, tam_j);
			
			for(int j = 0; j < filas.length; j++) {
				for(int i = 0; i < filas[j].length(); i++){
					if(filas[j].charAt(i) == ' ')
						tablero.setCelda(new Coordenada2D(tam_i, tam_j), EstadoCelda.MUERTA);
					else
						tablero.setCelda(new Coordenada2D(tam_i, tam_j), EstadoCelda.VIVA);
				}
			}
			
			return tablero;
		} catch (ExcepcionCoordenadaIncorrecta ex) {
			throw new ExcepcionEjecucion(ex);
		}catch(ExcepcionPosicionFueraTablero ex){
			throw new ExcepcionEjecucion(ex);
		}
	}
}
