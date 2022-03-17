
package gestionProductos;

/**
 *
 * @author Jose Carlos
 */
public class CalculoPrecioDerivado implements CalculoPrecio {

    @Override
    public double calcularPrecio(ProdMultiple producto) {
        double precio = 0;
        double porcentajeDescuento = 0.10;
        
        if (producto.getListadoProductos().isEmpty()) {
            throw new NullPointerException();
        } else {
            for (int i = 0; i < producto.getListadoProductos().size(); i++) {
                precio += (producto.getListadoProductos().get(i).getPrecio() * producto.getListadoCantidades().get(i));
            }
        }
        precio *= (1 - porcentajeDescuento);
        
        return precio;
    }
    
    @Override
    public String toString(){
        return "precio derivado";
    }
    
}
