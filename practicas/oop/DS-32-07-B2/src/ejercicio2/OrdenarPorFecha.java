
package ejercicio2;

import java.util.Comparator;

/**
 *
 * @author natalia.iglesiast
 * @author jose.naharro
 */
public class OrdenarPorFecha implements Comparator<Mensaje> {
    @Override
    public int compare(Mensaje m1, Mensaje m2) {
        return m1.getFecha().compareTo(m2.getFecha());
    }
}
