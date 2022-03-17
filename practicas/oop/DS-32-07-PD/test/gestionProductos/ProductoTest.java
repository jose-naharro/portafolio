package gestionProductos;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author natalia.iglesiast
 * @author jose.naharro
 */
public class ProductoTest {

    public ProductoTest() {
    }

    /**
     * Test of setTipoProducto method, of class Producto.
     */
    @Test
    public void testSetTipoProducto() {
        Ingrediente arroz = new Ingrediente("arroz", "arroz redondo", 1, 0.54, 0.1, TipoExistencia.KILOGRAMOS);

        arroz.setTipoProducto(TipoProducto.INDIVIDUAL);
        
        assertEquals(TipoProducto.INDIVIDUAL, arroz.getTipoProducto());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProductoMal1() {
        Ingrediente arroz = new Ingrediente("arroz", "arroz redondo", 9, 0.54, -0.1, TipoExistencia.KILOGRAMOS);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProductoMal2() {
        Ingrediente arroz = new Ingrediente("arroz", "arroz redondo", 9, -0.54, 0.1, TipoExistencia.KILOGRAMOS);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetIvaMal1() {
        Ingrediente arroz = new Ingrediente("arroz", "arroz redondo", 9, 0.54, 0.1, TipoExistencia.KILOGRAMOS);

        arroz.setIva(-0.04);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetPrecioMal1() {
        Ingrediente arroz = new Ingrediente("arroz", "arroz redondo", 9, 0.54, 0.1, TipoExistencia.KILOGRAMOS);

        arroz.setPrecio(-0.54);
    }

}
