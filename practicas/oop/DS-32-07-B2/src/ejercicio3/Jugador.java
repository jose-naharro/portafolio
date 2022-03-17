package ejercicio3;

import java.util.ArrayList;
import java.util.List;


public class Jugador {

    private String name;
    private Comportamiento estrategia;
    private List<Carta> mano;
    private boolean eliminado;

    public Jugador(String name, Comportamiento estrategia) {
        this.name = name;
        this.eliminado = false;
        this.estrategia = estrategia;
    }

    public Accion accion(Tapete t) {      
        return estrategia.accion(this, t);
    }
    
    public void eliminado() {
        mano.clear();
        this.eliminado = true;
    }

    public int evaluarMano() {
        int count = 0;

        if (mano.isEmpty()) {
            return count =0 ;
        } else {
            for (int i = 0; i < mano.size(); i++) {
                count += mano.get(i).getValor() * 10;
            }
        }
        return count;
    }

    public List<Carta> getMano() {
        return mano;

    }

    public List<Carta> getManoPublica() {
        List<Carta> aux = new ArrayList<>();

        for (int i = 0; i < mano.size(); i++) {
            if (mano.get(i).getDescubierta() == true) {
                aux.add(mano.get(i));
            }
        }
        return aux;
    }

    public void inicializaMano() {
        this.mano = new ArrayList<>();
    }

    public void insertaCarta(Carta c){

        if (mano.isEmpty())
            mano.add(c);
        else{
            for (int i = 0; i < mano.size(); i++) {
                if (mano.get(i).getDescubierta() == false) {
                    if (c.getDescubierta() == false){
                        mano.get(i).setDescubierta(true);
                    }
                }
            }
            mano.add(c);
        }
            
    }

    public boolean isEliminado() {
        return this.eliminado;
    }

    public String toString() {
        return this.name;
    }
}
