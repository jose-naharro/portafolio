package ejercicio3;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author natalia.iglesiast
 */
public class JugadorTest {

    public JugadorTest() {
    }

    @Test
    public void testAccion() {
        Tapete t = new Tapete();

        Comportamiento comportamientoJuan = new CompTonto();
        Jugador x = new Jugador("Juan", comportamientoJuan);
        x.inicializaMano();

        Carta cx1 = new Carta(NumeroCarta.DOS, PaloCarta.BASTOS);
        cx1.setDescubierta(false);
        x.insertaCarta(cx1);

        Accion ExpResult = Accion.DESCUBIERTA;
        Accion result = x.accion(t);
        assertEquals(ExpResult, result);

        Comportamiento comportamientoMaria = new Comp1();
        Jugador m = new Jugador("Maria", comportamientoMaria);
        m.inicializaMano();

        Carta cm1 = new Carta(NumeroCarta.DOS, PaloCarta.BASTOS);
        cm1.setDescubierta(false);
        m.insertaCarta(cm1);

        ExpResult = Accion.CUBIERTA;
        result = m.accion(t);
        assertEquals(ExpResult, result);

        Comportamiento comportamientoLucas = new CompSeguro();
        Jugador j = new Jugador("Lucas", comportamientoLucas);
        j.inicializaMano();

        ExpResult = Accion.CUBIERTA;
        result = j.accion(t);
        assertEquals(ExpResult, result);

        Carta c1 = new Carta(NumeroCarta.DOS, PaloCarta.BASTOS);
        Carta c2 = new Carta(NumeroCarta.SOTA, PaloCarta.ESPADAS);
        Carta c3 = new Carta(NumeroCarta.AS, PaloCarta.COPAS);
        Carta c4 = new Carta(NumeroCarta.DOS, PaloCarta.OROS);

        c1.setDescubierta(false);
        j.insertaCarta(c1);
        c1.setDescubierta(true);
        j.insertaCarta(c2);
        c1.setDescubierta(true);
        j.insertaCarta(c3);
        c1.setDescubierta(false);
        j.insertaCarta(c4);

        ExpResult = Accion.DESCUBIERTA;
        result = j.accion(t);
        assertEquals(ExpResult, result);

        Carta c5 = new Carta(NumeroCarta.DOS, PaloCarta.BASTOS);
        c5.setDescubierta(false);
        j.insertaCarta(c5);

        ExpResult = Accion.PASAR;
        result = j.accion(t);
        assertEquals(ExpResult, result);

    }

    @Test
    public void testEvaluarMano() {
        Comportamiento comportamientoJuan = new CompSeguro();
        Jugador j = new Jugador("Lucas", comportamientoJuan);
        Carta c1 = new Carta(NumeroCarta.DOS, PaloCarta.BASTOS);
        Carta c2 = new Carta(NumeroCarta.SOTA, PaloCarta.ESPADAS);
        Carta c3 = new Carta(NumeroCarta.AS, PaloCarta.COPAS);
        Carta c4 = new Carta(NumeroCarta.DOS, PaloCarta.ESPADAS);
        j.inicializaMano();

        c1.setDescubierta(false);
        j.insertaCarta(c1);
        c1.setDescubierta(true);
        j.insertaCarta(c2);
        c1.setDescubierta(true);
        j.insertaCarta(c3);
        c1.setDescubierta(false);
        j.insertaCarta(c4);

        int result = j.evaluarMano();
        assertEquals(55, result);
    }

    @Test
    public void testGetManoPublica() {
        Comportamiento comportamientoJuan = new CompSeguro();
        Jugador j = new Jugador("Lucas", comportamientoJuan);
        Carta c1 = new Carta(NumeroCarta.DOS, PaloCarta.BASTOS);
        Carta c2 = new Carta(NumeroCarta.SOTA, PaloCarta.ESPADAS);
        Carta c3 = new Carta(NumeroCarta.AS, PaloCarta.COPAS);
        Carta c4 = new Carta(NumeroCarta.DOS, PaloCarta.ESPADAS);
        j.inicializaMano();

        c1.setDescubierta(false);
        j.insertaCarta(c1);

        List<Carta> ArrayVacio = new ArrayList<>();
        List<Carta> result = j.getManoPublica();
        assertEquals(ArrayVacio, result);

        c2.setDescubierta(false);
        j.insertaCarta(c2);
        c3.setDescubierta(false);
        j.insertaCarta(c3);
        c4.setDescubierta(false);
        j.insertaCarta(c4);

        List<Carta> ArrayCartas = new ArrayList<>();
        ArrayCartas.add(c1);
        ArrayCartas.add(c2);
        ArrayCartas.add(c3);
        List<Carta> result2 = j.getManoPublica();
        assertEquals(ArrayCartas, result2);
    }

    @Test
    public void testGetManoPublica2() {
        Carta c1 = new Carta(NumeroCarta.DOS, PaloCarta.BASTOS);
        Carta c2 = new Carta(NumeroCarta.SOTA, PaloCarta.ESPADAS);
        Carta c3 = new Carta(NumeroCarta.AS, PaloCarta.COPAS);
        Carta c4 = new Carta(NumeroCarta.DOS, PaloCarta.ESPADAS);

        Comportamiento comportamientoMaria = new CompSeguro();
        Jugador x = new Jugador("Maria", comportamientoMaria);

        x.inicializaMano();

        c1.setDescubierta(false);
        x.insertaCarta(c1);
        c2.setDescubierta(true);
        x.insertaCarta(c2);
        c3.setDescubierta(false);
        x.insertaCarta(c3);
        c4.setDescubierta(true);
        x.insertaCarta(c4);

        List<Carta> ArrayCartas2 = new ArrayList<>();
        ArrayCartas2.add(c1);
        ArrayCartas2.add(c2);
        ArrayCartas2.add(c4);
        List<Carta> result3 = x.getManoPublica();
        assertEquals(ArrayCartas2, result3);

    }

    @Test
    public void testGetMano() {
        Comportamiento comportamientoJuan = new CompSeguro();
        Jugador j = new Jugador("Lucas", comportamientoJuan);
        Carta c1 = new Carta(NumeroCarta.DOS, PaloCarta.BASTOS);
        Carta c2 = new Carta(NumeroCarta.CABALLO, PaloCarta.ESPADAS);
        Carta c3 = new Carta(NumeroCarta.AS, PaloCarta.COPAS);
        Carta c4 = new Carta(NumeroCarta.REY, PaloCarta.ESPADAS);
        j.inicializaMano();
        
        j.insertaCarta(c1);
        c1.setDescubierta(false);
        j.insertaCarta(c2);
        c2.setDescubierta(true);
        j.insertaCarta(c3);
        c3.setDescubierta(false);
        j.insertaCarta(c4);
        c4.setDescubierta(true);
        Carta result = j.getMano().get(0);
        assertEquals(c1, result);
        result = j.getMano().get(1);
        assertEquals(c2, result);
        result = j.getMano().get(2);
        assertEquals(c3, result);
        result = j.getMano().get(3);
        assertEquals(c4, result);

    }

    @Test
    public void testInicializaMano() {
        Comportamiento comportamientoJuan = new CompSeguro();
        Jugador j = new Jugador("Pepe", comportamientoJuan);
        Carta c = new Carta(NumeroCarta.SIETE, PaloCarta.COPAS);
        j.inicializaMano();
        assertEquals(true, j.getMano().isEmpty());
    }

    @Test
    public void testInsertaCarta() {
        Comportamiento comportamientoJuan = new CompSeguro();
        Jugador j = new Jugador("Maria", comportamientoJuan);
        Carta c = new Carta(NumeroCarta.DOS, PaloCarta.ESPADAS);
        j.inicializaMano();
        
        c.setDescubierta(false);
        j.insertaCarta(c);
        Carta result = j.getMano().get(0);

        assertEquals(false, result.getDescubierta());

        Carta c2 = new Carta(NumeroCarta.TRES, PaloCarta.BASTOS);
        c2.setDescubierta(true);
        j.insertaCarta(c2);

        Carta result2 = j.getMano().get(1);

        assertEquals(false, result.getDescubierta());
        assertEquals(true, result2.getDescubierta());

        Carta c3 = new Carta(NumeroCarta.CUATRO, PaloCarta.BASTOS);
        c3.setDescubierta(false);
        j.insertaCarta(c3);
        Carta result3 = j.getMano().get(2);

        Carta c4 = new Carta(NumeroCarta.CINCO, PaloCarta.BASTOS);
        c4.setDescubierta(true);
        j.insertaCarta(c4);
        Carta result4 = j.getMano().get(3);

        assertEquals(true, result.getDescubierta());
        assertEquals(true, result2.getDescubierta());
        assertEquals(false, result3.getDescubierta());
        assertEquals(true, result4.getDescubierta());

    }

    @Test
    public void testIsEliminado() {
        Comportamiento comportamientoJuan = new Comp1();
        Jugador j = new Jugador("Juan", comportamientoJuan);
        j.inicializaMano();

        Boolean result = j.isEliminado();
        assertEquals(false, result);

        j.eliminado();
        result = j.isEliminado();
        assertEquals(true, result);
    }

    @Test
    public void testToString() {
        Comportamiento comportamientoJuan = new CompTonto();
        Jugador j = new Jugador("Juan", comportamientoJuan);
        j.inicializaMano();

        String result = j.toString();
        assertEquals("Juan", result);
    }

}
