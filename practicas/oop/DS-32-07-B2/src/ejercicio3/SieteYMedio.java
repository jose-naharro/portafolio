package ejercicio3;

import java.util.Arrays;


public class SieteYMedio {


    public Jugador jugar(Jugador... jugadores) {

// Tienen que existir al menos 2 jugadores
        int numJugadores = jugadores.length;
        if (numJugadores < 2) {
            throw new IllegalArgumentException("2 jugadores son necesarios");
        }

        // Variables
        int[] puntosJugadores = new int[numJugadores];
        int[] rondasJugadores = new int[numJugadores];
        Tapete t;

        // Creamos la baraja y el mazo con el que jugamos
        Baraja b = new Baraja();
        Mazo m = b.getMazo();
        boolean finalizarJugador;
        boolean finalizarPartida = false;

        int ronda = 0;
        do { // Iniciamos la ronda

            for (Jugador j : jugadores) {
                // a cada jugador se le pasa una carta cubierta
                Carta carta = m.extraerCarta();
                if (m.numCartas() == 0) {
                    finalizarPartida = true; // Si no hay cartas hay que acabar
                    break;
                }

                // Insertamos una carta cubierta en el jugador
                j.inicializaMano();
                carta.setDescubierta(false);
                j.insertaCarta(carta);
            }

            if (!finalizarPartida) { // Si quedan cartas jugamos
                System.out.println("\n---------- RONDA " + ++ronda + "----------");
                for (int i = 0; i < numJugadores; i++) { // Inicio ronda
                    System.out.println();

                    do { // Inicio jugador
                        finalizarJugador = false;

                        // Se le pide al jugador una accion
                        t = generaTapete(jugadores);
                        System.out.println(jugadores[i].getMano());
                        Accion a = jugadores[i].accion(t);
                        System.out.println("Jugador " + jugadores[i] + ": " + a);
                        
                        // Acciones a tomar según la acción
                        switch (a) {
                            case CUBIERTA:
                            case DESCUBIERTA: {
                                Carta carta = m.extraerCarta();
                                if (m.numCartas() == 0) {
                                    // No quedan cartas, se finaliza la partida
                                    // Esta ronda no se tiene en cuenta
                                    finalizarPartida = true;
                                    finalizarJugador = true;
                                }

                                // Descubrimos o cubrimos la carta
                                if (a == Accion.CUBIERTA) {
                                    carta.setDescubierta(false);
                                } else {
                                    carta.setDescubierta(true);
                                }

                                // Insertamos la carta en la mano del jugador
                                jugadores[i].insertaCarta(carta);

                                // Evaluamos su mano
                                puntosJugadores[i] = jugadores[i].evaluarMano();

                                // Si es mayor que 7.5 (75) el jugador ha perdido la ronda
                                if (puntosJugadores[i] > 75) {
                                    // Ha perdido, quedas eliminado
                                    System.out.println(carta);
                                    System.out.println("Jugador " + jugadores[i] + ": ¡¡ Eliminado !!");
                                    jugadores[i].eliminado();
                                    puntosJugadores[i] = 0;
                                    finalizarJugador = true;
                                }
                            }
                            break;

                            case PASAR: {
                                // Fin de la jugada, el jugador se queda con la puntuación que tenía
                                finalizarJugador = true;
                                puntosJugadores[i] = jugadores[i].evaluarMano();
                            }
                            break;
                        }

                    } while (finalizarJugador == false); // Finaliza jugador   

                    // Si la partida está finalizada no se muestra su estado
                    if (finalizarPartida) {
                        break;
                    } else {
                        System.out.println("Cartas: " + jugadores[i].getMano());
                        System.out.println("Cartas visibles: " + jugadores[i].getManoPublica());
                        System.out.println("Puntos : " + jugadores[i].evaluarMano());
                    }
                } // Finaliza Ronda
            }
            if (!finalizarPartida) { // Si quedan cartas jugamos
                // Decidimos el ganador de la ronda y se suman puntos
                int ganadorRonda = decidirGanador(puntosJugadores);
                if (ganadorRonda != -1) {
                    System.out.println("\nGanador ronda: " + jugadores[ganadorRonda]);
                    rondasJugadores[ganadorRonda]++;
                    if (puntosJugadores[ganadorRonda] == 75) { // punto extra por siete y medio
                        rondasJugadores[ganadorRonda]++;
                    }
                }
                System.out.println("Cartas restantes: " + m.numCartas());
                System.out.println(Arrays.toString(rondasJugadores));
            }
        } while (finalizarPartida == false);

        // Decidimos el ganador de la partida
        int ganadorPartida = decidirGanador(rondasJugadores);
        System.out.println("\n---------- FIN PARTIDA " + ++ronda + "----------");
        System.out.println(Arrays.toString(rondasJugadores));
        System.out.println("El ganador ha sido: " + jugadores[ganadorPartida]);
        return jugadores[ganadorPartida];
    }

    /**
     * Genera un tapete para comunicar a los jugadores el estado de la partida
     * @param jugadores Array con todos los jugadores de la partida
     * @return Tapete generado
     */
    private Tapete generaTapete(Jugador[] jugadores) {
        Tapete t = new Tapete();
        for (Jugador j : jugadores) {
            t.insertaJugador(j.getManoPublica(), j.isEliminado());
        }
        return t;
    }

    /**
     * Dado un array de puntuaciones decide quién es el ganador. 
     * Se usa para decidir tanto el ganador de la ronda como el de la partida
     * @param puntos Array con puntuaciones
     * @return Posición del ganador
     */
    private int decidirGanador(int[] puntos) {
        int ganador = -1;
        int puntosGanador = 0;
        for (int i = 0; i < puntos.length; i++) {
            if (puntos[i] > 0) {
                if (puntos[i] > puntosGanador) {
                    ganador = i;
                    puntosGanador = puntos[i];
                }
            }
        }
        return ganador;
    }
    
    public static void main(String [ ] args){
        Comportamiento compPepe = new Comp1();
        Jugador Pepe = new Jugador("Pepe", compPepe);

        Comportamiento compJuan = new CompTonto();
        Jugador Juan = new Jugador("Juan", compJuan);

        Comportamiento compMaria = new CompSeguro();
        Jugador Maria = new Jugador("Maria", compMaria);

        Comportamiento compLucas = new CompSeguro();
        Jugador Lucas = new Jugador("Lucas", compLucas);

        SieteYMedio instance = new SieteYMedio();
        instance.jugar(Pepe,Juan,Maria,Lucas);
        
    }
}