package gestionTpv;

import gestionProductos.ProdFinal;

/**
 *
 * @author natalia.iglesiast
 * @author jose.naharro
 */
public class Impagada implements FaseComanda {

    private static final Impagada instancia = new Impagada();

    public Impagada() {
    }

    public static Impagada getInstancia() {
        return instancia;
    }
    
    @Override
    public void addProducto(Comanda comanda, ProdFinal producto, int cantidad) {
        //En fase Impagada no se pueden añadir productos
        //Lanzamos una excepcion como aviso
        throw new NoSuchMethodError(); //No se si este es el correcto.
    }

    @Override
    public void removeProducto(Comanda comanda, ProdFinal producto) {
        //En fase Impagada no se pueden quitar productos
        //Lanzamos una excepcion como aviso
        throw new NoSuchMethodError(); //No se si este es el correcto.
    }

    @Override
    public String imprimir(Comanda comanda, double entregado, double decuento) {
        //Lanzamos una excepcion como aviso
        throw new NoSuchMethodError(); //No se si este es el correcto.
    }

    @Override
    public void clearComanda(Comanda comanda) {
        //En fase Impagada no se pueden eliminar todos los productos
        //Lanzamos una excepcion como aviso
        throw new NoSuchMethodError(); //No se si este es el correcto.
    }

    @Override
    public String toString(){
        return "impagada";
    }
}
