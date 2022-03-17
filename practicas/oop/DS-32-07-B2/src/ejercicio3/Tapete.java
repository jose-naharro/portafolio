package ejercicio3;

import java.util.ArrayList;
import java.util.List;

public class Tapete {
    
    private final List<List<Carta>> cartasJugadores = new ArrayList<>();
    private final List<Boolean> jugadoresEliminados = new ArrayList<>();
     

    public void insertaJugador(List<Carta> cartasJugador, boolean eliminado) {
        cartasJugadores.add(cartasJugador);
        jugadoresEliminados.add(eliminado);
    }
    
    /**
     * Devuelve la lista de cartas de un jugador dado
     * @param jugador Índice del jugador en la lista de cartas
     * @return Lista de cartas de ese jugador
     */
    public List<Carta> cartasJugador(int jugador) {
        return cartasJugadores.get(jugador);
    }
    
   
    public boolean eliminadoJugador(int jugador) {
        return jugadoresEliminados.get(jugador);
    }
    

    public int numJugadores() {
        return jugadoresEliminados.size();
    }
}
