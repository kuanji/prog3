package entradasalida;

import entradasalida.excepciones.ExcepcionGeneracion;
import modelo.Coordenada;
import modelo.Regla;
import modelo.Tablero;
import modelo.d1.Coordenada1D;
import modelo.d1.Regla30;
import modelo.d1.Tablero1D;
import modelo.d2.Coordenada2D;
import modelo.d2.ReglaConway;
import modelo.d2.Tablero2D;
import modelo.d2.TableroCeldasCuadradas;
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
	
	public static IGeneradorFichero creaGeneradorFichero(Tablero tablero, String s) throws ExcepcionGeneracion{
	    IGeneradorFichero temp = null;
	    if(tablero == null || s == null)
            throw new ExcepcionArgumentosIncorrectos();
	    try {
	        temp = (IGeneradorFichero) Class.forName("entradasalida."+ s + ".GeneradorTablero" + tablero.getDimensiones().getClass().getSimpleName()).newInstance();
	        //Class c = Class.forName(tablero.getDimensiones().getClass().getSimpleName());
	    }catch (InstantiationException | IllegalAccessException  ex) {
            throw new ExcepcionEjecucion(ex);
        }catch (ClassNotFoundException  ex) {
            throw new ExcepcionEjecucion(tablero.getDimensiones().getClass().getSimpleName());
        }
	    return temp;
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
