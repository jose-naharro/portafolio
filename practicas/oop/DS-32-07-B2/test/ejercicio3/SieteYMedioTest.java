package ejercicio3;

import org.junit.Test;

/**
 *
 * @author natalia.iglesiast
 * @author jose.naharro
 */
public class SieteYMedioTest {

    public SieteYMedioTest() {
    }

    /**
     * Test of jugar method, of class SieteYMedio.
     */
    @Test
    public void testJugar() {

        Comportamiento compPepe = new Comp1();
        Jugador Pepe = new Jugador("Pepe", compPepe);

        Comportamiento compJuan = new CompTonto();
        Jugador Juan = new Jugador("Juan", compJuan);

        Comportamiento compMaria = new CompSeguro();
        Jugador Maria = new Jugador("Maria", compMaria);

        Comportamiento compLucas = new CompSeguro();
        Jugador Lucas = new Jugador("Lucas", compLucas);

        SieteYMedio partida = new SieteYMedio();
        partida.jugar(Pepe,Juan,Maria,Lucas);
    }

}
