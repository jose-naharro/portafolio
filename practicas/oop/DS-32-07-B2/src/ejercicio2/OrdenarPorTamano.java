
package ejercicio2;

import java.util.Comparator;

/**
 *
 * @author natalia.iglesiast
 * @author jose.naharro
 */
public class OrdenarPorTamano implements Comparator<Mensaje> {
    @Override
    public int compare(Mensaje m1, Mensaje m2) {
        return m2.getTamano() - m1.getTamano();
    }
}
