
package ejercicio4;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 *@author natalia.iglesiast
 *@author jose.naharro
 */
public class CartaTest {
    
    public CartaTest() {
    }
   
    @Test
    public void testEquals() {
        Object obj = new Carta (NumeroCarta.DOS ,PaloCarta.BASTOS); 
        
        Carta carta1 = new Carta (NumeroCarta.DOS ,PaloCarta.BASTOS );
        Carta carta2 = new Carta (NumeroCarta.DOS, PaloCarta.BASTOS);
        Object carta3 = null;
        Carta carta4 = new Carta (NumeroCarta.DOS, PaloCarta.ESPADAS);
        Carta carta5 = new Carta (NumeroCarta.AS, PaloCarta.OROS);
        
        assertEquals(true, obj.equals(carta1));
        assertEquals(true, carta1.equals(carta1));// Reflexividad
        assertEquals(true, carta1.equals(carta1));// Simetria
        assertEquals(true, obj.equals(carta1));// Transitividad
        assertEquals(true, carta1.equals(carta2));//Transitividad
        assertEquals(obj.equals(carta1), obj.equals(carta1));// Consistencia
        assertEquals(false, carta1.equals(null));// Uso de nulos, 
        assertEquals(false, obj.equals(carta3));
        String miString = new String();
        assertEquals(false, carta1.equals(miString));// Comparacion de clases  
        
        assertEquals(false, carta1.equals(carta4));
        assertEquals(false, carta1.equals(carta5));
    }
    
    @Test
    public void testToString() {
        Carta carta1 = new Carta (NumeroCarta.DOS ,PaloCarta.BASTOS );
        assertEquals("DOS de BASTOS", carta1.toString());// Comparacion de clases
    }
    
    @Test
    public void testHashCode() {
        Carta carta1 = new Carta (NumeroCarta.DOS ,PaloCarta.BASTOS );
        int expResult = 63;
        int result = carta1.hashCode();
        assertEquals(expResult, result);

    }
    
}
