package modelo;

import java.util.ArrayList;

import modelo.excepciones.ExcepcionCoordenadaIncorrecta;
import modelo.excepciones.ExcepcionEjecucion;
import modelo.excepciones.ExcepcionPosicionFueraTablero;

public class Tablero2D extends Tablero {

    public Tablero2D(int x, int y) throws ExcepcionCoordenadaIncorrecta {
        super(new Coordenada2D(x, y));
        
        for(int i = 0; i < ((Coordenada2D) this.dimensiones).getX(); i++) {         //Preguntar por el cast
            for(int j = 0; j < ((Coordenada2D) this.dimensiones).getY(); j++) {     //de coordenada 2D
                try {
                    celdas.put(new Coordenada2D(i, j), EstadoCelda.MUERTA);
                }catch (ExcepcionCoordenadaIncorrecta e) {
                    throw new ExcepcionEjecucion(e); 
                }
            }
        }
    }

    @Override
    public ArrayList<Coordenada> getPosicionesVecinasCCW(Coordenada posicion) throws ExcepcionPosicionFueraTablero {
        // TODO Auto-generated method stub
        return null;
    }
    
}
