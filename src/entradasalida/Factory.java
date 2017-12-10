package entradasalida;

import entradasalida.excepciones.ExcepcionGeneracion;
import entradasalida.imagen.GeneradorGIFTablero1D;
import entradasalida.imagen.GeneradorGifAnimadoTablero2D;
import entradasalida.textoplano.GeneradorFicheroPlano;
import modelo.Coordenada;
import modelo.Coordenada1D;
import modelo.Coordenada2D;
import modelo.Regla;
import modelo.Regla30;
import modelo.ReglaConway;
import modelo.Tablero;
import modelo.Tablero1D;
import modelo.Tablero2D;
import modelo.TableroCeldasCuadradas;
import modelo.excepciones.ExcepcionArgumentosIncorrectos;
import modelo.excepciones.ExcepcionCoordenadaIncorrecta;
import modelo.excepciones.ExcepcionEjecucion;


/**
 * Clase de Factory: Clase encargada de generar los diferentes componentes del juego.
 * 
 * @author Juan Carlos Lopez Gutierrez 48772256C
 * @version 1.0.0
 */

public class Factory {

    /**
     * Constructor: constructor por defecto.
     */
    
	public Factory() {
		
	}
	
	/**
	 * Crea una clase GeneradorFichero en funcion del tablero de la extensión pasadas por argumento.
	 * 
	 * @param tablero Tablero que se evalua para generar la clase indicada.
	 * @param s Tipo de fichero que se quiere crear.
	 * @return Clase GeneradorFichero apropiada a los paremetros pasados.
	 * @throws ExcepcionGeneracion Error que salta cuando el formato no es valido.
	 */
	
	public static IGeneradorFichero creaGeneradorFichero(Tablero tablero, String s) throws ExcepcionGeneracion {
	    if(tablero == null || s == null)
	        throw new ExcepcionArgumentosIncorrectos();
	    
	    if(s.equals("txt")) {
	        return new GeneradorFicheroPlano();
	    }
	    else if(s.equals("gif")) {
	        if(tablero instanceof Tablero1D)
	            return new GeneradorGIFTablero1D();
	        else if(tablero instanceof Tablero2D)
	            return new GeneradorGifAnimadoTablero2D();
	        else
	            throw new ExcepcionEjecucion("No está considerado ese tipo de tablero");
        }
	    else
	        throw new ExcepcionGeneracion("El formato no está tratado");
	}
	
	/**
	 * Crea una clase regla acorde al tipo de tablero.
	 * 
	 * @param tablero Tablero que se evalua.
	 * @return Regla para el tablero pasado por parametro.
	 */
	
	public static Regla creaRegla(Tablero tablero) {
	    
	    if(tablero == null)
            throw new ExcepcionArgumentosIncorrectos();
	    
		if(tablero instanceof Tablero1D)
		    return new Regla30();
		else if(tablero instanceof Tablero2D)
            return new ReglaConway();
		else
		    throw new ExcepcionEjecucion("No está considerado ese tipo de tablero");
	}
	
	/**
	 * Crea una tablero a partir de la coordenada pasada por parametro.
	 * 
	 * @param dimensiones Dimensiones máximas del tablero que se va a crear.
	 * @return Tablero creado a corde con el tipo de coordenada.
	 * @throws ExcepcionCoordenadaIncorrecta Error que salta cuando se intenta crear una coordenada negativa.
	 */
	
	public static Tablero creaTablero(Coordenada dimensiones) throws ExcepcionCoordenadaIncorrecta {
	    
	    if(dimensiones == null)
            throw new ExcepcionArgumentosIncorrectos();
	    
	    try {
    		if(dimensiones instanceof Coordenada1D)
    		    return new Tablero1D(((Coordenada1D) dimensiones).getX());
    		
    		else if(dimensiones instanceof Coordenada2D)
                return new TableroCeldasCuadradas(((Coordenada2D) dimensiones).getX(), ((Coordenada2D) dimensiones).getY());
    		
    		else
    		    throw new ExcepcionEjecucion("La coordenada pasada por parametro no está considerada");
    		
	    }catch(ExcepcionCoordenadaIncorrecta ex) {
	        throw new ExcepcionCoordenadaIncorrecta();
	    }
	}
}
