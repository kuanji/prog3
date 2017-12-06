package entradasalida.excepciones;

public class ExcepcionLectura extends Exception{
	
	public ExcepcionLectura() {
		
	}
	
	public ExcepcionLectura(String s) {
		super(s);
	}

	public ExcepcionLectura(Throwable causa) {
	    super(causa);
	}
}
