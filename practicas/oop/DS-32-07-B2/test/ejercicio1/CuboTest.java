package ejercicio1;

import org.junit.Test;
import static org.junit.Assert.*;

public class CuboTest {

    public CuboTest() {
    }

    @Test
    public void testGet() {
        Cubo c1 = new Cubo("Cubo", 1, 2, 2, 6.3);

        String resultN = c1.getNombre();
        assertEquals("Cubo", resultN);

        int resultX = c1.getX();
        assertEquals(1, resultX);

        int resultY = c1.getY();
        assertEquals(2, resultY);

        int resultZ = c1.getZ();
        assertEquals(2, resultZ);

        double resultL = c1.getLado();
        assertEquals(6.3, resultL, 0.1);;

        int resultNum = c1.getNumCaras();
        assertEquals(6, resultNum);

        c1 = new Cubo("Cubo2", -2, -3, -5, -6.0);

        resultN = c1.getNombre();
        assertEquals("Cubo2", resultN);

        resultX = c1.getX();
        assertEquals(-2, resultX);

        resultY = c1.getY();
        assertEquals(-3, resultY);

        resultZ = c1.getZ();
        assertEquals(-5, resultZ);

        resultL = c1.getLado();
        assertEquals(6.0, resultL, 0.1);

        resultNum = c1.getNumCaras();
        assertEquals(6, resultNum);

    }

    @Test
    public void testVolumen() {
        Cubo c1 = new Cubo("Cubo", 45, 78, 4, 2.0);

        double result = c1.volumen();
        assertEquals(8.0, result, 0.1);

        c1 = new Cubo("Cubo", 34, -98, -5, 5.3);
        result = c1.volumen();
        assertEquals(148.87, result, 0.1);

    }

    @Test
    public void testArea() {

        Cubo c1 = new Cubo("Cubo", 45, -7, 4, 6.0);

        double result = c1.area();
        assertEquals(216.0, result, 0.1);

        c1 = new Cubo("Cubo", 23, 7, -6, -10.0);
        result = c1.area();
        assertEquals(600.0, result, 0.1);

    }

    @Test
    public void testAreaCara() {

        Cubo c1 = new Cubo("Cubo", 45, -7, 4, 6.0);

        double result = c1.areaCara();
        assertEquals(36.0, result, 0.1);

        c1 = new Cubo("Cubo", 23, 7, -6, -10.0);
        result = c1.areaCara();
        assertEquals(100.0, result, 0.1);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testCuboNoValido() {

        Cubo c1 = new Cubo("Cubo", 5, -1, 4, 0);
    }

}
