package modelo;

import java.util.ArrayList;

import modelo.excepciones.ExcepcionArgumentosIncorrectos;
import modelo.excepciones.ExcepcionCoordenadaIncorrecta;
import modelo.excepciones.ExcepcionEjecucion;
import modelo.excepciones.ExcepcionPosicionFueraTablero;

public class TableroCeldasCuadradas extends Tablero2D {

    public TableroCeldasCuadradas(int x, int y) throws ExcepcionCoordenadaIncorrecta {
        super(x, y);
    }
    
    /**
     * Getter.
     * 
     * @param posicion coordenada con las que vamos a tratar.
     * @return devolvemos un array con las coordenadas que tiene al rededor la coordenada pasada.
     * @throws ExcepcionPosicionFueraTablero 
     */
    public ArrayList<Coordenada> getPosicionesVecinasCCW(Coordenada2D posicion) throws ExcepcionPosicionFueraTablero {
        if(posicion == null)
            throw new ExcepcionArgumentosIncorrectos("Coorndenada a null.");
        ArrayList<Coordenada> vecinas = new ArrayList<Coordenada>();
        if(!contiene(posicion)) {
            throw new ExcepcionPosicionFueraTablero(dimensiones, posicion, "fuera de tablero");
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
    
    public String toString() {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("+");
            for(int i = 0; i < ((Coordenada2D) dimensiones).getX(); i++)            //Preguntar casts
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
        }catch (ExcepcionCoordenadaIncorrecta ex) {
            throw new ExcepcionEjecucion(ex);
        }
    }
    
}
