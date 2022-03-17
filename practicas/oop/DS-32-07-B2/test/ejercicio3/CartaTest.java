package ejercicio3;

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
    public void testSet() {
        Carta carta1 = new Carta (NumeroCarta.SEIS ,PaloCarta.OROS );
        
        assertEquals(NumeroCarta.SEIS, carta1.getNumero());
        assertEquals(PaloCarta.OROS, carta1.getPalo());
    }
    
    
    @Test
    public void testToString() {
        Carta carta1 = new Carta (NumeroCarta.DOS ,PaloCarta.BASTOS );
        assertEquals("DOS de BASTOS", carta1.toString());
    }
    
    
}

