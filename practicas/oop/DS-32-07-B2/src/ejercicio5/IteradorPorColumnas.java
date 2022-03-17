
package ejercicio5;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author natalia.iglesiast
 * @author jose.naharro
 */
public class IteradorPorColumnas implements Iterator<Integer> {

    private int fila;
    private int columna;
    //public boolean sepuedeeliminar = false;
    private final Matriz matriz;

    IteradorPorColumnas(Matriz matriz) {
        this.matriz = matriz;
    }

    @Override
    public boolean hasNext() {
        return ((this.fila + 1) < this.matriz.getColumnas() || (this.columna + 1) < this.matriz.getFilas());
    }

    @Override
    public Integer next() {

        if (!hasNext()) {
            throw new NoSuchElementException();
        } else {
            if ((this.columna + 1) == this.matriz.getFilas()) {
                this.fila++;
                this.columna = 0;
            } else {
                this.columna++;
            }
        }
        return this.matriz.getElementos()[this.columna][this.fila];
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

}
