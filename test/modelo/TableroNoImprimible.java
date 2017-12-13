/**
 * 
 */
package modelo;

import java.util.ArrayList;

import modelo.Coordenada;
import modelo.Tablero;
import modelo.excepciones.ExcepcionCoordenadaIncorrecta;
import modelo.excepciones.ExcepcionPosicionFueraTablero;

/**
 * @author gonzalo
 *
 */

final public class TableroNoImprimible extends Tablero {
			
		public TableroNoImprimible(Coordenada dimensiones)
				throws ExcepcionCoordenadaIncorrecta {
			super(dimensiones);
			// TODO Auto-generated constructor stub
		}

		@Override
		public ArrayList<Coordenada> getPosicionesVecinasCCW(Coordenada posicion)
				throws ExcepcionPosicionFueraTablero {
			// TODO Auto-generated method stub
			return null;
		}
		
	
}
