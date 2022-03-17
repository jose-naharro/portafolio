package ejercicio5;

/**
 *
 *@author natalia.iglesiast
 *@author jose.naharro
 */
public class ValoracionNoValidaException extends Exception {

    /**
     * Creates a new instance of <code>ValoracionNoValidaException</code>
     * without detail message.
     */
    public ValoracionNoValidaException() {
    }

    /**
     * Constructs an instance of <code>ValoracionNoValidaException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public ValoracionNoValidaException(String msg) {
        super(msg);
    }
}
