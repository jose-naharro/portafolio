
package ejercicio5;

import static ejercicio5.IteradorFilasOcolumnas.*;
import java.util.Iterator;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author natalia.iglesiast
 * @author jose.naharro
 */
public class MatrizTest {

    public MatrizTest() {
    }

    /**
     * Test of setIterador method, of class Matriz.
     */
    @Test
    public void testSetGetIterador() {
        Matriz m = new Matriz(3, 3);
        m.setIterador(FILAS);
        assertEquals(FILAS, m.getIterador());

    }

    /**
     * Test of getElementos method, of class Matriz.
     */
    @Test
    public void testGetElementos() {
        Matriz m = new Matriz(3, 3);
        int[][] expResult = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        int[][] result = m.getElementos();
        assertArrayEquals(expResult, result);

        int[][] matrizPasar = {{2, 4}, {6, 6}, {8, 9}};
        Matriz matriz2 = new Matriz(matrizPasar);
        assertArrayEquals(matrizPasar, matriz2.getElementos());
    }

    /**
     * Test of getFilas method, of class Matriz.
     */
    @Test
    public void testGetFilas() {
        Matriz m = new Matriz(3, 2);
        int result = m.getFilas();
        assertEquals(3, result);
    }

    /**
     * Test of getColumnas method, of class Matriz.
     */
    @Test
    public void testGetColumnas() {
        Matriz m = new Matriz(3, 2);
        int result = m.getColumnas();
        assertEquals(2, result);
    }

    /**
     * Test of getPosicionMatriz method, of class Matriz.
     */
    @Test
    public void testGetPosicionMatriz() {

        int[][] matrizPasar = {{2, 4}, {6, 6}, {8, 9}};
        Matriz m = new Matriz(matrizPasar);
        assertEquals(2, m.getPosicionMatriz(0, 0));
        assertEquals(4, m.getPosicionMatriz(0, 1));
    }

    /**
     * Test of setPosicionMatriz method, of class Matriz.
     */
    @Test
    public void testSetPosicionMatriz() {
        int[][] matrizPasar = {{2, 4}, {6, 6}, {8, 9}};
        Matriz m = new Matriz(matrizPasar);

        assertEquals(6, m.getPosicionMatriz(1, 1));

        int dato = 5;
        m.setPosicionMatriz(dato, 1, 1);
        assertEquals(5, m.getPosicionMatriz(1, 1));
    }

    /**
     * Test of getFilaMatriz method, of class Matriz.
     */
    @Test
    public void testGetFilaMatriz() {
        int[][] matrizPasar = {{2, 4}, {6, 6}, {8, 9}};
        Matriz m = new Matriz(matrizPasar);
        int fila = 1;
        int[] expResult = {6, 6};
        int[] result = m.getFilaMatriz(fila);
        assertArrayEquals(expResult, result);

    }

    /**
     * Test of getColumnaMatriz method, of class Matriz.
     */
    @Test
    public void testGetColumnaMatriz() {
        int[][] matrizPasar = {{2, 4}, {6, 6}, {8, 9}};
        Matriz m = new Matriz(matrizPasar);
        int col = 1;
        int[] expResult = {4, 6, 9};
        int[] result = m.getColumnaMatriz(col);
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of iterator method, of class Matriz.
     */
    @Test
    public void testIterator() {
        int[][] matrizPasar = {{2, 4}, {6, 6}, {8, 9}};
        Matriz m = new Matriz(matrizPasar);

        Iterator expResult = new IteradorPorFilas(m);
        Iterator it = m.iterator();
        assertEquals(true, it.hasNext());
    }

    @Test
    public void testIterator2() {
        int[][] matrizPasar = {{2, 4}, {6, 6}, {8, 9}};
        Matriz m = new Matriz(matrizPasar);
        m.setIterador(COLUMNAS);
        
        Iterator expResult = new IteradorPorColumnas(m);
        Iterator it = m.iterator();
        
        assertEquals(true, it.hasNext());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCrearMatrizMal1() {
        Matriz m = new Matriz(-1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCrearMatrizMal2() {
        Matriz m = new Matriz(6, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCrearMatrizMal3() {
        Matriz m = new Matriz(0, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCrearMatrizMal4() {
        Matriz m = new Matriz(6, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetPosicionMatriz1() {
        Matriz m = new Matriz(3, 3);
        m.getPosicionMatriz(3, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetPosicionMatriz2() {
        Matriz m = new Matriz(3, 3);
        m.getPosicionMatriz(-1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetPosicionMatriz3() {
        Matriz m = new Matriz(3, 3);
        m.getPosicionMatriz(0, 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetPosicionMatriz4() {
        Matriz m = new Matriz(3, 3);
        m.getPosicionMatriz(0, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetPosicionMatriz1() {
        Matriz m = new Matriz(3, 3);
        int dato = 5;
        m.setPosicionMatriz(dato, 3, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetPosicionMatriz2() {
        Matriz m = new Matriz(3, 3);
        int dato = 5;
        m.setPosicionMatriz(dato, -1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetPosicionMatriz3() {
        Matriz m = new Matriz(3, 3);
        int dato = 5;
        m.setPosicionMatriz(dato, 0, 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetPosicionMatriz4() {
        Matriz m = new Matriz(3, 3);
        int dato = 5;
        m.setPosicionMatriz(dato, 0, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetFilaMatriz1() {
        Matriz m = new Matriz(3, 3);
        m.getFilaMatriz(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetFilaMatriz2() {
        Matriz m = new Matriz(3, 3);
        m.getFilaMatriz(4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetColumnaMatriz1() {
        Matriz m = new Matriz(3, 3);
        m.getColumnaMatriz(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetColumnaMatriz2() {
        Matriz m = new Matriz(3, 3);
        m.getColumnaMatriz(4);
    }
}
