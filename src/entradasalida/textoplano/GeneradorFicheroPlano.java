package entradasalida.textoplano;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import entradasalida.IGeneradorFichero;
import entradasalida.excepciones.ExcepcionGeneracion;
import modelo.Imprimible;
import modelo.Juego;
import modelo.excepciones.ExcepcionArgumentosIncorrectos;

public class GeneradorFicheroPlano implements IGeneradorFichero{

	public GeneradorFicheroPlano() {
		
	}
	
	public void generaFichero(File fl, Juego j, int iteraciones) throws ExcepcionGeneracion{
		
	    if(fl == null || j == null)
            throw new ExcepcionArgumentosIncorrectos();
        
        if(iteraciones <= 0)
            throw new ExcepcionGeneracion("El numero de iteraciones tiene que ser positivo");
        
        if(!(j.getTablero() instanceof Imprimible))
            throw new ExcepcionGeneracion("El tablero que contiene el juego no es Imprimible");
	    
	    try {
            Imprimible tab;
            PrintWriter file = new PrintWriter(fl);
            
    	    for(int i = 0; i <= iteraciones - 1; i++) {
    	        j.actualiza();
    	        tab = (Imprimible) j.getTablero();
    	        file.write(tab.generaCadena());
    	    }
    	    file.close();
	    } catch (FileNotFoundException ex) {
            throw new ExcepcionGeneracion(ex);
        }
	}
}
