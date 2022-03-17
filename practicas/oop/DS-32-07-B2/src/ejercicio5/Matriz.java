
package ejercicio5;

import static ejercicio5.IteradorFilasOcolumnas.*;
import java.util.Iterator;

/**
 *
 * @author natalia.iglesiast
 * @author jose.naharro
 */
public class Matriz implements Iterable<Integer> {

    private int[][] elementos;
    private int filas;
    private int columnas;
    private IteradorFilasOcolumnas iterarPor;

    public Matriz(int filas, int columnas) {
        if (filas < 0 || columnas < 0) {
            throw new IllegalArgumentException();
        } else {
            this.elementos = new int[filas][columnas];
            this.filas = filas;
            this.columnas = columnas;
            this.iterarPor = FILAS; //Por defecto que itere por filas
        }
    }

    public Matriz(int[][] elementos) {
        this.filas = elementos.length;
        this.columnas = elementos[0].length;
        this.elementos = elementos;
        this.iterarPor = FILAS; //Por defecto que itere por filas
    }

    public void setIterador(IteradorFilasOcolumnas iterador) {
        this.iterarPor = iterador;
    }

    public IteradorFilasOcolumnas getIterador() {
        return this.iterarPor;
    }

    public int[][] getElementos() {
        return this.elementos;
    }

    public int getFilas() {
        return this.filas;
    }

    public int getColumnas() {
        return this.columnas;
    }

    public int getPosicionMatriz(int fila, int columna) {
        if ((fila >= 0 && fila < this.filas) && (columna >= 0 && columna < this.columnas)) {
            return this.elementos[fila][columna];
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void setPosicionMatriz(int dato, int fila, int columna) {
        if ((fila >= 0 && fila < this.filas) && (columna >= 0 && columna < this.columnas)) {
            this.elementos[fila][columna] = dato;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public int[] getFilaMatriz(int fila) {
        int[] filaM = new int[this.columnas];
        int i;

        if (fila < 0 || fila >= this.filas) {
            throw new IllegalArgumentException();
        } else {
            for (i = 0; i < this.columnas; i++) {
                filaM[i] = this.elementos[fila][i];
            }
        }

        return filaM;

    }

    public int[] getColumnaMatriz(int columna) {
        int[] columnaM = new int[this.filas];
        int i;

        if (columna < 0 || columna >= this.columnas) {
            throw new IllegalArgumentException();
        } else {
            for (i = 0; i < this.filas; i++) {
                columnaM[i] = this.elementos[i][columna];
            }
        }

        return columnaM;
    }

    public Iterator IteradorFilasColumnas() {
        return new IteradorPorFilas(this);
    }

    public Iterator IteradorColumnasFilas() {
        return new IteradorPorColumnas(this);
    }


    @Override
    public Iterator iterator() {
        if (this.iterarPor == FILAS) {
            return IteradorFilasColumnas();
        } else {
            return IteradorColumnasFilas();
        }
    }

}
