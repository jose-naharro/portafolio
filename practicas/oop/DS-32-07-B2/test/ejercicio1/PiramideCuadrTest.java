package ejercicio1;

import org.junit.Test;
import static org.junit.Assert.*;

public class PiramideCuadrTest {

    public PiramideCuadrTest() {
    }

    @Test
    public void testGet() {
        PiramideCuadr p1 = new PiramideCuadr("Piramide", 1, 2, 2, 6.3, -2.1);

        String resultN = p1.getNombre();
        assertEquals("Piramide", resultN);

        int resultX = p1.getX();
        assertEquals(1, resultX);

        int resultY = p1.getY();
        assertEquals(2, resultY);

        int resultZ = p1.getZ();
        assertEquals(2, resultZ);

        double resultL = p1.getLado();
        assertEquals(6.3, resultL, 0.1);

        double resultA = p1.getAltura();
        assertEquals(2.1, resultA, 0.1);

        int resultNum = p1.getNumCaras();
        assertEquals(5, resultNum);

        p1 = new PiramideCuadr("Piramide2", -2, -3, -5, -6.0, 1.0);

        resultN = p1.getNombre();
        assertEquals("Piramide2", resultN);

        resultX = p1.getX();
        assertEquals(-2, resultX);

        resultY = p1.getY();
        assertEquals(-3, resultY);

        resultZ = p1.getZ();
        assertEquals(-5, resultZ);

        resultL = p1.getLado();
        assertEquals(6.0, resultL, 0.1);

        resultA = p1.getAltura();
        assertEquals(1.0, resultA, 0.1);

        resultNum = p1.getNumCaras();
        assertEquals(5, resultNum);

    }

    @Test
    public void testVolumen() {
        PiramideCuadr p1 = new PiramideCuadr("Piramide", 45, 78, 4, 2.0, 8.1);

        double result = p1.volumen();
        assertEquals(10.8, result, 0.1);

        p1 = new PiramideCuadr("Piramide", 34, -98, -5, 5.3, 10.0);
        result = p1.volumen();
        assertEquals(93.6, result, 0.1);

    }

    @Test
    public void testArea() {

        PiramideCuadr p1 = new PiramideCuadr("Piramide", 45, -7, 4, 6.0, 19.8);

        double result = p1.area();
        assertEquals(276.3, result, 0.1);

        p1 = new PiramideCuadr("Piramide", 23, 7, -6, -10.0, -80);
        result = p1.area();
        assertEquals(1703.12, result, 0.1);

    }

    @Test
    public void testAreaCara() {

        PiramideCuadr p1 = new PiramideCuadr("Piramide", 45, -7, 4, 6.0, -45.2);

        double result = p1.areaCara();
        assertEquals(135.89, result, 0.1);

        p1 = new PiramideCuadr("Piramide", 23, 7, -6, -10.0, 20.0);
        result = p1.areaCara();
        assertEquals(103.0, result, 0.1);
    }

    @Test
    public void testAreaBase() {

        PiramideCuadr p1 = new PiramideCuadr("Piramide", 45, -7, 4, 6.0, -45.2);

        double result = p1.areaBase();
        assertEquals(36.0, result, 0.1);

        p1 = new PiramideCuadr("Piramide", 23, 7, -6, -10.0, 20.0);
        result = p1.areaBase();
        assertEquals(100.0, result, 0.1);
    }

    @Test
    public void testApotema() {

        PiramideCuadr p1 = new PiramideCuadr("Piramide", 45, -7, 4, 3.0, 10.2);

        double resultAp = p1.apotema();
        assertEquals(10.3, resultAp, 0.1);

        p1 = new PiramideCuadr("Piramide", 23, 7, -6, 5.8, 20.0);
        resultAp = p1.apotema();
        assertEquals(20.20, resultAp, 0.1);
    }
    
    
    @Test(expected = IllegalArgumentException.class)
    public void testPiramideNoValido() {
        PiramideCuadr p1 = new PiramideCuadr("Piramide", 45, -7, 4, 3.0, 0);
    }
    
     @Test(expected = IllegalArgumentException.class)
    public void testPiramideNoValido2() {
        PiramideCuadr p1 = new PiramideCuadr("Piramide", 45, -7, 4, 0, 9.3);
    }
    
     @Test(expected = IllegalArgumentException.class)
    public void testPiramideNoValido3() {
        PiramideCuadr p1 = new PiramideCuadr("Piramide", 45, -7, 4, 3.-6.89, 0);
    }
    
}
