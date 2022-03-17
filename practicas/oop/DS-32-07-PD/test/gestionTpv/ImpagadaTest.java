package gestionTpv;


import gestionProductos.ProdIndividual;
import static gestionProductos.TipoExistencia.UNIDAD;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author natalia.iglesiast
 * @author jose.naharro
 */
public class ImpagadaTest {

    public ImpagadaTest() {
    }

    @Test
    public void testComandaImpagadaToString() {

        Comanda comanda1 = new Comanda(1); //Ya está en fase Activa al crearla
        FaseComanda faseCobro = new Cobro();
        comanda1.setFase(faseCobro);
        FaseComanda faseImpagada = new Impagada();
        Impagada.getInstancia();
        comanda1.setFase(faseImpagada);
        assertEquals("impagada", comanda1.getFase().toString());
    }

    @Test(expected = NoSuchMethodError.class)
    public void testAddProducto() {
        double iva = 0.10;

        ProdIndividual aguaPequeña = new ProdIndividual("Agua FIC 50cl", "botella agua pequeña", 100, 1, iva, UNIDAD);

        Comanda comanda1 = new Comanda(1); //Ya está en fase Activa al crearla
        comanda1.addProducto(aguaPequeña, 1);
        FaseComanda faseCobro = new Cobro();
        comanda1.setFase(faseCobro);
        FaseComanda faseImpagada = new Impagada();
        comanda1.setFase(faseImpagada);
        comanda1.addProducto(aguaPequeña, 1);

    }

    @Test(expected = NoSuchMethodError.class)
    public void testRemoveProductos() {
        double iva = 0.10;

        ProdIndividual aguaPequeña = new ProdIndividual("Agua FIC 50cl", "botella agua pequeña", 100, 1, iva, UNIDAD);

        Comanda comanda1 = new Comanda(1); //Ya está en fase Activa al crearla
        comanda1.addProducto(aguaPequeña, 1);
        FaseComanda faseCobro = new Cobro();
        comanda1.setFase(faseCobro);        
        FaseComanda faseImpagada = new Impagada();
        comanda1.setFase(faseImpagada);

        comanda1.removeProducto(aguaPequeña);

    }

    @Test(expected = NoSuchMethodError.class)
    public void testClearComanda() {
        double iva = 0.10;

        ProdIndividual aguaPequeña = new ProdIndividual("Agua FIC 50cl", "botella agua pequeña", 100, 1, iva, UNIDAD);

        Comanda comanda1 = new Comanda(1); //Ya está en fase Activa al crearla
        comanda1.addProducto(aguaPequeña, 1);
        FaseComanda faseCobro = new Cobro();
        comanda1.setFase(faseCobro);
        FaseComanda faseImpagada = new Impagada();
        comanda1.setFase(faseImpagada);

        comanda1.clearComanda();
    }

    @Test(expected = NoSuchMethodError.class)
    public void testImprimirComanda() {
        double iva = 0.10;

        ProdIndividual aguaPequeña = new ProdIndividual("Agua FIC 50cl", "botella agua pequeña", 100, 1, iva, UNIDAD);

        Comanda comanda1 = new Comanda(1); //Ya está en fase Activa al crearla
        comanda1.addProducto(aguaPequeña, 1);
        FaseComanda faseCobro = new Cobro();
        comanda1.setFase(faseCobro);
        FaseComanda faseImpagada = new Impagada();
        comanda1.setFase(faseImpagada);

        comanda1.imprimir(10, 10);
    }
}
