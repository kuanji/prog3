package mains;

import modelo.Coordenada1D;
import modelo.EstadoCelda;
import modelo.Juego;
import modelo.Patron;
import modelo.Regla30;
import modelo.Tablero1D;

public class Main3 {

	/**
	 * @param args
	 * @throws ExcepcionJuegoVida 
	 * @throws ExcepcionExportacion 
	 * @throws ExcepcionLectura 
	 */
	public static void main(String[] args)  {
		try {
			Tablero1D tablero = new Tablero1D(43);
			Regla30 regla = new Regla30();
			Tablero1D tableroPatrong = new Tablero1D(1);
			tableroPatrong.setCelda(new Coordenada1D(0), EstadoCelda.VIVA);
			Patron p = new Patron("Simple", tableroPatrong);
			
			Juego juego = new Juego(tablero, regla);
			juego.cargaPatron(p, new Coordenada1D(22));
			for (int i=0; i<22; i++) {
				System.out.print(juego.getTablero().toString());
				juego.actualiza();
			}
		} catch (Exception e) {
			e.printStackTrace(); //NO DEBERIA DAR NUNCA
		}
	}

}
