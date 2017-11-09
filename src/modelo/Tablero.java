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
     * Constructor: guarda las dimensiones en su respectiva variable.
     * 
     * @param dimensiones dimension del tablero
     */
    protected Tablero(Coordenada dimensiones) {
    	if (dimensiones == null)
			throw new ExcepcionArgumentosIncorrectos();
        this.dimensiones = dimensiones;
    }
    
    /**
     * Getter.
     * 
     * @return devuelde las dimension del tablero.
     */
    public Coordenada getDimensiones() {
        return dimensiones;
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
            throw new ExcepcionArgumentosIncorrectos();
        if(contiene(posicion))
            return celdas.get(posicion);
        else {
    	    throw new ExcepcionPosicionFueraTablero(dimensiones, posicion);
        }
    }
    
    /**
     * Setter: Guardamos en la posicion pasada el estado celda que pasamos por parametro.
     * 
     * @param posicion lugar donde guardamos la informacion.
     * @param e estado que queremos almacenar.
     * @throws ExcepcionPosicionFueraTablero 
     */
    public void setCelda(Coordenada posicion, EstadoCelda e) throws ExcepcionPosicionFueraTablero {
        if(posicion == null)
            throw new ExcepcionArgumentosIncorrectos();
        if(e == null)
            throw new ExcepcionArgumentosIncorrectos();
        if(contiene(posicion))
            celdas.put(posicion, e);
        else
            throw new ExcepcionPosicionFueraTablero(dimensiones, posicion);
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
     * @throws ExcepcionPosicionFueraTablero 
     */
    public void cargaPatron(Patron patron, Coordenada coordenadaInicial) throws ExcepcionPosicionFueraTablero {
        if(coordenadaInicial == null)
            throw new ExcepcionArgumentosIncorrectos();
        if(patron == null)
            throw new ExcepcionArgumentosIncorrectos();
        try {
            for(Coordenada c : patron.getPosiciones()) {
                if(!contiene(c.suma(coordenadaInicial))) {
                    throw new ExcepcionPosicionFueraTablero(dimensiones, c.suma(coordenadaInicial));
                }
            }
            for(Coordenada c : patron.getPosiciones()) {this.celdas.put(c.suma(coordenadaInicial), patron.getCelda(c));}
        }catch (ExcepcionCoordenadaIncorrecta ex) {
            throw new ExcepcionEjecucion(ex);
        }
    }
    
    /**
     * Comprobamos que exista la coordenada pasada en tablero.
     * 
     * @param posicion coordenada a evaluar.
     * @return true si existe, false si no.
     */
    public boolean contiene(Coordenada posicion) {
        if(posicion == null)
            throw new ExcepcionArgumentosIncorrectos();
        return celdas.containsKey(posicion);
    }
}