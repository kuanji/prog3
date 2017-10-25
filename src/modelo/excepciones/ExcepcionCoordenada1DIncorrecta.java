package modelo.excepciones;

public class ExcepcionCoordenada1DIncorrecta extends ExcepcionCoordenadaIncorrecta {
	
	private int x;
	
	ExcepcionCoordenada1DIncorrecta(int x){
		this.x = x;
	}
}
