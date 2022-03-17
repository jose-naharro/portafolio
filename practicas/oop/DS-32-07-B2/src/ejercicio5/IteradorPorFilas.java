
package ejercicio5;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author natalia.iglesiast
 * @author jose.naharro
 */
public class IteradorPorFilas implements Iterator<Integer> {

    private int fila;
    private int columna;
    private Matriz matriz;

    IteradorPorFilas(Matriz matriz) {
        this.matriz = matriz;
    }

    @Override
    public boolean hasNext() {
        return ((this.fila + 1) < this.matriz.getFilas() || (this.columna + 1) < this.matriz.getColumnas());
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        } else {

            if ((this.columna + 1) == this.matriz.getColumnas()) {
                this.fila++;
                this.columna = 0;
            } else {
                this.columna++;
            }
        }
        return matriz.getElementos()[this.fila][this.columna];
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
