
package gestionTpv;

/**
 *
 * @author natalia.iglesiast
 * @author jose.naharro
 */
public class Tarjeta implements FormaPago {

    private static final Tarjeta instancia = new Tarjeta();

    public Tarjeta() {
    }

    public static Tarjeta getInstancia() {
        return instancia;
    }

    @Override
    public StringBuilder imprimePago(Comanda comanda, double entregado, double descuento) {
        double descuentoTotal = comanda.getTotalConIva() * descuento / 100;
        double total = (comanda.getTotalConIva() - comanda.getTotalConIva() * descuento / 100);
        double devolucion = entregado - total;
        
        comanda.setTotalConIva(total);
        comanda.setIvaTotal(total*0.1);
        comanda.setTotalSinIva(total-comanda.getIvaTotal());
        
        if (entregado != total || descuento < 0) {
            throw new IllegalArgumentException();
        } else {
            StringBuilder cadenaADevolver = new StringBuilder();
            cadenaADevolver.append("Pago con tarjeta de credito\n");
            if (descuento > 0) {
                cadenaADevolver.append(String.format("Descuento del %7.2f" , descuento)+"%\n");
                cadenaADevolver.append(String.format("Descuento %12.2f\n" , descuentoTotal));
            }
            cadenaADevolver.append(String.format("Total %16.2f\n" , total));
            cadenaADevolver.append(String.format("Entregado %12.2f\n" , entregado));
            cadenaADevolver.append(String.format("Devolucion %11.2f\n" , devolucion));

            return cadenaADevolver;
        }
    }

}
