package ejercicio1;

import org.junit.Test;
import static org.junit.Assert.*;

public class TrianguloEquiTest {

    public TrianguloEquiTest() {
    }

    @Test
    public void testGet() {

        TrianguloEqui t1 = new TrianguloEqui("Triangulo", 3, 3, 2.5);

        String resultN = t1.getNombre();
        assertEquals("Triangulo", resultN);

        int resultX = t1.getX();
        assertEquals(3, resultX);

        int resultY = t1.getY();
        assertEquals(3, resultY);

        double resultL = t1.getLado();
        assertEquals(2.5, resultL, 0.1);

        int resultNum = t1.getNumSegmentos();
        assertEquals(3, resultNum);

        t1 = new TrianguloEqui("Triangulo2", 3, 4, -3.5);

        resultN = t1.getNombre();
        assertEquals("Triangulo2", resultN);

        resultX = t1.getX();
        assertEquals(3, resultX);

        resultY = t1.getY();
        assertEquals(4, resultY);

        resultL = t1.getLado();
        assertEquals(3.5, resultL, 0.1);

        resultNum = t1.getNumSegmentos();
        assertEquals(3, resultNum);

    }

    @Test
    public void testPerimetro() {

        TrianguloEqui t1 = new TrianguloEqui("Triangulo", 31, 0, 4.0);

        double result = t1.perimetro();
        assertEquals(12.0, result, 0.1);

        t1 = new TrianguloEqui("Triangulo", 12, 12, -5.3);
        result = t1.perimetro();
        assertEquals(15.9, result, 0.1);

    }

    @Test
    public void testApotema() {
        TrianguloEqui t1 = new TrianguloEqui("Triangulo", 34, 56, 6.0);

        double result = t1.apotema();
        assertEquals(6.7, result, 0.1);

        t1 = new TrianguloEqui("Triangulo", 0, 65, -3.2);
        result = t1.apotema();
        assertEquals(3.58, result, 0.1);

    }

    @Test
    public void testArea() {
        TrianguloEqui t1 = new TrianguloEqui("Triangulo", 1, 1, 4.0);

        double result = t1.area();
        assertEquals(8.944, result, 0.1);

        t1 = new TrianguloEqui("Triangulo", 0, 0, -3.2);
        result = t1.area();
        assertEquals(5.72, result, 0.1);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testTrianguloNoValido() {
        TrianguloEqui t1 = new TrianguloEqui("Triangulo", 2, -1, 0);
    }

}
