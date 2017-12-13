package entradasalida.txt;

import entradasalida.IParserTablero;
import entradasalida.excepciones.ExcepcionLectura;
import modelo.EstadoCelda;
import modelo.Tablero;
import modelo.d2.Coordenada2D;
import modelo.d2.TableroCeldasCuadradas;
import modelo.excepciones.ExcepcionArgumentosIncorrectos;
import modelo.excepciones.ExcepcionCoordenadaIncorrecta;
import modelo.excepciones.ExcepcionEjecucion;
import modelo.excepciones.ExcepcionPosicionFueraTablero;

/**
 * Clase de ParserTablero2D: Clase encargada de generar un tablero de dos dimensiones desde una cadena.
 * 
 * @author Juan Carlos Lopez Gutierrez 48772256C
 * @version 1.0.0
 */

public class ParserTablero2D implements IParserTablero{

    /**
     * Constructor por defecto.
     */
    
	public ParserTablero2D() {}
	
	/**
	 * Crea una tablero de dos dimensiones desde la cadena.
	 */
	
	public Tablero leeTablero(String s) throws ExcepcionLectura {
		
		int tam_i;
		int tam_j = 1;
		
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
					if(filas[j].charAt(i) == '*')
						tablero.setCelda(new Coordenada2D(i, j), EstadoCelda.VIVA);
				}
			}
			
			return tablero;
		} catch (ExcepcionCoordenadaIncorrecta | ExcepcionPosicionFueraTablero ex) {
			throw new ExcepcionEjecucion(ex);
		}
	}
}
