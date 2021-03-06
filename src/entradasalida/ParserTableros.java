package entradasalida;

import entradasalida.excepciones.ExcepcionLectura;
import entradasalida.txt.ParserTablero1D;
import entradasalida.txt.ParserTablero2D;
import modelo.Tablero;
import modelo.excepciones.ExcepcionArgumentosIncorrectos;

/**
 * Clase de ParserTableros: Clase encargada de generar un tablero partiendo de un string.
 * 
 * @author Juan Carlos Lopez Gutierrez 48772256C
 * @version 1.0.0
 */


public class ParserTableros {

    /**
     * Constructor por defecto.
     */
    
	public ParserTableros() {
		
	}
	
	/**
	 * Crea un tablero partiendo de una cadena de caracteres.
	 * 
	 * @param s Cadena que contiene el tablero.
	 * @return Tablero generado.
	 * @throws ExcepcionLectura Error que salta cuando la cadena está vacía.
	 */
	
	public static Tablero leeTablero(String s) throws ExcepcionLectura {
	    Tablero tablero;
	    if(s == null)
	        throw new ExcepcionArgumentosIncorrectos();
	    if(s.isEmpty())
	        throw new ExcepcionLectura("La cadena está vacía");
	    try {
		    if(s.contains("\n"))
		        tablero = (new ParserTablero2D()).leeTablero(s);
		    else
		        tablero = (new ParserTablero1D()).leeTablero(s);
		    return tablero;
	    }catch(ExcepcionLectura ex) {
	        throw new ExcepcionLectura(ex);
	    }
	}
}
