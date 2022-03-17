package ejercicio3;

import static ejercicio3.Accion.*;

/**
 *
 * @author natalia.iglesiast
 * @author jose.naharro
 */
public class CompSeguro implements Comportamiento{

    @Override
    public Accion accion(Jugador j, Tapete t) {
        if (j.evaluarMano()== 75) {
        return PASAR;
        }
        else if (j.evaluarMano() < 30 ) return CUBIERTA;
        else return DESCUBIERTA;
    }
    
    
    
}