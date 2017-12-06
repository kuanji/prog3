package entradasalida;

import entradasalida.excepciones.ExcepcionGeneracion;
import entradasalida.imagen.GeneradorGIFTablero1D;
import entradasalida.imagen.GeneradorGifAnimadoTablero2D;
import entradasalida.textoplano.GeneradorFicheroPlano;
import mains.FileUtils;
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

public class Factory {

	public Factory() {
		
	}
	
	public static IGeneradorFichero creaGeneradorFichero(Tablero tablero, String s) throws ExcepcionGeneracion {
	    if(tablero == null || s == null)
	        throw new ExcepcionArgumentosIncorrectos();
	    
	    if(s == "txt") {
	        return new GeneradorFicheroPlano();
	    }
	    else if(s == "gif") {
	        if(tablero instanceof Tablero1D)
	            return new GeneradorGIFTablero1D();
	        else if(tablero instanceof Tablero2D)
	            return new GeneradorGifAnimadoTablero2D();
	        else
	            throw new ExcepcionEjecucion("No está considerado ese tipo de tablero");
        }
	    else
	        throw new ExcepcionGeneracion();
	}
	
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
