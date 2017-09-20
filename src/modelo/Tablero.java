package modelo;

/**
 * 
 * @author root
 *
 */

/**
 * @author root
 *
 */
public class Tablero {
	
	/**
	 * 
	 */
	
	private Coordenada dimensiones;
	
	/**
	 * 
	 * @param dims
	 */
	
	public Tablero(Coordenada dims) 
	{
		
	}

	/**
	 * 
	 */
	
	@Override
	public String toString() {
		return "Tablero [dimensiones=" + dimensiones + "]";
	}

	/**
	 * 
	 */
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dimensiones == null) ? 0 : dimensiones.hashCode());
		return result;
	}

	/**
	 * 
	 */
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tablero other = (Tablero) obj;
		if (dimensiones == null) {
			if (other.dimensiones != null)
				return false;
		} else if (!dimensiones.equals(other.dimensiones))
			return false;
		return true;
	};
	
	
}
