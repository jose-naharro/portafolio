
package ejercicio2;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 *@author natalia.iglesiast
 *@author jose.naharro
 */
public class CribaEratostenesTest {

   
    @Test
    public void testEsPrimo() {
        CribaEratostenes criba = new CribaEratostenes(10);
        assertTrue(criba.esPrimo(5));

        criba = new CribaEratostenes(100);
        assertFalse(criba.esPrimo(55));

    }

  
    @Test
    public void testAnteriorPrimo() {
        CribaEratostenes criba = new CribaEratostenes(50);
        int expResult = 23;
        int result = criba.anteriorPrimo(26);
        assertEquals(expResult, result);

        criba = new CribaEratostenes(100);
        expResult = 13;
        result = criba.anteriorPrimo(17);
        assertEquals(expResult, result);
        
        criba = new CribaEratostenes(13);
        expResult = 11;
        result = criba.anteriorPrimo(12);
        assertEquals(expResult, result);
        
        expResult = 2;
        result = criba.anteriorPrimo(3);
        assertEquals(expResult, result);
        
    }

 
    @Test
    public void testSiguientePrimo() {
        CribaEratostenes criba = new CribaEratostenes(50);
        int expResult = 29;
        int result = criba.siguientePrimo(25);
        assertEquals(expResult, result);

        criba = new CribaEratostenes(100);
        expResult = 11;
        result = criba.siguientePrimo(7);
        assertEquals(expResult, result);
        
         criba = new CribaEratostenes(12);
        expResult = 11;
        result = criba.siguientePrimo(7);
        assertEquals(expResult, result);
        
        
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEsPrimoNoValido1() {
        CribaEratostenes criba = new CribaEratostenes(50);
        criba.esPrimo(-5);
    }
    
     @Test(expected = IllegalArgumentException.class)
    public void testEsPrimoNoValido2() {
        CribaEratostenes criba = new CribaEratostenes(50);
        criba.esPrimo(56);
    }
     
    
    @Test(expected = IllegalArgumentException.class)
    public void testAnteriorPrimoNoValido() {
        CribaEratostenes criba = new CribaEratostenes(50);
        criba.anteriorPrimo(2);
    }

       @Test(expected = IllegalArgumentException.class)
    public void testAnteriorPrimoNoValido2() {
        CribaEratostenes criba = new CribaEratostenes(50);
        criba.anteriorPrimo(52); 
    }
    
       @Test(expected = IllegalArgumentException.class)
    public void testAnteriorPrimoNoValido3() {
        CribaEratostenes criba = new CribaEratostenes(50);
        criba.anteriorPrimo(-3); 
    }
    
    
    @Test(expected = IllegalArgumentException.class)
    public void testSiguientePrimoNoValido() {
        CribaEratostenes criba = new CribaEratostenes(50);
        criba.siguientePrimo(48); 
    }
    
        
    @Test(expected = IllegalArgumentException.class)
    public void testSiguientePrimoNoValido2() {
        CribaEratostenes criba = new CribaEratostenes(50);
        criba.siguientePrimo(55); 
    
    }
    
     @Test(expected = IllegalArgumentException.class)
    public void testSiguientePrimoNoValido3() {
        CribaEratostenes criba = new CribaEratostenes(50);
        criba.siguientePrimo(-5); 
    
    }
    
     @Test(expected = IllegalArgumentException.class)
    public void testSiguientePrimoNoValido4() {
        CribaEratostenes criba = new CribaEratostenes(50);
        criba.siguientePrimo(49); 
    
    }
}
