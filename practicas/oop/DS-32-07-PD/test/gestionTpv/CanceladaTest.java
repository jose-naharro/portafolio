package gestionTpv;

import gestionProductos.Ingrediente;
import gestionProductos.ProdIndividual;
import gestionProductos.ProdMultiple;
import static gestionProductos.TipoExistencia.KILOGRAMOS;
import static gestionProductos.TipoExistencia.LITROS;
import static gestionProductos.TipoExistencia.UNIDAD;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author natalia.iglesiast
 * @author jose.naharro
 */
public class CanceladaTest {

    public CanceladaTest() {
    }

    @Test
    public void testClearComanda() {

        double delta = 0.01;
        double iva = 0.10;

        Ingrediente cafe = new Ingrediente("cafe", "cafe variedad colombia JUAN VALDES", 5, 14, iva, KILOGRAMOS);
        Ingrediente leche = new Ingrediente("leche", "leche entera LA VACA", 30, 0.7, iva, LITROS);
        Ingrediente azucar = new Ingrediente("cafe", "azucar blanquilla SUCRE", 5, 0.7, iva, KILOGRAMOS);

        ProdIndividual croasan = new ProdIndividual("Croasan", "croasan PASTELERIA", 15, 1, iva, UNIDAD);
        ProdIndividual zumoNaranja = new ProdIndividual("Zumo de naranja", "zumo de naranja ZUMOFIC", 20, 1, iva, UNIDAD);
        ProdIndividual aguaPequeña = new ProdIndividual("Agua FIC 50cl", "botella agua pequeña", 100, 1, iva, UNIDAD);

        ProdMultiple cafeLeche = new ProdMultiple("Cafe con leche", "cafe con leche normal", 1, iva, UNIDAD);
        cafeLeche.addProductos(cafe, 0.05);
        cafeLeche.addProductos(leche, 0.05);
        cafeLeche.addProductos(azucar, 0.01);

        ProdMultiple desayuno1 = new ProdMultiple("Desayuno FIC", "cafe con leche normal, zumo de naranja y croasan", 2.8, iva, UNIDAD);
        desayuno1.addProductos(croasan, 1);
        desayuno1.addProductos(zumoNaranja, 1);
        desayuno1.addProductos(cafeLeche, 1);

        Comanda comanda1 = new Comanda(1); //Ya está en fase Activa al crearla
        comanda1.addProducto(desayuno1, 1);
        comanda1.addProducto(aguaPequeña, 1);
        comanda1.addProducto(croasan, 1);

        assertEquals(desayuno1, comanda1.getListadoComanda().get(0));
        assertEquals(aguaPequeña, comanda1.getListadoComanda().get(1));
        assertEquals(croasan, comanda1.getListadoComanda().get(2));
        assertEquals(1, comanda1.getListadoCantidades().get(0), delta);
        assertEquals(1, comanda1.getListadoCantidades().get(1), delta);
        assertEquals(1, comanda1.getListadoCantidades().get(2), delta);
        assertEquals(4.95, cafe.getStock(), delta);
        assertEquals(29.95, leche.getStock(), delta);
        assertEquals(4.99, azucar.getStock(), delta);
        assertEquals(13, croasan.getStock(), delta);
        assertEquals(99, aguaPequeña.getStock(), delta);
        assertEquals(19, zumoNaranja.getStock(), delta);

        FaseComanda faseCancelada = new Cancelada();
        comanda1.setFase(faseCancelada);

        assertEquals(true, comanda1.getListadoComanda().isEmpty());
        assertEquals(true, comanda1.getListadoCantidades().isEmpty());
        assertEquals(5, cafe.getStock(), delta);
        assertEquals(30, leche.getStock(), delta);
        assertEquals(5, azucar.getStock(), delta);
        assertEquals(15, croasan.getStock(), delta);
        assertEquals(100, aguaPequeña.getStock(), delta);
        assertEquals(20, zumoNaranja.getStock(), delta);
    }

    @Test
    public void testComandaCobroToString() {

        Comanda comanda1 = new Comanda(1); //Ya está en fase Activa al crearla
        
        FaseComanda faseCancelada = new Cancelada();
        Cancelada.getInstancia();
        comanda1.setFase(faseCancelada);
        assertEquals("cancelada", comanda1.getFase().toString());
    }

    @Test(expected = NoSuchMethodError.class)
    public void testAddProducto() {
        double iva = 0.10;

        ProdIndividual aguaPequeña = new ProdIndividual("Agua FIC 50cl", "botella agua pequeña", 100, 1, iva, UNIDAD);

        Comanda comanda1 = new Comanda(1); //Ya está en fase Activa al crearla
        comanda1.addProducto(aguaPequeña, 1);
        FaseComanda faseCancelada = new Cancelada();
        comanda1.setFase(faseCancelada);

        comanda1.addProducto(aguaPequeña, 1);

    }

    @Test(expected = NoSuchMethodError.class)
    public void testRemoveProductos() {
        double iva = 0.10;

        ProdIndividual aguaPequeña = new ProdIndividual("Agua FIC 50cl", "botella agua pequeña", 100, 1, iva, UNIDAD);

        Comanda comanda1 = new Comanda(1); //Ya está en fase Activa al crearla
        comanda1.addProducto(aguaPequeña, 1);
        FaseComanda faseCancelada = new Cancelada();
        comanda1.setFase(faseCancelada);

        comanda1.removeProducto(aguaPequeña);

    }


    @Test(expected = NoSuchMethodError.class)
    public void testImprimirComanda() {
        double iva = 0.10;

        ProdIndividual aguaPequeña = new ProdIndividual("Agua FIC 50cl", "botella agua pequeña", 100, 1, iva, UNIDAD);

        Comanda comanda1 = new Comanda(1); //Ya está en fase Activa al crearla
        comanda1.addProducto(aguaPequeña, 1);
        FaseComanda faseCancelada = new Cancelada();
        comanda1.setFase(faseCancelada);

        comanda1.imprimir(10, 10);
    }

}
