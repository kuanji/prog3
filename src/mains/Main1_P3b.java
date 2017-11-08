package mains;

import java.util.ArrayList;

import modelo.Coordenada2D;
import modelo.excepciones.ExcepcionCoordenada2DIncorrecta;

/**
@author drizo
 **/
public class Main1_P3b {

	/**
	 * Clase principal de la P1
	 * @param args
	 * @throws ExcepcionCoordenada2DIncorrecta 
	 */
	public static void main(String[] args)  {
		try {
			Coordenada2D c0; // no se instancia porque no se ha hecho new
			Coordenada2D c1 = new Coordenada2D(0,0);
			Coordenada2D c2 = new Coordenada2D(10,10);
			Coordenada2D c3 = new Coordenada2D(4,3);
			Coordenada2D c4 = new Coordenada2D(5,15);
			Coordenada2D c5 = new Coordenada2D(c4);
			Coordenada2D c6 = new Coordenada2D(2,5);
			System.out.println(c1.toString());
			System.out.println();
			
			System.out.println(c2.toString());
			System.out.println();
	
			System.out.println(c3.toString());
			System.out.println();
			
			System.out.println(c4.toString());
			System.out.println();
	
			System.out.println(c5.toString());
			System.out.println();
			
			Coordenada2D sumada = (Coordenada2D) c3.suma(c6);
			System.out.println("Suma de " + c3.toString() + "+" + c6.toString() + "= " + sumada.toString());
			System.out.println();
			
			Coordenada2D [] v = new Coordenada2D[5];
			for (int i=0; i<5; i++) {
				v[i] = new Coordenada2D(i,4-i);
			}
			
			for (int i=0; i<5; i++) {
				System.out.println(v[i].getX() + ", " + v[i].getY());
			}
	
			ArrayList<Coordenada2D> v2 = new ArrayList<Coordenada2D>();
			for (int i=0; i<8; i++) {
				v2.add(new Coordenada2D(i, i));
				System.out.println(v2.get(i).toString());
			}
			
			Coordenada2D c7 = (Coordenada2D) c1.suma(c3);
			if (c7.equals(c3)) {
				System.out.println("c7 y c3 son iguales");
			}
			
		} catch (Exception e) {
			e.printStackTrace(); // NO DEBE SALTAR NUNCA, EL UNICO ERROR YA SE HA CAPTURADO
		}
		
	}

}
