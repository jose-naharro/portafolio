package ejercicio2;

/**
 *
 *@author natalia.iglesiast
 *@author jose.naharro
 */
public class CribaEratostenes {

    /*
     * Array de longitud limitada en el que una posicion i es true
     * si y solo si el numero i es primo, y false en caso contrario.
     */
    private final boolean[] criba;

    /**
     * Inicializa una nueva criba de Eratostenes con el tamano indicado por
     * parametro. Una posicion i es true si y solo si el numero i es primo. El 0
     * y el 1 se consideran no primos .
     *
     * @param tamano tamano de la criba.
     */
    public CribaEratostenes(int tamano) {
        int i;
        int j;

        boolean arrayBoolEnteros[];
        arrayBoolEnteros = new boolean[tamano + 1];

        for (i = 0; i <= tamano; i++) {
            arrayBoolEnteros[i] = true;
        }

        arrayBoolEnteros[0] = false;
        arrayBoolEnteros[1] = false;

        for (i = 2; i <= 9; i++) {
            for (j = 2; j <= tamano; j++) {
                if (i != j) {
                    if (j % i == 0) {
                        arrayBoolEnteros[j] = false;
                    }
                }
            }
        }
        this.criba = arrayBoolEnteros;
    }

    /**
     * Comprueba si el numero pasado por parametro es primo.
     *
     * @param n numero a comprobar.
     * @return true si y solo si el parametro esta marcado como primo en esta
     * criba.
     */
    public boolean esPrimo(int n) {
        if ((n >= this.criba.length) || (n < 0)) {
            throw new IllegalArgumentException();
        } else {
            return this.criba[n];
        }
    }

    /**
     * Obtiene el numero primo mas cercano que sea menor que el numero pasado
     * como referencia. Lanza una excepcion no comprobada si no existe ningun
     * primo.
     *
     * @param n numero a tomar como referencia.
     * @return numero primo mas cercano que sea estrictamente menor que el
     * numero pasado por parametro.
     */
    public int anteriorPrimo(int n) { //creo que puede estar mejor
        int i, k = 0;

        if ((n <= 2) || (n > this.criba.length)) {
            throw new IllegalArgumentException();
        } else {
            for (i = n - 1; i >= 2; i--) { // Ojo, es n-1 porque puede que el n sea primo!!
                if (this.criba[i]) {
                    k = i; //aqui pararia la ejecucion del bucle for
                    break;
                }
            }
            return k;
        }
    }

    /**
     * Obtiene el numero primo mas cercano que sea mayor que el numero pasado
     * como referencia. Lanza una excepcion no comprobada si nos hemos salido de
     * los limites de esta criba.
     *
     * @param n numero a tomar como referencia.
     * @return numero primo mas cercano que sea estrictamente mayor que el
     * numero pasado por parametro.
     */
    public int siguientePrimo(int n) {
        int i, k = 0;

        if ((n >= this.criba.length) || (n < 0)) {
            throw new IllegalArgumentException();
        } else {
            for (i = n + 1; i <= (this.criba.length-1); i++) {// Ojo, es n+1 porque puede que el n sea primo!!
                if (this.criba[i]) {
                    k = i; //aqui pararia la ejecucion del bucle for
                    break;
                }
            }
            if (k==0){// k=0 si no entra en el if anterior y cambia el valor a k=i, es decir, se sale de los limites de la criba
                throw new IllegalArgumentException();
            }else{
                return k;
            }
        }
    }

  
}
