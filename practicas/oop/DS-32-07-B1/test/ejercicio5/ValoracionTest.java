package ejercicio5;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author natalia.iglesiast
 * @author jose.naharro
 */
public class ValoracionTest {

    public ValoracionTest() {
    }

    @Test
    public void testEsMayor() throws ValoracionNoValidaException {
        try {
            assertEquals(true, Valoracion.MALA.esMayor(Valoracion.PESIMA));
            assertEquals(true, Valoracion.MEDIOCRE.esMayor(Valoracion.MALA));
            assertEquals(true, Valoracion.BUENA.esMayor(Valoracion.MEDIOCRE));
            assertEquals(true, Valoracion.EXCELENTE.esMayor(Valoracion.BUENA));
            assertEquals(true, Valoracion.OBRA_MAESTRA.esMayor(Valoracion.EXCELENTE));
            assertEquals(true, Valoracion.PESIMA.esMayor(Valoracion.NO_VALORADA));

            assertEquals(false, Valoracion.MALA.esMayor(Valoracion.MALA));
            assertEquals(false, Valoracion.PESIMA.esMayor(Valoracion.MALA));

        } catch (ValoracionNoValidaException ex) {
            System.out.println("Problem: " + ex.getMessage());
        }
    }

    @Test
    public void testEsMayorExc1() throws ValoracionNoValidaException {
        try {
            assertEquals(true, Valoracion.PESIMA.esMayor(Valoracion.NO_VALORADA));
        } catch (ValoracionNoValidaException ex) {
            System.out.println("Problem: " + ex.getMessage());
        }
    }

    @Test
    public void testEsMayorExc2() throws ValoracionNoValidaException {
        try {
            assertEquals(true, Valoracion.NO_VALORADA.esMayor(Valoracion.PESIMA));
        } catch (ValoracionNoValidaException ex) {
            System.out.println("Problem: " + ex.getMessage());
        }
    }

    @Test
    public void testEsMayorExc3() throws ValoracionNoValidaException {
        try {
            assertEquals(true, Valoracion.NO_VALORADA.esMayor(Valoracion.NO_VALORADA));
        } catch (ValoracionNoValidaException ex) {
            System.out.println("Problem: " + ex.getMessage());
        }
    }

    @Test
    public void testEsMayorExc4() throws ValoracionNoValidaException {
        try {
            assertEquals(false, Valoracion.PESIMA.esMayor(Valoracion.EXCELENTE));
        } catch (ValoracionNoValidaException ex) {
            System.out.println("Problem: " + ex.getMessage());
        }
    }
}
