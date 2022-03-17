package gestionProductos;

/**
 *
 * @author natalia.iglesiast
 * @author jose.naharro
 */
public class ProdIndividual extends ProdFinal {

    private double stock;

    public ProdIndividual(String nombre, String descripcion, double stock, double precio, double iva, TipoExistencia unidades) {
        super(nombre, descripcion, precio, iva, unidades,TipoProducto.INDIVIDUAL);
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
        if (stock >= 0) {
            this.stock = stock;
        } else {
            throw new IllegalArgumentException("No se puede introducir stock negativo");
        }
    }

}
