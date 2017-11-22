package gifs;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import entradasalida.excepciones.ExcepcionGeneracion;
/**
 * Genera un fichero GID usando GIF4J
 * @author drizo
 *
 */
public class ImagenGIF {
	/**
	 * Anchura / altura de un cuadrado
	 */
	private static final int PIXELES_CUADRADO = 10;
	/**
	 * Ancho de la imagen en número de cuadrados
	 */
	private int ancho;
	/**
	 * Alto de la imagen en número de cuadrados
	 */
	private int alto;
	/**
	 * Objeto de la lib. GIF4J
	 */
	private Graphics2D ig2;
	/**
	 * Objeto de la lib. GIF4J
	 */
	private BufferedImage bi;

	/**
	 * Constructor de la clase
	 * @param ancho  Ancho de la imagen en numero de cuadrados
	 * @param alto Alto de la imagen en numero de cuadrados
	 */
	public ImagenGIF(int ancho, int alto) {
		this.ancho = ancho;
		this.alto = alto;
		bi = new BufferedImage(ancho*PIXELES_CUADRADO, alto*PIXELES_CUADRADO, BufferedImage.TYPE_BYTE_GRAY);
	    ig2 = bi.createGraphics();
	  //draw no pinta el fondo, fill si
	    ig2.fillRect(0, 0, ancho*PIXELES_CUADRADO - 1, alto*PIXELES_CUADRADO - 1); // rellena de blanco
	    ig2.setPaint(Color.BLACK); // color fondo rectángulos
	}
	/**
	 * Pinta un cuadrado en la posición proporcionada
	 * @param x Columna
	 * @param y Fila
	 * @throws ExcepcionGeneracion Cuando la coordenada es incorrecta
	 */
	public void pintaCuadrado(int x, int y) throws ExcepcionGeneracion {
		if (x<0 || x>=ancho) {
			throw new ExcepcionGeneracion("Coordenada x del cuadrado incorrecta " + x);
		}
		if (y<0 || y>=alto) {
			throw new ExcepcionGeneracion("Coordenada x del cuadrado incorrecta " + x);
		}
		ig2.fill(new Rectangle(x*PIXELES_CUADRADO, y*PIXELES_CUADRADO, PIXELES_CUADRADO, PIXELES_CUADRADO)); //draw no pinta el fondo, fill sí
	}
	/**
	 * Guarda el fichero gif
	 * @param file Fichero donde guardar
	 * @throws ExcepcionGeneracion Lanzada cuando hay un error de entrada / salida
	 */
	public void guardaFichero(File file) throws ExcepcionGeneracion {
	    try {
			ImageIO.write(bi, "GIF", file);
		} catch (IOException e) {
			throw new ExcepcionGeneracion(e);
		}
	}
	
	/**
	 * Visibilidad paquete para ser usada por ImagenGIFAnimado
	 * @return BufferedImage 
	 */
	BufferedImage getBufferedImage() {
		return bi;
	}
}
