
package ejercicio2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author natalia.iglesiast
 * @author jose.naharro
 */
public class Buzon {

    private ArrayList<Mensaje> buzon;

    /*
     * Metodo constructor de la clase, crea un ArrayList, vacio, de Mensaje(s) 
     */
    public Buzon() {
        this.buzon = new ArrayList<>();
    }

    /*
     * Agrega un mensaje llegado al ArrayList (de clase Mensaje) 
     */
    public void agregarMensaje(Mensaje mensaje) {
            this.buzon.add(mensaje);
            this.ordenarPor(new OrdenarPorFecha());
    }

    /*
     * Agrega una lista de mensajes al ArrayList (de clase Mensaje) 
     */
    public void agregarListaMensajes(ArrayList<Mensaje> listaMensajes) {
        this.buzon.addAll(listaMensajes);
        this.ordenarPor(new OrdenarPorFecha());
        
    }

    /*
     * Elimina un mensaje en concreto del ArrayList
     */
    public void eliminarMensaje(Mensaje mensaje) {
        this.buzon.remove(mensaje);            
    }

    /*
     * agrega un mensaje llegado al ArrayList (de clase Mensaje) 
     */
    public void eliminarListaMensajes() {
        this.buzon.clear(); //O(n)
    }

    public ArrayList<Mensaje> getCorreos() {
        return this.buzon;
    }
    /*
     * Ordena el buzon segun un comparador pasado
     */
    public void ordenarPor(Comparator comparador) {

        Collections.sort(this.buzon, comparador);

    }
}
