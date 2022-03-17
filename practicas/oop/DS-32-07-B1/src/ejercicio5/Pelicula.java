package ejercicio5;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 *@author natalia.iglesiast
 *@author jose.naharro
 */
public class Pelicula {

    /* Atributos . */
    private String titulo;
    private ArrayList<Valoracion> valoraciones;

    /**
     * Inicializa una nueva pelıcula con el titulo indicado y las valoraciones
     * como una lista vacia.
     *
     * @param titulo titulo de la pelicula .
     */
    public Pelicula(String titulo) {

        this.titulo = titulo;
        this.valoraciones = new ArrayList<>();
    }

    /* Getters y setters ( no necesariamente de todos los atributos). */
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public ArrayList<Valoracion> getValoraciones() {
        return valoraciones;
    }

    public void setValoraciones(ArrayList<Valoracion> valoraciones) {
        this.valoraciones = valoraciones;
    }

    /**
     * Inserta una nueva valoracion en la primera posicion vacia. Se permite
     * insertar NO_VALORADA.
     *
     * @param valoracion valoracion a insertar.
     */
    public void insertaValoracion(Valoracion valoracion) {
        this.valoraciones.add(valoracion);

    }

    /**
     * Comprueba si esta pelicula tiene alguna valoracion. ́
     *
     * @return true si y solo si existe alguna valoracion que sea distinta de
     * NO_VALORADA.
     */
    private boolean estaValorada() {
        boolean valorada = false;

        if (!this.valoraciones.isEmpty()) { //Si la lista no esta vacia, veo que valoraciones tiene
            for (int i = 0; i < this.valoraciones.size(); i++) {
                if (this.valoraciones.get(i) != Valoracion.NO_VALORADA) {
                    valorada = true;
                    break;//Si encuentra alguna distita de NO_VALORADA que salga del for
                }
            }
        }
        return valorada;
    }

    /**
     * Obtiene la valoracion maxima de esta pelicula.
     *
     * @return valoracion maxima; o NO_VALORADA si no existen valoraciones
     */
    public Valoracion valoracionMaxima() {
        Valoracion valoracionMaxima = null;
        int size = this.valoraciones.size();

        if (!estaValorada()) {
            valoracionMaxima = Valoracion.NO_VALORADA;
        } else {
            Collections.sort(this.valoraciones);//Ordenamos
            valoracionMaxima = this.valoraciones.get(size - 1);//Cogemos el mayor
        }

        return valoracionMaxima;

    }

    /**
     * Calcula la valoracion media de esta pelicula. Los NO_VALORADA no cuentan.
     *
     * @return valor decimal de la valoracion numerica media de esta pelıcula.
     * @exception ValoracionNoValidaException si no existen valoraciones.
     */
    public double valorNumericoMedio() throws ValoracionNoValidaException {
        double media = 0;
        double sumatorio = 0;
        int noValoradas = 0;

        for (int i = 0; i < this.valoraciones.size(); i++) {
            sumatorio += valoraciones.get(i).valor;
            if (this.valoraciones.get(i) == Valoracion.NO_VALORADA) {
                noValoradas++;//Contador para saber cuantas NO_VALORADAS hay, 
            }                 //ya que no cuentan para la media
        }

        if (this.valoraciones.isEmpty()) {
            throw new ValoracionNoValidaException();//No existen valoraciones
        } else if ((this.valoraciones.size() - noValoradas) == 0) {
            throw new ValoracionNoValidaException();//Division por 0
        } else {
            return media = sumatorio / (this.valoraciones.size() - noValoradas);
        }
    }
}
