package modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import modelo.excepciones.ExcepcionArgumentosIncorrectos;
import modelo.excepciones.ExcepcionCoordenadaIncorrecta;
import modelo.excepciones.ExcepcionEjecucion;
import modelo.excepciones.ExcepcionPosicionFueraTablero;

/**
 * Clase Tablero: esta clase sirve para crear tableros, modificarlos y obtener informacion de los mismos.
 * 
 * @author Juan Carlos Lopez Gutierrez    48772256C
 * @version 1.0.0
 *
 */
public abstract class Tablero {
	
    /**
     * Variable donde almacenamos la dimension del tablero.
     */
    protected Coordenada dimensiones;
    
    /**
     * Variable donde almacenamos el estado de cada una de las celdas que componen el tablero.
     */
    protected HashMap<Coordenada, EstadoCelda> celdas = new HashMap<Coordenada, EstadoCelda>();
    
    /**
     * Constructor: guarda las dimensiones en su respectiva variable e inicializa todas las celdas que componen el tablero como muertas.
     * 
     * @param dimensiones dimension del tablero
     */
    protected Tablero(Coordenada dimensiones) {
        this.dimensiones = dimensiones;

        /*for(int i = 0; i < this.dimensiones.getX(); i++) {
            for(int j = 0; j < this.dimensiones.getY(); j++) {
                try {
                    celdas.put(new Coordenada(i, j), EstadoCelda.MUERTA);
                }catch (ExcepcionCoordenadaIncorrecta e) {
                    throw new ExcepcionEjecucion(e); 
                }
            }
        }*/
    }
    
    /**
     * Getter.
     * 
     * @return devuelde las dimensiones del tablero.
     */
    public Coordenada getDimensiones() {
        return dimensiones;
        //return new Coordenada(dimensiones);
    }
    
    /**
     * Getter.
     * 
     * @return devuelve una Collection con todas las coordenadas que componen el tablero.
     */
    public Collection<Coordenada> getPosiciones() {
        return celdas.keySet();
    }
    
    /**
     * Getter.
     * 
     * @param posicion coordenada con la que vamos a tratar.
     * @return devuelve el estado de la celda en la coordenada suministrada.
     * @throws ExcepcionPosicionFueraTablero 
     */
    public EstadoCelda getCelda(Coordenada posicion) throws ExcepcionPosicionFueraTablero {
        if(posicion == null)
            throw new ExcepcionArgumentosIncorrectos("El argumento posicion no apunta a nungun sitio.");
        if(contiene(posicion))
            return celdas.get(posicion);
        else {
    	    //throw new ExcepcionPosicionFueraTablero(new Coordenada(dimensiones), new Coordenada(posicion), "fuera de tablero");
    	    throw new ExcepcionPosicionFueraTablero(dimensiones, posicion, "fuera de tablero");
        }
    }
    
    /**
     * Setter: Guardamos en la coordenada pasada el estado celda que pasamos por parametro.
     * 
     * @param posicion coordenada a tratar.
     * @param e estado que queremos almacenar.
     * @throws ExcepcionPosicionFueraTablero 
     */
    public void setCelda(Coordenada posicion, EstadoCelda e) throws ExcepcionPosicionFueraTablero {
        if(posicion == null)
            throw new ExcepcionArgumentosIncorrectos("El argumento posicion no apunta a nungun sitio.");
        if(e == null)
            throw new ExcepcionArgumentosIncorrectos("El argumento e no apunta a nungun sitio.");
        if(contiene(posicion))
            celdas.put(posicion, e);
        else
            throw new ExcepcionPosicionFueraTablero(dimensiones, posicion, "fuera de tablero");
    }
    
    /**
     * Getter.
     * 
     * @param posicion coordenada con las que vamos a tratar.
     * @return devolvemos un array con las coordenadas que tiene al rededor la coordenada pasada.
     * @throws ExcepcionPosicionFueraTablero 
     */
    public abstract ArrayList<Coordenada> getPosicionesVecinasCCW(Coordenada posicion) throws ExcepcionPosicionFueraTablero;
    
    /**
     * Intentamos cargar un patron en este tablero, si se carga, lo almacenamos en los aptrones usados, si no, emitimos un error.
     * 
     * @param patron patron que queremos guardar.
     * @param coordenadaInicial coordenada de la primera posicion del patron (arriba a la izquierda).
     * @return true si se ha completado, false si no.
     * @throws ExcepcionPosicionFueraTablero 
     */
    public void cargaPatron(Patron patron, Coordenada coordenadaInicial) throws ExcepcionPosicionFueraTablero {
        if(coordenadaInicial == null)
            throw new ExcepcionArgumentosIncorrectos("El argumento coordenadaInicial no apunta a nungun sitio.");
        if(patron == null)
            throw new ExcepcionArgumentosIncorrectos("El argumento patron no apunta a nungun sitio.");
        try {
            for(Coordenada c : patron.getPosiciones()) {
                if(!contiene(c.suma(coordenadaInicial))) {
                    throw new ExcepcionPosicionFueraTablero(dimensiones, c.suma(coordenadaInicial), "fuera de tablero");
                }
            }
            for(Coordenada c : patron.getPosiciones()) {this.celdas.put(c.suma(coordenadaInicial), patron.getCelda(c));}
        }catch (ExcepcionCoordenadaIncorrecta ex) {
            throw new ExcepcionEjecucion(ex);
        }
    }
    
    /**
     * Comprobamos que exista una coordenada dentro del tablero.
     * 
     * @param posicion coordenada a evaluar.
     * @return true si existe, false si no.
     */
    public boolean contiene(Coordenada posicion) {
        if(posicion == null)
            throw new ExcepcionArgumentosIncorrectos("El argumento posicion no apunta a nungun sitio.");
        return celdas.containsKey(posicion);
    }
    
    /**
     * Redefine la funcion toString para almacenar la informacion de tablero en un string.
     * 
     * @return devuelve la informacion del tablero.
     */
    /*public String toString() {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("+");
            for(int i = 0; i < dimensiones.getX(); i++)
                sb.append("-");
            sb.append("+\n");
            for(int j = 0; j < dimensiones.getY(); j++) {
                sb.append("|");
                for(int i = 0; i < dimensiones.getX(); i++) {
                    if(celdas.get(new Coordenada(i,j)) == EstadoCelda.VIVA)
                        sb.append("*");
                    else
                        sb.append(" ");
                }
                sb.append("|\n");
            }
            sb.append("+");
            for(int i = 0; i < dimensiones.getX(); i++)
                sb.append("-");
            sb.append("+\n");
            return sb.toString();
        }catch (ExcepcionCoordenadaIncorrecta ex) {
            throw new ExcepcionEjecucion(ex);
        }
    }*/
}