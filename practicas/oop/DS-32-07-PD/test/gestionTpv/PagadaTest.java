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
public class PagadaTest {

    public PagadaTest() {
    }

    @Test
    public void testImprimirEfectivo1() {

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

    }
    @Test
    public void testImprimirEfectivo2() {

        double iva = 0.10;

        ProdIndividual aguaPequeña = new ProdIndividual("Agua FIC 50cl", "botella agua pequeña", 100, 1, iva, UNIDAD);

        Comanda comanda1 = new Comanda(1); //Ya está en fase Activa al crearla
        comanda1.addProducto(aguaPequeña, 1);
        FaseComanda faseCobro = new Cobro();
        FaseComanda fasePagada = new Pagada();
        FormaPago pagoEfectivo = new Efectivo();
        Efectivo.getInstancia();
        comanda1.setFase(faseCobro);
        comanda1.setFase(fasePagada);
        comanda1.setFormaPago(pagoEfectivo);
        comanda1.imprimir(20, 0);

    }

    @Test
    public void testImprimirTarjeta1() {

        double iva = 0.10;

        ProdIndividual aguaPequeña = new ProdIndividual("Agua FIC 50cl", "botella agua pequeña", 100, 1, iva, UNIDAD);

        Comanda comanda1 = new Comanda(1); //Ya está en fase Activa al crearla
        comanda1.addProducto(aguaPequeña, 1);
        FaseComanda faseCobro = new Cobro();
        FaseComanda fasePagada = new Pagada();
        FormaPago pagoTarjeta = new Tarjeta();
        Tarjeta.getInstancia();
        comanda1.setFase(faseCobro);
        comanda1.setFase(fasePagada);
        comanda1.setFormaPago(pagoTarjeta);
        comanda1.imprimir(1.1, 0);

    }
    
    @Test
    public void testImprimirTarjeta2() {

        double iva = 0.10;

        ProdIndividual aguaPequeña = new ProdIndividual("Agua FIC 50cl", "botella agua pequeña", 100, 1, iva, UNIDAD);

        Comanda comanda1 = new Comanda(1); //Ya está en fase Activa al crearla
        comanda1.addProducto(aguaPequeña, 1);
        FaseComanda faseCobro = new Cobro();
        FaseComanda fasePagada = new Pagada();
        FormaPago pagoTarjeta = new Tarjeta();
        Tarjeta.getInstancia();
        comanda1.setFase(faseCobro);
        comanda1.setFase(fasePagada);
        comanda1.setFormaPago(pagoTarjeta);
        double entregado = 1.1*0.9;
        comanda1.imprimir(entregado, 10);

    }

    @Test
    public void testImprimirInvitacion() {

        double iva = 0.10;

        ProdIndividual aguaPequeña = new ProdIndividual("Agua FIC 50cl", "botella agua pequeña", 100, 1, iva, UNIDAD);

        Comanda comanda1 = new Comanda(1); //Ya está en fase Activa al crearla
        comanda1.addProducto(aguaPequeña, 1);
        FaseComanda faseCobro = new Cobro();
        FaseComanda fasePagada = new Pagada();
        FormaPago pagoInvitacion = new Invitacion();
        Invitacion.getInstancia();
        comanda1.setFase(faseCobro);
        comanda1.setFase(fasePagada);
        comanda1.setFormaPago(pagoInvitacion);
        comanda1.imprimir(0, 0);

    }

    @Test
    public void testImprimirChequeRegalo() {

        double iva = 0.10;

        ProdIndividual aguaPequeña = new ProdIndividual("Agua FIC 50cl", "botella agua pequeña", 100, 1, iva, UNIDAD);

        Comanda comanda1 = new Comanda(1); //Ya está en fase Activa al crearla
        comanda1.addProducto(aguaPequeña, 1);
        FaseComanda faseCobro = new Cobro();
        FaseComanda fasePagada = new Pagada();
        FormaPago pagoChequeRegalo = new ChequeRegalo();
        ChequeRegalo.getInstancia();
        comanda1.setFase(faseCobro);
        comanda1.setFase(fasePagada);
        comanda1.setFormaPago(pagoChequeRegalo);
        comanda1.imprimir(1.1, 0);

    }

    @Test
    public void testComandaPagadaToString() {

        Comanda comanda1 = new Comanda(1); //Ya está en fase Activa al crearla
        FaseComanda faseCobro = new Cobro();
        Cobro.getInstancia();
        FaseComanda fasePagada = new Pagada();
        Pagada.getInstancia();
        comanda1.setFase(faseCobro);
        comanda1.setFase(fasePagada);
        assertEquals("pagada", comanda1.getFase().toString());
    }

    @Test(expected = NoSuchMethodError.class)
    public void testAddProducto() {
        double iva = 0.10;

        ProdIndividual aguaPequeña = new ProdIndividual("Agua FIC 50cl", "botella agua pequeña", 100, 1, iva, UNIDAD);

        Comanda comanda1 = new Comanda(1); //Ya está en fase Activa al crearla
        comanda1.addProducto(aguaPequeña, 1);
        FaseComanda faseCobro = new Cobro();
        FaseComanda fasePagada = new Pagada();
        comanda1.setFase(faseCobro);
        comanda1.setFase(fasePagada);

        comanda1.addProducto(aguaPequeña, 1);

    }

    @Test(expected = NoSuchMethodError.class)
    public void testRemoveProductos() {
        double iva = 0.10;

        ProdIndividual aguaPequeña = new ProdIndividual("Agua FIC 50cl", "botella agua pequeña", 100, 1, iva, UNIDAD);

        Comanda comanda1 = new Comanda(1); //Ya está en fase Activa al crearla
        comanda1.addProducto(aguaPequeña, 1);
        FaseComanda faseCobro = new Cobro();
        FaseComanda fasePagada = new Pagada();
        comanda1.setFase(faseCobro);
        comanda1.setFase(fasePagada);

        comanda1.removeProducto(aguaPequeña);

    }

    @Test(expected = NoSuchMethodError.class)
    public void testClearComanda() {
        double iva = 0.10;

        ProdIndividual aguaPequeña = new ProdIndividual("Agua FIC 50cl", "botella agua pequeña", 100, 1, iva, UNIDAD);

        Comanda comanda1 = new Comanda(1); //Ya está en fase Activa al crearla
        comanda1.addProducto(aguaPequeña, 1);
        FaseComanda faseCobro = new Cobro();
        FaseComanda fasePagada = new Pagada();
        comanda1.setFase(faseCobro);
        comanda1.setFase(fasePagada);

        comanda1.clearComanda();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testImprimirEfectivoError1() {

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
        comanda1.imprimir(-1, 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testImprimirEfectivoError2() {

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
        comanda1.imprimir(0.2, 10);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testImprimirEfectivoError3() {

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
        comanda1.imprimir(10, -10);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testImprimirchequeRegaloError() {

        double iva = 0.10;

        ProdIndividual aguaPequeña = new ProdIndividual("Agua FIC 50cl", "botella agua pequeña", 100, 1, iva, UNIDAD);

        Comanda comanda1 = new Comanda(1); //Ya está en fase Activa al crearla
        comanda1.addProducto(aguaPequeña, 1);
        FaseComanda faseCobro = new Cobro();
        FaseComanda fasePagada = new Pagada();
        FormaPago pagoCheque = new ChequeRegalo();
        comanda1.setFase(faseCobro);
        comanda1.setFase(fasePagada);
        comanda1.setFormaPago(pagoCheque);
        comanda1.imprimir(2, 0);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testImprimirTarjetaMal1() {

        double iva = 0.10;

        ProdIndividual aguaPequeña = new ProdIndividual("Agua FIC 50cl", "botella agua pequeña", 100, 1, iva, UNIDAD);

        Comanda comanda1 = new Comanda(1); //Ya está en fase Activa al crearla
        comanda1.addProducto(aguaPequeña, 1);
        FaseComanda faseCobro = new Cobro();
        FaseComanda fasePagada = new Pagada();
        FormaPago pagoTarjeta = new Tarjeta();
        comanda1.setFase(faseCobro);
        comanda1.setFase(fasePagada);
        comanda1.setFormaPago(pagoTarjeta);
        comanda1.imprimir(1.5, 0);

    }
    @Test(expected = IllegalArgumentException.class)
    public void testImprimirTarjetaMal2() {

        double iva = 0.10;

        ProdIndividual aguaPequeña = new ProdIndividual("Agua FIC 50cl", "botella agua pequeña", 100, 1, iva, UNIDAD);

        Comanda comanda1 = new Comanda(1); //Ya está en fase Activa al crearla
        comanda1.addProducto(aguaPequeña, 1);
        FaseComanda faseCobro = new Cobro();
        FaseComanda fasePagada = new Pagada();
        FormaPago pagoTarjeta = new Tarjeta();
        comanda1.setFase(faseCobro);
        comanda1.setFase(fasePagada);
        comanda1.setFormaPago(pagoTarjeta);
        comanda1.imprimir(1.1, -15);

    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testImprimirTarjetaMal3() {

        double iva = 0.10;

        ProdIndividual aguaPequeña = new ProdIndividual("Agua FIC 50cl", "botella agua pequeña", 100, 1, iva, UNIDAD);

        Comanda comanda1 = new Comanda(1); //Ya está en fase Activa al crearla
        comanda1.addProducto(aguaPequeña, 1);
        FaseComanda faseCobro = new Cobro();
        FaseComanda fasePagada = new Pagada();
        FormaPago pagoTarjeta = new Tarjeta();
        comanda1.setFase(faseCobro);
        comanda1.setFase(fasePagada);
        comanda1.setFormaPago(pagoTarjeta);
        comanda1.imprimir(-1, 0);

    }

}
