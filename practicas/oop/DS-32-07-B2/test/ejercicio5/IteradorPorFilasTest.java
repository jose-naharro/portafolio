
package ejercicio5;

import java.util.NoSuchElementException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author natalia.iglesiast
 * @author jose.naharro
 */
public class IteradorPorFilasTest {
    
    public IteradorPorFilasTest() {
    }

    /**
     * Test of hasNext method, of class IteradorPorFilas.
     */
    @Test
    public void testHasNext() {

        Matriz m = new Matriz(3, 3);
        IteradorPorFilas itF = new IteradorPorFilas(m);
        assertEquals(true, itF.hasNext());
        itF.next();
        itF.next();
        itF.next();
        itF.next();
        itF.next();
        itF.next();
        itF.next();
        itF.next();
        assertEquals(false, itF.hasNext());

    }

    /**
     * Test of next method, of class IteradorPorFilas.
     */
    @Test
    public void testNext() {
        Matriz m = new Matriz(3, 3);
        IteradorPorFilas itF = new IteradorPorFilas(m);
        Integer expResult = 0;
        Integer result = itF.next();
        assertEquals(expResult, result);

    }

    /**
     * Test of remove method, of class IteradorPorFilas.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testRemove() {
        IteradorPorFilas itF = new IteradorPorFilas(null);
        itF.remove();
    }
    
    @Test(expected = NoSuchElementException.class)
    public void testNextFail() {
        Matriz m = new Matriz(3, 3);
        IteradorPorFilas itF = new IteradorPorFilas(m);
        itF.next();
        itF.next();
        itF.next();
        itF.next();
        itF.next();
        itF.next();
        itF.next();
        itF.next();
        itF.next();

    }
    
}
