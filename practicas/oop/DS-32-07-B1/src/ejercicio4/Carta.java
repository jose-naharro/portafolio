package ejercicio4;

import java.util.Objects;

/**
 *
 *@author natalia.iglesiast
 *@author jose.naharro
 */
public final class Carta {

    private final PaloCarta palo;
    private final NumeroCarta numero;

    public Carta(NumeroCarta numero, PaloCarta palo) {
        this.palo = palo;
        this.numero = numero;
    }

// No se piden getters en el enunciado.
   
    @Override
    public String toString() {
        return this.numero + " de " + this.palo;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Carta)) {
            return false;
        }

        Carta c = (Carta) obj;

        return numero.equals(c.numero) && palo.equals(c.palo);
    }

    @Override
    public int hashCode() {
        return this.numero.ordinal() + 31*this.palo.ordinal();
    }

}
