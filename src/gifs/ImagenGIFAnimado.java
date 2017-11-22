package gifs;

import java.io.File;
import java.io.IOException;

import com.gif4j.light.GifEncoder;
import com.gif4j.light.GifFrame;
import com.gif4j.light.GifImage;

import entradasalida.excepciones.ExcepcionGeneracion;

/**
 * Genera un GIF animado
 * @author drizo
 */
public class ImagenGIFAnimado {
	/**
	 * Objeto de la lib. GIF4J
	 */
	GifImage gifImage;
	/**
	 * Constructor
	 * @param duracionFotograma En milisegundos
	 */
	public ImagenGIFAnimado(int duracionFotograma) {
		gifImage = new GifImage();
		gifImage.setDefaultDelay(duracionFotograma);
	}
	/**
	 * Añade un nuevo fotograma
	 * @param gif El fotograma a ser añadido
	 * @throws ExcepcionGeneracion Cuando hay un error interno de la librería
	 */
	public void addFotograma(ImagenGIF gif) throws ExcepcionGeneracion {
        try {
			gifImage.addGifFrame(new GifFrame(gif.getBufferedImage()));
		} catch (InterruptedException e) {
			throw new ExcepcionGeneracion(e);
		}
	}
	/**
	 * Guarda el GIF animado
	 * @param file Fichero donde guardar
	 * @throws ExcepcionGeneracion Lanzada cuando hay un error de entrada / salida
	 */
	public void guardaFichero(File file) throws ExcepcionGeneracion {
	    try {
	        GifEncoder.encode(gifImage, file);
		} catch (IOException e) {
			throw new ExcepcionGeneracion(e);
		}
	}

}
