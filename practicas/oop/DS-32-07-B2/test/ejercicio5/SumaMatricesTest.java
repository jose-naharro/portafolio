
package ejercicio5;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author natalia.iglesiast
 * @author jose.naharro
 */
public class SumaMatricesTest {
    
    public SumaMatricesTest() {
    }

    /**
     * Test of sumaMatrices method, of class SumaMatrices.
     */
    @Test
    public void testSumaMatrices() {
        int[][] matrizPasar = {{2, 4, 5}, {6, 6, 7}, {8, 9, 1}};
        Matriz m1 = new Matriz(matrizPasar);
        Matriz m2 = new Matriz(3,3);
        Matriz mResul = new SumaMatrices().sumaMatrices(m1, m2);
        int [][] expResult = mResul.getElementos();
        assertArrayEquals(expResult, matrizPasar);

    }
    @Test(expected = RuntimeException.class)
    public void testSumaMatricesFail1(){
        int[][] matrizPasar = {{2, 4}, {6, 6}, {8, 9}};
        Matriz m1 = new Matriz(matrizPasar);
        Matriz m2 = new Matriz(3,3);
        Matriz mResul = new SumaMatrices().sumaMatrices(m1, m2);
    }
    
    @Test(expected = RuntimeException.class)
    public void testSumaMatricesFail2(){
        int[][] matrizPasar = {{2, 4, 5}, {6, 6, 7}, {8, 9, 1},{1,1,1}};
        Matriz m1 = new Matriz(matrizPasar);
        Matriz m2 = new Matriz(3,3);
        Matriz mResul = new SumaMatrices().sumaMatrices(m1, m2);
    }
}
