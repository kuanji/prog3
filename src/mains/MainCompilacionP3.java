package mains;
import modelo.*;
import modelo.d1.Coordenada1D;
import modelo.d1.ExcepcionCoordenada1DIncorrecta;
import modelo.d1.Regla30;
import modelo.d1.Tablero1D;
import modelo.d2.Coordenada2D;
import modelo.d2.ExcepcionCoordenada2DIncorrecta;
import modelo.d2.ReglaConway;
import modelo.d2.Tablero2D;
import modelo.d2.TableroCeldasCuadradas;
import modelo.excepciones.*;

public class MainCompilacionP3 {

	/**
	 * @param args
	 * @throws ExcepcionCoordenadaIncorrecta 
	 */
	public static void main(String[] args) throws ExcepcionCoordenadaIncorrecta {
		// TODO Auto-generated method stub
		Tablero t1d = new Tablero1D(4);
		Tablero t2d = new TableroCeldasCuadradas(4,6);
		Coordenada1D c1d = new Coordenada1D(0);
		Coordenada2D c2d = new Coordenada2D(0,0);
		Tablero t = t1d;
		Patron p1d = new Patron("1D", t1d);
		Patron p2d = new Patron("2D", t2d);
		Regla r1d = new Regla30();
		Regla r2d = new ReglaConway();
		Juego j = new Juego(t2d, r2d);
		
		try { Coordenada c1 = new Coordenada1D(5); } catch (ExcepcionCoordenadaIncorrecta ex) {System.out.println("Hey c1");}
		try { Coordenada c1 = new Coordenada2D(1,5); } catch (ExcepcionCoordenadaIncorrecta ex) {System.out.println("Hey c2");}
		try { c1d.suma(c1d); } catch (ExcepcionCoordenadaIncorrecta ex) {System.out.println("Hey suma");}
		try { Tablero t1 = new Tablero1D(10); } catch (ExcepcionCoordenadaIncorrecta ex) {System.out.println("Hey t1");}
		try { Tablero t2 = new TableroCeldasCuadradas(4,6); } catch (ExcepcionCoordenadaIncorrecta ex) {System.out.println("Hey t2");}
		try { t.getCelda(c1d); } catch (ExcepcionPosicionFueraTablero ex) {System.out.println("Hey celda1");}
		try { t.setCelda(c1d, EstadoCelda.VIVA); } catch (ExcepcionPosicionFueraTablero ex) {System.out.println("Hey celda2");}
		try { t.cargaPatron(p1d, c1d); } catch (ExcepcionPosicionFueraTablero ex) {System.out.println("Hey patron");}
		try { t.getPosicionesVecinasCCW(c1d); } catch (ExcepcionPosicionFueraTablero ex) {System.out.println("Hey vecinas");}
		try { p1d.getCelda(c1d); } catch (ExcepcionPosicionFueraTablero ex) {System.out.println("Hey celdas");}
		try { p2d.getCelda(c2d); } catch (ExcepcionPosicionFueraTablero ex) {System.out.println("Hey celdas2");}
		try { r1d.calculaSiguienteEstadoCelda(t, c1d); } catch (ExcepcionPosicionFueraTablero ex) {System.out.println("Hey estado1");}
		try { r2d.calculaSiguienteEstadoCelda(t, c1d); } catch (ExcepcionPosicionFueraTablero ex) {System.out.println("Hey estado2");}
		try { j.cargaPatron(p2d, c2d); } catch (ExcepcionPosicionFueraTablero ex) {System.out.println("Hey patron2");}

		
		try {
			//Coordenada
			Coordenada c1 = new Coordenada1D(new Coordenada1D(5));
			Coordenada c2 = new Coordenada2D(new Coordenada2D(1,2));
			c1.suma(c1);
			System.out.println(c1.suma(c1).toString());
			c2.suma(c2);
			System.out.println(c2.suma(c2).toString());
			System.out.println(c1.toString());
			System.out.println(c2.toString());
			Coordenada1D c1d1= (Coordenada1D)c1;
			Coordenada2D c2d1= (Coordenada2D)c2;
			System.out.println(c1d1.equals(c1));
			System.out.println(c2d1.equals(c2));
			System.out.println(c1d1.getX());
			System.out.println(c2d1.getX()); System.out.println(c2d1.getY());
			
			//Tablero
			Tablero t1 = new Tablero1D(10);
			Tablero t2 = new TableroCeldasCuadradas(4,6);
			Tablero2D t3 = (Tablero2D)t2;
			t1.getDimensiones(); t1.getPosiciones(); t1.getCelda(c1);
			t1.setCelda(c1, EstadoCelda.MUERTA);
			t1.cargaPatron(p1d, c1);
			t1.contiene(c1);
			Tablero1D t1d1 = (Tablero1D)t1;
			TableroCeldasCuadradas t2dc = (TableroCeldasCuadradas)t2;
			t1d1.getPosicionesVecinasCCW(c1); t1d1.toString();
			t2dc.getPosicionesVecinasCCW(c2); t2dc.toString();
			
			//Patron
			Patron p = new Patron("Test",t1);
			p.getNombre(); p.getCelda(c1); p.getPosiciones(); p.toString();
			
			//Regla
			Regla rc = new ReglaConway();
			Regla r30 = new Regla30();
			rc.calculaSiguienteEstadoCelda(t2,c2);
			r30.calculaSiguienteEstadoCelda(t1,c1);
			
			//Juego
			Juego j1 = new Juego(t1,r30);
			j1.cargaPatron(p1d,c1);
			j1.actualiza();
			j1.getTablero();
			j1.getPatrones();
			
			//EstadoCelda
			EstadoCelda estado = EstadoCelda.MUERTA;
			estado = EstadoCelda.VIVA;
			
			//Excepciones
			if (false) throw new ExcepcionCoordenada1DIncorrecta(10);
			if (false) throw new ExcepcionCoordenada2DIncorrecta(10,10);
			if (false) throw new ExcepcionCoordenadaIncorrecta();
			if (false) throw new ExcepcionArgumentosIncorrectos();
			if (true) throw new ExcepcionPosicionFueraTablero(c1,c1);
			if (false) throw new ExcepcionEjecucion("Error");
			
			System.out.println("FIN.");
			
		} catch (ExcepcionCoordenada1DIncorrecta e) {
			// TODO Auto-generated catch block
			e.getMessage();
			e.getX();
			e.printStackTrace();
		} catch (ExcepcionPosicionFueraTablero e1) {
			e1.getMessage();
			e1.getDimensiones();
			e1.getCoordenada();
			e1.printStackTrace();
		} catch (ExcepcionCoordenada2DIncorrecta e2) {
			e2.getMessage(); 
			e2.getX(); e2.getY();
			e2.printStackTrace();
		} catch (ExcepcionArgumentosIncorrectos e3) {
			e3.printStackTrace();
		} catch (ExcepcionEjecucion e4) {
			e4.printStackTrace();
		} catch (ExcepcionCoordenadaIncorrecta e5) {
			e5.printStackTrace();
		}
	}

}
