package ejercicio1;

import org.junit.Test;
import static org.junit.Assert.*;

public class RectanguloTest {

    public RectanguloTest() {
    }

    @Test
    public void testGet() {
        Rectangulo r1 = new Rectangulo("Rectangulo", 1, 2, 2.5, 6.3);

        String resultN = r1.getNombre();
        assertEquals("Rectangulo", resultN);

        int resultX = r1.getX();
        assertEquals(1, resultX);

        int resultY = r1.getY();
        assertEquals(2, resultY);

        double resultL = r1.getLado();
        assertEquals(2.5, resultL, 0.1);

        double resultA = r1.getAltura();
        assertEquals(6.3, resultA, 0.1);

        int resultNum = r1.getNumSegmentos();
        assertEquals(4, resultNum);

        r1 = new Rectangulo("Rectangulo2", -2, -3, -3.5, -6.0);

        resultN = r1.getNombre();
        assertEquals("Rectangulo2", resultN);

        resultX = r1.getX();
        assertEquals(-2, resultX);

        resultY = r1.getY();
        assertEquals(-3, resultY);

        resultL = r1.getLado();
        assertEquals(3.5, resultL, 0.1);

        resultA = r1.getAltura();
        assertEquals(6.0, resultA, 0.1);

        resultNum = r1.getNumSegmentos();
        assertEquals(4, resultNum);

    }

    @Test
    public void testPerimetro() {
        Rectangulo r1 = new Rectangulo("Rectangulo", 45, 78, 4.0, 6.0);

        double result = r1.perimetro();
        assertEquals(20.0, result, 0.1);

        r1 = new Rectangulo("Rectangulo", 34, -98, -5.0, 7.1);
        result = r1.perimetro();
        assertEquals(24.2, result, 0.1);

    }

    @Test
    public void testArea() {

        Rectangulo r1 = new Rectangulo("Rectangulo", 45, -7, 4.0, 6.0);

        double result = r1.area();
        assertEquals(24.0, result, 0.1);

        r1 = new Rectangulo("Rectangulo", 23, 7, -6.0, -10.0);
        result = r1.area();
        assertEquals(60.0, result, 0.1);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testRectanguloNoValido() {
        Rectangulo r1 = new Rectangulo("Rectangulo", 2, -1, -7, 0);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testRectanguloNoValido2() {
        Rectangulo r1 = new Rectangulo("Rectangulo", 2, -1, 0, 0);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testRectanguloNoValido3() {
        Rectangulo r1 = new Rectangulo("Rectangulo", 2, -1, 0, 3);
    }

}
