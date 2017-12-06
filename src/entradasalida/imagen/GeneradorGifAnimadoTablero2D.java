package entradasalida.imagen;

import java.io.File;

import entradasalida.IGeneradorFichero;
import entradasalida.excepciones.ExcepcionGeneracion;
import gifs.ImagenGIF;
import gifs.ImagenGIFAnimado;
import modelo.Coordenada2D;
import modelo.EstadoCelda;
import modelo.Juego;
import modelo.excepciones.ExcepcionArgumentosIncorrectos;
import modelo.excepciones.ExcepcionCoordenadaIncorrecta;
import modelo.excepciones.ExcepcionEjecucion;
import modelo.excepciones.ExcepcionPosicionFueraTablero;

public class GeneradorGifAnimadoTablero2D implements IGeneradorFichero{

	public GeneradorGifAnimadoTablero2D() {
		
	}
	
	public void generaFichero(File fl, Juego juego, int iteraciones) throws ExcepcionGeneracion{
		
	    if(fl == null || juego == null)
            throw new ExcepcionArgumentosIncorrectos();
        
        if(iteraciones <= 0)
            throw new ExcepcionGeneracion("El numero de iteraciones tiene que ser positivo");
        
        ImagenGIFAnimado gifAnimado = new ImagenGIFAnimado(100);
        ImagenGIF fotograma;
        
        for(int i = 0; i < iteraciones - 1; i++) {
            
             fotograma = new ImagenGIF(((Coordenada2D) juego.getTablero().getDimensiones()).getX(), ((Coordenada2D) juego.getTablero().getDimensiones()).getY());
             
             for(int x = 0; x < ((Coordenada2D) juego.getTablero().getDimensiones()).getX() - 1; x++) {
                 for(int y = 0; y < ((Coordenada2D) juego.getTablero().getDimensiones()).getY() - 1; y++) {
                     try {
                        if(juego.getTablero().getCelda(new Coordenada2D(x, y)) == EstadoCelda.VIVA)
                             fotograma.pintaCuadrado(x, y);
                    } catch (ExcepcionPosicionFueraTablero | ExcepcionCoordenadaIncorrecta ex) {
                        throw new ExcepcionEjecucion(ex);
                    }
                 }
             }
             gifAnimado.addFotograma(fotograma);
             juego.actualiza();
        }
        gifAnimado.guardaFichero(fl);
	}
}
