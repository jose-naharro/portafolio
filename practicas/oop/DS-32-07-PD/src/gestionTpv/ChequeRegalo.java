package gestionTpv;

/**
 *
 * @author natalia.iglesiast
 * @author jose.naharro
 */
public class ChequeRegalo implements FormaPago {

    private static final ChequeRegalo instancia = new ChequeRegalo();

    public ChequeRegalo() {
    }

    public static ChequeRegalo getInstancia() {
        return instancia;
    }

    @Override
    public StringBuilder imprimePago(Comanda comanda, double entregado, double descuento) {
        double descuentoTotal = 0;
        double total = comanda.getTotalConIva();
        double devolucion = 0;

        if (entregado != total) {
            throw new IllegalArgumentException();
        } else {
            StringBuilder cadenaADevolver = new StringBuilder();
            cadenaADevolver.append("Pago con cheque regalo\n");
            cadenaADevolver.append(String.format("Total %16.2f\n", total));
            cadenaADevolver.append(String.format("Entregado %12.2f\n", entregado));
            cadenaADevolver.append(String.format("Devolucion %11.2f\n", devolucion));

            return cadenaADevolver;
        }
    }

}
