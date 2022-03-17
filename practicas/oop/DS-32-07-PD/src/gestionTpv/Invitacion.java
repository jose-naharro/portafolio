package gestionTpv;

/**
 *
 * @author natalia.iglesiast
 * @author jose.naharro
 */
public class Invitacion implements FormaPago {

    private static final Invitacion instancia = new Invitacion();

    public Invitacion() {
    }

    public static Invitacion getInstancia() {
        return instancia;
    }

    @Override
    public StringBuilder imprimePago(Comanda comanda, double entregado, double descuento) {
        double descuentoTotal = comanda.getTotalConIva();
        double total = 0;
        double devolucion = 0;
        double descuento100 = 100;
        comanda.setTotalConIva(total);
        comanda.setIvaTotal(0);
        comanda.setTotalSinIva(0);

        StringBuilder cadenaADevolver = new StringBuilder();
        cadenaADevolver.append("Cliente invitado\n");
        cadenaADevolver.append(String.format("Descuento del %7.2f", descuento100) +"%\n");
        cadenaADevolver.append(String.format("Descuento %12.2f\n", descuentoTotal));
        cadenaADevolver.append(String.format("Total %16.2f\n", total));
        cadenaADevolver.append(String.format("Entregado %12.2f\n", total));
        cadenaADevolver.append(String.format("Devolucion %11.2f\n", devolucion));

        return cadenaADevolver;
    }

}
