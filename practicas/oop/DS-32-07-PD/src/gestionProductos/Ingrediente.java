
package gestionProductos;

/**
 *
 * @author natalia.iglesiast
 * @author jose.naharro
 */
public class Ingrediente extends Producto {
    double stock;
    
    public Ingrediente(String nombre, String descripcion, double stock, double precio, double iva, TipoExistencia unidades) {
        super(nombre, descripcion, precio, iva, unidades, TipoProducto.INGREDIENTE);
        
        if (stock >= 0) 
            this.stock = stock;
        else
            throw new IllegalArgumentException("No se puede introducir stock negativo");
    }
    
    @Override
    public double getStock() {
        return stock;
    }
    
    @Override
    public void setStock(double stock) {
        if (stock >= 0) 
            this.stock = stock;
        else
            throw new IllegalArgumentException("No se puede introducir stock negativo");
    }

}
