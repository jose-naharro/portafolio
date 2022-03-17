package gestionTpv;

import gestionProductos.Ingrediente;
import gestionProductos.ProdFinal;
import gestionProductos.ProdIndividual;
import gestionProductos.ProdMultiple;
import gestionProductos.Producto;
import gestionProductos.TipoProducto;

/**
 *
 * @author natalia.iglesiast
 * @author jose.naharro
 */
public class Cancelada implements FaseComanda {

    private static final Cancelada instancia = new Cancelada();

    public Cancelada() {
    }

    public static Cancelada getInstancia() {
        return instancia;
    }

    @Override
    public void addProducto(Comanda comanda, ProdFinal producto, int cantidad) {
        //En fase Cancelada no se pueden añadir productos
        //Lanzamos una excepcion como aviso
        throw new NoSuchMethodError(); //No se si este es el correcto.
    }

    @Override
    public void removeProducto(Comanda comanda, ProdFinal producto) {
        //En fase Cancelada no se pueden quitar productos
        //Lanzamos una excepcion como aviso
        throw new NoSuchMethodError(); //No se si este es el correcto.
    }

    @Override
    public String imprimir(Comanda comanda, double entregado, double decuento) {
        //Lanzamos una excepcion como aviso
        throw new NoSuchMethodError(); //No se si este es el correcto.
    }

    public void restaurarStock(Producto producto, double cantidad) {
        if (producto.getTipoProducto() == TipoProducto.INDIVIDUAL) {
            ProdIndividual prodInd = (ProdIndividual) producto;
            prodInd.setStock(prodInd.getStock() + cantidad);
        } else if (producto.getTipoProducto() == TipoProducto.INGREDIENTE) {
            Ingrediente prodIng = (Ingrediente) producto;
            prodIng.setStock(prodIng.getStock() + cantidad);
        } else {
            ProdMultiple producto2 = (ProdMultiple) producto;
            for (int i = 0; i < producto2.getListadoProductos().size(); i++) {
                restaurarStock(producto2.getListadoProductos().get(i), producto2.getListadoCantidades().get(i) * cantidad);
            }
        }
    }
    
    @Override
    public void clearComanda(Comanda comanda) {
        //Al cancelar una comanda se devuelven los prodictos al stock
        Producto producto;
        for (int i = 0; i < comanda.getListadoComanda().size(); i++) {
            producto = comanda.getListadoComanda().get(i);//Cogemos el primer producto
            if (producto.getTipoProducto() == TipoProducto.INDIVIDUAL) {
                producto.setStock(producto.getStock() + comanda.getListadoCantidades().get(i));
            } else {
                ProdMultiple producto2 = (ProdMultiple) producto;
                for (int j = 0; j < producto2.getListadoProductos().size(); j++) {
                    restaurarStock(producto2.getListadoProductos().get(j), producto2.getListadoCantidades().get(j)*comanda.getListadoCantidades().get(i));
                }
            }
        }
        comanda.getListadoComanda().clear();
        comanda.getListadoCantidades().clear();
        comanda.setTotalSinIva(0);
        comanda.setIvaTotal(0);
        comanda.setTotalConIva(0);
    }

    @Override
    public String toString(){
        return "cancelada";
    }
}
