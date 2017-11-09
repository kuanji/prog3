package modelo;

import java.util.ArrayList;

import modelo.excepciones.ExcepcionArgumentosIncorrectos;
import modelo.excepciones.ExcepcionCoordenadaIncorrecta;
import modelo.excepciones.ExcepcionEjecucion;
import modelo.excepciones.ExcepcionPosicionFueraTablero;

/**
 * Clase Tablero1D: esta clase sirve para crear tableros de una dimension, modificarlos y obtener informacion de los mismos.
 * 
 * @author Juan Carlos Lopez Gutierrez    48772256C
 * @version 1.0.0
 *
 */

public class Tablero1D extends Tablero {
    
    /**
     * Constructor: inicializa las celdas del tablero a Muertas.
     * 
     * @param x tamaño del tablero
     * @throws ExcepcionCoordenadaIncorrecta
     */

    public Tablero1D(int x) throws ExcepcionCoordenadaIncorrecta {
        super(new Coordenada1D(x));
        
        for(int i = 0; i < ((Coordenada1D)this.dimensiones).getX(); i++) {
            try {
                celdas.put(new Coordenada1D(i), EstadoCelda.MUERTA);
            }catch (ExcepcionCoordenadaIncorrecta e) {
                throw new ExcepcionEjecucion(e); 
            }
        }
    }
    
    /**
     * Getter.
     */
    
    public ArrayList<Coordenada> getPosicionesVecinasCCW(Coordenada pos) throws ExcepcionPosicionFueraTablero{
        Coordenada1D posicion = (Coordenada1D)pos;
        
        if(posicion == null)
            throw new ExcepcionArgumentosIncorrectos();
        ArrayList<Coordenada> vecinas = new ArrayList<Coordenada>();
        if(!contiene(posicion)) {
            throw new ExcepcionPosicionFueraTablero(dimensiones, posicion);
        }
        else {
            try {
                if(posicion.getX()-1 >= 0)
                    if(contiene(new Coordenada1D(posicion.getX()-1))) {vecinas.add(new Coordenada1D(posicion.getX()-1));}
                if(contiene(new Coordenada1D(posicion.getX()+1))) {vecinas.add(new Coordenada1D(posicion.getX()+1));}
            }catch (ExcepcionCoordenadaIncorrecta e) {
                throw new ExcepcionEjecucion(e);
            }
        }
        return vecinas;
        
    }
    
    /**
     * Devuelve la informacion del tablero.
     */
    
    public String toString() {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("|");
            for(int i = 0; i < ((Coordenada1D) dimensiones).getX(); i++) {
                if(celdas.get(new Coordenada1D(i)) == EstadoCelda.VIVA)
                    sb.append("*");
                else
                    sb.append(" ");
            }
            sb.append("|\n");
            return sb.toString();
        }catch (ExcepcionCoordenadaIncorrecta e) {
            throw new ExcepcionEjecucion(e);
        }
    }
    
}
