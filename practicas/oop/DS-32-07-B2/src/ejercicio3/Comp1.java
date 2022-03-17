package ejercicio3;

import static ejercicio3.Accion.*;

/**
 *
 * @author natalia.iglesiast
 * @author jose.naharro
 */
public class Comp1 implements Comportamiento{

    @Override
    public Accion accion(Jugador j, Tapete t) {
        return CUBIERTA;
    }
    
    
    
}