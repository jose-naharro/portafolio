
package ejercicio2;

/**
 *
 * @author natalia.iglesiast
 * @author jose.naharro
 */
class Adjunto {
    private String nombre;
    private int tamano;
    
    public Adjunto(String nombre, int tamano){
        this.nombre = nombre;
        this.tamano = tamano;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTamano() {
        return tamano;
    }
    
    public void setTamano(int tamano) {
        this.tamano = tamano;
    }
}
