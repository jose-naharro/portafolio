
package gestionTpv;

import gestionProductos.ProdFinal;

/**
 *
 * @author natalia.iglesiast
 * @author jose.naharro
 */
public interface FaseComanda {
    
    public String imprimir(Comanda comanda, double entregado, double descuento);
    public void addProducto(Comanda comanda, ProdFinal producto, int cantidad);
    public void removeProducto(Comanda comanda, ProdFinal producto);
    public void clearComanda(Comanda comanda);
}
