package gestionTpv;

import gestionProductos.ProdIndividual;
import static gestionProductos.TipoExistencia.UNIDAD;
import java.util.ArrayList;
import java.util.Calendar;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author natalia.iglesiast
 * @author jose.naharro
 */
public class ComandaTest {

    public ComandaTest() {
    }

    /**
     * Test of setMesa method, of class Comanda.
     */
    @Test
    public void testSetMesa() {
        double iva = 0.10;

        ProdIndividual aguaPequeña = new ProdIndividual("Agua FIC 50cl", "botella agua pequeña", 100, 1, iva, UNIDAD);

        Comanda comanda1 = new Comanda(1); //Ya está en fase Activa al crearla
        comanda1.addProducto(aguaPequeña, 1);
        comanda1.setMesa(5);
        assertEquals(5, comanda1.getMesa());
    }

    /**
     * Test of setFechaYHora method, of class Comanda.
     */
    @Test
    public void testSetFechaYHora() {

        double iva = 0.10;

        ProdIndividual aguaPequeña = new ProdIndividual("Agua FIC 50cl", "botella agua pequeña", 100, 1, iva, UNIDAD);

        Comanda comanda1 = new Comanda(1); //Ya está en fase Activa al crearla
        comanda1.addProducto(aguaPequeña, 1);
        Calendar calendar = Calendar.getInstance();
        comanda1.setFechaYHora(calendar);
        assertEquals(calendar, comanda1.getFechaYHora());
    }

    /**
     * Test of setListadoCantidades method, of class Comanda.
     */
    @Test
    public void testSetListadoCantidades() {

        double iva = 0.10;

        ProdIndividual aguaPequeña = new ProdIndividual("Agua FIC 50cl", "botella agua pequeña", 100, 1, iva, UNIDAD);

        Comanda comanda1 = new Comanda(1); //Ya está en fase Activa al crearla
        comanda1.addProducto(aguaPequeña, 1);
        ArrayList<Integer> arrayCantidades = new ArrayList<>();
        arrayCantidades.add(5);
        comanda1.setListadoCantidades(arrayCantidades);
        assertEquals(arrayCantidades, comanda1.getListadoCantidades());
    }

    @Test
    public void testSetFase1() {
        double iva = 0.10;

        ProdIndividual aguaPequeña = new ProdIndividual("Agua FIC 50cl", "botella agua pequeña", 100, 1, iva, UNIDAD);

        Comanda comanda1 = new Comanda(1); //Ya está en fase Activa al crearla
        comanda1.addProducto(aguaPequeña, 1);
        FaseComanda faseCobro = new Cobro();
        FaseComanda faseActiva = new Activa();
        comanda1.setFase(faseCobro);
        comanda1.setFase(faseActiva);
        assertEquals("activa", comanda1.getFase().toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetMesaError1() {
        double iva = 0.10;

        ProdIndividual aguaPequeña = new ProdIndividual("Agua FIC 50cl", "botella agua pequeña", 100, 1, iva, UNIDAD);

        Comanda comanda1 = new Comanda(1); //Ya está en fase Activa al crearla
        comanda1.addProducto(aguaPequeña, 1);
        comanda1.setMesa(-5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetFaseError1() {
        double iva = 0.10;

        ProdIndividual aguaPequeña = new ProdIndividual("Agua FIC 50cl", "botella agua pequeña", 100, 1, iva, UNIDAD);

        Comanda comanda1 = new Comanda(1); //Ya está en fase Activa al crearla
        comanda1.addProducto(aguaPequeña, 1);
        FaseComanda faseCobro = new Cobro();
        FaseComanda fasePagada = new Pagada();
        FormaPago pagoEfectivo = new Efectivo();
        comanda1.setFase(faseCobro);
        comanda1.setFase(fasePagada);
        comanda1.setFormaPago(pagoEfectivo);
        comanda1.setFase(faseCobro);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetFaseError2() {
        double iva = 0.10;

        ProdIndividual aguaPequeña = new ProdIndividual("Agua FIC 50cl", "botella agua pequeña", 100, 1, iva, UNIDAD);

        Comanda comanda1 = new Comanda(1); //Ya está en fase Activa al crearla
        comanda1.addProducto(aguaPequeña, 1);
        FaseComanda faseActiva = new Activa();
        FaseComanda faseCobro = new Cobro();
        FaseComanda faseImagada = new Impagada();
        FormaPago pagoEfectivo = new Efectivo();
        comanda1.setFase(faseCobro);
        comanda1.setFase(faseImagada);
        comanda1.setFormaPago(pagoEfectivo);
        comanda1.setFase(faseActiva);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetFaseError3() {
        double iva = 0.10;

        ProdIndividual aguaPequeña = new ProdIndividual("Agua FIC 50cl", "botella agua pequeña", 100, 1, iva, UNIDAD);

        Comanda comanda1 = new Comanda(1); //Ya está en fase Activa al crearla
        comanda1.addProducto(aguaPequeña, 1);
        FaseComanda faseCancelada = new Cancelada();
        FaseComanda faseCobro = new Cobro();
        comanda1.setFase(faseCobro);
        comanda1.setFase(faseCancelada);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetFaseError4() {
        double iva = 0.10;

        ProdIndividual aguaPequeña = new ProdIndividual("Agua FIC 50cl", "botella agua pequeña", 100, 1, iva, UNIDAD);

        Comanda comanda1 = new Comanda(1); //Ya está en fase Activa al crearla
        comanda1.addProducto(aguaPequeña, 1);
        FaseComanda fasePagada = new Pagada();
        comanda1.setFase(fasePagada);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testSetFaseError5() {
        double iva = 0.10;

        ProdIndividual aguaPequeña = new ProdIndividual("Agua FIC 50cl", "botella agua pequeña", 100, 1, iva, UNIDAD);

        Comanda comanda1 = new Comanda(1); //Ya está en fase Activa al crearla
        comanda1.addProducto(aguaPequeña, 1);
        FaseComanda faseImpagada = new Impagada();
        comanda1.setFase(faseImpagada);
    }
}
