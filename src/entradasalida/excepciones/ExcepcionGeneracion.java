package entradasalida.excepciones;

public class ExcepcionGeneracion extends Exception{

	public ExcepcionGeneracion() {
		
	}
	
	public ExcepcionGeneracion(String s) {
		super(s);
	}
	
	public ExcepcionGeneracion(Throwable causa) {
		super(causa);
	}
}
