package entradasalida.textoplano;

import entradasalida.IParserTablero;
import entradasalida.excepciones.ExcepcionLectura;
import modelo.Coordenada1D;
import modelo.EstadoCelda;
import modelo.Tablero;
import modelo.Tablero1D;
import modelo.excepciones.ExcepcionArgumentosIncorrectos;
import modelo.excepciones.ExcepcionCoordenadaIncorrecta;
import modelo.excepciones.ExcepcionEjecucion;
import modelo.excepciones.ExcepcionPosicionFueraTablero;

public class ParserTablero1D implements IParserTablero{

	public ParserTablero1D() {}
	
	public Tablero leeTablero(String s) throws ExcepcionLectura {
		try {
			if(s == null)
				throw new ExcepcionArgumentosIncorrectos();
			
			Tablero1D tablero = new Tablero1D(s.length());
			if(s.isEmpty())
				throw new ExcepcionLectura();
			
			else {
				for(int i = 0; i < s.length(); i++) {
					if(s.charAt(i) == ' ') {
						tablero.setCelda(new Coordenada1D(i), EstadoCelda.MUERTA);
					}
					else if(s.charAt(i) == '*') {
						tablero.setCelda(new Coordenada1D(i), EstadoCelda.VIVA);
					}
					else
						throw new ExcepcionLectura();
				}
			}
			return tablero;
			}catch(ExcepcionCoordenadaIncorrecta ex){
				throw new ExcepcionEjecucion(ex);
			}catch(ExcepcionPosicionFueraTablero ex){
				throw new ExcepcionEjecucion(ex);
			}
	}
}
