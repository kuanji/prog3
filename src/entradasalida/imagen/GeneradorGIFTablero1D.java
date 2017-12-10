package entradasalida.imagen;

import java.io.File;

import entradasalida.IGeneradorFichero;
import entradasalida.excepciones.ExcepcionGeneracion;
import gifs.ImagenGIF;
import modelo.Coordenada1D;
import modelo.EstadoCelda;
import modelo.Juego;
import modelo.excepciones.ExcepcionArgumentosIncorrectos;
import modelo.excepciones.ExcepcionCoordenadaIncorrecta;
import modelo.excepciones.ExcepcionEjecucion;
import modelo.excepciones.ExcepcionPosicionFueraTablero;

/**
 * Clase de GeneradorGIFTablero1D: Clase encargade de generar un gif partiendo de un tablero de una dimension.
 * 
 * @author Juan Carlos Lopez Gutierrez 48772256C
 * @version 1.0.0
 */

public class GeneradorGIFTablero1D implements IGeneradorFichero{
	
    /**
     * Constructor por defecto.
     */
    
	public GeneradorGIFTablero1D() {
		
	}
	
	/**
	 * Crea una imagen gif de la progresion de un tablero de una dimensión.
	 */
	
	public void generaFichero(File fl, Juego juego, int iteraciones) throws ExcepcionGeneracion{
		
	    if(fl == null || juego == null)
            throw new ExcepcionArgumentosIncorrectos();
        
        if(iteraciones <= 0)
            throw new ExcepcionGeneracion("El numero de iteraciones tiene que ser positivo");
        
        ImagenGIF gif = new ImagenGIF(((Coordenada1D) juego.getTablero().getDimensiones()).getX(), iteraciones);
        
        for(int i = 0; i < iteraciones - 1; i++) {
            for(int j = 0; j < ((Coordenada1D) juego.getTablero().getDimensiones()).getX() - 1; j++) {
                try {
                    if(juego.getTablero().getCelda(new Coordenada1D(j)) == EstadoCelda.VIVA)
                        gif.pintaCuadrado(j, i);
                } catch (ExcepcionPosicionFueraTablero | ExcepcionCoordenadaIncorrecta ex) {
                    throw new ExcepcionEjecucion(ex);
                }
            }
            juego.actualiza();
        }
        gif.guardaFichero(fl);
	}
}
