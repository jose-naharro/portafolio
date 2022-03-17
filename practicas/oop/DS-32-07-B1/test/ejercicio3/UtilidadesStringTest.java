
package ejercicio3;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 *@author natalia.iglesiast
 *@author jose.naharro
 */
public class UtilidadesStringTest {

    public UtilidadesStringTest() {
    }

    /**
     * Test of cuentaMayusculas method, of class UtilidadesString.
     */
    @Test
    public void testCuentaMayusculas() {
        String cad = "aaÑeRdTdëídFÏÓ";
        int expResult = 6;
        int result = UtilidadesString.cuentaMayusculas(cad);
        assertEquals(expResult, result);
    }

    /**
     * Test of cuentaMinusculas method, of class UtilidadesString.
     */
    @Test
    public void testCuentaMinusculas() {
        String cad = "aTggUñèëíìïYREaÓ";
        int expResult = 10;
        int result = UtilidadesString.cuentaMinusculas(cad);
        assertEquals(expResult, result);
    }

    /**
     * Test of cuentaTildes method, of class UtilidadesString.
     */
    @Test
    public void testCuentaTildes() {
        String cad = "aTggUñèëíìïYREaÓpgadiÏddÓáéíóúü ";
        int[] expResult = {1, 1, 2, 3, 1};
        int[] result = UtilidadesString.cuentaTildes(cad);
      
        assertEquals(expResult[0], result[0]);
        assertEquals(expResult[1], result[1]);
        assertEquals(expResult[2], result[2]);
        assertEquals(expResult[3], result[3]);
        assertEquals(expResult[4], result[4]);
    }

   
}
