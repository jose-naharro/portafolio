package ejercicio3;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author natalia.iglesiast
 * @author jose.naharro
 */

public class BarajaTest {

    public BarajaTest() {
    }

    @Test
    public void testGetCarta() {
        int posicion = 0;

        Baraja instance = new Baraja();
        Carta expResult = new Carta(NumeroCarta.AS, PaloCarta.ESPADAS);
        Carta result = instance.getCarta(posicion);
        assertEquals(expResult, result);

        posicion = 12;
        expResult = new Carta(NumeroCarta.TRES, PaloCarta.COPAS);
        result = instance.getCarta(posicion);
        assertEquals(expResult, result);

        posicion = 29;
        expResult = new Carta(NumeroCarta.REY, PaloCarta.BASTOS);
        result = instance.getCarta(posicion);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetMazo() {
        Baraja baraja = new Baraja();
        Mazo mazo = new Mazo();

        mazo = baraja.getMazo();

        //Veamos tambien si estan algo desordenadas
        //Lo hacemos comprobando que mazo no esta en el mismo orden que baraja    
        int acumulador = 0;
        for (int i = 0; i < mazo.numCartas(); i++) {
            if (baraja.getCarta(i) != mazo.getCarta(i)) {
                acumulador++;
            }
        }
        assertEquals(true, (acumulador > 30)); //Vemos que hayan mas de 30 cartas fuera de su sitio para decir que estaba desordenado el mazo
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testPosicionNoValida1() {
        int posicion = -1;
        Baraja instance = new Baraja();
        instance.getCarta(posicion);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testPosicionNoValida2() {
        int posicion = 40;
        Baraja instance = new Baraja();
        instance.getCarta(posicion);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testPosicionNoValida3() {
        int posicion = 50;
        Baraja instance = new Baraja();
        instance.getCarta(posicion);
    }
}
