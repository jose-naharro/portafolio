package ejercicio4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 *@author natalia.iglesiast
 *@author jose.naharro
 */
public class Mazo {

    private List<Carta> mazo;

    public Mazo() {
        this.mazo = new ArrayList<>();
    }

    public void insertaCarta(Carta carta) {
        // Se pueden insertar cartas repetidas? SI que se puede
            this.mazo.add(carta);
    }
    
    public void insertaCarta(int posicion, Carta carta) {
        // Se pueden insertar cartas repetidas? SI que se puede
        if ((posicion < 0) || (posicion > this.mazo.size()) || (this.mazo.isEmpty() && (posicion != 0))) {
            throw new IndexOutOfBoundsException();
        } else {
            this.mazo.add(posicion, carta);
        }
    }

    public Carta extraeCarta() {
        //Extrae la ultima carta (la ultima posicion)

        Carta cartaExtraida;

        if (this.mazo.isEmpty()) {
            throw new IndexOutOfBoundsException();
        } else {
            cartaExtraida = this.mazo.get(this.mazo.size() - 1); // Guardo la carta que saco para mostrarla
            this.mazo.remove(this.mazo.size() - 1); // La elimino del mazo ya que es extraccion sin retorno
            return cartaExtraida;// Se muestra la carta que hemos extraido del mazo

        }
    }

    public Carta extraeCarta(int posicion) {

        Carta cartaExtraida;

        if (this.mazo.isEmpty() || (posicion >= this.mazo.size()) || (posicion < 0)) {
            throw new IndexOutOfBoundsException();
        } else {
            cartaExtraida = this.mazo.get(posicion); // Guardo la carta que saco para mostrarla
            this.mazo.remove(posicion); // La elimino del mazo ya que es extraccion sin retorno, se reordena el ArrayList?
            return cartaExtraida;// Se muestra la carta que hemos extraido del mazo
        }

    }

    public void barajar() {//Una baraja no se puede barajar, un mazo si
        Collections.shuffle(this.mazo);
    }

    public int numCartas() {
        return this.mazo.size();
    }

    public Carta getCarta(int posicion) {
        if (this.mazo.isEmpty() || (posicion >= this.mazo.size()) || (posicion < 0)) {
            throw new IndexOutOfBoundsException();
        } else {
            return this.mazo.get(posicion);
        }
    }
    
}
