package gestionProductos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author natalia.iglesiast
 * @author jose.naharro
 */
public class ProdMultiple extends ProdFinal {

    private List<Producto> listadoProductos;
    private List<Double> listadoCantidades;
    private CalculoPrecio calculoDelPrecio;

    public ProdMultiple(String nombre, String descripcion, double precio, double iva, TipoExistencia unidades) {
        // Esta es la primera manera de darle precio a un producto multiple, con precio dado directamente
        super(nombre, descripcion, precio, iva, unidades, TipoProducto.MULTIPLE);
        this.listadoProductos = new ArrayList<>();
        this.listadoCantidades = new ArrayList<>();
    }

    public List<Producto> getListadoProductos() {
        return listadoProductos;
    }

    public void setListadoProductos(List<Producto> listadoProducto) {
        this.listadoProductos = listadoProducto;
    }

    public List<Double> getListadoCantidades() {
        return listadoCantidades;
    }

    public void setListadoCantidades(List<Double> listadoCantidades) {
        this.listadoCantidades = listadoCantidades;
    }

    public CalculoPrecio getCalculoDelPrecio() {
        return calculoDelPrecio;
    }

    public void setCalculoDelPrecio(CalculoPrecio calculoDelPrecio) {
        this.calculoDelPrecio = calculoDelPrecio;
    }

    public void calcularPrecio() {
        // Esta es la segunda manera de darle precio a un producto multiple,
        // precio derivado de la suma de los productos        
        double precio = this.calculoDelPrecio.calcularPrecio(this);

        super.setPrecio(precio);
    }

    public void comprobarStock(Producto producto, double cantidad) {
        if (producto.getTipoProducto() == TipoProducto.INGREDIENTE || producto.getTipoProducto() == TipoProducto.INDIVIDUAL) {
            if (producto.getStock() < cantidad) {
                throw new IllegalArgumentException();
            }
        } else {
            ProdMultiple prodMult = (ProdMultiple) producto;
            for (int i = 0; i < prodMult.getListadoProductos().size(); i++) {
                comprobarStock(prodMult.getListadoProductos().get(i), cantidad);
            }
        }
    }

    public void addProductos(Producto prod, double cantidad) {
        if (prod.getTipoProducto() == TipoProducto.INGREDIENTE || prod.getTipoProducto() == TipoProducto.INDIVIDUAL) {
            if (prod.getStock() > cantidad) {
                this.listadoProductos.add(prod);
                this.listadoCantidades.add(cantidad);
            } else {
                throw new IllegalArgumentException();
            }
        } else {
            ProdMultiple prodMult = (ProdMultiple) prod;
            for (int i = 0; i < prodMult.getListadoProductos().size(); i++) {
                comprobarStock(prodMult.getListadoProductos().get(i), cantidad);
            }
            this.listadoProductos.add(prod);
            this.listadoCantidades.add(cantidad);
        }

    }

    @Override
    public double getStock() {
        return 0;
    }

    @Override
    public void setStock(double stock) {
    }

}
