package ejercicio5;

/**
 *
 *@author natalia.iglesiast
 *@author jose.naharro
 */
public enum Valoracion {

    NO_VALORADA(0),
    PESIMA(0),
    MALA(2),
    MEDIOCRE(4),
    BUENA(6),
    EXCELENTE(8),
    OBRA_MAESTRA(10);

    public final int valor;

    Valoracion(int valor) {
        this.valor = valor;
    }

    public boolean esMayor(Valoracion valoracion) throws ValoracionNoValidaException {

        boolean esMayor = false;

        if ((this == NO_VALORADA) || (valoracion == NO_VALORADA)) {
            throw new ValoracionNoValidaException("Excepcion, NO_VALORADA");
        } else {
            if (this.valor > valoracion.valor) {
                esMayor = true;
            }
        }
        return esMayor;
    }
}
