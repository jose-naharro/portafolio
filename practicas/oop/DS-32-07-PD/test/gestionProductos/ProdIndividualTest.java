
package gestionProductos;

import org.junit.Test; 
import static org.junit.Assert.*;

/**
 *
 * @author natalia.iglesiast
 * @author jose.naharro
 */

public class ProdIndividualTest {

    @Test
    public void ProdIndividualTest() {
        ProdIndividual zumo = new ProdIndividual("Zumo", "zumo de naranja", 50,
                1.60, 0.1, TipoExistencia.UNIDAD);

        String resultN = zumo.getNombre();
        assertEquals("Zumo", resultN);

        String resultD = zumo.getDescripcion();
        assertEquals("zumo de naranja", resultD);

        double resultS = zumo.getStock();
        assertEquals(50, resultS, 0.1);

        double resultP = zumo.getPrecio();
        assertEquals(1.60, resultP, 0.1);

        double resultI = zumo.getIva();
        assertEquals(0.10, resultI, 0.1);

        TipoExistencia resultU = zumo.getUnidades();
        assertEquals(TipoExistencia.UNIDAD, resultU);
    }

    @Test
    public void ProdIndividualTest2() {

        ProdIndividual agua = new ProdIndividual("Agua", "agua pequeña", 0, 0.80, 0.1,
                TipoExistencia.UNIDAD);

        String resultN = agua.getNombre();
        assertEquals("Agua", resultN);
        
        agua.setNombre("agua2");
        resultN = agua.getNombre();
        assertEquals("agua2", resultN);
      
        String resultD = agua.getDescripcion();
        assertEquals("agua pequeña", resultD);
        
        agua.setDescripcion("agua con gas");
        resultD = agua.getDescripcion();
        assertEquals("agua con gas", resultD);
        
        double resultS = agua.getStock();
        assertEquals(0, resultS, 0.1);

        double resultP = agua.getPrecio();
        assertEquals(0.80, resultP, 0.1);
        
        agua.setPrecio(1.1);
        resultP = agua.getPrecio();
        assertEquals(1.1, resultP,0.1);

        double resultI = agua.getIva();
        assertEquals(0.10, resultI, 0.1);
        
        agua.setIva(0.15);
        resultI = agua.getIva();
        assertEquals(0.15, resultI,0.1);

        TipoExistencia resultU = agua.getUnidades();
        assertEquals(TipoExistencia.UNIDAD, resultU);
        
        agua.setUnidades(TipoExistencia.KILOGRAMOS);
        resultU = agua.getUnidades();
        assertEquals(TipoExistencia.KILOGRAMOS, resultU);
    

    }

    @Test
    public void testSetStock() {
        ProdIndividual refresco = new ProdIndividual("Refresco", "refresco frio",
                0, 1.80, 0.1, TipoExistencia.UNIDAD);

        refresco.setStock(10);
        double result = refresco.getStock();
        assertEquals(10, result, 0.1);

        refresco.setStock(0);
        result = refresco.getStock();
        assertEquals(0, result, 0.1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIndividualNoValido() {

        ProdIndividual zumo = new ProdIndividual("zumo piña", "zumo de piña",
                -1, 1.6, 0.1, TipoExistencia.UNIDAD);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetNoValido() {

        ProdIndividual zumo = new ProdIndividual("zumo piña", "zumo de piña",
                0, 1.6, 0.1, TipoExistencia.UNIDAD);

        zumo.setStock(-90);
    }

}
