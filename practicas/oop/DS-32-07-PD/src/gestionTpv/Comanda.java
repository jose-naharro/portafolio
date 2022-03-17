package gestionTpv;

import gestionProductos.*;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author natalia.iglesiast
 * @author jose.naharro
 */
public class Comanda {

    private ArrayList<ProdFinal> listadoComanda; //Solo se pueden vender productos finales
    private ArrayList<Integer> listadoCantidades;//Cada posicion de cantidad en la misma posicion que su correpondiente producto
    private int mesa;
    private Calendar fechaYHora;
    private double totalSinIva;
    private double totalConIva;
    private double ivaTotal;
    private FaseComanda fase;
    private FormaPago formaPago;

    public Comanda(int mesa) { //Cuando creamos una comanda solo necesitamos poner la mesa y la fecha en la que se registra
        this.listadoComanda = new ArrayList<>();
        this.listadoCantidades = new ArrayList<>();
        this.mesa = mesa;
        this.fechaYHora = Calendar.getInstance();
        this.totalSinIva = 0;
        this.totalConIva = 0;
        this.ivaTotal = 0;
        this.fase = Activa.getInstancia();
    }

    public FormaPago getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(FormaPago formaPago) {
        this.formaPago = formaPago;
    }

    public FaseComanda getFase() {
        return fase;
    }

    public void setFase(FaseComanda fase) {
        if (this.fase.toString() == "activa"){
            if (fase.toString() == "cobro") {
                this.fase = fase;
            }else if (fase.toString() == "cancelada"){
                this.fase = fase;
                this.clearComanda();
            } else if (fase.toString() == "impagada" || fase.toString() == "pagada"){
                throw new IllegalArgumentException();
            }
        } else if (this.fase.toString() == "cobro"){
            if (fase.toString() == "activa" || fase.toString() == "pagada" || fase.toString() == "impagada") {
                this.fase = fase;
            }else{
                throw new IllegalArgumentException();
            }
        } else if (this.fase.toString() == "impagada") {
            if (fase.toString() == "cobro") {
                this.fase = fase;
            }else{
                throw new IllegalArgumentException();
            }
        } else {
            throw new IllegalArgumentException();
        }

    }

    public ArrayList<ProdFinal> getListadoComanda() {
        return listadoComanda;
    }

    public int getMesa() {
        return mesa;
    }

    public void setMesa(int mesa) {
        if (mesa > 0) {
            this.mesa = mesa;
        } else {
            throw new IllegalArgumentException("No se puede introducir mesa negativa");
        }
    }

    public Calendar getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(Calendar fechaYHora) {
        this.fechaYHora = fechaYHora;
    }

    public double getTotalSinIva() {
        return totalSinIva;
    }

    public void setTotalSinIva(double totalSinIva) {
        this.totalSinIva = totalSinIva;
    }
    public double getTotalConIva() {
        return totalConIva;
    }

    public void setTotalConIva(double totalConIva) {
        this.totalConIva = totalConIva;
    }
    public ArrayList<Integer> getListadoCantidades() {
        return listadoCantidades;
    }

    public void setListadoCantidades(ArrayList<Integer> listadoCantidades) {
        this.listadoCantidades = listadoCantidades;
    }
    public double getIvaTotal() {
        return ivaTotal;
    }

    public void setIvaTotal(double ivaTotal) {
        this.ivaTotal = ivaTotal;
    }
    
    public void addProducto(ProdFinal producto, int cantidad) {
        this.fase.addProducto(this, producto, cantidad);
    }

    public void removeProducto(ProdFinal producto ) {
        this.fase.removeProducto(this, producto);
    }
    
    public void clearComanda() {
        this.fase.clearComanda(this);
    }

    public String imprimir(double entregado, double descuento) {
        return this.fase.imprimir(this, entregado, descuento);
    }

}
