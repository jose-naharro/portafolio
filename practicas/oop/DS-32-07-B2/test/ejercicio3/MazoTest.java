package ejercicio3;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author natalia.iglesiast
 * @author jose.naharro
 */
public class MazoTest {

    @Test
    public void testInsertaYExtraeCarta() {
        Mazo instance = new Mazo();
        instance.insertaCarta(new Carta(NumeroCarta.TRES, PaloCarta.OROS));
        instance.insertaCarta(new Carta(NumeroCarta.CINCO, PaloCarta.BASTOS));

        Carta expResult = new Carta(NumeroCarta.CINCO, PaloCarta.BASTOS);
        Carta result = instance.extraerCarta();
        assertEquals(expResult, result);

        expResult = new Carta(NumeroCarta.TRES, PaloCarta.OROS);
        result = instance.extraerCarta();
        assertEquals(expResult, result);

        /* Con indice. */
        instance.insertaCarta(0, new Carta(NumeroCarta.REY, PaloCarta.COPAS));
        instance.insertaCarta(1, new Carta(NumeroCarta.SOTA, PaloCarta.ESPADAS));
//        instance.insertaCarta(0, new Carta(NumeroCarta.REY, PaloCarta.COPAS));

        expResult = new Carta(NumeroCarta.SOTA, PaloCarta.ESPADAS);
        result = instance.extraeCarta(1);
        assertEquals(expResult, result);
    }

    @Test
    public void testNumCartas() {
        Mazo instance = new Mazo();
        int expResult = 0;
        int result = instance.numCartas();
        assertEquals(expResult, result);

        //insertando cartas
        instance.insertaCarta(0, new Carta(NumeroCarta.REY, PaloCarta.COPAS));
        instance.insertaCarta(1, new Carta(NumeroCarta.SOTA, PaloCarta.ESPADAS));

        expResult = 2;
        result = instance.numCartas();
        assertEquals(expResult, result);

        Mazo mazoCompleto = new Baraja().getMazo();
        expResult = 40;
        result = mazoCompleto.numCartas();
        assertEquals(expResult, result);

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testPosicionNoValidaInsertarCartaPosicion1() {
        int posicion = -1;
        Mazo mazo = new Baraja().getMazo();
        mazo.insertaCarta(posicion, new Carta(NumeroCarta.REY, PaloCarta.COPAS));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testPosicionNoValidaInsertarCartaPosicion2() {
        int posicion = 41;//En la 40 si se puede insertar, seria como insertar al final del mazo
        Mazo mazo = new Baraja().getMazo();
        mazo.insertaCarta(posicion, new Carta(NumeroCarta.REY, PaloCarta.COPAS));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testPosicionNoValidaInsertarCartaPosicion3() {
        int posicion = 1;
        Mazo mazo = new Mazo();
        mazo.insertaCarta(posicion, new Carta(NumeroCarta.REY, PaloCarta.COPAS));
    }


    @Test(expected = IndexOutOfBoundsException.class)
    public void testExtraeCartaMazoVacio() {
        Mazo mazo = new Mazo();
        mazo.extraerCarta();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testExtraeCartaPosicion1() {
        Mazo mazo = new Mazo();
        mazo.extraeCarta(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testExtraeCartaPosicion2() {
        Mazo mazo = new Mazo();
        mazo.extraeCarta(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testExtraeCartaPosicion3() {
        Mazo mazo = new Baraja().getMazo();
        mazo.extraeCarta(-1);
    }

//    @Test(expected = IndexOutOfBoundsException.class)
//    public void testExtraeCartaPosicion4() {
//        Mazo mazo = new Baraja().getMazo();
//        mazo.extraeCarta(40);
//    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetCartaPosicionNoValida1() {
        int posicion = -1;
        Mazo mazo = new Baraja().getMazo();
        mazo.getCarta(posicion);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetCartaPosicionNoValida2() {
        int posicion = 40;
        Mazo mazo = new Baraja().getMazo();
        mazo.getCarta(posicion);
    }

}
