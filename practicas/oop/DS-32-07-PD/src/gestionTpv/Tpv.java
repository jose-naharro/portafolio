package gestionTpv;

import gestionProductos.ProdFinal;
import gestionProductos.Producto;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author natalia.iglesiast
 * @author jose.naharro
 */
public class Tpv {

    private List<Comanda> listadoComandas;
    private double ingresos = 0;
    private double impuestos = 0;
    ArrayList<ProdFinal> listadoProductos = new ArrayList<>();
    ArrayList<Integer> listadoCantidades = new ArrayList<>();

    public Tpv() {
        this.listadoComandas = new ArrayList<>();
    }

    public List<Comanda> getListadoComandas() {
        return listadoComandas;
    }

    public double getIngresos() {
        return ingresos;
    }

    public void setIngresos(double ingresos) {
        this.ingresos = ingresos;
    }

    public double getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(double impuestos) {
        this.impuestos = impuestos;
    }

    public void addComanda(Comanda comanda) {
        if (comanda.getFase().toString() == "pagada") {
            this.listadoComandas.add(comanda);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void removeComanda(Comanda comanda) {
        if (this.listadoComandas.contains(comanda)) {
            for (int i = 0; i < this.listadoComandas.size(); i++) {
                if (comanda == this.listadoComandas.get(i)) {
                    this.listadoComandas.remove(i);
                }
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    public String CerrarCaja() {
        Producto producto;
        ProdFinal productoAnadir;
        int cantidadAnadir;
        Comanda comanda;

        for (int i = 0; i < this.listadoComandas.size(); i++) {
            comanda = this.getListadoComandas().get(i);
            for (int j = 0; j < comanda.getListadoComanda().size(); j++) {
                productoAnadir = comanda.getListadoComanda().get(j);
                cantidadAnadir = comanda.getListadoCantidades().get(j);
                if (!listadoProductos.contains(productoAnadir)) {
                    listadoProductos.add(productoAnadir);
                    listadoCantidades.add(cantidadAnadir);
                } else {
                    for (int k = 0; k < listadoProductos.size(); k++) {
                        if (listadoProductos.get(k).getNombre() == productoAnadir.getNombre()) {
                            listadoCantidades.set(k, listadoCantidades.get(k) + cantidadAnadir);
                        }
                    }
                }
            }
            this.ingresos = this.getIngresos() + comanda.getTotalSinIva();
            this.impuestos = this.getImpuestos() + comanda.getIvaTotal();

        }

        StringBuilder cierreCaja = new StringBuilder();// Aqui meteremos toda la cadena de texto que es la cuenta
        SimpleDateFormat formateador = new SimpleDateFormat("dd'/'MM'/'yyyy HH:mm", new Locale("ES"));
        String fecha = formateador.format(Calendar.getInstance().getTime());

        cierreCaja.append("\n# Cierre de caja\n");
        cierreCaja.append("# " + fecha + "\n");
        cierreCaja.append("\nProducto         Cantidad\n");
        cierreCaja.append("=========================\n");
        for (int i = 0; i < listadoProductos.size(); i++) {
            producto = listadoProductos.get(i);
            cierreCaja.append(String.format("%-13s %11d\n", producto.getNombre(), listadoCantidades.get(i)));
        }
        cierreCaja.append(String.format("%-13s %12.2f\n", "\n# Total", ingresos + impuestos));
        cierreCaja.append(String.format("%-13s %11.2f\n", "Impuestos", impuestos));
        cierreCaja.append(String.format("%-13s %11.2f\n", "Ingresos", ingresos));

        listadoProductos.clear();
        listadoCantidades.clear();
        listadoComandas.clear();
        ingresos = 0;
        impuestos = 0;

        return cierreCaja.toString();
    }
}
