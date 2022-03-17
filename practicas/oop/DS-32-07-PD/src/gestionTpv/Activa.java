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
public class Activa implements FaseComanda {

    private static final Activa instancia = new Activa();

    public Activa() {
    }

    public static Activa getInstancia() {
        return instancia;
    }

    public void eliminarStock(Producto producto, double cantidad) {

        if (producto.getTipoProducto() == TipoProducto.INDIVIDUAL) {
            ProdIndividual prodInd = (ProdIndividual) producto;
            prodInd.setStock(prodInd.getStock() - cantidad);
        } else if (producto.getTipoProducto() == TipoProducto.INGREDIENTE) {
            Ingrediente prodIng = (Ingrediente) producto;
            prodIng.setStock(prodIng.getStock() - cantidad);
        } else {
            ProdMultiple prodMult = (ProdMultiple) producto;
            for (int i = 0; i < prodMult.getListadoProductos().size(); i++) {
                eliminarStock(prodMult.getListadoProductos().get(i), prodMult.getListadoCantidades().get(i) * cantidad);
            }
        }
    }

    @Override
    public void addProducto(Comanda comanda, ProdFinal producto, int cantidad) {

        if (cantidad <= 0) {
            throw new IllegalArgumentException("No se puede introducir cantidad negativa o cero");
        } else {
            if (comanda.getListadoComanda().contains(producto)) {
                for (int i = 0; i < comanda.getListadoComanda().size(); i++) {
                    if (comanda.getListadoComanda().get(i) == producto) {
                        if (producto.getTipoProducto() == TipoProducto.INDIVIDUAL) {
                            ProdIndividual productoInd = (ProdIndividual) producto;
                            productoInd.setStock(productoInd.getStock() - cantidad);
                        } else {
                            ProdMultiple producto2 = (ProdMultiple) producto;
                            for (int j = 0; j < producto2.getListadoProductos().size(); j++) {
                                eliminarStock(producto2.getListadoProductos().get(j), cantidad);
                            }
                        }
                        comanda.getListadoCantidades().set(i, comanda.getListadoCantidades().get(i) + cantidad);
                    }
                }
            } else {
                if (producto.getTipoProducto() == TipoProducto.INDIVIDUAL) {
                    ProdIndividual productoInd = (ProdIndividual) producto;
                    productoInd.setStock(productoInd.getStock() - cantidad);
                } else {
                    ProdMultiple producto2 = (ProdMultiple) producto;
                    for (int j = 0; j < producto2.getListadoProductos().size(); j++) {
                        eliminarStock(producto2.getListadoProductos().get(j), cantidad);
                    }
                }
                comanda.getListadoComanda().add(producto);
                comanda.getListadoCantidades().add(cantidad);
            }
            comanda.setTotalSinIva(comanda.getTotalSinIva() + producto.getPrecio() * cantidad);
            comanda.setIvaTotal(comanda.getIvaTotal() + (cantidad * producto.getPrecio() * producto.getIva()));
            comanda.setTotalConIva(comanda.getTotalSinIva() + comanda.getIvaTotal());
        }

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
    public void removeProducto(Comanda comanda, ProdFinal producto) {

        if (!comanda.getListadoComanda().contains(producto)) {
            throw new IllegalArgumentException();
        } else {
            for (int i = 0; i < comanda.getListadoComanda().size(); i++) {
                if (comanda.getListadoComanda().get(i) == producto) {
                    if (producto.getTipoProducto() == TipoProducto.INDIVIDUAL) {
                        ProdIndividual productoInd = (ProdIndividual) producto;
                        productoInd.setStock(productoInd.getStock() + comanda.getListadoCantidades().get(i));
                    } else {
                        ProdMultiple producto2 = (ProdMultiple) producto;
                        for (int j = 0; j < producto2.getListadoProductos().size(); j++) {
                            restaurarStock(producto2.getListadoProductos().get(j), producto2.getListadoCantidades().get(j));
                        }
                    }
                }
            }
        }
        comanda.setTotalSinIva(comanda.getTotalSinIva() - producto.getPrecio());
        comanda.setIvaTotal(comanda.getIvaTotal() - producto.getPrecio() * producto.getIva());
        comanda.setTotalConIva(comanda.getTotalConIva() - comanda.getIvaTotal());
    }

    @Override
    public String imprimir(Comanda comanda, double entregado, double decuento) {
        throw new NoSuchMethodError();
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
                    restaurarStock(producto2.getListadoProductos().get(j), producto2.getListadoCantidades().get(j) * comanda.getListadoCantidades().get(i));
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
    public String toString() {
        return "activa";
    }
}
