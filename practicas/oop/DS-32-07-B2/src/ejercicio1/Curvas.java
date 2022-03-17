
package ejercicio1;

public abstract class Curvas extends FormaFigura {
    
    protected double radio;
    
    public Curvas(String nombre, int x, int y, double radio) {
        super(nombre, x, y);
        
        if (radio == 0)  {
            throw new IllegalArgumentException("radio = 0");
        } else {
            this.radio = Math.abs(radio);
        }
    }
    
    public double getRadio(){
        return radio;
    }
}