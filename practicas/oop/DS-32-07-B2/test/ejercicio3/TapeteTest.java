package ejercicio3;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author natalia.iglesiast
 * @author jose.naharro
 */
public class TapeteTest {

    @Test
    public void insertaJugador() {
        Tapete t = new Tapete();

        Comportamiento compPepe = new Comp1();
        Jugador j = new Jugador("Pepe", compPepe);
        j.inicializaMano();

        Carta c1 = new Carta(NumeroCarta.SIETE, PaloCarta.BASTOS);
        Carta c2 = new Carta(NumeroCarta.SOTA, PaloCarta.ESPADAS);

        c1.setDescubierta(false);
        j.insertaCarta(c1);
        c2.setDescubierta(true);
        j.insertaCarta(c2);

        List<Carta> manoPepe = new ArrayList<>();
        manoPepe = j.getMano();

        t.insertaJugador(manoPepe, false);

        assertEquals(manoPepe, t.cartasJugador(0));
        assertEquals(false, t.eliminadoJugador(0));

        Comportamiento compJuan = new CompTonto();
        Jugador x = new Jugador("Juan", compJuan);
        x.inicializaMano();

        Carta xc1 = new Carta(NumeroCarta.DOS, PaloCarta.BASTOS);
        Carta xc2 = new Carta(NumeroCarta.TRES, PaloCarta.OROS);

        xc1.setDescubierta(false);
        x.insertaCarta(xc1);
        xc2.setDescubierta(true);
        x.insertaCarta(xc2);
        
        List<Carta> manoJuan= new ArrayList<>();
        manoJuan = x.getMano();

        t.insertaJugador(manoJuan, false);

        assertEquals(manoJuan, t.cartasJugador(1));
        assertEquals(false, t.eliminadoJugador(1));

        Comportamiento compMaria = new CompSeguro();
        Jugador y = new Jugador("Maria", compMaria);
        y.inicializaMano();

        Carta yc1 = new Carta(NumeroCarta.CUATRO, PaloCarta.BASTOS);
        Carta yc2 = new Carta(NumeroCarta.TRES, PaloCarta.COPAS);

        yc1.setDescubierta(false);
        y.insertaCarta(yc1);
        yc2.setDescubierta(false);
        y.insertaCarta(yc2);
        
        List<Carta> manoMaria = new ArrayList<>();
        manoMaria = y.getMano();

        t.insertaJugador(manoMaria, false);

        assertEquals(manoMaria, t.cartasJugador(2));
        assertEquals(false, t.eliminadoJugador(2));
        
        int jugadores = t.numJugadores();
        assertEquals(3, jugadores);

    }

}


