
package gestionTpv;

import gestionProductos.ProdFinal;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 *
 * @author natalia.iglesiast
 * @author jose.naharro
 */
public class Cobro implements FaseComanda{
    
    private static final Cobro instancia = new Cobro();
    
    public Cobro() {
    }

    public static Cobro getInstancia() {
        return instancia;
    }
    
    @Override
    public String imprimir(Comanda comanda, double entregado, double descuento) {
        ProdFinal producto;
        
        StringBuilder cadenaADevolver = new StringBuilder();// Aqui meteremos toda la cadena de texto que es la cuenta
        SimpleDateFormat formateador = new SimpleDateFormat("dd'/'MM'/'yyyy HH:mm", new Locale("ES"));
        String fecha = formateador.format(comanda.getFechaYHora().getTime());
        
        cadenaADevolver.append("\n# Mesa numero " + comanda.getMesa() + "\n# " + fecha + "\n");
        cadenaADevolver.append("\n Producto         Cantidad     Precio      PVP unidad       PVP total\n");
        cadenaADevolver.append("=====================================================================\n");
        for(int i = 0; i < comanda.getListadoComanda().size(); i++){
            producto = comanda.getListadoComanda().get(i);
            cadenaADevolver.append(String.format("%-13s %12d %10.2f" , producto.getNombre(), comanda.getListadoCantidades().get(i), producto.getPrecio()));
            cadenaADevolver.append(String.format("%16.2f" , producto.getPrecio()*(1+producto.getIva())));
            cadenaADevolver.append(String.format("%16.2f\n" , producto.getPrecio()*(1+producto.getIva())*comanda.getListadoCantidades().get(i)));
        }
        cadenaADevolver.append("\n# Pendiente de cobro\n");
        cadenaADevolver.append(String.format("Total sin impuestos %10.2f\n" ,comanda.getTotalSinIva()));
        cadenaADevolver.append(String.format("Total de impuestos %11.2f\n",comanda.getIvaTotal()));
        cadenaADevolver.append(String.format("PVP total %20.2f\n" ,comanda.getTotalConIva()));
        
        return cadenaADevolver.toString();
    }

    @Override
    public void addProducto(Comanda comanda, ProdFinal producto, int cantidad) {
        //En fase de cobro no se pueden añadir productos
        //Lanzamos una excepcion como aviso
        throw new NoSuchMethodError(); //No se si este es el correcto.
    }

    @Override
    public void removeProducto(Comanda comanda, ProdFinal producto) {
        //En fase de cobro no se pueden quitar productos
        //Lanzamos una excepcion como aviso
        throw new NoSuchMethodError(); //No se si este es el correcto.
    }

    @Override
    public void clearComanda(Comanda comanda) {
        //No se puede vaciar la comanda en fase de cobro
        //Lanzamos una excepcion como aviso
        throw new NoSuchMethodError(); //No se si este es el correcto.
    }

    @Override
    public String toString(){
        return "cobro";
    }
}
