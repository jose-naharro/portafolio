package gestionProductos;

import static gestionProductos.TipoExistencia.KILOGRAMOS;
import static gestionProductos.TipoExistencia.LITROS;
import static gestionProductos.TipoExistencia.UNIDAD;
import gestionTpv.Comanda;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author natalia.iglesiast
 * @author jose.naharro
 */
public class ProdMultipleTest {

    public ProdMultipleTest() {

    }

    @Test
    public void testSetListadoProductos() {
        double iva = 0.10;

        Ingrediente cafe = new Ingrediente("cafe", "cafe variedad colombia JUAN VALDES", 5, 14, iva, KILOGRAMOS);
        Ingrediente leche = new Ingrediente("leche", "leche entera LA VACA", 30, 0.7, iva, LITROS);
        Ingrediente azucar = new Ingrediente("cafe", "azucar blanquilla SUCRE", 5, 0.7, iva, KILOGRAMOS);

        ProdIndividual croasan = new ProdIndividual("Croasan", "croasan PASTELERIA", 15, 1, iva, UNIDAD);
        ProdIndividual zumoNaranja = new ProdIndividual("Zumo naranja", "zumo de naranja ZUMOFIC", 20, 1, iva, UNIDAD);

        ProdMultiple cafeLeche = new ProdMultiple("Cafe leche", "cafe con leche normal", 1, iva, UNIDAD);
        cafeLeche.addProductos(cafe, 0.05);
        cafeLeche.addProductos(leche, 0.05);
        cafeLeche.addProductos(azucar, 0.01);

        ProdMultiple desayuno1 = new ProdMultiple("Desayuno FIC", "cafe con leche normal, zumo de naranja y croasan", 2.8, iva, UNIDAD);
        desayuno1.addProductos(croasan, 1);
        desayuno1.addProductos(zumoNaranja, 1);
        desayuno1.addProductos(cafeLeche, 1);

        ArrayList<Producto> arrayProductos = new ArrayList<>();
        arrayProductos.add(zumoNaranja);
        arrayProductos.add(zumoNaranja);
        arrayProductos.add(zumoNaranja);

        desayuno1.setListadoProductos(arrayProductos);
        assertEquals(arrayProductos, desayuno1.getListadoProductos());
    }

    @Test
    public void testSetListadoCantidades() {
        double iva = 0.10;

        Ingrediente cafe = new Ingrediente("cafe", "cafe variedad colombia JUAN VALDES", 5, 14, iva, KILOGRAMOS);
        Ingrediente leche = new Ingrediente("leche", "leche entera LA VACA", 30, 0.7, iva, LITROS);
        Ingrediente azucar = new Ingrediente("cafe", "azucar blanquilla SUCRE", 5, 0.7, iva, KILOGRAMOS);

        ProdIndividual croasan = new ProdIndividual("Croasan", "croasan PASTELERIA", 15, 1, iva, UNIDAD);
        ProdIndividual zumoNaranja = new ProdIndividual("Zumo naranja", "zumo de naranja ZUMOFIC", 20, 1, iva, UNIDAD);

        ProdMultiple cafeLeche = new ProdMultiple("Cafe leche", "cafe con leche normal", 1, iva, UNIDAD);
        cafeLeche.addProductos(cafe, 0.05);
        cafeLeche.addProductos(leche, 0.05);
        cafeLeche.addProductos(azucar, 0.01);

        ProdMultiple desayuno1 = new ProdMultiple("Desayuno FIC", "cafe con leche normal, zumo de naranja y croasan", 2.8, iva, UNIDAD);
        desayuno1.addProductos(croasan, 1);
        desayuno1.addProductos(zumoNaranja, 1);
        desayuno1.addProductos(cafeLeche, 1);

        ArrayList<Double> arrayCantidades = new ArrayList<>();
        arrayCantidades.add(2.0);
        arrayCantidades.add(2.0);
        arrayCantidades.add(2.0);

        desayuno1.setListadoCantidades(arrayCantidades);
        assertEquals(arrayCantidades, desayuno1.getListadoCantidades());
    }

    @Test
    public void testSetGetCalculoDelPrecio() {
        double iva = 0.10;

        ProdMultiple cafeLeche = new ProdMultiple("Cafe leche", "cafe con leche normal", 1, iva, UNIDAD);
        CalculoPrecio precioDerivado = new CalculoPrecioDerivado();
        cafeLeche.setCalculoDelPrecio(precioDerivado);
        assertEquals("precio derivado", cafeLeche.getCalculoDelPrecio().toString());
    }

        @Test
    public void testProductoMultiple() {
        Ingrediente cafe = new Ingrediente("cafe", "cafe arabia", 50, 7.5, 0.1,
                TipoExistencia.KILOGRAMOS);

        Ingrediente leche = new Ingrediente("leche", "leche entera", 50, 0.9,
                0.1, TipoExistencia.LITROS);

        Ingrediente azucar = new Ingrediente("azucar", "azucar blanco", 9,
                0, 0.1, TipoExistencia.KILOGRAMOS);

        ProdMultiple cafeLeche = new ProdMultiple("Cafe con leche",
                "cafe con leche normal", 1, 0.1, TipoExistencia.UNIDAD);

        cafeLeche.addProductos(cafe, 0.05);
        cafeLeche.addProductos(leche, 0.05);
        cafeLeche.addProductos(azucar, 0.01);

        String result = cafeLeche.getListadoProductos().get(0).getNombre();
        assertEquals("cafe", result);

        result = cafeLeche.getListadoProductos().get(1).getNombre();
        assertEquals("leche", result);

        result = cafeLeche.getListadoProductos().get(2).getNombre();
        assertEquals("azucar", result);

        double resultC = cafeLeche.getListadoCantidades().get(0);
        assertEquals(0.05, resultC, 0.01);

        resultC = cafeLeche.getListadoCantidades().get(1);
        assertEquals(0.05, resultC, 0.01);

        resultC = cafeLeche.getListadoCantidades().get(2);
        assertEquals(0.01, resultC, 0.01);

        CalculoPrecio precioDerivado = new CalculoPrecioDerivado();
        cafeLeche.setCalculoDelPrecio(precioDerivado);
        cafeLeche.calcularPrecio();
        double resultPrecio = cafeLeche.getPrecio();
        assertEquals(0.38, resultPrecio, 0.01);

    }

    @Test
    public void testProductoMultiple2() {
        Ingrediente cafe = new Ingrediente("cafe", "cafe arabia", 50, 7.5, 0.1,
                TipoExistencia.KILOGRAMOS);

        Ingrediente leche = new Ingrediente("leche", "leche entera", 50, 0.9,
                0.1, TipoExistencia.LITROS);

        Ingrediente azucar = new Ingrediente("azucar", "azucar blanco", 9,
                0.54, 0.1, TipoExistencia.KILOGRAMOS);

        ProdMultiple cafeLeche = new ProdMultiple("Cafe con leche",
                "cafe con leche normal", 1, 0.1, TipoExistencia.UNIDAD);
        cafeLeche.addProductos(cafe, 0.05);
        cafeLeche.addProductos(leche, 0.05);
        cafeLeche.addProductos(azucar, 0.01);

        ProdIndividual croasan = new ProdIndividual("Croasan",
                "croasan PASTELERIA", 15, 1, 0.1, UNIDAD);
        ProdIndividual zumoNaranja = new ProdIndividual("Zumo de naranja",
                "zumo de naranja ZUMOFIC", 20, 1, 0.1, UNIDAD);
        ProdMultiple desayuno = new ProdMultiple("Desayuno FIC",
                "cafe con leche normal, zumo de naranja y croasan", 2.8,
                0.1, TipoExistencia.UNIDAD);

        desayuno.addProductos(croasan, 1);
        desayuno.addProductos(zumoNaranja, 1);
        desayuno.addProductos(cafeLeche, 1);

        String result = desayuno.getListadoProductos().get(0).getNombre();
        assertEquals("Croasan", result);

        result = desayuno.getListadoProductos().get(1).getNombre();
        assertEquals("Zumo de naranja", result);

        result = desayuno.getListadoProductos().get(2).getNombre();
        assertEquals("Cafe con leche", result);

        double resultC = desayuno.getListadoCantidades().get(0);
        assertEquals(1, resultC, 0.01);

        resultC = desayuno.getListadoCantidades().get(1);
        assertEquals(1, resultC, 0.01);

        resultC = desayuno.getListadoCantidades().get(2);
        assertEquals(1, resultC, 0.01);

        CalculoPrecio precioDerivado = new CalculoPrecioDerivado();
        desayuno.setCalculoDelPrecio(precioDerivado);
        desayuno.calcularPrecio();
        
        double resultPrecio = desayuno.getPrecio();
        assertEquals(2.7, resultPrecio, 0.01);
        

    }

    public void testMultipleValido2() {

        Ingrediente cafe = new Ingrediente("cafe", "cafe arabia", 0.05, 7.5, 0.1,
                TipoExistencia.KILOGRAMOS);

        Ingrediente leche = new Ingrediente("leche", "leche entera", 50, 0.9,
                0.1, TipoExistencia.LITROS);

        Ingrediente azucar = new Ingrediente("azucar", "azucar blanco", 9,
                0.54, 0.1, TipoExistencia.KILOGRAMOS);

        ProdMultiple cafeLeche = new ProdMultiple("Cafe con leche",
                "cafe con leche normal", 1, 0.1, TipoExistencia.UNIDAD);
        cafeLeche.addProductos(cafe, 0.05);
        cafeLeche.addProductos(leche, 0.05);
        cafeLeche.addProductos(azucar, 0.01);

        ProdIndividual croasan = new ProdIndividual("Croasan",
                "croasan PASTELERIA", 15, 1, 0.1, UNIDAD);
        ProdIndividual zumoNaranja = new ProdIndividual("Zumo de naranja",
                "zumo de naranja ZUMOFIC", 20, 1, 0.1, UNIDAD);

        ProdMultiple desayuno = new ProdMultiple("Desayuno FIC",
                "cafe con leche normal, zumo de naranja y croasan", 2.8,
                0.1, TipoExistencia.UNIDAD);

        desayuno.addProductos(croasan, 1);
        desayuno.addProductos(zumoNaranja, 1);
        desayuno.addProductos(cafeLeche, 5);
    }

    @Test
    public void testMultipleValido3() {

        ProdIndividual sopa = new ProdIndividual("sopa",
                "sopa", 50, 1, 0.1, UNIDAD);
        ProdIndividual agua = new ProdIndividual("agua",
                "agua", 50, 1, 0.1, UNIDAD);

        Ingrediente arroz = new Ingrediente("arroz", "arroz redondo", 10, 0.5, 0.1,
                TipoExistencia.KILOGRAMOS);

        Ingrediente tomate = new Ingrediente("tomate", "tomate huerto", 20, 0.5, 0.1,
                TipoExistencia.UNIDAD);
        ProdMultiple arrozcubana = new ProdMultiple("arroz cubana",
                "simple", 3, 0.1, TipoExistencia.UNIDAD);

        arrozcubana.addProductos(arroz, 1);
        arrozcubana.addProductos(tomate, 2);

        ProdIndividual filete = new ProdIndividual("filete", "filete ternera", 12, 15, 0.1,
                TipoExistencia.UNIDAD);

        ProdMultiple segundo = new ProdMultiple("primero",
                "simple", 1, 0.1, TipoExistencia.UNIDAD);

        segundo.addProductos(arrozcubana, 1);
        segundo.addProductos(filete, 2);

        ProdMultiple menu = new ProdMultiple("menu",
                "simple", 7, 0.1, TipoExistencia.UNIDAD);

        menu.addProductos(sopa, 1);
        menu.addProductos(segundo, 2);
        menu.addProductos(agua, 1);

    }
    
    @Test
    public void testSetGetStock() {
        double iva = 0.10;

        Ingrediente cafe = new Ingrediente("cafe", "cafe variedad colombia JUAN VALDES", 5, 14, iva, KILOGRAMOS);
        Ingrediente leche = new Ingrediente("leche", "leche entera LA VACA", 30, 0.7, iva, LITROS);
        Ingrediente azucar = new Ingrediente("cafe", "azucar blanquilla SUCRE", 5, 0.7, iva, KILOGRAMOS);

        ProdIndividual croasan = new ProdIndividual("Croasan", "croasan PASTELERIA", 15, 1, iva, UNIDAD);
        ProdIndividual zumoNaranja = new ProdIndividual("Zumo naranja", "zumo de naranja ZUMOFIC", 20, 1, iva, UNIDAD);

        ProdMultiple cafeLeche = new ProdMultiple("Cafe leche", "cafe con leche normal", 1, iva, UNIDAD);
        cafeLeche.addProductos(cafe, 0.05);
        cafeLeche.addProductos(leche, 0.05);
        cafeLeche.addProductos(azucar, 0.01);

        ProdMultiple desayuno1 = new ProdMultiple("Desayuno FIC", "cafe con leche normal, zumo de naranja y croasan", 2.8, iva, UNIDAD);
        desayuno1.addProductos(croasan, 1);
        desayuno1.addProductos(zumoNaranja, 1);
        desayuno1.setStock(1);
        
        assertEquals(0, desayuno1.getStock(), 0.01);
    }

    @Test(expected = NullPointerException.class)
    public void testSetMesaError1() {
        double iva = 0.10;

        ProdMultiple cafeLeche = new ProdMultiple("Cafe leche", "cafe con leche normal", 1, iva, UNIDAD);
        CalculoPrecio precioDerivado = new CalculoPrecioDerivado();
        cafeLeche.setCalculoDelPrecio(precioDerivado);
        cafeLeche.calcularPrecio();

    }

    @Test(expected = IllegalArgumentException.class)
    public void testMultipleNoValido() {

        Ingrediente cafe = new Ingrediente("cafe", "cafe arabia", 0.01, 7.5, 0.1,
                TipoExistencia.KILOGRAMOS);

        Ingrediente leche = new Ingrediente("leche", "leche entera", 50, 0.9,
                0.1, TipoExistencia.LITROS);

        Ingrediente azucar = new Ingrediente("azucar", "azucar blanco", 9,
                0.54, 0.1, TipoExistencia.KILOGRAMOS);

        ProdMultiple cafeLeche = new ProdMultiple("Cafe con leche",
                "cafe con leche normal", 1, 0.1, TipoExistencia.UNIDAD);

        cafeLeche.addProductos(cafe, 0.05);
        cafeLeche.addProductos(leche, 0.05);
        cafeLeche.addProductos(azucar, 0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testStockInsuficiente1() {

        double iva = 0.10;

        ProdIndividual croasan = new ProdIndividual("Croasan", "croasan PASTELERIA", 2, 1, iva, UNIDAD);
        ProdIndividual zumoNaranja = new ProdIndividual("Zumo naranja", "zumo de naranja ZUMOFIC", 20, 1, iva, UNIDAD);

        ProdMultiple desayuno1 = new ProdMultiple("Desayuno FIC", "cafe con leche normal, zumo de naranja y croasan", 2.8, iva, UNIDAD);
        desayuno1.addProductos(croasan, 3);
        desayuno1.addProductos(zumoNaranja, 1);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testStockInsuficiente2() {
        double iva = 0.10;

        Ingrediente cafe = new Ingrediente("cafe", "cafe variedad colombia JUAN VALDES", 0.01, 14, iva, KILOGRAMOS);
        Ingrediente leche = new Ingrediente("leche", "leche entera LA VACA", 30, 0.7, iva, LITROS);
        Ingrediente azucar = new Ingrediente("cafe", "azucar blanquilla SUCRE", 5, 0.7, iva, KILOGRAMOS);

        ProdMultiple cafeLeche = new ProdMultiple("Cafe leche", "cafe con leche normal", 1, iva, UNIDAD);
        cafeLeche.addProductos(cafe, 0.05);
        cafeLeche.addProductos(leche, 0.05);
        cafeLeche.addProductos(azucar, 0.01);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testStockInsuficiente3() {

        double iva = 0.10;

        ProdIndividual croasan = new ProdIndividual("Croasan", "croasan PASTELERIA", 15, 1, iva, UNIDAD);
        ProdIndividual zumoNaranja = new ProdIndividual("Zumo naranja", "zumo de naranja ZUMOFIC", 20, 1, iva, UNIDAD);
        Ingrediente cafe = new Ingrediente("cafe", "cafe variedad colombia JUAN VALDES", 0.01, 14, iva, KILOGRAMOS);
        Ingrediente leche = new Ingrediente("leche", "leche entera LA VACA", 30, 0.7, iva, LITROS);
        Ingrediente azucar = new Ingrediente("cafe", "azucar blanquilla SUCRE", 5, 0.7, iva, KILOGRAMOS);

        ProdMultiple cafeLeche = new ProdMultiple("Cafe leche", "cafe con leche normal", 1, iva, UNIDAD);
        cafeLeche.addProductos(cafe, 0.05);
        cafeLeche.addProductos(leche, 0.05);
        cafeLeche.addProductos(azucar, 0.01);
        
        ProdMultiple desayuno1 = new ProdMultiple("Desayuno FIC", "cafe con leche normal, zumo de naranja y croasan", 2.8, iva, UNIDAD);
        desayuno1.addProductos(croasan, 1);
        desayuno1.addProductos(zumoNaranja, 1);
        desayuno1.addProductos(cafeLeche, 1);

    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testMultipleNoValido2() {

        Ingrediente cafe = new Ingrediente("cafe", "cafe arabia", 0.05, 7.5, 0.1,
                TipoExistencia.KILOGRAMOS);

        Ingrediente leche = new Ingrediente("leche", "leche entera", 50, 0.9,
                0.1, TipoExistencia.LITROS);

        Ingrediente azucar = new Ingrediente("azucar", "azucar blanco", 9,
                0.54, 0.1, TipoExistencia.KILOGRAMOS);

        ProdMultiple cafeLeche = new ProdMultiple("Cafe con leche",
                "cafe con leche normal", 1, 0.1, TipoExistencia.UNIDAD);
        cafeLeche.addProductos(cafe, 0.05);
        cafeLeche.addProductos(leche, 0.05);
        cafeLeche.addProductos(azucar, 0.01);

        ProdIndividual croasan = new ProdIndividual("Croasan",
                "croasan PASTELERIA", 15, 1, 0.1, UNIDAD);
        ProdIndividual zumoNaranja = new ProdIndividual("Zumo de naranja",
                "zumo de naranja ZUMOFIC", 20, 1, 0.1, UNIDAD);

        ProdMultiple desayuno = new ProdMultiple("Desayuno FIC",
                "cafe con leche normal, zumo de naranja y croasan", 2.8,
                0.1, TipoExistencia.UNIDAD);

        desayuno.addProductos(croasan, 1);
        desayuno.addProductos(zumoNaranja, 1);
        desayuno.addProductos(cafeLeche, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMultipleNoValido3() {

        ProdIndividual sopa = new ProdIndividual("sopa",
                "sopa", 10, 1, 0.1, UNIDAD);
        ProdIndividual agua = new ProdIndividual("agua",
                "agua", 10, 1, 0.1, UNIDAD);

        Ingrediente arroz = new Ingrediente("arroz", "arroz redondo", 10, 0.5, 0.1,
                TipoExistencia.KILOGRAMOS);

        Ingrediente tomate = new Ingrediente("tomate", "tomate huerto", 2, 0.5, 0.1,
                TipoExistencia.UNIDAD);
        ProdMultiple arrozcubana = new ProdMultiple("arroz cubana",
                "simple", 2, 0.1, TipoExistencia.UNIDAD);

        arrozcubana.addProductos(arroz, 1);
        arrozcubana.addProductos(tomate, 2);

        ProdIndividual filete = new ProdIndividual("filete", "filete ternera", 12, 15, 0.1,
                TipoExistencia.UNIDAD);

        ProdMultiple segundo = new ProdMultiple("primero",
                "simple", 1, 0.1, TipoExistencia.UNIDAD);

        segundo.addProductos(arrozcubana, 1);
        segundo.addProductos(filete, 2);

        ProdMultiple menu = new ProdMultiple("menu",
                "simple", 7, 0.1, TipoExistencia.UNIDAD);

        menu.addProductos(sopa, 1);
        menu.addProductos(segundo, 3);
        menu.addProductos(agua, 1);

    }

}
