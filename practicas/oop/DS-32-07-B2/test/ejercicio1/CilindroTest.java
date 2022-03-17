package ejercicio1;

import org.junit.Test;
import static org.junit.Assert.*;

public class CilindroTest {

    public CilindroTest() {
    }

    @Test
    public void testGet() {
        Cilindro c1 = new Cilindro("Cilindro", 1, 2, 2, 1.3, -2.1);

        String resultN = c1.getNombre();
        assertEquals("Cilindro", resultN);

        int resultX = c1.getX();
        assertEquals(1, resultX);

        int resultY = c1.getY();
        assertEquals(2, resultY);

        int resultZ = c1.getZ();
        assertEquals(2, resultZ);

        double resultR = c1.getRadio();
        assertEquals(1.3, resultR, 0.1);

        double resultA = c1.getAltura();
        assertEquals(2.1, resultA, 0.1);

        c1 = new Cilindro("Cilindro2", -2, -3, -5, -6.0, 1.0);

        resultN = c1.getNombre();
        assertEquals("Cilindro2", resultN);

        resultX = c1.getX();
        assertEquals(-2, resultX);

        resultY = c1.getY();
        assertEquals(-3, resultY);

        resultZ = c1.getZ();
        assertEquals(-5, resultZ);

        resultR = c1.getRadio();
        assertEquals(6.0, resultR, 0.1);

        resultA = c1.getAltura();
        assertEquals(1.0, resultA, 0.1);

    }

    @Test
    public void testVolumen() {
        Cilindro c1 = new Cilindro("Cilindro", 45, 78, 4, 2.0, 8.1);

        double result = c1.volumen();
        assertEquals(101.78, result, 0.1);

        c1 = new Cilindro("Cilindro", 34, -98, -5, 5.3, 10.0);
        result = c1.volumen();
        assertEquals(882.47, result, 0.1);

    }

    @Test
    public void testArea() {

        Cilindro c1 = new Cilindro("Cilindro", 45, -7, 4, 6.0, 19.8);

        double result = c1.area();
        assertEquals(972.63, result, 0.1);

        c1 = new Cilindro("Cilindro", 23, 7, -6, -10.0, -80);
        result = c1.area();
        assertEquals(5654.8, result, 0.1);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCilindroNoValido1() {
        
        Cilindro c1 = new Cilindro("Cilindro", 45, -7, 4, 6.0, 0);
    }
 
     @Test(expected = IllegalArgumentException.class)
    public void testCilindroNoValido2() {
        
        Cilindro c1 = new Cilindro("Cilindro", 45, -7, 4, 0, 0);
    }
   
     @Test(expected = IllegalArgumentException.class)
    public void testCilindroNoValido3() {
        
        Cilindro c1 = new Cilindro("Cilindro", 45, -7, 4, 0, -4);
    }
    
}
