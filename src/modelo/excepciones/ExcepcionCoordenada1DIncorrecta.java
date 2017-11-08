package modelo.excepciones;

public class ExcepcionCoordenada1DIncorrecta extends ExcepcionCoordenadaIncorrecta {
	
	private int x;
	
	public ExcepcionCoordenada1DIncorrecta(int x, String s){
	    super(s);
		this.x = x;
	}
	public String getMessage() {return super.getMessage();}
	
	public int getX() {
		return this.x;
	}
}
