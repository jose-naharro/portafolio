
package ejercicio1;

public class FormaFigura implements Figura{
    
    protected String nombre;
    protected int x;
    protected int y;

    public FormaFigura(String nombre, int x, int y) {
        this.nombre = nombre;
        this.x = x;
        this.y = y;
    }

    public String getNombre() {
        return nombre;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    

    
}
