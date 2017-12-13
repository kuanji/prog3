package entradasalida.txt;

import entradasalida.IParserTablero;
import entradasalida.excepciones.ExcepcionLectura;
import modelo.EstadoCelda;
import modelo.Tablero;
import modelo.d1.Coordenada1D;
import modelo.d1.Tablero1D;
import modelo.excepciones.ExcepcionArgumentosIncorrectos;
import modelo.excepciones.ExcepcionCoordenadaIncorrecta;
import modelo.excepciones.ExcepcionEjecucion;
import modelo.excepciones.ExcepcionPosicionFueraTablero;

/**
 * Clase de ParserTablero1D: Clase encargada de generar un tablero de una dimensión desde una cadena.
 * 
 * @author Juan Carlos Lopez Gutierrez 48772256C
 * @version 1.0.0
 */

public class ParserTablero1D implements IParserTablero{

    /**
     * Constructor por defecto.
     */
    
	public ParserTablero1D() {}
	
	/**
	 * Crea un tablero de una dimensión.
	 */
	
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
