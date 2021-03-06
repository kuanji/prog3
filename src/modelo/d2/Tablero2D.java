package modelo.d2;

import java.util.ArrayList;

import modelo.EstadoCelda;
import modelo.Tablero;
import modelo.excepciones.ExcepcionCoordenadaIncorrecta;
import modelo.excepciones.ExcepcionEjecucion;
import modelo.excepciones.ExcepcionPosicionFueraTablero;

/**
 * Clase Tablero2D: esta clase sirve para crear tableros de dos dimensiones.
 * 
 * @author Juan Carlos Lopez Gutierrez    48772256C
 * @version 1.0.0
 *
 */

public abstract class Tablero2D extends Tablero<Coordenada2D> {

    /**
     * Constructor: inicializa las celdas del tablero a Muertas.
     * 
     * @param x tamaño en el eje de abcisas.
     * @param y tamaño en el eje de ordenadas.
     * @throws ExcepcionCoordenadaIncorrecta Error que salta cuando se intenta crear una coordenada negativa.
     */
    
    public Tablero2D(int x, int y) throws ExcepcionCoordenadaIncorrecta {
        super(new Coordenada2D(x, y));
        
        for(int i = 0; i < (this.dimensiones).getX(); i++) {
            for(int j = 0; j < (this.dimensiones).getY(); j++) {
                try {
                    celdas.put(new Coordenada2D(i, j), EstadoCelda.MUERTA);
                }catch (ExcepcionCoordenadaIncorrecta e) {
                    throw new ExcepcionEjecucion(e); 
                }
            }
        }
    }
    
    /**
     * Getter.
     */

    @Override
    public abstract ArrayList<Coordenada2D> getPosicionesVecinasCCW(Coordenada2D posicion) throws ExcepcionPosicionFueraTablero;
    
}
