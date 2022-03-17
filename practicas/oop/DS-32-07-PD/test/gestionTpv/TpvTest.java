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
public class TpvTest {

    public TpvTest() {
    }

    /**
     * Test of addComanda method, of class Tpv.
     */
    @Test
    public void testAddComanda() {
        double iva = 0.10;
        double delta = 0.01;

        ProdIndividual aguaPequeña = new ProdIndividual("Agua FIC 50cl", "botella agua pequeña", 100, 1, iva, UNIDAD);

        Comanda comanda1 = new Comanda(1); //Ya está en fase Activa al crearla
        comanda1.addProducto(aguaPequeña, 1);
        FaseComanda faseCobro = new Cobro();
        FaseComanda fasePagada = new Pagada();
        FormaPago pagoEfectivo = new Efectivo();
        comanda1.setFase(faseCobro);
        comanda1.setFase(fasePagada);
        comanda1.setFormaPago(pagoEfectivo);
        comanda1.imprimir(20, 0);

        Tpv tpv1 = new Tpv();
        tpv1.addComanda(comanda1);
        tpv1.CerrarCaja();
        assertEquals(0, tpv1.getIngresos(), delta);
        assertEquals(true, tpv1.getListadoComandas().isEmpty());
    }

    /**
     * Test of removeComanda method, of class Tpv.
     */
    @Test
    public void testRemoveComanda() {
        double delta = 0.01;
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
        comanda1.imprimir(20, 10);

        Tpv tpv1 = new Tpv();
        tpv1.addComanda(comanda1);
        tpv1.removeComanda(comanda1);
        tpv1.CerrarCaja();
        assertEquals(0, tpv1.getIngresos(), delta);
        assertEquals(true, tpv1.getListadoComandas().isEmpty());

    }

    @Test
    public void testSetGetImpuestos() {
        double iva = 0.10;
        double delta = 0.01;

        ProdIndividual aguaPequeña = new ProdIndividual("Agua FIC 50cl", "botella agua pequeña", 100, 1, iva, UNIDAD);

        Comanda comanda1 = new Comanda(1); //Ya está en fase Activa al crearla
        comanda1.addProducto(aguaPequeña, 1);
        FaseComanda faseCobro = new Cobro();
        FaseComanda fasePagada = new Pagada();
        FormaPago pagoEfectivo = new Efectivo();
        comanda1.setFase(faseCobro);
        comanda1.setFase(fasePagada);
        comanda1.setFormaPago(pagoEfectivo);
        comanda1.imprimir(20, 0);

        Tpv tpv1 = new Tpv();
        tpv1.addComanda(comanda1);
        tpv1.CerrarCaja();
        tpv1.setImpuestos(100);
        assertEquals(100, tpv1.getImpuestos(), delta);

    }

    @Test
    public void testSetIngresos() {
        double iva = 0.10;
        double delta = 0.01;

        ProdIndividual aguaPequeña = new ProdIndividual("Agua FIC 50cl", "botella agua pequeña", 100, 1, iva, UNIDAD);

        Comanda comanda1 = new Comanda(1); //Ya está en fase Activa al crearla
        comanda1.addProducto(aguaPequeña, 1);
        FaseComanda faseCobro = new Cobro();
        FaseComanda fasePagada = new Pagada();
        FormaPago pagoEfectivo = new Efectivo();
        comanda1.setFase(faseCobro);
        comanda1.setFase(fasePagada);
        comanda1.setFormaPago(pagoEfectivo);
        comanda1.imprimir(20, 0);

        Tpv tpv1 = new Tpv();
        tpv1.addComanda(comanda1);
        tpv1.CerrarCaja();
        tpv1.setIngresos(100);
        assertEquals(100, tpv1.getIngresos(), delta);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddComandaMal1() {
        double iva = 0.10;

        ProdIndividual aguaPequeña = new ProdIndividual("Agua FIC 50cl", "botella agua pequeña", 100, 1, iva, UNIDAD);

        Comanda comanda1 = new Comanda(1); //Ya está en fase Activa al crearla
        comanda1.addProducto(aguaPequeña, 1);
        FaseComanda faseCobro = new Cobro();
        comanda1.setFase(faseCobro);

        Tpv tpv1 = new Tpv();
        tpv1.addComanda(comanda1);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveComandaMal1() {
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
        comanda1.imprimir(20, 10);

        Comanda comanda2 = new Comanda(2); //Ya está en fase Activa al crearla

        Tpv tpv1 = new Tpv();
        tpv1.addComanda(comanda1);
        tpv1.removeComanda(comanda2);

    }

}
