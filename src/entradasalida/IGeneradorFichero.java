package entradasalida;

import java.io.File;

import modelo.Juego;

public interface IGeneradorFichero {

	public void generaFichero(File fl, Juego j, int n);
}
