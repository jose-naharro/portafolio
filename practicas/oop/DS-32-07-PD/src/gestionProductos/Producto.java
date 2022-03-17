package gestionProductos;

/**
 *
 * @author natalia.iglesiast
 * @author jose.naharro
 */
public abstract class Producto {

    private String nombre;
    private String descripcion;
    private double precio;
    private double iva;
    private TipoExistencia unidades; // Todos los productos van a tener unidades, incluso los multiples, que seran UNIDAD
    TipoProducto tipoProducto;

    public Producto(String nombre, String descripcion, double precio, double iva, TipoExistencia unidades, TipoProducto tipoProducto) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.unidades = unidades;
        this.tipoProducto = tipoProducto;
        
        if (iva >= 0) {
            this.iva = iva;
        } else {
            throw new IllegalArgumentException("No se puede introducir iva negativo");
        }

        if (precio >= 0) {
            this.precio = precio;
        } else {
            throw new IllegalArgumentException("No se puede introducir precio negativo");
        }
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        if (iva >= 0) {
            this.iva = iva;
        } else {
            throw new IllegalArgumentException("No se puede introducir iva negativo");
        }
    }

    public TipoExistencia getUnidades() {
        return this.unidades;
    }

    public void setUnidades(TipoExistencia unidades) {
        this.unidades = unidades;
    }

    public abstract double getStock();//Aunque no hagan falta en todos los productos

    public abstract void setStock(double stock);

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        if (precio > 0) {
            this.precio = precio;
        } else {
            throw new IllegalArgumentException("No se puede introducir precio negativo");
        }
    }

    public TipoProducto getTipoProducto() {
        return this.tipoProducto;
    }

    public void setTipoProducto(TipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }
}
