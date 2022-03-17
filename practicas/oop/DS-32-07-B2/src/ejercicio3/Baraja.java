package ejercicio3;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *@author natalia.iglesiast
 *@author jose.naharro
 */
public final class Baraja {

    private final List<Carta> baraja;

    public Baraja() {
        this.baraja = new ArrayList<>();

        for (PaloCarta palo : PaloCarta.values()) {
            for (NumeroCarta numero : NumeroCarta.values()) {
                this.baraja.add(new Carta(numero, palo));
            }
        }
    }

    public Carta getCarta(int posicion) {
        if ( (posicion > (this.baraja.size() - 1)) || (posicion < 0) ) {
            throw new IndexOutOfBoundsException();
        } else {
            return this.baraja.get(posicion);
        }
    }

    public Mazo getMazo() {

        Baraja baraja = new Baraja();
        Mazo mazo = new Mazo();

        for (int i = 0; i < baraja.baraja.size(); i++) {//Metemos todas las cartas de la baraja en el mazo
            mazo.insertaCarta(baraja.baraja.get(i));    //de manera ordenada.
        }

        mazo.barajar();//barajamos el mazo, ya que la baraja NO se puede barajar (leer enunciado)

        return mazo;

    }

}


