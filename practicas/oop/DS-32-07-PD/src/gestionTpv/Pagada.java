
package gestionTpv;

import gestionProductos.ProdFinal;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 *
 * @author natalia.iglesiast
 * @author jose.naharro
 */
public class Pagada implements FaseComanda{
    private static final Pagada instancia = new Pagada();
    private int numFactura = 0;
    
    
    public Pagada() {
    }
    
    public static Pagada getInstancia() {
        return instancia;
    }
    
    public int getNumFactura() {
        return numFactura;
    }

    public void setNumFactura(int numFactura) {
        this.numFactura = numFactura;
    }
       
    public StringBuilder pago (Comanda comanda, double entregado, double descuento){
        return comanda.getFormaPago().imprimePago(comanda, entregado, descuento);
    }

    @Override
    public String imprimir(Comanda comanda, double entregado, double descuento) {
        ProdFinal producto;
        
        StringBuilder cadenaADevolver = new StringBuilder();// Aqui meteremos toda la cadena de texto que es la cuenta
        SimpleDateFormat formateador = new SimpleDateFormat("dd'/'MM'/'yyyy HH:mm", new Locale("ES"));
        String fecha = formateador.format(comanda.getFechaYHora().getTime());
        this.setNumFactura(this.getNumFactura()+1);
        cadenaADevolver.append("\n# Factura simplificada numero " + this.getNumFactura() + "\n");
        cadenaADevolver.append("# Mesa numero " + comanda.getMesa() + "\n# " + fecha + "\n");
        cadenaADevolver.append("\n Producto         Cantidad     Precio      PVP unidad       PVP total\n");
        cadenaADevolver.append("=====================================================================\n");
        for(int i = 0; i < comanda.getListadoComanda().size(); i++){
            producto = comanda.getListadoComanda().get(i);
            cadenaADevolver.append(String.format("%-13s %12d %10.2f" , producto.getNombre(), comanda.getListadoCantidades().get(i), producto.getPrecio()));
            cadenaADevolver.append(String.format("%16.2f" , producto.getPrecio()*(1+producto.getIva())));
            cadenaADevolver.append(String.format("%16.2f\n" , producto.getPrecio()*(1+producto.getIva())*comanda.getListadoCantidades().get(i)));
        }
        cadenaADevolver.append("\n# Total\n");
        cadenaADevolver.append(String.format("Total sin impuestos %10.2f\n" ,comanda.getTotalSinIva()));
        cadenaADevolver.append(String.format("Total de impuestos %11.2f\n",comanda.getIvaTotal()));
        cadenaADevolver.append(String.format("PVP total %20.2f\n" ,comanda.getTotalConIva()));
               
       cadenaADevolver.append("\n# Forma de pago:\n");
       cadenaADevolver.append(this.pago(comanda, entregado, descuento));
       
       return cadenaADevolver.toString();
    }

    @Override
    public void addProducto(Comanda comanda, ProdFinal producto, int cantidad) {
        //En fase de pago no se pueden añadir productos
        //Lanzamos una excepcion como aviso
        throw new NoSuchMethodError(); //No se si este es el correcto.
    }

    @Override
    public void removeProducto(Comanda comanda, ProdFinal producto) {
        //En fase de pago no se pueden quitar productos
        //Lanzamos una excepcion como aviso
        throw new NoSuchMethodError(); //No se si este es el correcto.
    }

    @Override
    public void clearComanda(Comanda comanda) {
        //no se puede vaciar la comanda en fase de Pagada
        //Lanzamos una excepcion como aviso
        throw new NoSuchMethodError(); //No se si este es el correcto.
    }
    
    @Override
    public String toString(){
        return "pagada";
    }
}
