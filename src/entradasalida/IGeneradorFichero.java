package entradasalida;

import java.io.File;

import entradasalida.excepciones.ExcepcionGeneracion;
import modelo.Juego;

public interface IGeneradorFichero {

	public void generaFichero(File fl, Juego j, int n) throws ExcepcionGeneracion;
}
