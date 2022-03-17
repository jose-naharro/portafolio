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
public class ActivaTest {

    public ActivaTest() {
    }

    @Test
    public void testAddProducto() {
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
        comanda1.addProducto(desayuno1, 3);
        comanda1.addProducto(aguaPequeña, 1);
        comanda1.addProducto(croasan, 1);

        assertEquals(desayuno1, comanda1.getListadoComanda().get(0));
        assertEquals(aguaPequeña, comanda1.getListadoComanda().get(1));
        assertEquals(croasan, comanda1.getListadoComanda().get(2));
        assertEquals(3, comanda1.getListadoCantidades().get(0), delta);
        assertEquals(1, comanda1.getListadoCantidades().get(1), delta);
        assertEquals(1, comanda1.getListadoCantidades().get(2), delta);
        assertEquals(4.85, cafe.getStock(), delta);
        assertEquals(29.85, leche.getStock(), delta);
        assertEquals(4.97, azucar.getStock(), delta);
        assertEquals(11, croasan.getStock(), delta);
        assertEquals(99, aguaPequeña.getStock(), delta);
        assertEquals(17, zumoNaranja.getStock(), delta);

        comanda1.addProducto(desayuno1, 3);

        assertEquals(desayuno1, comanda1.getListadoComanda().get(0));
        assertEquals(aguaPequeña, comanda1.getListadoComanda().get(1));
        assertEquals(croasan, comanda1.getListadoComanda().get(2));
        assertEquals(6, comanda1.getListadoCantidades().get(0), delta);
        assertEquals(1, comanda1.getListadoCantidades().get(1), delta);
        assertEquals(1, comanda1.getListadoCantidades().get(2), delta);
        assertEquals(4.70, cafe.getStock(), delta);
        assertEquals(29.70, leche.getStock(), delta);
        assertEquals(4.94, azucar.getStock(), delta);
        assertEquals(8, croasan.getStock(), delta);
        assertEquals(99, aguaPequeña.getStock(), delta);
        assertEquals(14, zumoNaranja.getStock(), delta);

    }
    
    @Test
    public void testAddProducto2() {
        double delta = 0.01;
        double iva = 0.10;

        ProdIndividual aguaPequeña = new ProdIndividual("Agua FIC 50cl", "botella agua pequeña", 100, 1, iva, UNIDAD);

        Comanda comanda1 = new Comanda(1); //Ya está en fase Activa al crearla
        comanda1.addProducto(aguaPequeña, 1);

        assertEquals(aguaPequeña, comanda1.getListadoComanda().get(0));
        assertEquals(1, comanda1.getListadoCantidades().get(0), delta);
        assertEquals(99, aguaPequeña.getStock(), delta);
    }

    @Test
    public void testRemoveProductos() {
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

        comanda1.removeProducto(desayuno1);
        comanda1.removeProducto(croasan);

        assertEquals(desayuno1, comanda1.getListadoComanda().get(0));
        assertEquals(aguaPequeña, comanda1.getListadoComanda().get(1));
        assertEquals(croasan, comanda1.getListadoComanda().get(2));
        assertEquals(1, comanda1.getListadoCantidades().get(0), delta);
        assertEquals(1, comanda1.getListadoCantidades().get(1), delta);
        assertEquals(5, cafe.getStock(), delta);
        assertEquals(30, leche.getStock(), delta);
        assertEquals(5, azucar.getStock(), delta);
        assertEquals(15, croasan.getStock(), delta);
        assertEquals(99, aguaPequeña.getStock(), delta);
        assertEquals(20, zumoNaranja.getStock(), delta);

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

        comanda1.clearComanda();

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
    public void testComandaActivaToString() {

        Comanda comanda1 = new Comanda(1); //Ya está en fase Activa al crearla

        assertEquals("activa", comanda1.getFase().toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionAddProductos() {
        double iva = 0.10;

        Ingrediente cafe = new Ingrediente("cafe", "cafe variedad colombia JUAN VALDES", 5, 14, iva, KILOGRAMOS);
        Ingrediente leche = new Ingrediente("leche", "leche entera LA VACA", 30, 0.7, iva, LITROS);
        Ingrediente azucar = new Ingrediente("cafe", "azucar blanquilla SUCRE", 5, 0.7, iva, KILOGRAMOS);

        ProdIndividual croasan = new ProdIndividual("Croasan", "croasan PASTELERIA", 15, 1, iva, UNIDAD);
        ProdIndividual zumoNaranja = new ProdIndividual("Zumo de naranja", "zumo de naranja ZUMOFIC", 20, 1, iva, UNIDAD);

        ProdMultiple cafeLeche = new ProdMultiple("Cafe con leche", "cafe con leche normal", 1, iva, UNIDAD);
        cafeLeche.addProductos(cafe, 0.05);
        cafeLeche.addProductos(leche, 0.05);
        cafeLeche.addProductos(azucar, 0.01);

        ProdMultiple desayuno1 = new ProdMultiple("Desayuno FIC", "cafe con leche normal, zumo de naranja y croasan", 2.8, iva, UNIDAD);
        desayuno1.addProductos(croasan, 1);
        desayuno1.addProductos(zumoNaranja, 1);
        desayuno1.addProductos(cafeLeche, 1);

        Comanda comanda1 = new Comanda(1); //Ya está en fase Activa al crearla
        comanda1.addProducto(desayuno1, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionAddProductos2() {
        double iva = 0.10;

        ProdIndividual aguaPequeña = new ProdIndividual("Agua FIC 50cl", "botella agua pequeña", 100, 1, iva, UNIDAD);

        Comanda comanda1 = new Comanda(1); //Ya está en fase Activa al crearla
        comanda1.addProducto(aguaPequeña, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionAddProductos3() {
        double iva = 0.10;

        Ingrediente cafe = new Ingrediente("cafe", "cafe variedad colombia JUAN VALDES", 5, 14, iva, KILOGRAMOS);
        Ingrediente leche = new Ingrediente("leche", "leche entera LA VACA", 30, 0.7, iva, LITROS);
        Ingrediente azucar = new Ingrediente("cafe", "azucar blanquilla SUCRE", 5, 0.7, iva, KILOGRAMOS);

        ProdIndividual croasan = new ProdIndividual("Croasan", "croasan PASTELERIA", 15, 1, iva, UNIDAD);
        ProdIndividual zumoNaranja = new ProdIndividual("Zumo de naranja", "zumo de naranja ZUMOFIC", 20, 1, iva, UNIDAD);

        ProdMultiple cafeLeche = new ProdMultiple("Cafe con leche", "cafe con leche normal", 1, iva, UNIDAD);
        cafeLeche.addProductos(cafe, 0.05);
        cafeLeche.addProductos(leche, 0.05);
        cafeLeche.addProductos(azucar, 0.01);

        ProdMultiple desayuno1 = new ProdMultiple("Desayuno FIC", "cafe con leche normal, zumo de naranja y croasan", 2.8, iva, UNIDAD);
        desayuno1.addProductos(croasan, 1);
        desayuno1.addProductos(zumoNaranja, 1);
        desayuno1.addProductos(cafeLeche, 1);

        Comanda comanda1 = new Comanda(1); //Ya está en fase Activa al crearla
        comanda1.addProducto(desayuno1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionAddProductos4() {
        double iva = 0.10;

        ProdIndividual aguaPequeña = new ProdIndividual("Agua FIC 50cl", "botella agua pequeña", 100, 1, iva, UNIDAD);

        Comanda comanda1 = new Comanda(1); //Ya está en fase Activa al crearla
        comanda1.addProducto(aguaPequeña, 0);
    }

    @Test(expected = NoSuchMethodError.class)
    public void testRemoveProductos1() {
        double iva = 0.10;

        ProdIndividual aguaPequeña = new ProdIndividual("Agua FIC 50cl", "botella agua pequeña", 100, 1, iva, UNIDAD);
        FaseComanda faseCobro = new Cobro();

        Comanda comanda1 = new Comanda(1); //Ya está en fase Activa al crearla
        comanda1.addProducto(aguaPequeña, 1);
        comanda1.setFase(faseCobro);
        comanda1.removeProducto(aguaPequeña);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveProductos2() {
        double iva = 0.10;

        ProdIndividual aguaPequeña = new ProdIndividual("Agua FIC 50cl", "botella agua pequeña", 100, 1, iva, UNIDAD);

        Comanda comanda1 = new Comanda(1); //Ya está en fase Activa al crearla

        comanda1.removeProducto(aguaPequeña);
    }

    @Test(expected = NoSuchMethodError.class)
    public void testImprimir() {
        double iva = 0.10;

        ProdIndividual aguaPequeña = new ProdIndividual("Agua FIC 50cl", "botella agua pequeña", 100, 1, iva, UNIDAD);

        Comanda comanda1 = new Comanda(1); //Ya está en fase Activa al crearla
        comanda1.addProducto(aguaPequeña, 1);
        comanda1.imprimir(20, 10);
    }

}
