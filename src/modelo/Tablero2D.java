package modelo;

import java.util.ArrayList;

import modelo.excepciones.ExcepcionCoordenadaIncorrecta;
import modelo.excepciones.ExcepcionEjecucion;
import modelo.excepciones.ExcepcionPosicionFueraTablero;

/**
 * 
 * @author Juanki
 *
 */

public abstract class Tablero2D extends Tablero {

    /**
     * 
     * @param x
     * @param y
     * @throws ExcepcionCoordenadaIncorrecta
     */
    
    public Tablero2D(int x, int y) throws ExcepcionCoordenadaIncorrecta {
        super(new Coordenada2D(x, y));
        
        for(int i = 0; i < ((Coordenada2D) this.dimensiones).getX(); i++) {
            for(int j = 0; j < ((Coordenada2D) this.dimensiones).getY(); j++) {
                try {
                    celdas.put(new Coordenada2D(i, j), EstadoCelda.MUERTA);
                }catch (ExcepcionCoordenadaIncorrecta e) {
                    throw new ExcepcionEjecucion(e); 
                }
            }
        }
    }
    
    /**
     * 
     */

    @Override
    public abstract ArrayList<Coordenada> getPosicionesVecinasCCW(Coordenada posicion) throws ExcepcionPosicionFueraTablero;
    
}
