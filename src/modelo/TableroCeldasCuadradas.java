package modelo;

import java.util.ArrayList;

import modelo.excepciones.ExcepcionArgumentosIncorrectos;
import modelo.excepciones.ExcepcionCoordenadaIncorrecta;
import modelo.excepciones.ExcepcionEjecucion;
import modelo.excepciones.ExcepcionPosicionFueraTablero;

/**
 * Clase Tablero2D: esta clase sirve para tratar con los tableros de dos dimensiones.
 * 
 * @author Juan Carlos Lopez Gutierrez    48772256C
 * @version 1.0.0
 *
 */

public class TableroCeldasCuadradas extends Tablero2D implements Imprimible{
    
    /**
     * 
     * @param x
     * @param y
     * @throws ExcepcionCoordenadaIncorrecta
     */

    public TableroCeldasCuadradas(int x, int y) throws ExcepcionCoordenadaIncorrecta {
        super(x, y);
    }
    
    /**
     * Getter.
     */
    
    public ArrayList<Coordenada> getPosicionesVecinasCCW(Coordenada pos) throws ExcepcionPosicionFueraTablero {
        Coordenada2D posicion = (Coordenada2D) pos;

    	if(posicion == null)
            throw new ExcepcionArgumentosIncorrectos();
        ArrayList<Coordenada> vecinas = new ArrayList<Coordenada>();
        if(!contiene(posicion)) {
            throw new ExcepcionPosicionFueraTablero(dimensiones, posicion);
        }
        else {
            try {
                if(posicion.getX()-1 >= 0) {
                    if(posicion.getY()-1 >= 0)
                        if(contiene(new Coordenada2D(posicion.getX()-1,posicion.getY()-1))) {vecinas.add(new Coordenada2D(posicion.getX()-1,posicion.getY()-1));}
                    if(contiene(new Coordenada2D(posicion.getX()-1,posicion.getY()))) {vecinas.add(new Coordenada2D(posicion.getX()-1,posicion.getY()));}
                    if(contiene(new Coordenada2D(posicion.getX()-1,posicion.getY()+1))) {vecinas.add(new Coordenada2D(posicion.getX()-1,posicion.getY()+1));}
                }
                if(contiene(new Coordenada2D(posicion.getX(),posicion.getY()+1))) {vecinas.add(new Coordenada2D(posicion.getX(),posicion.getY()+1));}
                if(contiene(new Coordenada2D(posicion.getX()+1,posicion.getY()+1))) {vecinas.add(new Coordenada2D(posicion.getX()+1,posicion.getY()+1));}
                if(contiene(new Coordenada2D(posicion.getX()+1,posicion.getY()))) {vecinas.add(new Coordenada2D(posicion.getX()+1,posicion.getY()));}
                if(posicion.getY()-1 >= 0) {    
                    if(contiene(new Coordenada2D(posicion.getX()+1,posicion.getY()-1))) {vecinas.add(new Coordenada2D(posicion.getX()+1,posicion.getY()-1));}
                    if(contiene(new Coordenada2D(posicion.getX(),posicion.getY()-1))) {vecinas.add(new Coordenada2D(posicion.getX(),posicion.getY()-1));}
                }
            }catch (ExcepcionCoordenadaIncorrecta e){
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
            sb.append("+");
            for(int i = 0; i < ((Coordenada2D) dimensiones).getX(); i++)
                sb.append("-");
            sb.append("+\n");
            for(int j = 0; j < ((Coordenada2D) dimensiones).getY(); j++) {
                sb.append("|");
                for(int i = 0; i < ((Coordenada2D) dimensiones).getX(); i++) {
                    if(celdas.get(new Coordenada2D(i,j)) == EstadoCelda.VIVA)
                        sb.append("*");
                    else
                        sb.append(" ");
                }
                sb.append("|\n");
            }
            sb.append("+");
            for(int i = 0; i < ((Coordenada2D) dimensiones).getX(); i++)
                sb.append("-");
            sb.append("+\n");
            return sb.toString();
        }catch (ExcepcionCoordenadaIncorrecta e) {
            throw new ExcepcionEjecucion(e);
        }
    }

	@Override
	public String generaCadena() {
		return toString();
	}
    
}
