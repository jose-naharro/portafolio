package ejercicio1;

import org.junit.Test;
import static org.junit.Assert.*;

public class RectanguloTest {

    /**
     *
     * @author natalia.iglesiast
     * @author jose.naharro
     */
    @Test
    public void testSetBase() {
        Rectangulo r1 = new Rectangulo();
        r1.setBase(5);
        int result = r1.getBase();
        assertEquals(5, result);

        r1.setBase(-2);
        result = r1.getBase();
        assertEquals(2, result);

    }

    @Test
    public void testSetAltura() {
        Rectangulo r1 = new Rectangulo();
        r1.setAltura(2);
        int result = r1.getAltura();
        assertEquals(2, result);

        r1.setAltura(-4);
        result = r1.getAltura();
        assertEquals(4, result);

    }

    @Test
    public void testEsCuadrado() {
        Rectangulo r1 = new Rectangulo(8, 8);
        assertTrue(r1.esCuadrado());

        r1 = new Rectangulo(7, 3);
        assertFalse(r1.esCuadrado());
    }

    @Test
    public void testArea() {
        Rectangulo r1 = new Rectangulo(-9, 4);
        int expResult = 36;
        int result = r1.area();
        assertEquals(expResult, result);

        r1 = new Rectangulo(9, -4);
        expResult = 36;
        result = r1.area();
        assertEquals(expResult, result);

        r1 = new Rectangulo(2, 3);
        expResult = 6;
        result = r1.area();
        assertEquals(expResult, result);

        r1 = new Rectangulo(-3, -3);
        expResult = 9;
        result = r1.area();
        assertEquals(expResult, result);

        r1 = new Rectangulo();
        expResult = 2;
        result = r1.area();
        assertEquals(expResult, result);
    }

    @Test
    public void testPerimetro() {
        Rectangulo r1 = new Rectangulo(5, -3);
        int expResult = 16;
        int result = r1.perimetro();
        assertEquals(expResult, result);

        r1 = new Rectangulo(-2, -3);
        expResult = 10;
        result = r1.perimetro();
        assertEquals(expResult, result);

        r1 = new Rectangulo(-4, 3);
        expResult = 14;
        result = r1.perimetro();
        assertEquals(expResult, result);

        r1 = new Rectangulo(1, 10);
        expResult = 22;
        result = r1.perimetro();
        assertEquals(expResult, result);

        r1 = new Rectangulo();
        expResult = 6;
        result = r1.perimetro();
        assertEquals(expResult, result);
    }

    @Test
    public void testGira() {
        Rectangulo r1 = new Rectangulo(4, 9);
        r1.gira();
        assertEquals(9, r1.getBase());
        assertEquals(4, r1.getAltura());

        r1 = new Rectangulo(-5, 3);
        r1.gira();
        assertEquals(3, r1.getBase());
        assertEquals(5, r1.getAltura());

        r1 = new Rectangulo(5, 5);
        r1.gira();
        assertEquals(5, r1.getBase());
        assertEquals(5, r1.getAltura());
    }

    @Test
    public void testPonHorizontal() {
        Rectangulo r1 = new Rectangulo(3, -1);
        r1.ponHorizontal();
        assertEquals(3, r1.getBase());
        assertEquals(1, r1.getAltura());

        r1 = new Rectangulo(3, 4);
        r1.ponHorizontal();
        assertEquals(4, r1.getBase());
        assertEquals(3, r1.getAltura());

        r1 = new Rectangulo(5, 5);
        r1.ponHorizontal();
        assertEquals(5, r1.getBase());
        assertEquals(5, r1.getAltura());

    }

    @Test
    public void testPonVertical() {
        Rectangulo r1 = new Rectangulo(9, 2);
        r1.ponVertical();
        assertEquals(2, r1.getBase());
        assertEquals(9, r1.getAltura());

        r1 = new Rectangulo(-7, 9);
        r1.ponVertical();
        assertEquals(7, r1.getBase());
        assertEquals(9, r1.getAltura());

        r1 = new Rectangulo(-7, 7);
        r1.ponVertical();
        assertEquals(7, r1.getBase());
        assertEquals(7, r1.getAltura());
    }

    @Test
    public void testEquals() {
        Object obj = new Rectangulo(7, -4);
        Rectangulo r1 = new Rectangulo(4, -7);
        boolean expResult = true;
        boolean result = r1.equals(obj);
        assertEquals(expResult, result);

        r1 = new Rectangulo(4, 1);
        expResult = false;
        result = r1.equals(obj);
        assertEquals(expResult, result);

        r1 = new Rectangulo(1, 1);
        expResult = false;
        result = r1.equals(obj);
        assertEquals(expResult, result);

        r1 = new Rectangulo(3, -7);
        expResult = false;
        result = r1.equals(obj);
        assertEquals(expResult, result);

        obj = new Rectangulo(4, -4);
        r1 = new Rectangulo(4, 4);
        expResult = true;
        result = r1.equals(obj);
        assertEquals(expResult, result);

        obj = new Rectangulo(7, -4);
        r1 = new Rectangulo(7, 4);
        expResult = true;
        result = r1.equals(obj);
        assertEquals(expResult, result);

        Rectangulo r2 = new Rectangulo(7, 4);
        Rectangulo r3 = new Rectangulo(6, 4);

        assertEquals(true, obj.equals(r1));
        assertEquals(true, r1.equals(r1));// Reflexividad
        assertEquals(true, r1.equals(obj));// Simetria
        assertEquals(true, obj.equals(r2));// Transitividad
        assertEquals(true, r1.equals(r2));//Transitividad
        assertEquals(obj.equals(r1), obj.equals(r1));// Consistencia
        assertEquals(false, r1.equals(null));// Uso de nulos, no se porque no va...

        assertEquals(false, obj.equals(r3));
        String miString = new String();
        assertEquals(false, r1.equals(miString));// Comparacion de clases  

    }

    @Test
    public void testHashCode() {
        Rectangulo r1 = new Rectangulo(5, -3);
        int expResult = 23;
        int result = r1.hashCode();
        assertEquals(expResult, result);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testRectanguloNoValido1() {

        Rectangulo r1 = new Rectangulo(0, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRectanguloNoValido2() {

        Rectangulo r1 = new Rectangulo(0, 0);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testRectanguloNoValido3() {

        Rectangulo r1 = new Rectangulo(2, 0);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testRectanguloNoValido4() {
        Rectangulo r1 = new Rectangulo();
        r1.setBase(8);
        r1.setAltura(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRectanguloNoValido5() {
        Rectangulo r1 = new Rectangulo();
        r1.setBase(0);
        r1.setAltura(-3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRectanguloNoValido6() {
        Rectangulo r1 = new Rectangulo();
        r1.setBase(0);
        r1.setAltura(9);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRectanguloNoValido7() {
        Rectangulo r1 = new Rectangulo();
        r1.setBase(0);
        r1.setAltura(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRectanguloNoValido9() {
        Rectangulo r1 = new Rectangulo();
        r1.setBase(-4);
        r1.setAltura(0);
    }

}
