package modelo.excepciones;

public class ExcepcionCoordenada1DIncorrecta extends ExcepcionCoordenadaIncorrecta {
	
	private int x;
	
	public ExcepcionCoordenada1DIncorrecta(int x){
		this.x = x;
	}
	public String getMessage() {
		return "Error coordenada incorrecta: (" + x + ").";
	}
	
	public int getX() {
		return this.x;
	}
}
