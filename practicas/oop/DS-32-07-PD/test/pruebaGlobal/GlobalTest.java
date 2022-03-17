
package pruebaGlobal;

import gestionProductos.*;
import gestionTpv.*;
import static gestionProductos.TipoExistencia.*;
import org.junit.Test;

/**
 *
 * @author natalia.iglesiast
 * @author jose.naharro
 */
public class GlobalTest {

    @Test
    public void TestPruebaGlobal() {

        ///////////////////////////////////////////
        ///////// CREACION DE INGREDIENTES ////////
        ///////////////////////////////////////////
        // Primero generaremos los tipos de iva que vamos a aplicar, 
        // en hosteleria el 10% independientemente del producto, pero podemos 
        // generar otros, es un atributo del producto
        double iva = 0.10;

        Ingrediente cafe = new Ingrediente("cafe", "cafe variedad colombia JUAN VALDES", 5, 14, iva, KILOGRAMOS);
        Ingrediente leche = new Ingrediente("leche", "leche entera LA VACA", 30, 0.7, iva, LITROS);
        Ingrediente azucar = new Ingrediente("cafe", "azucar blanquilla SUCRE", 5, 0.7, iva, KILOGRAMOS);

        Ingrediente macarrones = new Ingrediente("macarrones", "macarrones LA ESPIGA", 15, 0.5, iva, KILOGRAMOS);
        Ingrediente carnePicada = new Ingrediente("carne picada", "carne picada LA GRANJA", 20, 7, iva, KILOGRAMOS);
        Ingrediente tomateFrito = new Ingrediente("tomate frito", "tomate frito GUATE", 20, 0.6, iva, LITROS);

//        Ingrediente arroz = new Ingrediente("arroz", "arroz LA VALENCIANA", 15, 0.8, iva, KILOGRAMOS);
//        Ingrediente pollo = new Ingrediente("pollo", "pollo LA GRANJA", 20, 2, iva, KILOGRAMOS);
//        Ingrediente conejo = new Ingrediente("conejo", "conejo LA GRANJA", 20, 3, iva, KILOGRAMOS);
//        Ingrediente verdurasPaella = new Ingrediente("verduras  paella", "variado de judia, haba y garrofon", 15, 1, iva, KILOGRAMOS);

        Ingrediente merluzaRodaja = new Ingrediente("merluza en rodajas", "merluza congelada EL GRUMETE", 100, 1, iva, UNIDAD);
        Ingrediente guisantes = new Ingrediente("guisantes", "gisantes EL CULTIVADOR", 5, 3, iva, KILOGRAMOS);
        Ingrediente patatas = new Ingrediente("patatas", "patatas KENNEBECK", 100, 0.8, iva, KILOGRAMOS);

        ////////////////////////////////////////////////////////
        ///// PRODUCTOS FINALES (INDIVIDUALES O MULTIPLES) /////
        ////////////////////////////////////////////////////////
        ProdIndividual croasan = new ProdIndividual("Croasan", "croasan PASTELERIA", 15, 1, iva, UNIDAD);
        ProdIndividual zumoNaranja = new ProdIndividual("Zumo naranja", "zumo de naranja ZUMOFIC", 20, 1, iva, UNIDAD);
        ProdIndividual cervezaEstrella = new ProdIndividual("Estrella 33cl", "tercio de estrella", 100, 1.5, iva, UNIDAD);
        ProdIndividual aguaPequeña = new ProdIndividual("Agua FIC 50cl", "botella agua pequeña", 100, 1, iva, UNIDAD);
        ProdIndividual copaRiojaAzpi = new ProdIndividual("Copa Rioja", "copa de Azpilicueta", 100, 1.8, iva, UNIDAD);
        ProdIndividual flan = new ProdIndividual("Flan de huevo", "flanes EL POSTRE", 50, 1, iva, UNIDAD);
//        ProdIndividual yogur = new ProdIndividual("Yogur", "yogur EL POSTRE", 50, 1, iva, UNIDAD);

        ProdMultiple cafeLeche = new ProdMultiple("Cafe leche", "cafe con leche normal", 1, iva, UNIDAD);
        cafeLeche.addProductos(cafe, 0.05);
        cafeLeche.addProductos(leche, 0.05);
        cafeLeche.addProductos(azucar, 0.01);

        ProdMultiple desayuno1 = new ProdMultiple("Desayuno FIC", "cafe con leche normal, zumo de naranja y croasan", 2.8, iva, UNIDAD);
        desayuno1.addProductos(croasan, 1);
        desayuno1.addProductos(zumoNaranja, 1);
        desayuno1.addProductos(cafeLeche, 1);
        
        ProdMultiple primero1 = new ProdMultiple("Primer plato", "plato de macasrrones+carne+tomate", 1.8, iva, UNIDAD);//podria crearse haciendo la suma de las cantidades
        primero1.addProductos(macarrones, 0.15);
        primero1.addProductos(carnePicada, 0.1);
        primero1.addProductos(tomateFrito, 0.05);

        ProdMultiple segundo1 = new ProdMultiple("Segundo plato", "plato de merluza+gusantes+patatas", 2, iva, UNIDAD);//podria crearse haciendo la suma de las cantidades
        segundo1.addProductos(merluzaRodaja, 2);
        segundo1.addProductos(guisantes, 0.05);
        segundo1.addProductos(patatas, 0.2);

        ProdMultiple menu1 = new ProdMultiple("Menu FIC", "primero, segundo, postre, cafe ", 6.5, iva, UNIDAD);
        //Añadimos los productos al menu. Se contempla la posibilidad de que hayan varias opciones por plato.
        //es decir, que se puedan escoger de entre varios primeros, segundos o postres.
        menu1.addProductos(primero1, 1);
        menu1.addProductos(segundo1, 1);
        menu1.addProductos(aguaPequeña, 1);
        menu1.addProductos(flan, 1);
        menu1.addProductos(cafeLeche, 1);


        ////////////////////////////////
        ///// CREACION DE COMANDAS /////
        /////  EN TODAS SUS FASES  /////
        /////CREAMOS FORMAS DE PAGO/////
        ////////////////////////////////
        FaseComanda faseCobro = new Cobro();
        FaseComanda faseActiva = new Activa();
        FaseComanda faseCancelada = new Cancelada();
        FaseComanda faseImpagada = new Impagada();
        FaseComanda fasePagada = new Pagada();

        FormaPago pagoEfectivo = new Efectivo();
        FormaPago pagoTarjeta = new Tarjeta();
        FormaPago pagoChequeRegalo = new ChequeRegalo();
        FormaPago pagoInvitado = new Invitacion();
        
        Comanda comanda1 = new Comanda(1);
        comanda1.addProducto(aguaPequeña, 1);
        comanda1.addProducto(desayuno1, 1);
        comanda1.addProducto(zumoNaranja, 1);

        Comanda comanda2 = new Comanda(2);
        comanda2.addProducto(aguaPequeña, 1);
        comanda2.addProducto(copaRiojaAzpi, 1);
        comanda2.addProducto(cervezaEstrella, 1);
        comanda2.addProducto(aguaPequeña, 1);

        Comanda comanda3 = new Comanda(3);
        comanda3.addProducto(desayuno1, 1);
        comanda3.addProducto(cafeLeche, 1);
        
        Comanda comanda4 = new Comanda(4);
        comanda4.addProducto(menu1, 1);
        
        Comanda comanda5 = new Comanda(5);
        comanda5.addProducto(copaRiojaAzpi, 3);
        comanda5.addProducto(cervezaEstrella, 3);
        
        Comanda comanda6 = new Comanda(6);
        comanda6.addProducto(zumoNaranja, 3);
        comanda6.addProducto(desayuno1, 1);
        
        comanda1.setFase(faseCobro);
        System.out.println(comanda1.imprimir(0, 0));
        comanda1.setFase(fasePagada);
        comanda1.setFormaPago(pagoEfectivo);
        System.out.println(comanda1.imprimir(20, 0));

        comanda2.setFase(faseCobro);
        System.out.println(comanda2.imprimir(0, 0));
        comanda2.setFormaPago(pagoTarjeta);
        comanda2.setFase(fasePagada);
        System.out.println(comanda2.imprimir(5.83, 0));
        
        comanda3.setFase(faseCobro);
        System.out.println(comanda3.imprimir(0, 0));
        comanda3.setFormaPago(pagoInvitado);
        comanda3.setFase(fasePagada);
        System.out.println(comanda3.imprimir(0, 0));
        
        comanda4.setFase(faseCobro);
        System.out.println(comanda4.imprimir(0, 0));
        comanda4.setFormaPago(pagoChequeRegalo);
        comanda4.setFase(fasePagada);
        //System.out.println(comanda2.getTotalConIva());
        System.out.println(comanda4.imprimir(7.15, 0));
        
        comanda5.setFase(faseCobro);
        comanda5.setFase(faseImpagada);
        
        comanda6.setFase(faseCancelada);

        
        ////////////////////////////
        ///// CREACION DE CAJA /////
        /////  VARIOS CIERRES  /////
        ////////////////////////////
        
        // NO CONTAMOS LAS INVITACIONES EN EL CALCULO DE INGRESOS
        // PERO SI QUE ESE STOCK HA SALIDO
        
        Tpv tpv1 = new Tpv();
        
        tpv1.addComanda(comanda1);
        tpv1.addComanda(comanda2);
        tpv1.addComanda(comanda3);
        tpv1.addComanda(comanda4);
        System.out.println(tpv1.CerrarCaja());
        
        
        //Con la caja ya cerrada el cliente paga la comanda 5 que estaba impagada
        comanda5.setFase(faseCobro);
        System.out.println(comanda5.imprimir(0, 0));
        comanda5.setFase(fasePagada);
        comanda5.setFormaPago(pagoEfectivo);
        System.out.println(comanda5.imprimir(20, 10));
        
        tpv1.addComanda(comanda5);
        System.out.println(tpv1.CerrarCaja());
        
        }
    }
