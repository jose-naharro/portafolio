
package gestionProductos;

/**
 *
 * @author natalia.iglesiast
 * @author jose.naharro
 */
public abstract class ProdFinal extends Producto {
    
    
    public ProdFinal(String nombre, String descripcion, double precio, double iva, TipoExistencia unidades,TipoProducto tipoProducto) {
        super(nombre, descripcion, precio, iva, unidades, tipoProducto);

    }
    
}
