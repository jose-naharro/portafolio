package ejercicio1;

import org.junit.Test;
import static org.junit.Assert.*;

public class EsferaTest {

    public EsferaTest() {
    }

    @Test
    public void testGet() {
        Esfera e1 = new Esfera("Esfera", 1, 2, 2, -2.1);

        String resultN = e1.getNombre();
        assertEquals("Esfera", resultN);

        int resultX = e1.getX();
        assertEquals(1, resultX);

        int resultY = e1.getY();
        assertEquals(2, resultY);

        int resultZ = e1.getZ();
        assertEquals(2, resultZ);

        double resultR = e1.getRadio();
        assertEquals(2.1, resultR, 0.1);

        
        e1 = new Esfera("Esfera2", -2, -3, -5, 11.0);

        resultN = e1.getNombre();
        assertEquals("Esfera2", resultN);

        resultX = e1.getX();
        assertEquals(-2, resultX);

        resultY = e1.getY();
        assertEquals(-3, resultY);

        resultZ = e1.getZ();
        assertEquals(-5, resultZ);

        resultR = e1.getRadio();
        assertEquals(11.0, resultR, 0.1);

    }

    @Test
    public void testVolumen() {
        Esfera e1 = new Esfera("Esfera", 45, 78, 4, 8.1);

        double result = e1.volumen();
        assertEquals(2226, result, 0.1);

        e1 = new Esfera("Esfera", 34, -98, -5, 0.67);
        result = e1.volumen();
        assertEquals(1.26, result, 0.1);

    }

    @Test
    public void testArea() {

        Esfera e1 = new Esfera("Esfera", 5, 8, 4, 4.1);

        double result = e1.area();
        assertEquals(211.24, result, 0.1);

        e1 = new Esfera("Esfera", 45, -9, 4, -5.1);
        result = e1.area();
        assertEquals(326.9, result, 0.1);
    }
    
    
    @Test(expected = IllegalArgumentException.class)
    public void testEsperaNoValido() {
        Esfera e1 = new Esfera("Ecfera", 2,-1, -7, 0);
    }
}

