package ejercicio1;

import org.junit.Test;
import static org.junit.Assert.*;

public class CirculoTest {

    public CirculoTest() {
    }

    @Test
    public void testGet() {
    
        Circulo c1 = new Circulo("Circulo", 3,3, 2);

        String resultN = c1.getNombre();
        assertEquals("Circulo", resultN);

        int resultX = c1.getX();
        assertEquals(3, resultX);

        int resultY = c1.getY();
        assertEquals(3, resultY);
        
        double resultL = c1.getRadio();
        assertEquals(2, resultL, 0.1);

        c1 = new Circulo("Circulo2", 3,4, -3.5);

        resultN = c1.getNombre();
        assertEquals("Circulo2", resultN);

        resultX = c1.getX();
        assertEquals(3, resultX);

        resultY = c1.getY();
        assertEquals(4, resultY);

        resultL = c1.getRadio();
        assertEquals(3.5, resultL, 0.1);

    }

    @Test
    public void testPerimetro() {
   
        Circulo c1 = new Circulo ("Circulo", 31,0, 4.0);

        double result = c1.perimetro();
        assertEquals(25.13, result, 0.1);

        c1 = new Circulo ("Circulo", 12,12, -5.3);
        result = c1.perimetro();
        assertEquals(33.3, result, 0.1);

    }

    @Test
    public void testArea() {
        Circulo c1 = new Circulo("Circulo", 1,1, 4.0);

        double result = c1.area();
        assertEquals(50.26, result, 0.1);

        c1 = new Circulo("Circulo", 0,0, -3.2);
        result = c1.area();
        assertEquals(32.16, result, 0.1);

    }
    
     @Test(expected = IllegalArgumentException.class)
    public void testCirculoNoValido2() {
        
        Circulo c1 = new Circulo("Circulo", 1,1, 0);
    }

}
