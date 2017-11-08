package modelo.excepciones;

public class ExcepcionCoordenada2DIncorrecta extends ExcepcionCoordenadaIncorrecta {
	
	private int x, y;
	
	public ExcepcionCoordenada2DIncorrecta(int x, int y, String s){
	    super(s);
		this.x = x;
		this.y = y;
	}
	public String getMessage() {return super.toString();}
	
	public int getX() {return this.x;}
	
	public int getY() {return this.y;}
}
