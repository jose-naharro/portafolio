
package ejercicio3;

import static ejercicio3.Accion.*;

/**
 *
 * @author natalia.iglesiast
 * @author jose.naharro
 */
public class CompTonto implements Comportamiento{

    @Override
    public Accion accion(Jugador j, Tapete t) {
        return DESCUBIERTA;
    }
    
    
    
}
