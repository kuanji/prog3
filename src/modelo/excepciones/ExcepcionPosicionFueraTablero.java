package modelo.excepciones;

import modelo.Coordenada;

public class ExcepcionPosicionFueraTablero extends Exception{
	
	private Coordenada dimensiones;
	private Coordenada coordenada;
	
	public ExcepcionPosicionFueraTablero(Coordenada dimensiones, Coordenada coordenada){
		this.dimensiones = dimensiones;
		this.coordenada = coordenada;
	}
	
	public String getMessage(){
	    StringBuilder sb = new StringBuilder();
        sb.append("La coordenada ");
        sb.append(coordenada.toString());
        sb.append(" se encuentra ");
        sb.append(super.getMessage());
        sb.append(" en un tablero de dimensiones ");
        sb.append(dimensiones.toString());
        sb.append(".");
        return sb.toString();
    }
	
	public Coordenada getDimensiones() {return this.dimensiones;}
	
	public Coordenada getCoordenada() {return this.coordenada;}
	
}
