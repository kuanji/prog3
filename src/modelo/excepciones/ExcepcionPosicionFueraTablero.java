package modelo.excepciones;

import modelo.Coordenada;

public class ExcepcionPosicionFueraTablero extends Exception{
	
	private Coordenada dimensiones;
	private Coordenada coordenada;
	
	public ExcepcionPosicionFueraTablero(Coordenada dimensiones, Coordenada coordenada, String s){
	    super(s);
		this.dimensiones = new Coordenada(dimensiones);
		this.coordenada = new Coordenada(coordenada);
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
	
	public Coordenada getDimensiones() {return new Coordenada(this.dimensiones);}
	
	public Coordenada getCoordenada() {return new Coordenada(this.coordenada);}
	
}
