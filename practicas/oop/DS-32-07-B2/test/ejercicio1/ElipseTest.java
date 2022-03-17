package ejercicio1;

import org.junit.Test;
import static org.junit.Assert.*;

public class ElipseTest {

    public ElipseTest() {
    }

    @Test
    public void testGet() {
    
        Elipse e1 = new Elipse("Elipse", 3, 3, 2.1, 1.2);

        String resultN = e1.getNombre();
        assertEquals("Elipse", resultN);

        int resultX = e1.getX();
        assertEquals(3, resultX);

        int resultY = e1.getY();
        assertEquals(3, resultY);
        
        double resultR = e1.getRadio();
        assertEquals(2.1, resultR, 0.1);
        
        double resultR2 = e1.getRadio2();
        assertEquals(1.2, resultR2, 0.1);

        e1 = new Elipse("Elipse2", 3,4, -3.5, -2);

        resultN = e1.getNombre();
        assertEquals("Elipse2", resultN);

        resultX = e1.getX();
        assertEquals(3, resultX);

        resultY = e1.getY();
        assertEquals(4, resultY);

        resultR = e1.getRadio();
        assertEquals(3.5, resultR, 0.1);
        
        resultR2 = e1.getRadio2();
        assertEquals(2, resultR2, 0.1);

    }

    @Test
    public void testPerimetro() {
   
        Elipse e1 = new Elipse ("Elipse", 31,0, 4.0, -2.0);

        double result = e1.perimetro();
        assertEquals(28.0, result, 0.1);

        e1 = new Elipse ("Elipse", 12,12, -5.3, 1);
        result = e1.perimetro();
        assertEquals(33.8, result, 0.1);

    }

    @Test
    public void testArea() {
        Elipse e1 = new Elipse("Eclipse", 1,1, 4.0, 8);

        double result = e1.area();
        assertEquals(100.5, result, 0.1);

        e1 = new Elipse("Eclipse", 0,0, -3.2, 1);
        result = e1.area();
        assertEquals(10.05, result, 0.1);

    }
    
     @Test(expected = IllegalArgumentException.class)
    public void testElipseNoValido1() {
        Elipse e1 = new Elipse("Eclipse", 12,1, 0, 8);
    }

     @Test(expected = IllegalArgumentException.class)
    public void testElipseNoValido2() {
        Elipse e1 = new Elipse("Eclipse", 12,1, 0, 0);
    }
    
     @Test(expected = IllegalArgumentException.class)
    public void testElipseNoValido3() {
        Elipse e1 = new Elipse("Eclipse", 12,1, -7, 0);
    }
}
