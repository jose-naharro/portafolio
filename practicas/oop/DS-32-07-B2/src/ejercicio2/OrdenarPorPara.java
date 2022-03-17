
package ejercicio2;

import java.util.Comparator;

/**
 *
 * @author natalia.iglesiast
 * @author jose.naharro
 */
public class OrdenarPorPara implements Comparator<Mensaje> {
    @Override
    public int compare(Mensaje m1, Mensaje m2) {
        int i = 0;
        return m1.getPara().get(i).compareTo(m2.getPara().get(i));
    }
}
