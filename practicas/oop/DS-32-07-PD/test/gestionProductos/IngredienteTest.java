
package gestionProductos;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author natalia.iglesiast
 * @author jose.naharro
 */
public class IngredienteTest {

    @Test
    public void IngredienteTest() {

        Ingrediente cafe = new Ingrediente("Cafe", "cafe arabia", 50, 7.5, 0.1,
                TipoExistencia.KILOGRAMOS);

        String resultN = cafe.getNombre();
        assertEquals("Cafe", resultN);

        String resultD = cafe.getDescripcion();
        assertEquals("cafe arabia", resultD);

        double resultS = cafe.getStock();
        assertEquals(50, resultS, 0.1);

        double resultP = cafe.getPrecio();
        assertEquals(7.5, resultP, 0.1);

        double resultI = cafe.getIva();
        assertEquals(0.10, resultI, 0.1);

        TipoExistencia resultU = cafe.getUnidades();
        assertEquals(TipoExistencia.KILOGRAMOS, resultU);

    }

    @Test
    public void IngredienteTest2() {

        Ingrediente leche = new Ingrediente("leche", "leche entera", 0, 0.9,
                0.1, TipoExistencia.LITROS);

        String resultN = leche.getNombre();
        assertEquals("leche", resultN);

        leche.setNombre("sal");
        resultN = leche.getNombre();
        assertEquals("sal", resultN);

        String resultD = leche.getDescripcion();
        assertEquals("leche entera", resultD);

        leche.setDescripcion("en grano");
        resultD = leche.getDescripcion();
        assertEquals("en grano", resultD);

        double resultS = leche.getStock();
        assertEquals(0, resultS, 0.1);

        double resultP = leche.getPrecio();
        assertEquals(0.9, resultP, 0.1);

        leche.setPrecio(0.70);
        resultP = leche.getPrecio();
        assertEquals(0.70, resultP, 0.1);

        double resultI = leche.getIva();
        assertEquals(0.10, resultI, 0.1);

        leche.setIva(0.20);
        resultI = leche.getIva();
        assertEquals(0.20, resultI, 0.1);

        TipoExistencia resultU = leche.getUnidades();
        assertEquals(TipoExistencia.LITROS, resultU);

        leche.setUnidades(TipoExistencia.KILOGRAMOS);
        resultU = leche.getUnidades();
        assertEquals(TipoExistencia.KILOGRAMOS, resultU);

    }

    @Test
    public void testSet() {
        Ingrediente azucar = new Ingrediente("azucar", "azucar blanco", 9,
                0.54, 0.1, TipoExistencia.KILOGRAMOS);

        azucar.setStock(10);
        double result = azucar.getStock();
        assertEquals(10, result, 0.1);

        azucar.setStock(0);
        result = azucar.getStock();
        assertEquals(0, result, 0.1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIndividualNoValido() {

        Ingrediente arroz = new Ingrediente("arroz", "arroz redondo", -9,
                0.54, 0.1, TipoExistencia.KILOGRAMOS);

    }


    @Test(expected = IllegalArgumentException.class)
    public void testSetNoValido() {

        Ingrediente arroz = new Ingrediente("arroz", "arroz redondo", 9, 0.54,
                0.1, TipoExistencia.KILOGRAMOS);

        arroz.setStock(-90);
    }

}
