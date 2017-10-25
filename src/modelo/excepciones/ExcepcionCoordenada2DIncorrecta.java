package modelo.excepciones;

public class ExcepcionCoordenada2DIncorrecta extends ExcepcionCoordenadaIncorrecta {
	
	private int x, y;
	
	ExcepcionCoordenada2DIncorrecta(int x, int y){
		this.x = x;
		this.y = y;
	}
}
