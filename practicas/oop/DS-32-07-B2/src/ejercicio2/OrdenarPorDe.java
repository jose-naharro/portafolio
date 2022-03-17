
package ejercicio2;

import java.util.Comparator;

/**
 *
 * @author natalia.iglesiast
 * @author jose.naharro
 */
public class OrdenarPorDe implements Comparator<Mensaje> {
    @Override
    public int compare(Mensaje m1, Mensaje m2) {
        return m1.getDe().compareTo(m2.getDe());
    }
}
