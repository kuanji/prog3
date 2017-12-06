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

public class GeneradorGIFTablero1D implements IGeneradorFichero{
	
	public GeneradorGIFTablero1D() {
		
	}
	
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
