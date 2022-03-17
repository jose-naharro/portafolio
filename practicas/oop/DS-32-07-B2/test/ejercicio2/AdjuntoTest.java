
package ejercicio2;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author natalia.iglesiast
 * @author jose.naharro
 */
public class AdjuntoTest {
    
    public AdjuntoTest() {
    }

    /**
     * Test of getNombre method, of class Adjunto.
     */
    @Test
    public void testGetNombre() {
        
        Adjunto adjunto1 = new Adjunto("adjuntoA",100);
        
        String expResult = "adjuntoA";
        String result = adjunto1.getNombre();
        
        assertEquals(expResult, result);

    }
    
     /**
     * Test of setNombre method, of class Adjunto.
     */
    @Test
    public void testSetNombre() {
        
        Adjunto adjunto1 = new Adjunto("adjuntoA",100);
        adjunto1.setNombre("adjuntoB");
        assertEquals("adjuntoB", adjunto1.getNombre());

    }

    /**
     * Test of getTamano method, of class Adjunto.
     */
    @Test
    public void testGetTamano() {
        
        Adjunto adjunto1 = new Adjunto("adjuntoA",100);
        
        int expResult = 100;
        int result = adjunto1.getTamano();
        
        assertEquals(expResult, result);

    }
     /**
     * Test of setNombre method, of class Adjunto.
     */
    @Test
    public void testSetTamano() {
        
        Adjunto adjunto1 = new Adjunto("adjuntoA",100);
        adjunto1.setTamano(200);
        assertEquals(200, adjunto1.getTamano());

    }    
    
}
