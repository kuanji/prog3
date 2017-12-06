package entradasalida;

import entradasalida.excepciones.ExcepcionLectura;
import entradasalida.textoplano.ParserTablero1D;
import entradasalida.textoplano.ParserTablero2D;
import modelo.Tablero;
import modelo.excepciones.ExcepcionArgumentosIncorrectos;

public class ParserTableros {

	public ParserTableros() {
		
	}
	
	public static Tablero leeTablero(String s) throws ExcepcionLectura {
	    Tablero tablero;
	    if(s == null)
	        throw new ExcepcionArgumentosIncorrectos();
	    if(s.isEmpty())
	        throw new ExcepcionLectura();
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
